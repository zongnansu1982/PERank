/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package edu.snu.bike.perank.struts.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.snu.bike.perank.struts.form.PersonalizedSearchForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-04-2012
 * 
 * XDoclet definition:
 * @struts.action path="/personalizedSearch" name="personalizedSearchForm" input="/personalizedhome.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/personalizedSearch.jsp"
 * @struts.action-forward name="fail" path="/index.jsp"
 */
public class PersonalizedSearchAction extends Action {
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
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PersonalizedSearchForm personalizedSearchForm = (PersonalizedSearchForm) form;// TODO Auto-generated method stub
		return null;
	}
}