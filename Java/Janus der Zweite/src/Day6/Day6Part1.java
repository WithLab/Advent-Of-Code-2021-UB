package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6Part1 {


    public static void main(String[] args){
        Path path = Paths.get("src\\Day6\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> fishes = new ArrayList<>();

        String[] initFishes = lines.get(0).split(",");
        for(int i = 0; i < initFishes.length; i++){
            fishes.add(Integer.parseInt(initFishes[i]));
        }

        ArrayList<Integer> fishCache = new ArrayList<>();

        for(int i = 0; i < 80; i++) {
            for(int j = 0; j < fishes.size(); j++){
                if(fishes.get(j) == 0){
                    fishCache.add(8);
                    fishes.set(j, 6);
                    continue;
                }
                fishes.set(j, fishes.get(j) - 1);
            }
            fishes.addAll(fishCache);
            fishCache.clear();
        }

        System.out.println(fishes.size());



    }
}
