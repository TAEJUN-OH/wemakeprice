package com.wemakeprice.oh.convert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ConvertTest {

    @Test
    public void HTML태그제외() {

        //given
        String html = "<!doctype html>" +
                "<html>" +
                     "<head>" +
                         "<title>First parse</title>" +
                     "</head>" +
                     "<body>" +
                        "<p>Parsed HTML into a doc.</p>" +
                    "</body>" +
                "</html>";

        String expectation = "First parse Parsed HTML into a doc.";

        //when
        Document doc = Jsoup.parse(html);
        String result = doc.text();

        //then
        Assertions.assertEquals(result, expectation , "Html 태그가 제외되어야 한다");

    }

    @Test
    public void TEXT전체() {

        //given
        String html = "<!doctype html>" +
                        "<html>" +
                            "<head>" +
                                "<title>First parse</title>" +
                            "</head>" +
                            "<body>" +
                                "<p>Parsed HTML into a doc.</p>" +
                            "</body>" +
                        "</html>";

        String expectation = "doctypehtmlhtmlheadtitleFirstparsetitleheadbodypParsedHTMLintoadocpbodyhtml";


        //when
        Document doc = Jsoup.parse(html);
        String result = doc.toString();
        result= result.replaceAll("[^0-9a-zA-Z]", "");


        //then
        Assertions.assertEquals( result, expectation ,"텍스트 전체가 나와야 한다");
    }
}
