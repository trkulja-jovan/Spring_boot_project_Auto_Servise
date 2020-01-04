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
	
	var formaPopravka = document.getElementById("formPopravka");
	var formaEdit = document.getElementById("formEdit");
	
	if(formaPopravka.style.display === "none"){
		formaPopravka.style.display = "block";
		formaEdit.style.display = "none";
		formaPridr.style.display = "none";
	} else if(formaEdit.style.display === "none"){
		formaPridr.style.display = "none";
		formaEdit.style.display = "block";
		formaPopravka.style.display = "none";
	} 
}

var formaPridr = document.getElementById("formPridr");

function showFormPridr() {
	var formaPridr = document.getElementById("formPridr");
	var formaPopravka = document.getElementById("formPopravka");
	var formaEdit = document.getElementById("formEdit");
	
	formaPridr.style.display = "block";
	formaPopravka.style.display = "none";
	formaEdit.style.display = "none";
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
