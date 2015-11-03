package edu.snu.bike.perank.tool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.snu.bike.perank.bean.PreferenceBean;
import edu.snu.bike.perank.bean.SearchBean;
import edu.snu.bike.perank.bean.SessionPreferenceBean;
import edu.snu.bike.perank.bean.SessionQueryBean;
public class JsonGenerator {
	public String queryJsonGeneratr(HashMap<String,Double> queryStatics,List<SessionQueryBean> queryTrack){
		String queryJson = "";
		String[] pastKeys;
		int i=0;
		
		for(SessionQueryBean bean:queryTrack){
			String[] keys=bean.getKeyWords();
			if(i==0){
				
				
				
			}else{
				
				
			}
			
			pastKeys=bean.getKeyWords();
			i++;
		}
		
		return queryJson;
	}
	
	public HashMap<String,PreferenceBean> preferenceTrackGenerate(List<SessionPreferenceBean> sessionPreference){
		HashMap<String,PreferenceBean> map = new HashMap<String, PreferenceBean>();
		SessionPreferenceBean previousBean = null;
		System.out.println("SessionPreference: "+sessionPreference);
		int id=0;
		for(SessionPreferenceBean entry:sessionPreference){
			id++;
			SessionPreferenceBean sessionBean= entry;
			PreferenceBean preferenceBean = null;
			if(id==1){
				for(String topic:sessionBean.getTopics()){
					preferenceBean= new PreferenceBean();
					String[] inlink= new String[1];
					inlink[0]=topic;
					preferenceBean.setInlink(inlink);
					preferenceBean.setType("topic");
					map.put(topic, preferenceBean);
				}
				for(String link:sessionBean.getLinks()){
					preferenceBean= new PreferenceBean();
					String[] inlink= new String[1];
					inlink[0]=link;
					preferenceBean.setInlink(inlink);
					preferenceBean.setType("link");
					map.put(link, preferenceBean);
				}
				
				
			}else{
				for(String topic:sessionBean.getTopics()){
					if(!map.containsKey(topic)){
						
					
					preferenceBean= new PreferenceBean();
					preferenceBean.setType("topic");
					String[] labours= new String[previousBean.getLinks().length+previousBean.getTopics().length];
					int i=0;
					for(String t:previousBean.getTopics()){
						labours[i]=t;
						i++;
					}
					for(String l:previousBean.getLinks()){
						labours[i]=l;
						i++;
					}
					preferenceBean.setInlink(labours);
					map.put(topic, preferenceBean);
					}else{
						preferenceBean= new PreferenceBean();
						preferenceBean.setType("topic");
						String[] oldlink=map.get(topic).getInlink();
						String[] labours;
						if(oldlink==null){
							labours	= new String[previousBean.getLinks().length+previousBean.getTopics().length];
							int i=0;
							for(String t:previousBean.getTopics()){
								labours[i]=t;
								i++;
							}
							for(String l:previousBean.getLinks()){
								labours[i]=l;
								i++;
							}
							preferenceBean.setInlink(labours);
							map.put(topic, preferenceBean);
							
						}else{
							labours	= new String[previousBean.getLinks().length+previousBean.getTopics().length+oldlink.length];
							int i=0;
							for(String t:previousBean.getTopics()){
								labours[i]=t;
								i++;
							}
							for(String l:previousBean.getLinks()){
								labours[i]=l;
								i++;
							}
							for(String o:oldlink){
								labours[i]=o;
								i++;
							}	
							preferenceBean.setInlink(labours);
							map.put(topic, preferenceBean);
						}
						
					}
				}
				for(String link:sessionBean.getLinks()){
					if(!map.containsKey(link)){
						preferenceBean= new PreferenceBean();
						preferenceBean.setType("link");
						String[] labours= new String[previousBean.getLinks().length+previousBean.getTopics().length];
						int i=0;
						for(String t:previousBean.getTopics()){
							labours[i]=t;
							i++;
						}
						for(String l:previousBean.getLinks()){
							labours[i]=l;
							i++;
						}
						preferenceBean.setInlink(labours);
						map.put(link, preferenceBean);	
					}else{
						preferenceBean= new PreferenceBean();
						preferenceBean.setType("link");
						String[] labours;
						String[] oldlink=map.get(link).getInlink();
						if(oldlink==null){
							labours	= new String[previousBean.getLinks().length+previousBean.getTopics().length];
							int i=0;
							for(String t:previousBean.getTopics()){
								labours[i]=t;
								i++;
							}
							for(String l:previousBean.getLinks()){
								labours[i]=l;
								i++;
							}
							preferenceBean.setInlink(labours);
							map.put(link, preferenceBean);
						}else{
						
						labours= new String[previousBean.getLinks().length+previousBean.getTopics().length+oldlink.length];
						int i=0;
						for(String t:previousBean.getTopics()){
							labours[i]=t;
							i++;
						}
						for(String l:previousBean.getLinks()){
							labours[i]=l;
							i++;
						}
						for(String o:oldlink){
							labours[i]=o;
							i++;
						}
						preferenceBean.setInlink(labours);
						map.put(link, preferenceBean);
						}
					}
				}
				
			}
			
			previousBean=entry;
			
		}
		HashMap<String,PreferenceBean> results= new HashMap<String, PreferenceBean>();
		for(Entry<String,PreferenceBean> entry:map.entrySet()){
			String key=entry.getKey();
			ArrayList<String> chk = new ArrayList<String>();
			for(String link:entry.getValue().getInlink()){
				if(!chk.contains(link)){
					chk.add(link);
				}
			}
			PreferenceBean bean = new PreferenceBean();
			bean.setType(entry.getValue().getType());
			String[] links= new String[chk.size()];
			int i=0;
			for(String link:chk){
				links[i]=link;
				i++;
			}
			bean.setInlink(links);
			results.put(key, bean);
		}
//		for(Entry<String,PreferenceBean> entry:results.entrySet()){
//			String key=entry.getKey();
//			System.out.print("key: " +key +" link: ");
//			for(String link:entry.getValue().getInlink()){
//				System.out.print(link +" ");
//			}
//			System.out.println();
//		}
		
		return results;
	}
	
	
	public HashMap<String,ArrayList<String>> queryPreferenceTrackGenerate(HashMap<String,SearchBean> outwithPreference ){
		HashMap<String, ArrayList<String>> map= new HashMap<String, ArrayList<String>>();
		for(Entry<String,SearchBean> entry:outwithPreference.entrySet()){
			String hit= entry.getKey();
			String preference=entry.getValue().getPreference();
			preference=preference.substring(preference.lastIndexOf("/")+1,preference.length());
			if(map.get(preference)==null){
				ArrayList<String> list = new ArrayList<String>();
				list.add(hit);
				map.put(preference, list);
			}else{
				map.get(preference).add(hit);
			}
		}
		
		return map;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonGenerator g= new JsonGenerator();
		SearchBean bean1 = new SearchBean();
		bean1.setPreference("p1");
		SearchBean bean2 = new SearchBean();
		bean2.setPreference("p1");
		SearchBean bean3 = new SearchBean();
		bean3.setPreference("p2");
		SearchBean bean4 = new SearchBean();
		bean4.setPreference("p3");
		SearchBean bean5 = new SearchBean();
		bean5.setPreference("p1");
		SearchBean bean6 = new SearchBean();
		bean6.setPreference("p3");
		SearchBean bean7 = new SearchBean();
		bean7.setPreference("p2");
		SearchBean bean8 = new SearchBean();
		bean8.setPreference("p1");
		HashMap<String,SearchBean> outwithPreference = new HashMap<String, SearchBean>();
		outwithPreference.put("link1", bean1);
		outwithPreference.put("link2", bean2);
		outwithPreference.put("link3", bean3);
		outwithPreference.put("link4", bean4);
		outwithPreference.put("link5", bean5);
		outwithPreference.put("link6", bean6);
		outwithPreference.put("link7", bean7);
		outwithPreference.put("link8", bean8);
		System.out.println(outwithPreference);
		System.out.println(g.queryPreferenceTrackGenerate(outwithPreference));
	}

}
