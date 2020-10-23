# Get It Done!

Get It Done! is a program with a text-based user interface 
helping you to keep track of your tasks for different projects.

## Installation

1. Clone the repository to your computer.
2. Compile files in that folder by running 'javac -d . src/main/java/main/java/toDoList/*.java' in Terminal.
3. Run 'java main/java/toDoList/Main' in Terminal.

## Usage
Once compiled and launched the program is straightforward to use.
The text UI will guide you through different steps.
Detailed User Manual can be found below.

#####Important: 

The program saves your tasks between sessions into the
file named 'savedTasks.txt'(comes together with source files in the repository).

The program also saves the log for completed tasks into the
file named 'logCompletedTasks.txt'(also included in the repository).

##Functionalities and User Manual

The main menu of the program includes the following commands:
- (1) Show task list(by project or date)
- (2) Add new task
- (3) Edit task(update, mark as done, remove)
- (4) View log of completed tasks
- (5) Save and quit

#### Displaying tasks(1)
When displaying tasks command is requested, user will be prompted to choose
in what order he/she wants to see the tasks displayed.
Based on user's choice, tasks will be displayed sorted by date or project.

#### Adding a new task(2)
When adding a task, user will be prompted to enter task title, due date and project
a task belongs to. Based on that information a new task will be created.

NB: Task title and project name cannot be empty. Due date cannot be set to earlier than today.

#### Editing tasks(3)
When selecting this command, user will first be asked to select a task he wants to edit from
the list. Then user will choose an action he/she wants to perform with the selected task.
Possible actions include:
- (1) Update the name
- (2) Update the due date
- (3) Update the project name
- (4) Mark as done
- (5) Remove
- (-1) go to the main command menu

NB: When marked as done, a task will be saved into the log file.

#### Viewing the log of completed tasks(4)
User can view all the tasks he had ever completed for all the projects.

#### Saving changes and exiting the program(5)
When selecting this command, all the changes to tasks will be saved.
Ongoing tasks will be saved into 'savedTasks.txt' file while
completed tasks are saved into 'logCompletedTasks.txt' file.

NB: When next time, user opens the program all the tasks will be uploaded automatically.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
