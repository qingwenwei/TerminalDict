package webanalyzer.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	int numOfCrawlers = 0;
	ArrayList<String> seeds;
	
	public Crawler(){
		seeds = new ArrayList<String>();
	}
	
	
	public void start(){
		for(String seed: seeds){
			try {
				String html = "";
				URL oracle = new URL(seed);
//				System.out.println("poop"+oracle.g);
		        BufferedReader in = new BufferedReader(
		        new InputStreamReader(oracle.openStream()));

		        String inputLine;
		        while ((inputLine = in.readLine()) != null)
		        	html = html + inputLine + "\n";
		        
		        in.close();
		        
		        Document doc = Jsoup.parse(html);
		        Elements links = doc.select("a[href]");
		        for (Element link : links) {
//		        	if(link.attr("abs:href").toLowerCase().contains("jobview.monster.ca"))
		        		System.out.println(link.attr("href"));
		        }
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addSeed(String url){
		seeds.add(url);
	}
	
	public static void main(String[] args){
		Crawler crawler = new Crawler();
		crawler.addSeed("http://www.workopolis.com/jobsearch/android-jobs");
		crawler.start();
		
	}
}
