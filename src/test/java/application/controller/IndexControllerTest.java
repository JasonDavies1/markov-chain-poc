package application.controller;

import application.model.InputModel;
import application.service.MarkovChainService;
import application.util.MarkovCollectionInterpreter;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

public class IndexControllerTest {

    private final MarkovChainService markovChainService = mock(MarkovChainService.class);
    private final MarkovCollectionInterpreter markovCollectionInterpreter =
            mock(MarkovCollectionInterpreter.class);
    private final RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

    private final InputModel inputModel = new InputModel();
    private final IndexController target = new IndexController(markovChainService,
            markovCollectionInterpreter);

    @Test
    public void canAccessLandingPage() {
        assertThat(target.get())
                .isEqualTo("index");
    }

    @Test
    public void postMappingRedirectsToIndexWithMessage() {
        inputModel.setTextInput("Test");
        final HashMap<String, Set<String>> nodeRelationshipMap = new HashMap<>();
        final HashMap<String, String> interpretedRelationships = new HashMap<>();
        given(markovChainService.addInput(any()))
                .willReturn(nodeRelationshipMap);
        given(markovCollectionInterpreter.interpretRelationships(nodeRelationshipMap))
                .willReturn(interpretedRelationships);

        final String result = target.post(redirectAttributes, inputModel);

        then(markovChainService)
                .should()
                .addInput(inputModel.getTextInput());
        then(markovCollectionInterpreter)
                .should()
                .interpretRelationships(nodeRelationshipMap);
        then(redirectAttributes)
                .should()
                .addFlashAttribute(eq("message"), eq("Redirection successful, message read: " + inputModel.getTextInput()));
        assertThat(result)
                .isEqualTo("redirect:/");
    }

}