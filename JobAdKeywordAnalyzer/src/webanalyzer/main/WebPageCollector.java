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

import edu.carleton.webcrawler.listener.WebPageCollectorOnVisitListener;
import edu.carleton.webcrawler.models.Page;
import edu.carleton.webcrawler.models.Url;
import edu.carleton.webcrawler.models.UrlSet;

public class WebPageCollector {
	private int numOfThreads = 1;
	private int numOfThreadsFinished = 0;
	private int numOfThreadsStarted = 0;
	private UrlSet urlSet;
	private WebPageCollectorOnVisitListener listener;
	
	public WebPageCollector(){
		urlSet = new UrlSet();
	}

	public UrlSet getUrlSet() {
		return urlSet;
	}

	public void setUrlSet(UrlSet urlSet) {
		this.urlSet = urlSet;
	}
	
	public void addUrl(Url url){
		urlSet.addUrl(url);
	}
	
	public void setNumOfThreads(int n){
		this.numOfThreads = n;
	}
	
	public void setOnVisitListener(WebPageCollectorOnVisitListener listener){
		this.listener = listener;
	}
	
	
	public void start(){
		int totalNum 	= urlSet.size();
		int partNum		= urlSet.size()/numOfThreads;		
		int totalCount	= 0;
		int iterCount	= 0;
		int id 			= 0;
		ArrayList<WebContentCollectorThread> threads = new ArrayList<WebContentCollectorThread>();
		
		if(partNum == 0)
			partNum = 1;
		
		UrlSet subUrlSet = new UrlSet();
		while(true){
			/*
			 * If there is no a single link, do nothing.
			 */
			if(urlSet.size() == 0){
				System.out.println("There is at least one seed to be collected.");
				break;
			}
			
			
			/*
			 * Evenly partition and dispatch the links set to new starting thread(s)
			 */
			if(totalCount == totalNum - 1){
				numOfThreadsStarted++;
				subUrlSet.addUrl(urlSet.getUrl(totalCount));
				threads.add((WebContentCollectorThread)new WebContentCollectorThread(id, subUrlSet, listener, this));
				id++;
				break;
			}else if(iterCount == partNum){
				numOfThreadsStarted++;
				iterCount = 0;
				threads.add((WebContentCollectorThread)new WebContentCollectorThread(id, subUrlSet, listener, this));
				id++;
				subUrlSet = new UrlSet();
			}
			subUrlSet.addUrl(urlSet.getUrl(totalCount));
			totalCount++;
			iterCount++;
		}
		
		/*
		 * Start all threads
		 */
		for(WebContentCollectorThread thread: threads)
			thread.start();
	}
	
	public synchronized void threadFinished(){
		numOfThreadsFinished++;
		/*
		 * If all threads are finished, sum up all the LinkSets to the totalLinkSet 
		 */
		if(numOfThreadsFinished == numOfThreadsStarted){
			numOfThreadsFinished = 0;
			numOfThreadsStarted = 0;
			System.out.println("==WebContentCollector Is Finished==");
		}
	}
	
	/*
	 * Internal Thread class to do the job of collecting links
	 */
	private class WebContentCollectorThread extends Thread{
		int id;
		UrlSet subUrlSet;
		WebPageCollector mainThread;
		WebPageCollectorOnVisitListener listener;
		
		public WebContentCollectorThread(int id, UrlSet subUrlSet, WebPageCollectorOnVisitListener listener, WebPageCollector mainThread){
			this.id = id;
			this.listener = listener;
			this.subUrlSet = subUrlSet;
			this.mainThread = mainThread;
		}
		
		public void run() {
			System.out.println("=>Start WebContentCollectorThread: " + id + " with " + subUrlSet.size() + " seed(s)");
			
			/*
			 * Visit through all the links in the subLinkSet and parse the source content
			 */
			for(Url url:subUrlSet.getUrls()){
				Page page = new Page();
				
				try {
					String source = "";
					String host = "";
					URL web = new URL(url.getUrl());
					host = web.getHost();
			        BufferedReader in = new BufferedReader(
			        new InputStreamReader(web.openStream()));

			        String inputLine;
			        while ((inputLine = in.readLine()) != null)
			        	source = source + inputLine + "\n";
			        in.close();
			        
			        /*
			         * Use Jsoup to parse extract some data
			         */
			        String textContent = "";
			        Document doc = Jsoup.parse(source);
			        Elements content = doc.select("p");
			        for(Element p : content)
						textContent = textContent + p.text() + "\n";
			        
			        Elements div = doc.select("div");
			        for(Element p : div)
						textContent = textContent + p.text() + "\n";
			        
			        Elements span = doc.select("span");
			        for(Element p : span)
						textContent = textContent + p.text() + "\n";
			        
			        Elements heading1 = doc.select("h1");
					for(Element heading : heading1)
						textContent = textContent + heading.text() + "\n";
					
					Elements heading2 = doc.select("h2");
					for(Element heading : heading2)
						textContent = textContent + heading.text() + "\n";
					
					Elements heading3 = doc.select("h3");
					for(Element heading : heading3)
						textContent = textContent + heading.text() + "\n";

					Elements heading4 = doc.select("h4");
					for(Element heading : heading4)
						textContent = textContent + heading.text() + "\n";
					
					Elements titleElement = doc.select("title");
					String title = "";
					for(Element t : titleElement)
						title = title + t.text() + " ";
					
					
			        
			        /*
			         * When finishing loading the source code, store it in the page to be returned
			         */
			        page.setSource(source);
			        
			        /*
			         * Set the host address to the page
			         */
			        page.setHost(host);
			        
			        /*
			         * Set text content to the page
			         */
			        page.setTextContent(textContent);
			        
			        /*
			         * Set the title
			         */
			        page.setTitle(title);
			        
			        /*
			         * Set URL
			         */
					page.setUrl(url.getUrl());
					
					/*
					 * A tag could be anything, such as type of the page
					 */
					page.setTag(url.getTag());
			        
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				/*
				 * Call back the onVisit listener
				 */
				if(listener!=null)
					listener.visited(page);
			}
			System.out.println("LinkCollectorThread:" + id + " is finished.");
			finish();
		}
		
		public synchronized void finish(){
			mainThread.threadFinished();
		}
	}
	
}
