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
import javafx.scene.control.Toggle;
import entities.Designation;
import entities.Region;
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
	public void updateQueryState(Object o) {
		
			Map queryDataMap = new HashMap<>();
			queryDataMap.put("visitId", getValue(o));
			queryDataMap.put("siteList", getlvSite2Prop());
			queryDataMap.put("desList", getlvDesignation2Prop());
			queryDataMap.put("stateList", getlvState2Prop());
			queryDataMap.put("regionList", getlvRegion2Prop());
			myJPA.setMyQuery(QueryState.buildQuery(queryDataMap));
			myJPA.setMyQuery1(QueryState.buildQuery1(queryDataMap));
			myJPA.setMyQuery2(QueryState.buildQuery2(queryDataMap));
	}
	
	private Number getValue(Object o) {
		String str = o.toString();
		if(str.equals("RadioButton[id=radio0BTN, styleClass=radio-button]'Visited'")) {
			return 1;
		}
		else if (str.equals("RadioButton[id=radio1BTN, styleClass=radio-button]'Not Visited'")) {
			return 0;
		}
		else {
			return 2;
		}
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
		
		public static Site mySite;
		
		public static Site getMySite() {
			return mySite;
		}

		public static void setMySite(Site mySite) {
			ParkModel.mySite = mySite;
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
		public void addAllSite() {
		}
		
		public void removeAllSite() {
		}
		
		public void addSite() {
			if(getSitePropQ()!=null) {
				Site e = getSitePropQ();
				lvSite1Prop.get().remove(e);
				lvSite2Prop.get().add(e);
			}
		}
		
		public void removeSite() {
			if(getSiteProp2()!=null) {
				Site e = getSiteProp2();
				lvSite2Prop.get().remove(e);
				lvSite1Prop.get().add(e);
			}
		}
		
		public List<Site> mySiteRecordList = new ArrayList<Site>();
		public List<Site> getSiteRecordList(){
				if(myJPA != null)
				mySiteRecordList = myJPA.getDBSiteRecords();
			return mySiteRecordList;
		}
		
		ObjectProperty<ObservableList<Site>> lvSite1Prop = new SimpleObjectProperty<ObservableList<Site>>();
		public ObjectProperty<ObservableList<Site>> lvSite1Property() {
			lvSite1Prop.setValue(FXCollections.observableList(getSiteRecordList()));
			return lvSite1Prop;
		}
		
		public void setlvSite1Prop(ObservableList<Site> newProp){
			lvSite1Prop.setValue(newProp);	
		}
		
		public ObservableList<Site> getlvSite1Prop(){
			return lvSite1Prop.getValue();
		}
		
		
		ObjectProperty<ObservableList<Site>> lvSite2Prop = new SimpleObjectProperty<ObservableList<Site>>();
		public ObjectProperty<ObservableList<Site>> lvSite2Property() {
			lvSite2Prop.setValue(FXCollections.observableList(new ArrayList<Site>()));
			return lvSite2Prop;
		}
		
		public void setlvSite2Prop(ObservableList<Site> newProp){
			lvSite2Prop.setValue(newProp);	
		}
		
		public ObservableList<Site> getlvSite2Prop(){
			return lvSite2Prop.getValue();
		}
		
		
		ObjectProperty<Site> sitePropQ = new SimpleObjectProperty<Site>();
		public ObjectProperty<Site> SiteProperty(){
			return sitePropQ;
		}
		public void setSitePropQ(Site newSite){
			sitePropQ.setValue(newSite);
		}
		public Site getSitePropQ(){
			return sitePropQ.getValue();
		}
		
		ObjectProperty<Site> siteProp2 = new SimpleObjectProperty<Site>();
		public ObjectProperty<Site> SiteProperty2(){
			return siteProp2;
		}
		public void setSiteProp2(Site newSite){
			siteProp2.setValue(newSite);
		}
		public Site getSiteProp2(){
			return siteProp2.getValue();
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
		
/*
 *  region ARLV
 */
		public void addAllRegion() {
		}
		
		public void removeAllRegion() {
		}
		
		public void addRegion() {
			if(getRegionProp()!=null) {
				Region e = getRegionProp();
				lvRegion1Prop.get().remove(e);
				lvRegion2Prop.get().add(e);
			}
		}
		
		public void removeRegion() {
			if(getRegionProp2()!=null) {
				Region e = getRegionProp2();
				lvRegion2Prop.get().remove(e);
				lvRegion1Prop.get().add(e);
			}
		}
		
		public List<Region> myRegionRecordList = new ArrayList<Region>();
		public List<Region> getRegionRecordList(){
				if(myJPA != null)
				myRegionRecordList = myJPA.getDBRegionRecords();
			return myRegionRecordList;
		}
		
		ObjectProperty<ObservableList<Region>> lvRegion1Prop = new SimpleObjectProperty<ObservableList<Region>>();
		public ObjectProperty<ObservableList<Region>> lvRegion1Property() {
			lvRegion1Prop.setValue(FXCollections.observableList(getRegionRecordList()));
			return lvRegion1Prop;
		}
		
		public void setlvRegion1Prop(ObservableList<Region> newProp){
			lvRegion1Prop.setValue(newProp);	
		}
		
		public ObservableList<Region> getlvRegion1Prop(){
			return lvRegion1Prop.getValue();
		}
		
		
		ObjectProperty<ObservableList<Region>> lvRegion2Prop = new SimpleObjectProperty<ObservableList<Region>>();
		public ObjectProperty<ObservableList<Region>> lvRegion2Property() {
			lvRegion2Prop.setValue(FXCollections.observableList(new ArrayList<Region>()));
			return lvRegion2Prop;
		}
		
		public void setlvRegion2Prop(ObservableList<Region> newProp){
			lvRegion2Prop.setValue(newProp);	
		}
		
		public ObservableList<Region> getlvRegion2Prop(){
			return lvRegion2Prop.getValue();
		}
		
		
		ObjectProperty<Region> regionProp = new SimpleObjectProperty<Region>();
		public ObjectProperty<Region> RegionProperty(){
			return regionProp;
		}
		public void setRegionProp(Region newRegion){
			regionProp.setValue(newRegion);
		}
		public Region getRegionProp(){
			return regionProp.getValue();
		}
		
		ObjectProperty<Region> regionProp2 = new SimpleObjectProperty<Region>();
		public ObjectProperty<Region> RegionProperty2(){
			return regionProp2;
		}
		public void setRegionProp2(Region newRegion){
			regionProp2.setValue(newRegion);
		}
		public Region getRegionProp2(){
			return regionProp2.getValue();
		}

		Object o = new Object();
		public Object getRadioGroupSelection() {
			return o;
		}
		public void setRadioGroupSelection(Toggle newVal) {
			o = newVal;
		}

		public Designation getSiteDesignation(Site site) {
			return myJPA.getDBSiteDesignation(site);
		}
						
	

}
