package Code;

import Ui.*;

import org.json.JSONObject;

public class Json {
	private JSONObject JsonData;

	public String Get(String key) {
		return JsonData.get(key).toString();

	}

	public JSONObject getJsonData() {
		return JsonData;
	}

	public void setJsonData(String jsonString) {
		JsonData = new JSONObject(jsonString);

	}

	public String toString() {
		return JsonData.toString();
	}

	public String Get(int numero) {
		if (numero >= JsonData.length()) {
			return null;
		}
		return (Get((String) JsonData.keySet().toArray()[numero])).toString();
	}
}
