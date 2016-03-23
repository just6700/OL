$(function() {
	$('#myModal').modal({
		backdrop : 'static',
		keyboard : false
	})
	$('#myModal').modal('show');
});

function postResponse() {
	var txt = $('#txtarea1').val();
	$('#txtarea1').val('');
	var add = '<tr><td>' + txt + '</td><tr>';
	$('#tableq1').append(add);
}

function checkLogin() {
	$.get("LoginServlet", {
		userId : $('#inputEmail').val(),
		password : $('#inputPassword').val()
	}, function(response) {
		if (response == "true") {
			$('#myModal').modal("hide");
			$('#myprofile').append($('#inputEmail').val().split('@')[0]);

			// loading main page
			$.get("MainServlet", function(json) {
				console.log(json);
				appendQuestions(json);
			});

		}
	});
}

function appendQuestions(json) {
	for (i in json) {
		var id = 'tableq' + json[i].id;
		$('#questions').append($('<table/>').attr({
			id:id,
			width:"100%"}))
				.append($('<textarea/>').attr({
					id:"txtarea" + json[i].id,
					rows:"2",
					cols:"50",
					placeholder:"Type your response here.."
				}))
				.append($('<button/>').addClass("btn btn-block btn-md btn-success btn-post").attr({
					type:"button",
					onclick:"postResponse()"
				}).append('Post')).append('<br><br>');
		$('#'+id).append($('<tr/>').append($('<td/>').append('<h2>Q. '+json[i].question+'</h2>'))
						.append($('<td/>').addClass("right").append('<span class="label label-info">' + json[i].tags + '</span>').append('<br><br>').append('<span class="label label-warning">' + 'Asked by' + '<a href="#">Sagar</a>' + '</span>')));
	}
	// console.log(json.length);
	// console.log(json[0]);
	// for (i=0;i<json.length;i++) {
	// console.log(json[i].id);
	// // var t = $('<table>').attr({id: i.id});
	// // $('#questions').append(t);
	// }
}

function enterKey(e) {
	if (e.keyCode == 13) {
		checkLogin();
	}
}

function test() {
}