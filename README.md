# MultithreadedGradeServer

This is a Java program that runs a multithreaded client-server system. The **client** sends a numeric percentage grade to the **server**, which responds with the corresponding letter grade.

This project was created as an assignment for one of my classes.

---

## Features

- Multithreaded server handling multiple clients concurrently
- Client can send multiple grades until quitting
- Server converts numeric grades to letter grades (A, B, C, etc.)
- Clean socket communication with data streams
- Graceful disconnection handling

---


---

## Requirements

- Java 8 or higher
- Terminal or command prompt for running the app

---

## How to Compile and Run

### Compile

From the root of the project (where `src` is located), run:

```bash
javac src/main/java/Lab10/*.java
```

### Run

Run this command for the server

```bash
java -cp src/main/java Lab10.Server 1234
```

Run this command for the client in a new terminal

```bash
java -cp src/main/java Lab10.Client localhost 1234
```
### How to Run

Input numeric percentages into the client terminal to receive letter grades from the server. Enter -1 to quit the client.
