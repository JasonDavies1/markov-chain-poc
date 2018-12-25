package application.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MarkovCollectionInterpreter {

    public Map<String, String> interpretRelationships(final Map<String, Set<String>> nodeEntries) {
        final Map<String, String> relationships = new HashMap<>();
        nodeEntries.forEach((key, value) -> relationships.put(key, elementsAsCommaSeparatedList(value)));
        return relationships;
    }

    //Perhaps implement a LinkedHashSet to preserve order.
    private String elementsAsCommaSeparatedList(final Set<String> values) {
        return String.join(", ", values);

    }

}
