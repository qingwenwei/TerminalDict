import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Word {
	private String word;
	private String translation;
	private String errorCode;
	private ArrayList<Pair> phonetics;
	private ArrayList<Pair> explainations;
	private ArrayList<Pair> webParaphrase;
	
	public Word(){
		phonetics = new ArrayList<Pair>();
		explainations = new ArrayList<Pair>();
		webParaphrase = new ArrayList<Pair>();
	}
	
	public void addPhonetics(Pair phon){
		this.phonetics.add(phon);
	}
	
	public void addExplaination(Pair expl){
		this.explainations.add(expl);
	}
	
	public void addWebParaphrase(Pair para){
		this.webParaphrase.add(para);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public void print(){
		System.out.println("<" + word + ">");
		
		for(Pair pair : phonetics){
			if(pair.getName().equals("us-phonetic"))
				System.out.println("US: [" + pair.getValue() + "]");
			
			if(pair.getName().equals("uk-phonetic"))
				System.out.println("UK: [" + pair.getValue() + "]");
		}
		
		for(Pair pair : explainations){
			System.out.println(pair.getValue());
		}
		
		HashMap<String, String> phraseMap = new HashMap<String,String>();
		if(webParaphrase.size()!=0){
			System.out.println("\n-==Related Phrases From Web==-");
			for(Pair pair : webParaphrase){
				String key = pair.getName();
				String value = pair.getValue();
				
				if(phraseMap.get(pair.getName())==null){
					phraseMap.put(key, value);
				}else{
					phraseMap.put(key, phraseMap.get(key) + "; " + value);
				}
			}
			
			for(String key : phraseMap.keySet())
				System.out.println(key + ": " + phraseMap.get(key));
		}
	}
	
	public String toXMLElement(boolean web){
		String xml = "\n<item>\n";
		xml+="<word>" + word + "</word>\n";
		for(Pair pair : phonetics)
				xml+="<" + pair.getName() + ">" + pair.getValue() + "</" + pair.getName() + ">\n";
		
		for(Pair pair : explainations)
			xml+="<translation>" + pair.getValue() + "</translation>\n";
		
		//extract web paraphrases
		HashMap<String, String> phraseMap = new HashMap<String,String>();
		if(web==true){
			for(Pair pair : webParaphrase){
				String key = pair.getName();
				String value = pair.getValue();
				
				if(phraseMap.get(pair.getName())==null){
					phraseMap.put(key, value);
				}else{
					phraseMap.put(key, phraseMap.get(key) + "; " + value);
				}
			}
		}
		
		for(String key : phraseMap.keySet()){
			xml+= "<phrase>" + key + "=" + phraseMap.get(key) + "</phrase>\n";
		}
			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		xml+="<date>" + dateFormat.format(date) + "</date>\n";
		xml+="</item>\n";
		return xml;
	}
	
	public String toString(){
		String toStr = "";
		
		toStr += "word: " + word + "\n";
		toStr += "tran: " + translation + "\n";
		toStr += "errc: " + errorCode + "\n";
		
		for(Pair pair : phonetics)
			toStr += "phon: " + pair.getName() + "=" + pair.getValue() + "\n";
		
		for(Pair pair : explainations)
			toStr += "expl: " + pair.getName() + "=" + pair.getValue() + "\n";
		
		for(Pair pair : webParaphrase)
			toStr += "webp: " + pair.getName() + "=" + pair.getValue() + "\n";
		
		return toStr;
	}
	
}
