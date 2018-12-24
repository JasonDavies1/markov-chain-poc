package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @GetMapping("/")
    public String get() {
        return "index";
    }

    @PostMapping("/")
    public String post(final RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Redirection successful!");
        return "redirect:/";
    }

}
