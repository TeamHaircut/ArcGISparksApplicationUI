package controllers;

import java.util.ArrayList;
import java.util.List;

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
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import entities.Designation;
import entities.Region;
import entities.Site;
import entities.State;

public class ParkController {
	
	public static ArrayList<BorderPane> myViewList = new ArrayList<BorderPane>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML private BorderPane view0;
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
			view0 = myViewList.get(0);
			Accordion acc = (Accordion) view0.getChildren().get(0);
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
			view0.setCenter(mapView);
		//******************************************************************
	}
	
	@FXML void initialize(){
		
		
		if(listview1 != null)
		{
			parkModel.connect();
			
			ToggleGroup group = new ToggleGroup();
		    radio0BTN.setToggleGroup(group);
		    radio2BTN.setSelected(true);
		    radio1BTN.setToggleGroup(group);
		    radio2BTN.setToggleGroup(group);
		    
		    group.selectedToggleProperty().addListener((observable, oldVal, newVal) 
		    		-> parkModel.setRadioGroupSelection(newVal));
		    
		    final WebEngine webEngine = webview1.getEngine();
			webEngine.load("https://www.nps.gov/common/uploads/banner_image/imr/homepage/51D13BEA-1DD8-B71B-0B786860A6FE90FC.jpg");
			
			listview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>(){

				@Override
				public void changed(ObservableValue<? extends Site> arg0,
						Site arg1, Site arg2) {
						parkModel.setSiteProp(arg2);
						hyperlink1.setText(arg2.getSite_name());
						designationLabel.setText(parkModel.getSiteDesignation(arg2).getDesignation_name());
						
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
	        
	        lvSite1.setCellFactory(new Callback<ListView<Site>, ListCell<Site>>() {
	            @Override
	            public ListCell<Site> call(ListView<Site> p) {
	                return new ListCell<Site>() {
	                    @Override
	                    protected void updateItem(Site item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        
	        lvSite2.setCellFactory(new Callback<ListView<Site>, ListCell<Site>>() {
	            @Override
	            public ListCell<Site> call(ListView<Site> p) {
	                return new ListCell<Site>() {
	                    @Override
	                    protected void updateItem(Site item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
				
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
	        
	        lvDes1.setCellFactory(new Callback<ListView<Designation>, ListCell<Designation>>() {
	            @Override
	            public ListCell<Designation> call(ListView<Designation> p) {
	                return new ListCell<Designation>() {
	                    @Override
	                    protected void updateItem(Designation item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        
	        lvDes2.setCellFactory(new Callback<ListView<Designation>, ListCell<Designation>>() {
	            @Override
	            public ListCell<Designation> call(ListView<Designation> p) {
	                return new ListCell<Designation>() {
	                    @Override
	                    protected void updateItem(Designation item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        
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
	        
	        lvState1.setCellFactory(new Callback<ListView<State>, ListCell<State>>() {
	            @Override
	            public ListCell<State> call(ListView<State> p) {
	                return new ListCell<State>() {
	                    @Override
	                    protected void updateItem(State item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        
	        lvState2.setCellFactory(new Callback<ListView<State>, ListCell<State>>() {
	            @Override
	            public ListCell<State> call(ListView<State> p) {
	                return new ListCell<State>() {
	                    @Override
	                    protected void updateItem(State item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });

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
	        
	        lvRegion1.setCellFactory(new Callback<ListView<Region>, ListCell<Region>>() {
	            @Override
	            public ListCell<Region> call(ListView<Region> p) {
	                return new ListCell<Region>() {
	                    @Override
	                    protected void updateItem(Region item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        
	        lvRegion2.setCellFactory(new Callback<ListView<Region>, ListCell<Region>>() {
	            @Override
	            public ListCell<Region> call(ListView<Region> p) {
	                return new ListCell<Region>() {
	                    @Override
	                    protected void updateItem(Region item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText(null);
	                        } else {
	                            setText(item == null ? "null" : item.toString());
	                            setFont(Font.font(10));
	                        }
	                    }
	                };
	            }
	        });
	        //
		        
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
	
}
