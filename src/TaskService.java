import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final Path FILE_PATH = Path.of("task.json");
    private List<Task> tasks;

    public TaskService(){
        this.tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        List<Task> json_tasks = new ArrayList<>();

        if(!Files.exists(FILE_PATH))
            return new ArrayList<>();

        try {
            String json = Files.readString(FILE_PATH);
            String[] taskList = json.replace("[", "").replace("]", "").split("},");

            for (String t : taskList) {
                if(!t.endsWith("}")){
                    t += "}";
                    json_tasks.add(Task.fromJson(t));
                }
                else
                    json_tasks.add(Task.fromJson(t));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return json_tasks;
    }
    
    public void saveTasks() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[\n");
    	
    	for(int i = 0; i < tasks.size(); i++) {
    		sb.append(tasks.get(i).toJson());
    		if(i < tasks.size() - 1)
    			sb.append(",\n");
    	}
    	sb.append("\n]");
    	String jsonContent = sb.toString();
    	try {
    		Files.writeString(FILE_PATH, jsonContent);
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void listTasks(String type) {
    	for(Task task : tasks) {
    		String status = task.getStatus().toString().strip();
    		if(type.equals("All") || status.equals(type))
    			System.out.println(task.toString());
    	}
    }
    
    public void addTask(String description) {
    	Task newTask = new Task(description);
    	tasks.add(newTask);
    	System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
    }
    
    public void updateTask(String id, String newDescription) {
    	Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
    	task.updateDescription(newDescription);
    }
    
    public void markDone(String id) {
    	Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
    	task.markDone();
    }
    
    public void markInProgress(String id) {
    	Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
    	task.markInProgress();
    }
    
    public void deleteTask(String id) {
    	Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
    	tasks.remove(task);
    }
    
    public Optional<Task> findTask(String id){
    	return tasks.stream().filter((task) -> task.getId() == Integer.parseInt(id)).findFirst();
    }
}
