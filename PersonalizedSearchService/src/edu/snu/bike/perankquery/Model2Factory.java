package edu.snu.bike.perankquery;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;

import edu.snu.bike.perank.bean.SearchBean;

public class Model2Factory implements Searcher{
private String index;
	
	public Model2Factory(String index){
	this.index	= index;
		
	}

public Integer getNumberOfHits(QueryInput input) throws Exception {
	// TODO Auto-generated method stub
	int total=0;
	System.out.println("Preference Search Use Default Weights start...");
	HashMap<String,SearchBean> result= new HashMap<String, SearchBean>();
	String standarquery = input.getQuery().toLowerCase();
	HashMap<String,SearchBean> searchbeans = new HashMap<String,SearchBean>();
	

	IndexSearcher[] searchers = new IndexSearcher[new File(index)
			.listFiles().length];
	IndexReader[] readers = new IndexReader[new File(index)
			.listFiles().length];
	IndexSearcher searcher = null;
	IndexReader reader = null;
	int indexFileID = 0;
	for (File indexFile : new File(index).listFiles()) {
		FSDirectory dir = FSDirectory.open(indexFile);
		searcher = new IndexSearcher(dir);
		searchers[indexFileID] = searcher;

		indexFileID++;

	}

	MultiSearcher multisearcher = new MultiSearcher(searchers);

	ScoreDoc[] results = multisearcher.search(QueryModel.getWeightAutoSignedPreferenceQuery(standarquery, input.getPreferenceList())
			,
			multisearcher.maxDoc()).scoreDocs;
	
	System.out.println("Number of hits: " + results.length);
	if (results.length > 0) {
		int docID = 1;
		for (final ScoreDoc doc : results) {

			Double score = (double) doc.score;
			String vector = multisearcher.doc(doc.doc).get("Vector").trim();
			String cls = multisearcher.doc(doc.doc).get("Class").trim();
			String outlink = multisearcher.doc(doc.doc).get("OutLink")
					.trim();
			String FingerPrint = multisearcher.doc(doc.doc).get("Score")
					.trim();
			docID++;
			for(Entry<String,Double> preference:input.getPreferenceMap().entrySet()){
				if(cls.contains(preference.getKey().toLowerCase())){
				
				if(!searchbeans.containsKey(outlink)){
					SearchBean bean= new SearchBean();
					bean.setLink(vector);
					bean.setOutLink(outlink);
					bean.setPreference(cls);
					bean.setLocalizedFingerPrint(Double.valueOf(FingerPrint)*preference.getValue());
					searchbeans.put(outlink, bean);
				}else{
					SearchBean bean= new SearchBean();
					bean.setLink(vector);
					bean.setOutLink(outlink);
					bean.setPreference(cls);
					bean.setLocalizedFingerPrint(getFPFromMap(searchbeans,outlink)+Double.valueOf(FingerPrint)*preference.getValue());
					searchbeans.remove(outlink);
					searchbeans.put(outlink, bean);
					}
				}
			}
		}
		
	}

	multisearcher.close();
	total=searchbeans.size();
	return total;
}

public HashMap<String, SearchBean> search(QueryInput input, int start, int end) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("Preference Search Use Default Weights start...");
	HashMap<String,SearchBean> result= new HashMap<String, SearchBean>();
	String standarquery = input.getQuery().toLowerCase();
	HashMap<String,SearchBean> searchbeans = new HashMap<String,SearchBean>();
	

	IndexSearcher[] searchers = new IndexSearcher[new File(index)
			.listFiles().length];
	IndexReader[] readers = new IndexReader[new File(index)
			.listFiles().length];
	IndexSearcher searcher = null;
	IndexReader reader = null;
	int indexFileID = 0;
	for (File indexFile : new File(index).listFiles()) {
		FSDirectory dir = FSDirectory.open(indexFile);
		searcher = new IndexSearcher(dir);
		searchers[indexFileID] = searcher;

		indexFileID++;

	}

	MultiSearcher multisearcher = new MultiSearcher(searchers);

	ScoreDoc[] results = multisearcher.search(QueryModel.getWeightAutoSignedPreferenceQuery(standarquery, input.getPreferenceList())
			,
			multisearcher.maxDoc()).scoreDocs;

	System.out.println("Number of hits: " + results.length);
	if (results.length > 0) {
		int docID = 1;
		for (final ScoreDoc doc : results) {

			Double score = (double) doc.score;
			String vector = multisearcher.doc(doc.doc).get("Vector").trim();
			String cls = multisearcher.doc(doc.doc).get("Class").trim();
			String outlink = multisearcher.doc(doc.doc).get("OutLink")
					.trim();
			String FingerPrint = multisearcher.doc(doc.doc).get("Score")
					.trim();
			docID++;
			for(Entry<String,Double> preference:input.getPreferenceMap().entrySet()){
				if(cls.contains(preference.getKey().toLowerCase())){
				
				if(!searchbeans.containsKey(outlink)){
					SearchBean bean= new SearchBean();
					bean.setLink(vector);
					bean.setOutLink(outlink);
					bean.setPreference(cls);
					bean.setLocalizedFingerPrint(Double.valueOf(FingerPrint)*preference.getValue());
					searchbeans.put(outlink, bean);
				}else{
					SearchBean bean= new SearchBean();
					bean.setLink(vector);
					bean.setOutLink(outlink);
					bean.setPreference(cls);
					bean.setLocalizedFingerPrint(getFPFromMap(searchbeans,outlink)+Double.valueOf(FingerPrint)*preference.getValue());
					searchbeans.remove(outlink);
					searchbeans.put(outlink, bean);
					}
				}
			}
		}
		
	}

	multisearcher.close();
	Entry<String,SearchBean>[] entries=getSortedHashtableByValueGeneral(searchbeans);
	int i=0;
//	System.err.println(entries.length);
//	System.err.println(returnUum);
	//start from 1
	for(Entry<String,SearchBean> entry:entries){
		if(start<=i&&i<=end){
			System.out.println(i+"  "+" score: "+entry.getValue().getLocalizedFingerPrint()+ " Outlink: "+entry.getValue().getOutLink()+" search entity: "+entry.getValue().getLink());
			result.put(entry.getValue().getOutLink(), entry.getValue());
				
		}
		i++;
	}
	System.err.println(result.size());
	return result;
}

public static Double getFPFromMap(HashMap<String,SearchBean> map,String key){
	Double fingerprint=0.0;
	fingerprint=map.get(key).getLocalizedFingerPrint();
	map.remove(key);
	return fingerprint;
}

public static Map.Entry<String,SearchBean>[] getSortedHashtableByValueGeneral(Map<String,SearchBean> h) {
	 
	 
	   Set set = h.entrySet();  
       Map.Entry<String,SearchBean>[] entries = (Map.Entry<String,SearchBean>[]) set.toArray(new Map.Entry[set  
               .size()]);  
       Arrays.sort(entries, new Comparator() {  
           public int compare(Object arg0, Object arg1) {  
               Double key1 = ((Map.Entry<String,SearchBean>) arg0).getValue().getLocalizedFingerPrint();  
               Double key2 = ((Map.Entry<String,SearchBean>) arg1).getValue().getLocalizedFingerPrint();  
               return key2.compareTo(key1);  
           }  
       });  
  
       return entries;  
   }  
}
