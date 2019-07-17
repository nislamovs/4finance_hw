import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.*

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%-5level] %logger{15} - %msg%n%rEx"
        immediateFlush = false
    }
}

appender("FILE", RollingFileAppender) {
    file = 'logs/4Finance_hw_performance.log'
    encoder(PatternLayoutEncoder) {
        pattern = '%d{"yyyy-MM-dd HH:mm:ss,SSS", Europe/Helsinki} %-5p [%t] %c - %msg%n'
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = '4Finance_hw_performance_%d{dd-MM-yyyy}-%i.zip'
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "10MB"
        }
        maxHistory = 7
    }
}

appender("FILE-ASYNC", AsyncAppender) {
    appenderRef("FILE")
    includeCallerData = true
    queueSize = 2000
    discardingThreshold = 0
}

root(WARN, ["CONSOLE", "FILE-ASYNC"])
logger("io.gatling.http.ahc", TRACE)
logger("io.gatling.http.request", TRACE)
logger("io.gatling.http.response", TRACE)

logger("io.gatling.http.ahc", DEBUG)
logger("io.gatling.http.request", DEBUG)
logger("io.gatling.http.response", DEBUG)
