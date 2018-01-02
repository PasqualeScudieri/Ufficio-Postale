/*qua ci sta il compari scompari*/
/*e il triangolino*/


/*****************************triangolo***************************/

function appariMenu(){
	if((document.getElementById("menu").style.display)=="none" ||(document.getElementById("menu").style.display)==""){
		document.getElementById("menu").style.display="block";
	}else{
		document.getElementById("menu").style.display="none";
	}
	
}



/*********************************compari scompari****************************************/
function myFunction(x) {
	x.classList.toggle("change");
}
/*
function compari(){
	if((document.getElementById("navLaterale").style.display)=="none" ||(document.getElementById("navLaterale").style.display)==""){
		document.getElementById("navLaterale").style.display="block";
    }else{
    	document.getElementById("navLaterale").style.display="none";
    }
}
*/
function compari(){
	var  schermo;
	schermo= window.innerWidth;
	
	var sizenav1=(window.innerWidth*15)/100;
	if(sizenav1<=200){
		sizenav1=200;
	}
	
	schermo=schermo-sizenav1-30;
	schermo=schermo+'px';
	
	if(window.innerWidth > 700){
		if((document.getElementById("navLaterale").style.display)=="none" ||(document.getElementById("navLaterale").style.display)==""){
			document.getElementById("navLaterale").style.display="block";
			document.getElementById("mainDiv").style.marginLeft="210px";
			document.getElementById("mainDiv").style.width= schermo;
			console.log(schermo);
		}else{
	    	document.getElementById("navLaterale").style.display="none";
			document.getElementById("mainDiv").style.marginLeft="2%";
			document.getElementById("mainDiv").style.width="96%";

	    }
	}else{
		document.getElementById("navLaterale").style.display="block";
		document.getElementById("sfondoOmbra").style.visibility="visible";
	}
	
}

function scompari(){
	document.getElementById("navLaterale").style.display="none";
	document.getElementById("sfondoOmbra").style.visibility="hidden";
	myFunction(document.getElementById("sandwich"));
	document.getElementById("mainDiv").style.marginLeft="2%";
	document.getElementById("mainDiv").style.width="96%";

}

function resize(){
	if(window.innerWidth < 700){
		if(document.getElementById("navLaterale").style.display=="block"){
			document.getElementById("sfondoOmbra").style.visibility="visible";	
			document.getElementById("mainDiv").style.marginLeft="2%";
			document.getElementById("mainDiv").style.width="96%";

		}
		/*document.getElementById("navLaterale").style.top="0";*/
		//document.getElementById("navLaterale").style.boxShadow="3px 3px 4px grey";
		/*document.getElementById("sfondoOmbra").style.zIndex="2";
		document.getElementById("navLaterale").style.zIndex="3";*/

	}
	
	if(window.innerWidth > 700){
		if(document.getElementById("sfondoOmbra").style.visibility=="visible"){
			document.getElementById("sfondoOmbra").style.visibility="hidden";
			document.getElementById("mainDiv").style.marginLeft="220px";
			document.getElementById("mainDiv").style.width="83%";

		}
		//document.getElementById("navLaterale").style.boxShadow="none";
		/*document.getElementById("navLaterale").style.top="50";*/
		/*document.getElementById("sfondoOmbra").style.zIndex="-2";
		document.getElementById("navLaterale").style.zIndex="-1";*/
	}
}