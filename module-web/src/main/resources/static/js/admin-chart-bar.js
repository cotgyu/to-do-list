// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var ctx = document.getElementById("userBoardStatsChart");

const countData = [];
const userNameData = [];


var barChartMain = {
    init: function () {

        $.ajax({
            type: 'GET',
            url: '/api/admin/userBoardStatistics/',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'

        }).done(function (data) {

            for (var i = 0; i <= data.result.length; i++) {
                countData[i] = data.result[i].count;
                userNameData[i] = data.result[i].name;
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
}

barChartMain.init();

var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: userNameData,
        datasets: [{
            label: "Boards",
            backgroundColor: "rgba(2,117,216,1)",
            borderColor: "rgba(2,117,216,1)",
            data: countData,
        }],
    },
    options: {
        scales: {
            xAxes: [{
                time: {
                    unit: 'month'
                },
                gridLines: {
                    display: false
                },
                ticks: {
                    maxTicksLimit: 6
                }
            }],
            yAxes: [{
                ticks: {
                    min: 0,
                    max: 100,
                    maxTicksLimit: 5
                },
                gridLines: {
                    display: true
                }
            }],
        },
        legend: {
            display: false
        }
    }
});
