// minimizare/maximizare
var conti1 = true;
var conti2 = true;
var conti3 = true;
var conti4 = true;
var conti5 = true;

function butall(){
	but1();
	but2();
	but3();
	but4();
	but5();
}

function but1(){
	var d = document.getElementById("cont1");
	var b = document.getElementById("buton1");
	if(conti1) 
	{
		conti1 = false;          
		d.className += " mic";	
		b.innerHTML = "Maximizare continut";
	} 
	else {
		conti1 = true;
		d.className = ""
		b.innerHTML = "Minimizare continut";
	}
}

function but2(){
	var d = document.getElementById("cont2");
	var b = document.getElementById("buton2");
	if(conti2) 
	{
		conti2 = false;          
		d.className += " mic";	
		b.innerHTML = "Maximizare continut";
	} 
	else {
		conti2 = true;
		d.className = ""
		b.innerHTML = "Minimizare continut";
	}
}

function but3(){
	var d = document.getElementById("cont3");
	var b = document.getElementById("buton3");
	if(conti3) 
	{
		conti3 = false;          
		d.className += " mic";	
		b.innerHTML = "Maximizare continut";
	} 
	else {
		conti3 = true;
		d.className = ""
		b.innerHTML = "Minimizare continut";
	}
}

function but4(){
	var d = document.getElementById("cont4");
	var b = document.getElementById("buton4");
	if(conti4) 
	{
		conti4 = false;          
		d.className += " mic";	
		b.innerHTML = "Maximizare continut";
	} 
	else {
		conti4 = true;
		d.className = ""
		b.innerHTML = "Minimizare continut";
	}
}

function but5(){
	var d = document.getElementById("cont5");
	var b = document.getElementById("buton5");
	if(conti5) 
	{
		conti5 = false;          
		d.className += " mic";	
		b.innerHTML = "Maximizare continut";
	} 
	else {
		conti5 = true;
		d.className = ""
		b.innerHTML = "Minimizare continut";
	}
}

function ceAiApasat(event){
	var z = document.getElementById("chestii");
	z.src = "imagini/slide.png"; 

}


    document.addEventListener("click", adauga_mesaj("Amesaj"));

function adauga_mesaj(id){
    document.getElementById(id).innerHTML = "Adresa email va fi folosita pentru a va raspunde la mesaj!";

}

