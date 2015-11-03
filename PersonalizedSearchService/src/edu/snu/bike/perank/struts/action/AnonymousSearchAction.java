/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package edu.snu.bike.perank.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.template.util.Content;

import edu.snu.bike.perank.bean.SearchBean;
import edu.snu.bike.perank.query.AbstractQueryModelSupport;
import edu.snu.bike.perank.query.ContentSearcher;
import edu.snu.bike.perank.query.Model3Factory;
import edu.snu.bike.perank.query.QueryInput;
import edu.snu.bike.perank.query.WeightedrankFingerPrintSearcher;
import edu.snu.bike.perank.struts.form.AnonymousSearchForm;
import edu.snu.bike.perank.tool.*;

/**
 * MyEclipse Struts Creation date: 07-04-2012
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/anonymousSearch" name="anonymousSearchForm"
 *                input="/form/anonymousSearch.jsp" scope="request"
 *                validate="true"
 * @struts.action-forward name="success" path="/anonymousSearch.jsp"
 * @struts.action-forward name="fail" path="/index.jsp"
 */
public class AnonymousSearchAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException, InvalidTokenOffsetsException {
		AnonymousSearchForm anonymousSearchForm = (AnonymousSearchForm) form;// TODO
		// Auto-generated
		// method
		// stub
		
		
		ActionForward actionForward = null;
		String query = anonymousSearchForm.getQuery().toLowerCase();
		String[] topics = anonymousSearchForm.getTopic().split(" ");
		String[] links = anonymousSearchForm.getLink().split(" ");
		
		int currentPage=anonymousSearchForm.getPageNum();
		if(currentPage==0){
			currentPage=1;
		}
//		e:\\data\\index\\weightedFingerPrint_1000
		AbstractQueryModelSupport model3= new AbstractQueryModelSupport("E://PPV/SmallData/index/weightedFingerPrint_1000");
		Model3Factory factory=model3.createModel3();
		WeightedrankFingerPrintSearcher searcher = new WeightedrankFingerPrintSearcher(
				"E://PPV/SmallData/index/weightedFingerPrint_1000");
		ArrayList<String> preferenceslist = new ArrayList<String>();
		preferenceslist.add("pdrhealthlink");
		preferenceslist.add("brandedDrug");

		HashMap<String, Double> preferencesmap = new HashMap<String, Double>();
		// preferencesmap.put("enzymes",0.6);
		// preferencesmap.put("interactionDrug", 0.2);

		preferencesmap.put("drugs", 0.8);
		// preferencesmap.put("references", 0.2);
		preferencesmap.put("targets", 0.2);

		// searcher.singlePreferenceWithWeightSearch("DB01020_DB00353",
		// preferencesmap);
		// searcher.singlePreferenceWithWeightSearch("synonym", preferences);
		
		QueryInput input= new QueryInput();
		input.setQuery(query);
		input.setPreferenceMap(preferencesmap);
//		input.setPreferenceList(preferenceList);
		
		int totalHits=factory.getNumberOfHits(input);
		System.out.println("total hits: "+totalHits);
		int pageSize = 10;  
		int lowLimit=(currentPage-1)*pageSize;
	    int upperLimit=currentPage*pageSize-1;
	    HashMap<String,SearchBean> searchResults;
	    if(upperLimit<totalHits){
	    	searchResults =factory.search(input, lowLimit, upperLimit);
	    }else{
	    	searchResults =factory.search(input, lowLimit, totalHits);	
	    }
		
		HashMap<String,String> results = new HashMap<String, String>();
		HashMap<String,String> result;
		for(Entry<String, SearchBean> entry:searchResults.entrySet()){
			ContentSearcher content= new ContentSearcher();
			result=content.search(entry.getKey(),"db01295");
//			result=content.testhighlighter(entry.getKey());
			for(Entry<String,String> entry1:result.entrySet()){
				results.put(entry1.getKey(), entry1.getValue());
			}
			
		}
		
	    int totalPages = totalHits/pageSize + ((totalHits%pageSize)>0?1:0);  
		
		for(Entry<String,String> entry:results.entrySet()){
			System.err.println(entry.getKey());
			System.err.println(entry.getValue());
		}
		
		String[] topics0= new String[1];
		topics0[0]="t2";
		String[] links0= new String[2];
		links0[0]="l2";
		links0[1]="l1";
		System.out.println("currentPage\t"+currentPage);
		request.setAttribute("totalHits", totalHits);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("maxPage", totalPages);
		System.out.println(pagesSpliter.pagesSplit(currentPage, 5, totalPages).length);
		request.setAttribute("pages", pagesSpliter.pagesSplit(currentPage, 5, totalPages));
		request.setAttribute("oldtopcis", topics0);
		request.setAttribute("oldlinks", links0);
		request.setAttribute("oldquery", query);
		request.setAttribute("feedback", results);
		request.setAttribute("outwithPreference", searchResults);
		actionForward = mapping.findForward("success");

		return actionForward;
	}
}