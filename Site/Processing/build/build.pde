PFont f; // Display Font Object 
String content; // The text to be displayed
AETracker kfData; // Stores AE keyframe data
PVector loc; // Absolute location of the tracker 

void setup() {
	size(1280,720);
	smooth();

	// Initializations
	f = loadFont("Futura-Medium-48.vlw"); // The font
	content = "LIVE\nCOMPOSITED\nTEXT"; // The text to be displayed
	kfData = new AETracker("ParsedAEData.json"); // The keyframe data

}

void draw() {
	textFont(f);
	fill(0);
	//AETracker.calculate();
	loc = AETracker.move();
	pushMatrix();
		translate(loc.x, loc.y);
		text(content, 0, 0);
	popMatrix();
}
