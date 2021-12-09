package Day8;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Day8Part2 {
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

        int sum = 0;

        for(String in : input) {

            ArrayList<String> fives = new ArrayList<>();
            ArrayList<String> sixes = new ArrayList<>();
            String zero = null;
            String one = null;
            String two = null;
            String three = null;
            String four = null;
            String five = null;
            String six = null;
            String seven = null;
            String eight = null;
            String nine = null;

            String[] nums = in.split("\\s");

            for (String num : nums) {
                switch (num.length()) {
                    case (2):
                        one = num;
                        break;

                    case (3):
                        seven = num;
                        break;

                    case (4):
                        four = num;
                        break;

                    case (5):
                        fives.add(num);
                        break;

                    case (6):
                        sixes.add(num);
                        break;

                    case (7):
                        eight = num;
                        break;

                }
            }

            for (String fiv : fives) {
                String temp = fiv;
                for (int j = 0; j < seven.length(); j++) {
                    fiv = fiv.replace(String.valueOf(seven.charAt(j)), "");
                }
                if(fiv.length() == 2 && !(fiv.contains(String.valueOf(one.charAt(0))) && fiv.contains(String.valueOf(one.charAt(1))))){
                    three = temp;
                }

                else if(fiv.length() == 3){
                    int cnt = 0;
                    for(int j = 0; j < four.length(); j++){
                        if(fiv.contains(String.valueOf(four.charAt(j))))
                            cnt++;
                    }
                    if(cnt == 2) {
                        five = temp;
                    }
                    else {
                        two = temp;
                    }

                }


            }


            for(String si : sixes){
                String temp = si;
                String buf = eight;
                for(int j = 0; j < si.length(); j++) {
                    buf = buf.replace(String.valueOf(si.charAt(j)), "");
                }
                if(buf.length() == 1 && (buf.contains(String.valueOf(one.charAt(0))) ^ buf.contains(String.valueOf(one.charAt(1))))) {
                    six = temp;
                }
                else if(buf.length() == 1) {
                    int cnt = 0;
                    for(int j = 0; j < four.length(); j++){
                        if(buf.contains(String.valueOf(four.charAt(j))))
                            cnt++;
                    }
                    if(cnt > 0) {
                        zero = temp;
                    }
                    else {
                        nine = temp;
                    }
                }
            }

            String value = "";

            String out = output.get(input.indexOf(in));
            String[] oNums = out.split("\\s");
            for (String oNum : oNums) {
                if (isMadeOfChars(oNum, zero))
                    value = value + 0;

                else if (isMadeOfChars(oNum, one))
                    value = value + 1;

                else if (isMadeOfChars(oNum, two))
                    value = value + 2;

                else if (isMadeOfChars(oNum, three))
                    value = value + 3;

                else if (isMadeOfChars(oNum, four))
                    value = value + 4;

                else if (isMadeOfChars(oNum, five))
                    value = value + 5;

                else if (isMadeOfChars(oNum, six))
                    value = value + 6;

                else if (isMadeOfChars(oNum, seven))
                    value = value + 7;

                else if (isMadeOfChars(oNum, eight))
                    value = value + 8;

                else if (isMadeOfChars(oNum, nine))
                    value = value + 9;
            }

            if(!value.isEmpty())
                sum = sum + Integer.parseInt(value);
        }
        System.out.println(sum);
    }

    public static boolean isMadeOfChars(String s, String ing){
        if(ing == null || s.length() != ing.length())
            return false;

        int ctr = 0;
        for(int i = 0; i < ing.length(); i++){
            if(!s.contains(String.valueOf(ing.charAt(i))))
                return false;
        }
        return true;
    }
}
