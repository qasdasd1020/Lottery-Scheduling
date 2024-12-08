import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryScheduler {
    private List<Process> readyQueue;
    private int maxTickets;                     //M
    private int minPriority;                    //SPN
    private int maxPriority;                    //LPN
    private int totalTickets;
    private Random random;

    public LotteryScheduler(int maxTickets, int minPriority, int maxPriority){
        this.maxTickets = maxTickets;
        this.minPriority = minPriority;
        this.maxPriority = maxPriority;
        this.readyQueue = new ArrayList<>();
        this.random = new Random();
        this.totalTickets = 0;
    }

    public void addProcess (Process process){               //Add process to ready queue and assign tickets
        // Calculate tickets using (1-(P - SPN) / (LPN - SPN + 1)) * N
        int priority = process.getPriority();
        int tickets = (int)((1.0 - (double)(priority - minPriority) / (maxPriority - minPriority + 1)) * maxTickets);

        process.setTickets(tickets);
        totalTickets += tickets;
        readyQueue.add(process);

        readyQueue.sort((p1,p2) -> p2.getTickets() - p1.getTickets());
    }

    //Select next process using lottery scheduling
    public Process getNextProcess(){
        if (readyQueue.isEmpty() || totalTickets == 0){
            return null;
        }

        //Generate random winner ticket
        int winner = random.nextInt(totalTickets);

        //Find the winning process
        int counter = 0;
        int current = 0;

        while (current < readyQueue.size()){
            Process process = readyQueue.get(current);
            counter += process.getTickets();

            if (counter > winner){
                readyQueue.remove(current);
                totalTickets -= process.getTickets();
                return process;
            }
            current ++;
        }
        
        return null;
    }

    public boolean hasProcesses(){
        return !readyQueue.isEmpty();
    }

    public void printReadyQueueState(){
        System.out.println("\nReady Queue State: ");
        System.out.println("Total tickets: " + totalTickets);
        for (Process p: readyQueue) {
            System.out.printf("Process %d: %d tickets (Priority: %d)\n", p.getId(), p.getTickets(), p.getPriority());
        }
        System.out.println();
    }
}
