package supportclasses;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Site;
import javafx.scene.image.Image;

public class NPMap {
	public static Map<String, Integer> npMap = new HashMap<String, Integer>();
	
	public static void initializeNPMap() {
		npMap.put("Acadia",2554);
		npMap.put("Arches",2573);
		npMap.put("Badlands",2578);
		npMap.put("Big Bend",2584);
		npMap.put("Biscayne",2588);
		npMap.put("Black Canyon of the Gunnison",2592);
		npMap.put("Bryce Canyon",2599);
		npMap.put("Canyonlands",2616);
		npMap.put("Capitol Reef",2617);
		npMap.put("Carlsbad Caverns",2622);
		npMap.put("Channel Islands",2631);
		npMap.put("Congaree",2644);
		npMap.put("Crater Lake",2647);
		npMap.put("Cuyahoga Valley",2652);
		npMap.put("Denali",2658);
		npMap.put("Death Valley",2662);
		npMap.put("Dry Tortugas",2665);
		npMap.put("Everglades",2677);
		npMap.put("Gates of the Arctic",2716);
		npMap.put("Glacier",2725);
		npMap.put("Glacier Bay",2726);
		npMap.put("Great Basin",2732);
		npMap.put("Grand Canyon",2733);
		npMap.put("Great Sand Dunes",2738);
		npMap.put("Great Smoky Mountains",2739);
		npMap.put("Guadalupe Mountains",2744);
		npMap.put("Haleakala",2751);
		npMap.put("Hawai‘i Volcanoes",2753);
		npMap.put("Hot Springs",2760);
		npMap.put("Isle Royale",2769);
		npMap.put("Joshua Tree",2782);
		npMap.put("Katmai",2786);
		npMap.put("Kenai Fjords",2787);
		npMap.put("Kobuk Valley",2795);
		npMap.put("Lake Clark",2799);
		npMap.put("Lassen Volcanic",2803);
		npMap.put("Mammoth Cave",2818);
		npMap.put("Mesa Verde",2824);
		npMap.put("Mount Rainier",2835);
		npMap.put("North Cascades",2845);
		npMap.put("National Park of American Samoa",2847);
		npMap.put("Petrified Forest",2856);
		npMap.put("Olympic",2881);
		npMap.put("Pinnacles",2893);
		npMap.put("Rocky Mountain",2907);
		npMap.put("Redwood",2901);
		npMap.put("Saguaro",2917);
		npMap.put("Sequoia",2931);
		npMap.put("Kings Canyon",2931);
		npMap.put("Shenandoah",2933);
		npMap.put("Theodore Roosevelt",2949);
		npMap.put("Virgin Islands",2967);
		npMap.put("Voyageurs",2970);
		npMap.put("Wind Cave",2980);
		npMap.put("Wrangell-St.  Elias",2986);
		npMap.put("Yellowstone",2988);
		npMap.put("Yosemite",2991);
		npMap.put("Zion",2994);
		npMap.put("Grand Teton",13525);
	}
	
	public static Map<String, Image> bannerMap = new HashMap<String, Image>();
	public static boolean isInitialized = false;
	
	public static void initializeBannerMap(List<Site> listofsites) throws IOException {
		isInitialized = true;
		for(Site s: listofsites) {
			bannerMap.put(s.getSite_name(),new Image(getImageURL(s.getWebsite())));

		}
	}
	
	private static String getImageURL(String url) throws IOException {
		String imageURL = "";
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
		BufferedReader br = new BufferedReader(new FileReader("information.txt"));
		try {
		    String line = br.readLine();
		    while (line != null && imageURL.equals("")) {
		        line = br.readLine();
		        if(line.matches("^.*<meta property=\"og:image\".*$")) {
		        	int indexStart = line.indexOf("https");
		        	int indexEnd = line.indexOf(" />");
		        	imageURL = line.substring(indexStart, indexEnd-1);
		        }
		        
		    }
		} finally {
		    br.close();
		}
		return imageURL;
	}

}
