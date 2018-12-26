package application.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionInstantiator {

    public static List<String> immutableListOf(final String[] values){
        return Stream.of(values).collect(Collectors.toList());
    }

    public static Set<String> immutableSetOf(final String firstValue) {
        return Stream.of(firstValue).collect(Collectors.toSet());
    }

}
