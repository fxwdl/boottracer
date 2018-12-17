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