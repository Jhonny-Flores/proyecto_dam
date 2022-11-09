/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var chart1;
var chart2;
var chart3;
var chart4;
var chart5;

async function sumAllDays() {
    feather.replace({'aria-hidden': 'true'});
    await $.ajax({
        url: 'DetalleRegistroController',
        type: 'POST',
        data: {method: "sumAllDays"}
    }).done((resp) => {
        const ctx1 = document.getElementById('sumAllDaysGraphs');
        var fechaCreacion = resp.map(function (e) {
            return e.fechaCreacion;
        });
        var data = resp.map(function (e) {
            return e.totalVisitantes;
        });
        var config1 = {
            type: 'line',
            data: {
                labels: fechaCreacion,
                datasets: [{
                        label: 'Total de Visitantes',
                        data: data,
                        fill: {
                            target: 'origin',
                            above: 'rgb(255, 0, 0)', // Area will be red above the origin
                            below: 'rgb(0, 0, 255)'    // And blue below the origin
                        },
                        borderColor: '#007bff',
                        borderWidth: 4,
                        pointBackgroundColor: '#2596be'
                    }]
            }
        };
        if (chart1) {
            chart1.destroy();
        }
        chart1 = new Chart(ctx1, config1);
    }).fail((error) => {
        console.log(error.statusText);
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: `El servidor dice: ${error.statusText}`
        });
    });
}

async function graficoBarras() {
    feather.replace({'aria-hidden': 'true'});
    await $.ajax({
        url: 'DetalleRegistroController',
        type: 'POST',
        data: {method: "sumByPark"}
    }).done((resp) => {
        console.log(resp);
        const ctx2 = document.getElementById('allVisitorsSumPerPark');
        var nombreParque = resp.map(function (e) {
            return e.nombreCategoria;
        });
        var data = resp.map(function (e) {
            return e.totalVisitantes;
        });
        var config2 = {
            type: 'horizontalBar',
            data: {
                labels: nombreParque,
                datasets: [{
                        axis: 'y',
                        label: 'Total de Visitantes',
                        data: data,
                        fill: false,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 205, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(201, 203, 207, 0.2)'
                        ],
                        borderColor: [
                            'rgb(255, 99, 132)',
                            'rgb(255, 159, 64)',
                            'rgb(255, 205, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)',
                            'rgb(201, 203, 207)'
                        ],
                        borderWidth: 1
                    }]
            }, options: {
                indexAxis: 'y',
                scales: {
                    xAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }],
                    yAxes: [{
                            stacked: true
                        }]
                }
            }
        };
        if (chart2) {
            chart2.destroy();
        }
        chart2 = new Chart(ctx2, config2);
    }).fail((error) => {
        console.log(error.statusText);
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: `El servidor dice: ${error.statusText}`
        });
    });
}

async function getVisitorsPerAgeAll() {
    feather.replace({'aria-hidden': 'true'});
    await $.ajax({
        url: 'DetalleRegistroController',
        type: 'POST',
        data: {method: "getVisitorsPerAgeAll"}
    }).done((resp) => {
        const ctx3 = document.getElementById('allParksPerAgeGraph');
        var nombres = resp.map(function (e) {
            return e.nombreCategoria;
        });
        var data = resp.map(function (e) {
            return e.totalVisitantes;
        });
        var config3 = {
            type: 'doughnut',
            data: {
                labels: nombres,
                datasets: [{
                        label: 'Total de Visitantes',
                        data: data,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            'rgba(255, 205, 86, 0.5)',
                            'rgba(75, 192, 192, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                        ],
                        borderColor: [
                            'rgb(255, 99, 132)',
                            'rgb(255, 159, 64)',
                            'rgb(255, 205, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)',
                            'rgb(201, 203, 207)'
                        ],
                        borderWidth: 1,
                        hoverOffset: 4
                    }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
            }
        };
        if (chart3) {
            chart3.destroy();
        }
        chart3 = new Chart(ctx3, config3);
    }).fail((error) => {
        console.log(error.statusText);
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: `El servidor dice: ${error.statusText}`
        });
    });
}

async function getVisitorsSum(parqueA, fecha1A, fecha2A) {
    feather.replace({'aria-hidden': 'true'});
    const ctx4 = document.getElementById('visitorsSumGraph');
    await $.ajax({
        url: 'DetalleRegistroController',
        type: 'POST',
        data: {method: "getVisitorsSum", idParque: parseInt(parqueA), fecha1: fecha1A, fecha2: fecha2A}
    }).done((resp) => {
        var fechaCreacion = resp.map(function (e) {
            return e.fechaCreacion;
        });
        var data = resp.map(function (e) {
            return e.totalVisitantes;
        });
        var config4 = {
            type: 'line',
            data: {
                labels: fechaCreacion,
                datasets: [{
                        label: 'Total de Visitantes',
                        data: data,
                        fill: true,
                        lineTension: 0,
                        borderColor: '#007bff',
                        borderWidth: 4,
                        pointBackgroundColor: '#2596be'
                    }]
            }, options: {
                scales: {
                    xAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                }
            }
        };
        if (chart4) {
            chart4.destroy();
        }
        chart4 = new Chart(ctx4, config4);
    }).fail((error) => {
        console.log(error.statusText);
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: `${error.statusText}`
        });
    });
}

async function getVisitorsPerDay(parqueA, fecha1A) {
    feather.replace({'aria-hidden': 'true'});
    await $.ajax({
        url: 'DetalleRegistroController',
        type: 'POST',
        data: {method: "getVisitorsPerDay", idParqueCase2: parseInt(parqueA), fecha1Case2: fecha1A}
    }).done((resp) => {
        const ctx5 = document.getElementById('otro');
        var nombres = resp.map(function (e) {
            return e.nombreCategoria;
        });
        var data = resp.map(function (e) {
            return e.totalVisitantes;
        });
        var config5 = {
            type: 'bar',
            data: {
                labels: nombres,
                datasets: [{
                        label: 'Visitantes',
                        data: data,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            'rgba(255, 205, 86, 0.5)',
                            'rgba(75, 192, 192, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                        ],
                        borderColor: [
                            'rgb(255, 99, 132)',
                            'rgb(255, 159, 64)',
                            'rgb(255, 205, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)',
                            'rgb(201, 203, 207)'
                        ],
                        borderWidth: 1
                    }]
            }, options: {
                scales: {
                    xAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                }
            }
        };
        if (chart5) {
            chart5.destroy();
        }
        chart5 = new Chart(ctx5, config5);
    }).fail((error) => {
        console.log(error.statusText);
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: `${error.statusText}`
        });
    });
}

const llenarSelect = () => {
    $.ajax({
        url: 'ParqueController',
        type: 'POST',
        data: {method: "getParques"}
    }).done((resp) => {
        resp.forEach(parque => {
            const opt = document.createElement("option");
            opt.value = parque.idParque;
            opt.text = parque.nombre;
            $('.selectParques').append(opt);
        });
    }).fail((error) => {
        console.log(error.statusText);
    });
};
let today = new Date();
const offset = today.getTimezoneOffset();
today = new Date(today.getTime() - (offset * 60 * 1000));
let fechaFormatoFinal = today.toISOString().split('T')[0];
let semanaAnterior = 1000 * 60 * 60 * 24 * 7;
let resta = today.getTime() - semanaAnterior;
resta = new Date(resta);
let fechaFormatoInicial = resta.toISOString().split('T')[0];
$(document).ready(function () {
    sumAllDays();
    graficoBarras();
    llenarSelect();
    getVisitorsSum(1, fechaFormatoInicial, fechaFormatoFinal);
    getVisitorsPerAgeAll();
    $('#visitorsSum').submit(e => {
        e.preventDefault();
        const parque = $('#selectParque1').val();
        const fecha1 = $('#fecha1').val();
        const fecha2 = $('#fecha2').val();
        if (Date.parse(fecha1) > Date.parse(fecha2)) {
            Swal.fire({
                icon: 'warning',
                title: 'Oops...',
                text: `La fecha 1 no puede ser mayor a la fecha 2`
            });
            return;
        } else {
            getVisitorsSum(parque, fecha1, fecha2);
        }
    });
    $('#visitorsPerAge').submit(e => {
        e.preventDefault();
        const parque = $('#selectParque2').val();
        const fecha1 = $('#fechaVisitantes').val();
        getVisitorsPerDay(parque, fecha1);
    });
});