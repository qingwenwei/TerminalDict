import java.util.ArrayList;


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
	
	public void prompt(){
		
	}
	
	public String toXMLElement(){
		String xml = "";
		
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
