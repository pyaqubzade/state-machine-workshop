package az.pashabank.trafficlight.config;


import az.pashabank.trafficlight.model.Event;
import az.pashabank.trafficlight.model.State;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;


@EnableStateMachineFactory
public class Configuration extends EnumStateMachineConfigurerAdapter<State, Event> {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    private static final Logger log = getLogger(Configuration.class);

    @Override
    public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {

        var adapter = new StateMachineListenerAdapter<State, Event>() {
            @Override
            public void stateChanged(
                    org.springframework.statemachine.state.State<State, Event> from,
                    org.springframework.statemachine.state.State<State, Event> to
            ) {
                if (from == null) {
                    log.info("Initial state: {}", to.getId());
                    return;
                }
                log.info("State changed: {} -> {}", from.getId(), to.getId());
            }
        };

        config.withConfiguration().listener(adapter);
    }

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        // TODO-5: Configure all states
    }

    // @formatter:off
    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        // TODO-6: Configure all possible transitions
    }
}
