package Finanzmanager;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


public class Gui_On_Start extends Application{
    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image(getClass().getResourceAsStream("/app_icon.png"));
        stage.setTitle("Finanzmanager");
        Label label = new Label("Hallo");
        GridPane layout = new GridPane();
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setGridLinesVisible(true);
        layout.setAlignment(Pos.CENTER);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        layout.getColumnConstraints().addAll(col1, col2);
        Scene scene = new Scene(layout, 1024, 1024);
        layout.add(label, 1, 0);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(icon);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
