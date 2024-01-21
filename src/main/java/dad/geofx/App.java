package dad.geofx;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainController mainController = new MainController();
        Scene scene = new Scene(mainController.getView());

        primaryStage.setTitle("GeoFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}