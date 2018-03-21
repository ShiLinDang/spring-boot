package com.baidu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dangsl on 2018/3/21.
 */
@Component
public class PropertiesTest {
    @Value("${value1}")
    private String tmallId;

    @Value("${value1.description}")
    private String tmallIdDesc;

    @Value("${value2}")
    private String bValue;

    @Value("${value3}")
    private String bNumber;

    @Value("${value4}")
    private String bignumber;

    @Value("${value5}")
    private String bTest1;

    @Value("${value6}")
    private String bTest2;

    public String getTmallId() {
        return tmallId;
    }

    public void setTmallId(String tmallId) {
        this.tmallId = tmallId;
    }

    public String getTmallIdDesc() {
        return tmallIdDesc;
    }

    public void setTmallIdDesc(String tmallIdDesc) {
        this.tmallIdDesc = tmallIdDesc;
    }

    public String getbValue() {
        return bValue;
    }

    public void setbValue(String bValue) {
        this.bValue = bValue;
    }

    public String getbNumber() {
        return bNumber;
    }

    public void setbNumber(String bNumber) {
        this.bNumber = bNumber;
    }

    public String getBignumber() {
        return bignumber;
    }

    public void setBignumber(String bignumber) {
        this.bignumber = bignumber;
    }

    public String getbTest1() {
        return bTest1;
    }

    public void setbTest1(String bTest1) {
        this.bTest1 = bTest1;
    }

    public String getbTest2() {
        return bTest2;
    }

    public void setbTest2(String bTest2) {
        this.bTest2 = bTest2;
    }
}
