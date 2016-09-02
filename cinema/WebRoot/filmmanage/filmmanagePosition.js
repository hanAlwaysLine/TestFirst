
function position(){
	
	var position=document.getElementById("pos").value;
	var msg = "您确认要移动？请确认！";
	var id=document.getElementById("ids").value;
	document.forms[0].action= "/cinema/filmmanage/filmmanage!goPosition.action?id="+id+"&position="+position;
	document.forms[0].submit();
}

function cancleDiv() {
	var str = parent.$(".boxy-modal-blackout").length;
	if(str!=1){
	// 父页面DIV层移除
	parent.$(".boxy-modal-blackout").eq(str-1).remove();
	// 父页面DIV遮罩移除
	parent.$(".boxy-wrapper").eq(str-1).remove();
	}else{
		// 父页面DIV层移除
		parent.$(".boxy-modal-blackout").eq(0).remove();
		// 父页面DIV遮罩移除
		parent.$(".boxy-wrapper").eq(0).remove();
	}
}