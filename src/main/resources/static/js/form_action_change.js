/**
 * formのsubmit先を登録か更新かで切り替える
 */
$(function () {

	
	var hiddenId = $('input:hidden[name=id]');
	
	$('#customer-finish-btn').click(function () {
		$('#fix').attr('action', '/customer/save');
		
		$('#fix').submit();
 	});
	
	$('#customer-back-btn').click(function () {
		if (hiddenId.val()) {
			$('#fix').attr('action', '/customer/' + hiddenId.val() + '/edit');
		} else {
			$('#fix').attr('action', '/customer/new/entry');
		}
		
		$('#fix').submit();
 	});
	
	
})