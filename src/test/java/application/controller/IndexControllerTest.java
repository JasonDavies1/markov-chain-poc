package application.controller;

import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class IndexControllerTest {

    private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
    private final IndexController target = new IndexController();

    @Test
    public void canAccessLandingPage() {
        assertThat(target.get())
                .isEqualTo("index");
    }

    @Test
    public void postMappingRedirectsToIndexWithMessage() {
        final String result = target.post(redirectAttributes);

        then(redirectAttributes)
                .should()
                .addFlashAttribute(any(), any());
        assertThat(result)
                .isEqualTo("redirect:/");
    }

}