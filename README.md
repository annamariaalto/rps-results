# RPS - results

RPS-results will show results for rock-paper-scissors -game.
The Maven project uses Java 11.
The chosen library for the project is Jackson because of its good documentation and high performance.
This project is using only the other endpoint (/rps/history).

## The Usage

The main purpose of the project was to implement the project as a SpringBoot Application running at local host 8080.
However, at this point, the project is only running on IDE.

To run to project, run the Main class in App.java.
The project will ask user’s input which can be entered to IDE’s console.
The project will ask further questions. The results will be displayed on the IDE’s console.

## The tests

No test have been implemented for the project yet.

## To Do

The following points are yet to be implemented.

- Implement the other section of the assignment with Websocket.
- Bind Maven project to SpringBoot Application to run it on local host.
- Improve the project structure to have separate classes for model, view and controller (MVC design pattern).
- Create method to validate the user’s input.
