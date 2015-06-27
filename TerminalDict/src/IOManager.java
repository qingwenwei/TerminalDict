import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	public Word getWordData(String word, boolean web){
		//word to be returned
		Word newWord = new Word();
		newWord.setWord(word.replace("%20", " "));
		
		//construct the URL
		String callURL = String.format(URL,KEY_FROM,KEY,word);
//		System.out.println(callURL);
		
		try {
			URL url = new URL(callURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			//extract the JSON data
			JsonParser parser = new JsonParser();
			JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent())); 
			JsonObject rootObject = root.getAsJsonObject(); 
			
			//extract the "translation" JSON object
			newWord.setTranslation(rootObject.get("translation").getAsString());
			
			//extract the "errorCode" JSON object
			newWord.setErrorCode(rootObject.get("errorCode").getAsString());
			
			//extract the "basic" JsonObject
			Set<Entry<String,JsonElement>> basicObjectSet = rootObject.get("basic").getAsJsonObject().entrySet();
			Iterator<Entry<String,JsonElement>> basicObjectItr = basicObjectSet.iterator();
			while (basicObjectItr.hasNext()){
				Entry<String,JsonElement> entry = basicObjectItr.next();
				String keyValue = entry.getKey();
					
				if(keyValue.equals("explains")){
					for(JsonElement element:entry.getValue().getAsJsonArray()){
						Pair explaination = new Pair();
						explaination.setName(keyValue);
						explaination.setValue(element.getAsString().replaceAll("\"", ""));
						newWord.addExplaination(explaination);
					}
						
				}else{ // phonetics
					Pair phonetics = new Pair();
					phonetics.setName(keyValue);
					phonetics.setValue(entry.getValue().getAsString().replaceAll("\"", ""));
					newWord.addPhonetics(phonetics);
				}
			}
			
			//return the new word if the web phrases were not requested
			if(web==false)
				return newWord;
			
			//extract the "web" JsonArray
			JsonArray webArray = rootObject.get("web").getAsJsonArray();
			for(JsonElement element:webArray){
				String key = element.getAsJsonObject().get("key").getAsString().replaceAll("\"", "");
				JsonArray valueArray = element.getAsJsonObject().get("value").getAsJsonArray();
				
				for(JsonElement v : valueArray){
					Pair paraphrase = new Pair();
					paraphrase.setName(key);
					paraphrase.setValue(v.getAsString().replaceAll("\"", ""));
					newWord.addWebParaphrase(paraphrase);
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return newWord;
	}
	
	
	public static void main(String args[]) throws IOException{
		Word word = IOManager.getInstance().getWordData("air china", false);
		System.out.println(word.toXMLElement());
		word.print();
		
		
		
//		WordbookManager wb = new WordbookManager();
//		wb.addWord(word);
	}

}
