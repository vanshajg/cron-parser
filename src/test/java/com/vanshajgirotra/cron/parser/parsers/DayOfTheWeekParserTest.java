package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayOfTheWeekParserTest {

    private final DayOfTheWeekParser dayOfTheWeekParser = new DayOfTheWeekParser();

    @Test
    void parse() {
        assertEquals("*", dayOfTheWeekParser.parse("*"));
        assertEquals("1 4 7", dayOfTheWeekParser.parse("1,4,7"));
        assertEquals("1 2 3 4 5", dayOfTheWeekParser.parse("1-5"));
        assertEquals("1 2 3 4 5 6 7", dayOfTheWeekParser.parse("1,2,3,4,5,6,7"));
    }

}