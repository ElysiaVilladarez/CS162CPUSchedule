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
public class ShortestRemainingTimeFirstSchedule implements Schedulers{
    String blocks;
    ArrayList<Process> process; //sorted by arrival time
    int currTime = 0;
    
    public ShortestRemainingTimeFirstSchedule(ArrayList<Process> pr){
        
        process = pr;
        blocks = "";
        currTime = pr.get(0).arrivalTime;
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
                    arrived.add(p); //adding next
                    break;
                }
                Collections.sort(arrived, Process.burstTimeCompare);
            }
             
            
            //int i = 0;
            for(int i = 0; i < arrived.size()-1; i++){
                Process p = arrived.get(i);
                Process p2 = arrived.get(i+1);
                System.out.println(p.index + " + ");
                System.out.println(p2.index + " - ");
                if((currTime + p.burstTime) > p2.arrivalTime){
                    if(p.burstTime < p2.burstTime){
                        blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                        currTime = currTime + p.burstTime;
                        process.remove(p);
                        System.out.println(process.get(0).index);
                    }
                    System.out.println(p.index + " " + p2.index);
                    blocks = blocks + currTime + " " + p.index + " " + (p2.arrivalTime - currTime) + "\n";
                    p.setBurstTime(p.burstTime - p2.arrivalTime);
                    currTime = currTime + p2.arrivalTime;
                    //process.remove(p);
                }
                else{
                    blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                    currTime = currTime + p.burstTime;
                    process.remove(p);
                    System.out.println(process.get(0).index);
                }
                System.out.println(currTime + " curr");
                
                
            }
            System.out.println("Labas");
            
            if(process.size()==1){
                Process p = process.get(0);
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
