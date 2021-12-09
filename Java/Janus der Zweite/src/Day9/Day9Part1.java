package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day9Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src\\Day9\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int low = 0;

        for(String l : lines){
            int point = 0;
            for(int i = 0; i < l.length(); i++){
                point = Integer.parseInt(String.valueOf(l.charAt(i)));
                if(isLowestAdj(point, lines, i, l))
                    low = low + (point + 1);
            }
        }

        System.out.println(low);
    }

    public static boolean isLowestAdj(Integer p, List<String> lines, Integer i, String l){
        int cnt = 0;
        int q = lines.indexOf(l);

        if(q > 0) {
            String prevL = lines.get(q - 1);

            if(Integer.parseInt(String.valueOf(prevL.charAt(i))) > p)
                cnt++;
        }

        if(i > 0)
            if(Integer.parseInt(String.valueOf(l.charAt(i-1))) > p)
                cnt++;

        if(i < l.length() - 1) {
            if (Integer.parseInt(String.valueOf(l.charAt(i+1))) > p)
                cnt++;
        }



        if(q < lines.size() - 1){
            String nextL = lines.get(q+1);

            if(Integer.parseInt(String.valueOf(nextL.charAt(i))) > p)
                cnt++;
        }

        if(cnt == 4)
            return true;
        else if(!(q>0) && cnt == 3) //First row
            return true;
        else if(!(q>0) && !(i>0) && cnt == 2)   //Point at (0,0)
            return true;
        else if(!(i>0) && cnt == 3) //First column
            return true;
        else if(!(q<lines.size()-1) && cnt == 3)    //Last row
            return true;
        else if(!(i<l.length()-1) && cnt == 3)  //Last column
            return true;
        else if(!(q>0) && !(i<l.length()-1) && cnt == 2) //Point at (0, x)
            return true;
        else if(!(q<lines.size()-1) && !(i>0) && cnt == 2)  //Point at (x, 0)
            return true;
        else if(!(q<lines.size()-1) && !(i<l.length()-1) && cnt == 2) //Point at (x, x)
            return true;
        else
            return false;

    }
}
