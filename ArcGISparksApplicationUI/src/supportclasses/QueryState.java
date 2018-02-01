package supportclasses;

import java.util.Map;

import javafx.collections.ObservableList;
import entities.Designation;

public class QueryState {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String buildQuery(Map queryDataMap) {
		String query = "";
		//
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
		
		System.out.println(query);
		
		return query;
		
	}

}
