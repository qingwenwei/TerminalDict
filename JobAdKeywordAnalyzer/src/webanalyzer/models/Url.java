package webanalyzer.models;


import java.util.ArrayList;

public class Url {
	String url;
	String tag;
	ArrayList<String> matchers;
	
	public Url(String url) {
		super();
		this.url = url;
		matchers = new ArrayList<String>();
	}
	
	public Url(String url, ArrayList<String> matchers) {
		super();
		this.url = url;
		this.matchers = matchers;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<String> getMatchers() {
		return matchers;
	}

	public void setMatchers(ArrayList<String> matchers) {
		this.matchers = matchers;
	}
	
	public void addMatcher(String matcher){
		matchers.add(matcher);
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getNumOfMatchers(){
		return matchers.size();
	}
	
}
