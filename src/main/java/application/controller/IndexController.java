package application.controller;

import application.model.InputModel;
import application.service.MarkovChainService;
import application.util.MarkovCollectionInterpreter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MarkovChainService markovChainService;
    private final MarkovCollectionInterpreter markovCollectionInterpreter;

    @ModelAttribute("inputModel")
    public InputModel inputModel(){
        return new InputModel();
    }

    @GetMapping("/")
    public String get() {
        return "index";
    }

    @PostMapping("/")
    public String post(
            final RedirectAttributes redirectAttributes,
            final InputModel inputModel){

        final HashMap<String, Set<String>> relationships
                = markovChainService.addInput(inputModel.getTextInput());
        final Map<String, String> nodeRelationships
                = markovCollectionInterpreter.interpretRelationships(relationships);

        redirectAttributes.addFlashAttribute("nodeRelationships", nodeRelationships);
        redirectAttributes.addFlashAttribute("message", "Redirection successful, message read: " + inputModel.getTextInput());
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clear(){
        markovChainService.clearEntries();
        return "redirect:/";
    }

}
