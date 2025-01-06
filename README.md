# CLI Task Tracker

CLI Task Tracker is a command-line application for managing tasks. It allows users to add, update, delete, and list tasks, as well as mark tasks as in progress or done.
https://roadmap.sh/projects/task-tracker

## Features

- Add a new task
- Update an existing task's description
- Delete a task
- Mark a task as in progress
- Mark a task as done
- List tasks with optional filtering by status

## Usage

To use the CLI Task Tracker, run the `CLIApp` class with the appropriate command and arguments.

### Commands

- `add <description>`: Adds a new task with the given description.
- `update <id> <new description>`: Updates the description of the task with the specified ID.
- `delete <id>`: Deletes the task with the specified ID.
- `mark-in-progress <id>`: Marks the task with the specified ID as in progress.
- `mark-done <id>`: Marks the task with the specified ID as done.
- `list`: Lists all tasks.
- `list todo`: Lists tasks with status "Todo".
- `list in-progress`: Lists tasks with status "In progress".
- `list done`: Lists tasks with status "Done".

### Example

```sh
java CLIApp add "New Task"
java CLIApp update 1 "Updated Task Description"
java CLIApp delete 1
java CLIApp mark-in-progress 2
java CLIApp mark-done 2
java CLIApp list
java CLIApp list todo
```

## Code Structure

- `Task.java`: Defines the `Task` class with methods for converting to/from JSON, updating status, and updating description.
- `TaskService.java`: Provides methods for managing tasks, including loading from and saving to a JSON file.
- `Status.java`: Enum representing the status of a task (TODO, IN_PROGRESS, DONE).
- `CLIApp.java`: Main class for the command-line interface.

## JSON File

Tasks are stored in a JSON file (`task.json`) with the following structure:

```json
[
  {
    "id": "1",
    "description": "Sample Task",
    "status": "Todo",
    "createdAt": "2025-01-06T11:37:53.9851241",
    "updatedAt": "2025-01-06T12:11:39.64341"
  }
]
```

## License
This project is licensed under the MIT License.

## Installation Steps

1. Clone the repository:
  ```sh
  git clone https://github.com/iAndres-T/CLI-TaskTracker
  ```

2. Navigate to the project directory:
  ```sh
  cd CLI-TaskTracker
  ```

3. Compile the Java files:
  ```sh
  javac -d bin src/*.java
  ```

4. Run the application:
  ```sh
  java -cp bin Main
  ```