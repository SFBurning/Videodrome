// JSON Test

JSONArray rawData;

void setup() {
	rawData = loadJSONArray("parsedAEData.json");
	for(int i = 1; i < rawData.size(); i++) {
		println(rawData.getJSONArray(i).getInt(0));
	}
}

void draw() {
	
}