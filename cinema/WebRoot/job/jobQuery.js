$(document).ready(function(){
	$("#queryForm").submit();
});

function dosub(){
	 var startDate=$("#startDate").val();
	 var endDate=$("#endDate").val();
	 if(startDate!=""&&endDate!=""){
	 if(endDate<startDate){
		 alert("结束时间不能小于开始时间");
		 return;
	 }
	 }
	 $("#pageNo").val("1");
	$("#queryForm").submit();
}


//根据city查询区域内的所有影院
function selcinema(){
	var city=$("select option:selected").val() ;
	url = "/cinema/activity!cinemaList.action?jsonString="+city;
	jQuery.post(url, null,insercinema,'json');
}

//ajax往下拉菜单中添加option
function insercinema(data){
	$("#cinema").empty();
	$("#cinema").prepend("<option value=''>请选择</option>");   //为Select插入一个Option(第一个位置)
	var list=eval(data);
	var i=0;
	while(list[i]!=null){
		$("#cinema").append("<option value='"+list[i].id+"'>"+list[i].placename+"</option>");   //为Select追加一个Option(下拉项)
		i=i+1;
	}
}	

function goAdd(){
	var url = "/cinema/activitymanage/activity!addactivity.action";
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>活动-新增</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(750, 600);
}


