package application.controller;

import application.model.InputModel;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class IndexControllerTest {

    private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

    private final InputModel inputModel = new InputModel();
    private final IndexController target = new IndexController();

    @Test
    public void canAccessLandingPage() {
        assertThat(target.get())
                .isEqualTo("index");
    }

    @Test
    public void postMappingRedirectsToIndexWithMessage() {
        inputModel.setTextInput("Test");
        final String result = target.post(redirectAttributes, inputModel);

        then(redirectAttributes)
                .should()
                .addFlashAttribute(eq("message"), eq("Redirection successful, message read: " + inputModel.getTextInput()));
        assertThat(result)
                .isEqualTo("redirect:/");
    }

}