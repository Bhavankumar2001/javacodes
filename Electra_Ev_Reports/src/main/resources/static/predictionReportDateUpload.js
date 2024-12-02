
$(document).ready(function() {
	$('#excel_file').change(function(event) {
		var file = event.target.files[0];
		if (!['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.ms-excel'].includes(file.type)) {
			$('#excel_data').html('<div class="alert alert-danger">Only .xlsx or .xls file format are allowed</div>');
			$('#excel_file').val('');
			return false;
		}

		var reader = new FileReader();
		reader.readAsArrayBuffer(file);
		reader.onload = function(event) {
			var data = new Uint8Array(reader.result);
			var workbook = XLSX.read(data, { type: 'array' });
			var sheet_name = workbook.SheetNames;
			var sheet_data = XLSX.utils.sheet_to_json(workbook.Sheets[sheet_name[0]], { header: 1 });
			console.log(sheet_data)
			if (sheet_data.length > 0) {
				var table_output = '<table class="table table-striped table-bordered">';
				for (var row = 0; row < sheet_data.length; row++) {
					table_output += '<tr>';
					for (var cell = 0; cell < sheet_data[row].length; cell++) {
						var cellValue = sheet_data[row][cell];

						if (row == 0) {
							table_output += '<th>' + cellValue + '</th>';
						} else if (typeof cellValue === 'number' && !isNaN(cellValue) && cell == 1) {
							// Convert Excel date numeric value to JavaScript Date object
							var date = new Date((cellValue - (25567 + 2)) * 86400 * 1000);
							// Format the date into a human-readable string (e.g., MM/DD/YYYY)
							var formattedDate = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
							table_output += '<td>' + formattedDate + '</td>';
						} else {
							table_output += '<td>' + cellValue + '</td>';
						}
					}
					table_output += '</tr>';
				}
				table_output += '</table>';
				$('#excel_data').html(table_output);
			}
			$('#excel_file').val('');
		}
	});

});
function getRowData() {
	console.log("Submit");
	let tableRows = $('tr');
	console.log(tableRows);
	var arr = [];
	var keys = []; // Array to store table header keys
	tableRows.each(function(i, row) {
		var obj = {};
		let cells = $(row).find('td');
		console.log(cells);
		if (i == 0) {
			let tableHeader = $(row).find('th');
			tableHeader.each(function(k, th) {
				keys.push($(th).text()); // Store table header keys
			});
		} else {
			cells.each(function(j, cell) {
				obj[keys[j]] = j == 1 ? new Date($(cell).text()) : $(cell).text(); // Assign cell values to respective header keys
				console.log($(cell).text());
			});
		}
		if (i > 0) arr.push(obj);
	});
	console.log(arr);
	return arr;
}

function onSubmit() {
	let rowDatas = getRowData();
	console.log(rowDatas);
	console.log(JSON.stringify({ data: rowDatas }));

	$.ajax({
		method: "POST",
		url: "/api/prediction-report/bulk/save-prediction-date",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({ data: rowDatas }),
		success: function(data) {
					Swal.fire({
					icon: 'success',
					title: 'Sucessfully Uploaded the Dates !!'
				}).then((result) => { window.location.reload(); })
			
		}
	});


}



