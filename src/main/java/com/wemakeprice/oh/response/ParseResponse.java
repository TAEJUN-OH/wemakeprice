package com.wemakeprice.oh.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ParseResponse {
    private String quotient;
    private String rest;

}
