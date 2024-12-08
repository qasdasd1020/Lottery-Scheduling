public class Process{
    //Reuqire attributes
    private int id;                     //Process ID
    private int arrivalTime;            //Arrival Time
    private int burstLength;            //CPU burst Length
    private int priority;               //Priority
    private int tickets;                //Number of Lottery tickets

    //Satistics tracking attributes
    private int waitTime;               //Time spent waiting
    private int responseTime;           //Time until first CPU burst
    private int turnaroundTime;         //Total time in system
    private int startTime;              //Time when process first starts
    private int finishTime;             //Time when process completes
    private boolean hasStarted;         //Flag for tracking first CPU burst

    //construct
    public Process (int id, int arrival, int burst, int priority){
        this.id = id;
        this.arrivalTime = arrival;
        this.burstLength = burst;
        this.priority = priority;
        this.hasStarted = false;
        //initialize statistics tracking variables
        this.startTime = -1;                //-1 indicates not started
        this.finishTime = -1;               //-1 indicates not finished
        this.waitTime = 0;
        this.responseTime = 0;
        this.turnaroundTime = 0;
    }

    //Getter
    public int getId() { return id; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstLength() { return burstLength; }
    public int getPriority() { return priority; }
    public int getTickets() { return tickets; }
    public int getWaitTime() { return waitTime; }
    public int getResponseTime() { return responseTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public int getStartTime() { return startTime; }
    public int getFinishTime() { return finishTime; }

    //setter
    public void setTickets(int tickets) { this.tickets = tickets; }
    public void setStartTime(int time){
        if (!hasStarted) {
            startTime = time;
            hasStarted = true;
        }
    }
    public void setFinishTime(int time) {this.finishTime = time;}

    //statistics calculation method
    public void calculateStats(){
        turnaroundTime = finishTime - arrivalTime;
        responseTime = startTime - arrivalTime;
        waitTime = turnaroundTime - burstLength;
    }
}