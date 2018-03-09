package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import supportclasses.CampAppPane;

import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;

import models.ParkModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import entities.Designation;
import entities.Region;
import entities.Site;
import entities.State;

public class ParkController {
	
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private BorderPane borderPane;
	@FXML private TitledPane titledpane2;
	@FXML private Button submitBTN;
	
	@FXML private ListView<Site> listview1;	
	@FXML private Hyperlink hyperlink;
	@FXML private Hyperlink hyperlink1;
	
	@FXML private RadioButton radio0BTN;
	@FXML private RadioButton radio1BTN;
	@FXML private RadioButton radio2BTN;
	
	@FXML private ListView<Site> lvSite1;
	@FXML private ListView<Site> lvSite2;
	
	@FXML private Button addAllSiteBTN;
	@FXML private Button removeAllSiteBTN;
	@FXML private Button addSiteBTN;
	@FXML private Button removeSiteBTN;
	
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
	
	@FXML private WebView webview1;
	@FXML private Label designationLabel;
	@FXML private Button mapBTN;
	@FXML private Button photoBTN;
	@FXML private Button stampBTN;
	@FXML private Button patchBTN;
	@FXML private Button campBTN;
	
	@FXML private Button pictureCloseBTN;
	
	@FXML
	private void pictureCloseBTNAction() {
		StackPane stackpane = (StackPane) ((Pane) myViewList.get(1)).getChildren().get(1);
		stackpane.setVisible(false);
	}
	
	@FXML
	private void mapBTNAction() throws FileNotFoundException {
		
	}
	
	@FXML
	private void photoBTNAction() throws FileNotFoundException {
		
		List<ImageView> list = new ArrayList<ImageView>();
		ObservableList<ImageView> observableList = FXCollections.observableList(list);
		
		StackPane stackpane = (StackPane) ((Pane) myViewList.get(1)).getChildren().get(1);
		FlowPane pane = (FlowPane) ((StackPane) ((Pane) myViewList.get(1)).getChildren().get(1)).getChildren().get(0);
		BorderPane bpane = (BorderPane) pane.getChildren().get(0);
		ScrollPane spane = (ScrollPane) bpane.getChildren().get(1);
		AnchorPane apane = (AnchorPane) spane.getContent();
		FlowPane fpane = (FlowPane) apane.getChildren().get(0);
		fpane.getChildren().clear();
		String dir = parkModel.getSiteProp().getWebsite().substring(20, 24);
		
		for(int i = 1; i < new File("E:\\projectImages\\"+dir+"\\photos\\").listFiles().length+1; i++) {
			FileInputStream input = new FileInputStream("E:\\projectImages\\"+dir+"\\photos\\1 ("+i+").jpg");
			Image image = new Image(input, 300, 0, true, false);
			ImageView imageView = new ImageView(image);
			imageView.setPickOnBounds(true);
			observableList.add(imageView);
		}
		fpane.getChildren().addAll(observableList);
		stackpane.setVisible(true);	
	}
	
	@FXML
	private void stampBTNAction() throws FileNotFoundException {
		
		List<ImageView> list = new ArrayList<ImageView>();
		ObservableList<ImageView> observableList = FXCollections.observableList(list);
		
		StackPane stackpane = (StackPane) ((Pane) myViewList.get(1)).getChildren().get(1);
		FlowPane pane = (FlowPane) ((StackPane) ((Pane) myViewList.get(1)).getChildren().get(1)).getChildren().get(0);
		BorderPane bpane = (BorderPane) pane.getChildren().get(0);
		ScrollPane spane = (ScrollPane) bpane.getChildren().get(1);
		AnchorPane apane = (AnchorPane) spane.getContent();
		FlowPane fpane = (FlowPane) apane.getChildren().get(0);
		fpane.getChildren().clear();
		String dir = parkModel.getSiteProp().getWebsite().substring(20, 24);
		
		for(int i = 1; i < new File("projectImages\\"+dir+"\\stamps\\").listFiles().length+1; i++) {
			FileInputStream input = new FileInputStream("projectImages\\"+dir+"\\stamps\\"+i+".jpg");
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(image.getHeight()/4);
			imageView.setFitWidth(image.getWidth()/4);
			imageView.setPickOnBounds(true);
			observableList.add(imageView);
		}
		fpane.getChildren().addAll(observableList);
		stackpane.setVisible(true);	
	}
	
	@FXML
	private void patchBTNAction() throws FileNotFoundException {
		
		List<ImageView> list = new ArrayList<ImageView>();
		ObservableList<ImageView> observableList = FXCollections.observableList(list);
		
		StackPane stackpane = (StackPane) ((Pane) myViewList.get(1)).getChildren().get(1);
		FlowPane pane = (FlowPane) ((StackPane) ((Pane) myViewList.get(1)).getChildren().get(1)).getChildren().get(0);
		BorderPane bpane = (BorderPane) pane.getChildren().get(0);
		ScrollPane spane = (ScrollPane) bpane.getChildren().get(1);
		AnchorPane apane = (AnchorPane) spane.getContent();
		FlowPane fpane = (FlowPane) apane.getChildren().get(0);
		fpane.getChildren().clear();
		String dir = parkModel.getSiteProp().getWebsite().substring(20, 24);
		
		for(int i = 1; i < new File("projectImages\\"+dir+"\\patches\\").listFiles().length+1; i++) {
			FileInputStream input = new FileInputStream("projectImages\\"+dir+"\\patches\\"+i+".jpg");
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(image.getHeight()/4);
			imageView.setFitWidth(image.getWidth()/4);
			imageView.setPickOnBounds(true);
			observableList.add(imageView);
		}
		fpane.getChildren().addAll(observableList);
		stackpane.setVisible(true);
	}
	
	@FXML
	private void campBTNAction() throws IOException {
		StackPane stackpane = (StackPane) ((Pane) myViewList.get(1)).getChildren().get(1);
		FlowPane pane = (FlowPane) ((StackPane) ((Pane) myViewList.get(1)).getChildren().get(1)).getChildren().get(0);
		BorderPane bpane = (BorderPane) pane.getChildren().get(0);
		ScrollPane spane = (ScrollPane) bpane.getChildren().get(1);
		AnchorPane apane = (AnchorPane) spane.getContent();
		FlowPane fpane = (FlowPane) apane.getChildren().get(0);
		fpane.getChildren().clear();
		CampAppPane cap = new CampAppPane();
		cap.setFpane(fpane);
		cap.setSite(parkModel.getSiteProp());
		cap.getFpane();
		stackpane.setVisible(true);	
	}
	
	
	@FXML
	private void addSiteAction() {
		parkModel.addSite();
	}
	
	@FXML 
	private void removeSiteAction() {
		parkModel.removeSite();
	}
	
	@FXML
	private void addAllSiteAction() {
		parkModel.addAllSite();
	}
	
	@FXML
	private void removeAllSiteAction() {
		parkModel.removeAllSite();
	}
	
	@FXML
	private void addDesAction() {
		parkModel.add();
	}
	
	@FXML 
	private void removeDesAction() {
		parkModel.remove();
	}
	
	@FXML
	private void addAllDesAction() {
		parkModel.addAll();
	}
	
	@FXML
	private void removeAllDesAction() {
		parkModel.removeAll();
	}
	
	@FXML
	private void addStateAction() {
		parkModel.addState();
	}
	
	@FXML 
	private void removeStateAction() {
		parkModel.removeState();
	}
	
	@FXML
	private void addAllStateAction() {
		parkModel.addAllState();
	}
	
	@FXML
	private void removeAllStateAction() {
		parkModel.removeAllState();
	}
	
	@FXML
	private void addRegionAction() {
		parkModel.addRegion();
	}
	
	@FXML 
	private void removeRegionAction() {
		parkModel.removeRegion();
	}
	
	@FXML
	private void addAllRegionAction() {
		parkModel.addAllRegion();
	}
	
	@FXML
	private void removeAllRegionAction() {
		parkModel.removeAllRegion();
	}
	
	@FXML
	private void submitAction() {
		parkModel.updateQueryState(parkModel.getRadioGroupSelection());
		//** Query Action***************************************************
			borderPane = (BorderPane) myViewList.get(0);
			Accordion acc = (Accordion) borderPane.getChildren().get(0);
			titledpane2 = acc.getPanes().get(1);
			ArcGISMap map = new ArcGISMap(Basemap.createNavigationVector());
			Point leftPoint = new Point(-13983303, 2649490, SpatialReferences.getWebMercator());
			Point rightPoint = new Point(-7301655, 6347819, SpatialReferences.getWebMercator());
			Envelope initialExtent = new Envelope(leftPoint, rightPoint);
			Viewpoint viewPoint = new Viewpoint(initialExtent);
		    map.setInitialViewpoint(viewPoint);
			MapView mapView = new MapView();
			GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
			mapView.getGraphicsOverlays().add(graphicsOverlay);
			showQueryResults(graphicsOverlay);
			mapView.setMap(map);
			borderPane.setCenter(mapView);
		//******************************************************************
	}
	
	@FXML void initialize() throws IOException{
		
		if(listview1 != null)
		{
			parkModel.connect();
			mapBTN.setFont(Font.font("System",FontWeight.BOLD, 14));
			mapBTN.setWrapText(true);
			mapBTN.setText("MAP");
			mapBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("map.png"))));
			
			photoBTN.setFont(Font.font("System",FontWeight.BOLD, 14));
			photoBTN.setWrapText(true);
			photoBTN.setText("PHOTOS");
			photoBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("photo.png"))));
			
			stampBTN.setFont(Font.font("System",FontWeight.BOLD, 14));
			stampBTN.setWrapText(true);
			stampBTN.setText("STAMPS");
			stampBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("stamp.png"))));
			
			patchBTN.setFont(Font.font("System",FontWeight.BOLD, 14));
			patchBTN.setWrapText(true);
			patchBTN.setText("PATCH");
			patchBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("patch.png"))));
			
			campBTN.setFont(Font.font("System",FontWeight.BOLD, 14));
			campBTN.setWrapText(true);
			campBTN.setText("CAMP");
			campBTN.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("camp.png"))));
			
			ToggleGroup group = new ToggleGroup();
		    radio0BTN.setToggleGroup(group);
		    radio2BTN.setSelected(true);
		    radio1BTN.setToggleGroup(group);
		    radio2BTN.setToggleGroup(group);
		    
		    group.selectedToggleProperty().addListener((observable, oldVal, newVal) 
		    		-> parkModel.setRadioGroupSelection(newVal));
		    
		    final WebEngine webEngine = webview1.getEngine();
			webEngine.load(getImageTest("https://www.nps.gov/index.htm"));

			listview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>(){

				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
						parkModel.setSiteProp(arg2);
						ParkModel.setMySite(arg2);
						hyperlink1.setText(arg2.getSite_name());
						designationLabel.setText(parkModel.getSiteDesignation(arg2).getDesignation_name());
						try {
							webEngine.load(getImageTest(arg2.getWebsite()));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						ArcGISMap map = new ArcGISMap(Basemap.createNavigationVector());
						Point siteLeft = new Point(arg2.getLat()-0.1, arg2.getLon()+0.1, SpatialReferences.getWgs84());
						Point siteRight = new Point(arg2.getLat()+0.1, arg2.getLon()-0.1, SpatialReferences.getWgs84());
						Envelope initialExtent = new Envelope(siteLeft, siteRight);
						Viewpoint viewPoint = new Viewpoint(initialExtent);
					    map.setInitialViewpoint(viewPoint);
						MapView mapView = new MapView();
						GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
						mapView.getGraphicsOverlays().add(graphicsOverlay);
						showQueryResults(graphicsOverlay);
						mapView.setMap(map);
						borderPane.setCenter(mapView);

				}
			});
			
			listview1.itemsProperty().bindBidirectional(parkModel.listviewProperty());
			
			lvSite1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>(){

				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
						parkModel.setSitePropQ(arg2);
				}	
	        });
			
	        lvSite1.itemsProperty().bindBidirectional(parkModel.lvSite1Property());
	        
	        lvSite2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>(){

				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
					parkModel.setSiteProp2(arg2);
				}	
	        });
	        
	        lvSite2.itemsProperty().bindBidirectional(parkModel.lvSite2Property());
	        	
			lvDes1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Designation>(){
				@Override
				public void changed(ObservableValue<? extends Designation> arg0,
						Designation arg1, Designation arg2) {
						parkModel.setDesignationProp(arg2);
				}	
	        });
	        lvDes1.itemsProperty().bindBidirectional(parkModel.lvDesignation1Property());
	        
	        lvDes2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Designation>(){
				@Override
				public void changed(ObservableValue<? extends Designation> arg0,
						Designation arg1, Designation arg2) {
					parkModel.setDesignationProp2(arg2);
				}	
	        });
	        
	        lvDes2.itemsProperty().bindBidirectional(parkModel.lvDesignation2Property());
	        
	        lvState1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<State>(){
				@Override
				public void changed(ObservableValue<? extends State> arg0,
						State arg1, State arg2) {
						parkModel.setStateProp(arg2);
				}	
	        });
	        
	        lvState1.itemsProperty().bindBidirectional(parkModel.lvState1Property());
	        
	        lvState2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<State>(){
				@Override
				public void changed(ObservableValue<? extends State> arg0,
						State arg1, State arg2) {
					parkModel.setStateProp2(arg2);
				}	
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
	
	private void showQueryResults(GraphicsOverlay graphicsOverlay) {
		
		SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
		
		List<Site> resultSet = parkModel.queryDB();
		titledpane2.setText(resultSet.size()+" Parks Found");
		
		Image newImage = new Image("arrowhead.png");
		PictureMarkerSymbol parkSymbol = new PictureMarkerSymbol(newImage);
		
		for(Site site :resultSet) {
			placePictureMarkerSymbol(parkSymbol, new Point(site.getLat(), site.getLon(), SPATIAL_REFERENCE), graphicsOverlay);	
		}
	    
	}
	
	private void placePictureMarkerSymbol(PictureMarkerSymbol markerSymbol, Point graphicPoint, GraphicsOverlay graphicsOverlay) {
		
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
	
	private String getImageTest(String url) throws IOException {
		String imageURL = "";
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("information.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
		BufferedReader br = new BufferedReader(new FileReader("information.txt"));
		try {
		    String line = br.readLine();
		    while (line != null && imageURL.equals("")) {
		        line = br.readLine();
		        if(line.matches("^.*<meta property=\"og:image\".*$")) {
		        	int indexStart = line.indexOf("https");
		        	int indexEnd = line.indexOf(" />");
		        	imageURL = line.substring(indexStart, indexEnd-1);
		        }
		        
		    }
		} finally {
		    br.close();
		}
		return imageURL;
	}
	
}
