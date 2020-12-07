//  Does not need anything. Just write it with this
const GET_ALL_CARS = "http://localhost:8080/api/cars";

//  Needs {id} added
const GET_CAR = "http://localhost:8080/api/car/";

//  Needs {car_id}/{brand}/{model}/{car_year}/{color}/{additional}/{sold} added
const UPDATE_CAR = "http://localhost:8080/api/updateCar/";

//  Needs {brand}/{model}/{car_year}/{color}/{additional} added
const INSERT_CAR = "http://localhost:8080/api/insertCar/";

//  Needs {id} added
const DELETE_CAR = "http://localhost:8080/api/deleteCar/";

// FOR SOME REASON THE CARS METHOD DOESN'T WORK UNLESS THE URL CONTAINS INDEX.HTML
if (window.location.href == "http://localhost/frontend/") {
    window.location.replace("http://localhost/frontend/index.html");
}

// IF THE USER TRIES TO ACCESS THE DETAIL PAGE WITHOUT THE SPECIFIED CAR ID, THEY WILL BE REDIRECTED BACK TO INDEX
if (window.location.href == "http://localhost/frontend/detail.html") {
    window.location.replace("http://localhost/frontend/index.html");
}



// THIS CALLS METHODS APPROPRIATE TO THE USERS LOCATION
if (window.location.href.indexOf("index") > -1) {
    getCars(GET_ALL_CARS);
} else if (window.location.href.indexOf("detail") > -1) {
    detail();
}


async function getCars(url) {

    let response;
    let data;
    try {

        // Storing response 
        response = await fetch(url, {

            method: "GET",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true,
                "Accept": "application/json"
            }

        });

        data = await response.json();

    } catch (error) {
        console.log(error);
    }

    let array = [];

    if (response) {
        for (let r of Object.keys(data)) {
            var capital = data[r];

            array.push(capital);
        }
    }

    if (window.location.href.indexOf("index") > -1) {
        showAll(data);
    } else if (window.location.href.indexOf("detail") > -1) {
        show(data);
    }

}


// DISPLAYS DATA OF A SINGLE CAR DEPENDING ON THE ID RECIEVED FROM THE URL
function detail() {

    const querty = window.location.search;
    const urlParam = new URLSearchParams(querty);
    const id = urlParam.get('car_id');
    const url = GET_CAR + id;

    getCars(url);

}


// DISPLAYS THE DATA FOR ALL CARS
function showAll(data) {
    const container = document.getElementById("carParent");

    for (let e of Object.keys(data)) {
        var value = data[e];

        var brand = value.success.car.brand;
        var model = value.success.car.model;
        var car_year = value.success.car.car_year + "";
        var color = value.success.car.color;

        var filter = document.getElementById("searchTextField").value.toLowerCase();

        if (brand.toLowerCase().includes(filter) || model.toLowerCase().includes(filter) || car_year.includes(filter) || color.toLowerCase().includes(filter) || filter == "") {

            const content = `


            <div class="card col-md-6 col-lg-3 col-sm-6 mx-5 centerer">

                <a class="clickCard" href="detail.html?car_id=${value.success.car.car_id}"></a>

                <img class="carImage" src="images/random_car.JPG" alt="Default Image">

                <h3 class="card-header ">${brand} ${model}</h3>
                
                <div class="card-body ">
                  <h5 class="card-title ">${brand} ${model}, ${car_year}</h5>
                  <hr class="smallhr">
                  <h5 class="card-title ">${car_year}</h5>
                  <hr class="smallhr">
                  <h5 class="card-text ">${color}</h5>
                </div>
            
                  
        
            </div>

    
    `;

            container.innerHTML += content;
        }

    }
}

// DISPLAYS THE DETAILS FOR A CAR
function show(data) {
    const container = document.getElementById("detailParentDiv");

    var value = data[0];

    const content = `

    
                <div class="div-left">
                <h2> ${value.success.car.brand} ${value.success.car.model} </h2>

                <ul class="list-group list-group-flush paddington">
                <li class="list-group-item paddington">${value.success.car.brand} ${value.success.car.model}</li>
                <li class="list-group-item paddington">${value.success.car.car_year}</li>
                <li class="list-group-item paddington">${value.success.car.color}</li>
                </ul>
                </div>

                <div class="div-right">
                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" height="auto" width="100%" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
                      <rect width="100%" height="100%" fill="#868e96"></rect>
                      <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
                </svg>
                </div>
                     

        
        `;

    container.innerHTML += content;
}

function jozo() {

    document.getElementById("searchButton").onclick = function search() {

        document.getElementById("carParent").innerHTML = "";
        /*var div = document.getElementById('carParent');
        while (div.firstChild) {
            div.removeChild(div.firstChild);
        }*/

        getCars(GET_ALL_CARS);

    };

}
