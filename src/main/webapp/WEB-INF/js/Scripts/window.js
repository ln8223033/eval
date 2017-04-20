//获取浏览器窗口高度
function getTotalHeight(){             
	if($.browser.msie){
    	return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;
    }
    else{
    	return self.innerHeight;
    }
}

//获取浏览器窗口宽度
function getTotalWidth (){
	if($.browser.msie){
		return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth;
	}
	else{
		return self.innerWidth;
	}
}        