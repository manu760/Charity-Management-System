
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
public class RunEntry {
    private int eventNumber;
    private ArrayList<CharityRun> m_list;
    public RunEntry(){
        m_list = new ArrayList<CharityRun>();
    }
    public RunEntry(int eventNumber){
        this.eventNumber = eventNumber;
    }
    public ArrayList<CharityRun> getEvents(){
        return m_list;
    }
    public int getEventNumber(){
        return eventNumber;
    }
    public void setEventNumber(int eventNumber){
        this.eventNumber = eventNumber;
    }
    public void addEvent(CharityRun charityRun){
        m_list.add(charityRun);
    }
    public void removeEvent(){
        if (m_list.size() > 0)
            m_list.remove(m_list.size() - 1);
    }
}
