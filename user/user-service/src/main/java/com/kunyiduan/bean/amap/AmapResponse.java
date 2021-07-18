package com.kunyiduan.bean.amap;

import lombok.Data;

import java.util.List;

@Data
public class AmapResponse {

    private String status;

    private String info;

    private String infocode;

    private String count;

    private List<AmapResult> results;

}
