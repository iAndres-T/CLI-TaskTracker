import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private static int lastId = 0;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description){
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public static Task fromJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] jsonArray = json.split(",");

        String id = jsonArray[0].split(":")[1].strip();
        String description = jsonArray[1].split(":")[1].strip();
        String status = jsonArray[2].split(":")[1].strip();
        String created = jsonArray[3].split("[a-z]:")[1].strip();
        String updated = jsonArray[4].split("[a-z]:")[1].strip();

        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.status = Status.valueOf(status.toUpperCase().replace(" ", "_"));
        task.createdAt = LocalDateTime.parse(created, formatter);
        task.updateAt = LocalDateTime.parse(updated, formatter);

        if(Integer.parseInt(id) > lastId)
            lastId = Integer.parseInt(id);

        return task;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
