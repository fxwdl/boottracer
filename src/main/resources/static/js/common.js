//转换bool为string
function boolToString(value, row, index) {
	if (typeof value == typeof true) {
		if (value == false) {
			return '否';
		} else if (value == true) {
			return '是';
		}
	}
}

function operateFormatter(value, row, index, type) {
	return [
		'<a class="edit" href="javascript:void(0)" title="编辑">',
		'<i class="glyphicon glyphicon-pencil" data-type=' + type + '></i>',
		'</a>  ',
		'<a class="remove" href="javascript:void(0)" title="删除">',
		'<i class="glyphicon glyphicon-trash" data-type=' + type + '></i>',
		'</a>'
	].join('');
}

function operateOnlyDeleteFormatter(value, row, index, type) {
	return [
		'<a class="remove" href="javascript:void(0)" title="删除">',
		'<i class="glyphicon glyphicon-trash" data-type=' + type + '></i>',
		'</a>'
	].join('');
}

function operateOnlyApproveFormatter(value, row, index, type) {
	return [
		'<a class="approve" href="javascript:void(0)" title="审批">',
		'<i class="glyphicon glyphicon-pencil" data-type=' + type + '></i>',
		'</a>'
	].join('');
}

function memberStatusFormatter(value, row, index, type) {
	if (value == 0)
		return '申请中';
	if (value == 1)
		return '已拒绝';
	if (value == 2)
		return '已通过';
	if (value == 3)
		return '已停用';
	return '';
}

function approvedFormatter(value, row, index, type) {
	switch (value) {
		case 0:
			return '未审核';
		case 1:
			return '已审核';
		case 2:
			return '已拒绝';
		default:
			return '';
	}
}

function addUrlPara(currentUrl, name, value) {
	if (/\?/g.test(currentUrl)) {
		if (/name=[-\w]{4,25}/g.test(currentUrl)) {
			currentUrl = currentUrl.replace(/name=[-\w]{4,25}/g, name + "=" + value);
		} else {
			currentUrl += "&" + name + "=" + value;
		}
	} else {
		currentUrl += "?" + name + "=" + value;
	}
	return currentUrl;
}


function showSelectForm(params) {
	
	var dialogId = BootstrapDialog.newGuid();
	params.url = addUrlPara(params.url, 'dialogId', dialogId);
	var showOpt = {
		size: BootstrapDialog.SIZE_WIDE,
		id: dialogId,
		buttons: [{
			icon: 'glyphicon glyphicon-saved',
			label: '确定',
			cssClass: 'btn-primary',
			action: function (dialog) {
				if (typeof dialog.getData('callback') === 'function') {
					var tbl = $(dialog.getModalContent()).find('table[data-toggle="table"]');
					var selArray = tbl.bootstrapTable('getSelections');
					for (var row = 0; row < selArray.length; row++) {
						dialog.getData('callback').call(this, selArray[row]);
					}
				}
				dialog.close();
			}
		}, {
			icon: 'glyphicon glyphicon-ban-circle',
			label: '取消',
			action: function (dialog) {
				dialog.close();
			}
		}],
		data: {
			'pageToLoad': params.url
		},
		message: function (dialog) {
			var $d = $('<div></div>');
			var page = dialog.getData('pageToLoad');
			$d.load(page);

			return $d;
		},
		title: params.title,
		draggable: true,
		closeByBackdrop: true
	};
	$.extend(showOpt,params);
	var dialog = new BootstrapDialog(showOpt);
	if (params.callback != undefined && params.callback != null && typeof params.callback === 'function') {
		dialog.setData('callback', params.callback);
	}
	return dialog.open();
}