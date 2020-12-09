
//doesnt need anything
const GET_USERS = "http://localhost:8080/api/users";

function login(){

    console.log("hello");

    let userInput = document.getElementById("userInput").value;
    let passInput = document.getElementById("passInput").value;

    let data = getUsers(GET_USERS);

    for (let e of Object.keys(data)) {

        var value = data[e];
        //mozda  ce trebat  zamije it user sa users
        var username = value.success.user.username;
        var password = value.success.user.password;
        var type = value.success.user.type;

        if(userInput == ""){

            alert("Username must be filled out!");
            return false;
    
        }
        else if(userInput == username){

            if(passInput == ""){
    
                alert("Password must be filled out!");
                return false;
    
            }
            else if(passInput == password){
    
                var loginID = username;
                var loginType = type;

                sessionStorage.setItem('user',loginID);
                sessionStorage.setItem('type',loginType);
    
                window.location.replace("index.html");
    
            }
    
        }

    }

    
}

async function getUsers(url) {

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
            var user = data[r];

            array.push(user);
        }
    }

}

// async function login(url){

//     let response;
//     let data;
//     try{

//         // Storing response 
//         response = await fetch(url, {

//             method: "GET",
//             headers: {
//                 "Access-Control-Allow-Origin": "*",
//                 "Access-Control-Allow-Credentials": true,
//                 "Accept": "boolean"
//             }

//         });

//         data = await response.json();;

//     }catch (error) {
//         console.log(error);
//     }

//     if (response == true) {

        

//     }


//     console.log(data);

// }

