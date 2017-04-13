/**
 * suggest.jsを使ってAjaxサジェスト
 */

$(function(){
	

	var size = $('#customer_name').parent().width() - 1;


//	var size = $(#customer_name).width();
//	console.log(size);
	

	// 名称
	new Suggest.Local(
      "customer_name",
      "name_suggest",
      [],
      {
        highlight: true,
        hookBeforeSearch: function(nameSearchData) {

         var self = this,
         	suggestArea = $(self.suggestArea);

         suggestArea.width(size);

//         var self = this;
         
         $self = $(this);
         $self.width(size);

         
         $.ajax({
       	  type: 'GET',
       	  url: '/name/search',
       	  dataType: 'json',
       	  data: {nameSearchData},
       	  
       	  success: function (res) {

   		  	
//       		self.clearSuggestArea();
//       		self.candidateList = res;
//       		var resultList = self._search(nameSearchData);
//	       	 if (resultList.length != 0){
//	             self.createSuggestArea(resultList);
//	          }
//       		  var name_list = res;
//       		  $('#customer_name').autocomplete({
//       			source: name_list,
//       			autoFocus: true,
//       			minLength: 1

       		  
       		  
       		 self.clearSuggestArea();
             self.candidateList = res;
             var resultList = self._search(nameSearchData);
             if (resultList.length != 0){
                self.createSuggestArea(resultList);
             }
       		  
//       		  var name_list = res;
//       		  $('#customer_name').autocomplete({
//       			source: name_list,
//       			autoFocus: true

//       		  });
       	  }	  
       	});
       }
      }
   );
   
   // かな
   new Suggest.Local(
      "customer_kana",
      "kana_suggest",
      [],
      {
        highlight: true,
        hookBeforeSearch: function(kanaSearchData) {
         var self = this;
         $.ajax({
       	  type: 'GET',
       	  url: '/kana/search',
       	  dataType: 'json',
       	  data: {kanaSearchData},
       	  
       	  success: function (res) {
       		  
       		  var kana_list = res;
       		  $('#customer_kana').autocomplete({
       			source: kana_list,
       			autoFocus: true
       		  });
       	  }	  
       	});
       }
      }
   );
});

//$(function () {
//	
//  // 候補リストに表示するデータを配列で準備
//  var kana_list = [
//    'こきゃくでーた',
//    'かぶしきがいしゃうぃず',
//    'てすとでーたさん',
//    'てすとでーたよん'
//  ];
//	  
//	
////  // オートコンプリート機能を適用
////  $('#customer_name').autocomplete({
////    source: name_list,
////    autoFocus: true,
////    delay: 300,
////    minLength: 2
////  });
////	  
////  $('#customer_kana').autocomplete({
////    source: kana_list,
////    autoFocus: true,
////    delay: 300,
////    minLength: 3
////  });
//  
//  $('input[name="name"]').keypress(function () {
//	var nameSearchData = $(this).val();
//	
//	$.ajax({
//	  type: 'POST',
//	  url: '/name/search',
//	  dataType: 'json',
//	  data: {nameSearchData},
//	  
//	  success: function (res) {
//		  console.log(res);
//		  var name_list = res;
//		  $('#customer_name').autocomplete({
//			source: name_list,
//			autoFocus: true,
//			minLength: 1
//		  });
//	  }	  
//	});
//  });
//});