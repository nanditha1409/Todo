package org.example;

import java.io.*;
import java.util.*;

public class App {

    static ArrayList<String> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            System.out.println("\nTO-DO LIST");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    completeTask();
                    break;

                case 4:
                    saveTasks();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter task: ");
        String task = sc.nextLine();
        tasks.add(task);
        saveTasks();
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void completeTask() {
        viewTasks();

        System.out.print("Enter completed task number: ");
        int num = sc.nextInt();

        if (num > 0 && num <= tasks.size()) {
            tasks.remove(num - 1);
            saveTasks();
            System.out.println("Task completed!");
        }
    }

    static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (String task : tasks) {
                writer.println(task);
            }
        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void loadTasks() {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                tasks.add(fileScanner.nextLine());
            }
        } catch (Exception e) {

        }
    }
}