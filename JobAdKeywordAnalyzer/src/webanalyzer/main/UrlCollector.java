package webanalyzer.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.carleton.webcrawler.listener.UrlCollectorOnFinishListener;
import edu.carleton.webcrawler.models.Url;
import edu.carleton.webcrawler.models.UrlSet;

public class UrlCollector {
	private int numOfThreads = 1;
	private int numOfThreadsFinished = 0;
	private int numOfThreadsStarted = 0;
	private UrlSet seedUrlSet;
	private UrlSet parsedUrlSet;
	private UrlCollectorOnFinishListener listener;
	
	public UrlCollector(){
		seedUrlSet = new UrlSet();
		parsedUrlSet = new UrlSet();
	}
	
	public void addSeedUrl(Url url){
		seedUrlSet.addUrl(url);
	}
	
	public void setSeedUrlSet(UrlSet urlSet){
		this.seedUrlSet = urlSet;
	}
	
	public void setNumOfThreads(int n){
		this.numOfThreads = n;
	}
	
	public void setOnFinishListener(UrlCollectorOnFinishListener listener){
		this.listener = listener;
	}
	
	public void start(){
		int totalNum 	= seedUrlSet.size();
		int partNum		= seedUrlSet.size()/numOfThreads;		
		int totalCount	= 0;
		int iterCount	= 0;
		int id 			= 0;
		ArrayList<LinkCollectorThread> threads = new ArrayList<LinkCollectorThread>();
		
		if(partNum == 0)
			partNum = 1;
		
		ArrayList<Url> subSeeds = new ArrayList<Url>();
		while(true){
			/*
			 * If there is no seed, do nothing.
			 */
			if(seedUrlSet.size() == 0){
				System.out.println("There is at least one url to visit.");
				break;
			}
			
			/*
			 * Evenly partition and dispatch the seeds' set to new starting thread(s)
			 */
			if(totalCount == totalNum - 1){
				subSeeds.add(seedUrlSet.getUrl(totalCount));
				threads.add((LinkCollectorThread)new LinkCollectorThread(id, subSeeds, this));
				id++;
				numOfThreadsStarted++;
				break;
			}else if(iterCount == partNum){
				iterCount = 0;
				threads.add((LinkCollectorThread)new LinkCollectorThread(id, subSeeds, this));
				id++;
				numOfThreadsStarted++;
				subSeeds = new ArrayList<Url>();
			}
			subSeeds.add(seedUrlSet.getUrl(totalCount));
			totalCount++;
			iterCount++;
		}
		
		/*
		 * Start all threads
		 */
		for(LinkCollectorThread thread:threads)
			thread.start();
	}
	
	public synchronized void threadFinished(UrlSet urlSet){
		parsedUrlSet.appendSet(urlSet);
		numOfThreadsFinished++;
		
		/*
		 * If all threads are finished, sum up all the LinkSets to the totalLinkSet 
		 */
		if(numOfThreadsFinished == numOfThreadsStarted){
			numOfThreadsFinished = 0;
			numOfThreadsStarted = 0;
			
			if(listener!=null)
				listener.onFinish(parsedUrlSet);
			
			System.out.println("==LinksCollector Is Finished==");
		}
	}
	
	/*
	 * Internal Thread class to do the job of collecting links
	 */
	private class LinkCollectorThread extends Thread{
		int id;
		ArrayList<Url> seedSubSet;
		UrlSet parsedUrlSet = new UrlSet();
		UrlCollector mainThread;
		
		public LinkCollectorThread(int id, ArrayList<Url> seeds, UrlCollector instance){
			this.id = id;
			this.seedSubSet = seeds;
			this.mainThread = instance;
		}
		
		public void run() {
			System.out.println("Start LinkCollectorThread: " + id + " with " + seedSubSet.size() + " seed(s)");
			for(Url seed: seedSubSet){
				try {
					String html = "";
					URL web = new URL(seed.getUrl());
			        BufferedReader in = new BufferedReader(
			        new InputStreamReader(web.openStream()));

			        String inputLine;
			        while ((inputLine = in.readLine()) != null)
			        	html = html + inputLine + "\n";
			        
			        in.close();
			        
			        /*
			         * Use Jsoup to parse extract some data
			         */
			        Document doc = Jsoup.parse(html);
			        Elements links = doc.select("a[href]");
			        for (Element link : links) {
			        	String url = link.attr("href");
			        	
			        	/*
			        	 * Make the URL an integral one
			        	 */
			        	
			        	 if(!url.toLowerCase().contains(web.getHost()) && !url.contains(web.getProtocol()))
				        		url = web.getHost() + url;
			        	 
			        	 if(!url.toLowerCase().contains(web.getProtocol()))
				        		url = web.getProtocol()+"://" + url;
			        	
			        	 
			        	/*
			        	 * Check for the seed has matchers
			        	 * if not, the Collector will collect all the links
			        	 */
			        	
			        	if(seed.getNumOfMatchers() == 0){ //no matchers specified
		        			Url parsedUrl =  new Url(url);
		        			parsedUrl.setTag(seed.getTag());
		        			parsedUrlSet.addUrl(parsedUrl);
			        		
			        	}else{
			        		for(String matcher: seed.getMatchers()){ //check all the matchers
					        	if(url.toLowerCase().contains(matcher)){
				        			Url parsedUrl =  new Url(url);
				        			parsedUrl.setTag(seed.getTag());
				        			parsedUrlSet.addUrl(parsedUrl);
					        	}
				        	}
			        	}
			        }
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			System.out.println("LinkCollectorThread:" + id + " is finished.");
			mainThread.threadFinished(parsedUrlSet);
        }
	}
}
