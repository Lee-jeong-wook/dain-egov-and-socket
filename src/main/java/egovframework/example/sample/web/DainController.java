package egovframework.example.sample.web;

import egovframework.example.sample.service.DainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DainController {
    private final DainService dainService;
    @GetMapping("/test.do")
    public String test(Model model) throws Exception {
        model.addAttribute("vo", dainService.selectAllCCtvList());
        return "sample/test";
    }
}