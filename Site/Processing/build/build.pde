PFont f;
String content;
float r,g,b;

void setup() {
	size(1280,720);
	smooth();
	frameRate(30);
	f = loadFont("Futura-Medium-150.vlw");
	content = "LASER\nNIPPLE\nPARTY";

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
}
