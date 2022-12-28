const APIURL = "http://localhost:9090/elevators";
getElevetors();

// ASYNC METHODS

async function getElevetors() {
    const resp = await fetch(APIURL + '/status');
    const respData = await resp.json();

    fillDivs(respData)
}

async function sendData(sourceFloor, destinationFloor) {
    await fetch(APIURL + '/pickup?sourceFloor=' + sourceFloor + '&destinationFloor=' + destinationFloor, {method: 'put'});
}

async function step() {
    clearDivs()

    await fetch(APIURL + '/step', {method: 'put'});
    await getElevetors()
}

// HELP METHODS

function fillDivs(response){
    for(let i=0; i<16; i++){
        let firstClass = '.elevator-' + response[i].id
        let secondClass = '.floor-' + response[i].numberOfFloor
        const innerDiv = document
        .querySelector(firstClass)
        .querySelector(secondClass)
        innerDiv.classList.add("taken")
    }
}

function clearDivs(){
    for(let i=1; i<17; i++){
        for(let j=0; j<9; j++) {
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

function getValues() 
{
    sourceFloor = document.getElementById('sourceFloor').value;
    destinationFloor = document.getElementById('destinationFloor').value;

    sendData(sourceFloor, destinationFloor)
}

function makeStep() 
{
    step()
}
