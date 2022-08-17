<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
</head>
<body>
<div class="chart-div">
  <canvas id="pieChartCanvas" width="300px" height="300px"></canvas>
</div><c:forEach var="i" begin="0" end="${fn:length(checkList)}">
<label for="">${checkStrList[i]}</label><br>
${checkList[i]}
</c:forEach>
            <label for="">IMG</label>
            <label for="">VIDEO</label>
            <label for="">DOCUMENT</label>
            <label for="">TEXT</label>
            <label for="">ETC</label>
            
            <script>
            window.onload = function () {
              pieChartDraw();
              document.getElementById('legend-div').innerHTML = window.pieChart.generateLegend();
				 setLegendOnClick();
				}
            let pieChartData = {
                labels: ['IMG', 'VIDEO', 'DOCUMENT', 'ETC', 'TEXT'],
                datasets: [{
                    data: [${checkList[0]}, ${checkList[1]}, ${checkList[2]}, ${checkList[3]}, ${checkList[4]}],
                    backgroundColor: ['rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)']
                }]
            };

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
                    ul.innerHTML += `<li data-index="${index}"><span style="background-color: ${color[index]}"></span>${label}</li>`;
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
            </script>
</body>
</html>