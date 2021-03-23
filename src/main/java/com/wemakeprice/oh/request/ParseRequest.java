package com.wemakeprice.oh.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ParseRequest {
    private String url;
    private String type;
    private Integer unitBundle;
}
