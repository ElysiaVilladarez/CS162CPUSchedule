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
public class ShortestRemainingTimeFirstSchedule implements Schedulers {

    private String blocks;
    Process current;
//    boolean first = true;
    int originalBT;
    ArrayList<Process> arrived = new ArrayList<>();

//    String blocks;
//    ArrayList<Process> process; //sorted by arrival time
//    int currTime = 0;

    public ShortestRemainingTimeFirstSchedule(ArrayList<Process> processes) {

//        process = pr;
//        blocks = "";
//        currTime = pr.get(0).arrivalTime;
//        while(true){
//            ArrayList<Process> arrived = new ArrayList<>();
//            if(process.isEmpty()){
//                break;
//            }
//            for(Process p: process){
//                if(p.arrivalTime <= currTime){
//                    arrived.add(p);
//                    
//                }
//                else{
//                    arrived.add(p); //adding next
//                    break;
//                }
//                
//            }
//             //Collections.sort(arrived, Process.burstTimeCompare);
//            
//            //int i = 0;
//            for(int i = 0; i < arrived.size()-1; i++){
//                Process p = arrived.get(i);
//                Process p2 = arrived.get(i+1);
//                System.out.println(p.index + " + ");
//                System.out.println(p2.index + " - ");
//                if((currTime + p.burstTime) > p2.arrivalTime){
//                    if(p.burstTime < p2.burstTime){
//                        blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
//                        currTime = currTime + p.burstTime;
//                        process.remove(p);
//                        System.out.println(process.get(0).index);
//                    }
//                    System.out.println(p.index + " " + p2.index);
//                    blocks = blocks + currTime + " " + p.index + " " + (p2.arrivalTime - currTime) + "\n";
//                    p.setBurstTime(p.burstTime - p2.arrivalTime);
//                    currTime = currTime + p2.arrivalTime;
//                    //process.remove(p);
//                }
//                else{
//                    blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
//                    currTime = currTime + p.burstTime;
//                    process.remove(p);
//                    System.out.println(process.get(0).index);
//                }
//                System.out.println(currTime + " curr");
//                
//                
//            }
//            System.out.println("Labas");
//            Collections.sort(process, Process.arrivalTimeCompare);
//            
//            if(process.size()==1){
//                Process p = process.get(0);
//                blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
//                currTime = currTime + p.burstTime;
//                process.remove(p);
//            }
//        }

         blocks = "";
        int totalTimeElapsed = processes.get(0).arrivalTime;
        boolean hasBeenAdded = false;

        //First process
        current = processes.get(0);
        processes.remove(0);
        originalBT = current.burstTime;

        while (true) {
            if (!processes.isEmpty()) {
                if (totalTimeElapsed >= processes.get(0).arrivalTime) {
                    arrived.add(processes.get(0));
                    processes.remove(0);
                    hasBeenAdded = true;
                    Collections.sort(arrived, Process.burstTimeCompare);
                }
            }

//            if (first && hasBeenAdded) {
//                current = arrived.get(0);
//                originalBT = current.burstTime;
//                arrived.remove(0);
//                hasBeenAdded = false;
//                first = false;
//            }

            if (current != null) {
                if (current.burstTime == 0) {
                    blocks += Integer.toString(totalTimeElapsed - (originalBT - current.burstTime)) + " " + current.index + " " + (originalBT - current.burstTime) + "X\n";
                    if (arrived.isEmpty() && processes.isEmpty()) {
                        break;
                    } else if (arrived.isEmpty()) {
                        current = null;
                    } else {
                        setCurrentProcess();
                        hasBeenAdded = false;
                    }
                } else {
                    if (hasBeenAdded) {
                        if (current.burstTime > arrived.get(0).burstTime) {
                            blocks += Integer.toString(totalTimeElapsed - (originalBT - current.burstTime)) + " " + current.index + " " + (originalBT - current.burstTime) + "\n";
                            arrived.add(current);
                            setCurrentProcess();
                            Collections.sort(arrived, Process.burstTimeCompare);
                        } else {
                            --current.burstTime;
                        }
                        hasBeenAdded = false;
                    } else {
                        --current.burstTime;
                    }

                }
            } else if (hasBeenAdded) {
                setCurrentProcess();
                hasBeenAdded = false;
            }
            ++totalTimeElapsed;


        }
    }

    //Collections.sort(process, Process.);
    public void setCurrentProcess() {
        current = arrived.get(0);
        originalBT = current.burstTime;
        arrived.remove(0);
        --current.burstTime;
    }

    //Collections.sort(process, Process.);
    @Override
    public String getBlocks() {
        return blocks;
    }

}
