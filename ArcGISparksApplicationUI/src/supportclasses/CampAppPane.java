package supportclasses;

import java.io.IOException;

import entities.Site;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class CampAppPane {
	
	private FlowPane fpane;
	private Site site;
	
	public CampAppPane() {
		
	}

	public FlowPane getFpane() {
		return fpane;
	}

	public void setFpane(FlowPane fpane) throws IOException {
		this.fpane = fpane;
		fpane.getChildren().add(createCampAppPane());
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	public FlowPane createCampAppPane() throws IOException {
		FlowPane root = new FlowPane();
		Pane view1 = FXMLLoader.load(getClass().getResource("/view1.fxml"));
	    view1.setVisible(true);
		root.getChildren().add(view1);
		return root;
	}

}
