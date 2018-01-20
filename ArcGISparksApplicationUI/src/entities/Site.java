/*
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
*/
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	   private int site_id;
	   private String site_name;
	   private int designation_id;
	   private int state_id;
	   private double lat;
	   private double lon;
	   private String website;
	   private String map;
	   private int visited;
	   
		public int getSite_id() {
			return site_id;
		}
		public void setSite_id(int site_id) {
			this.site_id = site_id;
		}
		public int getDesignation_id() {
			return designation_id;
		}
		public void setDesignation_id(int designation_id) {
			this.designation_id = designation_id;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getMap() {
			return map;
		}
		public void setMap(String map) {
			this.map = map;
		}
		public int getVisited() {
			return visited;
		}
		public void setVisited(int visited) {
			this.visited = visited;
		}
		public String getSite_name() {
			return site_name;
		}
		public void setSite_name(String site_name) {
			this.site_name = site_name;
		}
		public int getState_id() {
			return state_id;
		}
		public void setState_id(int state_id) {
			this.state_id = state_id;
		}
		
		@Override
		public String toString() {
			return getSite_name();
		}
	   
}//end class