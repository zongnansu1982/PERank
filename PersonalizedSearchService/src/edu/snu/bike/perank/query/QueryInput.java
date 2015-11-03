package edu.snu.bike.perank.query;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryInput {
	private String query;
	private ArrayList<String> preferenceList;
	private HashMap<String,Double> preferenceMap;
	public ArrayList<String> getPreferenceList() {
		return preferenceList;
	}
	public void setPreferenceList(ArrayList<String> preferenceList) {
		this.preferenceList = preferenceList;
	}
	public HashMap<String, Double> getPreferenceMap() {
		return preferenceMap;
	}
	public void setPreferenceMap(HashMap<String, Double> preferenceMap) {
		this.preferenceMap = preferenceMap;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
}
