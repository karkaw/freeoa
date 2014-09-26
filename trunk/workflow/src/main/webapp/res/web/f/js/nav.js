// JavaScript Document
$(function(){
	$("#nav > li").hover(
		function(){
			$(this).addClass("hover").siblings().removeClass("hover");	
			$("#nav > li .nav_child").hide();
			$(this).find(".nav_child").show();
			//$(this).siblings().find(".nav_child").hide();	
		},
		function(){
			$(this).find(".nav_child").hide();
			
		}
	)	
})