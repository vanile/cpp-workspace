import java.util.concurrent.ThreadLocalRandom;

public class MPI_SampleProc extends MPI_Proc {
	
	private static final int REQ_TAG = 0;
	private static final int TASK_TAG = 1;
	private static final int RESULT_TAG = 2;
	
    public MPI_SampleProc(MPI_World world, int rank) {
        super(world, rank);
    }
    
	/**
	 * Structure is tree-like. Designed for 9 processors, extra processors are not used.
	 * *Should* handle any number of tasks
	 * 
	 * 	Root(8)    Generator (0)
	 * 					|
	 * 				  Comm (1)
	 *               /       \
	 *        Comm (2)        Comm (3)
	 *       /       \       /        \
	 *    Work(4)  Work(5) Work(6)  Work(7)
	 */
    public void exec(int argc, String argv[]) throws InterruptedException
    {
        MPI_Init(argc, argv);
        MPI_Status status = new MPI_Status();
        
        int rank = MPI_Comm_rank(MPI_COMM_WORLD);
        int size = MPI_Comm_size(MPI_COMM_WORLD);
        int energy;
        
        String[] buffer = new String[] { "buffer", "buf" }; //typically just used for requests
        String[] task = new String[3];

        //root = 8, generator = 0, communicators = [1, 2, 3], workers = [4, 5, 6, 7]
        if (rank == 0) { // Generator
        	String[] availableTasks = new String[30];
            for (int i = 0; i < availableTasks.length; i++) {
            	availableTasks[i] = Integer.toString(i + 1);
            }
            
            int nextComm = rank + 1;
            //generate tasks
        	for (int i = 0; i < availableTasks.length; i++) {
        		MPI_Recv(buffer, 2, MPI_STRING, nextComm, REQ_TAG, MPI_COMM_WORLD, status);
        		String[] sendTask = new String[] { "task", "Task " + availableTasks[i], "" };
        		MPI_Send(sendTask, 2, MPI_STRING, nextComm, TASK_TAG, MPI_COMM_WORLD);
        	}
        	
        	MPI_Send(availableTasks, 30, MPI_STRING, 8, TASK_TAG, MPI_COMM_WORLD);  
        	//generate and send empty and energy
            int randomEnergy = ThreadLocalRandom.current().nextInt(0, 101);
        	String[] empty = new String[] { "empty", Integer.toString(randomEnergy) };
        	System.out.println("Total Energy: " + empty[1]);
        	
        	MPI_Recv(buffer, 2, MPI_STRING, nextComm, REQ_TAG, MPI_COMM_WORLD, status);
        	MPI_Send(empty, 2, MPI_STRING, nextComm, TASK_TAG, MPI_COMM_WORLD);   	
        } else if (rank == 1 || rank == 2 || rank == 3) { // Comm
        	boolean emptyTaskFound = false;
    		int childL = rank * 2;
    		int childR = childL + 1;
        	int parent;
    		if (rank == 1) {
    			parent = 0;
    		} else {
    			parent = 1;
    		}

        	while (true) {
        		// empty task found at the end of previous loop (right child control section)
        		if (emptyTaskFound) {
        			//receive request and send empty to child
        			MPI_Recv(buffer, 2, MPI_STRING, childL, REQ_TAG, MPI_COMM_WORLD, status);
                	MPI_Send(task, 2, MPI_STRING, childL, TASK_TAG, MPI_COMM_WORLD);
                	
                	break;
        		}

        		//request and receive task from parent
        		MPI_Send(buffer, 2, MPI_STRING, parent, REQ_TAG, MPI_COMM_WORLD);
            	MPI_Recv(task, 2, MPI_STRING, parent, TASK_TAG, MPI_COMM_WORLD, status);
            	
            	//communication control for left child
            	if (task[0].equals("empty")) {
            		energy = Integer.parseInt(task[1]);
            		int randomNum = ThreadLocalRandom.current().nextInt(0, energy + 1);
            		energy -= randomNum;
            		task[1] = Integer.toString(randomNum);
            		

        			MPI_Recv(buffer, 2, MPI_STRING, childL, REQ_TAG, MPI_COMM_WORLD, status);
                	MPI_Send(task, 2, MPI_STRING, childL, TASK_TAG, MPI_COMM_WORLD); 
                	
                	//set energy as the other split
                	task[1] = Integer.toString(energy);
            		emptyTaskFound = true;
            	} else {
            		//task is a normal task, just send to child
        			MPI_Recv(buffer, 2, MPI_STRING, childL, REQ_TAG, MPI_COMM_WORLD, status);
                	MPI_Send(task, 2, MPI_STRING, childL, TASK_TAG, MPI_COMM_WORLD);
            	}
            	
            	//communication control for right child
            	if (!emptyTaskFound) {
            		MPI_Send(buffer, 2, MPI_STRING, parent, REQ_TAG, MPI_COMM_WORLD);
                	MPI_Recv(task, 2, MPI_STRING, parent, TASK_TAG, MPI_COMM_WORLD, status);
                	
                	if (task[0].equals("empty")) {
                		energy = Integer.parseInt(task[1]); //get energy from task
                		int randomNum = ThreadLocalRandom.current().nextInt(0, energy + 1); //generate random num between 0 and energy
                		energy -= randomNum; //split original energy amount by the random
                		task[1] = Integer.toString(randomNum); //energy to be sent is the random amount
                		
                		MPI_Recv(buffer, 2, MPI_STRING, childR, REQ_TAG, MPI_COMM_WORLD, status);
                    	MPI_Send(task, 2, MPI_STRING, childR, TASK_TAG, MPI_COMM_WORLD);
                    	
                    	//set energy as the other split
                    	task[1] = Integer.toString(energy);
                    	emptyTaskFound = true;
                	} else {
                    	MPI_Recv(buffer, 2, MPI_STRING, childR, REQ_TAG, MPI_COMM_WORLD, status);
                    	MPI_Send(task, 2, MPI_STRING, childR, TASK_TAG, MPI_COMM_WORLD);
                	}
            	} else {
        			MPI_Recv(buffer, 2, MPI_STRING, childR, REQ_TAG, MPI_COMM_WORLD, status);
                	MPI_Send(task, 2, MPI_STRING, childR, TASK_TAG, MPI_COMM_WORLD);
  
                	break;
            	}
        	}
        } else if (rank == 4 || rank == 5) { // Worker
        	doWork(2, buffer, task, status);
        } else if (rank == 6 || rank == 7) { // Worker
        	doWork(3, buffer, task, status);
        } else if (rank == 8) { // Root
        	MPI_Recv(buffer, 30, MPI_STRING, 0, TASK_TAG, MPI_COMM_WORLD, status);
        	
        	//receive task results
        	int numTasks = buffer.length;
        	while (numTasks >= 0) {
        		MPI_Recv(buffer, 2, MPI_STRING, MPI_ANY_SOURCE, RESULT_TAG, MPI_COMM_WORLD, status);
        		System.out.println("Worker " + status.MPI_SOURCE + " completed " + buffer[1]);
        	}
        	//receiving empty results from workers
        	for (int i = 4; i < 8; i++) {
        		MPI_Recv(buffer, 2, MPI_STRING, MPI_ANY_SOURCE, RESULT_TAG, MPI_COMM_WORLD, status);
        		System.out.println("Worker " + status.MPI_SOURCE + " sending energy " + buffer[1] + ", goodbye!");
        	}
        } 

        //printTerminating();
        MPI_Finalize();
    }
  
    /**
     * Worker function
     */
    private void doWork(int parent, String[] buffer, String[] tasks, MPI_Status status) {
    	while (true) {
    		MPI_Send(buffer, 2, MPI_STRING, parent, REQ_TAG, MPI_COMM_WORLD);
        	MPI_Recv(tasks, 2, MPI_STRING, parent, TASK_TAG, MPI_COMM_WORLD, status);
        	
        	if (tasks[0].equals("empty")) {
        		//send empty result to root
        		int root = 8;
        		//System.out.println("Worker " + this.rank() + " sending energy " + tasks[1] + ", goodbye!");
        		MPI_Send(tasks, 2, MPI_STRING, root, RESULT_TAG, MPI_COMM_WORLD);
        		
        		break; 
        	} 
        	//System.out.println("Worker " + this.rank() + " completed " + tasks[1]);
    	}
    }
    
    private void printTerminating() {
    	System.out.println("Processor " + this.rank() + " is terminating...");
    }
    
    private void printInfo(String message) {
    	String procType = "";
    	int myRank = this.rank();
    	if (myRank == 8) {
    		//procType = "Root";
    	} else if (myRank == 0) {
    		procType = "Generator";
    	} else if (myRank == 1 || myRank == 2 || myRank == 3) {
    		procType = "Comm";
    	} else {
    		procType = "Worker";
    	}
    	
    	System.out.println(procType + " " + myRank + " " + message);
    }
    
    private String generateInfo(int rank, String message) {
    	String procType = "";
    	int myRank = rank;
    	if (myRank == 8) {
    		procType = "Root";
    	} else if (myRank == 0) {
    		procType = "Generator";
    	} else if (myRank == 1 || myRank == 2 || myRank == 3) {
    		procType = "Comm";
    	} else {
    		procType = "Worker";
    	}
    	
    	return procType + " " + myRank + " " + message;
    }
}
