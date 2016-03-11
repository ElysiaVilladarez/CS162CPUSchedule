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
            //return new QueueSchedule(processes);
            return new ShortestRemainingTimeFirstSchedule(processes);
//            return new ShortestJobFirstSchedule(processes); 
        } else{
            //return new PrioritySchedule(processes);
//            return new ShortestJobFirstSchedule(processes); 
            return new ShortestRemainingTimeFirstSchedule(processes);
        }
    }
}
