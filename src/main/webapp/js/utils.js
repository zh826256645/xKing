function removeFather(id) {
	console.log(id);
	$("#" + id).parent().remove();
}
