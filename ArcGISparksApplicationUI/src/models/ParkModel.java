package models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Site;
import entities.TestEntity;
import jpa.ParkJPA;

public class ParkModel {
	
	public void connect() {
		if(myJPA == null) {
			myJPA = new ParkJPA();
		}
	}
	
//Site property (object property)														Site property (object property)
		ObjectProperty<Site> testentityProp = new SimpleObjectProperty<Site>();
		public ObjectProperty<Site> testentityProperty(){
			return testentityProp;
		}
		public void setTestentityProp(Site newTestentity){
			testentityProp.setValue(newTestentity);
			System.out.println(getTestentityProp());
		}
		public Site getTestentityProp(){
			return testentityProp.getValue();
		}
	
//list view property (items property)														list view property (items property)
		ObjectProperty<ObservableList<Site>> listviewProp = new SimpleObjectProperty<ObservableList<Site>>();
		public ObjectProperty<ObservableList<Site>> listviewProperty() {
			listviewProp.setValue(FXCollections.observableList(getList()));
			return listviewProp;
		}
		
		public void setListviewProp(ObservableList<Site> newListviewProp){
			listviewProp.setValue(newListviewProp);	
		}
		
		public ObservableList<Site> getListviewProp(){
			return listviewProp.getValue();
		}
		
//list view (getList())																list view (getList())
		public static ParkJPA myJPA;
		public List<Site> myList = new ArrayList<Site>();
		public List<Site> getList(){
			if(myJPA != null)
			myList = myJPA.getDBTestEntities();
			return myList;
		}

}
