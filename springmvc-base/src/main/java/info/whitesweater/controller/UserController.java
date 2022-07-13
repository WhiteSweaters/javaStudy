package info.whitesweater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/findUsers")
    @ResponseBody
    public String findUsers(){
        return "springmvc ...";
    }

}
