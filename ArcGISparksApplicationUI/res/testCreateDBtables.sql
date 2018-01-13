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

insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Acadia',15,19,-7594393.5763021745,5519266.198433882,'https://www.nps.gov/Acad/index.htm','https://www.nps.gov/Acad/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Arches',15,44,-1.2196791868656434E7,4682000.536926843,'https://www.nps.gov/Arch/index.htm','https://www.nps.gov/Arch/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Badlands',15,41,-1.1418448491102263E7,5419870.076205518,'https://www.nps.gov/Badl/index.htm','https://www.nps.gov/Badl/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Big Bend',15,43,-1.148770563499734E7,3416431.35478036,'https://www.nps.gov/BiBe/index.htm','https://www.nps.gov/BiBe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Biscayne',15,9,-8932446.167687763,2936660.8676984464,'https://www.nps.gov/Bisc/index.htm','https://www.nps.gov/Bisc/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Black Canyon of the Gunnison',15,6,-1.1992417003772512E7,4661868.582495152,'https://www.nps.gov/BlCa/index.htm','https://www.nps.gov/BlCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Bryce Canyon',15,44,-1.2488084117502362E7,4521400.75339952,'https://www.nps.gov/BrCa/index.htm','https://www.nps.gov/BrCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Canyonlands',15,44,-1.2235351304123575E7,4610554.325994286,'https://www.nps.gov/Cany/index.htm','https://www.nps.gov/Cany/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Capitol Reef',15,44,-1.2375853761165043E7,4606407.826052008,'https://www.nps.gov/CaRe/index.htm','https://www.nps.gov/CaRe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Carlsbad Caverns',15,31,-1.1641226147620127E7,3781358.8970038015,'https://www.nps.gov/cave/index.htm','https://www.nps.gov/cave/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Channel Islands',15,5,-1.3313593992234783E7,4030673.764643043,'https://www.nps.gov/ChIs/index.htm','https://www.nps.gov/ChIs/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Congaree',15,40,-8992148.58397007,4002087.342279039,'https://www.nps.gov/Cong/index.htm','https://www.nps.gov/Cong/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Crater Lake',15,37,-1.3593418191939363E7,5304463.2636309,'https://www.nps.gov/CrLa/index.htm','https://www.nps.gov/CrLa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Cuyahoga Valley',15,35,-9078555.662847314,5053642.865288224,'https://www.nps.gov/CuVa/index.htm','https://www.nps.gov/CuVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Death Valley',15,5,-1.303203309708482E7,4370052.23165338,'https://www.nps.gov/DeVa/index.htm','https://www.nps.gov/DeVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Denali',17,2,-1.6796389335073315E7,9170878.40860231,'https://www.nps.gov/Dena/index.htm','https://www.nps.gov/Dena/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Dry Tortugas',15,9,-9225392.700316038,2830181.768538803,'https://www.nps.gov/DrTo/index.htm','https://www.nps.gov/DrTo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Everglades',15,9,-8998574.620066812,2921323.5243619494,'https://www.nps.gov/Ever/index.htm','https://www.nps.gov/Ever/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Gates of the Arctic',17,2,-1.6989424165473655E7,1.0521298634861784E7,'https://www.nps.gov/gaar/index.htm','https://www.nps.gov/gaar/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Glacier Bay',15,2,-1.5242528621905586E7,8138334.728784427,'https://www.nps.gov/GlBa/index.htm','https://www.nps.gov/GlBa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Grand Canyon',15,3,-1.2483301240864174E7,4324969.293799689,'https://www.nps.gov/GrCa/index.htm','https://www.nps.gov/GrCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Glacier',15,26,-1.2675437658063903E7,6219279.118695671,'https://www.nps.gov/Glac/index.htm','https://www.nps.gov/Glac/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Grand Teton',15,50,-1.2318229102089321E7,5437916.399238861,'https://www.nps.gov/GrTe/index.htm','https://www.nps.gov/GrTe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Basin',15,28,-1.2716015042322654E7,4715278.79025374,'https://www.nps.gov/GrBa/index.htm','https://www.nps.gov/GrBa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Sand Dunes',17,6,-1.1757148683815824E7,4548550.582527555,'https://www.nps.gov/GrSa/index.htm','https://www.nps.gov/GrSa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Great Smoky Mountains',15,42,-9296552.761980193,4245679.599486808,'https://www.nps.gov/GrSm/index.htm','https://www.nps.gov/GrSm/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Guadalupe Mountains',15,43,-1.1677815261504149E7,3753806.883347329,'https://www.nps.gov/GuMo/index.htm','https://www.nps.gov/GuMo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Haleakala',15,11,-1.738756656392727E7,2355760.440557068,'https://www.nps.gov/Hale/index.htm','https://www.nps.gov/Hale/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Hawai‘i Volcanoes',15,11,-1.728650764388252E7,2203688.033134727,'https://www.nps.gov/HaVo/index.htm','https://www.nps.gov/HaVo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Hot Springs',15,4,-1.0357882706915643E7,4098707.787977334,'https://www.nps.gov/HoSp/index.htm','https://www.nps.gov/HoSp/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Isle Royale',15,22,-9897307.999838538,6104673.1576206125,'https://www.nps.gov/IsRo/index.htm','https://www.nps.gov/IsRo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Joshua Tree',15,5,-1.2898677713505637E7,4017522.7059932235,'https://www.nps.gov/JoTr/index.htm','https://www.nps.gov/JoTr/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Katmai',17,2,-1.7128937274828374E7,8142994.7022605715,'https://www.nps.gov/Katm/index.htm','https://www.nps.gov/Katm/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kenai Fjords',15,2,-1.6665291268221857E7,8371160.142033447,'https://www.nps.gov/KeFj/index.htm','https://www.nps.gov/KeFj/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kings Canyon',15,5,-1.320150455010316E7,4424241.453934334,'https://www.nps.gov/seki/index.htm','https://www.nps.gov/seki/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Kobuk Valley',15,2,-1.7702839690015674E7,1.0227302377129223E7,'https://www.nps.gov/KoVa/index.htm','https://www.nps.gov/KoVa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Lake Clark',17,2,-1.71988039962759E7,8503857.620353483,'https://www.nps.gov/LaCl/index.htm','https://www.nps.gov/LaCl/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Lassen Volcanic',15,5,-1.3514886087155465E7,4937868.146413689,'https://www.nps.gov/LaVo/index.htm','https://www.nps.gov/LaVo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mammoth Cave',15,17,-9589407.910913983,4466471.654646736,'https://www.nps.gov/MaCa/index.htm','https://www.nps.gov/MaCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mesa Verde',15,6,-1.2073582818292929E7,4472420.161347243,'https://www.nps.gov/MeVe/index.htm','https://www.nps.gov/MeVe/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Mount Rainier',15,47,-1.3551017673119541E7,5918483.907539986,'https://www.nps.gov/MoRa/index.htm','https://www.nps.gov/MoRa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('National Park of American Samoa',15,51,-1.8999920924691547E7,-1603853.7349146092,'https://www.nps.gov/npsa/index.htm','https://www.nps.gov/npsa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('North Cascades',15,47,-1.350572963039631E7,6248565.728447794,'https://www.nps.gov/NoCa/index.htm','https://www.nps.gov/NoCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Olympic',15,47,-1.3753199199560864E7,6074092.779016227,'https://www.nps.gov/Olym/index.htm','https://www.nps.gov/Olym/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Petrified Forest',15,3,-1.221239797784774E7,4161179.8577883644,'https://www.nps.gov/PeFo/index.htm','https://www.nps.gov/PeFo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Pinnacles',15,5,-1.3491431261732085E7,4371249.537443062,'https://www.nps.gov/Pinn/index.htm','https://www.nps.gov/Pinn/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Redwood',15,5,-1.3806265904695436E7,5046751.328061795,'https://www.nps.gov/Redw/index.htm','https://www.nps.gov/Redw/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Rocky Mountain',15,6,-1.1770917681604583E7,4921003.91832006,'https://www.nps.gov/RoMo/index.htm','https://www.nps.gov/RoMo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Saguaro',15,3,-1.2376517458213648E7,3801528.827057488,'https://www.nps.gov/Sagu/index.htm','https://www.nps.gov/Sagu/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Sequoia',15,5,-1.3200635326316288E7,4372353.883866485,'https://www.nps.gov/seki/index.htm','https://www.nps.gov/seki/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Shenandoah',15,46,-8730818.268188937,4649193.084855233,'https://www.nps.gov/Shen/index.htm','https://www.nps.gov/Shen/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Theodore Roosevelt',15,34,-1.1516798945488917E7,5934569.674541949,'https://www.nps.gov/ThRo/index.htm','https://www.nps.gov/ThRo/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Virgin Islands',15,59,-7206684.456768554,2077953.2407390422,'https://www.nps.gov/ViIs/index.htm','https://www.nps.gov/ViIs/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Voyageurs',15,23,-1.0338858301934069E7,6190150.271251976,'https://www.nps.gov/Voya/index.htm','https://www.nps.gov/Voya/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Wind Cave',15,41,-1.151417695370968E7,5403638.305947851,'https://www.nps.gov/WiCa/index.htm','https://www.nps.gov/WiCa/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Wrangell-St.  Elias',15,2,-1.598921931798662E7,8647439.6133071,'https://www.nps.gov/wrst/index.htm','https://www.nps.gov/wrst/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Yellowstone',15,50,-1.2314026502467243E7,5560161.976330059,'https://www.nps.gov/Yell/index.htm','https://www.nps.gov/Yell/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Yosemite',15,5,-1.3305366016031263E7,4558140.519352261,'https://www.nps.gov/Yose/index.htm','https://www.nps.gov/Yose/planyourvisit/maps.htm',0);
insert into site (site_name, designation_id, state_id, lat, lon, website, map, visited) values ('Zion',15,44,-1.2579335201129528E7,4480941.615932939,'https://www.nps.gov/Zion/index.htm','https://www.nps.gov/Zion/planyourvisit/maps.htm',0);


select * from color;
select * from designation;
select * from region;
select * from state;
select * from site;
