package deliveroo.helper.parserstrategy;

import java.util.StringJoiner;

public class StepStrategy implements ParseStrategy {
    @Override
    public boolean isApplicable(String input) {
        return input.contains("/");
    }

    @Override
    public String parse(String input, int low, int high, StrategyContext context) {
        String[] parts = input.split("/", 2);
        String left = parts[0];
        int step = parsePositiveInt(parts[1]);

        int start = low;
        int end = high;

        if (!left.equals("*")) {
            if (left.contains("-")) {
                String[] bounds = left.split("-", 2);
                context.validate(bounds[0]);
                context.validate(bounds[1]);
                start = Integer.parseInt(bounds[0]);
                end   = Integer.parseInt(bounds[1]);
                if (start > end) {
                    throw new IllegalArgumentException("Invalid ranged step: " + left + "/" + step + " for " + context.name());
                }
            } else {
                context.validate(left);
                start = Integer.parseInt(left);
            }
        }

        StringJoiner j = new StringJoiner(" ");
        for (int i = start; i <= end; i += step) j.add(String.valueOf(i));
        return j.toString();
    }

    private int parsePositiveInt(String s) {
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException("step" + " must be > 0");
        return v;
    }
}
