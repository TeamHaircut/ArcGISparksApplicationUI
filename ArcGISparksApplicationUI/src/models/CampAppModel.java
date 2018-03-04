package models;

import java.util.ArrayList;
import java.util.List;

import supportclasses.WebCrawler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpa.ParkJPA;

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
			
			public static WebCrawler myWebCrawler;
			
	//list view (getList())																list view (getList())
			public List<String> myList = new ArrayList<String>();
			public List<String> getList(){
				
				myList = myWebCrawler.crawl();
				
				return myList;
			}
	
}
