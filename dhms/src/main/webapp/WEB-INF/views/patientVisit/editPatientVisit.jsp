<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonExaminations = '<spring:escapeBody  javaScriptEscape="true">${jsonExaminations}</spring:escapeBody>';
</script>

<div id="add-patient-visit-contaner" ng-app="addPatientVisit"
	ng-controller="addPatientVisit" ng-init="init()">
	<h3 class="text-warning">Edit Patient Visit</h3>
	<table>
		<tr>
			<td>Patient</td>
			<td>{{patientVisit.patient.fullName}} <a
				href="<c:url value="/patients/edit/"/>{{patientVisit.patient.id}} "
				class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
			</a>
			</td>
		</tr>
	</table>

	<table id="add-patient-visit-table" class="table table-bordered"
		ng-form name="form">
		<tr>
			<td>Visit Case</td>
			<td><input ng-model="patientVisit.visitCase"
				class="form-control form-control-sm"></td>
		</tr>
		<tr>
			<td>Result</td>
			<td><input class="form-control form-control-sm"
				ng-model="patientVisit.result"></td>
		</tr>

		<tr>
			<td>Note</td>
			<td><input class="form-control form-control-sm"
				ng-model="patientVisit.note"></td>
		</tr>


	</table>


	<div>
		<h5 class="text-info">Examination</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Result</th>
					<th>Note</th>
					<th>F</th>
				</tr>
				<tr ng-form name="examinationForm">
					<th><input id="examination-name"
						ng-model="newExamination.name" required name="name"
						class="form-control form-control-sm"></th>
					<th><input ng-model="newExamination.result" required
						name="result" class="form-control form-control-sm"></th>

					<th><input ng-model="newExamination.note"
						class="form-control form-control-sm"></th>
					<th>
						<button ng-disabled="examinationForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addExamination()">
							<i class="fa fa-plus"></i>
						</button>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.examinations">
					<td>{{item.name}}</td>
					<td>{{item.result}}</td>
					<td class="cus-note-td" title="{{item.note}}">{{item.note}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteExamination(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<div>
		<h5 class="text-info">Medicines</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Dose</th>
					<th>Schedule</th>
					<th>F</th>
				</tr>
				<tr ng-form name="medicineForm">
					<th><input id="medicine-name" ng-model="newMedicine.name"
						required name="name" class="form-control form-control-sm"></th>
					<th><input ng-model="newMedicine.dose" required name="result"
						class="form-control form-control-sm"></th>

					<th><input ng-model="newMedicine.schedule" required
						class="form-control form-control-sm"></th>
					<th>
						<button ng-disabled="medicineForm.$invalid"
							class="btn btn-sm btn-success rounded-circle"
							ng-click="addMedicine()">
							<i class="fa fa-plus"></i>
						</button>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr ng-repeat="item in patientVisit.medicines">
					<td>{{item.name}}</td>
					<td>{{item.dose}}</td>
					<td>{{item.schedule}}</td>
					<td>
						<button class="btn btn-sm btn-danger rounded-circle"
							ng-click="deleteMedicine(item.id)">
							<i class="fa fa-times"></i>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

	<button class="btn btn-warning" ng-click="save()"
		ng-disabled="form.$invalid">
		<i class="fa fa-save"></i>
	</button>

</div>
