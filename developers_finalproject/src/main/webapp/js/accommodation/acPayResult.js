$(document).ready(function (e) {
  var count = 9;
  const timer = setInterval(function () {
    $("#timer span").html(count--);
    if (count == 0) {
      clearInterval(timer);
    }
  }, 1000);
});
