package net.boddin.clouddemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("demo")
public class DemoController {

    @GetMapping(value = "/one")
    @ResponseBody
    public Object getDemoObject(){
        return "hello";
    }
}
