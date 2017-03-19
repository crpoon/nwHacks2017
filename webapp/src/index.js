import '../images/Kappa.png'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css'

import React from 'react';
import Chart from "./chart";
import Highcharts from "highcharts";
import ReactDOM from 'react-dom';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import './index.css';

var element;
var mock = new MockAdapter(axios);
mock.onGet("instagram/stocks").reply(200, [
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
var counter = 1;
var nextDate = "2017-03-19T11:20:00";
mock.onGet("instagram/stocks/recent").reply(200, [
    {
        price: Math.random()*20,
        ticker: "KGSM",
        increase: 0.2,
        indexName: "KreyGasm",
        timeStamp: nextDate
    }
]);

var initialData = [];

var options = {
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
        layout: 'vertical',
        align: 'left',
        verticalAlign: 'top'
    },

    plotOptions: {
        series: {
            pointStart: 2010
        }
    }
};

var currentId = document.getElementsByClassName("tab-pane active")[0].id;
axios.get(currentId + "/stocks")
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
        options.series = initialData;

        console.log(options);

        // Create and render element
        element = React.createElement(Chart, { container: 'chart', options: options });
        ReactDOM.render(element, document.getElementsByClassName('tab-pane active')[0]);

    })
    .catch(function(error) {
        console.log(error);
    });

setInterval(function() {
    currentId = document.getElementsByClassName("tab-pane active")[0].id;
    var length = Highcharts.charts.length;
    console.log(Highcharts.charts[length-1]);
    axios.get(currentId + "/stocks/recent")
        .then(function(response) {
            console.log(response);
            var responseData = response.data;
            for(var i = 0; i < responseData.length; i++) {
                var name = responseData[i].indexName;
                var series = Highcharts.charts[length-1].get(name);
                var date = new Date(responseData[i].timeStamp);
                var newPoint = {
                    x: date.valueOf(),
                    y: responseData[i].price
                }
                var shift = series.data.length > 36;
                nextDate = new Date(new Date(nextDate).valueOf()+(1000*60*5)).toISOString()
                mock.reset();
                mock.onGet("instagram/stocks/recent").reply(200, [
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
}, 1000)


