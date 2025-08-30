package deliveroo.helper.parserstrategy;

import deliveroo.helper.StrategyContext;

import java.util.StringJoiner;

public class WildcardStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.equals("*");
    }

    @Override
    public String parse(String input, int low, int high, StrategyContext context) {
        StringJoiner j = new StringJoiner(" ");
        for (int i = low; i <= high; i++) j.add(String.valueOf(i));
        return j.toString();
    }
}
