package webanalyzer.models;


import java.util.ArrayList;

public class UrlSet {
	ArrayList<Url> urls = new ArrayList<Url>();

	public ArrayList<Url> getUrls() {
		return urls;
	}

	public void setUrls(ArrayList<Url> urls) {
		this.urls = urls;
	}
	
	public void addUrl(Url url){
		this.urls.add(url);
	}
	
	public Url getUrl(int index){
		return this.urls.get(index);
	}
	
	public void removeUrl(Url url){
		this.urls.add(url);
	}
	
	public void appendSet(UrlSet urlSet){
		for(Url url:urlSet.getUrls())
			urls.add(url);
	}
	
	public int size(){
		return this.urls.size();
	}
	
	public void printUrls(){
		for(Url url:urls)
			System.out.println("Tag:"+url.getTag()+"      URL:"+url.getUrl());
			
		
	}

}
