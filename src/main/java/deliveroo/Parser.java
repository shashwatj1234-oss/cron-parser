package deliveroo;

import deliveroo.helper.*;

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
