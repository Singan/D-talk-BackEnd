package dtalk.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Controller
public class HelloController {
    @GetMapping("/swagger-ui")
    public String homeRedirect() {
        System.out.println("스웨거 진입");
        return "redirect:/swagger-ui.html";
    }

}