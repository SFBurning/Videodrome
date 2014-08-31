import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {

PFont f;
String content;
float r,g,b;

public void setup() {
	size(1280,720);
	smooth();
	frameRate(30);
	f = loadFont("Futura-Medium-150.vlw");
	content = "LASER\nNIPPLE\nPARTY";

}

public void draw() {
	// Mix up the colors
	r = sin(radians(PApplet.parseFloat(frameCount)));
	r = map(r, -1, 1, 0, 255);
	g = sin(radians(PApplet.parseFloat(frameCount)*1.5f));
	g = map(g, -1, 1, 0, 255);
	b = sin(radians(PApplet.parseFloat(frameCount)*2));
	b = map(b, -1, 1, 0, 255);
	if(frameCount%15 == 0) {
		println("r: "+r);
		println("g: "+g);
		println("b: "+b);
	}
	// Draw the text
	fill(r,g,b);
	textFont(f, 150);
	text(content, 200, 200);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
