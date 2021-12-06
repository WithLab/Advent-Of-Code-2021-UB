package Day6;

import javax.management.MBeanRegistration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day6Part2 {


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


        long fish0 = 0;
        long fish1 = 0;
        long fish2 = 0;
        long fish3 = 0;
        long fish4 = 0;
        long fish5 = 0;
        long fish6 = 0;
        long fish7 = 0;
        long fish8 = 0;




        for(Integer fish : fishes){
            switch (fish) {
                case (0) -> fish0++;
                case (1) -> fish1++;
                case (2) -> fish2++;
                case (3) -> fish3++;
                case (4) -> fish4++;
                case (5) -> fish5++;
                case (6) -> fish6++;
                case (7) -> fish7++;
                case (8) -> fish8++;
            }
        }

        for(int i = 0; i < 256; i++){
            long temp = fish0;
            fish0 = fish1;
            fish1 = fish2;
            fish2 = fish3;
            fish3 = fish4;
            fish4 = fish5;
            fish5 = fish6;
            fish6 = fish7 + temp;
            fish7 = fish8;
            fish8 = temp;
        }

        System.out.println(fish0 + fish1 + fish2 + fish3 + fish4 + fish5 + fish6 + fish7 + fish8);


    }
}
