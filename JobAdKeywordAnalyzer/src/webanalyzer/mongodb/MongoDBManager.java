package webanalyzer.mongodb;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import webanalyzer.models.JobPostDocument;
import webanalyzer.models.JobPostDocumentCollection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBManager {
	DBCollection coll;
	private static MongoDBManager instance;
	
	public MongoDBManager() throws UnknownHostException{
		MongoClient mongoClient;
		mongoClient = new MongoClient("localhost");
		DB db = mongoClient.getDB( "JobPostDB" );
		coll =	db.getCollection("JobPost");
	}
	
	public static MongoDBManager getInstance() throws UnknownHostException {
		if (instance == null)
			instance = new MongoDBManager();
		return instance;
	}
	
	public boolean addDocument(JobPostDocument doc){	
		JobPostDocument found = findDocumentById(doc.getId());
		if(found == null){
			coll.insert(doc);
			return true;
		}else{
			return false;
		}
	}
	
	public void deleteDocument(int id){
		BasicDBObject query	= new BasicDBObject("id", id);
		coll.remove(query);
	}
	
	public boolean updateDocument(JobPostDocument doc){	
		JobPostDocument found = findDocumentById(doc.getId());
		if(found == null)
			coll.insert(doc);
		else
			coll.update(found, doc);
		return true;
	}
	
	public JobPostDocument findDocumentById(int docId){
		BasicDBObject query		= 	new	BasicDBObject("id", docId);
		DBCursor cursor			=	coll.find(query);
		try{
			DBObject object = cursor.next();
			return new JobPostDocument(object.toMap());
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	public JobPostDocument findDocumentByJobTitle(String title){
		BasicDBObject query		= 	new	BasicDBObject("jobTitle", title);
		DBCursor cursor			=	coll.find(query);
		try{
			DBObject object = cursor.next();
			return new JobPostDocument(object.toMap());
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	public JobPostDocument findDocumentByContent(String content){
		BasicDBObject query		= 	new	BasicDBObject("textContent", content);
		DBCursor cursor			=	coll.find(query);
		try{
			DBObject object = cursor.next();
			return new JobPostDocument(object.toMap());
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	public JobPostDocument findDocumentByTitle(String title){
		BasicDBObject query		= 	new	BasicDBObject("title", title);
		DBCursor cursor			=	coll.find(query);
		try{
			DBObject object = cursor.next();
			return new JobPostDocument(object.toMap());
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	public JobPostDocument findDocumentByUrl(String url){
		BasicDBObject query		= 	new	BasicDBObject("url", url);
		DBCursor cursor			=	coll.find(query);
		try{
			DBObject object = cursor.next();
			JobPostDocument doc = null;
			if(object!=null)
				doc = new JobPostDocument(object.toMap());
			return doc;
		}catch(NoSuchElementException e){
			return null;
		}
	}
	
	public long totalNumOfDocuments(){
		return coll.count();
	}
	
	public JobPostDocumentCollection getCollectionByJobTitle(String title){
		JobPostDocumentCollection docColl = new JobPostDocumentCollection();
		docColl.setJobPostDocuments(new ArrayList<JobPostDocument>());
		
		BasicDBObject query = new BasicDBObject("jobTitle", title);
		DBCursor cursor=coll.find(query);
		
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			docColl.getJobPostDocuments().add(new JobPostDocument(object.toMap()));
		}
		return docColl;
	}
	
	public JobPostDocumentCollection getCollectionByContent(String keyword){
		JobPostDocumentCollection docColl = new JobPostDocumentCollection();
		docColl.setJobPostDocuments(new ArrayList<JobPostDocument>());
		DBCursor cursor=coll.find();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			String textContent = (String) object.get("textContent");
			if(textContent.toLowerCase().contains(keyword))
				docColl.getJobPostDocuments().add(new JobPostDocument(object.toMap()));
			
		}
		return docColl;
	}
	
	public JobPostDocumentCollection getCollectionByTitle(String title){
		JobPostDocumentCollection docColl = new JobPostDocumentCollection();
		docColl.setJobPostDocuments(new ArrayList<JobPostDocument>());
		DBCursor cursor=coll.find();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			String pageTitle = (String) object.get("title");
			if(pageTitle.toLowerCase().contains(title))
				docColl.getJobPostDocuments().add(new JobPostDocument(object.toMap()));
			
		}
		return docColl;
	}
	
	public JobPostDocumentCollection getAllDocuments(){
		JobPostDocumentCollection docColl = new JobPostDocumentCollection();
		docColl.setJobPostDocuments(new ArrayList<JobPostDocument>());
		DBCursor cursor=coll.find();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			docColl.getJobPostDocuments().add(new JobPostDocument(object.toMap()));
		}
		return docColl;
	}
	
	public boolean containsKey(int id){		
		if(findDocumentById(id)!=null)
			return true;
		return false;
	}
	
	
	public static void main(String args[]) throws UnknownHostException{
//		System.out.println(MongoDBManager.getInstance().totalNumOfDocuments());
//		System.out.println(MongoDBManager.getInstance().findDocumentByUrl("www.baid.com"));
//		JobPostDocumentCollection jobCollection = MongoDBManager.getInstance().getCollectionByTitle("toronto");
//		for(JobPostDocument doc:jobCollection.getJobPostDocuments()){
//			System.out.println(doc.getTitle());
//		}
	}
}
