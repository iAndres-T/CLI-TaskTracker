import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
}
