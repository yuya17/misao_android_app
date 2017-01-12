package com.example.contactlistviewnamephhone_sonu;

/**
 * Created by yuyanozaki on 2017/01/12.
 */

public class ContactAttribute {//name& phonenum

    private String phone;
    private String name;

    public String getname(){
        return name;
    }

    public String getphone(){
        return phone;
    }

    public void setName(String name){
        this.name= name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
