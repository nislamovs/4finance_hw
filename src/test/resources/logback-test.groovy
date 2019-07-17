import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

appender("FILE", RollingFileAppender) {
        file = 'logs/4Finance_hw.log'
        encoder(PatternLayoutEncoder) {
                pattern = '%d{"yyyy-MM-dd HH:mm:ss,SSS", Europe/Helsinki} %-5p [%t] %c - %msg%n'
        }
        rollingPolicy(TimeBasedRollingPolicy) {
                fileNamePattern = '4Finance_hw_%d{dd-MM-yyyy}-%i.zip'
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

appender("STDOUT", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
                pattern = '%d{"yyyy-MM-dd HH:mm:ss,SSS", Europe/Helsinki} %-5p [%t] %c - %msg%n'
        }
}

root(INFO, ["FILE-ASYNC", "STDOUT"])
logger("4Finance_hw", DEBUG)