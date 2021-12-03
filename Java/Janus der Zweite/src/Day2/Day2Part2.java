package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {
    public static void main(String[] args){
        Path path = Paths.get("src\\Day2\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int depth = 0;
        int horPos = 0;
        int aim = 0;
        for(String s : lines){
            String[] splitCommand = s.split(" ");
            switch (splitCommand[0]) {
                case "forward" -> {
                    horPos = horPos + Integer.parseInt(splitCommand[1]);
                    depth = depth + aim * Integer.parseInt(splitCommand[1]);
                }
                case "down" -> aim = aim + Integer.parseInt(splitCommand[1]);
                case "up" -> aim = aim - Integer.parseInt(splitCommand[1]);
            }
        }

        System.out.println(depth*horPos);
    }
}
