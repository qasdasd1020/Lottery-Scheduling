import java.util.Queue;

public class Assignment1{
    public static void main(String args[]){
        if (args.length != 4) {
            System.out.println("Usage: java Assignment1 <input_file> <M> <SPN> <LPN>");
            System.out.println("Example: java Assignment1 assignment1.txt 100 -20 20");
            System.exit(1);
        }

        try {
            String inputFile = args[0];
            int maxTickets = Integer.parseInt(args[1]);         //M
            int minPriority = Integer.parseInt(args[2]);        //SPN
            int maxPriority = Integer.parseInt(args[3]);        //LPN

            ProcessFileReader reader = new ProcessFileReader(inputFile);
            Queue<Process> processes = reader.readProcesses();

            System.out.println("Lottery Scheduling algorithm");
            System.out.println("========================================================");

            LotteryScheduler scheduler = new LotteryScheduler(maxTickets, minPriority, maxPriority);

            Statistics stats = new Statistics();

            CPU cpu = new CPU(processes, scheduler, stats);

            cpu.run();

            stats.printStatistics();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format in command line arguments");
            System.exit(1);
        }
    }
}