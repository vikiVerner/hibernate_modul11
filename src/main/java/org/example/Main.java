package org.example;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:h2:./test1";
        new DatabaseInitService().createTable(url);
    }
}