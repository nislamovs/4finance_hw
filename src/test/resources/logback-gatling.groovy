import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.WARN

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%-5level] %logger{15} - %msg%n%rEx"
        immediateFlush = false
    }
}

logger("io.gatling.http.ahc", TRACE)
logger("io.gatling.http.response", TRACE)
logger("io.gatling.http.ahc", DEBUG)
logger("io.gatling.http.response", DEBUG)
root(WARN, ["CONSOLE"])