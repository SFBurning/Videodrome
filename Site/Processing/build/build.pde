PFont f;
String content;
float r,g,b;
PFont f; // Display Font Object 
String content; // The text to be displayed
AETracker kfData; // Stores AE keyframe data
PVector loc; // Absolute location of the tracker 

void setup() {
	size(1280,720);
	smooth();
	frameRate(30);
	f = loadFont("Futura-Medium-150.vlw");
	content = "LASER\nNIPPLE\nPARTY";

	// Initializations
	f = loadFont("Futura-Medium-48.vlw"); // The font
	content = "LIVE\nCOMPOSITED\nTEXT"; // The text to be displayed
	kfData = new AETracker("ParsedAEData.json"); // The keyframe data

}

void draw() {
	loadPixels();
	for(int i = 0; i < width * height; i++) {
		pixels[i] = 0; 
	};
	background(0, )0;
	// Mix up the colors
	r = sin(radians(float(frameCount)));
	r = map(r, -1, 1, 0, 255);
	g = sin(radians(float(frameCount)*1.5));
	g = map(g, -1, 1, 0, 255);
	b = sin(radians(float(frameCount)*2));
	b = map(b, -1, 1, 0, 255);
	/*
	if(frameCount%15 == 0) {
		println("r: "+r);
		println("g: "+g);
		println("b: "+b);
	}
	*/
	// Draw the text
	fill(r,g,b);
	textFont(f, 150);
	text(content, 200, 200);
	textFont(f);
	fill(0);
	//AETracker.calculate();
	loc = AETracker.move();
	pushMatrix();
		translate(loc.x, loc.y);
		text(content, 0, 0);
	popMatrix();
}
