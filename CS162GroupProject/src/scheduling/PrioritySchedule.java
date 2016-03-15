package scheduling;

import java.util.ArrayList;
import java.util.Collections;

public class PrioritySchedule implements Schedulers {

    private String blocks;
    Process current;
//    boolean first = true;
    int originalBT;
    ArrayList<Process> arrived = new ArrayList<>();

    public PrioritySchedule(ArrayList<Process> processes) {
        //Assumption: processes are sorted by arrivalTime
        blocks = "";
        int totalTimeElapsed = processes.get(0).arrivalTime;
        boolean hasBeenAdded = false;

        //First process
        current = processes.get(0);
        processes.remove(0);
        originalBT = current.burstTime;

        while (true) {
            if (!processes.isEmpty()) {
                while(!processes.isEmpty()) {
                    if (totalTimeElapsed >= processes.get(0).arrivalTime) {
                        arrived.add(processes.get(0));
                        processes.remove(0);
                        hasBeenAdded = true;
                    } else{ break;}
                }
                Collections.sort(arrived, Process.priorityCompare);
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
                        if (current.priority > arrived.get(0).priority) {
                            blocks += Integer.toString(totalTimeElapsed - (originalBT - current.burstTime)) + " " + current.index + " " + (originalBT - current.burstTime) + "\n";
                            arrived.add(current);
                            setCurrentProcess();
                            Collections.sort(arrived, Process.priorityCompare);
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

    public void setCurrentProcess() {
        current = arrived.get(0);
        originalBT = current.burstTime;
        arrived.remove(0);
        --current.burstTime;
    }

    @Override
    public String getBlocks() {
        return blocks;
    }
}
