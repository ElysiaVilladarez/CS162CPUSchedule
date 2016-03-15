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
public class Process{

    public int arrivalTime, burstTime, priority, index;

    public Process(int aT, int bT, int p, int i) {
        arrivalTime = aT;
        burstTime = bT;
        priority = p;
        index = i;
    }

    public void setBurstTime(int newBt) {
        burstTime = newBt;
    }
    public static Comparator<Process> arrivalTimeCompare = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            int arrival1 = o1.arrivalTime;
            int arrival2 = o2.arrivalTime;

            return arrival1 - arrival2;
        }
    };
    public static Comparator<Process> arrivalTimeCompareBT = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
//            int arrival1 = o1.arrivalTime;
//            int arrival2 = o2.arrivalTime;

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            if (o1 == o2 || o1.arrivalTime == o2.arrivalTime) {
                if (o1.burstTime < o2.burstTime) {
                    return BEFORE;
                } else if (o1.burstTime > o2.burstTime) {
                    return AFTER;
                } else return EQUAL;
            }
            if (o1.arrivalTime < o2.arrivalTime) {
                return BEFORE;
            }
            if (o1.arrivalTime > o2.arrivalTime) {
                return AFTER;
            }
            
            return EQUAL;
        }
    };
    public static Comparator<Process> arrivalTimeCompareP = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            if (o1 == o2 || o1.arrivalTime == o2.arrivalTime) {
                if (o1.priority < o2.priority) {
                    return BEFORE;
                } else if (o1.priority > o2.priority) {
                    return AFTER;
                } else return EQUAL;
            }
            if (o1.arrivalTime < o2.arrivalTime) {
                return BEFORE;
            }
            if (o1.arrivalTime > o2.arrivalTime) {
                return AFTER;
            }
            
            return EQUAL;
        }
    };
    public static Comparator<Process> priorityCompare = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            int pri1 = o1.priority;
            int pri2 = o2.priority;

            return pri1 - pri2;
        }
    };
    public static Comparator<Process> burstTimeCompare = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            int pri1 = o1.burstTime;
            int pri2 = o2.burstTime;

            return pri1 - pri2;
        }
    };
    public static Comparator<Process> burstTimeArrTimeCompare = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            int pri1 = o1.burstTime + o1.arrivalTime;
            int pri2 = o2.burstTime + o2.arrivalTime;

            return pri1 - pri2;
        }
    };

}
