package unittestcases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import org.junit.Test;

public class TestCase {
	
	@Test
	public void test() throws IOException {
		String testURL0 = "https://www.nps.gov/yell/index.htm";
//		String testURL1 = "https://www.nps.gov/grca/index.htm";
//		String testURL2 = "https://www.nps.gov/acad/index.htm";
//		String testURL3 = "https://www.nps.gov/index.htm";
		assertEquals("https://www.nps.gov/common/uploads/banner_image/imr/homepage/51D13BEA-1DD8-B71B-0B786860A6FE90FC.jpg",
				getImageTest(testURL0));
	}
	
	private String getImageTest(String url) throws IOException {
		String imageURL = "";
		//
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		BufferedReader br = new BufferedReader(new FileReader("information.txt"));
		try {
		    String line = br.readLine();

		    while (line != null) {
		        line = br.readLine();
		        if(line.matches("^.*<meta property=\"og:image\".*$")) {
		        	int indexStart = line.indexOf("https");
		        	int indexEnd = line.indexOf(" />");
		        	imageURL = line.substring(indexStart, indexEnd-1);
		        	System.out.println(imageURL);
		        }
		        
		    }
		} finally {
			fos.close();
		    br.close();
		}
		//
		return imageURL;
	}
	
	@Test
	public void getRecAreaIdsTest() throws IOException {
		
		for(int i = 2500; i < 3500; i++){
			String testURL0 = "https://www.recreation.gov/recreationalAreaDetails.do?contractCode=NRSO&recAreaId="+i+"&agencyCode=70904";
			String name = getRecAreaIds(testURL0);
			if(name.length()> 5 && name.endsWith("1") && name.contains("National Park")) {
				System.out.println(i+" " +name.substring(0, name.length()-2));
			}
		}	
	}
	
	@SuppressWarnings("finally")
	private String getRecAreaIds(String url) throws IOException {
		String imageURL = "";
		//
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		BufferedReader br = new BufferedReader(new FileReader("information.txt"));
		try {
		    String line = br.readLine();

		    while (line != null) {
		        line = br.readLine();
		        if(line.matches("^.*<title>Recreation.gov recreation area details.*$")) {
		        	int indexStart = line.indexOf("-");
		        	int indexEnd = line.lastIndexOf("-");
		        	imageURL = line.substring(indexStart, indexEnd-1);
		        }
		        if(line.matches("^.*<span class='locationsubtitle'>National Park Service</span></div>.*$")) {
		        	imageURL = imageURL+" 1";
		        }
		    }
		} finally {
			fos.close();
		    br.close();
		    return imageURL;
		}
	}
	
	@Test
	public void campGroundListTest() throws IOException {
		
		
		
		String testURL1 = "https://www.recreation.gov/recreationalAreaDetails.do?contractCode=NRSO&recAreaId=2933&agencyCode=70904";
		ArrayList<String> myList1 = new ArrayList<String>();
		myList1.add("BLACKWOODS CAMPGROUND");
		myList1.add("DUCK HARBOR CAMPGROUND");
		myList1.add("Schoodic Woods Campground");
		myList1.add("SEAWALL CAMPGROUND");		
		assertEquals(myList1,
				getCampGroundList(testURL1));
	}	
	
	public enum ParserState {
	    NONE, CAMPGROUND 
	}
	
	@SuppressWarnings("finally")
	private ArrayList<String> getCampGroundList(String url) throws IOException {
		ParserState parserState = ParserState.NONE;
		//
		ArrayList<String> dataList = new ArrayList<String>();
		//
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
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
			        		dataList.add(line.substring(indexStart+5, indexEnd));
			        		
			        		index = indexEnd;
		        		}
		        		
			        	parserState = ParserState.NONE;
		        	}
		        }
		    }
		} finally {
			fos.close();
		    br.close();
		    return dataList;
		}
	}

}
