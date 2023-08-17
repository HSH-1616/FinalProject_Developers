$(document).ready(function(){

})

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};


$(".like").on("change",function(){
	
	var acId=$(this).val();
	
	if($(this).is(":checked")){
		$.ajax({
			url : getContextPath()+"/ac/insertHeart",
			data :{
				memberId : memberId,
				acId : acId
			},
			success:function(result){
				if(result>0){
					
				}
			}
		})
	}else{
		$.ajax({
			url : getContextPath()+"/ac/deleteHeart",
			data :{
				memberId : memberId,
				acId : acId
			},
			success:function(result){
				if(result>0){
					
				}
			}
		})
	}
	
})