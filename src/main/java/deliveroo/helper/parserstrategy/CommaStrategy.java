package deliveroo.helper.parserstrategy;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CommaStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains(",");
    }

    @Override
    public String parse(String input, int low, int high, StrategyContext context) {
        return Arrays.stream(input.split(","))
                .map(s -> context.dispatch(s.trim()))
                .collect(Collectors.joining(" "));
    }
}
