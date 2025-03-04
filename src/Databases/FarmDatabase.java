package Databases;

import Farm.Farm;

import java.io.*;
import java.text.MessageFormat;

public class FarmDatabase {
    public Farm loadFarm() {
        String path = "Farm/Farm.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return new Farm(
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
                    Integer.parseInt(reader.readLine())
            );
        } catch (IOException e) {
            System.out.println("Farm not found");
        }
        System.out.println("Creating new farm...");
        Farm farm = new Farm(500, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        saveFarm(farm);
        return farm;
    }

    public void saveFarm(Farm farm) {
        String path = "Farm/Farm.txt";
        String template = "{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}\n{8}\n{9}\n{10}\n{11}\n{12}";
        String farmData = MessageFormat.format(template, farm.money, farm.level, farm.fields, farm.enclosures, farm.wheatSeeds, farm.cornSeeds, farm.melonSeeds, farm.wheat, farm.corn, farm.melon, farm.sheepCount, farm.cowCount, farm.pigCount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(farmData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}