package supportclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import models.ParkModel;
import controllers.ParkController;

public class ExplorerPane {

	public static StackPane getExplorerStackPane() {
		return (StackPane) ((Pane) ParkController.myViewList.get(1)).getChildren().get(1);
	}
	
	public static FlowPane getExplorerFlowPane() {
		return (FlowPane)(
					(AnchorPane)(
							(ScrollPane)(
									(BorderPane)(
											(FlowPane) (getExplorerStackPane()).getChildren().get(0))
									.getChildren().get(0))
							.getChildren().get(1))
					.getContent())
				.getChildren().get(0);
	}
	
	public static void viewGallery(String arg) throws FileNotFoundException {
		String dir = ParkModel.getMySite().getWebsite().substring(20, 24);

		ObservableList<ImageView> observableList = FXCollections.observableList(new ArrayList<ImageView>());
		getExplorerFlowPane().getChildren().clear();
		for(int i = 1; i < new File("E:\\projectImages\\"+dir+"\\"+arg+"\\").listFiles().length+1; i++) {
			FileInputStream input = new FileInputStream("E:\\projectImages\\"+dir+"\\"+arg+"\\1 ("+i+").jpg");
			Image image = new Image(input, 300, 0, true, false);
			ImageView imageView = new ImageView(image);
			if(!(image.getWidth() > image.getHeight())) {
				imageView.setFitWidth(250);
		        imageView.setPreserveRatio(true);
			}
			imageView.setPickOnBounds(true);
			observableList.add(imageView);
		}
		getExplorerFlowPane().getChildren().addAll(observableList);
		getExplorerStackPane().setVisible(true);
	}

}
