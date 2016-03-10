/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author elysi
 */
public class RoundRobinSchedule implements Schedulers {

    private String blocks;
    private int totalTimeElapsed;
    private ArrayList<Process> arrived = new ArrayList<>();

    public RoundRobinSchedule(ArrayList<Process> processes, int timeQuantum) {
        //Assumption: processes are sorted by arrivalTime
        blocks = "";
        ArrayList<Process> doneMyTime = new ArrayList<>();
        totalTimeElapsed = processes.get(0).arrivalTime;
        Process current = processes.get(0);
        Process store = null;
        int originalBT = current.burstTime;
        processes.remove(0);
        

        while (true){
            
            if(current!=null){
                int resourcesLeft = current.burstTime - timeQuantum;
                if(resourcesLeft>0){
                    current.burstTime = resourcesLeft;
                    blocks += Integer.toString(totalTimeElapsed) + " " + current.index + " " + (originalBT - current.burstTime) + "\n";
                    totalTimeElapsed+=(originalBT-current.burstTime);
                    doneMyTime.add(current);
                    checkArrival(processes);
                    
                } else{
                    current.burstTime = 0;
                    blocks += Integer.toString(totalTimeElapsed) + " " + current.index + " " + originalBT + "X\n";
                    totalTimeElapsed+=originalBT;
                    checkArrival(processes);
                }
                
                if(arrived.isEmpty() && processes.isEmpty() && doneMyTime.isEmpty()){
                    break;
                } else if(arrived.isEmpty()){
                    if(doneMyTime.isEmpty()){
                        current = null;
                    } else{
                        current = doneMyTime.get(0);
                        doneMyTime.remove(0);
                        originalBT = current.burstTime;
                    }
                } else {
                    current = arrived.get(0);
                    arrived.remove(0);
                    originalBT = current.burstTime;
                }
                
            } else if (!arrived.isEmpty()){
                current = arrived.get(0);
                arrived.remove(0);
                originalBT = current.burstTime;
            } else {
                ++totalTimeElapsed;
                checkArrival(processes);
            }
            
        }
    }
    
    public void checkArrival(ArrayList<Process> processes){
        if (!processes.isEmpty()) {
                if (totalTimeElapsed >= processes.get(0).arrivalTime) {
                    arrived.add(processes.get(0));
                    processes.remove(0);
                    
                }
            }
    }

    @Override
    public String getBlocks() {
        return blocks;
    }
}
