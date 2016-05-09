$(function () {
    console.log(data);
    $('#chart').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: 'News mentions'
        },
        xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Time mentioned'
            }
        },
        series: [{
            name: keyWord,
            data: data
        }]
    });
});