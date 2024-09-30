package az.pashabank.trafficlight.service;

import az.pashabank.trafficlight.model.Event;
import az.pashabank.trafficlight.model.State;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@org.springframework.stereotype.Service
public class Service {

    private final StateMachineFactory<State, Event> factory;

    public Service(StateMachineFactory<State, Event> factory) {
        this.factory = factory;
    }

    public StateMachine<State, Event> getStateMachine() {
        return factory.getStateMachine();
    }
}
