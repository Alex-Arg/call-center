package com.almundo.callcenter;

import com.almundo.callcenter.core.services.PriorityCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Initializer implements CommandLineRunner {

    @Autowired
    private PriorityCallService service;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Initializer.class, args);
    }

    /**
     * Init poinr for the app.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        int callQty = 50;
        if (args.length == 1) {
            callQty = Integer.valueOf(args[0]);
        }

        service.process(callQty);

        System.out.println("Process ended");
    }
}
