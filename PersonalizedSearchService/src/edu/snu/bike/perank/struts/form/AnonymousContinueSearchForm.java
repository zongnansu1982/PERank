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
 * Creation date: 07-05-2012
 * 
 * XDoclet definition:
 * @struts.form name="anonymousContinueSearchForm"
 */
public class AnonymousContinueSearchForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** query property */
	private String query;

	/** topic property */
	private String topic;

	/** link property */
	private String link;

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
	 * Returns the query.
	 * @return String
	 */
	public String getQuery() {
		return query;
	}

	/** 
	 * Set the query.
	 * @param query The query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/** 
	 * Returns the topic.
	 * @return String
	 */
	public String getTopic() {
		return topic;
	}

	/** 
	 * Set the topic.
	 * @param topic The topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/** 
	 * Returns the link.
	 * @return String
	 */
	public String getLink() {
		return link;
	}

	/** 
	 * Set the link.
	 * @param link The link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
}