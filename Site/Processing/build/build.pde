PFont f;
String content;

void setup() {
	size(1280,720);
	smooth();
	f = loadFont("Futura-Medium-48.vlw");
	content = "LASER\nNIPPLE\nPARTY";

}

void draw() {
	textFont(f);
	fill(0);
	text(content, 0, 0);
}
