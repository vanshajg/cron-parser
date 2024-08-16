package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HourParserTest {

    private final HourParser hourParser = new HourParser();

    @Test
    void parse() {
        assertEquals("*", hourParser.parse("*"));
        assertEquals("0 6 12 18", hourParser.parse("*/6"));
        assertEquals("0 1 2 3 4", hourParser.parse("0-4"));
        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14", hourParser.parse("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14"));
    }

}