package edu.snu.bike.perank.dbUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import edu.snu.bike.perank.bean.ProfileBean;
import edu.snu.bike.perank.hibernate.LinkInterestTable;
import edu.snu.bike.perank.hibernate.LinkInterestTableDAO;
import edu.snu.bike.perank.hibernate.PreferenceLinkMapping;
import edu.snu.bike.perank.hibernate.PreferenceLinkMappingDAO;
import edu.snu.bike.perank.hibernate.PreferenceLinkMappingId;
import edu.snu.bike.perank.hibernate.PreferenceTable;
import edu.snu.bike.perank.hibernate.PreferenceTableDAO;
import edu.snu.bike.perank.hibernate.PreferenceTopicMapping;
import edu.snu.bike.perank.hibernate.PreferenceTopicMappingDAO;
import edu.snu.bike.perank.hibernate.PreferenceTopicMappingId;
import edu.snu.bike.perank.hibernate.TopicInterestTable;
import edu.snu.bike.perank.hibernate.TopicInterestTableDAO;
import edu.snu.bike.perank.hibernate.UserTable;
import edu.snu.bike.perank.hibernate.UserTableDAO;

public class DbUtil {

	public ProfileBean getProfile(String name) {
		System.out.println("name: " + name);
		ProfileBean bean = new ProfileBean();
		PreferenceTableDAO preference = new PreferenceTableDAO();
		List<PreferenceTable> plist = preference.findAll();
		ArrayList<String> topics = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();
		for (PreferenceTable row : plist) {
			System.out
					.println(row.getId() + " " + row.getUsertable().getName());
			if (row.getUsertable().getName().equals(name)) {
				Set<PreferenceLinkMapping> lset = row
						.getPreferencelinkmappings();
				for (PreferenceLinkMapping mapping : lset) {
					if (mapping.getId().getPreferencetable().getUsertable()
							.getName().equals(name)) {
						links.add(mapping.getId().getLinkintresttable()
								.getLink());
					}
				}
				Set<PreferenceTopicMapping> tset = row
						.getPreferencetopicmappings();
				for (PreferenceTopicMapping mapping : tset) {
					if (mapping.getId().getPreferencetable().getUsertable()
							.getName().equals(name)) {
						topics.add(mapping.getId().getTopicintresttable()
								.getTopic());
					}
				}

			}
		}
		System.out.println("links: " + links.toString());
		System.out.println("topics: " + topics.toString());
		bean.setLinks(links.toArray());
		bean.setTopics(topics.toArray());
		return bean;

	}

	public void insert(String name, Object[] topics, Object[] links) {
		ArrayList<String> ts = transfer(topics);
		ArrayList<String> ls = transfer(links);
 
		for (Object topic : topics) {
			try {
				if (!new TopicInterestTableDAO().topicExsit(topic.toString())) {
					TopicInterestTable t = new TopicInterestTable();
					t.setTopic(topic.toString());
					TopicInterestTableDAO tdao = new TopicInterestTableDAO();
					tdao.save(t);
					tdao.getSession().beginTransaction().commit();
				} else {
					System.err.println("repeat topic: " + topic);
				}

			} catch (Exception e) {
				System.out.println("repeat topic: " + topic);
			}
		}

		for (Object link : links) {
			try {
				if (!new LinkInterestTableDAO().linkExsit(link.toString())) {
					LinkInterestTable l = new LinkInterestTable();
					l.setLink(link.toString());
					LinkInterestTableDAO ldao = new LinkInterestTableDAO();
					ldao.save(l);
					ldao.getSession().beginTransaction().commit();
				} else {
					System.err.println("repeat link: " + link);
				}

			} catch (Exception e) {

			}
		}

		UserTableDAO udao = new UserTableDAO();
		UserTable user = new UserTable();
		try {
			if (!new UserTableDAO().userExsit(name)) {
				user.setName(name);
				udao.save(user);
				udao.getSession().beginTransaction().commit();
			} else {
				System.err.println("repeat user: " + name);
			}

		} catch (Exception e) {
			System.out.println("repeat user: " + name);
		}

		udao = new UserTableDAO();
		List<UserTable> ulist = udao.findByName(name);
		user = new UserTable();
		for (UserTable utable : ulist) {
			System.out.println(utable.getId() + " " + utable.getName());
			user = utable;
		}

		PreferenceTable p = new PreferenceTable();
		p.setUsertable(user);
		PreferenceTableDAO pdao = new PreferenceTableDAO();
		pdao.save(p);
		pdao.getSession().beginTransaction().commit();

		TopicInterestTableDAO ttao = new TopicInterestTableDAO();
		List<TopicInterestTable> tlist = ttao.findAll();
		for (TopicInterestTable ttable : tlist) {
			if (ts.contains(ttable.getTopic())) {
				PreferenceTopicMappingDAO tmappingdao = new PreferenceTopicMappingDAO();
				PreferenceTopicMapping tmapping = new PreferenceTopicMapping();
				PreferenceTopicMappingId tid = new PreferenceTopicMappingId();
				tid.setPreferencetable(p);
				tid.setTopicintresttable(ttable);
				tmapping.setId(tid);
				tmappingdao.save(tmapping);
				ttao.getSession().beginTransaction().commit();
			}
		}

		LinkInterestTableDAO ldao = new LinkInterestTableDAO();
		List<LinkInterestTable> llist = ldao.findAll();
		for (LinkInterestTable ltable : llist) {
			if (ls.contains(ltable.getLink())) {
				PreferenceLinkMappingDAO lmappingdao = new PreferenceLinkMappingDAO();
				PreferenceLinkMapping lmapping = new PreferenceLinkMapping();

				PreferenceLinkMappingId lid = new PreferenceLinkMappingId();
				lid.setPreferencetable(p);
				lid.setLinkintresttable(ltable);
				lmapping.setId(lid);
				lmappingdao.save(lmapping);
				ldao.getSession().beginTransaction().commit();
			}
		}

	}

	public ArrayList<String> transfer(Object[] array) {
		ArrayList<String> out = new ArrayList<String>();
		for (Object object : array) {
			out.add(object.toString());
		}
		return out;
	}

	public static void main(String[] args) {
		DbUtil util = new DbUtil();
		Object[] topics = new Object[3];
		topics[0] = "drug";
		topics[1] = "disease";
		topics[2] = "gene";
		Object[] links = new Object[1];
		links[0] = "drugType";
		// util.insert("nansu", topics, links);
		util.getProfile("nansu");
	}

}
