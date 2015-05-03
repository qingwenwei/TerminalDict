package webanalyzer.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

public class JobPostDocument implements DBObject{
	private int id;
	private String url;
	private String host;
	private String title;
	private String source;
	private String textContent;
	private String jobTitle;
	private ArrayList<String> skillTags;
	
	public JobPostDocument(){
		skillTags = new ArrayList<String>();
	}
	public JobPostDocument(Map<?, ?> map) {
		super();
		this.id = (Integer) map.get("id");
		this.url = (String) map.get("url");
		this.host = (String) map.get("host");
		this.title = (String) map.get("title");
		this.source = (String) map.get("source");
		this.textContent = (String) map.get("textContent");
		this.jobTitle = (String) map.get("jobTitle");
		this.skillTags = (ArrayList<String>) map.get("skillTags");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public ArrayList<String> getSkillTags() {
		return skillTags;
	}
	public void setSkillTags(ArrayList<String> skillTags) {
		this.skillTags = skillTags;
	}
	
	@Override
	public boolean containsField(String field) {
		if(field.equalsIgnoreCase("id")){
			return true;
		}else if(field.equalsIgnoreCase("url")){
			return true;
		}else if(field.equalsIgnoreCase("host")){
			return true;
		}else if(field.equalsIgnoreCase("source")){
			return true;
		}else if(field.equalsIgnoreCase("textContent")){
			return true;
		}else if(field.equalsIgnoreCase("jobTitle")){
			return true;
		}else if(field.equalsIgnoreCase("skillTags")){
			return true;
		}else if(field.equalsIgnoreCase("title")){
			return true;
		}
		return false;
	}
	@Override
	public boolean containsKey(String field) {
		if(field.equalsIgnoreCase("id")){
			return true;
		}else if(field.equalsIgnoreCase("url")){
			return true;
		}else if(field.equalsIgnoreCase("host")){
			return true;
		}else if(field.equalsIgnoreCase("source")){
			return true;
		}else if(field.equalsIgnoreCase("textContent")){
			return true;
		}else if(field.equalsIgnoreCase("jobTitle")){
			return true;
		}else if(field.equalsIgnoreCase("skillTags")){
			return true;
		}else if(field.equalsIgnoreCase("title")){
			return true;
		}
		return false;
	}
	@Override
	public Object get(String field) {
		if(field.equalsIgnoreCase("id")){
			return getId();
		}else if(field.equalsIgnoreCase("url")){
			return getUrl();
		}else if(field.equalsIgnoreCase("host")){
			return getHost();
		}else if(field.equalsIgnoreCase("source")){
			return getSource();
		}else if(field.equalsIgnoreCase("textContent")){
			return getTextContent();
		}else if(field.equalsIgnoreCase("jobTitle")){
			return getJobTitle();
		}else if(field.equalsIgnoreCase("skillTags")){
			return getSkillTags();
		}else if(field.equalsIgnoreCase("title")){
			return getTitle();
		}
		
		return null;
	}
	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("url");
		set.add("host");
		set.add("source");
		set.add("textContent");
		set.add("jobTitle");
		set.add("skillTags");
		set.add("title");
		return set;
	}
	@Override
	public Object put(String field, Object object) {
		if(field.equalsIgnoreCase("id")){
			setId((Integer) object);
			return getId();
		}else if(field.equalsIgnoreCase("url")){
			setUrl((String)object);
			return getUrl();
		}else if(field.equalsIgnoreCase("host")){
			setHost((String)object);
			return getHost();
		}else if(field.equalsIgnoreCase("source")){
			setSource((String)object);
			return getSource();
		}else if(field.equalsIgnoreCase("textContent")){
			setTextContent((String)object);
			return getTextContent();
		}else if(field.equalsIgnoreCase("jobTitle")){
			setJobTitle((String)object);
			return getJobTitle();
		}else if(field.equalsIgnoreCase("skillTags")){
			setSkillTags((ArrayList<String>)object);
			return getSkillTags();
		}else if(field.equalsIgnoreCase("title")){
			setTitle((String)object);
			return getTitle();
		}
		return null;
	}
	@Override
	public void putAll(BSONObject arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object removeField(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", getId());
		map.put("url", getUrl());
		map.put("host", getHost());
		map.put("source", getSource());
		map.put("textContent", getTextContent());
		map.put("jobTitle", getJobTitle());
		map.put("skillTags", getSkillTags());
		map.put("title", getTitle());
		return map;
	}
	@Override
	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void markAsPartialObject() {
		// TODO Auto-generated method stub
		
	}
}
