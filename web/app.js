const APIURL = "http://localhost:9090/elevators";
createElevators();


async function createElevators() {
    let html = document.getElementById("board");

    for (let i = 1; i < 17; i++) {
        html.innerHTML += `<div class="elevator-${i} column"></div>`
        let column = document.querySelector('.elevator-' + i);
        for (let j = 0; j < 10; j++) {
            column.innerHTML += `<div class="floor-${j} place"></div>`
        }
    }

    await getElevators()
}

// ASYNC METHODS

async function getElevators() {
    const resp = await fetch(APIURL + '/status');
    const respData = await resp.json();

    fillDivs(respData)
}

async function update(elevatorId, floor) {
    clearDivs()
    await fetch(APIURL + '/update/' + elevatorId + '?floor=' + floor, {method: 'put'});

    await getElevators()
}

async function pickup(sourceFloor, destinationFloor) {
    await fetch(APIURL + '/pickup?sourceFloor=' + sourceFloor + '&destinationFloor=' + destinationFloor, {method: 'put'});
}

async function step() {
    clearDivs()

    await fetch(APIURL + '/step', {method: 'put'});
    await getElevators()
}

async function reset() {
    clearDivs()

    await fetch(APIURL + '/reset', {method: 'put'});
    await getElevators()
}

// HELP METHODS

function fillDivs(response) {
    for (let i = 0; i < 16; i++) {
        let firstClass = '.elevator-' + response[i].id
        let secondClass = '.floor-' + response[i].numberOfFloor
        const innerDiv = document
            .querySelector(firstClass)
            .querySelector(secondClass)
        innerDiv.classList.add("taken")
    }
}

function clearDivs() {
    for (let i = 1; i < 17; i++) {
        for (let j = 0; j < 9; j++) {
            let firstClass = '.elevator-' + i
            let secondClass = '.floor-' + j
            const innerDiv = document
                .querySelector(firstClass)
                .querySelector(secondClass)
            innerDiv.classList.remove("taken")
        }
    }
}

// BUTTONS METHODS

function getValuesForPickup() {
    let sourceFloor = document.getElementById('sourceFloor').value;
    let destinationFloor = document.getElementById('destinationFloor').value;

    pickup(sourceFloor, destinationFloor)
}

function getValuesForUpdate() {
    let elevatorId = document.getElementById('elevatorId').value;
    let floor = document.getElementById('floor').value;

    update(elevatorId, floor)
}

function makeStep() {
    step()
}

// BUTTON METHODS

function minusSource() {
    const input = document.getElementById("sourceFloor");
    if(input.value > 0){
        input.value--;
    }
}

function plusSource() {
    const input = document.getElementById("sourceFloor");
    if(input.value < 9){
        input.value++;
    }
}

function minusDestination() {
    const input = document.getElementById("destinationFloor");
    if(input.value > 0){
        input.value--;
    }
}

function plusDestination() {
    const input = document.getElementById("destinationFloor");
    if(input.value < 9){
        input.value++;
    }
}

function minusId() {
    const input = document.getElementById("elevatorId");
    if(input.value > 1){
        input.value--;
    }
}

function plusId() {
    const input = document.getElementById("elevatorId");
    if(input.value < 16){
        input.value++;
    }
}

function minusFloor() {
    const input = document.getElementById("floor");
    if(input.value > 0){
        input.value--;
    }
}

function plusFloor() {
    const input = document.getElementById("floor");
    if(input.value < 9){
        input.value++;
    }
}