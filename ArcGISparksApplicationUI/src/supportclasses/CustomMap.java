package supportclasses;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class CustomMap {

	private static CustomMap instance = new CustomMap();

	private ArcGISMap map;
	private MapView mapView;

	private CustomMap() {
		map = new ArcGISMap(Basemap.createNavigationVector());
		mapView = new MapView();
	}

	public static CustomMap getInstance() {
		return instance;
	}

	public  ArcGISMap getMap() {
		return map;
	} 

	public MapView getMapView() {
		return mapView;
	}

	
}//end CustomMap