package com.vanshajgirotra.cron.parser;

import com.vanshajgirotra.cron.parser.parsers.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CronParser {

    private static final int MIN_INPUT_LENGTH = 6;
    private static final int COLUMN_WIDTH = 15;
    private static final List<Parser> parsers = List.of(new MinuteParser(),
            new HourParser(),
            new DayParser(),
            new MonthParser(),
            new DayOfTheWeekParser(),
            new CommandParser());

    @Builder
    private record ParsedData(String parserName, String parsedValue) {
    }

    public static void main(String[] args) {
        final CronParser parser = new CronParser();
        parser.parse("*/15 0 1,15 * 1-5 /usr/bin/find hello world");
    }

    private void parse(final String input) {
        final List<String> inputParts = Arrays.stream(input.split(" ")).toList();

        if (inputParts.size() < MIN_INPUT_LENGTH) {
            throw new IllegalArgumentException("Invalid input for cron expression");
        }

        final List<String> params = new ArrayList<>(List.copyOf(inputParts.subList(0, MIN_INPUT_LENGTH - 1)));
        params.add(String.join(" ", inputParts.subList(MIN_INPUT_LENGTH - 1, inputParts.size())));

        final List<ParsedData> parsedData = IntStream
                .rangeClosed(0, 5)
                .boxed()
                .map(idx -> ParsedData.builder()
                        .parserName(parsers.get(idx).getName())
                        .parsedValue(parsers.get(idx)
                                .parse(params.get(idx))).build())
                .toList();

        prettyFormat(parsedData);
    }

    private static void prettyFormat(final List<ParsedData> parsedData) {
        parsedData.forEach(data -> {
            System.out.printf("%-" + COLUMN_WIDTH + "s", data.parserName);
            System.out.println(data.parsedValue);
        });
    }

}
