/*
CREATE TABLE DESIGNATION (
	DESIGNATION_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT DESIGNATION_PK PRIMARY KEY,
	DESIGNATION_NAME VARCHAR(50) NOT NULL UNIQUE,
	DESIGNATION_ABBREVIATION VARCHAR(10) NOT NULL UNIQUE);
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Designation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int designation_id;
	private String designation_name;
	private String designation_abbreviation;

	public int getDesignation_id() {
		return designation_id;
	}

	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}

	public String getDesignation_name() {
		return designation_name;
	}

	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}

	public String getDesignation_abbreviation() {
		return designation_abbreviation;
	}

	public void setDesignation_abbreviation(String designation_abbreviation) {
		this.designation_abbreviation = designation_abbreviation;
	}
	
	@Override
	public String toString() {
		return designation_name;
	}

}
