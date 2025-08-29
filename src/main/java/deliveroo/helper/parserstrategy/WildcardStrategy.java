package deliveroo.helper.parserstrategy;

import deliveroo.helper.BaseParser;

import java.util.StringJoiner;

public class WildcardStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.equals("*");
    }

    @Override
    public String parse(String input, int low, int high, BaseParser parser) {
        StringJoiner j = new StringJoiner(" ");
        for (int i = low; i <= high; i++) j.add(String.valueOf(i));
        return j.toString();
    }
}
