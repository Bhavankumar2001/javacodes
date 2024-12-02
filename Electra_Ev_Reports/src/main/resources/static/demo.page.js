$(document).ready(function() {

	$(".generate-excel").click(function() {
		excel = new ExcelGen({
			"src_id": "test_table",
			"show_header": true


		});
		
		excel.generate();
	});


	/*$(".generate-excel").click(function() {

	});*/
});