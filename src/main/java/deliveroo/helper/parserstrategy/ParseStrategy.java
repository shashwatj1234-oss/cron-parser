package deliveroo.helper.parserstrategy;

public interface ParseStrategy {
    boolean isApplicable(String input);
    String parse(String input, int low, int high, StrategyContext context);
}
