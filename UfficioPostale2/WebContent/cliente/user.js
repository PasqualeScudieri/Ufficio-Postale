/**
 * 
 */
function iniziali(){
	var x = document.getElementById("inzialiNascoste").innerHTML;
	console.log("ahhhhhh: "+x);
	document.getElementById("nome").innerHTML = x;	
}

window.onload=iniziali;