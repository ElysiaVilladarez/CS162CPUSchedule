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
//            Process p = process.get(0);
//            //System.out.println(p.index);
//            int i = 1;
//            //System.out.println(p.index);
//
//            if (i < process.size()) {
//                Process p2 = process.get(i);
//                
//                while (((currTime + p.burstTime) > p2.arrivalTime) && i < process.size()) {
//                    System.out.println(currTime + p.burstTime);
//                    System.out.println(p2.index + " " + p2.arrivalTime);
//                    willArrive.add(p2);
//                    p2 = process.get(i);
//                    i++;
//                }
//                System.out.println("Enough");    
//            }
            Collections.sort(willArrive, Process.burstTimeCompare);
            //willArrive.add(0, p); 
            
            
            //for(Process r: willArrive){
                Process p = willArrive.get(0);
                blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                currTime = currTime + p.burstTime;
                process.remove(p);
                
//                Process r = willArrive.get(0);
//                blocks = blocks + currTime + " " + r.index + " " + r.burstTime + "X" + "\n";
//                currTime = currTime + r.burstTime;
//                process.remove(r);
            //}
            
            //i = 0;
        }
        
            
        }
        
        //Collections.sort(process, Process.);
    

    @Override
    public String getBlocks() {
        return blocks;
    }
    
}
