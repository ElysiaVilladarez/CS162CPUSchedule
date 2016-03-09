/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Pearl Santos
 */
public class ShortestJobFirstSchedule implements Schedulers {
    String blocks;
    ArrayList<Process> process; //sorted by arrival time
    int currTime = 0;
    
    public ShortestJobFirstSchedule(ArrayList<Process> pr){
        
        process = pr;
        blocks = "";
        currTime = process.get(0).arrivalTime;
        //int remaining = process.size();
        while(true){
            ArrayList<Process> arrived = new ArrayList<>();
            if(process.isEmpty()){
                break;
            }
            for(Process p: process){
                if(p.arrivalTime <= currTime){
                    arrived.add(p);
                    
                }
                else{
                    break;
                }
                Collections.sort(arrived, Process.burstTimeCompare);
            }
             
            
            //int i = 0;
            for(Process p: arrived){
                blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                currTime = currTime + p.burstTime;
                process.remove(p);
            }
        }
        
            
        }
        
        //Collections.sort(process, Process.);
    

    @Override
    public String getBlocks() {
        return blocks;
    }
    
}
