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
            ArrayList<Process> willArrive = new ArrayList<>();
            if(process.isEmpty()){
                break;
            }
            for(Process p: process){
                if(p.arrivalTime <= currTime){
                    willArrive.add(p);
                }
                else{
                    break;
                }
                
            }
            Collections.sort(willArrive, Process.burstTimeCompare);
            Process p;
            if(!willArrive.isEmpty()){
                p = willArrive.get(0);
            }
            else{
                p = process.get(0);
            }
                
                blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                currTime = currTime + p.burstTime;
                process.remove(p);
                
        }
        
            
        }
    

    @Override
    public String getBlocks() {
        return blocks;
    }
    
}
