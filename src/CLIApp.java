public class CLIApp {
    public static void main(String[] args) {

        if(args.length < 1){
            System.out.println("""
                    CLIApp add <description>
                    CLIApp update <id> <new description>
                    CLIApp delete <id>
                    CLIApp mark-in-progress <id>
                    CLIApp mark-done <id>
                    CLIApp list
                    CLIApp list todo
                    CLIApp list in-progress
                    CLIApp list done""");
            return;
        }

        String command = args[0];
        switch (command) {
            case "add" -> {
                if (args.length < 2) {
                    System.out.println("Usage: CLIApp add <description>");
                    return;
                }
                System.out.println("Task added");
            }
            case "update" -> {
                if(args.length < 3){
                    System.out.println("Usage: CLIApp update <id> <new description>");
                    return;
                }
                System.out.println("Task updated successfully (ID: " + args[1] + ")");
            }
            case "delete" -> {
                if(args.length < 2){
                    System.out.println("Usage: CLIApp delete <id>");
                    return;
                }
                System.out.println("Task deleted successfully (ID: " + args[1] + ")");
            }
            case "mark-in-progress" -> {
                if(args.length < 2){
                    System.out.println("Usage: CLIApp mark-in-progress <id>");
                    return;
                }
                System.out.println("Task marked as in progress (ID: " + args[1] + ")");
            }
            case "mark-done" -> {
                if(args.length < 2){
                    System.out.println("Usage: CLIApp mark-done <id>");
                    return;
                }
                System.out.println("Task marked as done (ID: " + args[1] + ")");
            }
            case "list" -> {
                if(args.length < 2){
                    System.out.println("List All");
                }
                else{
                    Status filterStatus;
                    try {
                        filterStatus = Status.valueOf(args[1].toUpperCase().replace("-", "_"));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status: " + args[1]);
                        return;
                    }
                    System.out.println("List by " + args[1]);
                }
            }
            default -> System.out.println("Unknown command: " + command);
        }


    }
}
