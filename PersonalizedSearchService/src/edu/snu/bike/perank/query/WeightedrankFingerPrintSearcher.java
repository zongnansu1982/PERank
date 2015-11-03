package edu.snu.bike.perank.query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.store.FSDirectory;

import edu.snu.bike.perank.bean.SearchBean;

public class WeightedrankFingerPrintSearcher {

	public String mainIndexDir = null;

	public WeightedrankFingerPrintSearcher(String mainIndexDir) {
		this.mainIndexDir = mainIndexDir;

	}

	public void singlePreferenceWithoutWeightSearch(String query,
			ArrayList<String> preferences) throws IOException, ParseException {

		System.out.println("Preference Search Use Default Weights start...");

		String standarquery = query.toLowerCase();

		IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
				.listFiles().length];
		IndexReader[] readers = new IndexReader[new File(mainIndexDir)
				.listFiles().length];
		IndexSearcher searcher = null;
		IndexReader reader = null;
		int indexFileID = 0;
		for (File indexFile : new File(mainIndexDir).listFiles()) {
			FSDirectory dir = FSDirectory.open(indexFile);
			searcher = new IndexSearcher(dir);
			searchers[indexFileID] = searcher;

			indexFileID++;

		}

		Sort sort = new Sort(new SortField("Score", SortField.DOUBLE, true));
		MultiSearcher multisearcher = new MultiSearcher(searchers);

		ScoreDoc[] results = multisearcher.search(QueryModel
				.getWeightAutoSignedPreferenceQuery(standarquery, preferences),
				10, sort).scoreDocs;

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
				System.out.println("FingerPrints: " + FingerPrint + "link: "
						+ vector + " outlink: " + outlink + " class: " + cls
						+ " doc score: " + score);

			}
			multisearcher.close();

		}
	}

	public HashMap<String, Double> singlePreferenceWithWeightSearch(String query,
			HashMap<String, Double> preferences, int retrunNum) throws IOException,
			ParseException {

		HashMap<String,Double> result =new HashMap<String, Double>();
		System.out.println("query start...");

		String standarquery = query.toLowerCase();

		IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
				.listFiles().length];
		IndexReader[] readers = new IndexReader[new File(mainIndexDir)
				.listFiles().length];
		IndexSearcher searcher = null;
		IndexReader reader = null;
		int indexFileID = 0;
		for (File indexFile : new File(mainIndexDir).listFiles()) {
			FSDirectory dir = FSDirectory.open(indexFile);
			searcher = new IndexSearcher(dir);
			searchers[indexFileID] = searcher;

			reader = searcher.getIndexReader();
			readers[indexFileID] = reader;
			indexFileID++;

		}

		MultiReader multireader = new MultiReader(readers);
		Sort sort = new Sort(new SortField("Score",
				new FingerPrintComparatorSource(preferences), true));
		MultiSearcher multisearcher = new MultiSearcher(searchers);

		ScoreDoc[] results = multisearcher.search(QueryModel
				.getWeightDesignatatedPreferenceQuery(query, preferences), retrunNum,
				sort).scoreDocs;

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
				System.out.println("FingerPrints: " + FingerPrint + "link: "
						+ vector + " outlink: " + outlink + " class: " + cls
						+ " doc score: " + score);
				result.put(outlink, Double.valueOf(FingerPrint));
			}
			multisearcher.close();

		}
		return result;
	}

	public void NormalizedSearch(String query,
			HashMap<String, Double> preferences) throws IOException,
			ParseException {

		System.out.println("Normalized Query start...");

		String standarquery = query.toLowerCase();

		IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
				.listFiles().length];
		IndexReader[] readers = new IndexReader[new File(mainIndexDir)
				.listFiles().length];
		IndexSearcher searcher = null;
		IndexReader reader = null;
		int indexFileID = 0;
		for (File indexFile : new File(mainIndexDir).listFiles()) {
			FSDirectory dir = FSDirectory.open(indexFile);
			searcher = new IndexSearcher(dir);
			searchers[indexFileID] = searcher;
			indexFileID++;

		}

		Sort sort = new Sort(new SortField("Score", SortField.DOUBLE, true));
		MultiSearcher multisearcher = new MultiSearcher(searchers);

		ScoreDoc[] results = multisearcher.search(
				QueryModel.getNormalizedQuery(standarquery), 10, sort).scoreDocs;

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
				System.out.println("FingerPrints: " + FingerPrint + "link: "
						+ vector + " outlink: " + outlink + " class: " + cls
						+ " doc score: " + score);

			}
			multisearcher.close();

		}
	}

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */
	
		public HashMap<String,SearchBean> mergePreferenceWithWeightSearch(String query,
			HashMap<String,Double> preferences, int start,int end) throws IOException, ParseException {

		System.out.println("Preference Search Use Default Weights start...");
		HashMap<String,SearchBean> result= new HashMap<String, SearchBean>();
		String standarquery = query.toLowerCase();
		HashMap<String,SearchBean> searchbeans = new HashMap<String,SearchBean>();
		

		IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
				.listFiles().length];
		IndexReader[] readers = new IndexReader[new File(mainIndexDir)
				.listFiles().length];
		IndexSearcher searcher = null;
		IndexReader reader = null;
		int indexFileID = 0;
		for (File indexFile : new File(mainIndexDir).listFiles()) {
			FSDirectory dir = FSDirectory.open(indexFile);
			searcher = new IndexSearcher(dir);
			searchers[indexFileID] = searcher;

			indexFileID++;

		}

		MultiSearcher multisearcher = new MultiSearcher(searchers);

		ScoreDoc[] results = multisearcher.search(QueryModel
				.getWeightDesignatatedPreferenceQuery(standarquery, preferences),
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
				for(Entry<String,Double> preference:preferences.entrySet()){
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
//		System.err.println(entries.length);
//		System.err.println(returnUum);
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
		public Integer returnMergePreferenceWithWeightSearchTotalNumber(String query,
				HashMap<String,Double> preferences) throws IOException, ParseException {
			int total=0;
			System.out.println("Preference Search Use Default Weights start...");
			HashMap<String,SearchBean> result= new HashMap<String, SearchBean>();
			String standarquery = query.toLowerCase();
			HashMap<String,SearchBean> searchbeans = new HashMap<String,SearchBean>();
			

			IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
					.listFiles().length];
			IndexReader[] readers = new IndexReader[new File(mainIndexDir)
					.listFiles().length];
			IndexSearcher searcher = null;
			IndexReader reader = null;
			int indexFileID = 0;
			for (File indexFile : new File(mainIndexDir).listFiles()) {
				FSDirectory dir = FSDirectory.open(indexFile);
				searcher = new IndexSearcher(dir);
				searchers[indexFileID] = searcher;

				indexFileID++;

			}

			MultiSearcher multisearcher = new MultiSearcher(searchers);

			ScoreDoc[] results = multisearcher.search(QueryModel
					.getWeightDesignatatedPreferenceQuery(standarquery, preferences),
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
					for(Entry<String,Double> preference:preferences.entrySet()){
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
		
		public void mergePreferenceWithoutWeightSearch(String query,
				ArrayList<String> preferences) throws IOException, ParseException {
			HashMap<String,SearchBean> searchbeans = new HashMap<String,SearchBean>();
			System.out.println("Preference Search Use Default Weights start...");

			String standarquery = query.toLowerCase();

			IndexSearcher[] searchers = new IndexSearcher[new File(mainIndexDir)
					.listFiles().length];
			IndexReader[] readers = new IndexReader[new File(mainIndexDir)
					.listFiles().length];
			IndexSearcher searcher = null;
			IndexReader reader = null;
			int indexFileID = 0;
			for (File indexFile : new File(mainIndexDir).listFiles()) {
				FSDirectory dir = FSDirectory.open(indexFile);
				searcher = new IndexSearcher(dir);
				searchers[indexFileID] = searcher;

				indexFileID++;

			}

			MultiSearcher multisearcher = new MultiSearcher(searchers);

			ScoreDoc[] results = multisearcher.search(QueryModel
					.getWeightAutoSignedPreferenceQuery(standarquery, preferences),
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
//					System.out.println("FingerPrints: " + FingerPrint + "link: "
//							+ vector + " outlink: " + outlink + " class: " + cls
//							+ " doc score: " + score);
					
					for(String preference:preferences){
						if(cls.contains(preference.toLowerCase())){
						
						if(!searchbeans.containsKey(outlink)){
							SearchBean bean= new SearchBean();
							bean.setLink(vector);
							bean.setOutLink(outlink);
							bean.setLocalizedFingerPrint(Double.valueOf(FingerPrint)/preferences.size());
							searchbeans.put(outlink, bean);
						}else{
							SearchBean bean= new SearchBean();
							bean.setLink(vector);
							bean.setOutLink(outlink);
							bean.setLocalizedFingerPrint(getFPFromMap(searchbeans,outlink)+Double.valueOf(FingerPrint)/preferences.size());
							searchbeans.remove(outlink);
							searchbeans.put(outlink, bean);
							}
						}
					}

				}
				multisearcher.close();

				}
			
			Entry<String,SearchBean>[] entries=getSortedHashtableByValueGeneral(searchbeans);
			
			for(Entry<String,SearchBean> entry:entries){
				System.out.println(" score: "+entry.getValue().getLocalizedFingerPrint()+ " Outlink: "+entry.getValue().getOutLink()+" search entity: "+entry.getValue().getLink());
				
			}
			
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
		           @Override
				public int compare(Object arg0, Object arg1) {  
		               Double key1 = ((Map.Entry<String,SearchBean>) arg0).getValue().getLocalizedFingerPrint();  
		               Double key2 = ((Map.Entry<String,SearchBean>) arg1).getValue().getLocalizedFingerPrint();  
		               return key2.compareTo(key1);  
		           }  
		       });  
		  
		       return entries;  
		   }  
		
		

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		WeightedrankFingerPrintSearcher searcher = new WeightedrankFingerPrintSearcher(
				"E://PPV/SmallData/index/weightedFingerPrint_1000");
		 ArrayList<String> preferenceslist= new ArrayList<String>();
		 preferenceslist.add("pdrhealthlink");
		 preferenceslist.add("brandedDrug");
		
	

		HashMap<String, Double> preferencesmap = new HashMap<String, Double>();
//		preferencesmap.put("enzymes",0.6);
//		preferencesmap.put("interactionDrug", 0.2);
		
		preferencesmap.put("drugs", 0.8);
//		preferencesmap.put("references", 0.2);
		preferencesmap.put("targets", 0.2);
		
//		searcher.singlePreferenceWithWeightSearch("DB01020_DB00353", preferencesmap);
//		searcher.singlePreferenceWithWeightSearch("synonym", preferences);
		try {
			searcher.mergePreferenceWithWeightSearch("db01295", preferencesmap,0,10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		searcher.NormalizedSearch("http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/db01295", preferencesmap);
		//		searcher.singlePreferenceWithWeightSearch("DB01295", preferencesmap);
//		searcher.mergePreferenceWithoutWeightSearch("DB01020_DB00353", preferenceslist);
		

	}

}
