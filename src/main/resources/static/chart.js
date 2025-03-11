document.addEventListener("DOMContentLoaded", function () {
    fetchPerformanceData();
	
});

async function fetchPerformanceData() {
    try {
        let response = await fetch("http://localhost:8080/api/performance/actual-percentage");
        let percentageData = await response.json();

        let totalResponse = await fetch("http://localhost:8080/api/performance/total-employees");
        let totalData = await totalResponse.json();
        let totalEmployees = totalData.totalEmployees;

        let countData = {
            A: Math.round((percentageData.A / 100) * totalEmployees),
            B: Math.round((percentageData.B / 100) * totalEmployees),
            C: Math.round((percentageData.C / 100) * totalEmployees),
            D: Math.round((percentageData.D / 100) * totalEmployees),
            E: Math.round((percentageData.E / 100) * totalEmployees)
        };

        renderBellCurveChart(percentageData, countData, totalEmployees);
    } catch (error) {
        console.error("Error fetching performance data:", error);
    }
}

function renderBellCurveChart(percentageData, countData, totalEmployees) {
    let ctx = document.getElementById("performanceChart").getContext("2d");

    let categories = ["E (Low Performers)", "D (Needs Improvement)", "C (Good)", "B (Very Good)", "A (Outstanding)"];
    let percentages = [
	    parseFloat(percentageData.E.toFixed(2)), 
	    parseFloat(percentageData.D.toFixed(2)), 
	    parseFloat(percentageData.C.toFixed(2)), 
	    parseFloat(percentageData.B.toFixed(2)), 
	    parseFloat(percentageData.A.toFixed(2))
	];
    let counts = [countData.E, countData.D, countData.C, countData.B, countData.A];

    let chartData = percentages.map((value, index) => ({
        x: index + 1,
        y: value,
        count: counts[index]
    }));

    new Chart(ctx, {
        type: "line",
        data: {
            datasets: [{
                label: "Performance Distribution (%)",
                data: chartData,
                borderColor: "#000",
                backgroundColor: createGradient(ctx),
                tension: 0.4,
                pointRadius: 5,
                pointBackgroundColor: "#000",
                fill: true
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    type: "linear",
                    position: "bottom",
                    ticks: {
                        stepSize: 1,
                        callback: (value) => categories[value - 1]
                    }
                },
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 10 },
                    title: { display: true, text: "Performance Percentage (%)" }
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: (tooltipItem) => {
                            let index = tooltipItem.dataIndex;
                            return `Count: ${chartData[index].count} | ${chartData[index].y}%`;
                        }
                    }
                }
            }
        }
    });
}

function createGradient(ctx) {
    let gradient = ctx.createLinearGradient(0, 0, 0, 400);
    gradient.addColorStop(0, "rgba(33, 150, 243, 0.3)");
    gradient.addColorStop(0.5, "rgba(0, 150, 0, 0.5)");
    gradient.addColorStop(1, "rgba(255, 0, 0, 0.3)");
    return gradient;
}
