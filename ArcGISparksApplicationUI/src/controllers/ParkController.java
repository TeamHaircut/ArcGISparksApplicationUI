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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import entities.Site;

public class ParkController {
	
	public static ArrayList<BorderPane> myViewList = new ArrayList<BorderPane>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private BorderPane view0;
	@FXML private Button button1;
	@FXML private CheckBox checkbox1;
	@FXML private ListView<Site> listview1;
	
	@FXML
	private void queryAction(ActionEvent event){
		view0 = myViewList.get(0);
		
		ArcGISMap map = new ArcGISMap(Basemap.createNavigationVector());
	
		MapView mapView = new MapView();
		GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
		mapView.getGraphicsOverlays().add(graphicsOverlay);
		
		showQueryResults(graphicsOverlay);
		mapView.setMap(map);
		view0.setCenter(mapView);
	
	}
	
	@FXML 
	private void checkboxAction(ActionEvent event) {
		int code100 = 100;
		parkModel.updateQueryState(code100);
	}
	
	
	@FXML void initialize(){
		
		if(listview1 != null)
		{
			parkModel.connect();
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
