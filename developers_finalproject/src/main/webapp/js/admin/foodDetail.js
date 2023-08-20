
function getContextPath() {
   var hostIndex = location.href.indexOf(location.host) + location.host.length;
   return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
};

function YNCheck(event){
    let result = event.target.value;
    if(event.target.checked)  {
       result = 1;
    }else{
       result = 0;
    }
    $("#flexSwitchCheckChecked").val(result);
    console.log($("#flexSwitchCheckChecked").val());
 };

function fn_updateCheck(event){
    console.log($("#flexSwitchCheckChecked").val());
   const result = confirm("맛집 수정을 완료 하시겠습니까?");
   if(result == true){
      $(event.target).attr("type","submit");
      const form=new FormData();
        const fileInput=$("#upFile");

        $.each(fileInput[0].files,(i,f)=>{
            form.append("upFile",f);
            console.log(form);
        });
        form.append("foodNo",'${f.foodNo}');
        form.append("frGrade",$(".starcountnum").val()/2);
        form.append("frContent",$("#FR_CONTENT").val());
        form.append("frNo",$("#selected_food_no").val());
        form.append("allow",$("#flexSwitchCheckChecked").val());

        $.ajax({
            url:getContextPath()+"/updateFoodReview.do",
            data:form,
            type:"post",
            enctype: "multipart/form-data",
            processData:false,
            contentType:false,
            cache: false,
            success:data=>{
            alert("수정이 완료되었습니다.");
            location.reload();
            $('window').scrollTop(0);
            },
            error:(r,s,e)=>{
            console.log("수정 실패 "+r.s+"\n"+"msg "+r.responseText+"\n"+"error "+e);
            alert("수정 실패");
            },
            complete:()=>{
            $(".upFile").val('');
            }
        });
   }
};
