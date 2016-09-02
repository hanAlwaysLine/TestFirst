$(document).ready(function(){
	getPlaceList();
	var cinemano=$("#cinemano").val() ;
	//var cinema=$("#cinema").val() ;
	//alert(cinema);
    //$("#cinema").attr("value", cinemano); //这句话的意思就是设置值等于2的option被选中
	$("#queryForm").submit();
});

function goSubmit(){
	$("#pageNo").val("1");
	$("#queryForm").submit();
}
function addfeature(){
	var url = "featuremanage/waitingTip.jsp";
	window.open ('/cinema/featuremanage/waitingTip.jsp','newwindow','height=200,width=400,top=350,left=450,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
//	var b = new parent.parent.Boxy.load(url,//
//			{
//				modal : true,// 
//				unloadOnHide : true
//			});
//	b.tween(400, 200);
	
	//var action = "/cinema/featuremanage/featuremanage!addfeature.action";
	//jQuery.post(url, null,frush);
}
//根据city查询区域内的所有影院
function getPlaceList(){
	//alert("11");
	var city=$("#cityno").val() ;
	url = "/cinema/featuremanage/featuremanage!queryPlaceList.action?jsonString="+city;
	jQuery.post(url, null,insercinema,'json');
}

//ajax往下拉菜单中添加option
function insercinema(data){
	var list=eval(data);
	var i=0;
	$("#cinema").empty();
	$("#cinema").append("<option  value='' >-影院-</option>");
	var cinemano=$('#cinemano').val() ;
	while(list[i]!=null){
		if(cinemano == list[i].placeno){
			$("#cinema").append("<option selected='selected' value='"+list[i].placeno+"'>"+list[i].placename+"</option>");   //为Select追加一个Option(下拉项)
			i=i+1;
		}else{
			$("#cinema").append("<option value='"+list[i].placeno+"'>"+list[i].placename+"</option>");   //为Select追加一个Option(下拉项)
			i=i+1;
		}
	}
}