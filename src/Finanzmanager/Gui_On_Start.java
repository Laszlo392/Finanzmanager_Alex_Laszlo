package Finanzmanager;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


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
}
