/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package edu.snu.bike.perank.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.snu.bike.perank.bean.SearchBean;
import edu.snu.bike.perank.struts.form.AnonymousContinueSearchForm;
import edu.snu.bike.perankquery.ContentSearcher;
import edu.snu.bike.perankquery.WeightedrankFingerPrintSearcher;

/** 
 * MyEclipse Struts
 * Creation date: 07-05-2012
 * 
 * XDoclet definition:
 * @struts.action path="/anonymousContinueSearch" name="anonymousContinueSearchForm" input="/anonymousSearch.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/anonymousSearch.jsp"
 */
public class AnonymousContinueSearchAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		AnonymousContinueSearchForm anonymousContinueSearchForm = (AnonymousContinueSearchForm) form;// TODO Auto-generated method stub
		ActionForward actionForward = null;
		String query = anonymousContinueSearchForm.getQuery().toLowerCase();
		String[] topics = anonymousContinueSearchForm.getTopic().split(" ");
		String[] links = anonymousContinueSearchForm.getLink().split(" ");

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
		HashMap<String,SearchBean> searchResults=searcher.mergePreferenceWithWeightSearch("db01295", preferencesmap, 0,10);
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
		
//		for(Entry<String,String> entry:results.entrySet()){
//			System.err.println(entry.getKey());
//			System.err.println(entry.getValue());
//		}
		String[] topics1= new String[2];
		topics1[0]="t3";
		topics1[1]="t2";
		String[] links1= new String[1];
		links[0]="l2";
		request.setAttribute("oldtopcis", topics1);
		request.setAttribute("oldlinks", links1);
		request.setAttribute("oldquery", query);
		request.setAttribute("feedback", results);
		request.setAttribute("outwithPreference", searchResults);
		actionForward = mapping.findForward("success");

		return actionForward;
	}
}