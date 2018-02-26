package unittestcases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.junit.Test;

public class TestCase {
	
	@Test
	public void test() throws IOException {
		String testURL0 = "https://www.nps.gov/yell/index.htm";
		String testURL1 = "https://www.nps.gov/grca/index.htm";
		String testURL2 = "https://www.nps.gov/acad/index.htm";
		String testURL3 = "https://www.nps.gov/index.htm";
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
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        line = br.readLine();
		        if(line.matches("^.*<meta property=\"og:image\".*$")) {
		        	int indexStart = line.indexOf("https");
		        	int indexEnd = line.indexOf(" />");
		        	imageURL = line.substring(indexStart, indexEnd-1);
		        	System.out.println(imageURL);
		        	break;
		        }
		        
		    }
		} finally {
		    br.close();
		}
		//
		return imageURL;
	}
	

}
