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

    String blocks;
    ArrayList<Process> process; //sorted by arrival time
    int currTime = 0;

    public ShortestRemainingTimeFirstSchedule(ArrayList<Process> pr) {

        process = pr;
        blocks = "";
        currTime = pr.get(0).arrivalTime;
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

        Process p2;
        while (true) {
            ArrayList<Process> willArrive = new ArrayList<>();
            Process p = process.get(0);
            int i = 1;
            //System.out.println(p.index);

            if (i < process.size()) {
                p2 = process.get(i);

                while (((currTime + p.burstTime) > p2.arrivalTime) && i < process.size()) {
                    p2 = process.get(i);
                    willArrive.add(p2);
                    i++;
                }

            }

            if (!willArrive.isEmpty()) {
                Collections.sort(willArrive, Process.burstTimeArrTimeCompare);
                Process q = willArrive.get(0);
                //System.out.println(q.index);
                if ((p.burstTime > q.burstTime)) {
                    if (currTime > q.arrivalTime) { //both have entered
                        blocks = blocks + currTime + " " + q.index + " " + q.burstTime + "X" + "\n";
                        currTime = currTime + q.burstTime;
                        process.remove(q);
                        //p = q;
                        process.remove(p);
                        process.add(0, p);
                    } else {
                        blocks = blocks + currTime + " " + p.index + " " + (q.arrivalTime - currTime) + "\n";
                        p.setBurstTime(p.burstTime - (q.arrivalTime - currTime));
                        currTime = q.arrivalTime;
                        p = q;
                        process.remove(q);
                        process.add(0, p);
                    }

                } else {
                    blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                    currTime = currTime + p.burstTime;
                    process.remove(p);
                    p = q;
                    process.remove(q);
                    process.add(0, p);
                }
            } else {
                System.out.println("No interruptions");
                blocks = blocks + currTime + " " + p.index + " " + p.burstTime + "X" + "\n";
                currTime = currTime + p.burstTime;
                process.remove(p);
            }

            if (process.isEmpty()) {
                break;

            }

        }
    }

    //Collections.sort(process, Process.);
    @Override
    public String getBlocks() {
        return blocks;
    }

}
