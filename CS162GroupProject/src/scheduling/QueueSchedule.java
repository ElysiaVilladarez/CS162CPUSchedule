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
public class QueueSchedule implements Schedulers {

    private String blocks;

    public QueueSchedule(ArrayList<Process> processes) {
        //Assumption: processes are sorted by arrivalTime
        blocks = "";
        int totalTimeElapsed = processes.get(0).arrivalTime;
        int i = 0;
        while (i < processes.size()) {
            Process p = processes.get(i);
            if (totalTimeElapsed >= p.arrivalTime) {
                blocks += Integer.toString(totalTimeElapsed) + " " + p.index + " " + p.burstTime + "X\n";
                i++;
                totalTimeElapsed += p.burstTime;
            } else {
                ++totalTimeElapsed;
            }
        }

    }

    public String getBlocks() {
        return blocks;
    }
}
