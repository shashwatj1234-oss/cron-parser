package deliveroo.helper.parserstrategy;

import deliveroo.helper.BaseParser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CommaStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains(",");
    }

    @Override
    public String parse(String input, int low, int high, BaseParser parser) {
        return Arrays.stream(input.split(","))
                .map(s -> parser.dispatch(s.trim()))
                .collect(Collectors.joining(" "));
    }
}
