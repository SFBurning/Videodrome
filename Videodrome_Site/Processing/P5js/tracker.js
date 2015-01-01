var rawData;
var curFrame;
function preload(){
    rawData = loadJSON("parsedAEData.json");
}

function setup() {
    createCanvas(1280, 720);
    frameRate(24);
    background(0, 0);
    curFrame = 0;
}

function draw() {
    clear();
    if(rawData[curFrame][0] != null && rawData[curFrame][1] != 0) {
        noStroke();
        fill(255,0,0);
        ellipseMode(CENTER);
        ellipse(rawData[curFrame][0], rawData[curFrame][1], 50, 50);
    }
    curFrame++;
}