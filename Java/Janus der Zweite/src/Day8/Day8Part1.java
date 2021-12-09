package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src\\Day8\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();

        for(String l : lines){
            String[] splitL = l.split("\\|");
            input.add(splitL[0]);
            output.add(splitL[1]);
        }

        ArrayList<String> ones = new ArrayList<>();
        ArrayList<String> fours = new ArrayList<>();
        ArrayList<String> sevens = new ArrayList<>();
        ArrayList<String> eights = new ArrayList<>();

        for(String out : output){
            String[] nums = out.split("\\s");
            for(int i = 0; i < nums.length; i++){
                switch (nums[i].length()){
                    case (2):
                        ones.add(nums[i]);
                        break;

                    case (4):
                        fours.add(nums[i]);
                        break;

                    case (3):
                        sevens.add(nums[i]);
                        break;

                    case (7):
                        eights.add(nums[i]);
                        break;
                }
            }
        }

        System.out.println(ones.size()+fours.size()+sevens.size()+eights.size());
    }
}
