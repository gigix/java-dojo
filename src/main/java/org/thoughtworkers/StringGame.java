package org.thoughtworkers;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import java.util.List;

import static com.google.common.collect.Iterables.transform;
import static java.util.Arrays.asList;

public class StringGame {
    private List<String> strings;

    public StringGame(String... strings) {
        this.strings = asList(strings);
    }

    public String output() {
        String longest = longest(strings);
        String horizon = getHorizon(longest);
        return "" +
                " " + horizon + " \n" +
                innerLines(strings, longest.length()) +
                " " + horizon + " \n";
    }

    private String innerLines(List<String> strings, int width) {
        String result = "";
        for (String line : transform(strings, format(width))) {
            result += line;
        }
        return result;
    }

    private Function<String, String> format(final int width) {
        return new Function<String, String>() {
            @Override
            public String apply(String from) {
                return String.format("|%s%s|\n", from, getPadding(from, width));
            }
        };
    }

    private String getPadding(String string, int width) {
        String result = "";
        for (int i = 0; i < width - string.length(); i++) {
            result += " ";
        }
        return result;
    }

    private String longest(List<String> strings) {
        List<String> sortedString = Ordering.natural().onResultOf(minusLength()).sortedCopy(strings);
        return sortedString.get(0);
    }

    private Function<String, Integer> minusLength() {
        return new Function<String, Integer>() {
            @Override
            public Integer apply(String from) {
                return 0 - from.length();
            }
        };
    }

    private String getHorizon(String string) {
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            result += "-";
        }
        return result;
    }

}
