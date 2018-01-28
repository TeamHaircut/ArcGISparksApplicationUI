package supportclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import entities.Designation;

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
		
		String desIdString = "";
		for(	Designation d: ((ObservableList<Designation>)queryDataMap.get("desList"))	) {
//			query = query.concat(" x.designation_id = "+d.getDesignation_id());
//			query = query.concat(" or");
			desIdString = desIdString.concat(""+d.getDesignation_id()+",");
		}
		desIdString = desIdString.substring(0, desIdString.length()-1);
		
		query = query.concat(" x.designation_id in ("+desIdString+")");
		
//		query = query.substring(0, query.length()-3);
		System.out.println(query);
		
		return query;
		
	}

}
