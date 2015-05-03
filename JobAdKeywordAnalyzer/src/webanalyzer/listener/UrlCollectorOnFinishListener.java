package webanalyzer.listener;

import edu.carleton.webcrawler.models.UrlSet;



public interface UrlCollectorOnFinishListener {
	public void onFinish(UrlSet linkSet);
}
