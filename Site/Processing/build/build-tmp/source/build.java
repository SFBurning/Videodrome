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

PFont f; // Display Font Object 
String content; // The text to be displayed
AETracker kfData; // Stores AE keyframe data
PVector loc; // Absolute location of the tracker 

public void setup() {
	size(1280,720);
	smooth();

	// Initializations
	f = loadFont("Futura-Medium-48.vlw"); // The font
	content = "LIVE\nCOMPOSITED\nTEXT"; // The text to be displayed
	kfData = new AETracker("ParsedAEData.json"); // The keyframe data

}

public void draw() {
	textFont(f);
	fill(0);
	//AETracker.calculate();
	loc = AETracker.move();
	pushMatrix();
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
		rawData = loadJSONArray(jData);

		//Determine the start frame
		for (int i = 0; i < rawData.length; i++) {
			if(rawData[i][0] != 0 && rawData[i][1] != 0) {
				startFrame = i; // If the current frame has data, it is the starframe
				break; // Job done, exit the loop 
			}
		}
		// Set pos to current frame positions
		pos = new PVector(rawData[startFrame][0],rawData[startFrame][1]);
		// Set prevPos to current frame, for now
		prevPos = new PVector(rawData[startFrame][0],rawData[startFrame][1]);
		// Set delta to zero
		deltaPos = new PVector(0, 0);
	
	}

	public static void calculate() {

		// Set the previous position and delta
		if(frameCount > startFrame) {
			prevPos.x = rawData[frame-1][0];
			prevPos.y = rawData[frame-1][1];
		}
		// Set the current position
		if (frameCount >= startFrame) {
			pos.x = rawData[frame][0];
			pos.y = rawData[frame][1];
		}

		deltaPos.x = pos.x - prevPos.x;
		deltaPos.y = pos.y - prevPos.y;
	}

	public static PVector moveRelative() {

		return deltaPos;

	}

	public static PVector move() {
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
