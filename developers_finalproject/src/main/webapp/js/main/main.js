AOS.init();

$(window).scroll(function () {
  var scroll = $(window).scrollTop();
  var height = $(".navbar").height();
  if (scroll > height) {
    $(".navbar").addClass("drop");
  } else {
    $(".navbar").removeClass("drop");
  }
});

$(".main-menu").mouseover(function () {
  $(".navbar").addClass("drop");
  $("#blurSection").css("display", "block");
});

$(".main-menu").mouseout(function () {
  var scroll = $(window).scrollTop();
  var height = $(".navbar").height();
  $("#blurSection").css("display", "none");
  if (scroll < height) {
    $(".navbar").removeClass("drop");
  }
});

$(document).on("mouseover", ".travelBtn", function () {
  $(this).parent().children(".blurImage").fadeOut(300, "linear");
});

$(document).on("mouseout", ".travelBtn", function () {
  console.log(1);
  $(this).parent().children(".blurImage").fadeIn(300, "linear");
});
