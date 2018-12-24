package application.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexControllerTest {

    private final IndexController target = new IndexController();

    @Test
    public void canAccessLandingPage(){
        assertThat(target.landingPage())
                .isEqualTo("index");
    }

}