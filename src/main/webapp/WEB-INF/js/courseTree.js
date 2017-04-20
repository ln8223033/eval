var couseTreeSetting = {
	check : {
		enable : false
	},
	async : {
		enable : true,
		url : 'education/course!listAllCourseForTree.action',
		autoParam : [ "id=ids", "name" ]
	},
	callback : {
		onClick : onClick
	},
	view : {
		selectedMulti : false
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};

function onClick(event, treeId, treeNode) {
	var dataToSend = "courseId=" + treeNode.id;
	$.ajax({
		url:'/question/course',
		dataType:'text',
		data:dataToSend,
		type:'post',
		success:function(data){
			window.location.href = window.location.href;
		},
		error:function(data){
			alert(data);
		}
	});
}

function hideMenu_course() {
	$("#courseContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDownForWindow);
}

function onBodyDownForWindow(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "citySel"
			|| event.target.id == "courseContent" || $(event.target).parents(
			"#courseContent").length > 0)) {
		hideMenu_course();
	}
}

function chooseCourse(target) {//选择课程
	var cityObj = $(target);
	var cityOffset = $(target).offset();
	$("#courseContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDownForWindow);
	$.fn.zTree.init($("#courseTree"), couseTreeSetting);
}

//正则表达式匹配url
function GetQueryString(name) {
	var regex = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(regex);
	if (r != null)
		return unescape(r[2]);
	return null;
}