package org.example.main;


import org.example.core.ApplicationContext;
import org.example.service.HelloService;
import org.example.service.Printer;

public class MainApp {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ApplicationContext();
        context.register(Printer.class, HelloService.class);

        HelloService helloService = context.getInstance(HelloService.class);
        helloService.sayHello();
    }
}
