package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import supportclasses.QueryState;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Designation;
import entities.Site;
import entities.State;
import jpa.ParkJPA;

public class ParkModel {
	
	public void connect() {
		if(myJPA == null) {
			myJPA = new ParkJPA();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateQueryState(Number id) {
			//queryState.setVisited(id);
			//queryState.setDesignationList(getlvDesignation2Prop());
			
			Map queryDataMap = new HashMap<>();
			queryDataMap.put("visitId", id);
			System.out.println(getlvDesignation2Prop());
			queryDataMap.put("desList", getlvDesignation2Prop());
			queryDataMap.put("stateList", getlvState2Prop());
			myJPA.setMyQuery(QueryState.buildQuery(queryDataMap));
	}
	
	public List<Site> queryDB() {
		
		List<Site> siteList = new ArrayList<Site>();
		siteList = myJPA.getDBSites();
		myList = siteList;
		listviewProperty();
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
			return myList;
		}
		
/*
 * ARLV
 */
		public void addAll() {
		}
		
		public void removeAll() {
		}
		
		public void add() {
			if(getDesignationProp()!=null) {
				Designation e = getDesignationProp();
				lvDesignation1Prop.get().remove(e);
				lvDesignation2Prop.get().add(e);
			}
		}
		
		public void remove() {
			if(getDesignationProp2()!=null) {
				Designation e = getDesignationProp2();
				lvDesignation2Prop.get().remove(e);
				lvDesignation1Prop.get().add(e);
			}
		}
		
		public List<Designation> myDesignationRecordList = new ArrayList<Designation>();
		public List<Designation> getDesignationRecordList(){
				if(myJPA != null)
				myDesignationRecordList = myJPA.getDBDesignationRecords();
			return myDesignationRecordList;
		}
		
		ObjectProperty<ObservableList<Designation>> lvDesignation1Prop = new SimpleObjectProperty<ObservableList<Designation>>();
		public ObjectProperty<ObservableList<Designation>> lvDesignation1Property() {
			lvDesignation1Prop.setValue(FXCollections.observableList(getDesignationRecordList()));
			return lvDesignation1Prop;
		}
		
		public void setlvDesignation1Prop(ObservableList<Designation> newProp){
			lvDesignation1Prop.setValue(newProp);	
		}
		
		public ObservableList<Designation> getlvDesignation1Prop(){
			return lvDesignation1Prop.getValue();
		}
		
		
		ObjectProperty<ObservableList<Designation>> lvDesignation2Prop = new SimpleObjectProperty<ObservableList<Designation>>();
		public ObjectProperty<ObservableList<Designation>> lvDesignation2Property() {
			lvDesignation2Prop.setValue(FXCollections.observableList(new ArrayList<Designation>()));
			return lvDesignation2Prop;
		}
		
		public void setlvDesignation2Prop(ObservableList<Designation> newProp){
			lvDesignation2Prop.setValue(newProp);	
		}
		
		public ObservableList<Designation> getlvDesignation2Prop(){
			return lvDesignation2Prop.getValue();
		}
		
		
		ObjectProperty<Designation> designationProp = new SimpleObjectProperty<Designation>();
		public ObjectProperty<Designation> DesignationProperty(){
			return designationProp;
		}
		public void setDesignationProp(Designation newDes){
			designationProp.setValue(newDes);
		}
		public Designation getDesignationProp(){
			return designationProp.getValue();
		}
		
		ObjectProperty<Designation> designationProp2 = new SimpleObjectProperty<Designation>();
		public ObjectProperty<Designation> DesignationProperty2(){
			return designationProp2;
		}
		public void setDesignationProp2(Designation newDes){
			designationProp2.setValue(newDes);
		}
		public Designation getDesignationProp2(){
			return designationProp2.getValue();
		}
		
/*
 *  state ARLV
 */
		public void addAllState() {
		}
		
		public void removeAllState() {
		}
		
		public void addState() {
			if(getStateProp()!=null) {
				State e = getStateProp();
				lvState1Prop.get().remove(e);
				lvState2Prop.get().add(e);
			}
		}
		
		public void removeState() {
			if(getStateProp2()!=null) {
				State e = getStateProp2();
				lvState2Prop.get().remove(e);
				lvState1Prop.get().add(e);
			}
		}
		
		public List<State> myStateRecordList = new ArrayList<State>();
		public List<State> getStateRecordList(){
				if(myJPA != null)
				myStateRecordList = myJPA.getDBStateRecords();
			return myStateRecordList;
		}
		
		ObjectProperty<ObservableList<State>> lvState1Prop = new SimpleObjectProperty<ObservableList<State>>();
		public ObjectProperty<ObservableList<State>> lvState1Property() {
			lvState1Prop.setValue(FXCollections.observableList(getStateRecordList()));
			return lvState1Prop;
		}
		
		public void setlvState1Prop(ObservableList<State> newProp){
			lvState1Prop.setValue(newProp);	
		}
		
		public ObservableList<State> getlvState1Prop(){
			return lvState1Prop.getValue();
		}
		
		
		ObjectProperty<ObservableList<State>> lvState2Prop = new SimpleObjectProperty<ObservableList<State>>();
		public ObjectProperty<ObservableList<State>> lvState2Property() {
			lvState2Prop.setValue(FXCollections.observableList(new ArrayList<State>()));
			return lvState2Prop;
		}
		
		public void setlvState2Prop(ObservableList<State> newProp){
			lvState2Prop.setValue(newProp);	
		}
		
		public ObservableList<State> getlvState2Prop(){
			return lvState2Prop.getValue();
		}
		
		
		ObjectProperty<State> stateProp = new SimpleObjectProperty<State>();
		public ObjectProperty<State> StateProperty(){
			return stateProp;
		}
		public void setStateProp(State newState){
			stateProp.setValue(newState);
		}
		public State getStateProp(){
			return stateProp.getValue();
		}
		
		ObjectProperty<State> stateProp2 = new SimpleObjectProperty<State>();
		public ObjectProperty<State> StateProperty2(){
			return stateProp2;
		}
		public void setStateProp2(State newState){
			stateProp2.setValue(newState);
		}
		public State getStateProp2(){
			return stateProp2.getValue();
		}
						
	

}
