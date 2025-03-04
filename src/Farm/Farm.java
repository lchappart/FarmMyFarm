package Farm;

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

    public Farm(int money, int level, int fields, int enclosures, int wheatSeeds, int cornSeeds, int melonSeeds, int wheat, int corn, int melon, int sheepCount, int cowCount, int pigCount) {
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
    }

    public boolean buy (int price) {
        if (money >= price) {
            money -= price;
            return true;
        }
        return false;
    }
}
