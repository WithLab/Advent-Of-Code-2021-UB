package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3Part2 {

    public static void main(String[] args) {
        Path path = Paths.get("src\\Day3\\input.txt");
        List<String> lines = new ArrayList<>();
        List<String> oxyList = new ArrayList<>();
        List<String> coList = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
            oxyList = Files.readAllLines(path);
            coList = Files.readAllLines(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> cacheOx = new ArrayList<>();
        List<String> cacheCo = new ArrayList<>();


        for (int i = 0; i < oxyList.get(0).length(); i++) {

            int zeros = 0;
            int ones = 0;

            cacheOx.clear();

            for (String s : oxyList) {
                if (s.charAt(i) == '0') {
                    zeros++;
                } else
                    ones++;
            }

            if(ones >= zeros){
                for(String s : oxyList){
                    if(s.charAt(i) == '0')
                        cacheOx.add(s);
                }
            }
            else{
                for(String s : oxyList){
                    if(s.charAt(i) == '1')
                        cacheOx.add(s);
                }
            }
            oxyList.removeAll(cacheOx);


            if (oxyList.size() == 1) {
                break;
            }
        }




        for (int i = 0; i < coList.get(0).length(); i++) {

            int zeros = 0;
            int ones = 0;

            cacheCo.clear();

            for (String s : coList) {
                if (s.charAt(i) == '0') {
                    zeros++;
                } else
                    ones++;
            }

            if(ones >= zeros){
                for(String s : coList){
                    if(s.charAt(i) == '1')
                        cacheCo.add(s);
                }
            }
            else{
                for(String s : coList){
                    if(s.charAt(i) == '0')
                        cacheCo.add(s);
                }
            }
            coList.removeAll(cacheCo);


            if (coList.size() == 1) {
                break;
            }
        }

        System.out.println(Integer.parseInt(oxyList.get(0), 2) * Integer.parseInt(coList.get(0), 2));

    }
}
