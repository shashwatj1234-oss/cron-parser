package deliveroo.helper.parserstrategy;

public interface StrategyContext extends ValueValidator, Dispatcher {
    String expandRange(int start, int end);
    int low();
    int high();
    String name();
}