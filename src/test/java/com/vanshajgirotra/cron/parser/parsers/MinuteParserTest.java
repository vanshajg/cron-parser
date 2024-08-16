package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinuteParserTest {

    private final MinuteParser minuteParser = new MinuteParser();

    @Test
    void parse() {
        assertEquals("*", minuteParser.parse("*"));
        assertEquals("0 15 30 45", minuteParser.parse("*/15"));
        assertEquals("0 1 2 3 4", minuteParser.parse("0-4"));
        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14", minuteParser.parse("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14"));
    }

}