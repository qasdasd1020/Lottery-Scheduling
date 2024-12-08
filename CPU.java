import java.util.Queue;

public class CPU {
    private Queue<Process> arrivingProcesses;               //Queue of processes yet to arrive
    private LotteryScheduler scheduler;                     //Scheduler object
    private Statistics stats;                               //Statistics object
    private Process currentProcess;                         //Currently running process
    private int clock;                                      //system clock
    private int remainingBurst;                             //Remaining burst time of current process
    private int busyTime;                                  //time cpu was busy (for utilization)


    public CPU (Queue<Process> processes, LotteryScheduler scheduler, Statistics stats){
        this. arrivingProcesses = processes;
        this. scheduler = scheduler;
        this. stats = stats;
        this. clock = 0;
        this. busyTime = 0;
        this. currentProcess = null;
    }
    
    public void run(){        
        //loop until all processes are complete
        while (!arrivingProcesses.isEmpty() || scheduler.hasProcesses() || currentProcess != null) {
            checkArrivingProcesses();

            if (currentProcess == null){
                currentProcess = scheduler.getNextProcess();
                if(currentProcess != null){
                    currentProcess.setStartTime(clock);
                    remainingBurst = currentProcess.getBurstLength();
                    printProcessRunning();
                } else {
                    printCPUIdle();
                    clock++;
                    continue;
                }
            }

            if (currentProcess != null){
                printProcessRunning();
                remainingBurst--;
                busyTime++;

                if (remainingBurst == 0){
                    currentProcess.setFinishTime(clock + 1);
                    printProcessFinished();
                    stats.addProcess(currentProcess);
                    currentProcess = null;
                }
            }

            clock++;
        }

        printSimulationEnd();
        stats.setCpuUtilization((double)busyTime / clock);
    }

    private void checkArrivingProcesses(){
        while (!arrivingProcesses.isEmpty() && arrivingProcesses.peek().getArrivalTime() <= clock){
            Process process = arrivingProcesses.remove();
            scheduler.addProcess(process);
        }
    }

    private void printProcessRunning(){
        System.out.printf("<system time %d> process %d is running\n", clock, currentProcess.getId());
    }

    private void printProcessFinished(){
        System.out.printf("<system time %d> process %d is finished....\n", clock + 1, currentProcess.getId());
    }

    private void printCPUIdle(){
        System.out.printf("<system time %d> CPU is idle\n", clock);
    }

    private void printSimulationEnd(){
        System.out.printf("<system time %d> All processes finished...........\n", clock);
    }
}
