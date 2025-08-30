package deliveroo.helper.parserstrategy;

import deliveroo.helper.StrategyContext;

public class SingleValueStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return true; // fallback
    }

    @Override
    public String parse(String input, int low, int high, StrategyContext context) {
        context.validate(input);
        return input;
    }
}
