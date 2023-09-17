package com.book.manage.domain;

import jakarta.persistence.*;

@Entity
@Table(name="KDC_CODE")
public class KdcCode {

    @Id
    @Column(name="KDC_MAIN")
    private Short main;

    @Id
    @Column(name="KDC_DIV")
    private Short div;

    public KdcCode(){}
    public KdcCode(Short main, Short div) {
        this.main = main;
        this.div = div;
    }

    public Short getMain() {
        return main;
    }
    public void setMain(Short main) {
        this.main = main;
    }
    public Short getDiv() {
        return div;
    }
    public void setDiv(Short div) {
        this.div = div;
    }

    @Override
    public String toString() {
        return "KdcCode{" +
                "main=" + main +
                ", div=" + div +
                '}';
    }

}
