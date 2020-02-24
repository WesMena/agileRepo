/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

/**
 *
 * @author wesli
 */
public class Tag {
   private String tag;
   private int id; 


    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    
    public Tag(String tag, int id) {
        this.tag = tag;
        this.id = id;
    }

  

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
