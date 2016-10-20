/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$.fn.extend({
	animateCss: function (animationName) {
		var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
		this.addClass('animated ' + animationName).one(animationEnd, function () {
			$(this).removeClass('animated ' + animationName);
		});
	}
});


$(function () {
  $('[data-toggle="tooltip"]').tooltip();
});

$('#myTabs a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});


function updateCartCountBadge() {
	var data = OmniFaces.Ajax.data;
	$("#cart-add-success").removeClass('hidden');
	$("#cart-item-count").text(data.cartCount);
	$("#cart-item-count").animateCss('bounce');
}


function paymentsuccess(){
	$('#payment-success').removeClass("hidden");
	$('#payment-form').addClass("hidden");
	updateCartCountBadge();
	setTimeout(function() { 
    window.location.href = "/dukes/index.xhtml"; 
 }, 3000);
}
;