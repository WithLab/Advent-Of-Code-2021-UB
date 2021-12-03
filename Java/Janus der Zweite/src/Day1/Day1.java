package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args){
        Path path = Paths.get("src\\Day1\\input.txt");
        List<String> depths = new ArrayList<>();

        try {
            depths = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int counter = 0;

        for(int i = 0; i < depths.size(); i++){
            if(i+3 >= depths.size()){
                break;
            }

            int firstBlock = Integer.parseInt(depths.get(i)) + Integer.parseInt(depths.get(i+1)) + Integer.parseInt(depths.get(i+2));
            int secondBlock = Integer.parseInt(depths.get(i+1)) + Integer.parseInt(depths.get(i+2)) + Integer.parseInt(depths.get(i+3));

            if(secondBlock > firstBlock){
                counter++;
            }
        }

        System.out.println(counter);

    }
}
