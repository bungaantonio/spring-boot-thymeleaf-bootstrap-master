var app = angular.module("utenteregistrationsystem", ["ngRoute", "ngResource"]);

app.config(function ($routeProvider) {
  $routeProvider
    .when("/list-all-utente", {
      templateUrl: "listutente.html",
      controller: "listUtenteController", 
    })
    .when("/register-new-utente", {
      templateUrl: "utenteregistration.html",
      controller: "registrationUtenteController",
    })
    .when("/update-utente/:id", {
      templateUrl: "utenteupdation.html",
      controller: "utenteDetailsController",
    })
    .otherwise({
      redirectTo: "/home",
      templateUrl: "home.html",
    });
});
