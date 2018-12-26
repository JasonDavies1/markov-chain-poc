package application.service;

import java.util.HashMap;
import java.util.List;

public interface MarkovChainService {

    HashMap<String, List<String>> addInput(String input);

    void clearEntries();

    String determineProbability(String input);
}
