import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Process> completedProcesses;
    private double cpuUtilization;

    public Statistics(){
        completedProcesses = new ArrayList<>();
    }

    //Add completed process for statistics calculation
    public void addProcess(Process p){
        completedProcesses.add(p);
    }

    //Set CPU utilization from CPU object
    public void setCpuUtilization (double utilization){
        this.cpuUtilization = utilization;
    }

    //Calculate and print statistics
    public void printStatistics(){
        double totalWaitTime = 0;
        double totalResponseTime = 0;
        double totalTurnaroundTime = 0;

        for (Process p : completedProcesses){
            p.calculateStats();
            totalWaitTime += p.getWaitTime();
            totalResponseTime += p.getResponseTime();
            totalTurnaroundTime += p.getTurnaroundTime();
        }

        int numProcesses = completedProcesses.size();

        //print summary statistics
        System.out.println("========================================================");
        System.out.printf("Average CPU usage: %.2f%%\n", cpuUtilization * 100);
        System.out.printf("Average waiting time: %.2f\n", totalWaitTime / numProcesses);
        System.out.printf("Average response time: %.2f\n", totalResponseTime / numProcesses);
        System.out.printf("Average turnaround time: %.2f\n", totalTurnaroundTime / numProcesses);
        System.out.println("=======================================================");
    }
}
