package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7Part1 {

    public static void main(String[] args) {
        Path path = Paths.get("src\\Day7\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] crabsString = lines.get(0).split(",");
        Integer[] crabs = new Integer[crabsString.length];
        for(int i = 0; i < crabsString.length; i++){
            crabs[i] = Integer.parseInt(crabsString[i]);
        }

        ArrayList<Integer> savedFuel = new ArrayList<>();

        int fuel = 0;

        for(int i = 0; i < crabs.length; i++){
            fuel = 0;
            for(int j = 0; j < crabs.length; j++){
                fuel = fuel + Math.abs(crabs[i] - crabs[j]);
            }
            savedFuel.add(fuel);
        }

        int maxFuel = Collections.min(savedFuel);
        System.out.println(maxFuel);
    }
}
