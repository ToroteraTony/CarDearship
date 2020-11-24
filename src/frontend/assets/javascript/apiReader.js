//Testing getallcars
const api_url = "http://localhost:8080/api/cars";

// Defining async function 
async function getapi() { 
    
    // Storing response 
    const response = await fetch(api_url,{
        
        method: "GET",
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials" : true,
            "Accept": "application/json"
        }
        
    }); 

    console.log(response);

    let data = await response.json();

    if (response) { 
        hideloader(); 
    } 

    console.log(data);

    show(data); 
} 
// Calling that async function 
getapi(api_url); 
  
// Function to hide the loader 
function hideloader() { 
    document.getElementById('loading').style.display = 'none'; 
} 
// Function to define innerHTML for HTML table 
function show(data) { 
    let tab =  
        `<tr> 
          <th>ID</th>
          <th>Brand</th> 
          <th>Model</th> 
          <th>Car Year</th> 
          <th>color</th> 
          <th>additional</th>
          <th>sold</th>
         </tr>`; 

    // Loop to access all rows  
    for (let r of Object.keys(data)) {
        
        var capital = data[r];
        console.log(r, capital);
        

        tab += `<tr>  
            <td>${capital.success.car.car_id}</td>
            <td>${capital.success.car.brand} </td> 
            <td>${capital.success.car.model}</td> 
            <td>${capital.success.car.car_year}</td>
            <td>${capital.success.car.color}</td>
            <td>${capital.success.car.additional}</td>  
            <td>${capital.success.car.sold}</td>           
        </tr>`; 
    } 
    // Setting innerHTML as tab variable 
    document.getElementById("employees").innerHTML = tab; 
} 