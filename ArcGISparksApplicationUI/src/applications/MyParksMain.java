package applications;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

import controllers.ParkController;

public class MyParksMain extends Application {
	private MapView mapView;

	  @Override
	  public void start(Stage stage) throws IOException {
		      
	      BorderPane view0 = FXMLLoader.load(getClass().getResource("/view0.fxml"));
	      ParkController.myViewList.add(view0);
	      
	      view0.setVisible(true);
	      
	      Scene scene = new Scene(view0);
	      stage.setTitle("Test ArcGIS JavaFX Application with MVC");
	      stage.setWidth(800);
	      stage.setHeight(700);
	      stage.setScene(scene);
	      stage.show();
	
	      ArcGISMap map = new ArcGISMap(Basemap.createLightGrayCanvasVector());
	
	      mapView = new MapView();
	      mapView.setMap(map);
	      view0.setCenter(mapView);
	  }
	
	  @Override
	  public void stop() throws Exception {
		  if (mapView != null) {
			  mapView.dispose();
		  }
	  }
	
	  public static void main(String[] args) {Application.launch(args);}

}