package Day9;

import Day8.Coordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9Part2 {

    public static int basinCount = 1;
    public static ArrayList<Coordinate> currentBasins = new ArrayList<>();

    public static void main(String[] args) {
        Path path = Paths.get("src\\Day9\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> basins = new ArrayList<>();

        for(String l : lines){
            int point = 0;
            for(int i = 0; i < l.length(); i++){
                point = Integer.parseInt(String.valueOf(l.charAt(i)));
                if(isLowestAdj(point, lines, i, l)) {
                    checkBasins(point, lines, i, l);
                    basins.add(currentBasins.size());
                    currentBasins.clear();
                }
            }
        }


        int v1 = 0;
        int v2 = 0;
        int v3 = 0;

        v1 = Collections.max(basins);
        basins.remove(Integer.valueOf(v1));
        v2 = Collections.max(basins);
        basins.remove(Integer.valueOf(v2));
        v3 = Collections.max(basins);


        System.out.println(v1*v2*v3);

    }

    public static boolean isLowestAdj(Integer p, List<String> lines, Integer i, String l){
        int cnt = 0;
        int q = lines.indexOf(l);

        if(q > 0) {
            String prevL = lines.get(q - 1);

            if(Integer.parseInt(String.valueOf(prevL.charAt(i))) > p){
                cnt++;
                basinCount++;
            }

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
    public static void checkBasins(Integer p, List<String> lines, Integer i, String l){
        int q = lines.indexOf(l);

        if(q > 0) {
            String prevL = lines.get(q - 1);

            if(Integer.parseInt(String.valueOf(prevL.charAt(i))) > p && Integer.parseInt(String.valueOf(prevL.charAt(i))) < 9) {
                boolean contains = false;
                for(Coordinate c : currentBasins){
                    if(c.getX() == i && c.getY() == q - 1) {
                        contains = true;
                    }
                    }
                if(!contains){
                    currentBasins.add(new Coordinate(i, q-1));
                    checkBasins(Integer.parseInt(String.valueOf(prevL.charAt(i))), lines, i, lines.get(q-1));
                }
                }
            }



        if(i > 0) {
            if (Integer.parseInt(String.valueOf(l.charAt(i - 1))) > p && Integer.parseInt(String.valueOf(l.charAt(i - 1))) < 9) {
                boolean contains = false;
                for (Coordinate c : currentBasins) {
                    if (c.getX() == i - 1 && c.getY() == q) {
                        contains = true;
                    }
                }
                if (!contains) {
                    currentBasins.add(new Coordinate(i - 1, q));
                    checkBasins(Integer.parseInt(String.valueOf(l.charAt(i - 1))), lines, i - 1, l);
                }
                }
        }

        if(i < l.length() - 1) {
            if (Integer.parseInt(String.valueOf(l.charAt(i + 1))) > p && Integer.parseInt(String.valueOf(l.charAt(i + 1))) < 9) {
                boolean contains = false;
                for (Coordinate c : currentBasins) {
                    if (c.getX() == i + 1 && c.getY() == q) {
                        contains = true;
                    }
                }
                if (!contains) {
                    currentBasins.add(new Coordinate(i+1, q));
                    checkBasins(Integer.parseInt(String.valueOf(l.charAt(i + 1))), lines, i + 1, l);
                }
            }
        }



        if(q < lines.size() - 1){
            String nextL = lines.get(q+1);

            if(Integer.parseInt(String.valueOf(nextL.charAt(i))) > p && Integer.parseInt(String.valueOf(nextL.charAt(i))) < 9) {
                boolean contains = false;
                for(Coordinate c : currentBasins){
                    if(c.getX() == i && c.getY() == q+1) {
                        contains = true;
                    }
                    }
                if(!contains) {
                    currentBasins.add(new Coordinate(i, q+1));
                    checkBasins(Integer.parseInt(String.valueOf(nextL.charAt(i))), lines, i, lines.get(q+1));
                }
                }
            }

        boolean cont = false;
        for(Coordinate c : currentBasins)
            if(c.getX() == i && c.getY() == q)
                cont = true;

        if(!cont)
            currentBasins.add(new Coordinate(i, q));

        }
    }
