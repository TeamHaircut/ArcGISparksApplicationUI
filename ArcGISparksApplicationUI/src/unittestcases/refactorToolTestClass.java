package unittestcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class refactorToolTestClass {
	
	@Test
	public void refactorToolTest() throws IOException {
		
		
		List<String> searchList = new ArrayList<String>();
		List<String> resultsList = new ArrayList<String>();
		searchList = getSearchList();
		resultsList = getSearchList();
		BufferedReader br = new BufferedReader(new FileReader("view0.fxml"));
		try {
		    String line = br.readLine();

		    while (line != null) {
		        line = br.readLine();
		        for(String term: searchList) {
			        if(line.matches("^.*"+term+".*$")) {
			        	resultsList.remove(term);
			        }
		        }
		        
		    }
		} finally {
			System.out.println("Done "+resultsList);
		    br.close();
		}
		
		
	}
	
	private ArrayList<String> getSearchList() {
		ArrayList<String> searchList = new ArrayList<String>();
		
		searchList.add("titledpane1");
		searchList.add("borderPane");
		searchList.add("titledpane2");
		searchList.add("resultsTab");
		
		searchList.add("listview1");
		searchList.add("listview2");
		searchList.add("hyperlink");
		searchList.add("hyperlink1");
		
		searchList.add("radio0BTN");
		searchList.add("radio1BTN");
		searchList.add("radio2BTN");
		
		searchList.add("lvSite1");
		searchList.add("lvSite2");
		
		searchList.add("addAllSiteBTN");
		searchList.add("removeAllSiteBTN");
		searchList.add("addSiteBTN");
		searchList.add("removeSiteBTN");
		
		searchList.add("lvDes1");
		searchList.add("lvDes2");
		
		searchList.add("addAllDesBTN");
		searchList.add("removeAllDesBTN");
		searchList.add("addDesBTN");
		searchList.add("removeDesBTN");
		
		searchList.add("lvState1");
		searchList.add("lvState2");
		
		searchList.add("addAllStateBTN");
		searchList.add("removeAllStateBTN");
		searchList.add("addStateBTN");
		searchList.add("removeStateBTN");
		
		searchList.add("lvRegion1");
		searchList.add("lvRegion2");
		
		searchList.add("addAllRegionBTN");
		searchList.add("removeAllRegionBTN");
		searchList.add("addRegionBTN");
		searchList.add("removeRegionBTN");
		
		searchList.add("webview1");
		searchList.add("webImage");
		
		searchList.add("designationLabel");
		searchList.add("mapBTN");
		searchList.add("photoBTN");
		searchList.add("stampBTN");
		searchList.add("patchBTN");
		searchList.add("campBTN");
		
		searchList.add("pictureCloseBTN");
		return searchList;
	}

}
