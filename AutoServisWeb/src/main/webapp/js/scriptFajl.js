function showForm() {

	var x = document.getElementById("form");
	if (x.style.display === "none") {
		x.style.display = "block";
	} 
	
	var y = document.getElementyByClassName("zaposleni");
	
	if(y.style.display === "block"){
		y.style.display === "none";
	}
	
	var tekst = document.getElementByClassName("usluge");
	
	if(tekst.style.display === "block"){
		tekst.style.display = "none";
	}

}

function showFormPopravka(){
	// Prikazati formu nakon klika na radioButton 
}

function showFormIzmena(){
	// Prikazati formu za izmenu popravke
}

function showFormVozilo(){
	
	var forma = document.getElementById("formV");
	var formaK = document.getElementById("formK");
	
	if(forma.style.display === "none"){
		forma.style.display = "block";
		formaK.style.display = "none";
	}
	
}

function showFormKlijent(){

	var formaK = document.getElementById("formK");
	var forma = document.getElementById("formV");
	
	if(formaK.style.display === "none"){
		formaK.style.display = "block";
		forma.style.display = "none";
	}
}
