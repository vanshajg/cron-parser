package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {

    private final CommandParser commandParser = new CommandParser();

    @Test
    void parse() {
        assertEquals("echo", commandParser.parse("echo"));
    }

}