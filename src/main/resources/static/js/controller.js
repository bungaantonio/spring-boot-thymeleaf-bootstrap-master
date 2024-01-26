app.controller(
  "registerUtenteController",
  function ($scope, $http, $location, $route) {
    $scope.submitUtenteForm = function () {
      $http({
        method: "POST",
        url: "http://localhost:8081/api/utente/",
        data: $scope.utente,
      }).then(
        function (response) {
          $location.path("/list-all-utente");
          $route.reload();
        },
        function (errResponse) {
          $scope.errorMessage = errResponse.data.errorMessage;
        }
      );
    };
    $scope.resetForm = function () {
      $scope.utente = null;
    };
  }
);

app.controller(
  "listUtenteController",
  function ($scope, $http, $location, $route) {
    $http({
      method: "GET",
      url: "http://locahost:8081/api/utente/",
    }).then(function (response) {
      $scope.utente = response.data;
    });

    $scope.editUtente = function (utenteId) {
      $location.path("/update-utente" + utenteId);
    };
    $scope.deleteUtente = function (utenteId) {
      $http({
        method: "DELETE",
        url: "http://locahost:8081/api/utente" + utenteId,
      }).then(function (response) {
        $location.path("/list-all-utente");
        $route.reload();
      });
    };
  }
);

app.controller(
  "utenteDetailsController",
  function ($scope, $http, $location, $routeParams, $route) {
    $scope.utenteId = $routeParams.id;

    $http({
      method: "GET",
      url: "http://locahost:8081/api/utente/" + $scope.utenteId,
    }).then(function (response) {
      $scope.utente = response.data;
    });

    $scope.submitUtenteForm = function () {
      $http({
        method: "POST",
        url: "http://localhost:8081/api/utente/",
        data: $scope.utente,
      }).then(
        function (response) {
          $location.path("/list-all-utente");
          $route.reload();
        },
        function (errResponse) {
          $scope.errorMessage =
            "Error ao editar utente - Messagem de erro: " +
            errResponse.data.errorMessage;
        }
      );
    };
  }
);
