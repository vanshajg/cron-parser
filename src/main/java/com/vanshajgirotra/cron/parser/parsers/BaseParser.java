package com.vanshajgirotra.cron.parser.parsers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class BaseParser implements Parser {

    private final int lower_bound;
    private final int upper_bound;
    @Getter
    private final String name;

    public String parse(final String input) {
        return switch (input) {
            case "*" -> "*";
            case String s when s.contains("/") -> parseWithSlash(input);
            case String s when s.contains("-") -> parseWithDash(input);
            case String s when s.contains(",") -> parseWithComma(input);
            default -> parseSingleValue(input);
        };
    }

    private String parseWithSlash(final String input) {
        String[] parts = input.split("/");
        final int step = Integer.parseInt(parts[1]);
        StringBuilder result = new StringBuilder();
        for (int i = lower_bound; i <= upper_bound; i += step) {
            result.append(i).append(" ");
        }
        return result.toString().trim();
    }

    private String parseWithDash(final String input) {
        String[] parts = input.split("-");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        if (start < lower_bound || end > upper_bound) {
            throw new IllegalArgumentException("Invalid input for " + name);
        }
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            result.append(i).append(" ");
        }
        return result.toString().trim();
    }

    private String parseWithComma(final String input) {
        String[] parts = input.split(",");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(parseSingleValue(part)).append(" ");
        }
        return result.toString().trim();
    }

    private String parseSingleValue(final String input) {
        int value = Integer.parseInt(input);
        if (value < lower_bound || value > upper_bound) {
            throw new IllegalArgumentException("Invalid input for " + name);
        }
        return String.valueOf(value);
    }

}
