$(document).ready(function(){

})

$(".like").on("change",function(){
	
	var acId=$(this).val();
	
	if($(this).is(":checked")){
		$.ajax({
			url : "/ac/insertHeart",
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
			url : "/ac/deleteHeart",
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