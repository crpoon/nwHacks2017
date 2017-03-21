import Chart from "./chart";

import React from 'react';
import Highcharts from "highcharts";
import ReactDOM from 'react-dom';
import axios from 'axios';
//import MockAdapter from 'axios-mock-adapter';


class TabModule {
    renderTab(tabId, chartNum) {

        var apiInstance = axios.create({
            baseURL: "http://localhost:8080/",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        console.log(tabId);
        var element;

        var initialData = [];

        var config = {
            chart: {
                zoomType: "x"
            },
            credits: {
                enabled: false
            },
            title: {
                text: null
            },

            xAxis: {
                gridLineWidth: 1,
                type: "datetime"
            },
            yAxis: {
                opposite: true,
                title:  {
                    text: "Rare Pepes (RP)"
                }
            },
            legend: {
                floating: true,
                useHTML: true,
                layout: 'vertical',
                align: 'left',
                verticalAlign: 'top',
                labelFormatter: function() {
                    return "<div id='" + this.options.name + "'class='legend-label " + tabId + "'><span id='current-price'></span><span id='difference'></span><div class='pepecoin'></div><span>" + this.options.displayName + "</span></div>";
                }
            },

            plotOptions: {
                series: {

                }
            }
        };

        if(tabId === "twitch") {
            var startDate = new Date("2017-01-17T00:00:00.000Z");
            var endDate = new Date("2017-01-19T00:00:00.000Z");
            config.xAxis.min = startDate.valueOf();
            config.xAxis.max = endDate.valueOf();
        }

        apiInstance.get(tabId + "/stocks")
            .then(function(response) {
                console.log(response);
                var responseData = response.data;
                for(var i = 0; i < responseData.length; i++) {
                    if(responseData[i] && responseData[i][0] && responseData[i][0].indexName) {
                        initialData[i] = {
                                        id: responseData[i][0].indexName,
                                        name: responseData[i][0].indexName,
                                        displayName: responseData[i][0].indexName + " (" + responseData[i][0].ticker + ")",
                                        type: "line"
                                    }
                                    var data = [];
                                    for(var j = responseData[i].length - 1; j >= 0; j--) {
                                        var date = new Date(responseData[i][j].timeStamp);
                                        data[responseData[i].length - j - 1] = {
                                            x: date.valueOf(),
                                            y: responseData[i][j].price
                                        }
                                    }
                                    initialData[i].data = data;
                                    initialData[i].increase = responseData[i][j+1].increase;
                    }else {
                        initialData[i] = {};
                    }

                }
                config.series = initialData;

                console.log(config);

                // Create and render element
                element = React.createElement(Chart, { container: 'chart-'+tabId, options: config });
                console.log(Highcharts.charts);
                ReactDOM.render(element, document.getElementById(tabId));

            })
            .catch(function(error) {
                console.log(error);
            });


                        if(tabId !== "twitch") {
                            setInterval(function() {
                                apiInstance.get(tabId + "/stocks/recent")
                                    .then(function(response) {
                                        console.log(response);
                                        var responseData = response.data;
                                        for(var i = 0; i < responseData.length; i++) {
                                            var name = responseData[i].indexName;

                                            console.log(Highcharts.charts);
                                            var series = Highcharts.charts[chartNum].get(name);
                                            var date = new Date(responseData[i].timeStamp);
                                            var newPoint = {
                                                x: date.valueOf(),
                                                y: responseData[i].price
                                            }

                                            var legend;

                                            var legendList = document.getElementsByClassName("legend-label "+ tabId);

                                            for(var k = 0; k < legendList.length; k++) {
                                                if(legendList[k].id === name) {
                                                    legend = legendList[k];
                                                    break;
                                                }
                                            }
                                            legend.children[0].innerHTML = responseData[i].price.toFixed(2);
                                            legend.children[1].innerHTML = " (" + responseData[i].increase.toFixed(2) + ") ";
                                            if(responseData[i].increase >= 0) {
                                                legend.children[1].className = "increase";
                                            } else {
                                                legend.children[1].className = "decrease";
                                            }

                                            console.log(tabId);

                                            if (series && series.data) {
                                                 var shift = series.data.length> 240;
                                                 series.addPoint(newPoint, true, shift);
                                            }


                                        }
                                    })
                            }, 15000)
                        }

      }

}

export default TabModule;