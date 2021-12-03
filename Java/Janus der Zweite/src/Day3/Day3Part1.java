package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src\\Day3\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String gamma = "";
        String epsilon = "";

        for(int i = 0; i < lines.get(0).length(); i++){

            int zeros = 0;
            int ones = 0;

            for(int j = 0; j < lines.size(); j++){
                if(lines.get(j).charAt(i) == '0'){
                    zeros++;
                }
                else
                    ones++;
            }
            if(ones > zeros){
                gamma = gamma + '1';
                epsilon = epsilon + '0';
            }
            else if(zeros > ones) {
                gamma = gamma + '0';
                epsilon = epsilon + '1';
            }
        }

        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
    }
}
