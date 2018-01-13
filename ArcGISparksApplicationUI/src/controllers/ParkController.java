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

import entities.TestEntity;


public class ParkController {
	
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	public static ParkModel parkModel = new ParkModel();
	
	@FXML Button button1;
	@FXML ListView<TestEntity> listview1;
	
	@FXML
	private void button1Action(ActionEvent event){
		System.out.println("Button1 pressed");
	}
	
	@FXML void initialize(){
		if(listview1 != null)
		{
			parkModel.connect();
			listview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TestEntity>(){

				@Override
				public void changed(ObservableValue<? extends TestEntity> arg0,
						TestEntity arg1, TestEntity arg2) {
						parkModel.setTestentityProp(arg2);
				}
			});
			listview1.itemsProperty().bindBidirectional(parkModel.listviewProperty());
		}
	}

}
