function addStep() {
    var stepsTable = document.getElementById('stepsTable');
    var newRow = stepsTable.insertRow(-1);
    var cell = newRow.insertCell(0);

    var newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('name', 'recipeSteps');
    newInput.setAttribute('size', '77')
    newInput.setAttribute('placeholder', 'Write a new recipe step here...')
    cell.appendChild(newInput);

    var removeButton = document.createElement('button');
    removeButton.setAttribute('type', 'button');
    removeButton.setAttribute('onclick', 'removeStep(this)');
    removeButton.setAttribute('class', 'removeButton btn btn-outline-danger');
    cell.appendChild(removeButton);
}

window.onload = function () {
    addStep()
};

function removeStep(button) {
    var row = button.closest('tr');

    row.remove();
}