package deliveroo.helper;

public class CommandParser implements FieldParser {
    @Override
    public String parse(String input) {
        return String.format("%-14s%s", "command", input);
    }
}
