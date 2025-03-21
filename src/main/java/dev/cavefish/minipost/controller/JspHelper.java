package dev.cavefish.minipost.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JspHelper {
    public static <T> String MapAndConcat(List<T> values, Function<T, String> mapper) {
        return values.stream().map(mapper).collect(Collectors.joining(", "));
    }
}
