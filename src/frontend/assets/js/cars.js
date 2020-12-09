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
        var imgID = value.success.car.car_id;

        var filter = document.getElementById("searchTextField").value.toLowerCase();

        if (brand.toLowerCase().includes(filter) || model.toLowerCase().includes(filter) || car_year.includes(filter) || color.toLowerCase().includes(filter) || filter == "") {

            const content = `


            <div class="card col-md-6 col-lg-3 col-sm-6 mx-5 centerer">

                <a class="clickCard" href="detail.html?car_id=${value.success.car.car_id}"></a>

                <img class="carImage" src="images/index/${imgID}.jpg" alt="images/default-image.png">
                
                <div class="card-body">
                  <h5>${brand} ${model}, ${car_year}</h5>
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
    document.title = `${value.success.car.brand} ${value.success.car.model}`;
    var imgID = value.success.car.car_id;
    var img2 = imgID + "-1";
    var img3 = imgID + "-2";
    const content = `

                <div class="div-left" style="margin-bottom:0%!important;">
                    
                    <div id="carouselExampleIndicators" class="carousel slide" style="height: 500px;" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox" style="min-height: 500px; margin-bottom: 0%!important;">
                            <!-- Slide One - Set the background image for this slide in the line below -->
                            <div class="carousel-item active" style="background-image: url('images/index/${imgID}.jpg'); height: 500px;">
                            </div>
                            <!-- Slide Two - Set the background image for this slide in the line below -->
                            <div class="carousel-item" style="background-image: url('images/detail/${img2}.jpg'); height: 500px;">
                            </div>
                            <!-- Slide Three - Set the background image for this slide in the line below -->
                            <div class="carousel-item" style="background-image: url('images/detail/${img3}.jpg'); height: 500px;">
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="div-right"  style="font-family:'Montserrat', sans-serif">
                    <h2> ${value.success.car.brand} ${value.success.car.model} </h2>

                    <ul class="list-group list-group-flush paddington text-dark">
                        <li class="list-group-item paddington"><h6 class="mb-1">Model: </h6> ${value.success.car.brand} ${value.success.car.model}</li>
                        <li class="list-group-item paddington"><h6 class="mb-1">Generation: </h6> ${value.success.car.car_year}</li>
                        <li class="list-group-item paddington"><h6 class="mb-1">Color: </h6> ${value.success.car.color}</li>
                        <li class="list-group-item paddington mb-0"><h6 class="mb-1">Additional: </h6> ${value.success.car.additional}</li>
                    </ul>
                </div>

                <button
                    id="${imgID}"
                    type="button"
                    class="btn btn-light float-right buyButton"
                    style="margin-right: 5%; border-radius: 0 !important;"
                    onclick="ask()"
                    >
                    <span>
                        <b>BUY NOW</b>
                    </span>
                </button>
                     
        `;

    container.innerHTML += content;
}



function jozo() {

    document.getElementById("searchTextField")
        .addEventListener("keydown", function (event) {
            
            if (event.keyCode === 13) {
                event.preventDefault();
                document.getElementById("searchButton").click();
            }
        });


    document.getElementById("searchButton").onclick = function search() {

        var field = document.getElementById("searchTextField");
        var filter = document.getElementById("searchTextField").value.toLowerCase();
        if (filter == "") {
            field.setAttribute("style", "display:inline !important; opacity: 1!important;");
            return;
        }

        document.getElementById("carParent").innerHTML = "";

        getCars(GET_ALL_CARS);

        // MAKE THE CAROUSEL DISSAPEAR UPON SEARCH
        var carousel = document.getElementById("carousel");
        carousel.setAttribute("style", "display:none !important;");

        // MAKE THE CARDS HAVE BETTER MARGINS
        var cardParent = document.getElementById("carParent");
        cardParent.setAttribute("style", "margin: 10% 10% 0 10% !important");


    };



}

// THIS FUNCTION MAKES THE TEXT FIELD DISSAPEAR
function dissapear(){
    var field = document.getElementById("searchTextField");
    field.setAttribute("style", "display:none !important; opacity:0 !important;");
}

//window.jsPDF = require('jspdf');

const pdf = new jsPDF();

function ask() {


    question = confirm("Are you sure?");

    if (question){

        alert("I am here");

        pdf.text(100,15,"Receipt");
        pdf.text(10,50,"Name:");
        pdf.text(50,50,sessionStorage.getItem("fullname"));
        pdf.text(10,70,"Object: ");
        pdf.text(50,70,"Car");
        pdf.text(10,80,"Brand:");
        pdf.text(50,80,document.getElementById("brand").innerText);
        pdf.text(10,90,"Color:");
        pdf.text(50,90,document.getElementById("color").innerText);
        pdf.text(10,100,"Year:");
        pdf.text(50,100,document.getElementById("year").innerText);
        pdf.text(10,110,"Quantity:");
        pdf.text(50,110,"1");

        pdf.save();
    }
}
