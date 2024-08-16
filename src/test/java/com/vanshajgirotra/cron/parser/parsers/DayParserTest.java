package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayParserTest {

    private final DayParser dayParser = new DayParser();

    @Test
    void parse() {
        assertEquals("*", dayParser.parse("*"));
        assertEquals("1 11 21 31", dayParser.parse("*/10"));
        assertEquals("1 2 3 4 5", dayParser.parse("1-5"));
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", dayParser.parse("1,2,3,4,5,6,7,8,9,10,11,12"));
    }

}