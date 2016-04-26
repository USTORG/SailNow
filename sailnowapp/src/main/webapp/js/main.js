$(document).ready( function() {
  $('.cookies-used').fadeIn();
  setEvents();
});

setEvents = function() {
  $('.black-complete-background').click( function() {
    $('.password-reset-div').fadeOut();
  });
}

showPasswordForgotten = function() {
  $('.password-reset-div').fadeIn();
}

closePF = function() {
   $('.password-reset-div').fadeOut();
}

