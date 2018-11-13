/**
 * 
 */
$(function () {

	$('.edit-btn').click(function () {
		var hidden = $('input:hidden[name=id]');
		location.href='/customer/' + hidden.val() + '/edit';
	});
})