import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IOManager {
	private String KEY = "844913748";
	private String KEY_FROM = "Weiqingwen";
	private String URL = "http://fanyi.youdao.com/openapi.do?keyfrom=%s&key=%s&type=data&doctype=json&version=1.1&q=%s";
	private static IOManager instance;
	
	public static IOManager getInstance(){
		if(instance == null){
			instance = new IOManager();
		}
		return instance;
	}
	
	public HashMap<String,ArrayList<Entry<String,String>>> getURLJsonData(String word){
		//the HashMap to be returned
		HashMap<String,ArrayList<Entry<String,String>>> wordInfo = new HashMap<String,ArrayList<Entry<String,String>>>();
		wordInfo.put("phon", new ArrayList<Entry<String,String>>());
		wordInfo.put("expl", new ArrayList<Entry<String,String>>());
		wordInfo.put("relv", new ArrayList<Entry<String,String>>());
		
		//construct the URL
		String callURL = String.format(URL,KEY_FROM,KEY,word);
		System.out.println(callURL);
		
		try {
			URL url = new URL(callURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			//extract the JSON data
			JsonParser parser = new JsonParser();
			JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent())); 
			JsonObject rootObject = root.getAsJsonObject(); 
			
			//extract the "basic" JsonObject
			Set<Entry<String,JsonElement>> basicObjectSet = rootObject.get("basic").getAsJsonObject().entrySet();
			Iterator<Entry<String,JsonElement>> basicObjectItr = basicObjectSet.iterator();
			
			String value = null;
			Entry<String,String> e = null;
			while (basicObjectItr.hasNext()){
				Entry<String,JsonElement> entry = basicObjectItr.next();
				String keyValue = entry.getKey();
				
				switch(keyValue){
					case("us-phonetic"):
						value = entry.getValue().toString().replaceAll("\"", "");
						e = new AbstractMap.SimpleEntry<String, String>("us-phonetic",value);
						wordInfo.get("phon").add(e);
						break;
					
					case("phonetic"):
						value = entry.getValue().toString().replaceAll("\"", "");
						e = new AbstractMap.SimpleEntry<String, String>("phonetic",value);
						wordInfo.get("phon").add(e);
						break;
					
					case("uk-phonetic"):
						value = entry.getValue().toString().replaceAll("\"", "");
						e = new AbstractMap.SimpleEntry<String, String>("uk-phonetic",value);
						wordInfo.get("phon").add(e);
						break;
					
					case("explains"):
						for(JsonElement element:entry.getValue().getAsJsonArray()){
							value = element.toString().replaceAll("\"", "");
							e = new AbstractMap.SimpleEntry<String, String>("explain",value);
							wordInfo.get("expl").add(e);
						}
						break;
				}
			}
			
			//extract the "web" JsonArray
			JsonArray webArray = rootObject.get("web").getAsJsonArray();
			for(JsonElement element:webArray){
				String key = element.getAsJsonObject().get("key").toString().replaceAll("\"", "");
				JsonArray valueArray = element.getAsJsonObject().get("value").getAsJsonArray();
				
				for(JsonElement v:valueArray){
					value = v.toString().replaceAll("\"", "");
					e = new AbstractMap.SimpleEntry<String, String>(key,value);
					wordInfo.get("relv").add(e);
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordInfo;
	}
	
	
	public static void main(String args[]){
		HashMap<String, ArrayList<Entry<String, String>>> wordInfo = IOManager.getInstance().getURLJsonData("china");
		
		for(Entry<String, String> e:wordInfo.get("relv")){
			System.out.println(e);
		}
		
		

	}
}
