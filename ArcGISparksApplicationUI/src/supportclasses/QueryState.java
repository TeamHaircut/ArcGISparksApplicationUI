package supportclasses;

import java.util.Map;

import javafx.collections.ObservableList;
import entities.Designation;
import entities.State;
import entities.Site;

public class QueryState {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String buildQuery(Map queryDataMap) {
		String query = "";
		
		query = "Select x from Site x";
		
		if(		Integer.parseInt(queryDataMap.get("visitId").toString()) == 2		) {
			
		}
		else {
			query = query.concat(" where x.visited = "+queryDataMap.get("visitId").toString()); 
		}
		
		if(query.length() <= 20) {
			query = query.concat(" where");
		}
		else {
			query = query.concat(" and");
		}
		
		if(!((ObservableList<Designation>)queryDataMap.get("desList")).isEmpty()) {
			String desIdString = "";
			for(	Designation d: ((ObservableList<Designation>)queryDataMap.get("desList"))	) {
				desIdString = desIdString.concat(""+d.getDesignation_id()+",");
			}
			desIdString = desIdString.substring(0, desIdString.length()-1);
			
			query = query.concat(" x.designation_id in ("+desIdString+")");
		}
		else {
			query = query.substring(0, query.lastIndexOf(" "));
		}
		if(query.length() <= 20) {
			query = query.concat(" where");
		}
		else {
			query = query.concat(" and");
		}
		
		if(!((ObservableList<State>)queryDataMap.get("stateList")).isEmpty()) {
			String stateIdString = "";
			for(	State s: ((ObservableList<State>)queryDataMap.get("stateList"))	) {
				stateIdString = stateIdString.concat(""+s.getState_id()+",");
			}
			stateIdString = stateIdString.substring(0, stateIdString.length()-1);
			
			query = query.concat(" x.state_id in ("+stateIdString+")");
		}
		else {
			query = query.substring(0, query.lastIndexOf(" "));
		}
		
		
		System.out.println(query);
		
		return query;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String buildQuery1(Map queryDataMap) {
		String query = "";
		
		if(!((ObservableList<Site>)queryDataMap.get("siteList")).isEmpty()) {
			query = "Select x from Site x where";
			String siteIdString = "";
			for(	Site s: ((ObservableList<Site>)queryDataMap.get("siteList"))	) {
				siteIdString = siteIdString.concat(""+s.getSite_id()+",");
			}
			siteIdString = siteIdString.substring(0, siteIdString.length()-1);
			
			query = query.concat(" x.site_id in ("+siteIdString+")");
		}
		else {
			if(!query.equals(""))
			query = query.substring(0, query.lastIndexOf(" "));
			else query = null;
		}
		return query;
	}
}
