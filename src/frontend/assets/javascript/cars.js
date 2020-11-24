//////////////////////////////////////////////
//
//  Ovi const su ti tu jer ces s njima upisivat url da ne
//  trebas manually svaki put taj dio upisat.
//  ofc ne upisujes za sve samo const. Npr za GET_ALL_CARS
//  jest da samo to upises ali za ostali trebas upisat tocno
//  stvari koje traze. Znaci za GET_CAR trebas ID, za UPDATE_CAR.......
//  you get the point. I'll have the things they need commented next to them so you can easily know what you need
//  Make sure you right now get GET_ALL_CARS and GET_CAR working.
//  Other 3 you can have just the idea of it going. You don't have to have them done by tomorrow.
//
//////////////////////////////////////////////

//  Does not need anything. Just write it with this
const GET_ALL_CARS = "http://localhost:8080/api/cars";

//  Needs an id so write down GET_CAR+"1" if you want the first id car
const GET_CAR = "http://localhost:8080/api/car/";

//  Needs {car_id}/{brand}/{model}/{car_year}/{color}/{additional}/{sold} added
const UPDATE_CAR = "http://localhost:8080/api/updateCar/";

//  Needs {brand}/{model}/{car_year}/{color}/{additional} added
const INSERT_CAR = "http://localhost:8080/api/insertCar/";

//  Needs {id} added
const DELETE_CAR = "http://localhost:8080/api/deleteCar/";



//////////////////////////////////////////////
//
//  Ovdje ces napravit sve funkcije koje ce prikazat auto
//  na html-u. Pogledaj kako je na templateu napravljeno
//  i s time pokusaj da se dinamicki prikazu svi auti na nacin kako je u templatu
//  funkciju koju ces koristiti iz aipiReader.js-a je getCars()
//  To je obicni getter koji ti geta i stavi sve sto je dobio u arraylistu
//
//////////////////////////////////////////////

//////////////////////////////////////////////
//
//  Mozes isto napravit da je div clickable i da te vodi u detail.html
//  gdje ces prikazat sve o auto detaljno. Ne trebas sada nista fancy
//  da bude treba glavno da ti valja i da je dinamicno
//  funkciju koju ces koristiti iz aipiReader.js-a je getCars()
//  To je obicni getter koji ti geta i stavi sve sto je dobio u arraylistu
//
//////////////////////////////////////////////
