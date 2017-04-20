function bindAddForm() {
	$('#addEvaForm').submit(function() {
		if ($('#evalTitle').val() == '') {
			alert("请填写题目");
			return false;
		}

		$(this).ajaxSubmit(function(data) {
			if (data.length == 32) {
				$('#evalq_id').val(data);
				alert("成功");
				window.location.href = "interact/eval!getDetail.action?id="+data;
			} else {
				alert("提交失败");
			}
		});

		return false;
	});
	$("#argForm").submit(function() {
		if ($('#time1').val() == '') {
			alert("请选择开始时间");
			return false;
		}
		if ($('#time2').val() == '') {
			alert("请选择结束时间");
			return false;
		}
		if ($('#time3').val() == '') {
			alert("请选择作品上传截止时间");
			return false;
		}
		$(this).ajaxSubmit(function(data) {
			if (data == 'true') {
				alert("设置成功");
			} else {
				alert(data);
				return false;
			}
			$("#argLayer").hide();
		});
		return false;
	});
}

/**
 * 参数设置
 */
function setArgLayer() {
	$("#shareUserLayer").hide();
	$("#argLayer").show(1000);
}

var tmpShareUser = null;

function setShareUserLayer() {
	$("#argLayer").hide();
	$("#shareUserLayer").show(1000);
	var qid = $("#evalq_id").val();
	if (qid == null || qid == '') {
		$("#suinfo").html("题目还未创建");
		return;
	} else {
		$.ajax({
			url : 'evaluate!getShareUser.action?qid=' + qid + '&ran='
					+ Math.random(),
			dataType : 'json',
			success : function(data) {
				data = eval("(" + data + ")");
				var tmp = new Array();
				var tmp2 = new Array();
				var data1 = data.data1;
				var data2 = data.data2;
				html = "";
				for (var i = 0; i < data2.length; i++) {
					var uid = data2[i].uid;
					var name = data2[i].name;
					var id = data2[i].id;
					tmp[uid] = name;
					tmp2[uid] = id;
					html += "<span><input type=\"checkbox\" checked type=\"checkbox\" value='"
							+ uid
							+ "' name=\"userIds\">"
							+ name
							+ "&nbsp;&nbsp;</span>";
				}
				for (var i = 0; i < data1.length; i++) {
					var uid = data1[i].uid;
					var name = data1[i].name;
					if (typeof(tmp[uid]) == 'undefined') {
						tmp[uid] = name;
						html += "<span><input type=\"checkbox\" value='" + uid
								+ "' name=\"userIds\"/>" + name
								+ "&nbsp;&nbsp;</span>";
					}
				}
				tmpShareUser = tmp2;
				$("#data1").html(html);
			}
		});
	}
}

/**
 * 提交共享用户信息
 */
function submitShareUserInfo() {
	var ids = document.getElementsByName("userIds");
	var data = "";
	var tmp = new Array();
	for (var i = 0; i < ids.length; i++) {
		if (ids[i].checked == true) {
			var id = ids[i].value;
			tmp[id] = id;
			if (typeof(tmpShareUser[id]) == 'undefined') {
				data += "ids=" + id + "&";
			}
		}
	}
	for (var index in tmpShareUser) {
		if (index.length == 32) {
			if (typeof(tmp[index]) == 'undefined') {
				data += "dids=" + tmpShareUser[index] + "&";
			}
		}
	}
	var qid = $("#evalq_id").val();
	$.ajax({
				url : 'evaluate!addOrUpdateShareUser.action',
				data : 'id=' + qid + '&ran=' + Math.random() + '&' + data,
				dataType : 'text',
				type : 'post',
				success : function(data) {
					if (data == 'true') {
						alert("设置成功");
					} else {
						alert("操作失败");
					}
					$("#shareUserLayer").hide();
				}
			});
}

function createWindow(id, x, y, w, h, title, url) {
	var tmp = dhxWins.window(id);
	if (tmp) {
		return;
	}

	var win = dhxWins.createWindow(id, x, y, w, h);

	win.setText(title);

	win.denyResize();
	win.denyPark();

	if (url != null) {
		if (url.indexOf("?") > 0) {
			url += "&ran=" + Math.random();
		} else {
			url += "?ran=" + Math.random();
		}
		win.attachURL(url, true);
	}
	return win;
}

// 创建添加检查项目的窗口
function createAddItemWin() {
	
	var qid = document.getElementById("evalq_id").value;
	if (qid == '') {
		alert("请先创建题目再添加该项目");
		return;
	}
	var win = createWindow("addItemWin", 40, 0, 400, 120, "添加或修改检查项目", null);
	var html = "<div style=\"margin:5px\"><input type=\"hidden\" id=\"hide_item_id\"/><table width=100%><tr><td>项目名称</td>"
			+ "<td><input id=\"new_item\" size=50 type=\"text\" style=\"height:30px;\"></td></tr>"
			+ "<tr><td></td><td><input type=\"button\" onclick=\"addItem()\" class=\"btn btn-primary\" value=\"确定\">"
			+ "&nbsp;&nbsp;<input type=\"button\" onclick=\"closeWin('addItemWin')\" class=\"btn btn-primary\" value=\"取消\"></td></tr></table></div>";
	win.attachHTMLString(html);
}
// 修改检查项目
function modifyItem() {
	var itemId = itemGrid.getSelectedId();
	if (itemId == '' || itemId == null) {
		alert("请选择一个检查项目进行修改");
		return;
	}
	createAddItemWin();
	var name = itemGrid.cells(itemId, 0).getValue();
	$("#new_item").val(name);
	$("#hide_item_id").val(itemId);
}
// 添加项目指标
function createAddSubitemWin() {
	var itemId = itemGrid.getSelectedId();
	if (itemId == '' || itemId == null) {
		alert("请选择一个检查项目，然后再添加该项目的指标");
		return;
	}

	var win = createWindow("addSubItemWin", 80, 0, 400, 220, "添加检查项目指标", null);
	var html = "<div style=\"margin:5px\"><input type=\"hidden\" id=\"hide_subitem_id\"/><table width=100%><tr><td>指标内容</td>"
			+ "<td><textarea cols=40 id=\"new_subItem\" style=\"resize: none;\" rows=3></textarea></td></tr>"
			+ "<tr><td>标准分</td><td><input id=\"new_credit\" style=\"height:40px;\" type=\"text\"></td></tr>"
			+ "<tr><td></td><td><input type=\"button\" onclick=\"addSubitem()\" class=\"btn btn-primary\" value=\"确定\">"
			+ "&nbsp;&nbsp;&nbsp;<input type=\"button\" onclick=\"closeWin('addSubItemWin')\" class=\"btn btn-primary\" value=\"取消\"></td></tr></table></div>";
	win.attachHTMLString(html);
}
// 修改指标
function modifySubItem() {
	var id = subitemGrid.getSelectedRowId();
	if (id == null || id == '') {
		alert("请选择一个检查指标进行修改");
		return;
	}
	createAddSubitemWin();
	var item = subitemGrid.cells(id, 0).getValue();
	var credit = subitemGrid.cells(id, 1).getValue();
	$("#new_credit").val(credit);
	$("#new_subItem").val(item);
	$("#hide_subitem_id").val(id);
}
function closeWin(id) {
	try {
		dhxWins.window(id).close();
	} catch (e) {
	}
}

/**
 * 转到编辑状态
 */
function toEdit() {
	var id = qGrid.getSelectedId();
	if (id == null || id == '') {
		alert("请选择一个题目进行编辑");
		return;
	}
	window.location.href = "evaluate!getQ.action?id=" + id + "&to=edit";
}
/**
 * 添加检查项目
 */
function addItem() {
	var item = $("#new_item").val();
	if (item == "") {
		alert("请输入检查项目名称");
		return;
	}
	var id = $("#evalq_id").val();
	var itemId = $("#hide_item_id").val();
	var data = 'id=' + id + "&item=" + item;
	if (itemId != '' && itemId.length == 32) {
		data += '&itemId=' + itemId;
	}
	$.ajax({
		url : 'interact/eval-question!addItem.action',
		data : data,
		dataType : 'text',
		type : 'post',
		success : function(data) {
			if (data == 'true') {
				itemGrid.updateFromXML("interact/eval-question!getItemXML.action?id="
						+ id);
				alert("添加指标成功!");
			} else {
				alert("添加检查项目失败");
			}
			closeWin('addItemWin');
		}
	});
}

/**
 * Item选择事件
 */
function doOnItemSelected(id) {
	var value = itemGrid.cells(id, 0).getValue();
	$("#currentItem").html(value);
	subitemGrid.clearAll();
	subitemGrid.updateFromXML("interact/eval-question!getSubItemXML.action?itemId=" + id,
			true, true);
}

function addSubitem() {
	var text = $("#new_subItem").val();
	if (text == '') {
		alert("请输入项目指标内容");
		return null;
	}
	var credit = $("#new_credit").val();
	if (credit == '' || parseFloat(credit) < 0) {
		alert("请输入标准分数");
		return;
	}
	if (isNaN(credit)) {
		alert("标准分数是数字，请输入数字");
		return;
	}
	var id = $("#evalq_id").val();
	var itemId = itemGrid.getSelectedId();
	var subItemId = $("#hide_subitem_id").val();
	var data = 'itemId=' + itemId + "&credit=" + credit + "&item=" + text;
	if (subItemId != '' && subItemId.length == 32) {
		data += '&subItemId=' + subItemId;
	}
	$.ajax({
				url : 'interact/eval-question!addSubItem.action',
				dataType : 'text',
				data : data,
				type : 'post',
				success : function(data) {
					if (data == 'true') {
						itemGrid.updateFromXML("interact/eval-question!getItemXML.action?id=" + id);
						//var targetRow = $(itemGrid.getRowById(itemId));
						subitemGrid
								.updateFromXML("interact/eval-question!getSubItemXML.action?itemId="
										+ itemId);
						
					} else {
						alert("添加指标内容失败");
					}
					closeWin('addSubItemWin');
				}
			});
}

/**
 * 删除subitem
 */
function deleteSubItem() {
	var id = subitemGrid.getSelectedRowId();
	if (id == null || id == '') {
		alert("请选择一个检查指标");
		return;
	} else {
		if (confirm("你确定在删除所选检查指标吗?")) {
			$.ajax({
						url : 'interact/eval-question!deleteSubItem.action?subitem=' + id,
						dataType : 'text',
						success : function(data) {
							if (data == 'true') {
								var id = $("#evalq_id").val();
								itemGrid.updateFromXML("interact/eval-question!getItemXML.action?id=" + id);
								var itemId = itemGrid.getSelectedId();
								subitemGrid.updateFromXML(
										"interact/eval-question!getSubItemXML.action?itemId="
												+ itemId, true, true);
							} else if (data == 'false') {
								alert("该评价表在使用中，不能删除");
							} else {
								alert("该评价表在使用中，不能删除");
							}
						},
						error : function() {
							alert("该评价表在使用中，不能删除");
						}
					});
		}
	}
}

// 复制评论表
function copyQ() {
	var srcId = $("#qselector").val();
	var id = $("#evalq_id").val();
	if (id == '' || id == null) {
		alert("请先创建题目再");
		return;
	}
	if (srcId == null || srcId == '' || srcId == '0') {
		alert("请选择需要复制的评论表");
		return;
	}
	if (srcId.length != 32) {
		alert("请选择需要复制的评论表");
		return;
	}
	$.ajax({
				url : 'interact/eval-question!copyCheckItem.action?id=' + id + '&srcId=' + srcId,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("复制成功");
						//window.location.href = window.location.href;
					} else {
						alert("复制失败");
					}
				}
			});

}

$(function() {
	$("#qselector").change(function(){
		previewQ();
	});
});

// 预览评测课题指标
function previewQ() {
	var srcId = $("#qselector").val();
	if (srcId == null || srcId == '' || srcId == '0') {
		alert("请选择需要预览的课题指标");
		return;
	} else if (srcId.length != 32) {
		alert("请选择需要预览的课题指标");
		return;
	} else {
		itemGrid = new dhtmlXGridObject('grid1');
		itemGrid.setImagePath("plugins/dhtmlx/dhtmlxGrid/codebase/imgs/");
		itemGrid.setHeader("检查项目名称");
		itemGrid.setInitWidths("*");
		itemGrid.setColAlign("left");
		itemGrid.setColTypes("ed");
		itemGrid.attachEvent("onRowSelect", doOnItemSelected);		
		itemGrid.init();
		itemGrid.setSkin("dhx_skyblue");
		if (srcId != "") {
			itemGrid.loadXML("interact/eval-question!getItemXML.action?id="+srcId);
		}
	}
}

//查看自己的项目指标
function seeSelfQ() {
	var id = $("#evalq_id").val();
	if (id != "") {
		itemGrid = new dhtmlXGridObject('grid1');
		itemGrid.setImagePath("plugins/dhtmlx/dhtmlxGrid/codebase/imgs/");
		itemGrid.setHeader("检查项目名称");
		itemGrid.setInitWidths("*");
		itemGrid.setColAlign("left");
		itemGrid.setColTypes("ed");
		itemGrid.attachEvent("onRowSelect", doOnItemSelected);		
		itemGrid.init();
		itemGrid.setSkin("dhx_skyblue");
		itemGrid.loadXML("interact/eval-question!getItemXML.action?id="+id);
	}
}

// 学生预览
function stuPreView(id, groupId2) {
	var obj = new Object();
	var groupId = groupId2;
	if (groupId == null || groupId == '') {
		obj.groupId = groupGrid.getSelectedId();
		if (obj.groupId == null || obj.groupId == '') {
			alert("请选择一项目");
			return;
		}
	} else {
		obj.groupId = groupId;
	}
	var url = "evaluate!getQ.action?id=" + id + "&to=stuPreview";
	window.showModalDialog(url, obj,
			"dialogWidth=840px;resizable=yes;dialogHeight=600px");

}

/**
 * 当点击一个人一组时的事件
 */
function onClickSingleGroup(obj) {
	var checked = obj.checked;
	if (checked) {
		$("#forEach").val(1);
		$("#forEach").attr("disabled", "disabled");
		$("#free").attr("disabled", "disabled");
		$("#free").removeAttr("checked");
	} else {
		$("#forEach").removeAttr("disabled");
		$("#free").removeAttr("disabled");
	}
}
/**
 * 分组事件
 */
function doGroup(obj) {
	
	var checkGroupCount = $("#checkGroup").val();
	if (checkGroupCount == null || checkGroupCount == '' || isNaN(checkGroupCount)) {
		alert("评论人数不能为空且是数字!");
		return;
	}
	var size = $("#forEach").val();
	if (size == null || size == '' || isNaN(size)) {
		alert("每组多少人不能为空且是数字!");
		return;
	}
	$(obj).remove();
	$('#returnInfo').css("display", "inline-block");
	$('#returnInfo').html("正在分组中...请稍后！");
	var free = $("#free").attr("checked");
	var evalId = $("#evalId").val();
	var url = 'interact/eval-group!doGroup.action?evalId=' + evalId + "&groupSize=" + size
			+ "&free=" + free + "&checkGroupCount=" + checkGroupCount;
	$.ajax({
				url : url,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("分组成功");
						window.location.href = window.location.href;
					}
					/*
					 * var selector = document.getElementById("classSelector");
					 * $(data).find("class").each(function() { var cls =
					 * $(this); var option = document.createElement("option");
					 * option.setAttribute("value", cls.find("id") .text());
					 * option.text = (cls.find("name").text()); try {
					 * selector.add(option, null); } catch (e) {
					 * selector.add(option); } }); loadGroup();
					 */
				},
				error:function() {
					alert("分组失败！请刷新重试或联系管理员！");
					$('#returnInfo').html("");
				}
			});
}

function doCheckTask(evalId) {//对评论任务分组进行参数设置
	//alert(evalId);
	//var evalId = $("#evalId").val();
	var evalId = evalId;
	var checkGroupCount = document.getElementById("checkGroup").value;
	var url = 'interact/eval-group!doCheckTask.action?evalId=' + evalId + '&checkGroupCount=' + checkGroupCount;
	$.ajax({
			url : url,
			dataType : 'text',
			success : function(data) {
				if (data == 'true') {
					alert("分配任务成功");
					window.location.href = window.location.href;
				}else if (data == 'false') {
					alert("分配任务失败！请检查组数是否符合要求！");
				}
			},
			error:function(data) {
				alert("分配任务失败！请刷新重试或联系管理员！");
			}
		});
}

function loadGroup() {
	var classId = $("#classSelector").val();
	var evalId = $("#evalId").val();
	var url = "evaluate!getGroupXML.action?id=" + evalId + "&clsId=" + classId;
	try {
		groupGrid.clearAll();
		groupGrid.updateFromXML(url, true, true, afterLoadGroup);
	} catch (e) {
	}
}
function loadMyCheckGroup() {
	var evalId = $("#evalId").val();
	var url = "evaluate!getMyCheckGroup.action?id=" + evalId;
	try {
		groupGrid2.updateFromXML(url, true, true);
	} catch (e) {
	}
}
var needEdit = true;
function afterLoadGroup() {
	if (groupGrid.getRowsNum() <= 0 && needEdit) {
		$("#info").css("display", "none");
		$("#edit").css("display", "block");
	} else {
		needEdit = false;
		doneLoadClassFile = false;
		var divs = document.getElementsByName("fileDivLine");
		var parent = document.getElementById("fileList");
		for (var i = 0; i < divs.length; i++) {
			divs[i].parent.removeChild(divs[i]);
		}
	}
}

/**
 * 创建添加我的项目名称的窗口
 */
function createAddOrUpdateMyProNameWin() {
	var win = createWindow("addProNameWin", 40, 20, 400, 180, "编辑我的项目名称", null);
	$("#obj_addProName").css("display", "block");
	win.attachObject('obj_addProName');
}
/**
 * 添加我的项目名称
 */
function addOrUpdateProName(id) {
	var proName = $("#proName").val();
	if (proName == null || proName == '') {
		alert("请输入项目名称");
		return;
	}
	if (id.length != 32) {
		alert("数据有误");
		return;
	}
	$.ajax({
				url : 'evaluate!addOrUpdateMyProName.action',
				dataType : 'text',
				data : 'id=' + id + "&name=" + proName,
				type : 'post',
				success : function(data) {
					if (data != 'true') {
						alert("添加失败");
					} else {
						alert("操作成功");
						$("#proName_").html(proName);

						loadGroup();
					}
					$('#myProNameDiv').hide(1000);
				}
			});
}

/**
 * 创建一个组
 */
function createAGroup() {
	var evalId = $("#evalId").val();
	var no = groupGrid.getRowsNum();
	var data = "id=" + evalId + "&no=" + no;
	$.ajax({
				url : 'evaluate!createGroup.action?' + data,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("创建成功");
						window.location.href = window.location.href;
					} else if (data == 'leader') {
						alert("创建失败，你已是一个组的组长");
						return;
					} else if (!isNaN(data)) {
						alert("创建失败，你已在第" + data + "组");
						return;
					} else {
						alert("创建失败");
					}
				}
			});
}
/**
 * 加入某个组
 */
function addToGroup() {
	var evalId = $("#evalId").val();
	var groupId = groupGrid.getSelectedRowId();
	if (groupId == null || groupId == '') {
		alert("请选择一个组");
		return;
	}

	var groupSize = $("#groupSize").val();
	var groups = groupGrid.cells(groupId, 1).getValue();
	var length = groups.split("  ").length;
	if (Number(length) >= Number(groupSize)) {
		alert("该组人已经满了");
		return;
	}

	var leader = groupGrid.cells(groupId, 2).getValue();
	if (confirm("你确定加入" + leader + "组?")) {
		$.ajax({
					url : 'evaluate!addToGroup.action?id=' + evalId
							+ '&groupId=' + groupId,
					dataType : 'text',
					success : function(data) {
						if (data == 'true') {
							alert("加入成功");
							window.location.href = window.location.href;
						} else if (!isNaN(data)) {
							alert("加入失败，你已经在第" + data + "组中!");
							return;
						} else {
							alert("加入失败,请联系管理员");
							return;
						}
					},
					error : function() {
						alert("加入失败");
					}
				});

	}
}

/**
 * 改变一个组的组长
 */
function changeLeader(memberId) {
	if (!confirm("你确定要转让吗?")) {
		return;
	}
	var evalId = $("#evalId").val();
	var url = "evaluate!changeLeader.action?id=" + evalId + "&memberId="
			+ memberId + "&ran=" + Math.random();
	$.ajax({
				url : url,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("组长转让成功");
						window.location.href = window.location.href;
					} else {
						alert("转让失败");
					}
				},
				error : function() {
					alert("转让出错");
				}
			});
}
/**
 * 退出当前组
 */
function quitGroup(id) {
	if (!confirm("确定要退出当前的组吗?")) {
		return;
	}
	if (id.length != 32) {
		alert("数据错误");
		return;
	}
	$.ajax({
				url : 'evaluate!removeFromGroup.action?id=' + id,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("退出组成功");
						window.location.href = window.location.href;
					} else {
						alert("退出组失败");
					}
				}
			});
}

/**
 * 自由组合开关
 */
function freeGroup(id, tag) {
	if (tag == 'yes') {
		if (!confirm("确实要打开自由组合吗？打开自由组合后，评论将自动设置为关闭状态")) {
			return;
		}
	} else if (tag == 'no') {

		if (!confirm("确实要关闭自由组合吗？")) {
			return;
		}
	}
	var id = $("#evalId").val();
	$.ajax({
				url : 'evaluate!qSwitch.action?id=' + id + '&_tag=' + tag,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("成功");
						window.location.href = window.location.href;
					} else {
						alert("操作失败");
					}
				},
				error : function() {
					alert("操作失败");
				}
			});
}

/**
 * 分组评论
 */
function doCheckGroup() {
	var size = $("#checkSize").val();
	if (size == '') {
		alert("请填写每人评论数量");
		return;
	}
	if (isNaN(size)) {
		alert("评论分组大小应该为数字");
		return;
	} else {
		var groupSize = groupGrid.getRowsNum();
		if (groupSize < 1) {
			alert("该班级还没有分组,请先进行分组后再进行评论分组");
			return;
		}
		if (size < 1 && size > groupSize) {
			alert("每个同学评论的数量应该大于0和小于或等于该班的分组数量(" + groupSize + ")");
			return;
		}
	}

	var evlId = $("#evalId").val();
	var clsId = $("#classSelector2").val();
	if (clsId.length == 32 && evlId.length == 32) {
		var url = "evaluate!divideStudentCheck.action?id=" + evlId + "&clsId="
				+ clsId + "&size=" + size;
		$("#loadingbgmsg").html("正在进行评论分组……");
		$("#loadingbg").show();
		$.ajax({
					url : url,
					dataType : 'text',
					success : function(data) {
						if (data == 'true') {
							loadCheckGroupData(true);
						} else if (data == 'false') {
							alert("该评论题目所交作业的人数小于每组组数，暂不能进行评论分组操作");
							$("#loadingbg").hide();
						} else {
							alert("评论分组失败");
							$("#loadingbg").hide();
						}
					}
				});
	}
}

/**
 * 删除评论分组
 */
function deleteCheckGroup() {
	var tmpIds = groupCheckGrid.getAllItemIds().split(/,|_/);
	var data = "ids=";
	for (var i = 0; i < tmpIds.length; i++) {
		if (tmpIds[i].length == 32) {

			data += "'" + tmpIds[i] + "',";
		}
	}
	data = data.substring(0, data.length - 1);
	$.ajax({
				url : 'evaluate!deleteCheckGroup.action',
				type : 'post',
				dataType : 'text',
				data : data,
				success : function(data) {
					if (data == 'true') {
						alert("成功");
						groupCheckGrid.clearAll();
						$("#dividinfo").show();
					}
				}
			});
	return false;
}

/**
 * 加载评论分组信息 tag 可选，有的时候是刚分完组，为true
 */
var isFirstLoad = true;// 分组信息是否是第一次加载
function loadCheckGroupData(tag) {

	if (!isFirstLoad && !tag) {
		return;
	}
	isFirstLoad = false;
	$("#dividinfo").hide();

	var classId = $("#classSelector2").val();
	var evalId = $("#evalId").val();
	var url = "evaluate!getCheckGroupXML.action?id=" + evalId + "&clsId="
			+ classId;
	groupCheckGrid.clearAll();
	groupCheckGrid.updateFromXML(url, true, true, afterLoadCheckGroup);

	$("#loadingbg").hide();
}
function afterLoadCheckGroup() {
	if (groupCheckGrid.getRowsNum() <= 0) {
		$("#dividinfo").show();
		return;
	}
	if (needLoadClassFile) {
		needLoadClassFile = false;
		fileList4Class();
	}
}
// 添加评论反馈
function addFeedBack(id) {
	if (id.length == 32) {
		var data = $("#stuFB").val();
		if (data == null || data == '') {
			alert("请输入反馈意见");
			return;
		}
		$.ajax({
					url : 'evaluate!addFeedBack.action',
					data : 'id=' + id + '&fb=' + data,
					type : 'post',
					dataType : 'text',
					success : function(data) {
						if (data == 'true') {
							$("#stuFBDiv").hide();
						}
					}
				});
	}
}

// 设置用户可以再次上传文件
function setUploadFileP(id) {
	$.ajax({
				url : 'evaluate!setUploadP.action?id=' + id,
				dataType : 'text',
				success : function(data) {
					if (data == 'true') {
						alert("设置成功");
						return;
					} else if (data == 'done') {
						alert("已设置");
						return;
					}
				}

			});
}

/**
 * 获取鼠标的坐标
 */
function mousePosition(ev) {
	if (ev.pageX || ev.pageY) {
		return {
			x : ev.pageX,
			y : ev.pageY
		};
	}
	return {
		x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}

var commentArray = new Array();// 评论的本地变量
var fcommentArray = new Array();// 评论的

var clientX;
var clientY;

function showComment(id, e) {

	$('#fctextarea').hide();
	var mousePos = mousePosition(e);
	$("#commentDiv").css("top", mousePos.y - 15);
	$("#commentDiv").css("left", mousePos.x - 15);
	clientY = mousePos.y - 15;
	clientX = mousePos.x - 15;
	if (typeof(commentArray[id]) == 'undefined') {
		$.ajax({
					url : 'evaluate!getGroupComment.action?groupId=' + id,
					dataType : 'json',
					success : function(data) {
						var data_ = eval("(" + data + ")");
						if (data_.success == true) {
							commentArray[id] = data_.comments;
							dealData(id, e, commentArray[id]);
						}
					}
				});

	} else {
		dealData(id, e, commentArray[id]);
	}
	$("#fcommentDiv").html("");
	if (typeof(fcommentArray[id]) == 'undefined') {
		$.ajax({
					url : 'evaluate!getGroupFeeComment.action?gid=' + id,
					dataType : 'json',
					success : function(data) {
						var _data = eval("(" + data + ")");
						if (_data.success == true && _data.data.length > 0) {
							fcommentArray[id] = _data.data;
							dealData2(id, e, fcommentArray[id]);
						} else {

						}
					}
				});
	} else {
		dealData2(id, e, fcommentArray[id]);
	}
	$("#commentDiv").show(500);
}
function dealData(id, e, obj) {

	try {
		var stuName = groupGrid.cells(id, 2).getValue();
		var proName = groupGrid.cells(id, 3).getValue();
		var feedBack = groupGrid.cells(id, 5).getValue();
		if (feedBack == '') {
			feedBack = "无反馈意见";
		}
	} catch (e) {
		$("#fgid").val(id);
	}

	$("#stuNameInfo").html(stuName);
	$("#proNameInfo").html(proName);

	var box = document.getElementById("infoDetail");
	$("#infoDetail").html("");
	for (var i = 0; i < obj.length; i++) {
		var stuN = obj[i].userName;
		var stuC = obj[i].comment;
		var tag = obj[i].tag;
		if ((tag & 1) != 1 || stuC == '') {
			continue;
		}
		var div = document.createElement("div");
		div.className = "checkUserName2";
		div.innerHTML = stuN;

		var div2 = document.createElement("div");
		div2.className = "checkResult";
		div2.innerHTML = stuC;

		box.appendChild(div);
		box.appendChild(div2);

	}
	$("#feedBackDiv2").html(feedBack);

}

function dealData2(id, e, fc) {
	var box = document.getElementById("fcommentDiv");
	$("#fcommentDiv").html("");
	for (var i = 0; i < fc.length; i++) {
		var name = fc[i].name;
		var comment = fc[i].c;
		var div = document.createElement("div");
		div.className = "checkUserName2";
		div.innerHTML = name;

		var div2 = document.createElement("div");
		div2.className = "checkResult";
		div2.innerHTML = comment;
		box.appendChild(div);
		box.appendChild(div2);
	}
}
function hideComment(e) {
	var mousePos = mousePosition(e);
	var y = mousePos.y;
	var x = mousePos.x;
	var height = ($("#commentDiv").attr("offsetHeight"));
	if (x > (Number(clientX) + 3) && x < (clientX + 447) && y > (clientY + 3)
			&& y < (clientY + height - 3)) {
	} else {
		$("#commentDiv").hide(500);

	}

}

function showCheckGroupD() {
	var box = document.getElementById("dcheckgroupInfo2");
	box.innerHTML = "";
	$("#dcheckgroupInfo").show(500);
	var idStr = groupCheckGrid.getAllItemIds();
	var ids = idStr.split(",");
	var tmpGarray = new Array();
	for (var i = 0; i < ids.length; i++) {
		var name = groupCheckGrid.cells(ids[i], 0).getValue();
		var groupNo = groupCheckGrid.cells(ids[i], 1).getValue();
		if (typeof(tmpGarray[groupNo]) == 'undefined') {
			tmpGarray[groupNo] = '第' + groupNo + '组  ' + name;
		} else {
			tmpGarray[groupNo] = tmpGarray[groupNo] + "   " + name;
		}
	}

	for (var index in tmpGarray) {
		if (!isNaN(index)) {
			var div = document.createElement("div");
			div.innerHTML = tmpGarray[index];
			div.className = "dcheckgroupLine";
			box.appendChild(div);
		}
	}
}