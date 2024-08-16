package com.vanshajgirotra.cron.parser.parsers;

public class CommandParser extends BaseParser {

    public CommandParser() {
        super(0, 0, "command");
    }

    @Override
    public String parse(String input) {
        return input;
    }

}
