package application.util;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static application.util.CollectionInstantiator.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionInstantiatorTest {

    @Test
    public void canCreateASet() {
        final Set<String> result = immutableSetOf("Hey");

        assertThat(result)
                .hasOnlyOneElementSatisfying(string -> assertThat(string)
                        .isEqualTo("Hey"));
    }

    @Test
    public void canCreateAList() {
        final List<String> result = immutableListOf(new String[]{"Hey"});

        assertThat(result)
                .hasOnlyOneElementSatisfying(string -> assertThat(string)
                        .isEqualTo("Hey"));
    }

}