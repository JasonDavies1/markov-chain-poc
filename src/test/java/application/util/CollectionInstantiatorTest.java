package application.util;

import org.junit.Test;

import java.util.List;

import static application.util.CollectionInstantiator.immutableListOf;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionInstantiatorTest {

    @Test
    public void canCreateAListFromSingleString() {
        final List<String> result = immutableListOf("Hey");

        assertThat(result)
                .hasOnlyOneElementSatisfying(string -> assertThat(string)
                        .isEqualTo("Hey"));
    }

    @Test
    public void canCreateAListFromMultipleStringValues() {
        final List<String> result = immutableListOf(new String[]{"Hey"});

        assertThat(result)
                .hasOnlyOneElementSatisfying(string -> assertThat(string)
                        .isEqualTo("Hey"));
    }

}