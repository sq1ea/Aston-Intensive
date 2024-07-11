package org.example;

import org.example.context.IntensiveContext;

public class RunDI {

    private static final String SCAN_PATH = "org.example";

    public static void main(String[] args) {
        IntensiveContext context = new IntensiveContext(SCAN_PATH);
        ExampleDI example = context.getObject(ExampleDI.class);
        example.run();
    }
}


