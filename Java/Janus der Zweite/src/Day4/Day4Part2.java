package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4Part2 {
    public static void main(String[] args) {
        Path path = Paths.get("src\\Day4\\input.txt");
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] numbers = lines.get(0).split(",");
        ArrayList<String[][]> boards = new ArrayList<>();
        String[][] newBoard = new String[5][5];
        int counter = 0;

        for (int i = 2; i < lines.size(); i++) {
            if (!lines.get(i).isBlank()) {
                String newLine = lines.get(i);
                if (String.valueOf(lines.get(i).charAt(0)).equals(" ")) {
                    newLine = lines.get(i).replaceFirst("\\s+", "");
                }
                newBoard[counter] = newLine.split("\\s+");
                counter++;
            } else {
                boards.add(newBoard);
                newBoard = new String[5][5];
                counter = 0;
            }
        }

        String currentNumber = null;
        int x = 0;
        int y = 0;
        String[][] lastBoard = new String[5][5];
        ArrayList<String[][]> boardCache = new ArrayList<>();
        int index = 0;

        for (String number : numbers) { //For every number
            for (String[][] board : boards) {   //Check for every board
                for (int i = 0; i < board.length; i++) {    //check every row-coordinate
                    for (int j = 0; j < board.length; j++) {    //check every column-coordinate
                        if(board[i][j].equals(number)){
                            board[i][j] = "X";
                            if(isBingo(board)){
                                currentNumber = number;
                                x = i;
                                y = j;
                                index = boards.indexOf(board);
                                lastBoard = board;
                                boardCache.add(board);
                            }
                        }
                    }
                }

            }
            boards.removeAll(boardCache);
        }

        System.out.println(Integer.parseInt(currentNumber));

        int sum = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(!lastBoard[i][j].equals("X")){
                    sum = sum + Integer.parseInt(lastBoard[i][j]);
                }
            }
        }
        System.out.println(sum * Integer.parseInt(currentNumber));
    }


    public static boolean isBingo(String[][] board){
        int counter = 0;
        for(int i = 0; i < board.length; i++){
            String line = Arrays.toString(board[i]);
            line = line.replaceAll("\\[", "");
            line = line.replaceAll("\\]", "");
            line = line.replaceAll("\\, ", "");
            if(line.equals("XXXXX")){
                return true;
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[j][i] == "X"){
                    counter++;
                }
            }
            if(counter == 5){
                return true;
            }
            else
                counter = 0;
        }
        return false;
    }
}
