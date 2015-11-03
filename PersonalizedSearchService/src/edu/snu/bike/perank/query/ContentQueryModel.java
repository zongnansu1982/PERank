package edu.snu.bike.perank.query;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.sindice.siren.search.SirenCellQuery;
import org.sindice.siren.search.SirenTermQuery;
import org.sindice.siren.search.SirenTupleClause;
import org.sindice.siren.search.SirenTupleQuery;
public class ContentQueryModel {
	public static Query getORQueryInOneDoc(String inputSeparatedBySpace){

		String[] input=inputSeparatedBySpace.split(" ");
		
		final BooleanQuery booleanQuery = new BooleanQuery();
		
		for(String in:input){
			final SirenCellQuery cq = new SirenCellQuery();
			final SirenTupleQuery tuple= new SirenTupleQuery();
			SirenTermQuery term= new SirenTermQuery(new Term("content", in));
			cq.setQuery(term);
			
			tuple.add(cq,SirenTupleClause.Occur.MUST);
			booleanQuery.add(tuple, BooleanClause.Occur.SHOULD);
		}
		
		
		return booleanQuery;
	}
}
