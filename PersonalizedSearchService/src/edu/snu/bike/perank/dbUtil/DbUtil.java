package edu.snu.bike.perank.dbUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;

import edu.snu.bike.perank.bean.ProfileBean;
import edu.snu.bike.perank.hibernate.Linkintresttable;
import edu.snu.bike.perank.hibernate.LinkintresttableDAO;
import edu.snu.bike.perank.hibernate.Preferencelinkmapping;
import edu.snu.bike.perank.hibernate.PreferencelinkmappingDAO;
import edu.snu.bike.perank.hibernate.PreferencelinkmappingId;
import edu.snu.bike.perank.hibernate.Preferencetable;
import edu.snu.bike.perank.hibernate.PreferencetableDAO;
import edu.snu.bike.perank.hibernate.Preferencetopicmapping;
import edu.snu.bike.perank.hibernate.PreferencetopicmappingDAO;
import edu.snu.bike.perank.hibernate.PreferencetopicmappingId;
import edu.snu.bike.perank.hibernate.Topicintresttable;
import edu.snu.bike.perank.hibernate.TopicintresttableDAO;
import edu.snu.bike.perank.hibernate.Usertable;
import edu.snu.bike.perank.hibernate.UsertableDAO;

public class DbUtil {

	public ProfileBean getProfile(String name) {
		System.out.println("name: " + name);
		ProfileBean bean = new ProfileBean();
		PreferencetableDAO preference = new PreferencetableDAO();
		List<Preferencetable> plist = preference.findAll();
		ArrayList<String> topics = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();
		for (Preferencetable row : plist) {
			System.out
					.println(row.getId() + " " + row.getUsertable().getName());
			if (row.getUsertable().getName().equals(name)) {
				Set<Preferencelinkmapping> lset = row
						.getPreferencelinkmappings();
				for (Preferencelinkmapping mapping : lset) {
					if (mapping.getId().getPreferencetable().getUsertable()
							.getName().equals(name)) {
						links.add(mapping.getId().getLinkintresttable()
								.getLink());
					}
				}
				Set<Preferencetopicmapping> tset = row
						.getPreferencetopicmappings();
				for (Preferencetopicmapping mapping : tset) {
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
				if (!new TopicintresttableDAO().topicExsit(topic.toString())) {
					Topicintresttable t = new Topicintresttable();
					t.setTopic(topic.toString());
					TopicintresttableDAO tdao = new TopicintresttableDAO();
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
				if (!new LinkintresttableDAO().linkExsit(link.toString())) {
					Linkintresttable l = new Linkintresttable();
					l.setLink(link.toString());
					LinkintresttableDAO ldao = new LinkintresttableDAO();
					ldao.save(l);
					ldao.getSession().beginTransaction().commit();
				} else {
					System.err.println("repeat link: " + link);
				}

			} catch (Exception e) {

			}
		}

		UsertableDAO udao = new UsertableDAO();
		Usertable user = new Usertable();
		try {
			if (!new UsertableDAO().userExsit(name)) {
				user.setName(name);
				udao.save(user);
				udao.getSession().beginTransaction().commit();
			} else {
				System.err.println("repeat user: " + name);
			}

		} catch (Exception e) {
			System.out.println("repeat user: " + name);
		}

		udao = new UsertableDAO();
		List<Usertable> ulist = udao.findByName(name);
		user = new Usertable();
		for (Usertable utable : ulist) {
			System.out.println(utable.getId() + " " + utable.getName());
			user = utable;
		}

		Preferencetable p = new Preferencetable();
		p.setUsertable(user);
		PreferencetableDAO pdao = new PreferencetableDAO();
		pdao.save(p);
		pdao.getSession().beginTransaction().commit();

		TopicintresttableDAO ttao = new TopicintresttableDAO();
		List<Topicintresttable> tlist = ttao.findAll();
		for (Topicintresttable ttable : tlist) {
			if (ts.contains(ttable.getTopic())) {
				PreferencetopicmappingDAO tmappingdao = new PreferencetopicmappingDAO();
				Preferencetopicmapping tmapping = new Preferencetopicmapping();
				PreferencetopicmappingId tid = new PreferencetopicmappingId();
				tid.setPreferencetable(p);
				tid.setTopicintresttable(ttable);
				tmapping.setId(tid);
				tmappingdao.save(tmapping);
				ttao.getSession().beginTransaction().commit();
			}
		}

		LinkintresttableDAO ldao = new LinkintresttableDAO();
		List<Linkintresttable> llist = ldao.findAll();
		for (Linkintresttable ltable : llist) {
			if (ls.contains(ltable.getLink())) {
				PreferencelinkmappingDAO lmappingdao = new PreferencelinkmappingDAO();
				Preferencelinkmapping lmapping = new Preferencelinkmapping();

				PreferencelinkmappingId lid = new PreferencelinkmappingId();
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
