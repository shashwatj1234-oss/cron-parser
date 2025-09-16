package deliveroo;

import deliveroo.helper.CommandParser;
import deliveroo.helper.DayParser;
import deliveroo.helper.FieldParser;
import deliveroo.helper.HourParser;
import deliveroo.helper.MinuteParser;
import deliveroo.helper.MonthParser;
import deliveroo.helper.WeekParser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Parser {
    private final List<FieldParser> parsers;

    public Parser() {
        parsers = new ArrayList<>();
        parsers.add(new MinuteParser());
        parsers.add(new HourParser());
        parsers.add(new DayParser());
        parsers.add(new MonthParser());
        parsers.add(new WeekParser());
        parsers.add(new CommandParser());
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Insufficient argument length.");
        }
        Parser parser = new Parser();
        String output = parser.parse(args[0]);
        System.out.println(output);
    }

    public String parse(String input) {
        input = input.strip();
        String[] params = input.split("\\s+");
        if (params.length != parsers.size()) {
            throw new IllegalArgumentException("Incorrect cron expression length, expected "
                    + parsers.size() + " fields but got " + params.length);
        }

        StringJoiner out = new StringJoiner("\n");
        for (int i = 0; i < parsers.size(); i++) {
            out.add(parsers.get(i).parse(params[i]));
        }
        return out.toString();
    }
}

///*
////* input: "*/15 0 1,15 JAN-JUN 1-3 /usr/bin/find"
////        ```
////
////Will produce output like:
////
////        ```bash
////minute        0 15 30 45
////hour          0
////day of month  1 15
////month         1 2 3 4 5 6
////day of week   1 2 3
////command       /usr/bin/find
//* */
