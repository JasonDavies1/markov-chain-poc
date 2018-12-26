package application.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MarkovCollectionInterpreter {

    public Map<String, String> interpretRelationships(final Map<String, List<String>> nodeEntries) {
        final Map<String, String> relationships = new HashMap<>();
        nodeEntries.forEach((key, value) -> relationships.put(key, elementsAsCommaSeparatedList(value)));
        return relationships;
    }

    //Perhaps implement a LinkedHashSet to preserve order.
    private String elementsAsCommaSeparatedList(final List<String> values) {
        return String.join(", ", values);

    }

}
