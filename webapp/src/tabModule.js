import Chart from "./chart";

import React from 'react';
import Highcharts from "highcharts";
import ReactDOM from 'react-dom';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';


class TabModule {
    renderTab(tabId, chartNum) {
        console.log(tabId);

        var element;
        var mock = new MockAdapter(axios);
        mock.onGet("/"+tabId+"/stocks").reply(200, [
            [
                {
                    price: 15,
                    ticker: "KGSM",
                    increase: 0.12,
                    indexName: "KreyGasm",
                    timeStamp: "2017-03-19T11:00:00"
                },
                {
                    price: 16.8,
                    ticker: "KGSM",
                    increase: 1.8,
                    indexName: "KreyGasm",
                    timeStamp: "2017-03-19T11:05:00"
                },
                {
                    price: 15,
                    ticker: "KGSM",
                    increase: -1.8,
                    indexName: "KreyGasm",
                    timeStamp: "2017-03-19T11:10:00"
                },
                {
                    price: 14.2,
                    ticker: "KGSM",
                    increase: -0.8,
                    indexName: "KreyGasm",
                    timeStamp: "2017-03-19T11:15:00"
                },

            ]
        ]);
        var nextDate = "2017-03-19T11:20:00";
        mock.onGet("/"+tabId+"/stocks/recent").reply(200, [
            {
                price: Math.random()*20,
                ticker: "KGSM",
                increase: 0.2,
                indexName: "KreyGasm",
                timeStamp: nextDate
            }
        ]);

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
                    pointStart: 2010
                }
            }
        };

        axios.get("/"+tabId + "/stocks")
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
                    for(var j = 0; j < responseData[i].length; j++) {
                        var date = new Date(responseData[i][j].timeStamp);
                        data[j] = {
                            x: date.valueOf(),
                            y: responseData[i][j].price
                        }
                    }
                    initialData[i].data = data;
                    initialData[i].increase = responseData[i][j-1].increase;
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

        setInterval(function() {
            axios.get("/"+tabId + "/stocks/recent")
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
                        nextDate = new Date(new Date(nextDate).valueOf()+(1000*60*5)).toISOString();
                        mock.onGet("/"+tabId+"/stocks/recent").reply(200, [
                            {
                                price: Math.random()*20,
                                ticker: "KGSM",
                                increase: 0.2,
                                indexName: "KreyGasm",
                                timeStamp: nextDate
                            }
                        ]);
                        series.addPoint(newPoint, true, shift);

                    }
                })
        }, 4000)

      }

}

export default TabModule;