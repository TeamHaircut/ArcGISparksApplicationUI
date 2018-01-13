package applications;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

import controllers.ParkController;

public class MyParksMain extends Application {
	private MapView mapView;

	  @Override
	  public void start(Stage stage) throws IOException {
		      StackPane stackpane = new StackPane();
		      
		      
		      Node view0 = FXMLLoader.load(getClass().getResource("/view0.fxml"));
		      ParkController.myViewList.add(view0);
		      
		      stackpane.getChildren().add(view0);
		      view0.setVisible(true);
		
		      Scene scene = new Scene(stackpane);
		      stage.setTitle("Test ArcGIS JavaFX Application");
		      stage.setWidth(800);
		      stage.setHeight(700);
		      stage.setScene(scene);
		      stage.show();
		
		      ArcGISMap map = new ArcGISMap(Basemap.createLightGrayCanvasVector());
		
		      mapView = new MapView();
		      mapView.setMap(map);
		
		      stackpane.getChildren().addAll(mapView);
	  }
	
	  @Override
	  public void stop() throws Exception {
		  if (mapView != null) {
			  mapView.dispose();
		  }
	  }
	
	  public static void main(String[] args) {Application.launch(args);}

}