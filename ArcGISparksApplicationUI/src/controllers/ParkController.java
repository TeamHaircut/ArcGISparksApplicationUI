package controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
//import models.AccountModel;

public class ParkController {
	
	public static ArrayList<Node> myViewList = new ArrayList<Node>();
	//public static AccountModel accountModel = new AccountModel();
	
	@FXML private Node applicationView;//view 0
	
	@FXML void initialize(){
		//myViewList.get(0).setVisible(true);
	}

}
