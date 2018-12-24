package application.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class MarkovChainServiceImpl implements MarkovChainService {

    private final HashMap<String, Set<String>> words = new HashMap<>();

    private boolean listContainsAFullStop(final String input) {
        return input.contains(".");
    }

    @Override
    public HashMap<String, Set<String>> addInput(@NonNull final String input) {
        initialiseInputValues(input);
        return words;
    }

    private void initialiseInputValues(final String input) {
        if (listContainsAFullStop(input)) {
            splitSentences(input).forEach(s -> {
                final List<String> spaceDelimited = immutableListOf(s.split(" "));
                IntStream.range(0, spaceDelimited.size())
                        .forEachOrdered(i -> {
                            final String currentWord = spaceDelimited.get(i);
                            final Optional<Set<String>> strings = Optional.ofNullable(words.get(currentWord));
                            if (strings.isPresent()){
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
    }

    private List<String> immutableListOf(final String[] values){
        return Stream.of(values).collect(Collectors.toList());
    }

    private Set<String> immutableSetOf(final String firstValue) {
        return Stream.of(firstValue).collect(Collectors.toSet());
    }

    private List<String> splitSentences(final String input) {
        return Stream.of(input.split("\\."))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
