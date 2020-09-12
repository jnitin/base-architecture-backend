package com.backend.api.utils;

import java.util.Comparator;
import java.util.function.Function;

public class Basics {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <TYPE> Comparator<TYPE> sort(Function<TYPE, ? extends Comparable> getterFunction, boolean descending) {
        if (descending) {
            return (o1, o2) -> getterFunction.apply(o2).compareTo(getterFunction.apply(o1));
        }
        return (o1, o2) -> getterFunction.apply(o1).compareTo(getterFunction.apply(o2));
    }
}
