package org.example.service;


import org.example.annotations.Component;
import org.example.annotations.Inject;

@Component
public class HelloService {
    @Inject
    private IPrinter printer; // Injection par attribut

    private IPrinter constructorInjectedPrinter;

    @Inject
    public HelloService(IPrinter printer) { // Injection par constructeur
        this.constructorInjectedPrinter = printer;
    }

    private IPrinter setterInjectedPrinter;

    @Inject
    public void setPrinter(IPrinter printer) { // Injection par setter
        this.setterInjectedPrinter = printer;
    }

    public void sayHello() {
        printer.print("Hello from attribute injection!");
        constructorInjectedPrinter.print("Hello from constructor injection!");
        setterInjectedPrinter.print("Hello from setter injection!");
    }
}
