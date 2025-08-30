package deliveroo.helper;

public class DayParser extends StrategyContext {
    public DayParser() { super(1, 31, "day of month"); }
    // For leap-year/real-month validation, see "Leap year extension" section below.
}
