package webanalyzer.test;


import edu.carleton.webcrawler.listener.UrlCollectorOnFinishListener;
import edu.carleton.webcrawler.main.UrlCollector;
import edu.carleton.webcrawler.models.UrlSet;

public class Tester {
	public static void main(String[] args){
		UrlSet links;
		UrlCollector collector = new UrlCollector();
		collector.setNumOfThreads(5);
		UrlCollectorOnFinishListener listener = new UrlCollectorOnFinishListener(){
			@Override
			public void onFinish(UrlSet linkSet) {
				linkSet.printUrls();
				
				
				
//				WebCollector webCollector = new WebCollector();
//				webCollector.setUrlSet(linkSet);
//				webCollector.setNumOfThreads(1);
//				webCollector.setOnVisitListener(new WebContentCollectorOnVisitListener() {
//					@Override
//					public void visited(Page page) {
////						StringTokenizer tokenizer = new StringTokenizer(page.getTextContent());
////						while(tokenizer.hasMoreElements()){
////							String keyword = tokenizer.nextToken();
////							System.out.println(keyword);
////						}
//						System.out.println(page.getTag()+"   "+page.getUrl());
//					}
//				});
//				webCollector.start();
			}
		};
		collector.setOnFinishListener(listener);
		
//		for(int i=1; i<10; i++){
//			Seed seed = new Seed("http://jobsearch.monster.ca/jobs/?q=web-developer&pg="+i+"&cy=ca");
//			seed.addMatcher("jobview.monster.ca");
//			collector.addSeed(seed);
//		}
		
//		for(int i=1; i<2; i++){
//			Seed seed = new Seed("http://www.workopolis.com/jobsearch/android-jobs");
//			seed.addMatcher("jobsearch/job");
//			collector.addSeed(seed);
//		}
		
//		for(int i=0; i<6; i++){
//			Url seed = new Url("http://ca.indeed.com/jobs?q=mobile+developer&start="+i+"0");
//			seed.addMatcher("rc/clk");
//			seed.addMatcher("cmp/");
//			seed.setTag("Web Developer");
//			collector.addSeedUrl(seed);
//		}
		
//		for(int i=1; i<6; i++){
//			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=mobile-developer&pg="+i+"&cy=ca");
//			seed.addMatcher("jobview.monster.ca");
//			seed.setTag("Mobile Developer");
//			collector.addSeedUrl(seed);
//		}

//		for(int i=1; i<2; i++){
//			Seed seed = new Seed("http://www.jobsearch.ca/web-jobs");
//			seed.addMatcher("goto.php?url=/a/job-details");
//			collector.addSeed(seed);
//		}
//
//		for(int i=1; i<2; i++){
//			Seed seed = new Seed("http://www.simplyhired.ca/a/jobs/list/q-android/fln-any/pn-1");
//			seed.addMatcher("job-details/view");
//			collector.addSeed(seed);
//		}
		
		collector.start();	
		
		
	
//		WebCollector webCollector = new WebCollector();
//		LinkSet linkSet = new LinkSet();
//		linkSet.addLink("http://ca.indeed.com/rc/clk?jk=a8f092754bcb1d69");
//		
//		webCollector.setLinkSet(linkSet);
//		webCollector.setNumOfThreads(1);
//		webCollector.setOnVisitListener(new WebContentCollectorOnVisitListener() {
//			@Override
//			public void visited(Page page) {
////				StringTokenizer tokenizer = new StringTokenizer(page.getTextContent());
////				while(tokenizer.hasMoreElements()){
////					String keyword = tokenizer.nextToken();
////					System.out.println(keyword);
////				}
//				System.out.println(page.getTextContent());
//			}
//		});
//		webCollector.start();
		

		
		
	}
}
