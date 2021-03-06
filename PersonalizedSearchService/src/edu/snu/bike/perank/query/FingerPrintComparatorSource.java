package edu.snu.bike.perank.query;


import java.io.IOException;  
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.lucene.index.IndexReader;  
import org.apache.lucene.search.FieldComparator;
import org.apache.lucene.search.FieldComparatorSource; 


public class FingerPrintComparatorSource extends FieldComparatorSource {
	private HashMap<String,Double> preferences;
//	private IndexReader reader;
	
	public FingerPrintComparatorSource (HashMap<String,Double> preferences){
		this.preferences=preferences;
//		this.reader=reader;
	}
	
	@Override
	public FieldComparator newComparator(String fieldname, int numHits,  
	        int sortPos, boolean reversed) throws IOException {
		// TODO Auto-generated method stub
		return new WeightedFingerPrintComparator(numHits, fieldname,preferences);
	}  
   
	private static class WeightedFingerPrintComparator extends  FieldComparator{
		 private float[] localizedFingerPrints;
		 private float[] currentReaderValues; 
		 private final String field;
		 private HashMap<String,Double> preferences;
		 private float bottom;
		 public WeightedFingerPrintComparator(int numHits, String field, HashMap<String,Double> preferences) throws IOException {   
	           
	            this.field=field;
	            this.preferences=preferences;
	            localizedFingerPrints = new float[numHits];  // │§╩╝╗»distances   
	            }

		@Override
		public int compare(int arg0, int arg1) {
			// TODO Auto-generated method stub
			 if (localizedFingerPrints[arg0] > currentReaderValues[arg1])  
	                return -1;  
	            if (localizedFingerPrints[arg0] < currentReaderValues[arg1])  
	                return 1;  
	            return 0;  
		}

		@Override
		public int compareBottom(int arg0) throws IOException {
			// TODO Auto-generated method stub
			
			
			 if (bottom > currentReaderValues[arg0])  
	                return -1;  
	            if (bottom < currentReaderValues[arg0])  
	                return 1;  
	            return 0;
		}

		@Override
		public void copy(int arg0, int arg1) throws IOException {
			// TODO Auto-generated method stub
			
			localizedFingerPrints[arg0]=currentReaderValues[arg1];
			
		}

		@Override
		public void setBottom(int arg0) {
			// TODO Auto-generated method stub
			
			this.bottom=localizedFingerPrints[arg0];
			
		}

		@Override
		public void setNextReader(IndexReader arg0, int arg1)
				throws IOException {
			// TODO Auto-generated method stub
			System.err.println(arg1);
			currentReaderValues = new float[arg0.maxDoc()];  // │§╩╝╗»distances   
	            for(int i=arg1;i<arg0.maxDoc();i++){
	            	String cls=arg0.document(i).get("Class");
	            	for(Entry<String,Double> preference:preferences.entrySet()){
	            		
	            		if(cls.contains(preference.getKey().toLowerCase())){
	            			
	            			currentReaderValues[i]=(float) (Float.valueOf(arg0.document(i).get("Score"))*preference.getValue());
//	            			System.out.println("fingerPrint: "+arg0.document(i).get("Score")+" preference: "+preference.getValue()+" localed fp: "+ currentReaderValues[i]);
	            		}
	            	}
	            }
//			reader=arg0;
		}

		@Override
		public Comparable value(int arg0) {
			// TODO Auto-generated method stub
			return localizedFingerPrints[arg0];
		}   
	   }   
		
		
		
	}
	
	
 
