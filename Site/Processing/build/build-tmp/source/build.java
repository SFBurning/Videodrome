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

float r,g,b;
PFont f; // Display Font Object 
String content; // The text to be displayed
AETracker kfData; // Stores AE keyframe data
PVector loc; // Absolute location of the tracker 

public void setup() {
	size(1280,720);
	smooth();
	frameRate(30);
	// Initializations
	f = loadFont("Futura-Medium-48.vlw"); // The font
	content = "LIVE\nCOMPOSITED\nTEXT"; // The text to be displayed
	kfData = new AETracker("ParsedAEData.json"); // The keyframe data

}

public void draw() {
	loadPixels();
	for(int i = 0; i < width * height; i++) {
		pixels[i] = 0; 
	};
	background(0, 0);
	// Mix up the colors
	r = sin(radians(PApplet.parseFloat(frameCount)));
	r = map(r, -1, 1, 0, 255);
	g = sin(radians(PApplet.parseFloat(frameCount)*1.5f));
	g = map(g, -1, 1, 0, 255);
	b = sin(radians(PApplet.parseFloat(frameCount)*2));
	b = map(b, -1, 1, 0, 255);
	/*
	if(frameCount%15 == 0) {
		println("r: "+r);
		println("g: "+g);
		println("b: "+b);
	}
	*/
	// Draw the text
	kfData.calculate(frameCount);
	loc = kfData.move();
	pushMatrix();
		// If tracker data is not present
		if(loc.x == 0 && loc.y == 0) {
			// Make the text Invisivble 
			fill(0,0);
		} else {
			// Otherwise display the text
			fill(r,g,b);
			}
		textFont(f);
		println("loc.x: "+loc.x);
		translate(loc.x, loc.y);
		text(content, 0, 0);
	popMatrix();
}
// AE Tracker JSON -> Processing 

class AETracker {

	// Data
	JSONArray rawData; //Stores Raw JSON data
	PVector pos; // Stores current position information 
	PVector prevPos; // Stores previous position information
	PVector deltaPos; // Stores incremental differences betwen frames

	// Helper Data
	int startFrame; // Stores the first frame of valid data
	int curFrame; 


	AETracker(String jData) {
		rawData = loadJSONArray("parsedAEData.json");

		//Determine the start frame
		for (int i = 0; i < rawData.size(); i++) {
			if(rawData.getJSONArray(i).getInt(0) != 0 && rawData.getJSONArray(i).getInt(1) != 0) {
				startFrame = i; // If the current frame has data, it is the starframe
				break; // Job done, exit the loop 
			}
		}
		// Set pos to current frame positions
		pos = new PVector(rawData.getJSONArray(startFrame).getInt(0),rawData.getJSONArray(startFrame).getInt(1));
		// Set prevPos to current frame, for now
		prevPos = new PVector(rawData.getJSONArray(startFrame).getInt(0),rawData.getJSONArray(startFrame).getInt(1));
		// Set delta to zero
		deltaPos = new PVector(0, 0);

		// Start on frame 2, TEMPORARY
		curFrame = 2; 
	
	}

	public void calculate(int fc) {

		// Set the previous position and delta
		prevPos.x = rawData.getJSONArray(curFrame-1).getInt(0);
		prevPos.y = rawData.getJSONArray(curFrame-1).getInt(1);
		
		// Set the current position
		pos.x = rawData.getJSONArray(curFrame).getInt(0);
		pos.y = rawData.getJSONArray(curFrame).getInt(1);
		
		// Calculate the difference between current and previous frames
		deltaPos.x = pos.x - prevPos.x;
		deltaPos.y = pos.y - prevPos.y;

		try {
			rawData.getJSONArray(curFrame+1);
			curFrame = curFrame + 1;
		} catch (Exception e) {
			pos.x = 0;
			pos.y = 0; 
		}
	}

	/* 
	This class provides two methods for retrieving position information
	
	moveRelative() provides the change in position between two frames, and can be used without 
	pushMatrix/popMatrix

	move() provides absolute position information, and must be used with push/popMatrix
	*/

	
	public PVector moveRelative() {

		return deltaPos;

	}

	public PVector move() {
		return pos; 
	}

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
