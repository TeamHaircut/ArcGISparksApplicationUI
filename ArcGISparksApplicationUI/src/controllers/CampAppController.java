package controllers;

import models.CampAppModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CampAppController {
	
	public static CampAppModel campAppModel = new CampAppModel();
	@FXML private ListView<String> listview;
	
@FXML void initialize(){
		
		if(listview != null)
		{
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
