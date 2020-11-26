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


    console.log(data);
    //show(data);
    
    if (window.location.href.indexOf("index") > -1) {
        show(data);
    }else if(window.location.href.indexOf("detail") > -1){
        detail(data);
    }

}
//Use this to call the right site
if (window.location.href.indexOf("index") > -1) {
    getCars(GET_ALL_CARS);
}else if(window.location.href.indexOf("detail") > -1){
    //This is hard coded you will have to look at cookies or whatever kind of way you want to
    getCars(GET_CAR + "1");
}




function show(data){
const container = document.getElementById("carParent");
    //let data = getCars(GET_ALL_CARS);
    console.log(data);

for (let e of Object.keys(data)) {
    var value = data[e];
    //console.log(value);
    const card = document.createElement("div");
    //card.classList = "card col-md-6 col-lg-3 col-sm-6 mx-3";

    //console.log(value);
    //console.log(e, value);

    const content = `

            <div class="card col-md-6 col-lg-3 col-sm-6 mx-3">
                <h3 class="card-header">${value.success.car.brand} ${value.success.car.model}</h3>
                <div class="card-body">
                  <h5 class="card-title">${value.success.car.brand} ${value.success.car.model}, ${value.success.car.car_year}</h5>
                </div>
                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
                  <rect width="100%" height="100%" fill="#868e96"></rect>
                  <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
                </svg>
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">${value.success.car.brand} ${value.success.car.model}</li>
                  <li class="list-group-item">${value.success.car.car_year}</li>
                  <li class="list-group-item">${value.success.car.color}</li>
                </ul>
                <div class="card-body">
                  <a href="detail.html?id=${value.success.car.id}" class="card-link">Card link</a>
                </div>
                <div class="card-footer text-muted">
                  2 days ago
                </div>
            </div>

    
    `;


    container.innerHTML += content;
}
}

function detail(data){
    //fasdkfaldf
}