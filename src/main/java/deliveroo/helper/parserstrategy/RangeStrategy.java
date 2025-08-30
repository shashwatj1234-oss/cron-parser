package deliveroo.helper.parserstrategy;

import deliveroo.helper.BaseParser;

public class RangeStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains("-");
    }

    @Override
    public String parse(String input, int low, int high, BaseParser context) {
        String[] bounds = input.split("-", 2);
        context.validate(bounds[0]);
        context.validate(bounds[1]);

        int start = Integer.parseInt(bounds[0]);
        int end   = Integer.parseInt(bounds[1]);

        return context.expandRange(start, end);
    }
}
