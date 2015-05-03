package webanalyzer.models;

import java.util.List;

public class JobPostDocumentCollection {
	private List<JobPostDocument> jobPostDocuments;
	
	public List<JobPostDocument> getJobPostDocuments() {
		return jobPostDocuments;
	}

	public void setJobPostDocuments(List<JobPostDocument> documents) {
		this.jobPostDocuments = documents;
	}
}
