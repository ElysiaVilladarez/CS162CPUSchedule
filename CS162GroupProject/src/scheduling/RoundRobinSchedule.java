/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author elysi
 */
public class RoundRobinSchedule implements Schedulers {

    private String blocks;

    public RoundRobinSchedule(ArrayList<Process> processes, int timeQuantum) {
        //Assumption: processes are sorted by arrivalTime
        blocks = "";
        ArrayList<Process> arrived = new ArrayList<>();
        int totalTimeElapsed = processes.get(0).arrivalTime;
        Process current = processes.get(0);
        int originalBT = current.burstTime;
        processes.remove(0);
        

        while (true){
            if (!processes.isEmpty()) {
                if (totalTimeElapsed >= processes.get(0).arrivalTime) {
                    arrived.add(processes.get(0));
                    processes.remove(0);
                }
            }

            if(current!=null){
                int resourcesLeft = timeQuantum - current.burstTime;
                if(resourcesLeft<0){
                    current.burstTime = Math.abs(resourcesLeft);
                    blocks += Integer.toString(totalTimeElapsed) + " " + current.index + " " + (originalBT - current.burstTime) + "\n";
                    totalTimeElapsed+=current.burstTime;
                    processes.add(current);
                } else{
                    current.burstTime = 0;
                    blocks += Integer.toString(totalTimeElapsed) + " " + current.index + " " + originalBT + "X\n";
                    totalTimeElapsed+=originalBT;
                }
                
                if(arrived.isEmpty() && processes.isEmpty()){
                    break;
                } else if(arrived.isEmpty()){
                    current = null;
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
            }
            
        }
    }

    @Override
    public String getBlocks() {
        return blocks;
    }
}
