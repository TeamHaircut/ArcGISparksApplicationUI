package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import supportclasses.WebCrawler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CampAppModel {

	ObjectProperty<ObservableList<String>> listviewProp = new SimpleObjectProperty<ObservableList<String>>();
	public ObjectProperty<ObservableList<String>> listviewProperty() throws IOException {
		listviewProp.setValue(FXCollections.observableList(getList()));
		return listviewProp;
	}
	
	public void setListviewProp(ObservableList<String> newListviewProp){
		listviewProp.setValue(newListviewProp);	
	}
	
	public ObservableList<String> getListviewProp(){
		return listviewProp.getValue();
	}
	
	public List<String> myList = new ArrayList<String>();
	public List<String> getList() throws IOException{
		myList = WebCrawler.crawl();
		
		return myList;
	}
			
}
