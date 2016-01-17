package cs431proj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CS431Proj1 {

    public void FCFS(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int index = 1;
        ArrayList<Task> tasks = new ArrayList<>(); // storage for the tasks
        while (input.hasNext()) // read CPU burst times of tasks 
        {
            String job = input.next();
            int burst = input.nextInt();
            tasks.add(new Task(job, burst));       // add a new task to the array
            index++;
        }
        int numTasks = tasks.size();               // total # of tasks
        int avg = 0;
        int time = 0;                              // CPU time
        System.out.println("First Come First Serve (FCFS): ");
        while (!tasks.isEmpty()) // take the tasks in the natural order
        {                                          // and throw them into the schedule
            Task t = tasks.remove(0);
            time += t.burst;
            avg += time;
            System.out.print(t.job + ": " + time + "\n");
        }
        System.out.println("Average TurnAround time for FCFS is: = "
                + ((1.0 * avg) / numTasks) + "\n");
    }

    public void SJF(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int index = 1;
        ArrayList<Task> tasks = new ArrayList<>();  // storage for tasks
        while (input.hasNext()) // read CPU burst times of tasks 
        {
            String job = input.next();
            int burst = input.nextInt();            // create a new task
            tasks.add(new Task(job, burst));        // and add it to an array
            index++;
        }
        int numTasks = tasks.size();                // total number of tasks

        Task[] tasksA = tasks.toArray(new Task[tasks.size()]); // convert ArrayList
        Comparator<Task> c = new Comparator<Task>() // into array
        {
            @Override
            public int compare(Task t1, Task t2) // comparator for sorting
            {
                return (t1.burst - t2.burst);
            }
        };
        Arrays.sort(tasksA, c);                     // sort tasks by burst times

        System.out.println("Shortest Job First (SJF): ");
        int avg = 0;
        int time = 0;
        for (int i = 0; i < tasksA.length; i++) {
            Task t = tasksA[i];
            time += t.burst;
            avg += time;
            System.out.print(t.job + ": " + time + "\n");

        }
        System.out.println("Average TurnAround time for SJF is:  = "
                + ((1.0 * avg) / numTasks) + "\n");
    }

    public void RR3(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int index = 1;
        ArrayList<Task> tasks = new ArrayList<>();  // read tasks from file
        while (input.hasNext()) {
            String job = input.next();
            int burst = input.nextInt();
            tasks.add(new Task(job, burst));        // add a new task to the array
            index++;
        }
        int tasksNo = tasks.size();                 // initial # of tasks
        int quanta = 3;
        System.out.println("Round Robin(time slice = 3)");
        int timeStamp = 0;
        double aveTurnaround = 0;
        while (!tasks.isEmpty()) // loop over all tasks
        {
            Task t = tasks.remove(0);              // get the first task from the queue
            System.out.print(t.job + ": ");
            if (t.burst > quanta) {
                timeStamp += quanta;
                t.burst -= quanta;
                tasks.add(t);                     // put this process back to queue
            } else {
                timeStamp += t.burst;
                aveTurnaround += timeStamp;
            }
            System.out.print(timeStamp + "\n");
        }
        aveTurnaround /= tasksNo;
        System.out.println("Average TurnAround time for RR(3) = " + aveTurnaround + "\n");
    }

    public void RR5(File file) throws FileNotFoundException {
        //File file = new File("testdata1.txt");
        Scanner input = new Scanner(file);
        int index = 1;
        ArrayList<Task> tasks = new ArrayList<>();  // read tasks from file
        while (input.hasNext()) {
            String job = input.next();
            int burst = input.nextInt();
            tasks.add(new Task(job, burst));    // add a new task to the array
            index++;
        }
        int tasksNo = tasks.size();             // initial # of tasks
        int quanta = 5;
        System.out.println("Round Robin(time slice = 5)");
        int timeStamp = 0;
        double aveTurnaround = 0;
        while (!tasks.isEmpty()) // loop over all tasks
        {
            Task t = tasks.remove(0);           // get the first task from the queue
            System.out.print(t.job + ": ");
            if (t.burst > quanta) {
                timeStamp += quanta;
                t.burst -= quanta;
                tasks.add(t);                   // put this process back to queue
            } else {
                timeStamp += t.burst;
                aveTurnaround += timeStamp;
            }
            System.out.print(timeStamp + "\n");
        }
        aveTurnaround /= tasksNo;
        System.out.println("Average TurnAround time for RR(5) = " + aveTurnaround + "\n");
    }
}
