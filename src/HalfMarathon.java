/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monta
 */
public class HalfMarathon extends CharityRun{
    
    private Venue venue;
    private int waterFacilities;
    public void HalfMarathon(){
        
    }
    public Venue getVenue(){
        return venue;
    }
    public void setVenue(Venue venue){
        this.venue = venue;
    }
   
    public int getWaterFacilities(){
        return waterFacilities;
    }
    public void setWaterFacilities(int waterFacilities){
        this.waterFacilities = waterFacilities;
    }
}
