package application.controller;

import application.model.InputModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

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
        redirectAttributes.addFlashAttribute("message", "Redirection successful, message read: " + inputModel.getTextInput());
        return "redirect:/";
    }

}
