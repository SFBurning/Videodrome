// Container module for manipulating tracker JSON
var tracker;

// Load JSON
function preload(){
	tracker = new TrackerData;
	tracker.loadJSONData(loadJSON("Processing/build/data/parsedAEData.json"));
}

function setup(){
	createCanvas(1280,720);
	background(0); 

}

function draw(){
	tracker.
}

// CLASSES
var TrackerData = (function(){
	// PRIVATE 
	var jsonData;
	var absX, absY;
	// PUBLIC
	return {
		loadJSONData: function(newData) {
			jsonData = newData; 
		},
		getFrameData: function(frameNumber) {
			if(jsonData == null) {
				absX = null;
				absY = null; 
			}
			try {
				absX = jsonData[frameNumber][0];
				absY = jsonData[frameNumber][1];
			} catch(e) {
				absX = null;
				absY = null; 
			}
		},

	}
})();