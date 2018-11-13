// Angular
app = angular.module("addPatientVisit", []);

app.controller('addPatientVisit', function($scope, $http) {

	$scope.patientVisit = {};

	$scope.newExamination = {
		name : "",
		result : "",
		note : ""
	};

	$scope.resetNewExamination = angular.copy($scope.newExamination);

	$scope.newMedicine = {
		name : "",
		dose : "",
		schedule : ""
	};

	$scope.resetNewMedicine = angular.copy($scope.newMedicine);

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonPatientVisit=" + jsonPatientVisit);
		$scope.patientVisit = JSON.parse(jsonPatientVisit);

		// S-Examination AutoCompletion
		console.log("jsonExaminations=", jsonExaminations);
		var examinations = JSON.parse(jsonExaminations);
		$("#examination-name").autocomplete({
			source : examinations
		});
		// E-Examination AutoCompletion

	};

	// S-Examination

	$scope.addExamination = function() {
		console.log("addExamination->fired");

		$scope.patientVisit.examinations.push($scope.newExamination);
		$scope.newExamination = angular.copy($scope.resetNewExamination);
	}
	$scope.deleteExamination = function(index) {
		$scope.patientVisit.examinations.splice(index, 1);
	}

	// E-Examination

	// S-Medicine

	$scope.addMedicine= function() {
		console.log("addMedicine->fired");

		$scope.patientVisit.medicines.push($scope.newMedicine);
		$scope.newMedicine = angular.copy($scope.resetNewMedicine);
	}
	$scope.deleteMedicine = function(index) {
		$scope.patientVisit.medicines.splice(index, 1);
	}

	// E-Medicine

	$scope.save = function() {
		console.log("save->fired");

		console.log("$scope.patientVisit=", $scope.patientVisit);

		$http({
			method : 'POST',
			data : $scope.patientVisit,
			url : $$ContextURL + '/patientVisits/add'
		}).then(
				function(response) {
					console.log(response);
					if (response.data.status == 200) {
						$("#modal-body").html(response.data.message);
						$("#modal").modal("show");
						setTimeout(function() {
							window.location.href = $$ContextURL
									+ '/patientVisits/edit/'
									+ response.data.etc;
						}, 1000);

					}
				}, function(response) {
					$("#modal-body").html(response.data);
					$("#modal").modal("show");
				});

	}

});
