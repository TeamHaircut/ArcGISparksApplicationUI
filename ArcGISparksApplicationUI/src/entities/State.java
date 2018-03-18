/*
CREATE TABLE STATE (
	STATE_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT STATE_PK PRIMARY KEY,
	STATE_NAME VARCHAR(50) NOT NULL UNIQUE,
	STATE_ABBREVIATION VARCHAR(2) NOT NULL UNIQUE,
	REGION_ID INT CONSTRAINT REGION_FK REFERENCES REGION);
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class State {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int state_id;
	private String state_name;
	private String state_abbreviation;
	private int region_id;
	
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getState_abbreviation() {
		return state_abbreviation;
	}
	public void setState_abbreviation(String state_abbreviation) {
		this.state_abbreviation = state_abbreviation;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	
	@Override
	public String toString() {
		return getState_name();
	}
	

}
