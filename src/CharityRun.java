
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monta
 */
public abstract class CharityRun {
    private String date;
    private String startTime;
    private ArrayList<Competitor> m_Competitor;
    private boolean state;
    private boolean flag;
    public CharityRun(){
        date = "";
        startTime = "";        
    }
    
    public CharityRun(String date,String startTime,boolean flag){
        this.date = date;
        this.startTime = startTime;    
    }    
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public ArrayList<Competitor> getCompetitor(){
        return m_Competitor;
    }
    public void setCompetitor(ArrayList<Competitor> list){
        m_Competitor = list;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean state){
        this.state = state;
    }
}
