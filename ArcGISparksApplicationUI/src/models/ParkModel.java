package models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Site;
import jpa.ParkJPA;

public class ParkModel {
	
	public void connect() {
		if(myJPA == null) {
			myJPA = new ParkJPA();
		}
	}
	
	public List<Site> queryDB() {
		List<Site> siteList = new ArrayList<Site>();
		siteList = myJPA.getDBSites();
		return siteList;
	}
	
//Site property (object property)														Site property (object property)
		ObjectProperty<Site> siteProp = new SimpleObjectProperty<Site>();
		public ObjectProperty<Site> siteProperty(){
			return siteProp;
		}
		public void setSiteProp(Site newSite){
			siteProp.setValue(newSite);
			System.out.println(getSiteProp());
		}
		public Site getSiteProp(){
			return siteProp.getValue();
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
			myList = myJPA.getDBSites();
			return myList;
		}

}
