/*package Finanzmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Gui_On_Start extends Application{
    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image(getClass().getResourceAsStream("/app_icon.png"));
        stage.setTitle("Finanzmanager");

        BorderPane root = new BorderPane();
        root.setId("root");

        VBox balance = new VBox();
        balance.setId("balanceBox");
        Label balanceLabel = new Label("Balance:");
        Label balanceAmnt = new Label("Amount");
        balanceAmnt.setId("balanceAmnt");

        balance.getChildren().addAll(balanceLabel, balanceAmnt);

        root.setTop(balance);

        GridPane grid = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);


        VBox earnings = new VBox(5);
        earnings.setId("detailBox");
        Label earnLabel = new Label("Earnings");
        Label earnAmnt = new Label("x.xx€");
        earnAmnt.setId("number");
        earnings.getChildren().addAll(earnLabel, earnAmnt);

        VBox expenses = new VBox(5);
        expenses.setId("detailBox");
        Label expensesLabel = new Label("Expenses:");
        Label expenseAmnt = new Label("x.xx€");
        expenseAmnt.setId("number");
        expenses.getChildren().addAll(expensesLabel, expenseAmnt);

        Button newEarning = new Button("New Earning");
        newEarning.setMaxWidth(Double.MAX_VALUE);
        Button newExpense = new Button("New Expense");
        newExpense.setMaxWidth(Double.MAX_VALUE);

        grid.add(earnings, 0, 0);
        grid.add(expenses, 1, 0);
        grid.add(newEarning, 0, 1);
        grid.add(newExpense, 1, 1);

        root.setCenter(grid);

        Scene scene = new Scene(root, 400, 300);

        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(icon);

    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
package Finanzmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Optional;

public class Gui_On_Start extends Application {

    // Verbindung zur Logik-Klasse herstellen
    private FinanzmanagerLogik logik = new FinanzmanagerLogik();

    @Override
    public void start(Stage stage) throws Exception {
        // Falls das Icon nicht existiert, fangen wir den Fehler ab
        try {
            Image icon = new Image(getClass().getResourceAsStream("/app_icon.png"));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Icon nicht gefunden, Standard-Icon wird genutzt.");
        }

        stage.setTitle("Finanzmanager");

        BorderPane root = new BorderPane();
        root.setId("root");

        // --- TOP AREA: BALANCE ---
        VBox balance = new VBox();
        balance.setId("balanceBox");
        Label balanceLabel = new Label("Kontostand:");
        // Wir nutzen den Wert aus deiner Logik!
        Label balanceAmnt = new Label(String.format("%.2f €", logik.getKontostand()));
        balanceAmnt.setId("balanceAmnt");

        balance.getChildren().addAll(balanceLabel, balanceAmnt);
        root.setTop(balance);

        // --- CENTER AREA: GRID ---
        GridPane grid = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);

        VBox earnings = new VBox(5);
        earnings.setId("detailBox");
        Label earnLabel = new Label("Einnahmen gesamt:");
        Label earnAmnt = new Label(String.format("%.2f €", logik.getGesamtEinnahmen()));
        earnAmnt.setId("number");
        earnings.getChildren().addAll(earnLabel, earnAmnt);

        VBox expenses = new VBox(5);
        expenses.setId("detailBox");
        Label expensesLabel = new Label("Ausgaben gesamt:");
        Label expenseAmnt = new Label(String.format("%.2f €", logik.getGesamtAusgaben()));
        expenseAmnt.setId("number");
        expenses.getChildren().addAll(expensesLabel, expenseAmnt);

        Button newEarning = new Button("Neue Einnahme");
        newEarning.setMaxWidth(Double.MAX_VALUE);
        Button newExpense = new Button("Neue Ausgabe");
        newExpense.setMaxWidth(Double.MAX_VALUE);

        grid.add(earnings, 0, 0);
        grid.add(expenses, 1, 0);
        grid.add(newEarning, 0, 1);
        grid.add(newExpense, 1, 1);

        root.setCenter(grid);

        // --- BUTTON LOGIK ---

        // Event-Handler für Einnahmen
        newEarning.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Einnahme");
            dialog.setHeaderText("Neue Einnahme hinzufügen");
            dialog.setContentText("Betrag:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(amount -> {
                try {
                    double wert = Double.parseDouble(amount);
                    logik.transaktionHinzufuegen("Einnahme", wert);
                    // GUI updaten
                    updateLabels(balanceAmnt, earnAmnt, expenseAmnt);
                } catch (NumberFormatException ex) {
                    System.out.println("Bitte eine gültige Zahl eingeben!");
                }
            });
        });

        // Event-Handler für Ausgaben
        newExpense.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ausgabe");
            dialog.setHeaderText("Neue Ausgabe hinzufügen");
            dialog.setContentText("Betrag:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(amount -> {
                try {
                    double wert = Double.parseDouble(amount);
                    // Ausgaben sind negativ für die Logik
                    logik.transaktionHinzufuegen("Ausgabe", -Math.abs(wert));
                    // GUI updaten
                    updateLabels(balanceAmnt, earnAmnt, expenseAmnt);
                } catch (NumberFormatException ex) {
                    System.out.println("Ungültige Eingabe!");
                }
            });
        });

        Scene scene = new Scene(root, 600, 400);
        try {
            scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Stylesheet konnte nicht geladen werden.");
        }

        stage.setScene(scene);
        stage.show();
    }

    // Hilfsmethode um die Anzeige zu aktualisieren
    private void updateLabels(Label balance, Label earn, Label expense) {
        balance.setText(String.format("%.2f €", logik.getKontostand()));
        earn.setText(String.format("%.2f €", logik.getGesamtEinnahmen()));
        expense.setText(String.format("%.2f €", logik.getGesamtAusgaben()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
