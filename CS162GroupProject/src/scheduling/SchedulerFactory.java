/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;

/**
 *
 * @author elysi
 */
public class SchedulerFactory {
    public Schedulers schedule(String algorithm, ArrayList<Process> processes){
        
        if(algorithm.equals("FCFS")){
            return new QueueSchedule(processes); 
        } else if (algorithm.equals("SJF")){
            return new ShortestJobFirstSchedule(processes); 
        } else if (algorithm.equals("SRTF")){
            return new ShortestRemainingTimeFirstSchedule(processes); 
        } else if (algorithm.equals("P")){
            return new PrioritySchedule(processes); 
        } else{
            System.out.println("NO SUCH SCHEDULE");
            return null;
        }
    }
}
