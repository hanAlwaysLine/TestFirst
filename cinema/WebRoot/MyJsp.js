$(document).ready(function(){
//	alert(1);
});

function dayin(){
	var a = window.CallCSharpMethod("TicketPrint","电影名");
	alert(a);
}