
	function validateEmail() {

		var data = {
			email : $('#userEmail').val(),
//			firstName :$('#firstName').val(),
//			lastName :$('#lastName').val(),
//			age : $('#age').val(),
//			userName : $('#userName').val(),
//			passWord : $('#passWord').val(),
//			phoneNo : $('#phoneNo').val()
		};

		$.ajax({
			url : 'register',
			type : 'GET',
			data : data,
			contentType : 'application/json',
			success : function(result) {
				$('#validate').html(result);
			},
		});
	};
	function validateUserName(){
		var data = {
				userName : $('#userName').val(),
		}
		$.ajax({
			url : 'register',
			type : 'GET',
			data : data,
			contentType : 'application/json',
			success : function(result) {
				$('#validateUser').html(result);
			},
		});
	};
function sendRequest(url, data, method, callback) {
	$.ajax({
		url : url,
		type : method,
		data : data,
		contentType : 'application/json',
		success : callback,
		error : function(request, msg, error) {
			// handle failure
		}
	});
};