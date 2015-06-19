import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Dictionary {
	public static void main(String args[]){
		System.out.println(args.length);
		
		//word without arguments
		if(args.length == 1){
			System.out.println(args[0]);
			
			HashMap<String,ArrayList<Entry<String,String>>> wordInfo = new IOManager().getURLJsonData(args[0]);
			
			PromptManager.printResult(wordInfo);
		}
		
		if(args.length == 2){
			//nothing here
		}
	}
	
	
}
