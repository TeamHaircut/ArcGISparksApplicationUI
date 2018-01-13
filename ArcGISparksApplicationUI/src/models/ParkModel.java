package models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.TestEntity;
import jpa.ParkJPA;

public class ParkModel {
	
	public void connect() {
		if(myJPA == null) {
			myJPA = new ParkJPA();
		}
	}
	
//list view property (items property)														list view property (items property)
		ObjectProperty<ObservableList<TestEntity>> listviewProp = new SimpleObjectProperty<ObservableList<TestEntity>>();
		public ObjectProperty<ObservableList<TestEntity>> listviewProperty() {
			listviewProp.setValue(FXCollections.observableList(getList()));
			return listviewProp;
		}
		
		public void setListviewProp(ObservableList<TestEntity> newListviewProp){
			listviewProp.setValue(newListviewProp);	
		}
		
		public ObservableList<TestEntity> getListviewProp(){
			return listviewProp.getValue();
		}
		
//list view (getAccountList())																list view (getAccountList())
		public static ParkJPA myJPA;
		public List<TestEntity> myList = new ArrayList<TestEntity>();
		public List<TestEntity> getList(){
			if(myJPA != null)
			myList = myJPA.getDBTestEntities();
			return myList;
		}

}
