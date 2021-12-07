package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day5Part2 {
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

        for(int i = 0; i < starts.size(); i++) {
            String[] startCoor = starts.get(i).split(",");
            int x1 = Integer.parseInt(startCoor[0]);
            int y1 = Integer.parseInt(startCoor[1]);

            String[] endCoor = ends.get(i).split(",");
            int x2 = Integer.parseInt(endCoor[0]);
            int y2 = Integer.parseInt(endCoor[1]);


                if (x1 == x2) {
                    if (y1 >= y2) {
                        for (int y = y1; y >= y2; y--) {
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
                    } else if (y1 < y2) {
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
                else if (y1 == y2) {
                    if (x1 >= x2) {
                        for (int x = x1; x >= x2; x--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.y == y1 && c.x == x) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y1));
                        }
                    } else if (x1 < x2) {
                        for (int x = x2; x >= x1; x--) {
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.y == y1 && c.x == x) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y1));
                        }
                    }
                }
                /*else if (x1 == y1 && x2 == y2) {
                    if (x1 >= x2) {
                        int y = y1 + 1;
                        for (int x = x1; x >= x2; x--) {
                            y--;
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x && c.y == y) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y));
                        }
                    } else {
                        int y = y2 + 1;
                        for (int x = x2; x >= x1; x--) {
                            y--;
                            boolean toAdd = true;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x && c.y == y) {
                                    c.visited++;
                                    toAdd = false;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y));
                        }
                    }
                }*/
                /*else if (x1 == y2 && y1 == x2) {
                    if (x1 >= x2) {
                        int y = y1 - 1;
                        for (int x = x1; x >= x2; x--) {
                            boolean toAdd = true;
                            y++;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x && c.y == y) {
                                    toAdd = false;
                                    c.visited++;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y));
                        }
                    } else {
                        int y = y2 - 1;
                        for (int x = x2; x >= x1; x--) {
                            boolean toAdd = true;
                            y++;
                            for (Coordinate c : visitedFields) {
                                if (c.x == x && c.y == y) {
                                    toAdd = false;
                                    c.visited++;
                                    break;
                                }
                            }
                            if (toAdd)
                                visitedFields.add(new Coordinate(x, y));
                        }
                    }
                }*/
                else{
                    if(checkDiagonal(x1, y1, x2, y2)){
                        if(x1 > x2) {
                            if (y1 > y2) {
                                while (x1 >= x2 && y1 >= y2) {
                                    if (!isVisited(x1, y1, visitedFields))
                                        visitedFields.add(new Coordinate(x1, y1));
                                    if (x1 == x2 && y1 == y2) {
                                        break;
                                    }
                                    x1--;
                                    y1--;
                                }
                            }
                            else if(y1 < y2){
                                while (x1 >= x2 && y1 <= y2){
                                    if(!isVisited(x1, y1, visitedFields))
                                        visitedFields.add(new Coordinate(x1, y1));
                                    if(x1 == x2 && y1 == y2){
                                        break;
                                    }
                                    x1--;
                                    y1++;
                                }
                            }
                        }
                        else if(x1 < x2) {
                            if (y1 > y2) {
                                while (x1 <= x2 && y1 >= y2) {
                                    if (!isVisited(x1, y1, visitedFields))
                                        visitedFields.add(new Coordinate(x1, y1));
                                    if (x1 == x2 && y1 == y2) {
                                        break;
                                    }
                                    x1++;
                                    y1--;
                                }
                            } else if (y1 < y2) {
                                while (x1 <= x2 && y1 <= y2) {
                                    if (!isVisited(x1, y1, visitedFields))
                                        visitedFields.add(new Coordinate(x1, y1));
                                    if (x1 == x2 && y1 == y2) {
                                        break;
                                    }
                                    x1++;
                                    y1++;
                                }
                            }
                        }

                    }
                }
        }
        int maxAmount = 0;

        for(Coordinate coor : visitedFields){
            if(coor.visited >= 2){
                maxAmount++;
                System.out.println(coor.x + ", " + coor.y + ": " + coor.visited);
            }
        }

        System.out.println(maxAmount);
    }

    public static boolean checkDiagonal(int x1, int y1, int x2, int y2){
        if(x1 > x2){
            if(y1 > y2){
                while (x1 >= x2 && y1 >= y2){
                    if(x1 == x2 && y1 == y2)
                        return true;
                    x1--;
                    y1--;
                }
            }
            else if(y1 < y2){
                while (x1 >= x2 && y1 <= y2){
                    if (x1 == x2 && y1 == y2)
                        return true;
                    x1--;
                    y1++;
                }
            }
        }
        else if(x1 < x2){
            if(y1 > y2){
                while (x1 <= x2 && y1 >= y2){
                    if(x1 == x2 && y1 == y2)
                        return true;
                    x1++;
                    y1--;
                }
            }
            else if(y1 < y2) {
                while (x1 <= x2 && y1 <= y2) {
                    if (x1 == x2 && y1 == y2)
                        return true;
                    x1++;
                    y1++;
                }
            }
        }
        return false;
    }

    public static boolean isVisited(int x1, int y1, ArrayList<Coordinate> coorList){
        for(Coordinate c : coorList){
            if(c.x == x1 && c.y == y1){
                c.visited++;
                return true;
            }
        }
        return false;
    }

}
