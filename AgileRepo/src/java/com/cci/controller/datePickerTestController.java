/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nvidi
 */
 @ManagedBean(name="dtPickerController")
 @SessionScoped 
public class datePickerTestController {
    private List<Date> range = new ArrayList<>();
    
    public datePickerTestController(){
        Calendar c = Calendar.getInstance();
        c.set(2020, 10, 1);
        range.add(c.getTime());
        c.set(2020, 10, 2);
        range.add(c.getTime());
    
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }
    
    
}
 