package webanalyzer.utilities;

import java.util.ArrayList;

import webanalyzer.constants.JobTitles;
import edu.carleton.webcrawler.models.Url;
import edu.carleton.webcrawler.models.UrlSet;

public class SeedConfig {
	
	public static ArrayList<String> titleFilter = new ArrayList<String>();
	
	public static UrlSet getSeeds(){
		UrlSet urlSet = new UrlSet();
		
		/*
		 * Mobile Developer Job Posts
		 */
//		titleFilter.add("mobile developer");
//		titleFilter.add("objective c");
//		titleFilter.add("android");
//		titleFilter.add("mobile");
//		titleFilter.add("ios");
//		titleFilter.add("iphone");
//		
//		for(int i=1; i<11; i++){
//			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=mobile-developer&pg="+i+"&cy=ca");
//			seed.addMatcher("jobview.monster.ca");
//			seed.setTag(JobTitles.MOBILE_DEVELOPER);
//			urlSet.addUrl(seed);
//		}
//		
//		for(int i=1; i<11; i++){
//			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=iso-developer&pg="+i+"&cy=ca");
//			seed.addMatcher("jobview.monster.ca");
//			seed.setTag(JobTitles.MOBILE_DEVELOPER);
//			urlSet.addUrl(seed);
//		}
//		
//		for(int i=1; i<11; i++){
//			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=android&pg="+i+"&cy=ca");
//			seed.addMatcher("jobview.monster.ca");
//			seed.setTag(JobTitles.MOBILE_DEVELOPER);
//			urlSet.addUrl(seed);
//		}
//	
//		for(int i=0; i<11; i++){
//			Url seed = new Url("http://ca.indeed.com/jobs?q=mobile+developer&start="+i+"0");
//			seed.addMatcher("rc/clk");
//			seed.addMatcher("cmp/");
//			seed.setTag(JobTitles.MOBILE_DEVELOPER);
//			urlSet.addUrl(seed);
//		}	
//		for(int i=1; i<11; i++){
//			Url seed = new Url("http://www.workopolis.com/jobsearch/mobile-developer-jobs?ak=mobile+developer&lg=en&pn="+i);
//			seed.addMatcher("jobsearch/job");
//			seed.setTag(JobTitles.MOBILE_DEVELOPER);
//			urlSet.addUrl(seed);
//		}
		
		
		
		
		/*
		 * Web Developer Job Posts
		 */

		titleFilter.add("web");
		titleFilter.add("web developer");
		titleFilter.add("web designer");
		titleFilter.add("website developer");
		
		for(int i=1; i<6; i++){
			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=Web&pg="+i+"&cy=ca");
			seed.addMatcher("jobview.monster.ca");
			seed.setTag(JobTitles.WEB_DEVELOPER);
			urlSet.addUrl(seed);
		}
		
		
		
		
		
		/*
		 * System Analyst
		 */
		titleFilter.add("system");
		titleFilter.add("database");
		titleFilter.add("data");
		
		for(int i=1; i<6; i++){
			Url seed = new Url("http://jobsearch.monster.ca/jobs/?q=system-analyst&pg="+i+"&cy=ca");
			seed.addMatcher("jobview.monster.ca");
			seed.setTag(JobTitles.SYSTEM_ANALYST);
			urlSet.addUrl(seed);
		}
		
		return urlSet;
	}
	
	
	
}
