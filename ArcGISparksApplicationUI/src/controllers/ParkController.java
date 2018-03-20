package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import supportclasses.CampAppPane;
import supportclasses.CustomMap;
import supportclasses.ExplorerPane;
import supportclasses.NPMap;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;

import models.ParkModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import entities.Designation;
import entities.Region;
import entities.Site;
import entities.State;

public class ParkController {
	private static final boolean INIT_BANNERS = true;//for testing only
	
	private CustomMap mapControl = CustomMap.getInstance();
	
	private static GraphicsOverlay graphicsOverlay;
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private TitledPane titledpane1;
	@FXML private TitledPane titledpane2;
	
	@FXML private BorderPane borderPane;
	
	@FXML private Tab resultsTab;
	
	@FXML private ListView<Site> listview1;
	@FXML private ListView<Site> listview2;
	
	@FXML private Hyperlink hyperlink1;
	
	@FXML private RadioButton radio0BTN;
	@FXML private RadioButton radio1BTN;
	@FXML private RadioButton radio2BTN;
	
	@FXML private ListView<Designation> lvDes1;
	@FXML private ListView<Designation> lvDes2;
	@FXML private Button addAllDesBTN;
	@FXML private Button removeAllDesBTN;
	@FXML private Button addDesBTN;
	@FXML private Button removeDesBTN;
	
	@FXML private ListView<State> lvState1;
	@FXML private ListView<State> lvState2;
	@FXML private Button addAllStateBTN;
	@FXML private Button removeAllStateBTN;
	@FXML private Button addStateBTN;
	@FXML private Button removeStateBTN;
	
	@FXML private ListView<Region> lvRegion1;
	@FXML private ListView<Region> lvRegion2;
	@FXML private Button addAllRegionBTN;
	@FXML private Button removeAllRegionBTN;
	@FXML private Button addRegionBTN;
	@FXML private Button removeRegionBTN;
	
	@FXML private ImageView webview1;
	
	@FXML private Label designationLabel;
	@FXML private Button mapBTN;
	@FXML private Button photos;
	@FXML private Button stamps;
	@FXML private Button patches;
	@FXML private Button campBTN;
	
	@FXML private Button pictureCloseBTN;
	@FXML private ToggleGroup toggleGroup;
	
	@FXML
	private void pictureCloseBTNAction() {
		ExplorerPane.getExplorerStackPane().setVisible(false);
		ExplorerPane.getExplorerFlowPane().getChildren().clear();
	}
	
	@FXML
	private void mapBTNAction() throws FileNotFoundException {
		
	}
	
	@FXML
	private void albumActionPerformed(ActionEvent event) throws FileNotFoundException {
		ExplorerPane.viewGallery(((Button)event.getSource()).getId());
	}
	
	@FXML
	private void campBTNAction() throws IOException {
		ExplorerPane.getExplorerFlowPane().getChildren().clear();
		CampAppPane cap = new CampAppPane();
		cap.setFpane(ExplorerPane.getExplorerFlowPane());
		cap.setSite(parkModel.getSiteProp());
		cap.getFpane();
		ExplorerPane.getExplorerStackPane().setVisible(true);
	}
	
	@FXML void arlvActionPerformed(ActionEvent event) {
		switch(((Button)event.getSource()).getId()) {
		case "addDesBTN": case "addStateBTN": case "addRegionBTN":
			parkModel.add(((Button)event.getSource()).getId()); break;
		case "removeDesBTN": case "removeStateBTN": case "removeRegionBTN":
			parkModel.remove(((Button)event.getSource()).getId()); break;
		case "addAllDesBTN": case "addAllStateBTN": case "addAllRegionBTN":
			parkModel.addAll(((Button)event.getSource()).getId()); break;
		case "removeAllDesBTN": case "removeAllStateBTN": case "removeAllRegionBTN":
			parkModel.removeAll(((Button)event.getSource()).getId()); break;
		}
		submitAction();
	}

	private void submitAction() {
		parkModel.updateQueryState(parkModel.getRadioGroupSelection());
		//** Query Action***************************************************
			graphicsOverlay = new GraphicsOverlay();
			mapControl.getMapView().getGraphicsOverlays().clear();
			mapControl.getMapView().getGraphicsOverlays().add(graphicsOverlay);
			showQueryResults();
			mapControl.getMapView().setMap(mapControl.getMap());
		//******************************************************************
	}
	
	@FXML void initialize() throws IOException{
		
		if(listview1 != null)
		{
			parkModel.connect();
			if(NPMap.isInitialized == false && INIT_BANNERS) {
		    	//NPMap.initializeBannerMap(parkModel.getSiteRecordList());
		    }
			
		    radio2BTN.setSelected(true);
		    
		    toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
		    	parkModel.setRadioGroupSelection(newValue);
				submitAction();
		    });
		    
		    titledpane1.expandedProperty().addListener((observable, oldValue, newValue) -> {
		    	if(newValue == true) {
					titledpane2.setExpanded(false);
				}
		    });
		    
		    titledpane2.expandedProperty().addListener((observable, oldValue, newValue) -> {
		    	if(newValue == true) {
					titledpane1.setExpanded(false);
				}
		    });
		    
		    ChangeListener<Site> listviewChangeListener = new ChangeListener<Site>() {
		    	
				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
					parkModel.setSiteProp(arg2);
					ParkModel.setMySite(arg2);
					hyperlink1.setText(arg2.getSite_name());
					designationLabel.setText(parkModel.getSiteDesignation(arg2).getDesignation_name());
					webview1.setImage(NPMap.bannerMap.get(arg2.getSite_name()));
					Point centerPoint = new Point(arg2.getLat(), arg2.getLon(), SpatialReferences.getWgs84());
			        Viewpoint viewpoint = new Viewpoint(centerPoint, 150000);
			        mapControl.getMapView().setViewpointAsync(viewpoint, 7);	
				}
		    };
		    
		    listview1.getSelectionModel().selectedItemProperty().addListener(listviewChangeListener);
			listview1.itemsProperty().bindBidirectional(parkModel.listviewProperty());
						
			listview2.getSelectionModel().selectedItemProperty().addListener(listviewChangeListener);
			listview2.itemsProperty().bindBidirectional(parkModel.listviewProperty());
	        	
			lvDes1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				parkModel.setDesignationProp(newValue);
			});
			
	        lvDes1.itemsProperty().bindBidirectional(parkModel.lvDesignation1Property());
	        
	        lvDes2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				parkModel.setDesignationProp2(newValue);
			});
	        
	        lvDes2.itemsProperty().bindBidirectional(parkModel.lvDesignation2Property());
	        
	        lvState1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				parkModel.setStateProp(newValue);
			});
	        
	        lvState1.itemsProperty().bindBidirectional(parkModel.lvState1Property());
	        
	        lvState2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				parkModel.setStateProp2(newValue);
			});
	        
	        lvState2.itemsProperty().bindBidirectional(parkModel.lvState2Property());
	        
	        lvRegion1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Region>(){
				@Override
				public void changed(ObservableValue<? extends Region> arg0,
						Region arg1, Region arg2) {
						parkModel.setRegionProp(arg2);
				}	
	        });
	        
	        lvRegion1.itemsProperty().bindBidirectional(parkModel.lvRegion1Property());
	        
	        lvRegion2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Region>(){

				@Override
				public void changed(ObservableValue<? extends Region> arg0,
						Region arg1, Region arg2) {
					parkModel.setRegionProp2(arg2);
				}	
	        });
	        
	        lvRegion2.itemsProperty().bindBidirectional(parkModel.lvRegion2Property());
    
		}
	}
	
	@FXML
	private void hyperlinkAction() {
		try{  
			String command = 
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\CHROME.EXE "
			   + parkModel.getSiteProp().getWebsite(); 
			@SuppressWarnings("unused")
			Process link = Runtime.getRuntime().exec(command);
		}catch(Exception ex){  
			System.out.println("cannot execute command. " +ex);   
		} 
	}
	
	private void showQueryResults() {
		
		SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
		
		List<Site> resultSet = parkModel.queryDB();
		titledpane2.setText("Explore Result Set ("+resultSet.size()+" Parks Found)");
		resultsTab.setText("Result Set ("+resultSet.size()+" Parks Found)");
		
		Image newImage = new Image("arrowhead.png");
		PictureMarkerSymbol parkSymbol = new PictureMarkerSymbol(newImage);
		graphicsOverlay.getGraphics().clear();
		for(Site site :resultSet) {
			placePictureMarkerSymbol(parkSymbol, new Point(site.getLat(), site.getLon(), SPATIAL_REFERENCE));	
		}
	    
	}
	
	private void placePictureMarkerSymbol(PictureMarkerSymbol markerSymbol, Point graphicPoint) {
		
	    markerSymbol.setHeight(30);
	    markerSymbol.setWidth(30);
	    markerSymbol.loadAsync();
	    markerSymbol.addDoneLoadingListener(() -> {
	      if (markerSymbol.getLoadStatus() == LoadStatus.LOADED) {
	    	  Graphic symbolGraphic = new Graphic(graphicPoint, markerSymbol);
	    	  graphicsOverlay.getGraphics().add(symbolGraphic);
	      } else {
	    	  Alert alert = new Alert(Alert.AlertType.ERROR, "Picture Marker Symbol Failed to Load!");
	    	  alert.show();
	      }
      });
	}
	
}
