function del(id){
	var msg = "删除此影片会删除对应的首页轮播图,请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){			
		var url = "/cinema/filmmanage/filmmanage!delfilm.action";		           
		params = {
				id:id
		}
		jQuery.post(url,params,callBackData,'json');
});
}

function callBackData(data){
	var flag = eval(data.flag);
	if(flag){
		alertResult("删除成功!",null);
	}else{
		alertResult("删除失败!",null);
	}
}

//位置
function position(id,position){
	var url = "/cinema/filmmanage/filmmanagePosition.jsp?id="+id+"&position="+position;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>位置-设置</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(350,100);
}
//发布
function goRelease(id){
	var msg = "您确认要发布?请确认!";
	parent.parent.Boxy.confirm(""+msg+"",function(){
		var url = "/cinema/filmmanage/filmmanage!gorelease.action";
		params = {
				id:id
		}
		jQuery.post(url,params,callBackUpdate,'json');
	});
}

function callBackUpdate(data){
	var flags = eval(data.film_d);
	if(flags){
		alertResult("发布成功!",null);
	}else{
		alertResult("发布失败!",null);
	}
}
//修改
function goEdit(id){

	var url = "/cinema/filmmanage/filmmanage!editfilm.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影片-修改</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(750, 457);
}
//详情
function goFimlDetails(id){
	//var url = "/cinema/filmmanage/filmmanage!details.action?id="+id;
	/*
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影片-详情</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(700, 500);
	*/
	
	window.open ('/cinema/filmmanage/filmmanage!details.action?id='+id,'newwindow','height=600,width=500,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')

}

//察看排期
function queryFeatures(filmno){
	var pageNo=1;
	var pageSize=10;
	var moudleID = "menu_31";
	var url = "/cinema/featuremanage/featuremanage!query.action?pageNo="+pageNo+"&pageSize="+pageSize+"&filmno="+filmno;
	parent.parent.zmueu(parent.parent.$("#Menu_30"));
	parent.parent.gotoul(moudleID,url);
	
}

//剧照
function goFilmStills(id,filmname){
	var url = "/cinema/filmmanage/filmmanage!stillsfimewarn.action?id="+id+"&filmnameStr="+filmname;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影片-剧照</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(680, 200);
}
//片花
function goFimeWarn(id,filmname){
	var url = "/cinema/filmmanage/filmmanage!editfimewarn.action?id="+id;
	var b = new parent.parent.Boxy.load(url,// 地址 可以带参数
			{
				title : "<div style='padding:10px;'>影片-片花</div>", // 标题
				modal : true,// 是否开遮罩
				unloadOnHide : true
			});
	b.tween(680, 200);
}
