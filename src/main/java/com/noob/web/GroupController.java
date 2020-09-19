package com.noob.web;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("TransferController")
@Controller
@RequestMapping("/group")
public class GroupController {

    @GetMapping("")
    public String group() {
        return "group";
    }

    @GetMapping("/topic")
    public String topic() {
        return "group/topic";
    }
}
