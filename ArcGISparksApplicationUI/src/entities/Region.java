/*
CREATE TABLE REGION (
	REGION_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT REGION_PK PRIMARY KEY,
	REGION_NAME VARCHAR(30) NOT NULL UNIQUE,
	REGION_COLOR_ID INT NOT NULL UNIQUE CONSTRAINT COLOR_FK REFERENCES REGION);

 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Region {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int region_id;
	private String region_name;
	private int region_color_id;

	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public int getRegion_color_id() {
		return region_color_id;
	}

	public void setRegion_color_id(int region_color_id) {
		this.region_color_id = region_color_id;
	}
	
	@Override
	public String toString() {
		return getRegion_name();
	}

}
