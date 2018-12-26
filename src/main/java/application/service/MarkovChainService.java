package application.service;

import java.util.HashMap;
import java.util.Set;

public interface MarkovChainService {

    HashMap<String, Set<String>> addInput(String input);

    void clearEntries();

    String determineProbability(String input);
}
