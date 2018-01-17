package controllers;

import java.util.ArrayList;
import java.util.List;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import models.ParkModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import entities.Site;

public class ParkController {
	
	public static ArrayList<BorderPane> myViewList = new ArrayList<BorderPane>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private BorderPane view0;
	@FXML private Button button1;
	@FXML private ListView<Site> listview1;
	
	@FXML
	private void queryAction(ActionEvent event){
		view0 = myViewList.get(0);
		
		ArcGISMap map = new ArcGISMap(Basemap.createLightGrayCanvasVector());
	
		MapView mapView = new MapView();
		GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
		mapView.getGraphicsOverlays().add(graphicsOverlay);
		
		displayDataPoints(graphicsOverlay);
		mapView.setMap(map);
		view0.setCenter(mapView);
	
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
	
	private void displayDataPoints(GraphicsOverlay graphicsOverlay) {
		
		
		SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
	    // create a red (0xFFFF0000) circle simple marker symbol
	    SimpleMarkerSymbol redCircleSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);

	    // create graphics and add to graphics overlay
	    Graphic graphic;
	    List<Site> resultSet = parkModel.queryDB();
	    
	    for(Site site :resultSet) {
	    	
	    	graphic = new Graphic(new Point(site.getLat(), site.getLon(), SPATIAL_REFERENCE), redCircleSymbol);
		    graphicsOverlay.getGraphics().add(graphic);
		    System.out.println(site+" "+site.getLon()+" "+site.getLat());
	    }
	    
	  }

}
