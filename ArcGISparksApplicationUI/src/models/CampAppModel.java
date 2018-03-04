package models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CampAppModel {

	//list view property (items property)														list view property (items property)
			ObjectProperty<ObservableList<String>> listviewProp = new SimpleObjectProperty<ObservableList<String>>();
			public ObjectProperty<ObservableList<String>> listviewProperty() {
				listviewProp.setValue(FXCollections.observableList(getList()));
				return listviewProp;
			}
			
			public void setListviewProp(ObservableList<String> newListviewProp){
				listviewProp.setValue(newListviewProp);	
			}
			
			public ObservableList<String> getListviewProp(){
				return listviewProp.getValue();
			}
			
	//list view (getList())																list view (getList())
			public List<String> myList = new ArrayList<String>();
			public List<String> getList(){
				myList.add("item 1");
				myList.add("item 2");
				myList.add("item 3");
				myList.add("item 4");
				return myList;
			}
	
}
