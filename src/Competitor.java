/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monta
 */
public class Competitor {
    private String name;
    private int age;
    private int eventNo;
    public Competitor(){
        name = "";
        age = 0;
    }
    public Competitor(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;        
    }
    public int getEventNo(){
        return eventNo;
    }            
    public void setEventNo(int eventNo){
        this.eventNo = eventNo;
    }
}
