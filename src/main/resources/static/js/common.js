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