package application.service;

import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static application.util.CollectionInstantiator.immutableListOf;
import static application.util.CollectionInstantiator.immutableSetOf;

@Service
public class MarkovChainServiceImpl implements MarkovChainService {

    private final HashMap<String, Set<String>> words = new HashMap<>();

    private boolean listDoesNotHaveFullStopAtTheEnd(final String input) {
        return !input.contains(".") || input.charAt(input.length() - 1) != '.';
    }

    @Override
    public HashMap<String, Set<String>> addInput(@NonNull @NotEmpty final String input) {
        if (listDoesNotHaveFullStopAtTheEnd(input)) {
            final String formattedInput = input.trim() + ".";
            initialiseInputValues(formattedInput);
        } else {
            initialiseInputValues(input);
        }
        return words;
    }

    @Override
    public void clearEntries() {
        words.clear();
    }

    @Override
    public String determineProbability(final String input) {
        final List<String> split = splitInput(input);

        final AtomicReference<Double> probability = new AtomicReference<>(1.00);

        if (listDoesNotHaveFullStopAtTheEnd(input)) {
            final String formattedInput = input.trim() + ".";
            return getProbabilityValue(split, probability, formattedInput);
        } else {
            return getProbabilityValue(split, probability, input);
        }
    }

    private String getProbabilityValue(
            final List<String> split,
            final AtomicReference<Double> probability,
            final String formattedInput) {
        final Optional<String> terminatorStringFound = getTerminatorString(formattedInput);
        if (terminatorStringFound.isPresent()) {
            for (int i = 0; i < split.size() - 1; i++) {
                final Optional<Set<String>> strings = Optional.ofNullable(words.get(split.get(i)));
                if (strings.isPresent()) {
                    probability.set(probability.get() * (1.00 / strings.get().size()));
                } else {
                    probability.set(0.00);
                    i = split.size();
                }
            }

            if (probability.get() > 0.00) {
                final Optional<Set<String>> terminatorValueOptional =
                        Optional.ofNullable(words.get(terminatorStringFound.get()));
                if (terminatorValueOptional.isPresent()) {
                    probability.set(probability.get() * (1.00 / terminatorValueOptional.get().size()));
                } else {
                    probability.set(0.00);
                }
            }
        } else {
            probability.set(0.00);
        }

        return "Probability: " + probability.get();
    }

    private Optional<String> getTerminatorString(final String formattedInput) {
        final List<String> strings = splitInput(formattedInput);
        final String terminatorString = strings.get(strings.size() - 1);
        final String formattedTerminator = terminatorString.substring(0, terminatorString.length() - 1);
        return findTerminatingValues().stream()
                .filter(s -> s.equals(formattedTerminator))
                .findFirst();
    }

    private Set<String> findTerminatingValues() {
        return words.entrySet()
                .stream()
                .filter(entry -> entry.getValue().stream().anyMatch(s -> s.equals(".")))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private void initialiseInputValues(final String input) {
        splitSentences(input).forEach(s -> {
            final List<String> spaceDelimited = splitInput(s);
            IntStream.range(0, spaceDelimited.size())
                    .forEachOrdered(i -> {
                        final String currentWord = spaceDelimited.get(i);
                        final Optional<Set<String>> strings = Optional.ofNullable(words.get(currentWord));
                        if (strings.isPresent()) {
                            final Set<String> set = strings.get();
                            if (i + 1 != spaceDelimited.size()) {
                                set.add(spaceDelimited.get(i + 1));
                            } else {
                                set.add(".");
                            }
                        } else {
                            if (i + 1 != spaceDelimited.size()) {
                                words.put(currentWord, immutableSetOf(spaceDelimited.get(i + 1)));
                            } else {
                                words.put(currentWord, immutableSetOf("."));
                            }
                        }
                    });
        });
    }

    private List<String> splitInput(String s) {
        return immutableListOf(s.split(" "));
    }

    private List<String> splitSentences(final String input) {
        return Stream.of(input.split("\\."))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
