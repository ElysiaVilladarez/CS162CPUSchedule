/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;


import java.util.Comparator;

/**
 *
 * @author elysi
 */
public class Process {
    public int arrivalTime, burstTime, priority, index;
    
    public Process(int aT, int bT, int p, int i){
        arrivalTime = aT;
        burstTime = bT;
        priority = p;
        index = i;
    }
    
    public static Comparator<Process> arrivalTimeCompare = new Comparator<Process>(){

        @Override
        public int compare(Process o1, Process o2) {
            int arrival1 = o1.arrivalTime;
            int arrival2 = o2.arrivalTime;
            
            return arrival1-arrival2;
        }
        
    };
    
    public static Comparator<Process> priorityCompare = new Comparator<Process>(){

        @Override
        public int compare(Process o1, Process o2) {
            int pri1 = o1.priority;
            int pri2 = o2.priority;
            
            return pri1-pri2;
        }
        
    };
    
}
