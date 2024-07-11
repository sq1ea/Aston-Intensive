package org.example;

import org.example.component.IntensiveComponent;

@IntensiveComponent
public class ExampleDI {

    String name;


    public ExampleDI() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("Example.Class is running");
    }
}

