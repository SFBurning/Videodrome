var rawData;
var curFrame;
var lastFrame;

function preload(){
    rawData = loadJSON("parsedAEData.json");
    curFrame = 0;
}

function setup() {
    canvas = createCanvas(100, 100);
    canvas.parent("processingDiv");
    frameRate(24);
    background(0, 0);
    lastFrame = 0;
}

function draw() {
    clear(); // Clear to alpha
    if(curFrame) lastFrame = curFrame; // if curframe exists, it is now the previous frame
    curFrame = parseInt($("#time").text()); // get the new frame value
    if (curFrame == lastFrame) curFrame++; // simulate movement if there's no new frame
    //console.log("Target position: " + rawData[curFrame][0] + ", " + rawData[curFrame][1]);
    if(rawData[curFrame][0] != null && rawData[curFrame][1] != 0) { // Draw
        noStroke();
        fill(255,0,0);
        ellipseMode(CENTER);
        ellipse(width/2, height/2, 10, 10);
        canvas.position(rawData[curFrame][0], rawData[curFrame][1]);
    }

}

