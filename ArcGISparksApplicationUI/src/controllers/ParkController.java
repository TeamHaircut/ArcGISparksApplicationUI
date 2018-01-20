package controllers;

import java.util.ArrayList;
import java.util.List;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;

import models.ParkModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import entities.Site;

public class ParkController {
	
	public static ArrayList<BorderPane> myViewList = new ArrayList<BorderPane>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private BorderPane view0;
	@FXML private TitledPane titledpane2;
	@FXML private ChoiceBox<String> choicebox1;
	@FXML private ListView<Site> listview1;	
	
	@FXML void initialize(){
		
		if(listview1 != null)
		{
			parkModel.connect();
			choicebox1.setItems(FXCollections.observableArrayList(
				    "visited", "unvisited","both (visited & unvisited)")
				);
			
			choicebox1.getSelectionModel().selectedIndexProperty().addListener(new 
				ChangeListener<Number>() {

						@Override
						public void changed(
								ObservableValue<? extends Number> arg0,
								Number arg1, Number arg2) {
							parkModel.updateQueryState(arg2);
							//** Query Action***************************************************
								view0 = myViewList.get(0);
								Accordion acc = (Accordion) view0.getChildren().get(0);
								titledpane2 = acc.getPanes().get(1);
								ArcGISMap map = new ArcGISMap(Basemap.createNavigationVector());
								MapView mapView = new MapView();
								GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
								mapView.getGraphicsOverlays().add(graphicsOverlay);
								showQueryResults(graphicsOverlay);
								mapView.setMap(map);
								view0.setCenter(mapView);
							//******************************************************************
						}
				}
			);
			
			listview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>(){

				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
						parkModel.setSiteProp(arg2);
				}
			});
			listview1.itemsProperty().bindBidirectional(parkModel.listviewProperty());
		}
	}
	
	private void showQueryResults(GraphicsOverlay graphicsOverlay) {
		
		SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
		
		List<Site> resultSet = parkModel.queryDB();
		titledpane2.setText(resultSet.size()+" parks found");
		
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
	
}
