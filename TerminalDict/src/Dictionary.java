import java.io.IOException;
import java.util.Scanner;


public class Dictionary {
	private static Scanner input;

	public static void main(String args[]){
		Word word = null;
		
		//one argument
		if(args.length == 1){
			String argu1 = args[0];
			if(handleCommand(argu1))
				return; 
			
			word = IOManager.getInstance().getWordData(argu1, false);
		}
		
		//more than one arguments
		if(args.length > 1){
			String lastArg = args[args.length-1];
			if(lastArg.equalsIgnoreCase("-p")){
				String query = buildQuery(args, args.length-1);
				word = IOManager.getInstance().getWordData(query, true);
			}else{
				String query = buildQuery(args, args.length);
				word = IOManager.getInstance().getWordData(query, false);
			}
		}
		
		word.print();
		
		/*
		 * prompt
		 */
		System.out.println("\nDo you want to save this word to wordbook? [Y/N]");
		input = new Scanner(System.in);  
        String choice = input.next();
        
        if(choice.equalsIgnoreCase("y")){
        		try {
					new WordbookManager().addWord(word);
				} catch (IOException e) {
					e.printStackTrace();
				}
        }else{
        		System.out.println("[Ignored] This word is ignored.");
        }
	}
	
	private static String buildQuery(String args[], int end){
		String query = "";
		for(int i=0; i<end; i++){
			query+=args[i]+" ";
		}
		query = query.substring(0, query.length()-1);
		query = query.replaceAll(" ", "%20");
		return query;
	}
	
	private static boolean handleCommand(String cmd){
		switch (cmd){
			case "-which": 
				System.out.println(new WordbookManager().getRootDir());
				return true;
				
			case "-howmany": 
				new WordbookManager().numOfWord();
				return true;
				
			case "-about": 
				System.out.println("Qingwen Wei (2015)");
				return true;
		}
		return false;
	}
	
}
