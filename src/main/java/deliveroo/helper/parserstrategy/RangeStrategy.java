package deliveroo.helper.parserstrategy;

import deliveroo.helper.MonthParser;

public class RangeStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains("-");
    }

    @Override
    public String parse(String input, int low, int high, StrategyContext context) {
        String[] bounds = input.split("-", 2);
        int start = context.convertToInt(bounds[0]);
        int end   =context.convertToInt(bounds[1]);


        context.validate(String.valueOf(start));
        context.validate(String.valueOf(end));


        return context.expandRange(start, end);
    }
}

//1,15/1
