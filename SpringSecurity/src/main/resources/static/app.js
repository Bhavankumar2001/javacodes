var userCreationUrl = "/api/usercreation";
var roleCreationUrl = "/api/rolecreation";
var departmentCreationUrl = "/api/departmentCreation";
var approvalRoleCreationUrl = "/api/approvalrole";
var indentCreationUrl = "/api/indentCreation";
var draftCreationUrl = "/api/draftCreation";
var drafttoindentCreationUrl = "/api/draftindentCreation";
var templateCreationUrl = "/api/templateCreation";
var app =
{
};
app.data = {
	totalPlannedData: [],
	locationdata: [],
	locationwhs: [],
};

var Toast = Swal.mixin({
	toast: true,
	position: 'top-end',
	showConfirmButton: false,
	timer: 1000
});
app.model = {
	getData: function() {
		$.ajax({
			method: 'GET',
			url: "/autocomplete/data",
			success: function(data) {
				app.data.totalPlannedData = data;
				app.ui.updatePlanedData(app.data.totalPlannedData);
			}
		});
	},



	CreateUser: function() {
		var stage = 0;
		var userName = $("#user-name").val();
		var userEmail = $("#user-email").val();
		var userPsw = $("#user-psw").val();
		var empCode = $("#user-empcode").val();

		var empDep = $("#user-department option:selected").text();
		var empDepcode = $("#user-department option:selected").val();


		let reportTo = $("#user-reportTo option:selected").val();
		let secondApproval = $("#user-approval option:selected").val();


		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();


		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let userRole = $("#user-role option:selected").val();
		let userRolecode = $("#user-role option:selected").text();



		if (userName.trim().length == 0) {
			Swal.fire("Invalid User Name", "Please Enter User Name", "error");
			stage = 1;
			return;
		}

		if (userEmail.trim().length == 0) {
			Swal.fire("Invalid User Email", "Please Enter User Email", "error");
			stage = 1;
			return;
		}
		if (userPsw.trim().length == 0) {
			Swal.fire("Invalid User Password", "Please Enter User Password", "error");
			stage = 1;
			return;
		}
		if (userRole.trim().length == 0) {
			Swal.fire("Invalid User Role", "Please Select Role ", "error");
			stage = 1;
			return;
		}



		if (empDep.trim().length == 0) {
			Swal.fire("Invalid User Department", "Please Select User Department ", "error");
			stage = 1;
			return;
		}

		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid User Unit", "Please Select User Unit", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		var json = {
			"username": userName,
			"userMail": userEmail,
			"password": userPsw,
			"departmentCode": empDep,
			"department": empDepcode,
			"reportTo": reportTo,
			"secondApproval": secondApproval,
			"roleId": userRole,
			"roleCode": userRolecode,
			"code": empCode,
			"locationCode": location,
			"locationId": locationcode,
			"unit": unit,
			"unitCode": unitcode
		}

		console.log(json)

		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: userCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Created the User!", "", "success").then((data) => {
						window.location = "/employee"
					});
				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}




	},
	newCreateUser: function() {
		var stage = 0;
		var userName = $("#user-name").val();
		var userEmail = $("#user-email").val();
		var userPsw = $("#user-psw").val();
		var name = $("#name").val();

		var empCode = $("#user-empcode").val();

		var empDep = $("#user-department option:selected").text();
		var empDepcode = $("#user-department option:selected").val();


		let reportTo = $("#user-reportTo option:selected").val();
		let secondApproval = $("#user-approval option:selected").val();


		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();


		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let userRole = $("#user-role option:selected").val();
		let userRolecode = $("#user-role option:selected").text();

		var empclass = $("#user-class option:selected").text();
		var empclasscode = $("#user-class option:selected").val();

		var empbusines = $("#user-business option:selected").text();
		var empbusinesscode = $("#user-business option:selected").val();


		if (userName.trim().length == 0) {
			Swal.fire("Invalid User Name", "Please Enter User Name", "error");
			stage = 1;
			return;
		}

		if (userEmail.trim().length == 0) {
			Swal.fire("Invalid User Email", "Please Enter User Email", "error");
			stage = 1;
			return;
		}
		if (userPsw.trim().length == 0) {
			Swal.fire("Invalid User Password", "Please Enter User Password", "error");
			stage = 1;
			return;
		}
		if (userRole.trim().length == 0) {
			Swal.fire("Invalid User Role", "Please Select Role ", "error");
			stage = 1;
			return;
		}



		if (empDep.trim().length == 0) {
			Swal.fire("Invalid User Department", "Please Select User Department ", "error");
			stage = 1;
			return;
		}

		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid User Unit", "Please Select User Unit", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		var json = {
			"username": name,
			"userMail": userEmail,
			"password": userPsw,
			"departmentCode": empDep,
			"department": empDepcode,
			"reportTo": reportTo,
			"secondApproval": secondApproval,
			"roleId": userRole,
			"roleCode": userRolecode,
			"code": empCode,
			"locationCode": location,
			"locationId": locationcode,
			"business": empbusines,
			"businessId": empbusinesscode,
			"toclass": empclass,
			"toclassId": empclasscode,
			"unit": unit,
			"unitCode": unitcode
		}

		//console.log(json)

		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: userCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Created the User!", "", "success").then((data) => {
						window.location = "/employee"
					});
				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}
	},
	//InActive the User Status
	InActiveUser: function() {

		var empStatus = $('#EmpNewStatus').val();
		var empId = $('#EmpId').val();

		var json = {
			"empStatus": empStatus,
			"empId": empId,
		}
		$.ajax({
			method: 'POST',
			url: "/api/change-emp-status",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {
				Swal.fire("Updated the Status !!!", "", "success").then((data) => {
					window.location = "/employee"
				});
			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});

	},

	RoleChange: function() {

		let userRole = $("#edit-user-role option:selected").val();
		let userRolecode = $("#edit-user-role option:selected").text();
		let empId = $('#EmpId').val();

		var json = {
			"userId": empId,
			"userRole": userRole,
			"userRolecode": userRolecode,
		}

		console.log(json);
		$.ajax({
			method: 'POST',
			url: "/api/change-emp-role",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {


				if (data != null && data != "") {
					Swal.fire("Updated the Role !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist Role !!!", "", "error")
				}


			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});

	},

	departmentChange: function() {

		let userDepartmentCode = $("#edit-user-department option:selected").val();
		let userDepartment = $("#edit-user-department option:selected").text();
		let userId = $('#EmpId').val();

		var json = {
			"userDepartment": userDepartment,
			"userDepartmentCode": userDepartmentCode,
			"userId": userId,
		}

		console.log(json);
		$.ajax({
			method: 'POST',
			url: "/api/change-emp-department",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {

				if (data != null && data != "") {
					Swal.fire("Updated the Department !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist Department !!!", "", "error")
				}



			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});



	},

	subsidiaryChange: function() {

		let userSubsidiary = $("#edit-user-subsidiary option:selected").val();
		let userSubsidiaryCode = $("#edit-user-subsidiary option:selected").text();
		let userId = $('#EmpId').val();

		var json = {
			"userSubsidiary": userSubsidiary,
			"userSubsidiaryCode": userSubsidiaryCode,
			"userId": userId,
		}

		console.log(json);

		$.ajax({
			method: 'POST',
			url: "/api/change-emp-subsidiary",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {

				if (data != null && data != "") {
					Swal.fire("Updated the subsidiary !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist Department !!!", "", "error")
				}



			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});




	},

	locationChange: function() {

		let userLocationid = $("#edit-user-location option:selected").val();
		let userLocationCode = $("#edit-user-location option:selected").text();
		let userId = $('#EmpId').val();

		var json = {
			"userLocationid": userLocationid,
			"userLocationCode": userLocationCode,
			"userId": userId,
		}
		$.ajax({
			method: 'POST',
			url: "/api/change-emp-location",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {

				if (data != null && data != "") {
					Swal.fire("Updated the location !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist Location !!!", "", "error")
				}
			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}
		});
	},
	classChange: function() {

		let userLocationid = $("#edit-user-class option:selected").val();
		let userLocationCode = $("#edit-user-class option:selected").text();
		let userId = $('#EmpId').val();

		var json = {
			"classKey": userLocationid,
			"className": userLocationCode,
			"userId": userId,
		}
		$.ajax({
			method: 'POST',
			url: "/api/change-emp-class",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {

				if (data != null && data != "") {
					Swal.fire("Updated the Class !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist Class !!!", "", "error")
				}
			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}
		});
	},

	businessunitChange: function() {

		let userBusinessunitid = $("#edit-user-businessunit option:selected").val();
		let userBusinessunitcode = $("#edit-user-businessunit option:selected").text();
		let userId = $('#EmpId').val();

		var json = {
			"businessUnitKey": userBusinessunitid,
			"businessUnitName": userBusinessunitcode,
			"userId": userId,
		}

		console.log(json);

		$.ajax({
			method: 'POST',
			url: "/api/change-emp-businessunit",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {

				if (data != null && data != "") {
					Swal.fire("Updated the businessUnit !!!", "", "success").then((data) => {
						window.location = "/employee"
					});
				} else {
					Swal.fire("Please Change Exist businessunit !!!", "", "error")
				}



			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});




	},



	UpdateCustomer: function() {
		var stage = 0;
		var id = $("#user-id").val();

		var userName = $("#user-name").val();
		var userEmail = $("#user-email").val();
		var userPsw = $("#user-psw").val();
		var empCode = $("#user-empcode").text();

		var empDep = $("#user-department option:selected").text();
		var empDepcode = $("#user-department option:selected").val();


		let reportTo = $("#user-reportTo option:selected").val();
		let secondApproval = $("#user-approval option:selected").val();


		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();


		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let userRole = $("#user-role option:selected").val();
		let userRolecode = $("#user-role option:selected").text();



		if (userName.trim().length == 0) {
			Swal.fire("Invalid User Name", "Please Enter User Name", "error");
			stage = 1;
			return;
		}

		if (userEmail.trim().length == 0) {
			Swal.fire("Invalid User Email", "Please Enter User Email", "error");
			stage = 1;
			return;
		}
		if (userPsw.trim().length == 0) {
			Swal.fire("Invalid User Password", "Please Enter User Password", "error");
			stage = 1;
			return;
		}
		if (userRole.trim().length == 0) {
			Swal.fire("Invalid User Role", "Please Select Role ", "error");
			stage = 1;
			return;
		}



		if (empDep.trim().length == 0) {
			Swal.fire("Invalid User Department", "Please Select User Department ", "error");
			stage = 1;
			return;
		}

		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid User Unit", "Please Select User Unit", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid User Location", "Please Select User Location", "error");
			stage = 1;
			return;
		}



		var json = {
			"id": id,
			"username": userName,
			"userMail": userEmail,
			"password": userPsw,
			"departmentCode": empDep,
			"department": empDepcode,
			"reportTo": reportTo,
			"secondApproval": secondApproval,
			"roleId": userRole,
			"roleCode": userRolecode,
			"employeeCode": empCode,
			"locationCode": location,
			"locationId": locationcode,
			"unit": unit,
			"unitCode": unitcode
		}

		console.log(json);

		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: '/api/userupdate',
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Update the User!", "", "success").then((data) => {
						window.location = "/employee"
					});
				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}




	}
	,
	CreateDepartment: function() {


		var departmentCode = $("#department-Code").val();
		var departmentName = $("#department-Name").val();


		var json = {
			"department": departmentName,
			"departmentCode": departmentCode,

		}
		$.ajax({
			method: 'POST',
			url: departmentCreationUrl,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {
				//console.log(data);

				Swal.fire("Created the Department !", "", "success").then((data) => {
					/*window.location.reload();*/
				});
				$("#department-Code").val("");
				$("#department-Name").val("");

			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});

	},
	ackIndent: function(ele) {

		console.log($(ele))

		var lineId = $(ele)[0].dataset.line;

		var itemcode = $(ele)[0].dataset.itemcode;



		var json = {};
		$.ajax({
			method: 'POST',
			url: "/api/indentack/" + lineId + "/" + itemcode,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {
				//console.log(data);

				Swal.fire("Acknowledge", "", "success").then((data) => {
				});

				window.location.reload();

			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});


	},

	CreateRole: function() {

		var RoleCode = $("#role-Code").val();
		var RoleName = $("#role-Name").val();


		var json = {
			"role": RoleName,
			"roleCode": RoleCode,

		}
		$.ajax({
			method: 'POST',
			url: roleCreationUrl,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {
				//console.log(data);

				Swal.fire("Created the Role !", "", "success").then((data) => {
					/*window.location.reload();*/
					window.location = "/rolelist"


				});
				$("#role-Code").val("");
				$("#role-Name").val("");

			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});

	},
	CreateChat: function(ele) {

		var command = $('#command').val();
		var indentid = $('#indentid').val();

		dataToSend = {

			"indentId": indentid,
			"command": command,

		}

		$.ajax({
			method: 'POST',
			url: "/api/chatCreation",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(dataToSend),

			success: function(data) {
				Toast.fire({
					icon: 'success',
					title: 'Chat Added Successfully!'
				})
				$('.direct-chat-msg').remove();

				$.ajax({
					method: 'GET',
					url: "/api/chatList/" + indentid,
					success: function(data) {
						var chat = '';
						for (var i = 0; i < data.length; i++) {
							const mydate = data[i].createdOn;
							let utcDate = moment(mydate);

							chat += '<div class="direct-chat-msg right"> <div class="direct-chat-info clearfix"> <span class="direct-chat-name pull-right dispute_closed_by"></span> <span class="direct-chat-timestamp pull-left  dispute_closed_on"><img src="/dist/img/chatuser.png" alt="message user image"> ' + data[i].createdName + '&nbsp;&nbsp;<img src="/dist/img/clock.png" alt="message user image"> ' + utcDate.format("DD-MM-YYYY HH:mm:ss") + '&nbsp;&nbsp;&nbsp; </span> </div> <img class="direct-chat-img" src="/dist/img/chatuser.png" alt="message user image"> <div class="direct-chat-text Admincomments">' + data[i].command + '</div> </div>'
						}
						$('#AppendChatData').append(chat);
					}
				});
				$('#Chat-modal').modal("show");
			},
			error: function(data) {
				Toast.fire({
					icon: 'error',
					title: ' Not Successfully Added Chat!'
				});
			}

		});


		$("#command").val("");

	},


	CreateIndent: function() {

		var stage = 0;

		var indentDate = $("#reservation").val();
		var Requestor = $("#requestor").val();

		let locationcode = $("#user-fromlocation option:selected").val();
		let location = $("#user-fromlocation option:selected").text();

		let tolocationcode = $("#user-tolocation option:selected").val();
		let tolocation = $("#user-tolocation option:selected").text();

		let loccode = $("#user-location option:selected").val();
		let loc = $("#user-location option:selected").text();




		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let departmentid = $("#user-department option:selected").val();
		let department = $("#user-department option:selected").text();

		var headerRemark = $("#headerRemark").val();
		var itemType = $("#itemType option:selected").val();
		
		
		/* Business Unit Validation on 07-Feb-2025 */
		let businessUnitId = $("#user-businessUnit option:selected").val();
		let businessUnitName = $("#user-businessUnit option:selected").text();
		/* End of Business Unit Validation on 07-Feb-2025 */

		// Validation Added on 07-Feb-2025
		//if(loc != businessUnitName){
		//	Swal.fire("Invalid Location And BusinessUnit", "Location And BusinessUnit Should be Same, Please Contact Admin!!", "error");
		//	stage = 1;
		//	return;
		//}

		if (indentDate.trim().length == 0) {
			Swal.fire("Invalid Date", "Please Enter Valid Date", "error");
			stage = 1;
			return;
		}


		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid Unit", "Please Select Unit", "error");
			stage = 1;
			return;
		}

		if (departmentid.trim().length == 0) {
			Swal.fire("Invalid Department", "Please Select Department", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		console.log();

		if (typeof tolocationcode === "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		if (typeof tolocationcode != "undefined") {
			if (locationcode.trim().length == 0) {
				Swal.fire("Invalid Location", "Please Select Location", "error");
				stage = 1;
				return;
			}
		}

		if (tolocationcode.trim().length == 0) {
			Swal.fire("Invalid Location", "Please Select To Location", "error");
			stage = 1;
			return;
		}

		if (itemType.trim().length == 0) {
			Swal.fire("Invalid Item Type", "Please Select Item Type", "error");
			stage = 1;
			return;
		}



		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var stock = $(value).closest('tr').find('.stock').val();
			var price = $(value).closest('tr').find('.price').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var total = $(value).closest('tr').find('.total').val();
			var needdate = $(value).closest('tr').find('.needdate').val();
			var remark = $(value).closest('tr').find('.remark').val();

			if (itemvalueid.trim().length == 0) {
				Swal.fire("Invalid Item", "Please Enter Item", "error");
				stage = 1;
				return;
			}






			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (qty.trim().length == 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(qty) <= 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (needdate.trim().length == 0) {
				Swal.fire("Invalid Need By Date ", "Please Enter Need By Date ", "error");
				stage = 1;
				return;
			}






			var line = {
				"itemCode": itemcode,
				"itemId": itemvalueid,
				"itemDescription": itemtext,
				"quantity": qty,
				"price": parseFloat(price),
				"totalPrice": parseFloat(total),
				"remark": remark,
				"uom": uom,
				"needBy": needdate,
				"approveStatus": "N"
			}

			indentLine.push(line);

		});


		var json = {

			"requestor": Requestor,
			"requestorId": null,
			"docDate": indentDate,
			"reqDate": indentDate,
			"subsidiary": unit,
			"subsidiaryId": unitcode,
			"department": department,
			"departmentId": departmentid,
			"location": location,
			"locationId": locationcode,
			"toLocation": tolocation,
			"tolocationId": tolocationcode,
			"userlocId": loccode,
			"userloc": loc,
			"remark": headerRemark,
			"indentLine": indentLine,
			"itemType": itemType
		}
		console.log(json);
		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: indentCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {

					Swal.fire("Indent have been Created!", "Indent Id-" + data.id, "success").then((data) => {
						window.location.reload();
					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}


	},
	CreatedraftIndent: function() {

		var stage = 0;
		var draftid = $("#draftid").val();

		var indentDate = $("#reservation").val();
		var Requestor = $("#requestor").val();

		let locationcode = $("#user-fromlocation option:selected").val();
		let location = $("#user-fromlocation option:selected").text();

		let tolocationcode = $("#user-tolocation option:selected").val();
		let tolocation = $("#user-tolocation option:selected").text();

		let loccode = $("#user-location option:selected").val();
		let loc = $("#user-location option:selected").text();



		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let departmentid = $("#user-department option:selected").val();
		let department = $("#user-department option:selected").text();

		var headerRemark = $("#headerRemark").val();

		var itemType = $("#itemType option:selected").val();


		if (indentDate.trim().length == 0) {
			Swal.fire("Invalid Date", "Please Enter Valid Date", "error");
			stage = 1;
			return;
		}


		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid Unit", "Please Select Unit", "error");
			stage = 1;
			return;
		}

		if (departmentid.trim().length == 0) {
			Swal.fire("Invalid Department", "Please Select Department", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		console.log();

		if (typeof tolocationcode === "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		if (typeof tolocationcode != "undefined") {
			if (locationcode.trim().length == 0) {
				Swal.fire("Invalid Location", "Please Select Location", "error");
				stage = 1;
				return;
			}
		}

		if (tolocationcode.trim().length == 0) {
			Swal.fire("Invalid Location", "Please Select To Location", "error");
			stage = 1;
			return;
		}

		if (itemType.trim().length == 0) {
			Swal.fire("Invalid Item Type", "Please Select Item Type", "error");
			stage = 1;
			return;
		}



		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var stock = $(value).closest('tr').find('.stock').val();
			var price = $(value).closest('tr').find('.price').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var total = $(value).closest('tr').find('.total').val();
			var needdate = $(value).closest('tr').find('.needdate').val();
			var remark = $(value).closest('tr').find('.remark').val();

			if (itemvalueid.trim().length == 0) {
				Swal.fire("Invalid Item", "Please Enter Item", "error");
				stage = 1;
				return;
			}






			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (qty.trim().length == 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(qty) <= 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (needdate.trim().length == 0) {
				Swal.fire("Invalid Need By Date ", "Please Enter Need By Date ", "error");
				stage = 1;
				return;
			}






			var line = {
				"itemCode": itemcode,
				"itemId": itemvalueid,
				"itemDescription": itemtext,
				"quantity": qty,
				"price": parseFloat(price),
				"totalPrice": parseFloat(total),
				"remark": remark,
				"uom": uom,
				"needBy": needdate,
				"approveStatus": "N"
			}

			indentLine.push(line);

		});


		var json = {

			"requestor": Requestor,
			"requestorId": null,
			"docDate": indentDate,
			"reqDate": indentDate,
			"subsidiary": unit,
			"subsidiaryId": unitcode,
			"department": department,
			"departmentId": departmentid,
			"location": location,
			"locationId": locationcode,
			"toLocation": tolocation,
			"tolocationId": tolocationcode,
			"userlocId": loccode,
			"userloc": loc,
			"remark": headerRemark,
			"draftid": draftid,
			"indentLine": indentLine,
			"itemType": itemType
		}
		console.log(json);
		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: drafttoindentCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {

					Swal.fire("Indent have been Created!", "Indent Id-" + data.id, "success").then((data) => {
						window.location = "/intentdraftlist"
					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}


	},
	editIndent: function() {

		var stage = 0;

		var id = $("#indentid").val();





		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var stock = $(value).closest('tr').find('.stock').val();
			var price = $(value).closest('tr').find('.price').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var total = $(value).closest('tr').find('.total').val();
			var needdate = $(value).closest('tr').find('.needdate').val();
			var remark = $(value).closest('tr').find('.remark').val();

			if (itemvalueid.trim().length == 0) {
				Swal.fire("Invalid Item", "Please Enter Item", "error");
				stage = 1;
				return;
			}






			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (qty.trim().length == 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(qty) <= 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (needdate.trim().length == 0) {
				Swal.fire("Invalid Need By Date ", "Please Enter Need By Date ", "error");
				stage = 1;
				return;
			}






			var line = {
				"itemCode": itemcode,
				"itemId": itemvalueid,
				"itemDescription": itemtext,
				"quantity": qty,
				"price": parseFloat(price),
				"totalPrice": parseFloat(total),
				"remark": remark,
				"uom": uom,
				"needBy": needdate,
				"approveStatus": "N"
			}

			indentLine.push(line);

		});


		var json = {

			"id": id,
			"indentLine": indentLine,

		}
		console.log(json);
		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: "/api/indentEdit",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Indent have been Updated!", "", "success").then((data) => {
						window.location = "/intentApprovel"

					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}


	},
	editUserIndent: function() {
		var stage = 0;

		var id = $("#indentid").val();





		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var stock = $(value).closest('tr').find('.stock').val();
			var price = $(value).closest('tr').find('.price').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var total = $(value).closest('tr').find('.total').val();
			var needdate = $(value).closest('tr').find('.needdate').val();
			var remark = $(value).closest('tr').find('.remark').val();

			if (itemvalueid.trim().length == 0) {
				Swal.fire("Invalid Item", "Please Enter Item", "error");
				stage = 1;
				return;
			}






			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (qty.trim().length == 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(qty) <= 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (needdate.trim().length == 0) {
				Swal.fire("Invalid Need By Date ", "Please Enter Need By Date ", "error");
				stage = 1;
				return;
			}






			var line = {
				"itemCode": itemcode,
				"itemId": itemvalueid,
				"itemDescription": itemtext,
				"quantity": qty,
				"price": parseFloat(price),
				"totalPrice": parseFloat(total),
				"remark": remark,
				"uom": uom,
				"needBy": needdate,
				"approveStatus": "N"
			}

			indentLine.push(line);

		});


		var json = {

			"id": id,
			"indentLine": indentLine,

		}
		console.log(json);
		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: "/api/indentEdit",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Indent have been Updated!", "", "success").then((data) => {
						window.location = "/pendingList"

					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}


	},
	CreateDraft: function() {

		var stage = 0;
		var indentDate = $("#reservation").val();
		var Requestor = $("#requestor").val();

		let locationcode = $("#user-fromlocation option:selected").val();
		let location = $("#user-fromlocation option:selected").text();

		let tolocationcode = $("#user-tolocation option:selected").val();
		let tolocation = $("#user-tolocation option:selected").text();

		let loccode = $("#user-location option:selected").val();
		let loc = $("#user-location option:selected").text();

		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let departmentid = $("#user-department option:selected").val();
		let department = $("#user-department option:selected").text();

		var headerRemark = $("#headerRemark").val();
		var itemType = $("#itemType option:selected").text();


		if (indentDate.trim().length == 0) {
			Swal.fire("Invalid Date", "Please Enter Valid Date", "error");
			stage = 1;
			return;
		}


		if (unitcode.trim().length == 0) {
			Swal.fire("Invalid Unit", "Please Select Unit", "error");
			stage = 1;
			return;
		}

		if (departmentid.trim().length == 0) {
			Swal.fire("Invalid Department", "Please Select Department", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}



		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}
		if (typeof tolocationcode === "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		if (typeof tolocationcode != "undefined") {
			if (locationcode.trim().length == 0) {
				Swal.fire("Invalid Location", "Please Select Location", "error");
				stage = 1;
				return;
			}
		}
		if (itemType.trim().length == 0) {
			Swal.fire("Invalid Item Type", "Please Select Item Type", "error");
			stage = 1;
			return;
		}





		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var stock = $(value).closest('tr').find('.stock').val();
			var price = $(value).closest('tr').find('.price').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var total = $(value).closest('tr').find('.total').val();
			var needdate = $(value).closest('tr').find('.needdate').val();
			var remark = $(value).closest('tr').find('.remark').val();


			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (qty.trim().length == 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}

			if (parseFloat(qty) <= 0) {
				Swal.fire("Invalid Quantity ", "Please Enter Quantity ", "error");
				stage = 1;
				return;
			}

			if (needdate.trim().length == 0) {
				Swal.fire("Invalid Need By Date ", "Please Enter Need By Date ", "error");
				stage = 1;
				return;
			}
			var line = {
				"itemCode": itemcode,
				"itemId": itemvalueid,
				"itemDescription": itemtext,
				"quantity": qty,
				"price": parseFloat(price),
				"totalPrice": parseFloat(total),
				"remark": remark,
				"uom": uom,
				"needBy": needdate,
				"approveStatus": "N"
			}

			indentLine.push(line);

		});


		var json = {

			"requestor": Requestor,
			"requestorId": null,
			"docDate": indentDate,
			"reqDate": indentDate,
			"subsidiary": unit,
			"subsidiaryId": unitcode,
			"department": department,
			"departmentId": departmentid,
			"location": location,
			"locationId": locationcode,
			"toLocation": tolocation,
			"tolocationId": tolocationcode,
			"userlocId": loccode,
			"userloc": loc,
			"remark": headerRemark,
			"indentLine": indentLine,
			"itemType": itemType
		}
		console.log(json);

		if (stage == 0) {

			$.ajax({
				method: 'POST',
				url: draftCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					console.log(data);

					Swal.fire("Draft have been Created!", "", "success").then((data) => {
						window.location.reload();
					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}



	},
	CreateTemplate: function() {

		var stage = 0;


		var templateMaster = $("#template").val();

		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();




		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let departmentid = $("#user-department option:selected").val();
		let department = $("#user-department option:selected").text();


		var itemType = $("#itemType option:selected").val();

		if (templateMaster.trim().length == 0) {
			Swal.fire("Invalid Template Master", "Please Enter Template Master", "error");
			stage = 1;
			return;
		}

		if (itemType.trim().length == 0) {
			Swal.fire("Invalid Item Type", "Please Enter Item Type", "error");
			stage = 1;
			return;
		}

		var getRows = $('#emptbl>tbody>tr');
		var indentLine = []

		$.each(getRows, function(index, value) {

			var itemtext = $(value).closest('tr').find('.ajax-item option:selected').text();
			var itemvalueid = $(value).closest('tr').find('.ajax-item option:selected').val();

			var itemcode = $(value).closest('tr').find('.itemcode').val();
			var uom = $(value).closest('tr').find('.uom').val();
			var price = $(value).closest('tr').find('.price').val();

			if (itemcode.trim().length == 0) {
				Swal.fire("Invalid Item Code", "Please Select Item Code", "error");
				stage = 1;
				return;
			}

			if (price.trim().length == 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}
			if (parseInt(price) <= 0) {
				Swal.fire("Invalid Price ", "Please Enter Price ", "error");
				stage = 1;
				return;
			}
			var line = {
				"itemId": itemvalueid,
				"itemCode": itemcode,
				"itemName": itemtext,
				"price": price,
				"uom": uom,

			}

			indentLine.push(line);

		});

		var json = {

			"templateMaster": templateMaster,
			"templateCode": templateMaster,
			"templateLine": indentLine,
			"subsidiary": unit,
			"subsidiaryId": unitcode,
			"department": department,
			"departmentId": departmentid,
			"location": location,
			"locationId": locationcode,
			"itemType": itemType
		}

		if (stage == 0) {
			$.ajax({
				method: 'POST',
				url: templateCreationUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(json),
				success: function(data) {
					//console.log(data);

					Swal.fire("Created the Template !", "", "success").then((data) => {
						window.location.reload();
					});

				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});
		}

	},

	CreateapprovalRole: function() {


		let reportTo = $("#user-reportTo option:selected").val();
		let secondApproval = $("#user-approval option:selected").val();
		let thirdApproval = $("#user-approval-third option:selected").val();

		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();


		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let department_id = $("#user-dept option:selected").val();
		let department = $("#user-dept option:selected").text();


		if (department_id.trim().length == 0) {
			Swal.fire("Invalid Department", "Please Select Department", "error");
			stage = 1;
			return;
		}

		if (typeof (locationcode) == "undefined") {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}



		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}




		if (locationcode.trim().length == 0) {
			Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			return;
		}

		if (reportTo.trim().length == 0) {
			Swal.fire("Invalid First Approver", "Please Select First Approver", "error");
			stage = 1;
			return;
		}

		/*	if (secondApproval.trim().length == 0) {
				Swal.fire("Invalid Second Approver", "Please Select Second Approver", "error");
				stage = 1;
				return;
			}
			if (thirdApproval.trim().length == 0) {
				Swal.fire("Invalid First Approver", "Please Select Third Approver", "error");
				stage = 1;
				return;
			}*/

		var json = {
/*  "roleId": null,
*/  "unitId": unitcode,
			"unitName": unit,
			"locationId": locationcode,
			"department": department,
			"departmentId": department_id,
			"location_name": location,
			"firstApprover": reportTo,
			"secondApprover": secondApproval,
			"thirdApprover": thirdApproval
		};
		console.log(json)

		$.ajax({
			method: 'POST',
			url: approvalRoleCreationUrl,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(json),
			success: function(data) {
				//console.log(data);

				Swal.fire("Created the Approval Role !", "", "success").then((data) => {
/*					window.location.reload();
*/				});

			},
			error: function(data) {
				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
					"<span style=color:black> -</span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});
			}

		});
	},

	IndentReject: function(ele) {

		var indentID = $(ele).closest('a')[0].dataset.indentid;

		Swal.fire({
			icon: 'warning',
			title: 'Add Reject Comments',
			input: 'text',
			html: '<b>Please add the  comments <b>',
			confirmButtonText: 'Reject',
			customClass: {
				validationMessage: 'my-validation-message'
			},
			preConfirm: (value) => {
				if (!value) {
					Swal.showValidationMessage(
						'<i class="fa fa-info-circle"></i> Comments is required'
					)
				} else {
					var json = {
						"command": value,
						"indnetid": indentID
					}
					$.ajax({
						method: 'POST',
						url: "/api/indentReject",
						contentType: "application/json; charset=utf-8",
						data: JSON.stringify(json),
						success: function(data) {
							//console.log(data);

							Swal.fire("Indent have been Reject !", "", "success").then((data) => {
							});

							window.location.reload();

						},
						error: function(data) {
							var myhtml = document.createElement("div");
							myhtml.innerHTML = "<span style=color:blue><b>Please Refresh! " + data.responseJSON.message + " </b></span></br></br>" +
								"<span style=color:black> -</span>";
							swal.fire({
								html: myhtml,
								showCloseButton: true,
								showConfirmButton: false,
							});
						}

					});


				}
			}
		})
	},

	IndentApproval: function(ele) {

		var indentID = $(ele)[0].dataset.indentid;

		var json = {
			"command": "N",
			"indnetid": indentID
		}

		Swal.fire({
			title: 'Please Confirm the Approve',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Approve'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					method: 'POST',
					url: "/api/indentApproval",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(json),
					success: function(data) {
						//console.log(data);

						Swal.fire("Indent have been Approved !", "", "success").then((data) => {
						});

						window.location.reload();

					},
					error: function(data) {
						var myhtml = document.createElement("div");
						myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
							"<span style=color:black> -</span>";
						swal.fire({
							html: myhtml,
							showCloseButton: true,
							showConfirmButton: false,
						});
					}

				});
			}
		})



	},
	CreateTO: function() {



		var tempDate = new Date($("#reservation").val());
		var dateval = [tempDate.getDate(), tempDate.getMonth() + 1, tempDate.getFullYear()].join('/');
		var fromLocation = $("#fromLocation").val()
		var toLocation = $("#toLocation").val()


		var getRows = $('#emptbl>tbody>tr');

		$.each(getRows, function(index, value) {
			var itemcode = $(value).closest('tr').find('.ajax-item').val();
			var qty = $(value).closest('tr').find('.qty').val();
			var price = $(value).closest('tr').find('.price').val();


			var item = {
				"date": dateval,
				"subsidiary": "1",
				"status": "A",
				"incoterm": "1",
				"toLocation": toLocation,
				"item": itemcode,
				"quantity": qty,
				"amount": price
			}

			/*if (itemcode.trim().length == 0) {
				Swal.fire("enter the item code", "error");
				return false;
			}*/


			var url = "/api/TransferOrder";
			$.ajax({
				method: 'POST',
				url: url,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(item),
				success: function(data) {
					//console.log(data);

					Swal.fire("Created saved Transfer Order!", "", "success").then((data) => {
						window.location.reload();
					});
				},
				error: function(data) {
					var myhtml = document.createElement("div");
					myhtml.innerHTML = "<span style=color:blue><b>Please Refresh ! " + data.responseJSON.message + " </b></span></br></br>" +
						"<span style=color:black> -</span>";
					swal.fire({
						html: myhtml,
						showCloseButton: true,
						showConfirmButton: false,
					});
				}

			});

			//console.log(item);

		});

		/*

		var monthId = $(ele)[0].dataset.monthid;
		var monthName = $(ele)[0].dataset.monthname;
		var fy = $(ele)[0].dataset.fy;

		Swal.fire({
			title: 'Create the Snapshot for the :  ' + monthName + ' - ' + fy + ' !!',
			showDenyButton: true,
			icon: 'warning',
			confirmButtonText: 'Create SnapSahot',
			denyButtonText: `Don't Create`,
		}).then((result) => {
			if (result.isConfirmed) {

				var myhtml = document.createElement("div");
				myhtml.innerHTML = "<span style=color:blue><b> Your Request is Processing !! </b></span></br></br>" +
					"<span style=color:black> Please Wait... </span>";
				swal.fire({
					html: myhtml,
					showCloseButton: true,
					showConfirmButton: false,
				});

				var lineItems = [];
				var tbllines = $('table#example1 tbody tr');
				$.each(tbllines.not(':last'), function(index, ele) {
					var tds = $(ele).find('td');
					var itemGroup = $(tds[2]).text();
					var customer = $(tds[3]).text();
					var description = $(tds[4]).text();
					var configure = $.trim($(tds[5]).text());
					var soDeliveryDate = $(tds[32]).text();
					var soAmount = $(tds[7].attributes[1])[0].nodeValue;// $(tds[7]).text();
					var targetDate = $(tds[19]).text();
					var receivedAmount = $(tds[9].attributes[1])[0].nodeValue;// $(tds[9]).text();
					var receivedOn = $(tds[27]).text();// $(tds[10]).text();
					var billDate = $(tds[31]).text();//$(tds[11]).text();
					var billAmount = $(tds[12].attributes[1])[0].nodeValue;//$(tds[12]).text();
					var billGst = $(tds[13].attributes[1])[0].nodeValue;// $(tds[13]).text();
					var sapPaymentDocNum = $(tds[14]).text();
					var sapReceivedAmount = $(tds[15].attributes[1])[0].nodeValue;// $(tds[15]).text();
					var sapReceivedOn = $(tds[28]).text();
					var bankReferenceNo = $(tds[17]).text();
					var soStatus = $(tds[20]).text();
					var soDocEntry = $(tds[21]).text();
					var soDocNum = $(tds[22]).text();
					var soLine = $(tds[23]).text();
					var invoiceDocEntry = $(tds[24]).text();
					var invoiceDocNum = $(tds[25]).text();
					var invoiceLine = $(tds[26]).text();
					var fpId = $(tds[29]).text();
					var fpUc = $(tds[30]).text();
					if (targetDate.trim().length == 0) {
						Swal.fire("Target date was Empty!!", "Please Enter the Target date for : " + customer + "  & ItemGroup :  " + itemGroup + " " + index + "", "error");
						return false;
					}
					if (targetDate.trim().length == 0) {
						Swal.fire("Target date was Empty!!", "Please Enter the Target date for : " + customer + "  & ItemGroup :  " + itemGroup + " " + index + "", "error");
						return false;
					}

					if (receivedAmount == '0') {
						Swal.fire("Recevied Amount was Empty!!", "Please Enter the Recevied Amount for : " + customer + "  & ItemGroup :  " + itemGroup + " " + index + "", "error");

						return false;
					}

					var item = {
						"itemGroup": itemGroup,
						"customer": customer,
						"description": description,
						"config": configure,
						"baseMonth": soDeliveryDate,
						"soAmount": soAmount,
						"targetDate": targetDate,
						"receivedAmount": receivedAmount,
						"receivedOn": receivedOn,
						"billDate": billDate,
						"billAmount": billAmount,
						"billGst": billGst,
						"sapPaymentDocNum": sapPaymentDocNum,
						"sapReceivedAmount": sapReceivedAmount,
						"sapReceivedOn": sapReceivedOn,
						"bankRef": bankReferenceNo,
						"soStatus": soStatus,
						"soDocEntry": soDocEntry,
						"soDocNum": soDocNum,
						"soLine": soLine,
						"invDocEntry": invoiceDocEntry,
						"invDocNum": invoiceDocNum,
						"invLine": invoiceLine,
						"fpId": fpId,
						"uc": fpUc
					}
					lineItems.push(item);
				});




				var dataToSend = {
					"month": monthId,
					"fy": fy,
					"fundPlanSnapLine": lineItems
				};

				var status = 0;

				for (let i = 0; i < lineItems.length; i++) {

					if (lineItems[i].receivedAmount == '0') {
						Swal.fire("Recevied Amount was Empty!!", "Please Enter the Recevied Amount for : " + customer + "  & ItemGroup :  " + itemGroup + " " + index + "", "error");
						return false;
					} else {
						status = 1;
					}
				}


				if (status == 1) {
					var url = "/api/reports/snap/save";
					$.ajax({
						method: 'POST',
						url: url,
						contentType: "application/json; charset=utf-8",
						data: JSON.stringify(dataToSend),
						success: function(data) {
							//console.log(data);

							Swal.fire("Snapshot saved Successfully!", "" + data + "", "success").then((data) => {
								window.location.reload();
							});
						},
						error: function(data) {
							var myhtml = document.createElement("div");
							myhtml.innerHTML = "<span style=color:blue><b> Your Snapshot was Not Created ! " + data.responseJSON.message + " </b></span></br></br>" +
								"<span style=color:black> -</span>";
							swal.fire({
								html: myhtml,
								showCloseButton: true,
								showConfirmButton: false,
							});
						}

					});
				}

			} else if (result.isDenied) {
				Swal.fire('Snapshot Request was Cancelled', '', 'info')
			}
		})
*/
	}
	,




}

app.ui = {

	getRole: function() {
		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/role",
				success: function(data) {
					//console.log(data);

					var customer = '<option value="" disabled selected >Select Role</option>';
					for (var i = 0; i < data.length; i++) {
						customer += '<option value="' + data[i].id + '">' + data[i].role + '</option>';
					}
					//console.log(customer);
					$(".ajax-role").html(customer);
				}
			});
		});
	},
	getInv: function() {
		let itemTypetid = $("#itemType option:selected").val();

		if (itemTypetid.length != 0) {

			$("#itemType").prop('disabled', true);
			app.ui.getItemindent();
		}



	},
	getDept: function() {
		$(document).ready(function() {



			$(document).ready(function() {
				$.ajax({
					type: "GET",
					url: "/api/GetDepartment",
					success: function(data) {


						$(".ajax-dept").select2({

							data: data.result,
							placeholder: " Select Item"

						})
					}
				});
			});
		});
	}, getReportloc: function() {
		$(document).ready(function() {



			$(document).ready(function() {
				$.ajax({
					type: "GET",
					url: "/api/Getlocationlist",
					success: function(data) {


						console.log(data)

						var customer = '';
						for (var i = 0; i < data.length; i++) {
							customer += '<option value="' + data[i].id + '">' + data[i].text + '</option>';
						}
						//console.log(customer);
						$(".ajax-reportloc").html(customer);
					}
				});
			});
		});
	},
	getLocation: function() {
		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetLocation",
				success: function(data) {
					//console.log();

					var customer = '<option value="" disabled selected >Select Location</option>';
					for (var i = 0; i < data.result.length; i++) {
						customer += '<option value="' + data.result[i].internalId + '">' + data.result[i].name + '</option>';
					}
					//console.log(customer);
					$(".ajax-location").html(customer);
				}
			});
		});
	},
	getSubsidiary: function() {
		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetSubsidiary",
				success: function(data) {
					//console.log();

					var customer = '<option value="" disabled selected >Select Unit</option>';
					for (var i = 0; i < data.result.length; i++) {
						customer += '<option value="' + data.result[i].internalId + '">' + convertionString(data.result[i].name) + '</option>';

					}
					//console.log(customer);
					$(".ajax-subsidiary").html(customer);
				}
			});
		});
	},


	getUser: function() {
		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/user",
				success: function(data) {
					//console.log();

					var customer = '<option value="" disabled selected >Select User</option>';
					for (var i = 0; i < data.length; i++) {
						customer += '<option value="' + data[i].id + '">' + data[i].username + '</option>';
					}
					//console.log(customer);
					$(".ajax-user").html(customer);
				}
			});
		});
	},
	getUserapprover: function() {

		var stage = 0;
		let locationcode = $("#user-location option:selected").val();
		let location = $("#user-location option:selected").text();




		let unitcode = $("#user-unit option:selected").val();
		let unit = $("#user-unit option:selected").text();


		let departmentid = $("#user-dept option:selected").val();
		let department = $("#user-dept option:selected").text();




		if (unitcode.trim().length == 0) {
			//	Swal.fire("Invalid Unit", "Please Select Unit", "error");
			stage = 1;
			//return;
		}



		if (typeof (locationcode) == "undefined") {
			//	Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			//return;
		}



		if (locationcode.trim().length == 0) {
			//	Swal.fire("Invalid Location", "Please Select Location", "error");
			stage = 1;
			//	return;
		}



		$.ajax({
			type: "GET",
			url: "/api/user/" + unitcode + "/" + locationcode + "/" + departmentid + "/" + "ROLE_APPROVER",
			success: function(data) {
				console.log(data);

				var customer = '<option value="" disabled selected >Select User</option>';
				for (var i = 0; i < data.length; i++) {
					customer += '<option value="' + data[i].id + '">' + data[i].username + '</option>';
				}
				//console.log(customer);
				$(".ajax-user").html(customer);
			}
		});




	}, getchat: function(ele) {
		var indentid = $(ele).closest('span')[0].dataset.indentid;

		console.log(indentid)

		$('#indentid').val(indentid);

		$('.direct-chat-msg').remove();

		$.ajax({
			method: 'GET',
			url: "/api/chatList/" + indentid,
			success: function(data) {
				var chat = '';
				for (var i = 0; i < data.length; i++) {
					const mydate = data[i].createdOn;
					let utcDate = moment(mydate);

					chat += '<div class="direct-chat-msg right"> <div class="direct-chat-info clearfix"> <span class="direct-chat-name pull-right dispute_closed_by"></span> <span class="direct-chat-timestamp pull-left  dispute_closed_on"><img src="/dist/img/chatuser.png" alt="message user image"> ' + data[i].createdName + '&nbsp;&nbsp;<img src="/dist/img/clock.png" alt="message user image"> ' + utcDate.format("DD-MM-YYYY HH:mm:ss") + '&nbsp;&nbsp;&nbsp; </span> </div> <img class="direct-chat-img" src="/dist/img/chatuser.png" alt="message user image"> <div class="direct-chat-text Admincomments">' + data[i].command + '</div> </div>'
				}
				$('#AppendChatData').append(chat);
			}
		});

		$('#Chat-modal').modal("show");


	},
	getempdetials: function(ele) {
		let username = $("#user-name option:selected").text();
		let userid = $("#user-name option:selected").val();


		$.ajax({
			method: 'GET',
			url: "/api/Getemployee/" + userid,
			success: function(data) {
				var value = data.result;
				console.log(value);
				var subsity = '<option value="' + value.SubsidiaryId + '">' + value.SubsidiaryName + '</option>';
				var department = '<option value="' + value.DepartmentId + '">' + value.Department + '</option>';
				var classd = '<option value="' + value.ClassId + '">' + value.Class + '</option>';
				var location = '<option value="' + value.LocationId + '">' + value.LocationName + '</option>';


				var business = '<option value="' + value.BusinessUnitId + '">' + value.BusinessUnit + '</option>';
				$("#name").val(username);
				$("#user-department").html(department);
				$("#user-unit").html(subsity);
				$("#user-location").html(location);
				$("#user-class").html(classd);
				$("#user-business").html(business);
				$('#user-empcode').val(value.text)

				$('#user-email').val(value.Email)

			}
		});

	}

	, getTempList: function(ele) {

		let itemTypetxt = $("#itemType option:selected").text();
		let itemTypetid = $("#itemType option:selected").val();
		$('.direct-chat-msg').remove();

		if (itemTypetid.length == 0) {

			Swal.fire("Invalid  Item Type ", "Please Select Item Type", "error");

		} else {
			$.ajax({
				method: 'GET',
				url: "/api/templateListtyp/" + itemTypetid,
				success: function(data) {
					console.log(data)
					var chat = '';
					for (var i = 0; i < data.length; i++) {
						chat += '<div class="direct-chat-msg right"> <div class="direct-chat-info clearfix"> <span class="direct-chat-name pull-right dispute_closed_by"></span> </div>   <button type="button" class="btn bg-olive btn-flat margin " data-tempid=' + data[i].id + ' onclick="app.ui.templAdd(this)"> Add </button> &nbsp;&nbsp <b> Template Name: </b>' + data[i].templateMaster + '</div>'
					}
					$('#AppendChatData').append(chat);
				}
			});
			$('#Chat-modal').modal("show");
		}






	}, templAdd: function(ele) {
		var tempid = $(ele).closest('button')[0].dataset.tempid;
		console.log(tempid)
		let itemTypetid = $("#itemType option:selected").val();
		var modelindex = 1;
		$.ajax({
			method: 'GET',
			url: "/api/templateList/" + tempid,
			success: function(data) {
				console.log(data)

				var dataval = ""

				for (var i = 0; i < data.templateLine.length; i++) {

					modelindex = 1 + modelindex + 1;
					/*	var price = 0;
						var onhand = 0;
						var displayName = "";
						var uom = 0;
						if (itemTypetid == "noninv") {
							var url = "/api/GetItemnoninv/" + data.templateLine[i].itemId;
							var postData = "";
							$.get(url, postData, function(data, status) {
								data = JSON.parse(JSON.stringify(data));
								console.log(data);
	
	
	
								if (data.result.totalQuantityOnHand != null) {
									onhand = data.result.totalQuantityOnHand;
								}
	
								if (data.result.totalValue != null) {
									price = data.result.totalValue;
								}
								displayName = data.result.displayName;
								uom = data.result.unitstype;
	
							});
	
						}
	
						if (itemTypetid == "inv") {
							var url = "/api/GetIteminv/" + data.templateLine[i].itemId;
							var postData = "";
							$.get(url, postData, function(data, status) {
								data = JSON.parse(JSON.stringify(data));
								console.log(data);
	
	
	
	
								if (data.result.totalQuantityOnHand != null) {
									onhand = data.result.totalQuantityOnHand;
								}
	
								if (data.result.totalValue != null) {
									price = data.result.totalValue;
								}
								displayName = data.result.displayName;
								uom = data.result.unitstype;
							});
	
						}*/

					if (i == 0) {
						dataval += '<tr><td><button type="button" class="btn btn-info batchaddrow addCF">  <i class="fa fa-plus" aria-hidden="true"></i>                </button></td>       <td id="col4"><select class="form-control ajax-item select2" name="itemname"  id="#' + modelindex + 'v" onchange="app.ui.getitemdetials(this)"> <option value=' + data.templateLine[i].itemId + '>' + data.templateLine[i].itemName + '</option> </select></td> <td hidden ><input type="text" class="form-control itemcode"  placeholder="Item code" disabled="disabled" value=' + data.templateLine[i].itemCode + ' ></td>  <td id="col4"><input type="text" class="form-control uom"  placeholder="UOM" disabled="disabled"></td>  <td id="col1"><input type="number"  class="form-control stock" placeholder="Stock" disabled="disabled" ></td> <td id="col2"><input type="text" class="form-control price" placeholder="Price" value=' + data.templateLine[i].price + ' onchange="app.ui.getpriceDetials(this)" ></td>   <td id="col1"><input type="number"  class="form-control qty" placeholder="Quantity" onchange="app.ui.getQtyDetails(this)" value=0></td>   <td id="col3"><input type="number"  class="form-control total" placeholder="TotalPrice" disabled="disabled"></td>  <td id="col4"><input type="date" class="form-control needdate" placeholder="date"></td><td id="col4"><input type="text" class="form-control remark" placeholder="Remark"></td>  </tr>   ';

					} else {
						dataval += '<tr><td><button type="button" class="btn btn-danger batchdeleterow remCF" >   <i class="fa fa-minus" aria-hidden="true"></i>     </button></td>       <td id="col4"><select class="form-control ajax-item select2" name="itemname"  id="#' + modelindex + 'v" onchange="app.ui.getitemdetials(this)"> <option value=' + data.templateLine[i].itemId + '>' + data.templateLine[i].itemName + '</option> </select></td> <td hidden ><input type="text" class="form-control itemcode"  placeholder="Item code" disabled="disabled" value=' + data.templateLine[i].itemCode + ' ></td>  <td id="col4"><input type="text" class="form-control uom"  placeholder="UOM" disabled="disabled"></td>  <td id="col1"><input type="number"  class="form-control stock" placeholder="Stock" disabled="disabled" ></td> <td id="col2"><input type="text" class="form-control price" placeholder="Price" value=' + data.templateLine[i].price + ' onchange="app.ui.getpriceDetials(this)" ></td>   <td id="col1"><input type="number"  class="form-control qty" placeholder="Quantity" onchange="app.ui.getQtyDetails(this)" value=0></td>   <td id="col3"><input type="number"  class="form-control total" placeholder="TotalPrice" disabled="disabled"></td>  <td id="col4"><input type="date" class="form-control needdate" placeholder="date"></td><td id="col4"><input type="text" class="form-control remark" placeholder="Remark"></td>  </tr>   ';

					}
				}
				$("#emptbl").find("tr:gt(0)").remove();
				$("#customFields")
					.append(dataval);
			}
		});

		//$('#approver-modal').modal("hidden");


	},

	getChatDataList: function(ele) {

		var indentid = $(ele).closest('span')[0].dataset.indentid;

		console.log(indentid);

		$('.direct-chat-msg').remove();

		$.ajax({
			method: 'GET',
			url: "/api/chatList/" + indentid,
			success: function(data) {
				var chat = '';
				for (var i = 0; i < data.length; i++) {
					const mydate = data[i].createdOn;
					let utcDate = moment(mydate);
					chat += '<div class="direct-chat-msg right"> <div class="direct-chat-info clearfix"> <span class="direct-chat-name pull-right dispute_closed_by"></span> <span class="direct-chat-timestamp pull-left  dispute_closed_on"><img src="/dist/img/clock.png" alt="message user image"> ' + utcDate.format("DD-MM-YYYY HH:mm:ss") + '</span> </div> <img class="direct-chat-img" src="/dist/img/chatuser.png" alt="message user image"> <div class="direct-chat-text Admincomments">' + data[i].command + '</div> </div>'
				}
				$('#AppendChatData').append(chat);
			}
		});
		$('#Chat-modal').modal("show");


	}, getApproveDataList: function(ele) {

		var indentid = $(ele).closest('span')[0].dataset.indentid;

		console.log(indentid);

		$('.direct-chat-msg').remove();

		$.ajax({
			method: 'GET',
			url: "/api/indentApproverList/" + indentid,
			success: function(data) {
				console.log(data)
				var chat = '';
				for (var i = 0; i < data.length; i++) {

					const mydate = data[i].createdOn;
					let utcDate = moment(mydate);
					var appovestatus = ""

					if (data[i].approvedStatus == "FA") {
						appovestatus = "Approval"
					} else if (data[i].approvedStatus == "SA") {
						appovestatus = "Second Approval"

					} else if (data[i].approvedStatus == "TA") {
						appovestatus = "Third Approval"


					} else if (data[i].approvedStatus == "CA") {
						appovestatus = "Cancelled"


					} else if (data[i].approvedStatus == "Ack") {
						appovestatus = "Acknowledge"


					}

					chat += '<div class="direct-chat-msg right"> <div class="direct-chat-info clearfix"> <span class="direct-chat-name pull-right dispute_closed_by"></span> <span class="direct-chat-timestamp pull-left  dispute_closed_on"><img src="/dist/img/clock.png" alt="message user image"> ' + utcDate.format("DD-MM-YYYY HH:mm:ss") + ' </span> </div> <img class="direct-chat-img" src="/dist/img/chatuser.png" alt="message user image"> <div class="direct-chat-text Admincomments">' + appovestatus + ' by ' + data[i].approvedName + '</div> </div>'
				}
				$('#approveChatData').append(chat);
			}
		});
		$('#approver-modal').modal("show");


	},
	GetDeptmentSuite: function() {


		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetDepartment",
				success: function(data) {


					$(".ajax-item").select2({

						data: data.result,
						placeholder: " Select Item"

					})
				}
			});
		});
	},

	getItem: function() {





		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetItem",
				success: function(data) {
					//console.log(data);

					/*	var customer = '<option value="" disabled selected >Select Item</option>';
						for (var i = 0; i < data.result.length; i++) {
							customer += '<option value="' + data.result[i].id + '">' + data.result[i].text + '</option>';
						}
						//console.log(customer);
						$(".ajax-item").html(customer);*/

					$(".ajax-item").select2({

						data: data.result,
						placeholder: " Select Item"

					})
				}
			});
		});
		//$('.ajax-item').select2();
		/*	$(".ajax-item").select2({
				placeholder: " Select Item",
				data: data,
	
			})*/
	},
	getEmployee: function() {





		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/Getemployee",
				success: function(data) {


					$(".ajax-employee").select2({

						data: data.result,
						placeholder: " Select Employee"

					})
				}
			});
		});
		//$('.ajax-item').select2();
		/*	$(".ajax-item").select2({
				placeholder: " Select Item",
				data: data,
	
			})*/
	}, getItemindent: function() {

		let itemTypetxt = $("#itemType option:selected").text();
		let itemTypetid = $("#itemType option:selected").val();

		if (itemTypetid == "noninv") {

			/*	var itemurl = "/api/GetItemnoninv"
				$(document).ready(function() {
					$.ajax({
						type: "GET",
						url: itemurl,
						success: function(data) {
	
	
							$(".ajax-item").select2({
	
								data: data,
								placeholder: " Select Item"
	
							})
						}
					});
				});*/

			$('.ajax-item').select2({
				placeholder: ' Select Item',
				ajax: {
					delay: 250,
					url: function(params) {

						var ajaxURlVendor = "/api/Getrepononinvitemfilter/" + params.term;
						console.log(ajaxURlVendor);
						return ajaxURlVendor;
					},
					dataType: 'json',
					processResults: function(data) {
						// Tranforms the top-level key of the response object from 'items' to 'results'
						return {
							results: data
						};
					},
					cache: false
				},
				minimumInputLength: 1


			});


		} else if (itemTypetid == "inv") {

			$('.ajax-item').select2({
				placeholder: ' Select Item',
				ajax: {
					delay: 250,
					url: function(params) {

						var ajaxURlVendor = "/api/Getrepoinvitemfilter/" + params.term;
						console.log(ajaxURlVendor);
						return ajaxURlVendor;
					},
					dataType: 'json',
					processResults: function(data) {
						// Tranforms the top-level key of the response object from 'items' to 'results'
						return {
							results: data
						};
					},
					cache: false
				},
				minimumInputLength: 1


			});




			/*	
				var itemurl = "/api/Getrepoinvitem"
				$(document).ready(function() {
					$.ajax({
						type: "GET",
						url: itemurl,
						success: function(data) {
	
	
							$(".ajax-item").select2({
	
								data: data,
								placeholder: " Select Item"
	
							})
						}
					});
				});*/
		}




	},
	getitemselect: function(ele) {
		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetItem",
				success: function(data) {
					//console.log(data);

					var customer = '<option value="" disabled selected >Select Item</option>';
					for (var i = 0; i < data.result.length; i++) {
						customer += '<option value="' + data.result[i].id + '">' + i + '</option>';
					}
					//console.log(customer);

					$(ele).closest('tr td').find(".ajax-item").html(customer);
				}
			});
		});
	},
	getTempitemdetails: function(ele) {
		var itemcode = $(ele).closest('tr td').find('.ajax-item :selected').val();


		let itemTypetxt = $("#itemType option:selected").text();
		let itemTypetid = $("#itemType option:selected").val();

		if (itemTypetid == "noninv") {
			var url = "/api/GetItemnoninv/" + itemcode;
			var postData = "";
			$.get(url, postData, function(data, status) {
				data = JSON.parse(JSON.stringify(data));
				console.log(data);

				var price = 0;
				var onhand = 0;


				if (data.result.totalQuantityOnHand != null) {
					onhand = data.result.totalQuantityOnHand;
				}

				if (data.result.totalValue != null) {
					price = data.result.totalValue;
				}
				$(ele).closest('tr').find('.itemcode').val(data.result.displayName);
				$(ele).closest('tr').find('.uom').val(data.result.unitstype);
				$(ele).closest('tr').find('.price').val(price);

			});

		}

		if (itemTypetid == "inv") {
			var url = "/api/GetIteminv/" + itemcode;
			var postData = "";
			$.get(url, postData, function(data, status) {
				data = JSON.parse(JSON.stringify(data));
				console.log(data);

				var price = 0;
				var onhand = 0;


				if (data.result.totalQuantityOnHand != null) {
					onhand = data.result.totalQuantityOnHand;
				}

				if (data.result.totalValue != null) {
					price = data.result.totalValue;
				}
				$(ele).closest('tr').find('.itemcode').val(data.result.displayName);
				$(ele).closest('tr').find('.uom').val(data.result.purchaseUnit);
				$(ele).closest('tr').find('.price').val(price);

			});

		}










		//console.log(itemcode);

		/*		var url = "/api/GetItem/" + itemcode;
				var postData = "";
				$.get(url, postData, function(data, status) {
					data = JSON.parse(JSON.stringify(data));
					//console.log(data);
		
					var price = 0;
					var onhand = 0;
		
		
					if (data.result.totalQuantityOnHand != null) {
						onhand = data.result.totalQuantityOnHand;
					}
		
					if (data.result.totalValue != null) {
						price = data.result.totalValue;
					}
					$(ele).closest('tr').find('.itemcode').val(data.result.itemName);
					$(ele).closest('tr').find('.uom').val(data.result.unitsNype);
						$(ele).closest('tr').find('.price').val(price);
		
				});*/
	},
	getitemdetials: function(ele) {
		var itemcode = $(ele).closest('tr td').find('.ajax-item :selected').val();
		//console.log(itemcode);


		let itemTypetxt = $("#itemType option:selected").text();
		let itemTypetid = $("#itemType option:selected").val();
		let fromloc = $("#user-fromlocation option:selected").val();

		if (itemTypetid == "noninv") {
			var url = "/api/GetItemnoninv/" + itemcode;
			var postData = "";
			$.get(url, postData, function(data, status) {
				data = JSON.parse(JSON.stringify(data));
				console.log(data);

				var price = 0;
				var onhand = 0;


				if (data.result.totalQuantityOnHand != null) {
					onhand = data.result.totalQuantityOnHand;
				}

				if (data.result.totalValue != null) {
					price = data.result.cost;
				}
				$(ele).closest('tr').find('.itemcode').val(data.result.displayName);
				$(ele).closest('tr').find('.uom').val(data.result.purchaseUnit);
				$(ele).closest('tr').find('.stock').val(onhand);
				$(ele).closest('tr').find('.price').val(parseFloat(price));
				$(ele).closest('tr').find('.qty').val(1);
				var totalPrice = 1 * price
				$(ele).closest('tr').find('.total').val(parseFloat(totalPrice));
			});

		}

		if (itemTypetid == "inv") {
			var url = "/api/postinvitem/" + fromloc + "/" + itemcode;
			console.log(url)
			var postData = "";
			$.get(url, postData, function(data, status) {
				data = JSON.parse(JSON.stringify(data.result[0]));
				console.log(data);

				var price = 0;
				var onhand = 0;


				if (data.locationQuantityOnHand != null) {
					if (data.locationQuantityOnHand != "") {
						onhand = data.locationQuantityOnHand;
					}
				}

				if (data.cost != null) {
					if (data.cost != "") {

						price = data.cost;
					}
				}
				$(ele).closest('tr').find('.itemcode').val(data.displayName);
				$(ele).closest('tr').find('.uom').val(data.purchaseUnit);
				$(ele).closest('tr').find('.stock').val(onhand);
				$(ele).closest('tr').find('.price').val(parseFloat(price));
				$(ele).closest('tr').find('.qty').val(1);
				var totalPrice = 1 * price
				$(ele).closest('tr').find('.total').val(parseFloat(totalPrice));
			});

		}



	},
	getQtyDetails: function(ele) {
		var qty = $(ele).closest('tr').find('.qty').val();
		var price = $(ele).closest('tr').find('.price').val();

		var totalPrice = qty * price
		$(ele).closest('tr').find('.total').val(parseFloat(totalPrice).toFixed(2));
	},
	getpriceDetials: function(ele) {
		var qty = $(ele).closest('tr').find('.qty').val();
		var price = $(ele).closest('tr').find('.price').val();

		var totalPrice = qty * price
		$(ele).closest('tr').find('.total').val(parseFloat(totalPrice).toFixed(2));


	},
	getlocationFilter: function() {
		//console.log("wworking on this ");
		let unitcode = $("#user-unit option:selected").val();
		//console.log(unitcode);

		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetLocation/" + unitcode,
				success: function(data) {


					var customer = '<option value="" disabled selected >Select Location</option>';
					for (var i = 0; i < data.result.length; i++) {
						customer += '<option value="' + data.result[i].internalId + '">' + convertionString(data.result[i].name) + '</option>';
					}
					//console.log(customer);
					$(".ajax-location").html(customer);

					app.ui.getUserapprover()
				}
			});
		});

	}, gettolocationFilter: function() {
		//console.log("wworking on this ");
		let unitcode = $("#user-unit option:selected").val();
		//console.log(unitcode);

		$(document).ready(function() {
			$.ajax({
				type: "GET",
				url: "/api/GetLocation/" + unitcode,
				success: function(data) {

					console.log(data.result);
					let toloc = $("#user-location option:selected").val();

					var locval = []
					for (var j = 0; j < data.result.length; j++) {

						if (data.result[j].internalId != toloc) {
							locval.push(data.result[j]);
						}

					}
					var customer = '<option value="" disabled selected >Select Location</option>';
					for (var i = 0; i < locval.length; i++) {
						customer += '<option value="' + locval[i].internalId + '">' + convertionString(locval[i].name) + '</option>';
					}
					//console.log(customer);
					$(".ajax-tolocation").html(customer);

					//	app.ui.getUserapprover()
				}
			});
		});

	},
	getlocationwhs: function() {

		//	$(".ajax-fromwhslocation").html('<option value="404"> Main Store-Ooty Fernhill</option>');
		//	$(".ajax-towhslocation").html('<option value="405"> Virtual Ooty Fernhill</option>');



		let loc = $("#user-location option:selected").val();

		var url = "/api/GetLocationwhs/" + loc;
		var postData = "";
		$.get(url, postData, function(data, status) {

			var whs = "";
			var stor = "";
			var value = data.result;
			console.log(value)
			for (var i = value.length; i--;) {
				var itemwhslist = convertionString(value[i].text);
				var itemwhstype = convertionString(value[i].locationType);

				console.log(itemwhslist);
				console.log(itemwhstype);




				if (itemwhstype == "Warehouse") {
					console.log("whs")
					whs += '<option value="' + value[i].locationId + '">' + convertionString(value[i].text) + '</option>'
				} else {

					stor += '<option value="' + value[i].locationId + '">' + convertionString(value[i].text) + '</option>'


				}



			}

			console.log("whs")
			console.log("store")
			$(".ajax-towhslocation").html(whs);


			$(".ajax-fromwhslocation").html(stor);





		});


	}
	,
	monthSelection: function() {
		var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
		var month = (new Date()).getMonth() + 1;
		var Months = '<option value="" disabled>-- Select Month --</option><option value="All" >All</option>';

		for (var i = 0; i < monthNames.length; i++) {
			monthId = i + 1;
			if (month == monthId) {
				Months += '<option value="' + monthId + '" selected>' + monthNames[i] + '</option>';
			} else {
				Months += '<option value="' + monthId + '">' + monthNames[i] + '</option>';

			}
		}
		$(".ajax-months").html(Months);


	}

}


/*// this is the Place Where Every Function Executed and Declare in HTML Page
app.init = function() {
	app.model.getData("");
	app.ui.customerList();
	app.ui.indicatorList();
	app.ui.monthSelection();
	app.ui.itemGrpList();
	app.ui.customerListselect2();
}*/


/*app.fundPlanner = function() {
	app.model.recievedDate();
	app.model.targetDate();

}*/

app.transferOrder = function() {
	app.ui.getLocation();
	//app.ui.getItem();
	app.ui.getUser();

}


app.usercreation = function() {
	//app.ui.getLocation();
	//app.ui.getItem();
	app.ui.getSubsidiary();
	//app.ui.getUser();
	app.ui.getDept();
	app.ui.getEmployee();

}


app.indentCreation = function() {
	app.ui.getlocationwhs();

	//app.ui.getLocation();
	//app.ui.getSubsidiary();
	//app.ui.getDept();
	//app.ui.getItem();
	app.ui.getUser();
}



function dateConvertion(inst) {

	const monthNames = ["Jan", "Feb", "Mar", "Apr",
		"May", "Jun", "Jul", "Aug",
		"Sep", "Oct", "Nov", "Dec"];
	inst.currentDay;
	//console.log(inst.currentDay);
	//console.log(monthNames[inst.currentMonth])

	const date = [inst.currentDay, monthNames[inst.currentMonth], inst.currentYear.toString().substr(-2)].join('-');

	return date;
}

function convertionString(val) {

	var split = val.split(':');


	return split[split.length - 1]

}
app.autocontroller = {

	getalllocation: function() {
		var url = "/api/GetLocation";
		var postData = "";
		$.get(url, postData, function(data, status) {
			//	console.log(data);
			data = JSON.parse(JSON.stringify(data));
			app.data.locationdata = data.result;

			//console.log(app.data.locationdata);

		});

	}



}


function isNumber(evt) {

	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}

