package deliveroo.helper.parserstrategy;

import deliveroo.helper.BaseParser;
import java.util.StringJoiner;

public class RangeStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains("-");
    }

    @Override
    public String parse(String input, int low, int high, BaseParser parser) {
        String[] bounds = input.split("-", 2);
        parser.validate(bounds[0]);
        parser.validate(bounds[1]);

        int start = Integer.parseInt(bounds[0]);
        int end   = Integer.parseInt(bounds[1]);

        return expandRange(start, end, parser);
    }

    private String expandRange(int start, int end, BaseParser parser) {
        if (start > end) {
            throw new IllegalArgumentException("Invalid range " + start + "-" + end + " for " + parser.name());
        }
        StringJoiner j = new StringJoiner(" ");
        for (int i = start; i <= end; i++) j.add(String.valueOf(i));
        return j.toString();
    }
}
