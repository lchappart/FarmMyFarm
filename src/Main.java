import Databases.FarmDatabase;
import Farm.Farm;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    private Label dollars;
    @FXML
    private GridPane fields_grid;
    @FXML
    private Button addFieldButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        Main controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 600, 400);
        FarmDatabase db = new FarmDatabase();
        Farm farm = db.loadFarm();
        primaryStage.setOnCloseRequest(event -> {
            db.saveFarm(farm);
        });
        primaryStage.setTitle("FarmMyFarm");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
        controller.dollars.setText(farm.money + " $");
        refreshFields(controller, farm);
    }

    public void refreshFields(Main controller, Farm farm) throws Exception {
        controller.fields_grid.getChildren().clear();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 1; j++) {
                switch (j) {
                    case 0:
                        if (i < farm.fields) {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/left_field.fxml"));
                            controller.fields_grid.add(fxmlLoader2.load(), 0, i);
                        } else {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/addField.fxml"));
                            Parent fieldRoot = fxmlLoader2.load();
                            Button addButton = (Button) fieldRoot.lookup("#addFieldButton");
                            if (addButton != null) {
                                addButton.setOnMouseClicked(event -> {
                                    if (farm.buy(200 * farm.level)) {
                                        refreshDollars(controller, farm);
                                        farm.level++;
                                        farm.fields++;
                                        try {
                                            refreshFields(controller, farm);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                            controller.fields_grid.add(fieldRoot, 0, i);
                        }
                        break;
                    case 1:
                        if (i < farm.enclosures) {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/right_field.fxml"));
                            controller.fields_grid.add(fxmlLoader2.load(), 1, i);
                        } else {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/addField.fxml"));
                            Parent fieldRoot = fxmlLoader2.load();
                            Button addButton = (Button) fieldRoot.lookup("#addFieldButton");
                            if (addButton != null) {
                                addButton.setOnMouseClicked(event -> {
                                    if (farm.buy(200 * farm.level)) {
                                        refreshDollars(controller, farm);
                                        farm.level++;
                                        farm.enclosures++;
                                        try {
                                            refreshFields(controller, farm);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                            controller.fields_grid.add(fieldRoot, 1, i);
                        }
                        break;
                    default:
                }
            }
        }
    }

    public void refreshDollars(Main controller, Farm farm) {
        controller.dollars.setText(farm.money + " $");
    }
    public static void main(String[] args) {
        launch(args);
    }
}