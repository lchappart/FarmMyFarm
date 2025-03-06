package Farm;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Random;

public class Shop {
    private GridPane shopGrid;

    public int wheatSeedsSellPrice;
    public int wheatSeedsBuyPrice;
    public int cornSeedsSellPrice;
    public int cornSeedsBuyPrice;
    public int melonSeedsSellPrice;
    public int melonSeedsBuyPrice;
    public int wheatSellPrice;
    public int wheatBuyPrice;
    public int cornSellPrice;
    public int cornBuyPrice;
    public int melonSellPrice;
    public int melonBuyPrice;
    public int sheepSellPrice;
    public int sheepBuyPrice;
    public int cowSellPrice;
    public int cowBuyPrice;
    public int pigSellPrice;
    public int pigBuyPrice;
    public int woolSellPrice;
    public int woolBuyPrice;
    public int milkSellPrice;
    public int milkBuyPrice;
    public int truffleSellPrice;
    public int truffleBuyPrice;

    public Shop() {
        Random rand = new Random();
        this.wheatSeedsSellPrice = rand.nextInt(10) + 1;
        this.wheatSeedsBuyPrice = rand.nextInt(10) + 1;
        this.cornSeedsSellPrice = rand.nextInt(10) + 5;
        this.cornSeedsBuyPrice = rand.nextInt(10) + 5;
        this.melonSeedsSellPrice = rand.nextInt(10) + 10;
        this.melonSeedsBuyPrice = rand.nextInt(10) + 10;
        this.wheatSellPrice = this.wheatSeedsSellPrice * 2;
        this.wheatBuyPrice = this.wheatSeedsBuyPrice * 2;
        this.cornSellPrice = this.cornSeedsSellPrice * 2;
        this.cornBuyPrice = this.cornSeedsBuyPrice * 2;
        this.melonSellPrice = this.melonSeedsSellPrice * 2;
        this.melonBuyPrice = this.melonSeedsBuyPrice * 2;
        this.sheepSellPrice = rand.nextInt(100) + 50;
        this.sheepBuyPrice = rand.nextInt(100) + 50;
        this.cowSellPrice = rand.nextInt(100) + 100;
        this.cowBuyPrice = rand.nextInt(100) + 100;
        this.pigSellPrice = rand.nextInt(100) + 150;
        this.pigBuyPrice = rand.nextInt(100) + 150;
        this.woolSellPrice = rand.nextInt(20) + 10;
        this.woolBuyPrice = rand.nextInt(20) + 10;
        this.milkSellPrice = rand.nextInt(20) + 20;
        this.milkBuyPrice = rand.nextInt(20) + 20;
        this.truffleSellPrice = rand.nextInt(20) + 30;
        this.truffleBuyPrice = rand.nextInt(20) + 30;
    }

    public void initializeShop(GridPane shopGrid, Farm farm) {
        int count = 0;
        this.shopGrid = shopGrid;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shopItem.fxml"));
                    Pane shopItem = loader.load();
                    ImageView shopItemImage = (ImageView) shopItem.lookup("#shopItemImage");
                    shopItemImage.setImage(new Image(getClass().getResourceAsStream("/Includes/Shop/" + i + j + ".png")));
                    String shopItemName = "" + i + j;
                    Label shopItemSellPrice = (Label) shopItem.lookup("#shopItemSellPrice");
                    Label shopItemBuyPrice = (Label) shopItem.lookup("#shopItemBuyPrice");
                    Label shopItemNameLabel = (Label) shopItem.lookup("#shopItemNameLabel");
                    Label quantityLabel = (Label) shopItem.lookup("#quantityLabel");
                    Button buyButton = (Button) shopItem.lookup("#buyButton");
                    Button sellButton = (Button) shopItem.lookup("#sellButton");
                    switch (shopItemName) {
                        case "00":
                            shopItemNameLabel.setText("Wheat Seeds");
                            shopItemSellPrice.setText("Sell: " + this.wheatSeedsSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.wheatSeedsBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.wheatSeeds);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.wheatSeedsBuyPrice) {
                                    farm.money -= this.wheatSeedsBuyPrice;
                                    farm.wheatSeeds++;
                                    quantityLabel.setText("Quantity: " + farm.wheatSeeds);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.wheatSeeds > 0) {
                                    farm.money += this.wheatSeedsSellPrice;
                                    farm.wheatSeeds--;
                                    quantityLabel.setText("Quantity: " + farm.wheatSeeds);
                                }
                            });
                            break;
                        case "10":
                            shopItemNameLabel.setText("Corn Seeds");
                            shopItemSellPrice.setText("Sell: " + this.cornSeedsSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.cornSeedsBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.cornSeeds);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.cornSeedsBuyPrice) {
                                    farm.money -= this.cornSeedsBuyPrice;
                                    farm.cornSeeds++;
                                    quantityLabel.setText("Quantity: " + farm.cornSeeds);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.cornSeeds > 0) {
                                    farm.money += this.cornSeedsSellPrice;
                                    farm.cornSeeds--;
                                    quantityLabel.setText("Quantity: " + farm.cornSeeds);
                                }
                            });
                            break;
                        case "20":
                            shopItemNameLabel.setText("Melon Seeds");
                            shopItemSellPrice.setText("Sell: " + this.melonSeedsSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.melonSeedsBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.melonSeeds);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.melonSeedsBuyPrice) {
                                    farm.money -= this.melonSeedsBuyPrice;
                                    farm.melonSeeds++;
                                    quantityLabel.setText("Quantity: " + farm.melonSeeds);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.melonSeeds > 0) {
                                    farm.money += this.melonSeedsSellPrice;
                                    farm.melonSeeds--;
                                    quantityLabel.setText("Quantity: " + farm.melonSeeds);
                                }
                            });
                            break;
                        case "01":
                            shopItemNameLabel.setText("Wheat");
                            shopItemSellPrice.setText("Sell: " + this.wheatSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.wheatBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.wheat);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.wheatBuyPrice) {
                                    farm.money -= this.wheatBuyPrice;
                                    farm.wheat++;
                                    quantityLabel.setText("Quantity: " + farm.wheat);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.wheat > 0) {
                                    farm.money += this.wheatSellPrice;
                                    farm.wheat--;
                                    quantityLabel.setText("Quantity: " + farm.wheat);
                                }
                            });
                            break;
                        case "11":
                            shopItemNameLabel.setText("Corn");
                            shopItemSellPrice.setText("Sell: " + this.cornSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.cornBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.corn);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.cornBuyPrice) {
                                    farm.money -= this.cornBuyPrice;
                                    farm.corn++;
                                    quantityLabel.setText("Quantity: " + farm.corn);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.corn > 0) {
                                    farm.money += this.cornSellPrice;
                                    farm.corn--;
                                    quantityLabel.setText("Quantity: " + farm.corn);
                                }
                            });
                            break;
                        case "21":
                            shopItemNameLabel.setText("Melon");
                            shopItemSellPrice.setText("Sell: " + this.melonSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.melonBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.melon);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.melonBuyPrice) {
                                    farm.money -= this.melonBuyPrice;
                                    farm.melon++;
                                    quantityLabel.setText("Quantity: " + farm.melon);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.melon > 0) {
                                    farm.money += this.melonSellPrice;
                                    farm.melon--;
                                    quantityLabel.setText("Quantity: " + farm.melon);
                                }
                            });
                            break;
                        case "02":
                            shopItemNameLabel.setText("Sheep");
                            shopItemSellPrice.setText("Sell: " + this.sheepSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.sheepBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.sheepCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.sheepBuyPrice) {
                                    farm.money -= this.sheepBuyPrice;
                                    farm.sheepCount++;
                                    quantityLabel.setText("Quantity: " + farm.sheepCount);
                                }
                            });
                            break;
                        case "12":
                            shopItemNameLabel.setText("Cow");
                            shopItemSellPrice.setText("Sell: " + this.cowSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.cowBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.cowCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.cowBuyPrice) {
                                    farm.money -= this.cowBuyPrice;
                                    farm.cowCount++;
                                    quantityLabel.setText("Quantity: " + farm.cowCount);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.cowCount > 0) {
                                    farm.money += this.cowSellPrice;
                                    farm.cowCount--;
                                    quantityLabel.setText("Quantity: " + farm.cowCount);
                                }
                            });
                            break;
                        case "22":
                            shopItemNameLabel.setText("Pig");
                            shopItemSellPrice.setText("Sell: " + this.pigSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.pigBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.pigCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.pigBuyPrice) {
                                    farm.money -= this.pigBuyPrice;
                                    farm.pigCount++;
                                    quantityLabel.setText("Quantity: " + farm.pigCount);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.pigCount > 0) {
                                    farm.money += this.pigSellPrice;
                                    farm.pigCount--;
                                    quantityLabel.setText("Quantity: " + farm.pigCount);
                                }
                            });
                            break;
                        case "03":
                            shopItemNameLabel.setText("Wool");
                            shopItemSellPrice.setText("Sell: " + this.woolSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.woolBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.woolCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.woolCount > 0) {
                                    farm.money += this.woolSellPrice;
                                    farm.woolCount--;
                                    quantityLabel.setText("Quantity: " + farm.woolCount);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.woolBuyPrice) {
                                    farm.money -= this.woolBuyPrice;
                                    farm.woolCount++;
                                    quantityLabel.setText("Quantity: " + farm.woolCount);
                                }
                            });
                            break;
                        case "13":
                            shopItemNameLabel.setText("Milk");
                            shopItemSellPrice.setText("Sell: " + this.milkSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.milkBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.milkCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.milkBuyPrice) {
                                    farm.money -= this.milkBuyPrice;
                                    farm.milkCount++;
                                    quantityLabel.setText("Quantity: " + farm.milkCount);
                                }
                            });
                            sellButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.milkCount > 0) {
                                    farm.money += this.milkSellPrice;
                                    farm.milkCount--;
                                    quantityLabel.setText("Quantity: " + farm.milkCount);
                                }
                            });
                            break;
                        case "23":
                            shopItemNameLabel.setText("Truffle");
                            shopItemSellPrice.setText("Sell: " + this.truffleSellPrice);
                            shopItemBuyPrice.setText("Buy: " + this.truffleBuyPrice);
                            quantityLabel.setText("Quantity: " + farm.truffleCount);
                            buyButton.setOnMouseClicked(mouseEvent -> {
                                if (farm.money >= this.truffleBuyPrice) {
                                    farm.money -= this.truffleBuyPrice;
                                    farm.truffleCount++;
                                    quantityLabel.setText("Quantity: " + farm.truffleCount);
                                }
                            });
                            break;
                    }
                    shopGrid.add(shopItem, j, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}