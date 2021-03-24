package com.wemakeprice.oh.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    /**
     * URL Paring By Jsoup
     * @param url
     * @param type
     * @return URL Paring
     * @throws Exception
     */
    public String parse(String url , String type) throws Exception{
        Document doc = Jsoup.connect(url).get();
        String parsedText = "";

        switch (type) {
            case "html" : //html태그 제외
                parsedText = doc.text();
                break;
            case "text" : //text 전체
                parsedText = doc.toString().replaceAll("[^0-9a-zA-Z]", "");
                break;
        }
        return parsedText;
    }

    /**
     * 숫자 오름차순 정렬
     * @param text
     * @return
     */
    public String sortByNumAsc(String text) {
        String num = text.replaceAll("[^0-9]", "");
        String[] arr = num.split("");
        Arrays.sort(arr);
        num = String.join("", arr);
        return num;
    }


    /**
     * 문자 오름차순 정렬 (1.대소문자 구분없이 알파벳 우선 , 2.대문자 우선)
     * @param text
     * @return
     */
    public String sortByStrAsc(String text) {
        String str = text.replaceAll("[^a-zA-Z]", "");

        // 대소문자 구분없이 비교후,
        // 같을경우 compareTo() naturalOrder
        // 다를경우 compareToIgnoreCase()
        str = Arrays.stream(str.split(""))
                .sorted((o1, o2) -> { int res = o1.compareToIgnoreCase(o2); return (res == 0) ? o1.compareTo(o2) : res;})
                .collect(Collectors.joining());

        return str;
    }


    /**
     *  영문 숫자 merge
     * @param unit
     * @param num
     * @param str
     * @return 몫 , 나머지
     */
    public List<String> merge(int unit , String num , String str) {

        int input = unit;
        List result = new ArrayList<>();
        StringBuilder quotient = new StringBuilder(); //몫
        String rest = ""; //나머지
        String bundle = "";

        int maxSize = num.length() > str.length() ? num.length() : str.length();

        for(int i=0;i<maxSize;i++) {
            //영문
            if(str.length() > i) {
                bundle += (Character.toString(str.charAt(i)));

                if(bundle.length() == input) {
                    quotient.append(bundle);
                    bundle = "";
                }
            }

            //숫자
            if(num.length() > i) {
                bundle += (Character.toString(num.charAt(i)));

                if(bundle.length() == input) {
                    quotient.append(bundle);
                    bundle = "";
                }
            }

            //나머지
            if(i == maxSize-1) {
                rest = bundle;
            }
        }
        result.add(0, quotient.toString());
        result.add(1, rest);

        return result;
    }

}
