/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author elysi
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Process> processes = new ArrayList<>();
        String results = "";
        int testCase = in.nextInt();
        
        for (int test = 1; test <= testCase; test++) {
            System.out.println("1st ForLOOp " + test);
            int processesNum = in.nextInt();
            results += Integer.toString(test) + "\n";
           // String schedule = in.next();
            for (int i = 0; i < processesNum; i++) {
                System.out.println("2ndt ForLOOp" + processesNum + " " + i);
                Process p = new Process(in.nextInt(), in.nextInt(), in.nextInt(), i+1);
                processes.add(p);
            }
            Collections.sort(processes, Process.arrivalTimeCompare);
            PrioritySchedule que = new PrioritySchedule(processes);
            results+=que.getBlocks();
            System.out.println(results);
        }
    }
}
