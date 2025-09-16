package deliveroo.helper;


import java.util.HashMap;
import java.util.Map;

public class MonthParser extends BaseParser {

     private final Map<String, Integer> monthToInt;

    public MonthParser() {
        super(1, 12, "month");
        Map<String, Integer> monthToInt = new HashMap<>();
        monthToInt.put("JAN", 1);
        monthToInt.put("FEB", 2);
        monthToInt.put("MAR", 3);
        monthToInt.put("APR", 4);
        monthToInt.put("MAY", 5);
        monthToInt.put("JUN", 6);
        monthToInt.put("JUL", 7);
        monthToInt.put("AUG", 8);
        monthToInt.put("SEP", 9);
        monthToInt.put("OCT", 10);
        monthToInt.put("NOV", 11);
        monthToInt.put("DEC", 12);
        this.monthToInt = monthToInt;
    }

     @Override
     public String parse(String input) {
//        String updatedInput = convertToIntString(input);
         return String.format("%-" + NAME_COL_LENGTH + "s%s", this.name(), dispatch(input.strip()));
     }

    @Override
    public Integer convertToInt(String str) {
        if (monthToInt.containsKey(str)) {
            return monthToInt.get(str);
        }
        return Integer.parseInt(str);
    }

//    private String convertToIntString(String str) {
//        // JAN-MAR
//        //Stack --
//        //String resultStr = "";
//    }


 }

 // , *, -
