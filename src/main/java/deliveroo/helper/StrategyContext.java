package deliveroo.helper;

import deliveroo.helper.parserstrategy.CommaStrategy;
import deliveroo.helper.parserstrategy.ParseStrategy;
import deliveroo.helper.parserstrategy.RangeStrategy;
import deliveroo.helper.parserstrategy.SingleValueStrategy;
import deliveroo.helper.parserstrategy.StepStrategy;
import deliveroo.helper.parserstrategy.WildcardStrategy;

import java.util.List;
import java.util.StringJoiner;

public abstract class StrategyContext implements FieldParser, deliveroo.helper.parserstrategy.StrategyContext {
    private final int low;
    private final int high;
    private final String name;
    private static final int NAME_COL = 14;

    private final List<ParseStrategy> strategies;

    /** Default: uses the standard strategies */
    protected StrategyContext(int low, int high, String name) {
        this(low, high, name, List.of(
                new CommaStrategy(),
                new StepStrategy(),
                new RangeStrategy(),
                new WildcardStrategy(),
                new SingleValueStrategy()
        ));
    }

    /** Manually Inject Strategy */
    protected StrategyContext(int low, int high, String name, List<ParseStrategy> strategies) {
        this.low = low;
        this.high = high;
        this.name = name;
        this.strategies = strategies;
    }

    public int low() { return low; }
    public int high() { return high; }
    public String name() { return name; }

    @Override
    public String parse(String input) {
        return String.format("%-" + NAME_COL + "s%s", name, dispatch(input.strip()));
    }


    @Override
    public String dispatch(String input) {
        for (ParseStrategy s : strategies) {
            if (s.isApplicable(input)) {
                return s.parse(input, low, high, this);
            }
        }
        throw new IllegalArgumentException("No strategy for input: " + input);
    }

    @Override
    public void validate(String value) {
        try {
            int v = Integer.parseInt(value);
            if (v < low || v > high) {
                throw new IllegalArgumentException(value + " out of bounds for " + name +
                        " (expected " + low + "-" + high + ")");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(value + " is not a valid integer for " + name);
        }
    }

    @Override
    public String expandRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Invalid range " + start + "-" + end + " for " + parser.name());
        }
        StringJoiner j = new StringJoiner(" ");
        for (int i = start; i <= end; i++) j.add(String.valueOf(i));
        return j.toString();
    }
}
