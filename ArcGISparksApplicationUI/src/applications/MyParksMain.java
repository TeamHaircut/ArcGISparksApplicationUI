package applications;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;

import controllers.ParkController;

public class MyParksMain extends Application {
	private MapView mapView;

	  @Override
	  public void start(Stage stage) throws IOException {
		      
	      BorderPane view0 = FXMLLoader.load(getClass().getResource("/view0.fxml"));
	      view0.setVisible(true);
	      	      
	      Scene scene = new Scene(view0);
	      stage.setTitle("Test JDBC ArcGIS JavaFX Application with MVC");
	      stage.setMaximized(true);
	      stage.setFullScreen(true);
	      stage.setScene(scene);
	      stage.show();
	
	      ArcGISMap map = new ArcGISMap(Basemap.createNavigationVector());
	   // create an initial extent envelope
	      Point leftPoint = new Point(-13983303, 2649490, SpatialReferences.getWebMercator());
	      Point rightPoint = new Point(-7301655, 6347819, SpatialReferences.getWebMercator());
	      Envelope initialExtent = new Envelope(leftPoint, rightPoint);

	      // create a viewpoint from envelope
	      Viewpoint viewPoint = new Viewpoint(initialExtent);

	      // set initial ArcGISMap extent
	      map.setInitialViewpoint(viewPoint);
	      mapView = new MapView();
	      mapView.setMap(map);
	      view0.setCenter(mapView);
	      
	      ParkController.myViewList.add(view0);
	  }
	
	  @Override
	  public void stop() throws Exception {
		  if (mapView != null) {
			  mapView.dispose();
		  }
	  }
	
	  public static void main(String[] args) {Application.launch(args);}

}