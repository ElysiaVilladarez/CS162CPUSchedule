/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author elysi
 */
public class QueueSchedule {

    private ArrayList<Process> processes;
    private String blocks;

    public QueueSchedule(ArrayList<Process> processes) {
        this.processes = processes;

        blocks = "";
        int totalTimeElapsed = 0;
        int i = 0;
        while(i<processes.size()) {
            Process p = processes.get(i);
            totalTimeElapsed += 1;
            if (totalTimeElapsed == p.arrivalTime) {
                blocks += Integer.toString(totalTimeElapsed) + " " + p.index + " " + p.burstTime + "X\n";
                i++;
                totalTimeElapsed += p.burstTime;
            }
        }

    }

    public String getBlock() {
        return blocks;
    }
}
