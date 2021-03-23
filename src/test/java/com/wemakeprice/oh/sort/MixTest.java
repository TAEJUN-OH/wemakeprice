package com.wemakeprice.oh.sort;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MixTest {

    @Test
    public void 영어숫자Mix() {

        //given
        String numStr = "112349";
        String stringStr = "AaBbCcDdeeFfiinqssUwZZ";
        String expectation = "A1a1B2b3C4c9DdeeFfiinqssUwZZ";

        //when
        String result = "";
        int strLength = stringStr.length();
        int numLength = numStr.length();

        int strIndex = 0;
        int numIndex = 0;

        for (;;) {
            if (strIndex < strLength) {
                result += stringStr.substring(strIndex, strIndex + 1);
                strIndex++;
            }
            if (numIndex < numLength) {
                result += numStr.substring(numIndex, numIndex + 1);
                numIndex++;
            }

            if (strIndex == strLength && numIndex == numLength) {
                break;
            }
        }

        //then
        Assertions.assertEquals(result , expectation , "영어 숫자 Mix 추출");
    }
}
