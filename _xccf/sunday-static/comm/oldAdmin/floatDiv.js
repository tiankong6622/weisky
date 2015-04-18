
function floadDivToMiddle(divid){ 
	var fucenghtml = '<div id="fuchenbeijing" style="position:absolute;top:0;left:0;background:#FFF;opacity:0.9;filter:Alpha(Opacity=10);z-index:1003;"></div>';

	$("body").append(fucenghtml);
	$('#fuchenbeijing').css({
		'height':$(window).height()+$(window).scrollTop(),
		'width':$(window).width()
	}).show();
	$('#'+divid).css({
		'top':($(window).height() - $('#'+divid).height())/2+$(window).scrollTop(),
		'left':($(window).width() - $('#'+divid).width())/2
	}).show();
		
	
	
}

function hidenFloatDiv(divid){
	$('#'+divid).hide();
	$('#fuchenbeijing').remove();
}