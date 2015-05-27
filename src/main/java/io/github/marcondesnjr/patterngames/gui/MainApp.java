package io.github.marcondesnjr.patterngames.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static BorderPane BPane;
    
    @Override
    public void start(Stage stage) throws Exception {
        BPane = FXMLLoader.load(getClass().getResource("/fxml/TelaInicial.fxml"));
        AnchorPane entrada = FXMLLoader.load(getClass().getResource("/fxml/TelaEntrada.fxml"));
        BPane.setCenter(entrada);
        Scene scene = new Scene(BPane);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    public static BorderPane getBPane() {
        return BPane;
    }

    public static void setBPane(BorderPane BPane) {
        MainApp.BPane = BPane;
    }
    
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
