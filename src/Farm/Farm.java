package Farm;


import java.util.ArrayList;

public class Farm {
    public int money;
    public int level;
    public int fields;
    public int enclosures;
    public int wheatSeeds;
    public int cornSeeds;
    public int melonSeeds;
    public int wheat;
    public int corn;
    public int melon;
    public int sheepCount;
    public int cowCount;
    public int pigCount;
    public int woolCount;
    public int milkCount;
    public int truffleCount;

    public Farm(int money, int level, int fields, int enclosures, int wheatSeeds, int cornSeeds, int melonSeeds, int wheat, int corn, int melon, int sheepCount, int cowCount, int pigCount, int woolCount, int milkCount, int truffleCount) {
        this.money = money;
        this.level = level;
        this.fields = fields;
        this.enclosures = enclosures;
        this.wheatSeeds = wheatSeeds;
        this.cornSeeds = cornSeeds;
        this.melonSeeds = melonSeeds;
        this.wheat = wheat;
        this.corn = corn;
        this.melon = melon;
        this.sheepCount = sheepCount;
        this.cowCount = cowCount;
        this.pigCount = pigCount;
        this.woolCount = woolCount;
        this.milkCount = milkCount;
        this.truffleCount = truffleCount;
    }

    public boolean buy (int price) {
        if (money >= price) {
            money -= price;
            return true;
        }
        return false;
    }

    public ArrayList<Long> plant (String plant, ArrayList<Long> fields) {
        switch (plant) {
            case "Plant Wheat":
                if (wheatSeeds > 5) {
                    long time = System.currentTimeMillis();
                    wheatSeeds-=5;
                    fields.set(0,time);
                    System.out.println(fields.get(0));
                    return fields;
                }
                break;
            case "Plant Corn":
                if (cornSeeds > 5) {
                    long time = System.currentTimeMillis();
                    cornSeeds-=5;
                    fields.set(1,time);
                    return fields;
                }
                break;
            case "Plant Melon":
                if (melonSeeds > 5) {
                    long time = System.currentTimeMillis();
                    melonSeeds-=5;
                    fields.set(2,time);
                    return fields;
                }
                break;
        }
        return fields;
    }

    public void feedAnimal(int type, ArrayList<Long> fields) {
        switch (type) {
            case 1:
                if (this.sheepCount > 0) {
                    this.sheepCount--;
                    fields.set(3, System.currentTimeMillis());
                }
                break;
            case 2:
                if (this.cowCount > 0) {
                    this.cowCount--;
                    fields.set(4, System.currentTimeMillis());
                }
                break;
            case 3:
                if (this.pigCount > 0) {
                    this.pigCount--;
                    fields.set(5, System.currentTimeMillis());
                }
        }
    }
}
