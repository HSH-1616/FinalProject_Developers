
function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function pageDetail(tourisId){
	location.assign(getContextPath()+'/tourisDetail/selectById?tourisId='+tourisId);
}
$(()=>{
   	$("#searchType").change(e=>{
   		const type=$(e.target).val();
		$(e.target).parent().find("div").css("display","none");
			$("#search-"+type).css("display","inline-block");
   	});
   	$("#searchType").change();

})

// ======================= 지환 ====================
// function fn_foodInfo(foodNo,allow){
// 	location.assign(getContextPath()+'/selectFoodByFoodNo?foodNo='+foodNo+'&allow='+allow);
// }
// function fn_deleteFood(foodNo){
// 	const result = confirm("정말 삭제하시겠습니까?");
// 	if(result == true){
// 		location.assign(getContextPath()+'/deleteFoodByFoodNo?foodNo='+foodNo);
// 	}
// }

