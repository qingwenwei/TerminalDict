package webanalyzer.listener;


import edu.carleton.webcrawler.models.Page;

public interface WebPageCollectorOnVisitListener {
	public void visited(Page page);
}
