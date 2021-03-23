package com.wemakeprice.oh.controller;

import com.wemakeprice.oh.request.ParseRequest;
import com.wemakeprice.oh.response.ParseResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class ParseController {

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }


    @PostMapping(value="/parse")
    public @ResponseBody ParseResponse parse(@RequestBody ParseRequest request) throws Exception {

        String quotientStr = "";
        String restStr = "";
        String content = "";

        Document doc = Jsoup.connect(request.getUrl()).get();

        //모듈화해야함

        if(request.getType().equals("exceptTag")) {
            content = doc.text();
        }else{
            content = doc.toString();
            content= content.replaceAll("[^0-9a-zA-Z]", "");
        }

        int unitBundle = request.getUnitBundle();

        //숫자
        String numStr = content.replaceAll("[^0-9]", "");
        String[] arr = numStr.split("");
        Arrays.sort(arr);
        numStr = String.join("", arr);


        //영문
        String stringStr = content.replaceAll("[^a-zA-Z]", "");
        // 대소문자 구분없이 비교후,
        // 같을경우 compareTo() naturalOrder
        // 다를경우 compareToIgnoreCase()
        stringStr = Arrays.stream(stringStr.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                })
                .collect(Collectors.joining());


        //영문 숫자 Mix
        int strLength = stringStr.length();
        int numLength = numStr.length();

        int strIndex = 0;
        int numIndex = 0;
        String outStr = "";

        for (; ; ) {
            if (strIndex < strLength) {
                outStr += stringStr.substring(strIndex, strIndex + 1);
                strIndex++;
            }
            if (numIndex < numLength) {
                outStr += numStr.substring(numIndex, numIndex + 1);
                numIndex++;
            }

            if (strIndex == strLength && numIndex == numLength) {
                break;
            }
        }

        int quotient = outStr.length() / unitBundle; //		몫 :
        int remainder = outStr.length() % unitBundle; //		나머지 :


        if (quotient > 0) {
            quotientStr = outStr.substring(0, quotient * unitBundle);
        }

        if (remainder > 0) {
            if (quotient > 0) {
                restStr = outStr.substring(quotient * unitBundle);
            } else {
                restStr = outStr.substring(0);
            }
        }

        return new ParseResponse(quotientStr , restStr);
    }
}
