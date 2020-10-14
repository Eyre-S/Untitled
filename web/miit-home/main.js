var OUTBOARD = 0.05;
var FOLLOW_MODE = true;
var BG
var boardX;
var boardY;
var viewWidth;
var ViewHeight;

window.onload = function () {
	
	BG = document.getElementById("bg");
	viewWidth = document.defaultView.innerWidth;
	ViewHeight = document.defaultView.innerHeight;
	
	bgSizeCalc();
	rmCovery();
}

window.onresize = function () {
	viewWidth = document.defaultView.innerWidth;
	ViewHeight = document.defaultView.innerHeight;
	bgSizeCalc();
};

function mouseMove (e) {
	var x = e.clientX;
	var y = e.clientY;
	if (FOLLOW_MODE) {
		BG.style.right = "-" + x/viewWidth * boardX + "px";
		BG.style.bottom = "-" + y/ViewHeight * boardY + "px";
	} else {
		BG.style.left = "-" + x/viewWidth * boardX + "px";
		BG.style.top = "-" + y/ViewHeight * boardY + "px";
	}
	// console.log(x + "\t" + y);
	// console.log(BG.style.top + "\t" + BG.style.left);
}

function bgSizeCalc () {
	
	var picWidth = BG.clientWidth;
	var picHeight = BG.clientHeight;
	
	//    2000  /   1000   <    4000  /   1000
	if ((picWidth/picHeight) < (viewWidth/ViewHeight)) {
		var resize = viewWidth/picWidth;
	} else {
		var resize = ViewHeight/picHeight;
	}
	boardX = picWidth*OUTBOARD;
	boardY = picHeight*OUTBOARD;
	picWidth = picWidth * resize + boardX;
	picHeight = picHeight * resize + boardY;
	
	BG.style.width = picWidth + "px";
	BG.style.height = picHeight + "px";
	if (FOLLOW_MODE) {
		BG.style.right = "-" + boardX/2 + "px";
		BG.style.bottom = "-" + boardY/2 + "px";
	} else {
		BG.style.left = "-" + boardX/2 + "px";
		BG.style.top = "-" + boardY/2 + "px";
	}
}

function rmCovery() {
	var covery = document.getElementById("covery")
	covery.style.transitionDuration = "500ms";
	covery.style.backgroundColor = "#ffffff00";
	sleep(500).then(() => {
		covery.parentElement.removeChild(covery);
	})
}

function sleep (time) {
	return new Promise((resolve) => setTimeout(resolve, time));
}