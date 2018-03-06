package supportclasses;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import entities.Site;
import models.ParkModel;
import unittestcases.TestCase.ParserState;

public class WebCrawler {
	
	public static ArrayList<String> crawl() throws IOException {
		
		Site s = ParkModel.getMySite();
		int code = NPMap.npMap.get(s.getSite_name());
		
		ArrayList<String> myList = new ArrayList<String>();
		String testURL0 = "https://www.recreation.gov/recreationalAreaDetails.do?contractCode=NRSO&recAreaId="+code+"&agencyCode=70904";
		myList = getCampGroundList(testURL0);
		
		return myList;
	}
	
	@SuppressWarnings("finally")
	private static ArrayList<String> getCampGroundList(String url) throws IOException {
		ParserState parserState = ParserState.NONE;
		ArrayList<String> dataList = new ArrayList<String>();
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
		BufferedReader br = new BufferedReader(new FileReader("information.txt"));
		try {
		    String line = br.readLine();

		    while (line != null) {
		        line = br.readLine();
		        if(parserState == ParserState.NONE) {
		        	if(line.matches("^.*MAKE A RESERVATION.*$")) {
			        	parserState = ParserState.CAMPGROUND;
			        }
		        }
		        if(parserState == ParserState.CAMPGROUND) {
		        	if(line.matches("^.*\\d'    >.*$")) {
		        		int index = 0;
		        		int indexStart = 0;
		        		int indexEnd = 0;
		        		while(indexStart <= index) {
		        			indexStart = line.substring(index).indexOf("    >")+index;
			        		indexEnd = line.substring(indexStart).indexOf("&")+indexStart;
			        		dataList.add(line.substring(indexStart+5, indexEnd).toUpperCase());
			        		index = indexEnd;
		        		}
			        	parserState = ParserState.NONE;
		        	}
		        }
		    }
		} finally {
		    br.close();
		    return dataList;
		}
	}
	

}
