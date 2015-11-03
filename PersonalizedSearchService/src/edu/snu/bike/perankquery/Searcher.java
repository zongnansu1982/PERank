/**
 * 
 */
package edu.snu.bike.perankquery;

import java.util.HashMap;

import edu.snu.bike.perank.bean.SearchBean;

/**
 * @author zongnansu
 *
 */
public interface Searcher {
	public HashMap<String,SearchBean> search (QueryInput input,int start,int end)throws Exception;
	
	public Integer getNumberOfHits(QueryInput input) throws Exception;
}
