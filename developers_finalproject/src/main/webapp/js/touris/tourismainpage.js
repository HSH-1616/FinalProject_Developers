// /모달창 js/ 

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex));
};
$(document).ready(function () {
		  let isDataLoaded = false;
		  let clickedText = ""; // 클릭한 텍스트를 저장하는 변수
	    function openModal() {
	        $(".totalmodaldiv").show();
	        $(".mainmodaldiv").show();
	    }

	    function closeModal() {
	        $(".totalmodaldiv").hide();
	        $(".mainmodaldiv").hide();
	    }
	    

	    function loadData(areaName) {
	        // 데이터를 로드하는 함수
	        $.ajax({
	            url: path+"/touris/selecttourisarea",
	            method: "get",
	            data: {
	                'areaName': areaName
	            },
	            success: function (data) {
	                console.log(data);
	            	$(".mainmodalfleximages").empty();
	                $(".mainmodaltitle").remove();
	                $(".mainmodaltitle2").remove();
	                $(".mainmodaldetail").remove();

	                data.forEach(t => {
	                    // 이미지 주소
	                    const img = $("<img>").addClass("mainmodalimage").attr("src", t.areaImg);
	                    $(".mainmodalfleximages").append(img);
	                    // 영어제목
	                    const h2 = $("<h2>").addClass("mainmodaltitle").text(t.areaEng);
	                    h2.insertBefore(".mainmodaltextarea");
	                    // 지역명
	                    const div = $("<div>").addClass("mainmodaltitle2");
	                    const span = $("<span>").text("대한민국 " + t.areaName);
	                    div.append(span);
	                    div.insertBefore(".mainmodaltextarea");
	                    // 지역소개
	                    const p = $("<p>").addClass("mainmodaldetail").text(t.areaContent);
	                    p.insertBefore(".mainmodaltextarea");
	                
	                    /*const clickareaId = "location.href="+"'"+path+'${path}/touris/tourismap?areaId=" + t.areaId + "'";*/
	                	const clickareaId = "location.href="+"'"+path+"/touris/tourismap?areaEng=" + t.areaEng + "'";	                    
	                	$(".mainmodalbtn").attr("onclick", clickareaId);
	                });

	                openModal();
	            }
	        });
	    }
	    $(document).on("click", ".TEXT", function () {
	        console.log($(this).text());
	        const clickedAreaName = $(this).text();
	        if (!isDataLoaded) {
	            loadData(clickedAreaName);
	            clickedText = clickedAreaName;
	        } else {
	            openModal(); // 모달창만 띄우기
	        }
	    });
	    $(document).on("click", ".cardarea", function () {
	    	const cardTextKr = $(this).find(".cardtextkr").text();
	        if (!isDataLoaded) {
	            loadData(cardTextKr);
	            clickedText = cardTextKr;
	        } else {
	            openModal(); // 모달창만 띄우기
	        }
	    });
	    
	    $(".mainmodalspan").on("click", function () {
		    closeModal();
		  });
		});
		
		
		
// /모달창 js/
