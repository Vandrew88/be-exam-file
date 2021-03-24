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

    public static class Participant {
        private String name;
        private int sumOfPoints;

        public Participant(String name, int sumOfPoints) {
            this.name = name;
            this.sumOfPoints = sumOfPoints;
        }

        public String getName() {
            return name;
        }

        public int getSumOfPoints() {
            return sumOfPoints;
        }
    }

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<Participant> participants = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < data.size(); i++) {
                participants.add(getParticipant(data.get(i)));
            }
            return participants.stream()
                    .sorted(Comparator.comparingInt(Participant::getSumOfPoints).reversed())
                    .map(Participant::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return null;
    }

    private static Participant getParticipant(String line) {
        String[] participantData = line.split(",");
        return new Participant(participantData[0],
                Integer.parseInt(participantData[1]) + Integer.parseInt(participantData[2]) + Integer.parseInt(participantData[3]) + Integer.parseInt(participantData[4]) + Integer.parseInt(participantData[5]));
    }

}
