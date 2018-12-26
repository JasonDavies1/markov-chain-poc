package application.util;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkovCollectionInterpreterTest {

    private Map<String, List<String>> entries = new HashMap<>();
    private MarkovCollectionInterpreter target = new MarkovCollectionInterpreter();

    @Test
    public void canInterpretOneKeyWithOneValue() {
        entries.put("Apple", Collections.singletonList("Banana"));

        final Map<String, String> result = target.interpretRelationships(entries);

        assertThat(result.entrySet())
                .hasOnlyOneElementSatisfying(e -> {
                    assertThat(e.getKey())
                            .isEqualTo("Apple");
                    assertThat(e.getValue())
                            .isEqualTo("Banana");
                });
    }

    @Test
    public void canInterpretOneKeyWithTwoValues() {
        entries.put("Apple", listOf("Banana", "Cabbage"));

        final Map<String, String> result = target.interpretRelationships(entries);

        assertThat(result.entrySet())
                .hasOnlyOneElementSatisfying(e -> {
                    assertThat(e.getKey())
                            .isEqualTo("Apple");
                    assertThat(e.getValue())
                            .isEqualTo("Cabbage, Banana");
                });
    }

    @Test
    public void canInterpretOneKeyWithThreeValues() {
        entries.put("Apple", listOf("Banana", "Cabbage", "Duck"));

        final Map<String, String> result = target.interpretRelationships(entries);

        assertThat(result.entrySet())
                .hasOnlyOneElementSatisfying(e -> {
                    assertThat(e.getKey())
                            .isEqualTo("Apple");
                    assertThat(e.getValue())
                            .isEqualTo("Duck, Cabbage, Banana");
                });
    }

    private List<String> listOf(String... values) {
        return Arrays.stream(values)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}