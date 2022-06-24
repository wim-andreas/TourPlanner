package com.wimfra.tourplanner.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2Wrapper implements ILoggerWrapper {

    private Logger logger;
    private LoggerStateBase state = new UninitializedState();

    @Override
    public void debug(String message) {
        this.state.debug(message);
    }

    @Override
    public void fatal(String message) {
        this.state.fatal(message);
    }

    @Override
    public void error(String message) {
        this.state.error(message);
    }

    @Override
    public void warn(String message) {
        this.state.warn(message);
    }

    public void initialize(String name) {
        this.state = new InitializedState(LogManager.getLogger(name));
    }

}
