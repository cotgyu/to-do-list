// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var ctx = document.getElementById("monthlyUserRegisterChart");

const monthlyData = [];

var chartMain = {
  init: function () {

    $.ajax({
      type: 'GET',
      url: '/api/admin/monthlyUserStats/'+ 2021,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8'

    }).done(function (data) {

      for(var i=1; i<=12; i++){
        monthlyData[i-1] = data.result[i];
      }

    }).fail(function (error){
      alert(JSON.stringify(error));
    });

  }
}

chartMain.init();

var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
    datasets: [{
      label: "Revenue",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: monthlyData,
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
          maxTicksLimit: 12
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 100,
          maxTicksLimit: 10
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
