package com.book.manage.controller.dto;

import com.book.manage.domain.KdcCode;

public class KdcCodeDTO {
    private Short main;
    private Short div;

    public KdcCodeDTO(KdcCode kdcCode){
        this.main = kdcCode.getMain();
        this.div = kdcCode.getDiv();
    }
}
