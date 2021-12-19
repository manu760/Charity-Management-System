/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author monta
 */
public class Park extends Venue{
    private int numChangingFacilities;
    public Park(){
        numChangingFacilities = 0;
    }
    public Park(int numChangingFacilities){
        this.numChangingFacilities = numChangingFacilities;
    }
    public int getNumChangingFacilities(){
        return numChangingFacilities;
    }
    public void setNumChangingFacilities(int num){
        numChangingFacilities = num;
    }   
}
