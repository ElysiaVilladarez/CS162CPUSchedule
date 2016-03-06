package scheduling;

import java.util.ArrayList;
import java.util.Collections;

public class PrioritySchedule implements Schedulers {

    private String blocks;

    public PrioritySchedule(ArrayList<Process> processes) {
        //Assumption: processes are sorted by arrivalTime
        blocks = "";
        ArrayList<Process> arrived = new ArrayList<>();
        int totalTimeElapsed = processes.get(0).arrivalTime;
        Process current = null;
        boolean hasBeenAdded = false;
        boolean first = true;
        int originalBT = 0;

        while (true) {

            if (!processes.isEmpty()) {
                if (totalTimeElapsed >= processes.get(0).arrivalTime) {
                    Process arr = processes.get(0);
                    arrived.add(arr);
                    processes.remove(0);
                    hasBeenAdded = true;
                    Collections.sort(arrived, Process.priorityCompare);
                }
            }

            if (first && hasBeenAdded) {
                current = arrived.get(0);
                originalBT = current.burstTime;
                arrived.remove(0);
                hasBeenAdded = false;
                first = false;
            }

            if (current != null) {
                if (current.burstTime == 0) {
                    blocks += Integer.toString(totalTimeElapsed - (originalBT - current.burstTime)) + " " + current.index + " " + (originalBT - current.burstTime) + "X\n";
                    if (arrived.isEmpty() && processes.isEmpty()) {
                        break;
                    } else {
                        current = arrived.get(0);
                        originalBT = current.burstTime;
                        arrived.remove(arrived.get(0));
                        --current.burstTime;
                    }
                } else {

                    if (hasBeenAdded) {
                        if (current.priority > arrived.get(0).priority) {
                            blocks += Integer.toString(totalTimeElapsed - (originalBT - current.burstTime)) + " " + current.index + " " + (originalBT - current.burstTime) + "\n";
                            arrived.add(current);
                            current = arrived.get(0);
                            originalBT = current.burstTime;
                            arrived.remove(arrived.get(0));
                            --current.burstTime;
                            Collections.sort(arrived, Process.priorityCompare);
                        } else {
                            --current.burstTime;
                        }
                        hasBeenAdded = false;
                    } else {
                        --current.burstTime;
                    }
                }
            }
            totalTimeElapsed += 1;
        }

    }

    @Override
    public String getBlocks() {
        return blocks;
    }
}
