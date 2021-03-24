package com.wemakeprice.oh.controller;

import com.wemakeprice.oh.request.ParserRequest;
import com.wemakeprice.oh.response.ParserResponse;
import com.wemakeprice.oh.common.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ParserController {

    @Autowired Converter converter;

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }

    /**
     * 1.Url Paring
     * 2.Sort
     * 3.Merge
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value="/parser")
    public @ResponseBody ParserResponse parser(@RequestBody ParserRequest request) throws Exception {

        String text = converter.parse(request.getUrl(), request.getType()); //parsing url
        String str = converter.sortByStrAsc(text); //str sort
        String num = converter.sortByNumAsc(text); //num sort
        List<String> list = converter.merge(request.getUnitBundle(), num, str); //merge

        return new ParserResponse(list.get(0) , list.get(1));
    }
}
