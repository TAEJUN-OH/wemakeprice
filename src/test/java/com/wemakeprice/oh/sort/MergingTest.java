package com.wemakeprice.oh.sort;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MergingTest {

    @Test
    public void 영문숫자Mix_몫나머지출력() {

        //given
        int input = 5; //출력묶음단위
        String num = "112349";
        String str = "AaBbCcDdeeFfiinqssUwZZ";
        String QuoExpectation = "A1a1B2b3C4c9DdeeFfiinqssU";
        String RestExpectation = "wZZ";


        //when
        StringBuilder quotient = new StringBuilder(); // 몫
        String rest = ""; //나머지
//        String bundle = ""; //출력묶음
        StringBuilder bundle = new StringBuilder();

        int maxSize = num.length() > str.length() ? num.length() : str.length();

        for(int i=0;i<maxSize;i++) {

            //영문출력값
            if(str.length() > i) {
//                bundle += (str.charAt(i));

                bundle.append(str.charAt(i));

                if(bundle.length() == input) {
                    quotient.append(bundle);
//                    bundle = "";
                    bundle.setLength(0);
                }
            }

            //숫자출력값
            if(num.length() > i) {
//                bundle += (num.charAt(i));

                bundle.append(num.charAt(i));

                if(bundle.length() == input) {
                    quotient.append(bundle);
//                    bundle = "";
                    bundle.setLength(0);
                }
            }

            //나머지
            if(i == maxSize-1) {
                rest = bundle.toString();

//                rest = bundle;
            }
        }
        String result = quotient.toString();

        //then
        Assertions.assertEquals(result , QuoExpectation , "몫");
        Assertions.assertEquals(rest , RestExpectation , "나머지");
    }
}
