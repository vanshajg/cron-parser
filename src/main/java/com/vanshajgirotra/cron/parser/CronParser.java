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
    record ParsedData(String parserName, String parsedValue) {
    }

    public static void main(String[] args) {
        final CronParser parser = new CronParser();
        System.out.println(Arrays.toString(args));
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        System.out.println("Input: " + args[0]);
        List<ParsedData> parsedData = parser.parse(args[0]);
        System.out.println(prettyFormat(parsedData));
    }

    List<ParsedData> parse(final String input) {
        final List<String> inputParts = Arrays.stream(input.split(" "))
                .filter((token) -> !token.isBlank())
                .toList();

        if (inputParts.size() < MIN_INPUT_LENGTH) {
            throw new IllegalArgumentException("Invalid input for cron expression");
        }

        final List<String> params = new ArrayList<>(List.copyOf(inputParts.subList(0, MIN_INPUT_LENGTH - 1)));
        params.add(String.join(" ", inputParts.subList(MIN_INPUT_LENGTH - 1, inputParts.size())));

        return IntStream
                .rangeClosed(0, 5)
                .boxed()
                .map(idx -> ParsedData.builder()
                        .parserName(parsers.get(idx).getName())
                        .parsedValue(parsers.get(idx)
                                .parse(params.get(idx))).build())
                .toList();

    }

    static String prettyFormat(final List<ParsedData> parsedData) {
        return parsedData.stream()
                .map(data -> String.format("%-" + COLUMN_WIDTH + "s %s", data.parserName, data.parsedValue))
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("");
    }

}
