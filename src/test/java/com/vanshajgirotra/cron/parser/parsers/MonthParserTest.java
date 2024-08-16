package com.vanshajgirotra.cron.parser.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MonthParserTest {

    private final MonthParser monthParser = new MonthParser();

    @Test
    void parse() {
        assertEquals("*", monthParser.parse("*"));
        assertEquals("1 4 7 10", monthParser.parse("*/3"));
        assertEquals("1 2 3 4 5", monthParser.parse("1-5"));
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", monthParser.parse("1,2,3,4,5,6,7,8,9,10,11,12"));
    }

    @Test
    void parse_invalidInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> monthParser.parse("1,2,3,4,5,6,7,8,9,10,11,13"));
    }

    @Test
    void parse_invalidInput_throwsException2() {
        assertThrows(IllegalArgumentException.class, () -> monthParser.parse("1,2,3,4,5,6,7,8,9,10,11,0"));
    }

}