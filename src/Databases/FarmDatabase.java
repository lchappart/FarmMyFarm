package Databases;

import Farm.Farm;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public class FarmDatabase {
    public Farm loadFarm() {
        String path = "Farm/Farm.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return new Farm(
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", "")),
                    Integer.parseInt(reader.readLine().replace("\u202F", "")), Integer.parseInt(reader.readLine().replace("\u202F", ""))
            );
        } catch (IOException e) {
            System.out.println("Farm not found");
        }
        System.out.println("Creating new farm...");
        Farm farm = new Farm(500, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        saveFarm(farm);
        return farm;
    }

    public void saveFarm(Farm farm) {
        String path = "Farm/Farm.txt";
        String template = "{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}\n{8}\n{9}\n{10}\n{11}\n{12}\n{13}\n{14}\n{15}";
        String farmData = MessageFormat.format(template, farm.money, farm.level, farm.fields, farm.enclosures, farm.wheatSeeds, farm.cornSeeds, farm.melonSeeds, farm.wheat, farm.corn, farm.melon, farm.sheepCount, farm.cowCount, farm.pigCount, farm.woolCount, farm.milkCount, farm.truffleCount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(farmData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Long> loadFields() throws Exception{
        String path = "Farm/Fields.txt";
        ArrayList<Long> fields = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < 6; i++) {
                fields.add(Long.parseLong(reader.readLine().replace("\u202F", "")));            }
            return fields;
        } catch (IOException e) {
            System.out.println("Farm not found");
        }
        System.out.println("Creating new farm...");
        for (int i = 0; i < 6; i++) {
            fields.add(0L);
        }
        saveFields(fields);
        return fields;
    }

    public void saveFields(ArrayList<Long> fields) throws Exception {
        String path = "Farm/Fields.txt";
        String template = "{0}\n{1}\n{2}\n{3}\n{4}\n{5}";
        while (fields.size() < 6) {
            fields.add(0L);
        }
        String fieldData = MessageFormat.format(template, fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4), fields.get(5));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(fieldData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}