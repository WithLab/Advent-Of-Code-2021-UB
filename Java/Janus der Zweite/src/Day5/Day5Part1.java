package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5Part1 {
    public static void main(String[] args) {
        Path path = Paths.get("src\\Day5\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> starts = new ArrayList<>();
        ArrayList<String> ends = new ArrayList<>();

        for(String l : lines){
            String[] coor = l.split(" -> ");
            starts.add(coor[0]);
            ends.add(coor[1]);
        }

        ArrayList<Coordinate> visitedFields = new ArrayList<>();

        for(int i = 0; i < starts.size(); i++){
            String[] startCoor = starts.get(i).split(",");
            int x1 = Integer.parseInt(startCoor[0]);
            int y1 = Integer.parseInt(startCoor[1]);

            String[] endCoor = ends.get(i).split(",");
            int x2 = Integer.parseInt(endCoor[0]);
            int y2 = Integer.parseInt(endCoor[1]);

            if(x1 == x2 || y1 == y2){


                if(x1 == x2){
                    if(y1 >= y2) {
                        for (int y = y1; y >= y2; y--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x1 && c.y == y) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if(toAdd)
                                visitedFields.add(new Coordinate(x1, y));
                        }
                    }
                    else if(y1 <= y2){
                        for (int y = y2; y >= y1; y--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x1 && c.y == y) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x1, y));
                        }
                    }
                }


                else if(y1 == y2){
                    if(x1 >= x2) {
                        for (int x = x1; x >= x2; x--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.y == y1 && c.x == x) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if(toAdd)
                                visitedFields.add(new Coordinate(x, y1));
                        }
                    }
                    else if(x1 <= x2){
                        for (int x = x2; x >= x1; x--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.y == y1 && c.x == x) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if(toAdd)
                                visitedFields.add(new Coordinate(x, y1));
                        }
                    }
                }
            }
        }
        int maxAmount = 0;

        for(Coordinate coor : visitedFields){
            if(coor.visited >= 2){
                maxAmount++;
            }
        }


        System.out.println(maxAmount);
    }

}
