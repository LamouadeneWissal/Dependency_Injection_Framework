package org.example.service;


import org.example.annotations.Component;

@Component
public class Printer implements IPrinter {
    public void print(String message) {
        System.out.println("Printer: " + message);
    }
}
