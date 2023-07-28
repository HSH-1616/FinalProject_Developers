// 헤더
$(window).ready(function () {
  $(".btn").css("background-color", "transparent");
});

$(".main-menu").mouseover(function () {
  $(".navbar").addClass("drop");
  $("#blurSection").css("display", "block");
});

$(".main-menu").mouseout(function () {
  $("#blurSection").css("display", "none");
});
// /헤더/
// /모달창 js/ 
$(document).ready(function () {
  function openModal() {
    $(".totalmodaldiv").show();
    $(".mainmodaldiv").show();
  }
  function closeModal() {
    $(".totalmodaldiv").hide();
    $(".mainmodaldiv").hide();
  }
  $(".TEXT").on("click", function () {
    openModal();
  });
  $(".mainmodalspan").on("click", function () {
    closeModal();
  });
});

//-------------------------------------------------------------------------- 첫번재 지도 api
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 
function relayout() {    
    
  // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
  // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
  // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
  map.relayout();
}

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도 타입 컨트롤을 지도에 표시합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);



// 마커가 표시될 위치입니다 
 var markerPosition  = new kakao.maps.LatLng(); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
// 마커가 드래그 가능하도록 설정합니다 
marker.setDraggable(true); 

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
  // 클릭한 위도, 경도 정보를 가져옵니다 
  var latlng = mouseEvent.latLng; 
  
  // 마커 위치를 클릭한 위치로 옮깁니다
  marker.setPosition(latlng);

});
// 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
var iwContent = '<div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    content : iwContent
});

// 마커에 마우스오버 이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'mouseover', function() {
  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
});

// 마커에 마우스아웃 이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'mouseout', function() {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
});
//-------------------------------------------------------------------------- 여기까지 첫번재 지도 api

//모달창 지도 api

var mapContainer2 = document.getElementById('routemap'), // 지도를 표시할 div 
    mapOption2 = { 
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 10 // 지도의 확대 레벨
    };
    

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map2 = new kakao.maps.Map(mapContainer2, mapOption2); 
function relayout() {    
    
  // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
  // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
  // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
  
  
}

// function relayout() {    
    
//   // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
//   // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
//   // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
  
//   map2.relayout();
// }

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl2 = new kakao.maps.MapTypeControl();

// 지도 타입 컨트롤을 지도에 표시합니다
map2.addControl(mapTypeControl2, kakao.maps.ControlPosition.TOPRIGHT);



// 마커가 표시될 위치입니다 
 var markerPosition2  = new kakao.maps.LatLng(); 

// 마커를 생성합니다
var marker2 = new kakao.maps.Marker({
    position: markerPosition2
});

// 마커가 지도 위에 표시되도록 설정합니다
marker2.setMap(map2);
// 마커가 드래그 가능하도록 설정합니다 
marker2.setDraggable(true); 

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map2, 'click', function(mouseEvent) {        
    
  // 클릭한 위도, 경도 정보를 가져옵니다 
  var latlng = mouseEvent.latLng; 
  
  // 마커 위치를 클릭한 위치로 옮깁니다
  marker2.setPosition(latlng);

});
// 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
var iwContent2 = '<div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

// 인포윈도우를 생성합니다
var infowindow2 = new kakao.maps.InfoWindow({
    content : iwContent
});

// 마커에 마우스오버 이벤트를 등록합니다
kakao.maps.event.addListener(marker2, 'mouseover', function() {
  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map2, marker2);
});

// 마커에 마우스아웃 이벤트를 등록합니다
kakao.maps.event.addListener(marker2, 'mouseout', function() {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
});

// 지도 fullscreen하는 함수
// * 전체 화면으로 표시
// * elem: 전체 화면으로 표시할 엘리먼트
// * https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_fullscreen
// */
var elem = document.getElementById('routemap');
function openFullScreen() {
	if(!elem) alert("specify element for full screen.")
	if (elem.requestFullscreen) {
		elem.requestFullscreen();
	} else if (elem.mozRequestFullScreen) { /* Firefox */
		elem.mozRequestFullScreen();
	} else if (elem.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
		elem.webkitRequestFullscreen();
	} else if (elem.msRequestFullscreen) { /* IE/Edge */
		elem.msRequestFullscreen();
	}
}; 
// 현재 지도를 Full Screen에서 표시합니다.
$(".fullscreen>a").click(function(){
	var mapContainer = document.getElementById('routemap');
    // 사이즈를 정해 줘야 지도의 센터를 설정할 수 있습니다.
	mapContainer.style.width = screen.width + 'px';
	mapContainer.style.height = screen.height + 'px';

	openFullScreen('routemap');
	// 이동할 위도 경도 위치를 생성합니다 
	var moveLatLon = new kakao.maps.LatLng(33.450701, 126.570667);
	// 지도 레벨 설정( 1~14 낮을 수록 확대)
	map2.setLevel(10); // 현재 맵 보다 확대해서 표시	
	map2.relayout();  // 지도의 레이 아웃을 재 정렬합니다.
	map2.setCenter(moveLatLon); // 지도 중심을 이동 시킵니다
});
// FullScreen에서 ESC할 경우 작동함
document.addEventListener('fullscreenchange', exitHandler);
document.addEventListener('webkitfullscreenchange', exitHandler);
document.addEventListener('mozfullscreenchange', exitHandler);
document.addEventListener('MSFullscreenChange', exitHandler);

function exitHandler() {
	if (!document.fullscreenElement && !document.webkitIsFullScreen && !document.mozFullScreen && !document.msFullscreenElement) {
		// 현재 맵
		var mapContainer = document.getElementById('routemap');
		mapContainer.style.width = '100%'; // 원래 사이즈로 복귀
		mapContainer.style.height = '91.5vh';
		// 이동할 위도 경도 위치를 생성합니다 
		var moveLatLon = new kakao.maps.LatLng(33.450701, 126.570667);
		// 지도 레벨 설정( 1~14 낮을 수록 확대)
		map2.setLevel(10);
		map2.relayout(); // 지도의 레이아웃을 재 정렬합니다.
		map2.setCenter(moveLatLon); // 지도 중심을 이동 시킵니다
	}
}
// 모달창 지도 api

// 달력 js
$(function() {

  $('input[name="datefilter"]').daterangepicker({
      autoUpdateInput: false,
      locale: {
          cancelLabel: 'Clear'
      }
  });

  $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
  });

  $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
  });

});
$(document).ready(function(){
  $('input[name="datefilter"]').trigger('click');
 console.log($('input[name="datefilter"]').trigger('click'));
});
 
  // 탭 js
  
    //label 선택 js
    $(function() {

      $('.search-class1').click(function(){
        if( $(this).hasClass('btn-food') ) $(this).removeClass('btn-food');
        if( !$(this).hasClass('btn-search') ) $(this).addClass('btn-search');
        if( $('.food-class1').hasClass('btn-search') ) $('.food-class1').removeClass('btn-search');
        if( !$('.food-class1').hasClass('btn-food') ) $('.food-class1').addClass('btn-food');
      });
      
      $('.food-class1').click(function(){
        if( $(this).hasClass('btn-food') ) $(this).removeClass('btn-food');
        if( !$(this).hasClass('btn-search') ) $(this).addClass('btn-search');
        if( $('.search-class1').hasClass('btn-search') ) $('.search-class1').removeClass('btn-search');
        if( !$('.search-class1').hasClass('btn-food') ) $('.search-class1').addClass('btn-food');
      });
      
      });
      // 태그 생성 태그
        $("ul.listarea-right").on("click", ".plus-travel a", function () {
          var parentLi = $(this).closest("li.selectlist2");
          
          var newLi = `<li class="selectlist">
          <div class="listbox">
            <div class="listboximg"><img src="	https://www.myro.co.kr/getSpotImage/seoul?no=3449" alt=""></div>
            <div class="listicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z" />
            </svg>
            </div>
          </div>
          <div class="listtext">
            <h7 class="listtitle-text">카카오프렌즈 강남플래그십스토어(Kakao Store)</h7><br>
          </div>
          <div class="listbtn">
            <div class="detail-travellist"><a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="#e0e0e0">
              <path fill-rule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12zm11.378-3.917c-.89-.777-2.366-.777-3.255 0a.75.75 0 01-.988-1.129c1.454-1.272 3.776-1.272 5.23 0 1.513 1.324 1.513 3.518 0 4.842a3.75 3.75 0 01-.837.552c-.676.328-1.028.774-1.028 1.152v.75a.75.75 0 01-1.5 0v-.75c0-1.279 1.06-2.107 1.875-2.502.182-.088.351-.199.503-.331.83-.727.83-1.857 0-2.584zM12 18a.75.75 0 100-1.5.75.75 0 000 1.5z" clip-rule="evenodd" />
            </svg>
            </a>
            </div>
            <div class="delete-travel"><a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="#b31312">
              <path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 011.06 0L12 10.94l5.47-5.47a.75.75 0 111.06 1.06L13.06 12l5.47 5.47a.75.75 0 11-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 01-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 010-1.06z" clip-rule="evenodd" />
            </svg></a>
            </div>
          </div>
        </li>`;
          parentLi.remove();
         // 생성된거 없어지고 생겨지는 함수
          $("ul.listarea").append(newLi);
          var selectListElements = $("ul.listarea li.selectlist");
    
          console.log("카운터갯수:", selectListElements.length);
          if (selectListElements.length) {
            $(".list-text").hide();
          } else {
            $(".list-text").show();
          }
        });
     
      //태그 삭제 태그
      $("ul.listarea").on("click", ".delete-travel a", function () {
          var parentLi = $(this).closest("li.selectlist");
          
          var newLi = `<li class="selectlist2">
          <div class="listbox">
            <div class="listboximg"><img src="	https://www.myro.co.kr/getSpotImage/seoul?no=3449" alt=""></div>
            <div class="listicon"><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6" width="10" color="white">
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z" />
            </svg>
            </div>
          </div>
          <div class="listtext">
            <h7 class="listtitle-text">카카오프렌즈 강남플래그십스토어(Kakao Store)</h7><br>
          </div>
          <div class="listbtn">
            <div class="detail-travellist"><a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="#e0e0e0">
              <path fill-rule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12zm11.378-3.917c-.89-.777-2.366-.777-3.255 0a.75.75 0 01-.988-1.129c1.454-1.272 3.776-1.272 5.23 0 1.513 1.324 1.513 3.518 0 4.842a3.75 3.75 0 01-.837.552c-.676.328-1.028.774-1.028 1.152v.75a.75.75 0 01-1.5 0v-.75c0-1.279 1.06-2.107 1.875-2.502.182-.088.351-.199.503-.331.83-.727.83-1.857 0-2.584zM12 18a.75.75 0 100-1.5.75.75 0 000 1.5z" clip-rule="evenodd" />
            </svg>
            </a>
            </div>
            <div class="plus-travel"><a><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6" width="20" height="20" color="black">
              <path fill-rule="evenodd" d="M12 3.75a.75.75 0 01.75.75v6.75h6.75a.75.75 0 010 1.5h-6.75v6.75a.75.75 0 01-1.5 0v-6.75H4.5a.75.75 0 010-1.5h6.75V4.5a.75.75 0 01.75-.75z" clip-rule="evenodd" />
            </svg>              
            </a>
            </div>
          </div>
        </li>`;
          parentLi.remove();
          // 생성된거 없어지고 생겨지는 함수
          var selectListElements = $("ul.listarea li.selectlist");
    
          console.log("카운터갯수:", selectListElements.length);
          if (selectListElements.length) {
            $(".list-text").hide();
          } else {
            $(".list-text").show();
          }
          $("ul.listarea-right").append(newLi);
        });
        
    

   
    //모달창 띄어주기
      $(document).on("click", ".detail-travellist a", function () {
        $(".totalmodaldiv").show();
        $(".modaldiv").show();
      });
      $(document).on("click", ".modaldiv .modalspan", function () {
        $(".totalmodaldiv").hide();
        $(".modaldiv").hide();
      });
   
    // 맵에 있는 토글 이벤트
        $(".toggle-header").click(function() {
          $(this).next(".toggle-body").slideToggle();
        });
        
    // 경로생성 모달창 js
  $("#routecreatebtn").on("click", function () {
    // openModal();
    $(".routetotalmodal").show();
    $(".routemainmodaldiv").show();
    map2.relayout();
  });
  $(".routespan").on("click", function () {
    $(".routetotalmodal").hide();
    $(".routemainmodaldiv").hide();
  });

// 경로모달창에서 화면 확대되게 만들기
$(document).on("click", ".sidebarexpansion", function() {
  
  var alldaydetailschedule = $(".alldaydetailschedule");
  var userguidemenu = $(".userguidemenuarea");
  
  if (alldaydetailschedule.css("display") === "block") {
    alldaydetailschedule.css("display", "flex"); // 현재 display가 block이면 flex로 변경
    userguidemenu.css("right", "67px");
    userguidemenu.css("left", "auto");
    userguidemenu.css("top", "67px");
  } else {
    alldaydetailschedule.css("display", "block"); // 현재 display가 flex이면 block으로 변경
    userguidemenu.css("right", "auto");
    userguidemenu.css("left", "360px");
    userguidemenu.css("top", "40px");
  }
});
// 경로 모달창 검색화면 show, hiden js 
$(document).on("click", ".routesearchmodalbtn", function(){
  var routesearcharea = $(".routesearchmodalarea");

  if(routesearcharea.css("display") ==="none"){
    routesearcharea.css("display", "block");
  }
});
$(document).on("click", ".routesearchareadeletestyle", function(){
  var routesearcharea = $(".routesearchmodalarea");

  if(routesearcharea.css("display") ==="block"){
    routesearcharea.css("display", "none");
  }
});
// 플러스 버튼 누르면 효과나타나기 
$(document).on("click", ".list-texticon", function(){
  var heartbeat = $("#search-traveltitle");
  heartbeat.addClass("heartbeat");
  setTimeout(function() {
    heartbeat.removeClass("heartbeat");
  }, 1500);
});

// 장소등록 모달창 띄어주기 js #registspot

$(document).on("click", "#registspot", function () {
  $(".regspot").show();
  $(".smallmodal").show();
});
$(document).on("click", ".header-delete", function () {
  $(".regspot").hide();
  $(".smallmodal").hide();
});
// 장소등록 검색리스트 모달창 띄어주기 js .footera ".black-delteicon",
$(document).on("click", ".footera", function () {
  $(".reqsearchlist").show();
  $(".reqsearchlistmodalarea").show();
});
$(document).on("click", ".reqsearchdeltebtn", function () {
  $(".reqsearchlist").hide();
  $(".reqsearchlistmodalarea").hide();
});
$(document).on("click", ".black-delteicon", function () {
  $(".reqsearchlist").hide();
  $(".reqsearchlistmodalarea").hide();
});

// 경로 모달창 에서 일정별로 선택하는 js smallschedullisttextarea, totalscheduletext
$(document).on("click", ".smallschedullisttextarea", function(){
  var dailyroutearea = $(".dailyroutearea");
  var alldaydetailschedule = $(".alldaydetailschedule");
  var sidebarexpansionsvg = $(".sidebarexpansion svg");
  var dailyrightarea = $(".dailyrightarea");
  var routesearcharea = $(".routesearcharea");

  dailyroutearea.css("display", "block");
  alldaydetailschedule.css("display", "none");
  sidebarexpansionsvg.css("display", "none");
  dailyrightarea.css("display", "block");
  dailyrightarea.addClass("blink-1");
  routesearcharea.css("display", "none");
  
});
// 전체일정 버튼 js
$(document).on("click", ".totalscheduletext", function(){
  var alldaydetailschedule = $(".alldaydetailschedule");
  var dailyroutearea = $(".dailyroutearea");
  var sidebarexpansionsvg = $('.sidebarexpansion svg');
  var dailyrightarea = $(".dailyrightarea");
  var routesearcharea = $(".routesearcharea");

  alldaydetailschedule.css("display","block");
  dailyroutearea.css("display", "none");
  sidebarexpansionsvg.css("display", "block");
  dailyrightarea.css("display", "none");
  routesearcharea.css("display", "block");
});










