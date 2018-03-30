package applications;
import com.esri.runtime.ArcGISRuntime;
import java.io.IOException;

import supportclasses.CustomMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.runtime.ArcGISRuntime;

import controllers.ParkController;

public class MyParksMain extends Application {
	CustomMap map = CustomMap.getInstance();

	  @Override
	  public void start(Stage stage) throws IOException {
		  ArcGISRuntime.setClientID("sZaIn0lZBqK7jVvM");
	      Pane view0 = FXMLLoader.load(getClass().getResource("/view0.fxml"));
	      view0.setVisible(true);
	      	      
	      Scene scene = new Scene(view0);
	      stage.setTitle("Test JDBC ArcGIS JavaFX Application with MVC");
	      stage.setMaximized(true);
	      stage.setFullScreen(true);
	      stage.setScene(scene);
	      stage.show();
	      
	      Point leftPoint = new Point(-13983303, 2649490, SpatialReferences.getWebMercator());
	      Point rightPoint = new Point(-7301655, 6347819, SpatialReferences.getWebMercator());
	      Envelope initialExtent = new Envelope(leftPoint, rightPoint);
	      Viewpoint viewPoint = new Viewpoint(initialExtent);
	      map.getMap().setInitialViewpoint(viewPoint);
	      map.getMapView().setMap(map.getMap());
	      
	      BorderPane borderPane = (BorderPane) ((StackPane) view0.getChildren().get(0)).getChildren().get(0);
	      Pane pane = (Pane)borderPane.getChildren().get(0);
	      StackPane sPane = (StackPane)(pane).getChildren().get(0);
	      sPane.getChildren().add(map.getMapView());
	      //borderPane.setCenter(map.getMapView());
	      ParkController.myViewList.add(borderPane);
	      ParkController.myViewList.add(view0);
	      
	  }
	
	  @Override
	  public void stop() throws Exception {
		  if (map.getMapView() != null) {
			  map.getMapView().dispose();
		  }
	  }
	
	  public static void main(String[] args) {Application.launch(args);}

}