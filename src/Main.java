import Crops.Corn;
import Crops.Melon;
import Crops.Wheat;
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

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    @FXML
    private Label levelLabel;
    @FXML
    private Label wheatLabel;
    @FXML
    private Label cornLabel;
    @FXML
    private Label melonLabel;
    @FXML
    private Label sheepLabel;
    @FXML
    private Label cowLabel;
    @FXML
    private Label pigLabel;
    @FXML
    private Label dollars;
    @FXML
    private GridPane fields_grid;
    @FXML
    private Button addFieldButton;
    @FXML
    private Button plantButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        Main controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 600, 400);
        FarmDatabase db = new FarmDatabase();
        Farm farm = db.loadFarm();
        ArrayList<Long> fields = db.loadFields();
        Wheat wheat = new Wheat();
        Corn corn = new Corn();
        Melon melon = new Melon();
        System.out.println(fields.get(0));
        primaryStage.setOnCloseRequest(event -> {
            db.saveFarm(farm);
            try {
                db.saveFields(fields);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        primaryStage.setTitle("FarmMyFarm");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
        refreshFields(controller, farm, fields);
        refreshLabels(controller, farm);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {

        };
    }

    public void refreshFields(Main controller, Farm farm, ArrayList<Long> fields) throws Exception {
        controller.fields_grid.getChildren().clear();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 1; j++) {
                switch (j) {
                    case 0:
                        if (i < farm.fields) {
                            String btnText;
                            switch (i) {
                                case 0:
                                    btnText = "Plant Wheat";
                                    break;
                                case 1:
                                    btnText = "Plant Corn";
                                    break;
                                case 2:
                                    btnText = "Plant Melon";
                                    break;
                                default:
                                    btnText = "??";
                            }
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/left_field.fxml"));
                            Parent fieldRoot = fxmlLoader2.load();
                            Button plantButton = (Button) fieldRoot.lookup("#plantButton");
                            if (plantButton != null) {
                                plantButton.setText(btnText);
                                plantButton.setOnMouseClicked(event -> {
                                    farm.wheatSeeds = 9;
                                    farm.plant(btnText, fields);;
                                    refreshLabels(controller, farm);
                                });
                            }
                            controller.fields_grid.add(fieldRoot, 0, i);
                        } else {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/addField.fxml"));
                            Parent fieldRoot = fxmlLoader2.load();
                            Button addButton = (Button) fieldRoot.lookup("#addFieldButton");
                            if (addButton != null) {
                                addButton.setOnMouseClicked(event -> {
                                    if (farm.buy(200 * farm.level)) {
                                        refreshLabels(controller, farm);
                                        farm.level++;
                                        farm.fields++;
                                        try {
                                            refreshFields(controller, farm, fields);
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
                            Parent fieldRoot = fxmlLoader2.load();
                            Button plantButton = (Button) fieldRoot.lookup("#plantButton");
                            if (plantButton != null) {
                                plantButton.setOnMouseClicked(event -> {
                                    System.out.println(event.getButton());
                                });
                            }
                            controller.fields_grid.add(fieldRoot, 1, i);
                        } else {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("fxml/addField.fxml"));
                            Parent fieldRoot = fxmlLoader2.load();
                            Button addButton = (Button) fieldRoot.lookup("#addFieldButton");
                            if (addButton != null) {
                                addButton.setOnMouseClicked(event -> {
                                    if (farm.buy(200 * farm.level)) {
                                        refreshLabels(controller, farm);
                                        farm.level++;
                                        farm.enclosures++;
                                        try {
                                            refreshFields(controller, farm, fields);
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

    public void refreshLabels(Main controller, Farm farm) {
        controller.dollars.setText(farm.money + " $");
        controller.levelLabel.setText("Level: " + farm.level);
        controller.wheatLabel.setText("Wheat: " + farm.wheat);
        controller.cornLabel.setText("Corn: " + farm.corn);
        controller.melonLabel.setText("Melon: " + farm.melon);
        controller.sheepLabel.setText("Sheep: " + farm.sheepCount);
        controller.cowLabel.setText("Cow: " + farm.cowCount);
        controller.pigLabel.setText("Pig: " + farm.pigCount);
    }
    public static void main(String[] args) {
        launch(args);
    }
}