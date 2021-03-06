package controllers;

import java.io.IOException;

import supportclasses.NPMap;
import models.CampAppModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CampAppController {
	
	public static CampAppModel campAppModel = new CampAppModel();
	@FXML private ListView<String> listview;
	
	@FXML void initialize() throws IOException{
			
			if(listview != null)
			{
				NPMap.initializeNPMap();
				listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
	
					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String arg1, String arg2) {
						System.out.println("Button Pressed");
						
					}
					
				});
	
				listview.itemsProperty().bindBidirectional(campAppModel.listviewProperty());
			}
	}

}
