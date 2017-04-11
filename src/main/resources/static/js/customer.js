$(function() {
  // 候補リストに表示するデータを配列で準備
  var name_list = [
    '顧客データ',
    '株式会社W・I・Z',
    'テストデータ03',
    'テストデータ04'
  ];
	  
  var kana_list = [
    'こきゃくでーた',
    'かぶしきがいしゃうぃず',
    'てすとでーたさん',
    'てすとでーたよん'
  ];
	  
	
  // オートコンプリート機能を適用
  $('#customer_name').autocomplete({
    source: name_list,
    autoFocus: true,
    delay: 300,
    minLength: 2
  });
	  
  $('#customer_kana').autocomplete({
    source: kana_list,
    autoFocus: true,
    delay: 300,
    minLength: 3
  });
});