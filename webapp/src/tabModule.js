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
                title: null
            },
            legend: {
                floating: true,
                useHTML: true,
                layout: 'vertical',
                align: 'left',
                verticalAlign: 'top',
                labelFormatter: function() {
                    return "<div class'legend-label'><span>" + this.options.displayName + "</span><div class='pepecoin'></div></div>";
                }
            },

            plotOptions: {
                series: {

                }
            }
        };

        apiInstance.get(tabId + "/stocks")
            .then(function(response) {
                console.log(response);
                var responseData = response.data;
                for(var i = 0; i < responseData.length; i++) {
                    initialData[i] = {
                        id: responseData[i][0].indexName,
                        name: responseData[i][0].indexName,
                        displayName: responseData[i][0].indexName + " (" + responseData[i][0].ticker + ")"
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

                                            var series = Highcharts.charts[chartNum].get(name);
                                            var date = new Date(responseData[i].timeStamp);
                                            var newPoint = {
                                                x: date.valueOf(),
                                                y: responseData[i].price
                                            }
                                            var shift = series.data.length > 36;
                                            series.addPoint(newPoint, true, shift);

                                        }
                                    })
                            }, 30000)
                        }

      }

}

export default TabModule;