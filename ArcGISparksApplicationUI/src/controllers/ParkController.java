package controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ParkController {
	
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	
	@FXML Button button1;
	@FXML Button button2;
	
	@FXML
	private void button1Action(ActionEvent event){
		System.out.println("Button1 pressed");
	}
	
	@FXML
	private void button2Action(ActionEvent event){
		System.out.println("Button2 pressed");
	}
	
	@FXML void initialize(){
	}

}
