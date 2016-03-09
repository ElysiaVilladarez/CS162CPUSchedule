/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author elysi
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Process> processes = new ArrayList<>();
        String results = "";

        BufferedReader br = null;
        String filePath = "Input.txt";

        try {
            br = new BufferedReader(new FileReader(filePath));
            int testCase = Integer.parseInt(br.readLine());
            for (int test = 1; test <= testCase; test++) {
                String[] line2 = br.readLine().split(" ");
                int processesNum = Integer.parseInt(line2[0]);
                results += Integer.toString(test) + "\n";
                String schedule = line2[1];
                for (int i = 0; i < processesNum; i++) {
                    String[] pro = br.readLine().split(" ");
                    Process p = new Process(Integer.parseInt(pro[0]), Integer.parseInt(pro[1]), Integer.parseInt(pro[2]), i + 1);
                    processes.add(p);
                }
                Collections.sort(processes, Process.arrivalTimeCompare);

                SchedulerFactory sf = new SchedulerFactory();
                Schedulers use = null;
                if (schedule.trim().equals("RR")) {
                    use = new RoundRobinSchedule(processes, Integer.parseInt(line2[2]));
                } else {
                    use = sf.schedule(schedule, processes);
                }
                results += use.getBlocks();
                System.out.print(results);
                results = "";

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //Scanner in = new Scanner(System.in);
//        ArrayList<Process> processes = new ArrayList<>();
//        String results = "";
//        int testCase = in.nextInt();
//
//        for (int test = 1; test <= testCase; test++) {
//            System.out.println("1st ForLOOp " + test);
//            int processesNum = in.nextInt();
//            results += Integer.toString(test) + "\n";
//            // String schedule = in.next();
//            for (int i = 0; i < processesNum; i++) {
//                System.out.println("2ndt ForLOOp" + processesNum + " " + i);
//                Process p = new Process(in.nextInt(), in.nextInt(), in.nextInt(), i + 1);
//                processes.add(p);
//            }
//            Collections.sort(processes, Process.arrivalTimeCompare);
//            PrioritySchedule que = new PrioritySchedule(processes);
//            results += que.getBlocks();
//            System.out.println(results);
//        }
    }
}
