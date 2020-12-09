
//doesnt need anything
const GET_USERS = "http://localhost:8080/api/users";

var specChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

async function register(){

    console.log("hello");

    let userInput = document.getElementById("userInput").value;
    let passInput = document.getElementById("passInput").value;
    let fullNameInput = document.getElementById("fullNameInput").value;
    let emailInput = document.getElementById("emailInput").value;
    let numberInput = document.getElementById("numberInput").value;

    if(userInput == ""){

        alert("Username must be filled out!");
        return false;
    
    }
    else if(passInput == ""){

        alert("Password must be filled out!");
        return false;
    
    }
    else if(fullNameInput == ""){

        alert("Full Name must be filled out!");
        return false;
    
    }
    else if(hasNumber(fullNameInput)){

        alert("Full Name cannot contain numbers!");
        return false;

    }
    else if(specChars.test(fullNameInput)){

        alert("Full Name cannot contain special characters!");
        return false;

    }
    else if(emailInput == ""){

        alert("Email must be filled out!");
        return false;
    
    }
    else if(!(validateEmail(emailInput))){

        alert("Invalid email!");
        return false;
        
    }
    else if(numberInput == ""){

        alert("Phone number must be filled out!");
        return false;
    
    }
    else if(!(/^\d+$/.test(numberInput))){

        alert("Phone number must only contain numbers!");
        return false;
        
    }

    const INSERT_USERS = "http://localhost:8080/api/insertUser/" + userInput + "/" + passInput + "/"  + fullNameInput + "/" + emailInput + "/" + numberInput + "/2";

    let response;
    try {

        // Storing response 
        response = await fetch(INSERT_USERS, {

            method: "POST",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Credentials": true,
                "Accept": "application/json"
            }

        });

        var loginID = userInput;
        var loginType = 2;

        
        sessionStorage.setItem('user',loginID);
        sessionStorage.setItem('fullname',fullNameInput);
        sessionStorage.setItem('email',emailInput);
        sessionStorage.setItem('phonenumber',numberInput);
        sessionStorage.setItem('type',loginType);
    
        window.location.replace("index.html");

    } catch (error) {
        console.log(error);
    }
    
}

function hasNumber(myString) {
    return /\d/.test(myString);
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

