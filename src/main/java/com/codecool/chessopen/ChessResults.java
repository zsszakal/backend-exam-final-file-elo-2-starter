package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {
    public static class Competitors {
        private String name;
        private int sum;

        public Competitors(String name, int sum) {
            this.name = name;
            this.sum = sum;
        }

        public String getName() {
            return name;
        }

        public int getSum() {
            return sum;
        }
    }
    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<Competitors> competitorsList = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < data.size(); i++) {
                String[] competitorData = data.get(i).split(",");
                competitorsList.add(new Competitors(competitorData[0],
                        Integer.parseInt(competitorData[1]) +
                                Integer.parseInt(competitorData[2]) +
                                Integer.parseInt(competitorData[3]) +
                                Integer.parseInt(competitorData[4]) +
                                Integer.parseInt(competitorData[5])));
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return competitorsList.stream()
                .sorted((Comparator.comparingInt(Competitors::getSum).reversed()))
                .map(Competitors::getName)
                .collect(Collectors.toList());
    }

}
