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


public class Gui_On_Start extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Finanzmanager");
        Label label = new Label("Hallo");
        GridPane layout = new GridPane();
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setGridLinesVisible(true);
        layout.setAlignment(Pos.CENTER);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);

// Zweite Spalte soll auch 50% des Fensters einnehmen
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

// Die Regeln dem Grid hinzufügen
        layout.getColumnConstraints().addAll(col1, col2);
        Scene scene = new Scene(layout, 300, 300);
        layout.add(label, 1, 0);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
