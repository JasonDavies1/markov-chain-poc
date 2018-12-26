package application.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MarkovChainServiceImplTest {

    private final MarkovChainServiceImpl target = new MarkovChainServiceImpl();

    @Test
    public void supplyingNullInputAsParameterIsNotAccepted() {
        assertThatCode(() -> target.addInput(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void canInitialiseWithASingleSentence_AndNoRepeatingWords() {
        final HashMap<String, Set<String>> result = target.addInput("This is a new sentence.");

        thenKeySetShouldContainNumberOfValues(result, 5);
    }

    @Test
    public void canInitialiseWithTwoSentences_AndNoRepeatingWords() {
        final HashMap<String, Set<String>> result =
                target.addInput("This is the first sentence. Bananas are cool.");

        thenKeySetShouldContainNumberOfValues(result, 8);
    }

    @Test
    public void canInitialiseWithOneSentence_WithARepeatingWord() {
        final HashMap<String, Set<String>> result =
                target.addInput("This is my cool sentence with lots and lots of repetition.");

        thenKeySetShouldContainNumberOfValues(result, 10);
    }

    @Test
    public void canInitialiseWithTwoSentences_WithARepeatingWord() {
        final HashMap<String, Set<String>> result =
                target.addInput("This is one sentence. This is another sentence.");

        thenKeySetShouldContainNumberOfValues(result, 5);
    }

    @Test
    public void canAddTwoUniqueInputs() {
        target.addInput("One.");
        HashMap<String, Set<String>> result = target.addInput("Two.");

        thenKeySetShouldContainNumberOfValues(result, 2);
    }

    @Test
    public void probabilityIsZeroWhenPhraseContainsUnknownTerminator() {
        target.addInput("Fried Cheese.");

        final String result = target.determineProbability("Apricot.");

        thenProbabilityShouldBeZero(result);
    }

    @Test
    public void probabilityIsZeroWhenPhraseContainsUnknownWord() {
        target.addInput("Hey that's pretty good.");

        final String result = target.determineProbability("Hey that's pretty dang good.");

        thenProbabilityShouldBeZero(result);
    }

    @Test
    public void inputCanAffectProbability_OneHundredPercentChance() {
        target.addInput("Fried Cheese.");

        final String result = target.determineProbability("Cheese.");

        thenProbabilityShouldBe(result, "1.0");
    }

    @Test
    public void multipleInputsCanAffectProbability_FiftyPercentChance() {
        target.addInput("I said hey.");
        target.addInput("I said hello.");
        target.addInput("I shouted hi.");

        final String result = target.determineProbability("I shouted hi.");

        thenProbabilityShouldBe(result, "0.5");
    }

    @Test
    public void multipleInputsCanAffectProbability_TwentyFivePercentChance() {
        target.addInput("I said hey.");
        target.addInput("I said hello.");
        target.addInput("I shouted hi.");

        final String result = target.determineProbability("I said hello.");

        thenProbabilityShouldBe(result, "0.25");
    }

    private void thenProbabilityShouldBeZero(
            final String result) {
        assertThat(result)
                .isEqualTo("Probability: 0.0");
    }

    private void thenProbabilityShouldBe(
            final String result,
            final String probability) {
        assertThat(result)
                .isEqualTo("Probability: " + probability);
    }

    private void thenKeySetShouldContainNumberOfValues(
            final HashMap<String, Set<String>> result,
            final int i) {
        assertThat(result.keySet())
                .hasSize(i);
    }

}