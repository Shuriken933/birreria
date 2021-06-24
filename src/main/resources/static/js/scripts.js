$( document ).ready(function() {

  /* modal */
  $( ".button-modal-login" ).on('click', function() {
    $(".modal_login").toggleClass("modal--open");
  });

  $( ".modal--open" ).on('click', function() {
    $(".modal_login").removeClass("modal--open");
  });


	/*azione del bottone CHIUDI*/
  $( ".chiudi" ).on('click', function() {
		$(".modal_login").removeClass("modal--open");
    $(".modal_register").removeClass("modal--open");
		$(".modal_register--admin").removeClass("modal--open");
		$(".modal_register--cameriere").removeClass("modal--open");
		$(".modal_register--sala").removeClass("modal--open");
		$(".modal_register--tavolo").removeClass("modal--open");
		$(".modal_register--birra").removeClass("modal--open");
		
		$(".modal_edit--cameriere").removeClass("modal--open");
  });

  $( ".modal--open" ).on('click', function() {
    $(".modal_register").removeClass("modal--open");
  });

	$( ".button-modal-register--admin" ).on('click', function() {
    $(".modal_register--admin").toggleClass("modal--open");
  });

	$( ".button-modal-register--cameriere" ).on('click', function() {
    $(".modal_register--cameriere").toggleClass("modal--open");
  });

	$( ".button-modal-register--sala" ).on('click', function() {
    $(".modal_register--sala").toggleClass("modal--open");
  });

	$( ".button-modal-register--tavolo" ).on('click', function() {
    $(".modal_register--tavolo").toggleClass("modal--open");
  });

	$( ".button-modal-register--birra" ).on('click', function() {
    $(".modal_register--birra").toggleClass("modal--open");
  });

$( ".button-modal-edit--cameriere" ).on('click', function() {
    $(".modal_edit--cameriere").toggleClass("modal--open");
  });



});