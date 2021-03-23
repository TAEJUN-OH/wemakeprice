package com.wemakeprice.oh.sort;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SortTest {

    @Test
    public void 숫자추출정렬오름차순() {

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
    public void 영문추출정렬오름차순() {

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



    @Test
    public void 테스트() throws Exception {
        // TODO Auto-generated method stub
//        Document doc = Jsoup.connect("https://www.naver.com").get();


        String htmlStr = "위ssif@124FAiqC39ZUn메we1Zec프";

//        String htmlStr = doc.text();

        String stringStr = htmlStr.replaceAll("[^a-zA-Z]", "");
        System.out.println("stringStr = " + stringStr);


//		String[] array = stringStr.split("");
//        Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
//        stringStr = String.join("", array);

        stringStr = Arrays.stream(stringStr.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                }).collect(Collectors.joining());
//		https://tries1.github.io/java/2019/08/23/alphabet_sort.html
//		대소문자 구분없이 비교후, 같을경우 compareTo() naturalOrder 적용
//		다를경우 compareToIgnoreCase() 적용
//
//		소문자를 먼저 출력하고 싶다면 o1.compareTo(o2) -> o2.compareTo(o1)로 변경하면 됩니다.

        String numStr = htmlStr.replaceAll("[^0-9]", "");
        System.out.println("numStr = " + numStr);
        String[] array = numStr.split("");
        Arrays.sort(array);
        numStr = String.join("", array);
        //numStr.so
        System.out.println(stringStr);
        System.out.println(numStr);




        String outStr = "";
        int strLength = stringStr.length();
        int numLength = numStr.length();

        int strIndex = 0;
        int numIndex = 0;

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
        System.out.println("outStr : " + outStr);


        System.out.println("====================================================");
        System.out.println();

//        assertEquals(outStr , "A0a1a2a7BbZz");

    }


    @Test
    public void 테스트02() {
        // TODO Auto-generated method stub
        String htmlStr = "tjo오태준입니다람쥐#z20Aaa17bBZa?tjo";
        String stringStr = htmlStr.replaceAll("[^a-zA-Z]", "");
        int out = 4;
//		몫 : quotient
//		나머지 : remainder
//		String[] array = stringStr.split("");
//        Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
//        stringStr = String.join("", array);

        stringStr = Arrays.stream(stringStr.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                }).collect(Collectors.joining());
//		https://tries1.github.io/java/2019/08/23/alphabet_sort.html
//		대소문자 구분없이 비교후, 같을경우 compareTo() 사용하여 naturalOrder 적용
//		다를경우 compareToIgnoreCase()가 적용
//
//		소문자를 먼저 출력하고 싶다면 o1.compareTo(o2) -> o2.compareTo(o1)로 변경하면 됩니다.

        String numStr = htmlStr.replaceAll("[^0-9]", "");
        String[] array = numStr.split("");
        Arrays.sort(array);
        numStr = String.join("", array);
        //numStr.so
        System.out.println(stringStr);
        System.out.println(numStr);
        String outStr = "";
        int strLength = stringStr.length();
        int numLength = numStr.length();

        int strIndex = 0;
        int numIndex = 0;

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
        System.out.println("출력문 : " + outStr);
        System.out.println("총 길이 : " + outStr.length() + " 출력단위 : " + out + " 몫  : " + (int) outStr.length() / out + " 나머지 : " + outStr.length() % out);

        for (int start = 0, end = out, Length = outStr.length(); ; ) {

            if (end <= Length) {
                System.out.println("몫  : " + outStr.substring(start, end));
                start = end;
                end += out;
            } else {
                String temp = outStr.substring(start);
                if (temp.length() > 0) {
                    System.out.println("나머지  : " + outStr.substring(start));
                }
                break;
            }
        }


        System.out.println("====================================================");
        System.out.println();

    }

    @Test
    public void 테스트03() throws Exception {
        // TODO Auto-generated method stub

//        Document doc = Jsoup.connect("https://www.naver.com").get();
//
//        String htmlStr = doc.text();

        String htmlStr = "tjo오태준입니다람쥐#z20Aaa17bBZa?tjo";
        String stringStr = htmlStr.replaceAll("[^a-zA-Z]", "");
        int out = 100;

//		String[] array = stringStr.split("");
//        Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
//        stringStr = String.join("", array);

        stringStr = Arrays.stream(stringStr.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                }).collect(Collectors.joining());
//		https://tries1.github.io/java/2019/08/23/alphabet_sort.html
//		대소문자 구분없이 비교후, 같을경우 compareTo() 사용하여 naturalOrder 적용
//		다를경우 compareToIgnoreCase()가 적용
//
//		소문자를 먼저 출력하고 싶다면 o1.compareTo(o2) -> o2.compareTo(o1)로 변경하면 됩니다.

        String numStr = htmlStr.replaceAll("[^0-9]", "");
        String[] array = numStr.split("");
        Arrays.sort(array);
        numStr = String.join("", array);
        //numStr.so
        System.out.println(stringStr);
        System.out.println(numStr);
        String outStr = "";
        int strLength = stringStr.length();
        int numLength = numStr.length();

        int strIndex = 0;
        int numIndex = 0;

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

//        System.out.println("입력문 : " + htmlStr);
//        System.out.println("출력문 : " + outStr);
//        System.out.println("총 길이 : " + outStr.length() + " 출력단위 : " + out + " 몫  : " + (int) outStr.length() / out + " 나머지 : " + outStr.length() % out);
//
//        for (int start = 0, end = out, Length = outStr.length(); ; ) {
//
//            if (end <= Length) {
//                System.out.println("몫  : " + outStr.substring(start, end));
//                start = end;
//                end += out;
//            } else {
//                String temp = outStr.substring(start);
//                if (temp.length() > 0) {
//                    System.out.println("나머지  : " + outStr.substring(start));
//                }
//                break;
//            }
//        }
//        System.out.println("====================================================");
//        System.out.println();


        System.out.println("입력문 : " + htmlStr);
        System.out.println("출력문 : " + outStr);
        int quotient = (int) outStr.length() / out; //		몫 :
        int remainder = outStr.length() % out; //		나머지 :

        System.out.println("총 길이 : " + outStr.length() + " 출력단위 : " + out + " 몫  : " + quotient + " 나머지 : " + remainder);

        if (quotient > 0) {
            System.out.println("몫  : " + outStr.substring(0, quotient * out));
        }

        if (remainder > 0) {
            if (quotient > 0) {
                System.out.println("나머지  : " + outStr.substring(quotient * out));
            } else {
                System.out.println("나머지  : " + outStr.substring(0));
            }

        }


        System.out.println("====================================================");
        System.out.println();


    }
}
