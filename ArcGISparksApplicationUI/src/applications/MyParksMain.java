package applications;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyParksMain extends Application{
	
	public static void main(String...args){launch(args);}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group group = new Group();
		Scene scene = new Scene(group);
		
		stage.setWidth(200);
		stage.setHeight(200);
		stage.setTitle("Test FX Application");
		stage.setScene(scene);
		stage.show();
	}

}
