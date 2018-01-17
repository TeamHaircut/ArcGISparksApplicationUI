ALTER TABLE SITE DROP CONSTRAINT DESIGNATION_FK;
ALTER TABLE SITE DROP CONSTRAINT STATE_FK; 
ALTER TABLE STATE DROP CONSTRAINT REGION_FK;
ALTER TABLE REGION DROP CONSTRAINT COLOR_FK;

DROP TABLE COLOR;
DROP TABLE REGION;
DROP TABLE DESIGNATION;
DROP TABLE STATE;
DROP TABLE SITE;

CREATE TABLE COLOR (
	COLOR_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT COLOR_PK PRIMARY KEY,
	COLOR_NAME VARCHAR(30) NOT NULL UNIQUE,
	COLOR_HEX VARCHAR(7));
	
CREATE TABLE REGION (
	REGION_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT REGION_PK PRIMARY KEY,
	REGION_NAME VARCHAR(30) NOT NULL UNIQUE,
	REGION_COLOR_ID INT NOT NULL UNIQUE CONSTRAINT COLOR_FK REFERENCES REGION);

CREATE TABLE DESIGNATION (
	DESIGNATION_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT DESIGNATION_PK PRIMARY KEY,
	DESIGNATION_NAME VARCHAR(50) NOT NULL UNIQUE,
	DESIGNATION_ABBREVIATION VARCHAR(10) NOT NULL UNIQUE);

CREATE TABLE STATE (
	STATE_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT STATE_PK PRIMARY KEY,
	STATE_NAME VARCHAR(50) NOT NULL UNIQUE,
	STATE_ABBREVIATION VARCHAR(2) NOT NULL UNIQUE,
	REGION_ID INT CONSTRAINT REGION_FK REFERENCES REGION);

CREATE TABLE SITE (
	SITE_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT SITE_PK PRIMARY KEY, 
	SITE_NAME VARCHAR(50) NOT NULL UNIQUE,
	DESIGNATION_ID INT CONSTRAINT DESIGNATION_FK REFERENCES DESIGNATION,
	STATE_ID INT CONSTRAINT STATE_FK REFERENCES STATE,
	LAT DOUBLE NOT NULL,
	LON DOUBLE NOT NULL,
	WEBSITE VARCHAR(200),
	MAP VARCHAR(200),
	VISITED INT DEFAULT 0);

insert into color (color_name) values ('brown');
insert into color (color_name) values ('sky blue');
insert into color (color_name) values ('red');
insert into color (color_name) values ('purple');
insert into color (color_name) values ('orange');
insert into color (color_name) values ('grey');
insert into color (color_name) values ('yellow');
insert into color (color_name) values ('green');
insert into color (color_name) values ('blue');


insert into region (region_name, region_color_id) values ('North Atlantic',1);
insert into region (region_name, region_color_id) values ('Mid-Atlantic',2);
insert into region (region_name, region_color_id) values ('National Capital',3);
insert into region (region_name, region_color_id) values ('Southeast',4);
insert into region (region_name, region_color_id) values ('Midwest',5);
insert into region (region_name, region_color_id) values ('Southwest',6);
insert into region (region_name, region_color_id) values ('Rocky Mountain',7);
insert into region (region_name, region_color_id) values ('Western',8);
insert into region (region_name, region_color_id) values ('Pacific Northwest and Alaska',9);

insert into designation (designation_name, designation_abbreviation) values ('International Historic Site','IHS');
insert into designation (designation_name, designation_abbreviation) values ('National Battlefield','NB');
insert into designation (designation_name, designation_abbreviation) values ('National Battlefield Park','NBP');
insert into designation (designation_name, designation_abbreviation) values ('National Battlefield Site','NBS');
insert into designation (designation_name, designation_abbreviation) values ('National Historical Park','NHP');
insert into designation (designation_name, designation_abbreviation) values ('Nat''l Historical Park & Ecological Pres','NHP & EP');
insert into designation (designation_name, designation_abbreviation) values ('Nat''l Historical Park & Preserve','NHP & PRES');
insert into designation (designation_name, designation_abbreviation) values ('National Historical Reserve','NH RES');
insert into designation (designation_name, designation_abbreviation) values ('National Historical Site','NHS');
insert into designation (designation_name, designation_abbreviation) values ('National Lakeshore','NL');
insert into designation (designation_name, designation_abbreviation) values ('National Monument','N MON');
insert into designation (designation_name, designation_abbreviation) values ('National Monument & Preserve','NM & PRES');
insert into designation (designation_name, designation_abbreviation) values ('National Military Park','NMP');
insert into designation (designation_name, designation_abbreviation) values ('National Memorial','N MEM');
insert into designation (designation_name, designation_abbreviation) values ('National Park','NP');
insert into designation (designation_name, designation_abbreviation) values ('National & State Parks','N & SP');
insert into designation (designation_name, designation_abbreviation) values ('National Park & Preserve','NP & PRES');
insert into designation (designation_name, designation_abbreviation) values ('National Preserve','N PRES');
insert into designation (designation_name, designation_abbreviation) values ('National River','NR');
insert into designation (designation_name, designation_abbreviation) values ('National Recreation Area','NRA');
insert into designation (designation_name, designation_abbreviation) values ('National Recreational River','NRR');
insert into designation (designation_name, designation_abbreviation) values ('National River & Recreation Area','NRRA');
insert into designation (designation_name, designation_abbreviation) values ('National Reserve','N RES');
insert into designation (designation_name, designation_abbreviation) values ('National Seashore','NS');
insert into designation (designation_name, designation_abbreviation) values ('Nat''l Scenic River or Riverway','NSR');
insert into designation (designation_name, designation_abbreviation) values ('National Scenic Trail','NST');
insert into designation (designation_name, designation_abbreviation) values ('Parkway','PKWY');
insert into designation (designation_name, designation_abbreviation) values ('Scenic & Recreational River','SRR');
insert into designation (designation_name, designation_abbreviation) values ('Wild River','WR');
insert into designation (designation_name, designation_abbreviation) values ('Wild & Scenic River','WSR');

--region_name wildcards No%     Mid-%     Na%     Southe%     Midw%     Southw%     R%     W%     P%
insert into state (state_name, state_abbreviation, region_id) select 'Alabama','AL', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Alaska','AK', region_id from region where region_name like 'P%';
insert into state (state_name, state_abbreviation, region_id) select 'Arizona','AZ', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Arkansas','AR', region_id from region where region_name like 'Southw%';
insert into state (state_name, state_abbreviation, region_id) select 'California','CA', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Colorado','CO', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'Connecticut','CT', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'Delaware','DE', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'Florida','FL', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Georgia','GA', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Hawaii','HI', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Idaho','ID', region_id from region where region_name like 'P%';
insert into state (state_name, state_abbreviation, region_id) select 'Illinois','IL', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Indiana','IN', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Iowa',	'IA', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Kansas','KS', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Kentucky','KY', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Louisiana','LA', region_id from region where region_name like 'Southw%';
insert into state (state_name, state_abbreviation, region_id) select 'Maine','ME', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'Maryland','MD', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'Massachusetts','MA', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'Michigan','MI', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Minnesota','MN', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Mississippi','MS', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Missouri','MO', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Montana','MT', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'Nebraska','NE', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Nevada','NV', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'New Hampshire','NH', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'New Jersey','NJ', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'New Mexico','NM', region_id from region where region_name like 'Southw%';
insert into state (state_name, state_abbreviation, region_id) select 'New York','NY', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'North Carolina','NC', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'North Dakota',	'ND', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'Ohio',	'OH', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Oklahoma','OK', region_id from region where region_name like 'Southw%';
insert into state (state_name, state_abbreviation, region_id) select 'Oregon','OR', region_id from region where region_name like 'P%';
insert into state (state_name, state_abbreviation, region_id) select 'Pennsylvania','PA', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'Rhode Island','RI', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'South Carolina','SC', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'South Dakota','SD', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'Tennessee','TN', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Texas','TX', region_id from region where region_name like 'Southw%';
insert into state (state_name, state_abbreviation, region_id) select 'Utah','UT', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'Vermont','VT', region_id from region where region_name like 'No%';
insert into state (state_name, state_abbreviation, region_id) select 'Virginia','VA', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'Washington','WA', region_id from region where region_name like 'P%';
insert into state (state_name, state_abbreviation, region_id) select 'West Virginia','WV', region_id from region where region_name like 'Mid-%';
insert into state (state_name, state_abbreviation, region_id) select 'Wisconsin','WI', region_id from region where region_name like 'Midw%';
insert into state (state_name, state_abbreviation, region_id) select 'Wyoming','WY', region_id from region where region_name like 'R%';
insert into state (state_name, state_abbreviation, region_id) select 'American Samoa','AS', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'District of Columbia','DC', region_id from region where region_name like 'Na%';
insert into state (state_name, state_abbreviation, region_id) select 'Federated States of Micronesia','FM', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Guam','GU', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Marshall Islands','MH', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Northern Mariana Islands','MP', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Palau','PW', region_id from region where region_name like 'W%';
insert into state (state_name, state_abbreviation, region_id) select 'Puerto Rico','PR', region_id from region where region_name like 'Southe%';
insert into state (state_name, state_abbreviation, region_id) select 'Virgin Islands','VI', region_id from region where region_name like 'Southe%';

--ij> run 'C:/Users\RuthDan/Desktop/DanDesktop/dev/javadevelopment/lunaworkspace/ArcTest4/res/DBtablesCreate.txt';

insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Acadia',15,19,44.340313, -68.273396,'https://www.nps.gov/Acad/index.htm','https://www.nps.gov/Acad/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Arches',15,44,38.616580, -109.616662,'https://www.nps.gov/Arch/index.htm','https://www.nps.gov/Arch/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Badlands',15,41,43.853872, -102.350524,'https://www.nps.gov/Badl/index.htm','https://www.nps.gov/Badl/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Big Bend',15,43,29.159337, -103.257377,'https://www.nps.gov/BiBe/index.htm','https://www.nps.gov/BiBe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Biscayne',15,9,25.477785, -80.184273,'https://www.nps.gov/Bisc/index.htm','https://www.nps.gov/Bisc/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Black Canyon of the Gunnison',15,6,38.574441, -107.741514 ,'https://www.nps.gov/BlCa/index.htm','https://www.nps.gov/BlCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Bryce Canyon',15,44,37.592173, -112.187595,'https://www.nps.gov/BrCa/index.htm','https://www.nps.gov/BrCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Canyonlands',15,44,38.323331, -109.878973,'https://www.nps.gov/Cany/index.htm','https://www.nps.gov/Cany/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Capitol Reef',15,44,38.356048, -111.260386,'https://www.nps.gov/CaRe/index.htm','https://www.nps.gov/CaRe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Carlsbad Caverns',15,31,32.141150, -104.553627,'https://www.nps.gov/cave/index.htm','https://www.nps.gov/cave/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Channel Islands',15,5,33.991113, -119.729528,'https://www.nps.gov/ChIs/index.htm','https://www.nps.gov/ChIs/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Congaree',15,40,33.792038, -80.777035,'https://www.nps.gov/Cong/index.htm','https://www.nps.gov/Cong/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Crater Lake',15,37,42.869115, -122.157573,'https://www.nps.gov/CrLa/index.htm','https://www.nps.gov/CrLa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Cuyahoga Valley',15,35,41.279624, -81.569920,'https://www.nps.gov/CuVa/index.htm','https://www.nps.gov/CuVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Death Valley',15,5,36.426749, -117.030657,'https://www.nps.gov/DeVa/index.htm','https://www.nps.gov/DeVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Denali',17,2,63.166349, -151.171772,'https://www.nps.gov/Dena/index.htm','https://www.nps.gov/Dena/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Dry Tortugas',15,9,24.628269, -82.873654,'https://www.nps.gov/DrTo/index.htm','https://www.nps.gov/DrTo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Everglades',15,9,25.316088, -80.899433,'https://www.nps.gov/Ever/index.htm','https://www.nps.gov/Ever/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Gates of the Arctic',17,2,67.810865, -153.734304,'https://www.nps.gov/gaar/index.htm','https://www.nps.gov/gaar/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Glacier Bay',15,2,58.660097, -136.949043,'https://www.nps.gov/GlBa/index.htm','https://www.nps.gov/GlBa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Grand Canyon',15,3,36.160343, -112.154012,'https://www.nps.gov/GrCa/index.htm','https://www.nps.gov/GrCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Glacier',15,26,48.712762, -113.824467,'https://www.nps.gov/Glac/index.htm','https://www.nps.gov/Glac/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Grand Teton',15,50,43.798385, -110.685935,'https://www.nps.gov/GrTe/index.htm','https://www.nps.gov/GrTe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Basin',15,28,38.979981, -114.307374,'https://www.nps.gov/GrBa/index.htm','https://www.nps.gov/GrBa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Sand Dunes',17,6,37.789636, -105.599735,'https://www.nps.gov/GrSa/index.htm','https://www.nps.gov/GrSa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Smoky Mountains',15,42,35.603834, -83.525050,'https://www.nps.gov/GrSm/index.htm','https://www.nps.gov/GrSm/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Guadalupe Mountains',15,43,31.945712, -104.880240,'https://www.nps.gov/GuMo/index.htm','https://www.nps.gov/GuMo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Haleakala',15,11,20.723843, -156.190726,'https://www.nps.gov/Hale/index.htm','https://www.nps.gov/Hale/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Hawai‘i Volcanoes',15,11,19.426288, -155.289793,'https://www.nps.gov/HaVo/index.htm','https://www.nps.gov/HaVo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Hot Springs',15,4,34.522386, -93.044079,'https://www.nps.gov/HoSp/index.htm','https://www.nps.gov/HoSp/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Isle Royale',15,22,47.971302, -88.907485,'https://www.nps.gov/IsRo/index.htm','https://www.nps.gov/IsRo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Joshua Tree',15,5,33.858158, -115.902097,'https://www.nps.gov/JoTr/index.htm','https://www.nps.gov/JoTr/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Katmai',17,2,58.553591, -154.701900,'https://www.nps.gov/Katm/index.htm','https://www.nps.gov/Katm/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kenai Fjords',15,2,60.030492, -149.826287,'https://www.nps.gov/KeFj/index.htm','https://www.nps.gov/KeFj/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kings Canyon',15,5,36.882202, -118.553296,'https://www.nps.gov/seki/index.htm','https://www.nps.gov/seki/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kobuk Valley',15,2,67.316817, -159.136097,'https://www.nps.gov/KoVa/index.htm','https://www.nps.gov/KoVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Lake Clark',17,2,60.400069, -154.328035,'https://www.nps.gov/LaCl/index.htm','https://www.nps.gov/LaCl/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Lassen Volcanic',15,5,40.493861, -121.419357,'https://www.nps.gov/LaVo/index.htm','https://www.nps.gov/LaVo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mammoth Cave',15,17,37.184430, -86.100873,'https://www.nps.gov/MaCa/index.htm','https://www.nps.gov/MaCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mesa Verde',15,6,37.235451, -108.461526,'https://www.nps.gov/MeVe/index.htm','https://www.nps.gov/MeVe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mount Rainier',15,47,46.875111, -121.728816,'https://www.nps.gov/MoRa/index.htm','https://www.nps.gov/MoRa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('National Park of American Samoa',15,51,-14.260224, -170.683414,'https://www.nps.gov/npsa/index.htm','https://www.nps.gov/npsa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('North Cascades',15,47,48.817646, -121.267831,'https://www.nps.gov/NoCa/index.htm','https://www.nps.gov/NoCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Olympic',15,47,47.793316, -123.604536,'https://www.nps.gov/Olym/index.htm','https://www.nps.gov/Olym/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Petrified Forest',15,3,34.903063, -109.807599,'https://www.nps.gov/PeFo/index.htm','https://www.nps.gov/PeFo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Pinnacles',15,5,36.487895, -121.182212,'https://www.nps.gov/Pinn/index.htm','https://www.nps.gov/Pinn/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Redwood',15,5,41.208268, -124.005974,'https://www.nps.gov/Redw/index.htm','https://www.nps.gov/Redw/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Rocky Mountain',15,6,40.330962, -105.686899,'https://www.nps.gov/RoMo/index.htm','https://www.nps.gov/RoMo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Saguaro',15,3,32.284396, -111.164582,'https://www.nps.gov/Sagu/index.htm','https://www.nps.gov/Sagu/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Sequoia',15,5,36.471434, -118.565384,'https://www.nps.gov/seki/index.htm','https://www.nps.gov/seki/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Shenandoah',15,46,38.300605, -78.651428,'https://www.nps.gov/Shen/index.htm','https://www.nps.gov/Shen/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Theodore Roosevelt',15,34,46.956833, -103.522350,'https://www.nps.gov/ThRo/index.htm','https://www.nps.gov/ThRo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Virgin Islands',15,59,18.341025, -64.748593,'https://www.nps.gov/ViIs/index.htm','https://www.nps.gov/ViIs/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Voyageurs',15,23,48.486884, -92.820107,'https://www.nps.gov/Voya/index.htm','https://www.nps.gov/Voya/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Wind Cave',15,41,43.601120, -103.423311,'https://www.nps.gov/WiCa/index.htm','https://www.nps.gov/WiCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Wrangell-St.  Elias',15,2,61.639130, -142.991965,'https://www.nps.gov/wrst/index.htm','https://www.nps.gov/wrst/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Yellowstone',15,50,44.410559, -110.583835,'https://www.nps.gov/Yell/index.htm','https://www.nps.gov/Yell/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Yosemite',15,5,37.864566, -119.537912,'https://www.nps.gov/Yose/index.htm','https://www.nps.gov/Yose/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Zion',15,44,37.289471, -113.027066,'https://www.nps.gov/Zion/index.htm','https://www.nps.gov/Zion/planyourvisit/maps.htm',0);


select * from color;
select * from designation;
select * from region;
select * from state;
select * from site;
