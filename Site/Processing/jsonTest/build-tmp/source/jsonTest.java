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

public class jsonTest extends PApplet {

// JSON Test

JSONArray rawData;

public void setup() {
	rawData = loadJSONArray("parsedAEData.json");
	for(int i = 1; i < rawData.size(); i++) {
		println(rawData.getJSONArray(i).getInt(0));
	}
}

public void draw() {
	
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "jsonTest" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
