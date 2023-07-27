$(document).ready(function () {
  calender();
});

// 검색바
$(document).on("click", "#searchMini span", function (e) {
  $(".searchLabel").removeClass("active");
  $("#searchContainer").css("display", "flex");
  $("#blurSearchBar").css("display", "block");
  $(".main-menu").css("display", "none");

  var order = $(this).index() / 2;
  var id = $(this).attr("id");
  $(".search" + id + ".detail").css({
    display: "flex",
    justifyContent: "end",
  });
  $(".searchLabel").eq(order).addClass("active");
});

$(document).on("click", ".searchLabel", function (e) {
  $(".searchLabel").removeClass("active");
  $(this).addClass("active");
  if ($(this).hasClass("active")) {
    var id = $(this).children().attr("id");
    $(".detail").css("display", "none");
    $("." + id + ".detail").css({
      display: "flex",
      justifyContent: "end",
    });
  }
});

$(document).on("click", "#blurSearchBar", function (e) {
  $(".detail").css("display", "none");
  $("#searchContainer").css("display", "none");
  $("#filterDetail").removeClass("on");
  $("body").css("overflow", "scroll");
  $(this).css("display", "none");
  $(".main-menu").css("display", "flex");
  $(".detailHotelImage").fadeOut(500, "linear");
  $(".overImgCon").hide();
});
// /검색바/

// 지역함수
$(document).on("mouseover", ".searchMap path", function (e) {
  //   $(".searchMap path").css("fill", "#afafaf");
  //   $(this).css({fill: "#b31312", cursor: "pointer"});
});

$(document).on("mouseout", ".searchMap path", function (e) {
  //   $(".searchMap path").css("fill", "#afafaf");
});

$(document).on("click", ".searchMap path", function (e) {
  $(".searchMap path").css("fill", "#afafaf");
  $(this).css("fill", "#b31312");
  const text = $(this).attr("id") + "Text";
  $("#searchLocInput").val(
    $("#" + text)
      .html()
      .trim()
  );
});
$(document).on("click", ".TEXT", function (e) {
  $(".searchMap path").css("fill", "#afafaf");

  var text = $(this).attr("id");
  var textLeng = text.length;
  var textSub = text.substr(0, textLeng - 4);
  console.log(textSub);

  $("#" + textSub).css("fill", "#b31312");

  $("#searchLocInput").val($(this).html().trim());
});

// /지역함수/

//  달력함수
function calender() {
  //date 객체는 로컬 시간대를 이용해 날짜를 출력
  //어디서든 동일하게 출력하기 위해 utc와로컬 시간의 차이를 계산해서 출력해야됨

  //현재 날짜
  var date = new Date();
  // uct 표준시
  var utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000;
  //한국 기준시간
  var korTime = 9 * 60 * 60 * 1000;
  //한국 시간 date
  var today = new Date(utc + korTime);

  //달력 표기 날짜객체
  var thisMonth = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate()
  );

  var nextMonth = new Date(
    today.getFullYear(),
    today.getMonth() + 1,
    today.getDate()
  );

  // 달력에서 표기하는 년,월,일
  var currentYear = thisMonth.getFullYear();
  var currentMonth = thisMonth.getMonth();
  var currentDate = thisMonth.getDate();

  //달력 렌더링
  renderCalender(thisMonth);
  //renderNextCalender(nextMonth);

  function renderCalender(thisMonth) {
    //새로 들어오는 변수를 위한 데이터 정의
    currentYear = thisMonth.getFullYear();
    currentMonth = thisMonth.getMonth();
    currentDate = thisMonth.getDate();

    nextMonth = thisMonth.getMonth() + 1;

    // 이전 달의 마지막 날 날짜와 요일
    var startDay = new Date(currentYear, currentMonth, 0);
    var prevDate = startDay.getDate();
    var prevDay = startDay.getDay();

    var startNextDay = new Date(currentYear, nextMonth, 0);
    var prevNextDate = startNextDay.getDate();
    var prevNextDay = startNextDay.getDay();

    // 이번 달의 마지막날 날짜와 요일
    var endDay = new Date(currentYear, currentMonth + 1, 0);
    var nextDate = endDay.getDate();
    var nextDay = endDay.getDay();

    var endNextDay = new Date(currentYear, nextMonth + 1, 0);
    var nextNextDate = endNextDay.getDate();
    var nextNextDay = endNextDay.getDay();

    //현재월 출력
    $(".calMonth").text(currentYear + "년 " + (currentMonth + 1) + "월");
    //다음월 출력
    if (nextMonth >= 12) {
      $(".calNextMonth").text(currentYear + 1 + "년 1월");
    } else {
      $(".calNextMonth").text(currentYear + "년 " + (nextMonth + 1) + "월");
    }

    //출력할 요소 생성
    calendar = document.querySelector(".calDates");
    calendar.innerHTML = "";

    calendarNext = document.querySelector(".calDates.next");
    calendarNext.innerHTML = "";

    // 지난달
    for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
      calendar.innerHTML =
        calendar.innerHTML + '<div class="notday">' + "" + "</div>";
    }

    // 이번달
    for (var i = 1; i <= nextDate; i++) {
      //오늘날짜보다 작으면 disable추가
      if (i < currentDate && currentMonth + 1 == today.getMonth() + 1) {
        calendar.innerHTML =
          calendar.innerHTML + '<div class="notday current">' + i + "</div>";
      } else {
        calendar.innerHTML =
          calendar.innerHTML + '<div class="day current">' + i + "</div>";
      }
    }
    // 다음달
    for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
      calendar.innerHTML =
        calendar.innerHTML + '<div class="notday">' + "" + "</div>";
    }
    //오른쪽 달력
    for (var i = prevNextDate - prevNextDay + 1; i <= prevNextDate; i++) {
      calendarNext.innerHTML =
        calendarNext.innerHTML + '<div class="notday">' + "" + "</div>";
    }
    for (var i = 1; i <= nextNextDate; i++) {
      calendarNext.innerHTML =
        calendarNext.innerHTML + '<div class="day current">' + i + "</div>";
    }
    for (var i = 1; i <= (7 - nextNextDay == 7 ? 0 : 7 - nextNextDay); i++) {
      calendarNext.innerHTML =
        calendarNext.innerHTML + '<div class="notday">' + "" + "</div>";
    }
  }

  // 이전달로 이동
  $(".calBtn.prev").on("click", function () {
    thisMonth = new Date(currentYear, currentMonth - 1, currentDate);
    renderCalender(thisMonth);
    prevBtn();
  });

  // 다음달로 이동
  $(".calBtn.next").on("click", function () {
    thisMonth = new Date(currentYear, currentMonth + 1, currentDate);
    renderCalender(thisMonth);
    prevBtn();
  });

  //이번달에는 이전달 버튼 비활성화
  function prevBtn() {
    if (
      currentMonth + 1 == today.getMonth() + 1 &&
      currentYear == today.getFullYear()
    ) {
      $(".calBtn.prev ion-icon").css("display", "none");
    } else {
      $(".calBtn.prev ion-icon").css("display", "flex");
    }
  }
}

//달력 기간 구하기
$(document).on("click", ".day", function () {
  //선택한게 없으면 first class부여
  //있으면 두번째 선택자 last class 부여
  if ($(".calDates").find(".first").length) {
    //선택한 날짜가 first보다 크면 last부여
    if ($(this).text() > $(".first").text()) {
      //last가 있으면 삭제하고 새로부여 없으면 last부여
      //first가 다음달일 경우
      if (
        $(".calDates").find(".last").length &&
        $(".first").parent().attr("class") != "calDates next"
      ) {
        $(".day").removeClass("last");
        $(this).addClass("last");
      } else if ($(".first").parent().attr("class") == "calDates next") {
        if (
          $(this).parent().attr("class") == "calDates next" &&
          Number($(this).html()) > Number($(".first").html())
        ) {
          $(".day").removeClass("last");
          $(this).addClass("last");
        } else {
          $(".day").removeClass("first");
          $(this).addClass("first");
        }
      } else {
        $(this).addClass("last");
      }
    }
    //선택한 날짜가 first보다 작으면 기존first 지우고 새로 부여
    else {
      //선택한 날짜가 first보다 작지만 다음달일 경우 last부여
      if (
        $(this).parent().attr("class") == "calDates next" &&
        $(".first").parent().attr("class") == "calDates now"
      ) {
        $(".day").removeClass("last");
        $(this).addClass("last");
        if ($(".first").parent().attr("class") == "calDates next") {
          if (
            $(this).parent().attr("class") == "calDates next" &&
            Number($(this).html()) > Number($(".first").html())
          ) {
            $(".day").removeClass("last");
            $(this).addClass("last");
          } else {
            $(".day").removeClass("first");
            $(this).addClass("first");
          }
        }
      } else {
        if (Number($(this).html()) > Number($(".first").html())) {
          if (
            $(this).parent().attr("class") == "calDates now" &&
            $(".last").parent().attr("class") == "calDates next"
          ) {
            $(".day").removeClass("first");
            $(this).addClass("first");
          } else {
            $(".day").removeClass("last");
            $(this).addClass("last");
          }
        } else if (Number($(this).html()) < Number($(".first").html())) {
          $(".day").removeClass("first");
          $(this).addClass("first");
        }
        if (
          $(this).parent().attr("class") == "calDates now" &&
          $(".first").parent().attr("class") == "calDates next"
        ) {
          $(".day").removeClass("first");
          $(this).addClass("first");
        }
      }
    }
  } else {
    $(this).addClass("first");
  }

  //오른쪽 달력일 경우 다음달 날짜 출력
  if ($(this).parent().attr("class") == "calDates next") {
    //선택한 날짜가 첫번째일 경우 체크인 날짜 출력
    if ($(this).attr("class") == "day current first") {
      $("#checkInDay").text(
        $(".calNextMonth").text().split(" ").reverse()[0] +
          " " +
          $(".first").text() +
          "일"
      );
    }
    //선택한 날짜가 마지막일 경우 체크아웃 날짜 출력
    else {
      $("#checkOutDay").text(
        $(".calNextMonth").text().split(" ").reverse()[0] +
          " " +
          $(".last").text() +
          "일"
      );
    }
  }
  //왼쪽 달력일 경우 현재달 날짜 출력
  else {
    if ($(this).attr("class") == "day current first") {
      $("#checkInDay").text(
        $(".calMonth").text().split(" ").reverse()[0] +
          " " +
          $(".first").text() +
          "일"
      );
    } else {
      $("#checkOutDay").text(
        $(".calMonth").text().split(" ").reverse()[0] +
          " " +
          $(".last").text() +
          "일"
      );
    }
  }
});
// /달력함수/

// 인원수 함수
function countFn(type) {
  var count = $("#peopleCount").val();

  if (type == "plus") {
    if (count < 10) {
      $("#countInfo").text("");
      $("#peopleCount").val(++count);
    } else {
      $("#countInfo").text("최소 1명부터 10명까지 선택가능 합니다.");
    }
  } else {
    if (count > 1) {
      $("#countInfo").text("");
      $("#peopleCount").val(--count);
    } else {
      $("#countInfo").text("최소 1명부터 10명까지 선택가능 합니다.");
    }
  }
  if (count > 1) {
    $("#peopleBtn button").first().css("color", "#b31312");
  } else {
    $("#peopleBtn button").first().css("color", "#afafaf");
  }
}
// /인원수 함수/