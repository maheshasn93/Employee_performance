document.addEventListener("DOMContentLoaded", function () {
    fetchSuggestedChanges();
});

async function fetchSuggestedChanges() {
    try {
        let response = await fetch("http://localhost:8080/api/performance/suggested-changes");
        let data = await response.json();
        populateSuggestionTable(data);
    } catch (error) {
        console.error("Error fetching suggested changes:", error);
    }
}

function populateSuggestionTable(data) {
    let tableBody = document.querySelector("#suggestionTable tbody");
    tableBody.innerHTML = ""; // Clear previous entries

    Object.keys(data).forEach(employee => {
        let row = document.createElement("tr");

        let empCell = document.createElement("td");
        empCell.textContent = employee;
        
        let currentRatingCell = document.createElement("td");
        currentRatingCell.textContent = employee.split("Rating: ")[1][0];

        let suggestedRatingCell = document.createElement("td");
        suggestedRatingCell.textContent = data[employee].split(": ")[1];

        row.appendChild(empCell);
        row.appendChild(currentRatingCell);
        row.appendChild(suggestedRatingCell);
        tableBody.appendChild(row);
    });
}
