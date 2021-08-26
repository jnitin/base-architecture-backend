package com.backend.api.utils;

import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.pagination.Filter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Comparator;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Basics {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <TYPE> Comparator<TYPE> sort(Function<TYPE, ? extends Comparable> getterFunction, boolean descending) {
        if (descending) {
            return (o1, o2) -> getterFunction.apply(o2).compareTo(getterFunction.apply(o1));
        }
        return (o1, o2) -> getterFunction.apply(o1).compareTo(getterFunction.apply(o2));
    }

    public static Specification<?> generateSpecification(Filter filter, Company company, User user) {
        CrudSpecificationBuilder<?> builder = new CrudSpecificationBuilder<>(company, user);
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=|%)(\\w+?)(,|\\|)");
        Matcher matcher = pattern.matcher(filter.getSearch() + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        return builder.build();
    }
}
