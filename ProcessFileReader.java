import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProcessFileReader {
    private String filename;

    public ProcessFileReader(String filename) {
        this.filename = filename;
    }

    public Queue<Process> readProcesses(){
        Queue<Process> processes = new LinkedList<>();

        try{
            Scanner scanner = new Scanner (new File(filename));

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length >= 4){
                    int id = Integer.parseInt(parts[0]);
                    int arrival = Integer.parseInt(parts[1]);
                    int burst = Integer.parseInt(parts[2]);
                    int priority = Integer.parseInt(parts[3]);

                    processes.add (new Process(id, arrival, burst, priority));
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e){
            System.err.println("Error parsing numbers in input file: " + e.getMessage());
            System.exit(1);
        }
        return processes;
    }
}
