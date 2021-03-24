package com.wemakeprice.oh.sort;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SortingTest {

    @Test
    public void 숫자추출_정렬오름차순() {

        //given
        String parsedHtml = "위ssiaf@12B4FAiqC39ZbUnd메wDe1Zec프";
        String expectation = "112349";

        //when
        String result = parsedHtml.replaceAll("[^0-9]", "");
        String[] arr = result.split("");
        Arrays.sort(arr);
        result = String.join("", arr);


        //then
        Assertions.assertEquals(result , expectation , "숫자만 추출되고, 오름차순 정렬되어야 함");

    }

    @Test
    public void 영문추출_정렬오름차순() {

        //given
        String htmlStr = "위ssiaf@12B4FAiqC39ZbUnd메wDe1Zec프";
        String expectation = "AaBbCcDdeeFfiinqssUwZZ";

        //when
        String result = htmlStr.replaceAll("[^a-zA-Z]", "");

        // 대소문자 구분없이 비교후,
        // 같을경우 compareTo() naturalOrder
        // 다를경우 compareToIgnoreCase()
        result = Arrays.stream(result.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res; })
                .collect(Collectors.joining());

        //then
        Assertions.assertEquals(result , expectation , "영문만 추출되고 오름차순(1.대소문자 구분없이 알파벳 우선 , 2.대문자 우선) 정렬되어야함.");
    }
}
