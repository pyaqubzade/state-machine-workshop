package az.pashabank.trafficlight;

import az.pashabank.trafficlight.config.Configuration;
import az.pashabank.trafficlight.model.Event;
import az.pashabank.trafficlight.model.State;
import az.pashabank.trafficlight.service.Service;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Scanner;

@EnableAsync
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = Configuration.getLogger(Application.class);
    private static final Scanner scanner = new Scanner(System.in);

    private final Service service;

    public Application(Service service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var stateMachine = service.getStateMachine();
        stateMachine.start();

        while (stateMachine.getState().getId() == State.FINAL) {
            var input = scanner.nextLine();
            var event = Event.valueOf(input.toUpperCase());
            log.warn("Event: {}", event);
            var accepted = stateMachine.sendEvent(event);
            log.warn("Is accepted: {}", accepted);
        }

        stateMachine.stop();
        log.info("State machine stopped");
    }
}