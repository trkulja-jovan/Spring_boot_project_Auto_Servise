function showForm() {

	var x = document.getElementById("form");
	if (x.style.display === "none") {
		x.style.display = "block";
	} 
	
	var y = document.getElementyByClassName("zaposleni");
	
	if(y.style.display === "block"){
		y.style.display === "none";
	}
}