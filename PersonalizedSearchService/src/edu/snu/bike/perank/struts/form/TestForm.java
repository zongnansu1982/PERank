/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package edu.snu.bike.perank.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-04-2012
 * 
 * XDoclet definition:
 * @struts.form name="testForm"
 */
public class TestForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** input property */
	private String input;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the input.
	 * @return String
	 */
	public String getInput() {
		return input;
	}

	/** 
	 * Set the input.
	 * @param input The input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}
}