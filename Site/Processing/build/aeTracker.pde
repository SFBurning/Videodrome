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