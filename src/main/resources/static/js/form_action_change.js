$(function () {
	$('.back').click (function () {
		var button = $(this);
		$('#fix').attr('action', button.data('action'));
		$('#fix').submit();
	})
})