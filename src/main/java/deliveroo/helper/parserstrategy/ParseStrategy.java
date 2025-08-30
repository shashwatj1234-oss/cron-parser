package deliveroo.helper.parserstrategy;

import deliveroo.helper.StrategyContext;

public interface ParseStrategy {
    boolean isApplicable(String input);
    String parse(String input, int low, int high, StrategyContext context);
}
