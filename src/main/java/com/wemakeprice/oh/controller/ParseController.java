package com.wemakeprice.oh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParseController {

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }

    @PostMapping(value="/parse")
    public void parse() throws Exception {

    }
}
