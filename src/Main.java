import Animals.Animals;
import Animals.Sheep;
import Animals.Cow;
import Animals.Pig;
import Crops.Corn;
import Crops.Crops;
import Crops.Melon;
import Crops.Wheat;
import Databases.FarmDatabase;
import Farm.Farm;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        ArrayList<Crops> crops = new ArrayList<>();
        crops.add(new Wheat());
        crops.add(new Corn());
        crops.add(new Melon());
        ArrayList<Animals> animals = new ArrayList<>();
        animals.add(new Sheep());
        animals.add(new Cow());
        animals.add(new Pig());
        primaryStage.setTitle("FarmMyFarm");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
        refreshFields(controller, farm, fields);
        refreshLabels(controller, farm);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            for(int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 1; j++) {
                    switch(j) {
                        case 0:
                            if (fields.get(i) == 0) {
                                break;
                            }
                            long time = System.currentTimeMillis();
                            if (time - fields.get(i) > crops.get(i).growTime) {
                                System.out.println("graines poussées");
                                switch(i) {
                                    case 0:
                                        farm.wheat+= crops.get(i).plantResult;
                                        break;
                                    case 1:
                                        farm.corn+= crops.get(i).plantResult;
                                        break;
                                    case 2:
                                        farm.melon+= crops.get(i).plantResult;
                                        break;
                                }
                                fields.set(i, 0L);

                            }
                            break;
                        case 1:
                            if (fields.get(i + 3) == 0) {
                                break;
                            }
                            else {
                                long time2 = System.currentTimeMillis();
                                if (time2 - fields.get(i + 3) > animals.get(i).timeBeforeDeath) {
                                    System.out.println("animal mort");
                                    switch (i) {
                                        case 0:
                                            farm.sheepCount--;
                                            break;
                                        case 1:
                                            farm.cowCount--;
                                            break;
                                        case 2:
                                            farm.pigCount--;
                                            break;
                                    }
                                    fields.set(i + 3, 0L);
                                } else if (animals.get(i).age > 180000 && fields.get(i + 3) % 180000 < 0) {
                                    switch (i) {
                                        case 0:
                                            System.out.println("Laine Gagnée");
                                            farm.woolCount++;
                                            break;
                                        case 1:
                                            System.out.println("Lait gagné");
                                            break;
                                        case 2:
                                            System.out.println("Truffe Gagnée");
                                            break;
                                    }
                                }
                                else {
                                    animals.get(i).age++;
                                }
                            }

                    }
                }
            }
            Platform.runLater(() -> {
                refreshLabels(controller, farm);
            });

        };
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        primaryStage.setOnCloseRequest(event -> {
            executor.schedule(() -> {
                executor.shutdown();
                System.out.println("Executor stopped.");
            }, 10, TimeUnit.SECONDS);
            db.saveFarm(farm);
            try {
                db.saveFields(fields);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
                                    farm.cornSeeds = 9;
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
//                                    farm.feedAnimal(i);
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
        controller.wheatLabel.setText("🌾: " + farm.wheat);
        controller.cornLabel.setText("🌽: " + farm.corn);
        controller.melonLabel.setText("🍈: " + farm.melon);
        controller.sheepLabel.setText("🐑: " + farm.sheepCount);
        controller.cowLabel.setText("🐄: " + farm.cowCount);
        controller.pigLabel.setText("🐖: " + farm.pigCount);
    }
    public static void main(String[] args) {
        launch(args);
    }
}