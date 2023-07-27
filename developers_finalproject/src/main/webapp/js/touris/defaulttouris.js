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
