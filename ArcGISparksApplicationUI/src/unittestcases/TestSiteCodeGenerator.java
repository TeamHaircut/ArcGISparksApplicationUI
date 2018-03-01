package unittestcases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestSiteCodeGenerator {
	
	@Test
	public void testDirectoryBuilder() {
		List<String> data = codeGenerator();
		
		for(String code : data) {
			new File("TempFolder312018\\"+code).mkdirs();
		}
		
	}
	
	private List<String> codeGenerator() {
		List<String> data = populateData();
		List<String> output = new ArrayList<String>();
		
		for(String x : data) {
			String code = generateSymbol(x);
			output.add(code);
		}
		return output;
	}
	
	private String generateSymbol(String symbol)
	{
		String temp = symbol;
		try{
			int count = 0; int spaceIndex = 0;
			for(int i = 0; i < symbol.length(); i++) {
				if(count == 0) {
					temp = temp.substring(0,4);
				}
				if(	symbol.charAt(i)== ' '){
					if(count == 0)
					count++; 
					if(spaceIndex == 0)
					spaceIndex = i;
				}
				if(temp.length() <= 4 && symbol.charAt(spaceIndex)== ' ') {
					if(count == 1) {
						temp = temp.substring(0, 2)+symbol.substring(spaceIndex+1, spaceIndex+3);		
					}
				}
			}
		} catch(Exception e) {
			temp = symbol +"    ";
			temp = temp.substring(0, 4);	
		}
		return temp;
		
	}//end generate symbol
	
	//Data
	private List<String> populateData() {
		List<String> data = new ArrayList<String>();
		//data goes here
		   data.add("Antietam");
		   data.add("Big Hole");
		   data.add("Cowpens");
		   data.add("Fort Donelson");
		   data.add("Fort Necessity");
		   data.add("Monocacy");
		   data.add("Moores Creek");
		   data.add("Petersburg");
		   data.add("Stones River");
		   data.add("Tupelo");
		   data.add("Wilson's Creek");
		   data.add("Kennesaw Mountain");
		   data.add("Manassas");
		   data.add("Richmond");
		   data.add("River Raisin");
		   data.add("Brices Cross Roads");
		   data.add("Chickamauga and Chattanooga");
		   data.add("Fredericksburg and Spotsylvania County Battlefields Memorial");
		   data.add("Gettysburg");
		   data.add("Guilford Courthouse");
		   data.add("Horseshoe Bend");
		   data.add("Kings Mountain");
		   data.add("Pea Ridge");
		   data.add("Shiloh");
		   data.add("Vicksburg");
		   data.add("Abraham Lincoln Birthplace");
		   data.add("Adams");
		   data.add("Appomattox Court House");
		   data.add("Blackstone River Valley");
		   data.add("Boston");
		   data.add("Cane River Creole");
		   data.add("Cedar Creek and Belle Grove");
		   data.add("Chaco Culture");
		   data.add("Chesapeake and Ohio Canal");
		   data.add("Colonial");
		   data.add("Cumberland Gap");
		   data.add("Dayton Aviation Heritage");
		   data.add("First State");
		   data.add("George Rogers Clark");
		   data.add("Harpers Ferry");
		   data.add("Harriet Tubman Underground Railroad");
		   data.add("Hopewell Culture");
		   data.add("Independence");
		   data.add("Jean Lafitte National Historical Park and Preserve");
		   data.add("Kalaupapa");
		   data.add("Kaloko-Honokohau");
		   data.add("Keweenaw");
		   data.add("Klondike Gold Rush");
		   data.add("Lewis and Clark");
		   data.add("Lowell");
		   data.add("Lyndon B. Johnson");
		   data.add("Manhattan Project");
		   data.add("Marsh-Billings-Rockefeller");
		   data.add("Minute Man");
		   data.add("Morristown");
		   data.add("Natchez");
		   data.add("New Bedford Whaling");
		   data.add("New Orleans Jazz");
		   data.add("Nez Perce");
		   data.add("Palo Alto Battlefield");
		   data.add("Paterson Great Falls");
		   data.add("Pecos");
		   data.add("Pu'uhonua o Honaunau");
		   data.add("Rosie the Riveter/World War II Home Front");
		   data.add("Salt River Bay National Historical Park and Ecological Preserve");
		   data.add("San Antonio Missions");
		   data.add("San Francisco Maritime");
		   data.add("San Juan Island");
		   data.add("Saratoga");
		   data.add("Sitka");
		   data.add("Thomas Edison");
		   data.add("Tumacacori");
		   data.add("Valley Forge");
		   data.add("War in the Pacific");
		   data.add("Women's Rights");
		   data.add("Allegheny Portage Railroad");
		   data.add("Andersonville");
		   data.add("Andrew Johnson");
		   data.add("Bent's Old Fort");
		   data.add("Boston African American");
		   data.add("Brown v.  Board of Education");
		   data.add("Carl Sandburg Home");
		   data.add("Carter G.  Woodson Home");
		   data.add("Charles Pinckney");
		   data.add("Christiansted");
		   data.add("Clara Barton");
		   data.add("Edgar Allan Poe");
		   data.add("Eisenhower");
		   data.add("Eleanor Roosevelt");
		   data.add("Eugene O'Neill");
		   data.add("First Ladies");
		   data.add("Ford's Theatre");
		   data.add("Fort Bowie");
		   data.add("Fort Davis");
		   data.add("Fort Laramie");
		   data.add("Fort Larned");
		   data.add("Fort Point");
		   data.add("Fort Raleigh");
		   data.add("Fort Scott");
		   data.add("Fort Smith");
		   data.add("Fort Union Trading Post");
		   data.add("Fort Vancouver");
		   data.add("Frederick Douglass");
		   data.add("Frederick Law Olmsted");
		   data.add("Friendship Hill");
		   data.add("Golden Spike");
		   data.add("Grant-Kohrs Ranch");
		   data.add("Hampton");
		   data.add("Harry S Truman");
		   data.add("Herbert Hoover");
		   data.add("Home of Franklin D. Roosevelt");
		   data.add("Hopewell Furnace");
		   data.add("Hubbell Trading Post");
		   data.add("James A.  Garfield");
		   data.add("Jimmy Carter");
		   data.add("John Fitzgerald Kennedy");
		   data.add("John Muir");
		   data.add("Knife River Indian Villages");
		   data.add("Lincoln Home");
		   data.add("Little Rock Central High School");
		   data.add("Longfellow House - Washington's Headquarters");
		   data.add("Maggie L.  Walker");
		   data.add("Manzanar");
		   data.add("Martin Luther King, Jr.");
		   data.add("Martin Van Buren");
		   data.add("Mary McLeod Bethune Council House");
		   data.add("Minidoka");
		   data.add("Minuteman Missile");
		   data.add("Nicodemus");
		   data.add("Ninety Six");
		   data.add("Pennsylvania Avenue");
		   data.add("President William Jefferson Clinton Birthplace Home");
		   data.add("Pu'ukohola Heiau");
		   data.add("Sagamore Hill");
		   data.add("Saint-Gaudens");
		   data.add("Saint Paul's Church");
		   data.add("Salem Maritime");
		   data.add("San Juan");
		   data.add("Sand Creek Massacre");
		   data.add("Saugus Iron Works");
		   data.add("Springfield Armory");
		   data.add("Steamtown");
		   data.add("Theodore Roosevelt Birthplace");
		   data.add("Theodore Roosevelt Inaugural");
		   data.add("Thomas Stone");
		   data.add("Tuskegee Airmen");
		   data.add("Tuskegee Institute");
		   data.add("Ulysses S.  Grant");
		   data.add("Vanderbilt Mansion");
		   data.add("Washita Battlefield");
		   data.add("Weir Farm");
		   data.add("Whitman Mission");
		   data.add("William Howard Taft");
		   data.add("Saint Croix Island");
		   data.add("Apostle Islands");
		   data.add("Indiana Dunes");
		   data.add("Pictured Rocks");
		   data.add("Sleeping Bear Dunes");
		   data.add("Arkansas Post");
		   data.add("Arlington House, The Robert E. Lee Memorial");
		   data.add("Chamizal");
		   data.add("Coronado");
		   data.add("De Soto");
		   data.add("Federal Hall");
		   data.add("Flight 93");
		   data.add("Fort Caroline");
		   data.add("Franklin Delano Roosevelt Memorial");
		   data.add("General Grant");
		   data.add("Hamilton Grange");
		   data.add("Jefferson National Expansion Memorial");
		   data.add("Korean War Veterans");
		   data.add("Johnstown Flood");
		   data.add("Lincoln Boyhood");
		   data.add("Lincoln Memorial");
		   data.add("Lyndon Baines Johnson Memorial Grove on the Potomac");
		   data.add("Martin Luther King, Jr. Memorial");
		   data.add("Mount Rushmore");
		   data.add("Perry's Victory and International Peace Memorial");
		   data.add("Port Chicago Naval Magazine");
		   data.add("Roger Williams");
		   data.add("Thaddeus Kosciuszko");
		   data.add("Theodore Roosevelt Island");
		   data.add("Thomas Jefferson Memorial");
		   data.add("Vietnam Veterans Memorial");
		   data.add("Washington Monument");
		   data.add("World War I Memorial");
		   data.add("World War II Memorial");
		   data.add("Wright Brothers");
		   data.add("African Burial Ground");
		   data.add("Agate Fossil Beds");
		   data.add("Alibates Flint Quarries");
		   data.add("Aniakchak");
		   data.add("Aztec Ruins");
		   data.add("Bandelier");
		   data.add("Belmont-Paul Women’s Equality");
		   data.add("Booker T.  Washington");
		   data.add("Buck Island Reef");
		   data.add("Cabrillo");
		   data.add("Canyon de Chelly");
		   data.add("Cape Krusenstern");
		   data.add("Capulin Volcano");
		   data.add("Casa Grande Ruins");
		   data.add("Castillo de San Marcos");
		   data.add("Castle Clinton");
		   data.add("Castle Mountains");
		   data.add("Cedar Breaks");
		   data.add("Cesar E.  Chavez");
		   data.add("Charles Young Buffalo Soldiers");
		   data.add("Chiricahua");
		   data.add("Colorado");
		   data.add("Craters of the Moon");
		   data.add("Devils Postpile");
		   data.add("Devils Tower");
		   data.add("Dinosaur");
		   data.add("Effigy Mounds");
		   data.add("El Malpais");
		   data.add("El Morro");
		   data.add("Florissant Fossil Beds");
		   data.add("Fort Frederica");
		   data.add("Fort Matanzas");
		   data.add("Fort McHenry National Monument and Historic Shrine");
		   data.add("Fort Monroe");
		   data.add("Fort Pulaski");
		   data.add("Fort Stanwix");
		   data.add("Fort Sumter");
		   data.add("Fort Union");
		   data.add("Fossil Butte");
		   data.add("George Washington Birthplace");
		   data.add("George Washington Carver");
		   data.add("Gila Cliff Dwellings");
		   data.add("Governors Island");
		   data.add("Grand Portage");
		   data.add("Hagerman Fossil Beds");
		   data.add("Hohokam Pima");
		   data.add("Homestead NM of America");
		   data.add("Honouliuli National Monument");
		   data.add("Hovenweep");
		   data.add("Jewel Cave");
		   data.add("John Day Fossil Beds");
		   data.add("Lava Beds");
		   data.add("Little Bighorn Battlefield");
		   data.add("Montezuma Castle");
		   data.add("Muir Woods");
		   data.add("Natural Bridges");
		   data.add("Navajo");
		   data.add("Ocmulgee");
		   data.add("Oregon Caves National Monument and Preserve");
		   data.add("Organ Pipe Cactus");
		   data.add("Petroglyph");
		   data.add("Pipe Spring");
		   data.add("Pipestone");
		   data.add("Poverty Point");
		   data.add("Pullman National Monument");
		   data.add("Rainbow Bridge");
		   data.add("Russell Cave");
		   data.add("Salinas Pueblo Missions");
		   data.add("Scotts Bluff");
		   data.add("Statue of Liberty");
		   data.add("Stonewall National Monument");
		   data.add("Sunset Crater Volcano");
		   data.add("Timpanogos Cave");
		   data.add("Tonto");
		   data.add("Tule Springs Fossil Beds");
		   data.add("Tuzigoot");
		   data.add("Virgin Islands Coral Reef");
		   data.add("Waco Mammoth");
		   data.add("Walnut Canyon");
		   data.add("White Sands");
		   data.add("World War II Valor in the Pacific");
		   data.add("Wupatki");
		   data.add("Yucca House");
		   data.add("Acadia");
		   data.add("Arches");
		   data.add("Badlands");
		   data.add("Big Bend");
		   data.add("Biscayne");
		   data.add("Black Canyon of the Gunnison");
		   data.add("Bryce Canyon");
		   data.add("Canyonlands");
		   data.add("Capitol Reef");
		   data.add("Carlsbad Caverns");
		   data.add("Channel Islands");
		   data.add("Congaree");
		   data.add("Crater Lake");
		   data.add("Cuyahoga Valley");
		   data.add("Death Valley");
		   data.add("Denali");
		   data.add("Dry Tortugas");
		   data.add("Everglades");
		   data.add("Gates of the Arctic");
		   data.add("Glacier Bay");
		   data.add("Glacier");
		   data.add("Grand Canyon");
		   data.add("Grand Teton");
		   data.add("Great Basin");
		   data.add("Great Sand Dunes");
		   data.add("Great Smoky Mountains");
		   data.add("Guadalupe Mountains");
		   data.add("Haleakala");
		   data.add("Hawai‘i Volcanoes");
		   data.add("Hot Springs");
		   data.add("Isle Royale");
		   data.add("Joshua Tree");
		   data.add("Katmai");
		   data.add("Kenai Fjords");
		   data.add("Kings Canyon");
		   data.add("Kobuk Valley");
		   data.add("Lake Clark");
		   data.add("Lassen Volcanic");
		   data.add("Mammoth Cave");
		   data.add("Mesa Verde");
		   data.add("Mount Rainier");
		   data.add("National Park of American Samoa");
		   data.add("North Cascades");
		   data.add("Olympic");
		   data.add("Petrified Forest");
		   data.add("Pinnacles");
		   data.add("Redwood");
		   data.add("Rocky Mountain");
		   data.add("Saguaro");
		   data.add("Sequoia");
		   data.add("Shenandoah");
		   data.add("Theodore Roosevelt");
		   data.add("Virgin Islands");
		   data.add("Voyageurs");
		   data.add("Wind Cave");
		   data.add("Wrangell-St.  Elias");
		   data.add("Yellowstone");
		   data.add("Yosemite");
		   data.add("Zion");
		   data.add("Blue Ridge Parkway");
		   data.add("George Washington Memorial Parkway");
		   data.add("John D.  Rockefeller Jr.  Memorial Parkway");
		   data.add("Natchez Trace Parkway");
		   data.add("Aniakchak");
		   data.add("Bering Land Bridge");
		   data.add("Big Cypress");
		   data.add("Big Thicket");
		   data.add("Craters of the Moon");
		   data.add("Denali");
		   data.add("Gates of the Arctic");
		   data.add("Glacier Bay");
		   data.add("Great Sand Dunes");
		   data.add("Katmai");
		   data.add("Lake Clark");
		   data.add("Little River Canyon");
		   data.add("Mojave");
		   data.add("Noatak");
		   data.add("Tallgrass Prairie");
		   data.add("Timucuan Ecological and Historic Preserve");
		   data.add("Valles Caldera");
		   data.add("Wrangell-St. Elias");
		   data.add("Yukon-Charley Rivers");
		   data.add("City of Rocks");
		   data.add("Ebey's Landing National Historical Reserve");
		   data.add("Amistad");
		   data.add("Bighorn Canyon");
		   data.add("Boston Harbor Islands");
		   data.add("Chattahoochee River");
		   data.add("Chickasaw");
		   data.add("Curecanti");
		   data.add("Delaware Water Gap");
		   data.add("Gateway");
		   data.add("Gauley River");
		   data.add("Glen Canyon");
		   data.add("Golden Gate");
		   data.add("Lake Chelan");
		   data.add("Lake Mead");
		   data.add("Lake Meredith");
		   data.add("Lake Roosevelt");
		   data.add("Ross Lake");
		   data.add("Santa Monica Mountains");
		   data.add("Whiskeytown Unit, Whiskeytown-Shasta-Trinity");
		   data.add("Big South Fork National River and Recreation Area");
		   data.add("Buffalo National River");
		   data.add("New River Gorge National River");
		   data.add("Mississippi National River and Recreation Areas");
		   data.add("Ozark National Scenic Riverways");
		   data.add("Alagnak Wild River");
		   data.add("Bluestone National Scenic River");
		   data.add("Delaware National Scenic River");
		   data.add("Great Egg Harbor National Scenic and Recreational River");
		   data.add("Missouri National Recreational River");
		   data.add("Niobrara National Scenic River");
		   data.add("Obed Wild and Scenic River");
		   data.add("Rio Grande Wild and Scenic River");
		   data.add("Saint Croix National Scenic Riverway");
		   data.add("Upper Delaware Scenic and Recreation River");
		   data.add("Appalachian National Scenic Trail");
		   data.add("Natchez Trace National Scenic Trail");
		   data.add("Potomac Heritage National Scenic Trail");
		   data.add("Assateague Island");
		   data.add("Canaveral");
		   data.add("Cape Cod");
		   data.add("Cape Hatteras");
		   data.add("Cape Lookout");
		   data.add("Cumberland Island");
		   data.add("Fire Island");
		   data.add("Gulf Islands");
		   data.add("Padre Island");
		   data.add("Point Reyes");
		   data.add("Catoctin Mountain Park");
		   data.add("Constitution Gardens");
		   data.add("Fort Washington Park");
		   data.add("Greenbelt Park");
		   data.add("National Capital Parks");
		   data.add("National Mall");
		   data.add("Piscataway Park");
		   data.add("Prince William Forest Park");
		   data.add("Rock Creek Park");
		   data.add("White House");
		   data.add("Wolf Trap National Park for the Performing Arts");

		//end of data
		return data;
	}
	
	
	

}
