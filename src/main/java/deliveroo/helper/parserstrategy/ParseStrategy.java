package deliveroo.helper.parserstrategy;

import deliveroo.helper.BaseParser;

public interface ParseStrategy {
    boolean isApplicable(String input);
    String parse(String input, int low, int high, BaseParser context);
}
