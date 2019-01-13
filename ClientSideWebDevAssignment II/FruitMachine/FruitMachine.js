//---------------By Maciej Kubiniec SD3 R00144142----------------------//

//variable for sprite animation

var bgPos1 = 0;
	var current1=0;
var bgPos2 = 0;
	var current2=0;
var bgPos3 = 0;
	var current3=0;
// functions for moving the "hand" of the machine - pull and back
function pull() {
	
	document.getElementById("test1").style.height = '40',
    document.getElementById("test1").style.transform = 'translate(0px, 70px)',
	document.getElementById("test").style.transition = ' 0.3s',
	document.getElementById("test1").style.transition = ' 0.3s',
	document.getElementById("test3").style.transition = ' 0.3s',
	document.getElementById("test").style.transform = 'translate(0px, 72px)',
	document.getElementById("test").style.height = '27',
	document.getElementById("test").style.width ='27',
	document.getElementById("test3").style.transform = 'translate(0px, 70px)',
	setTimeout(back, 500);
	
}

function back(){
	document.getElementById("test1").style.height = '110',
	document.getElementById("test1").style.transform = 'translate(0px, 0px)',
	document.getElementById("test").style.transform = 'translate(0px, 0px)',
	document.getElementById("test").style.height = '25',
	document.getElementById("test").style.width ='25',
	document.getElementById("test3").style.transform = 'translate(0px, 0px)'
}
 //function for friut animation

function animation(){
	
	var target1 = Math.floor(Math.random()*5+1);
	
	var target2 = Math.floor(Math.random()*5+1);
	
	var target3 = Math.floor(Math.random()*5+1);
	var test =(target1-current1);
	bgPos1 -= 500+100*(target1-current1);
	
	bgPos2 -= 1000+100*(target2-current2);
	bgPos3 -= 1500+100*(target3-current3);
	 
	document.getElementById("col1").style.backgroundPositionY = bgPos1+"px",
	current1=target1;
	document.getElementById("col2").style.backgroundPositionY = bgPos2+"px",
	current2=target2;
	document.getElementById("col3").style.backgroundPositionY = bgPos3+"px",
	current3=target3;
    setTimeout(result, 3000);
	
	
}
function result(){
	
	var col1 = current1
	var col2 = current2
	var col3 = current3
	if ((col1 == col2 ) ||(col1 == col3 ) ||(col2 == col3 )  ){
		confetti();	
	}
	
}

// confetti animation function
function confetti(){
	
		document.getElementById("ole").style.height = '100%';
		document.getElementById("ole").style.transition = "all 4s ease-in";	
		document.getElementById("ole").style.zIndex = '4';
       
		setTimeout(confEnd, 6000);
	}
	
	
function confEnd(){
	document.getElementById("ole").style.height = '0%';
	document.getElementById("ole").style.transition = "0.1s";
	
}

// functions for changing color scheme
function modalStart() {
  
  document.getElementById("myModal").style.display = 'block'
}


function modalClose() {
  document.getElementById('myModal').style.display = "none";
  document.getElementsByClassName("close")[0];
}

function orange(){
	document.getElementById("body").style.backgroundColor = "orange";
	document.getElementById("rama").style.backgroundColor ="#821737";
}
function white(){
	document.getElementById("body").style.backgroundColor = "white";
	document.getElementById("rama").style.backgroundColor = "white";
	document.getElementById("rama").style.borderStyle = "solid";
	
	}



