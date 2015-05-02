import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class Dictionary {
	
	public static void printResult(HashMap<String,ArrayList<Entry<String,String>>> wordInfo){
		
		//word explanations
		System.out.println("=========Translation=========");	
		ArrayList<Entry<String,String>> expls = wordInfo.get("expl");
		for(Entry<String,String> entry:expls){
			System.out.println(entry.getValue());
		}
		
		
		//phonetics
		System.out.println("==========Phonetics==========");		
		ArrayList<Entry<String,String>> phons = wordInfo.get("phon");
		for(Entry<String,String> entry:phons){
			String key = entry.getKey();
			String value = entry.getValue();
			if(key.equalsIgnoreCase("us-phonetic"))
				System.out.println("US Phon:" + "["+value+"]");
			
			if(key.equalsIgnoreCase("uk-phonetic"))
				System.out.println("UK Phon:" + "["+value+"]");
			
			if(key.equalsIgnoreCase("phonetic"))
				System.out.println("Phon:" + "["+value+"]");
			
		}
		
		
		//web relevances
		ArrayList<Entry<String,String>> relvs = wordInfo.get("relv");
		HashMap<String, String> explMap = new HashMap<String, String>();
		for(Entry<String,String> entry:relvs){
			String key = entry.getKey();
			String value = entry.getValue();
			if(explMap.containsKey(key)){
				String appendValue = explMap.get(key);
				appendValue += value + "; ";
				explMap.put(key, appendValue);
			}else{
				explMap.put(key, value+ "; ");
			}
		}
		
		System.out.println("========Web Relevances========");
		Iterator<Entry<String,String>> itr = explMap.entrySet().iterator();
		while(itr.hasNext()){
			Entry<String, String> entry = itr.next();
			String key = entry.getKey();
			String value = entry.getValue().substring(0, entry.getValue().length()-1); //remove the empty space at the end
			System.out.println(key + ":[" + value + "]");
		}
		
	}
	
	
	
	public static void main(String args[]){
		System.out.println(args.length);
		
		//no arguments
		if(args.length == 1){
			System.out.println(args[0]);
			
			HashMap<String,ArrayList<Entry<String,String>>> wordInfo = new IOManager().getURLJsonData(args[0]);
			
			printResult(wordInfo);
		}
		
	}
}
