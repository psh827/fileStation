
let pieChartDraw = function () {
    let ctx = document.getElementById('pieChartCanvas').getContext('2d');

    window.pieChart = new Chart(ctx, {
        type: 'pie',
        data: pieChartData,
        options: {
            responsive: false,
            legend: {
                display: false
            },
            legendCallback: customLegend
        }
    });
};

let customLegend = function (chart) {
    let ul = document.createElement('ul');
    let color = chart.data.datasets[0].backgroundColor;

    chart.data.labels.forEach(function (label, index) {
        ul.innerHTML += `<li data-index="${index}"><span style="background-color:pink"></span>${label}</li>`;
    });

    return ul.outerHTML;
};

let setLegendOnClick = function () {
    let liList = document.querySelectorAll('#legend-div ul li');

    for (let element of liList) {
        element.onclick = function () {
            updateChart(event, this.dataset.index, "pieChart");

            if (this.style.textDecoration.indexOf("line-through") < 0) {
                this.style.textDecoration = "line-through";
            } else {
                this.style.textDecoration = "";
            }
        }
    }
};

let updateChart = function (e, datasetIndex, chartId) {
  let index = datasetIndex;
  let chart = e.view[chartId];
  let i, ilen, meta;

  for (i = 0, ilen = (chart.data.datasets || []).length; i < ilen; ++i) {
      meta = chart.getDatasetMeta(i);

      if (meta.data[index]) {
          meta.data[index].hidden = !meta.data[index].hidden;
      }
  }

  chart.update();
};