package org.kisti.edison.wfapi.custom.model;

public class Item{
    private String key;
    private Object value;
    public Item(){}
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }
    public Object getValue(){
        return value;
    }
    public void setValue(Object value){
        this.value = value;
    }
    public Item(String key){
        this.key = key;
    }
    public Item(String key, Object value){
        this.key = key;
        this.value = value;
    }
}