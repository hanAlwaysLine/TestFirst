$(document).ready(function(){
var msg=$("#msg").val();
if(msg!=""&&msg!="null")
{
    alertResult(msg, 0);
}
}
);



function doPos(){
	document.forms[0].submit();
}