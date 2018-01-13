package controllers;

import java.util.ArrayList;

import models.ParkModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ParkController {
	
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML Button button1;
	@FXML ListView<String> listview1;
	
	@FXML
	private void button1Action(ActionEvent event){
		System.out.println("Button1 pressed");
	}
	
	@FXML void initialize(){
		if(listview1 != null)
		{
			listview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

				@Override
				public void changed(ObservableValue<? extends String> arg0,
						String arg1, String arg2) {
						//accountModel.setAccountProp(arg2);
				}
			});
			listview1.itemsProperty().bindBidirectional(parkModel.listviewProperty());
		}
	}

}
