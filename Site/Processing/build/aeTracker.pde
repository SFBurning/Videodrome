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

	void calculate(int fc) {

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

	
	PVector moveRelative() {

		return deltaPos;

	}

	PVector move() {
		return pos; 
	}

}