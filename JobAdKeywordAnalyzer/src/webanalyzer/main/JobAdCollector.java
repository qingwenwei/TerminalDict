package webanalyzer.main;

import java.net.UnknownHostException;
import java.util.StringTokenizer;

import webanalyzer.models.JobPostDocument;
import webanalyzer.mongodb.MongoDBManager;
import webanalyzer.utilities.SeedConfig;
import webanalyzer.utilities.TextAnalyzer;
import edu.carleton.webcrawler.listener.UrlCollectorOnFinishListener;
import edu.carleton.webcrawler.listener.WebPageCollectorOnVisitListener;
import edu.carleton.webcrawler.main.UrlCollector;
import edu.carleton.webcrawler.main.WebPageCollector;
import edu.carleton.webcrawler.models.Page;
import edu.carleton.webcrawler.models.UrlSet;

public class JobAdCollector {
	private int numDocCount = 0;
	
	public void start() throws UnknownHostException{
		numDocCount = (int) MongoDBManager.getInstance().totalNumOfDocuments();
		UrlCollector collector = new UrlCollector();
		collector.setSeedUrlSet(SeedConfig.getSeeds());
		collector.setNumOfThreads(20);
		collector.setOnFinishListener(LinkCollectorOnFinishListener());
		collector.start();
	}
	
	
	public UrlCollectorOnFinishListener LinkCollectorOnFinishListener(){
		return new UrlCollectorOnFinishListener(){
			@Override
			public void onFinish(UrlSet urlSet) {
				System.out.println("==URL Collecting Finished==");
				urlSet.printUrls();
				
				WebPageCollector webCollector = new WebPageCollector();
				webCollector.setNumOfThreads(urlSet.size()/5);
				webCollector.setUrlSet(urlSet);
				webCollector.setOnVisitListener(WebPageCollectorOnVisitListener());
				webCollector.start();
			}
		};
	}
	
	
	public  WebPageCollectorOnVisitListener WebPageCollectorOnVisitListener(){
		return new WebPageCollectorOnVisitListener(){
			@Override
			public synchronized void visited(Page page) {
				/*
				 * Filter the pages by checking if its title contains certain keywords
				 */	
				int i = 0;
				for(String keyword:SeedConfig.titleFilter)
					if(!page.getTitle().toLowerCase().contains(keyword))
						i++;
				if(SeedConfig.titleFilter.size() == i)
					return;
				
				
				
				System.out.println("visited:"+getNumDocCount()+" title:"+page.getTitle());
				
				JobPostDocument jobPost = new JobPostDocument();
				jobPost.setId(getNumDocCount());
				jobPost.setUrl(page.getUrl());
				jobPost.setTitle(page.getTitle());
				jobPost.setHost(page.getHost());
				jobPost.setJobTitle(page.getTag());
				jobPost.setSource(page.getSource());
				jobPost.setTextContent(page.getTextContent());
				
				/*
				 * Go through the whole page text content
				 * Add job skill tags to the job post document
				 */
				StringTokenizer tokenizer = new StringTokenizer(page.getTextContent());
				while(tokenizer.hasMoreElements()){
					String keyword = tokenizer.nextToken();
					keyword = keyword.replaceAll(",|;$", "");
					
					/*
					 * Token keyword containing "/"
					 */
					if(keyword.contains("/")){
						StringTokenizer tokenizer2 = new StringTokenizer(keyword,"/");
						while(tokenizer2.hasMoreElements()){
							String token = tokenizer2.nextToken();
							String good = TextAnalyzer.standardizeKeyword(token);
							if(good!=null && !jobPost.getSkillTags().contains(good))
								jobPost.getSkillTags().add(good);
//								System.out.println(good);
						}
					}
										
					String good = TextAnalyzer.standardizeKeyword(keyword);
					if(good!=null && !jobPost.getSkillTags().contains(good))
						jobPost.getSkillTags().add(good);
//						System.out.println(good);
				}
				
				/*
				 * Add the job post to MongoDB
				 */
				try {
					if(MongoDBManager.getInstance().findDocumentByUrl(page.getUrl()) == null)
						MongoDBManager.getInstance().addDocument(jobPost);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
				/*
				 * Increase the number of doc as document id
				 */
				increaseNumDocCount();
			}
		};
	}
	
	public int getNumDocCount(){
		return numDocCount;
	}
	
	public synchronized void increaseNumDocCount(){
		numDocCount++;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]) throws UnknownHostException{
		JobAdCollector traveller = new JobAdCollector();
		traveller.start();

//		LinkSet linkSet = new LinkSet();
//		linkSet.addLink("http://jobview.monster.ca/Design-Manufacturing-Engineer-Job-North-York-ON-CA-132249471.aspx");
//		
//		WebCollector webCollector = new WebCollector();
//		webCollector.setNumOfThreads(1);
//		webCollector.setLinkSet(linkSet);
//		webCollector.setOnVisitListener(traveller.WebContentCollectorOnVisitListener());
//		webCollector.start();
	}
}
