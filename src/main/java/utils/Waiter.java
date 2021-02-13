package utils;

import com.codeborne.selenide.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import static java.lang.System.currentTimeMillis;

public class Waiter {

    public <T> void wait(T subject, Predicate<T> condition) {
        wait(subject, condition, Configuration.timeout, Configuration.pollingInterval);
    }

    public <T> void wait(T subject, Predicate<T> condition, long timeout, long pollingInterval) {
        for (long start = currentTimeMillis(); !isTimeoutExceeded(timeout, start) && !condition.apply(subject); ) {
            sleep(pollingInterval);
        }
    }

    public <T> void wait(Supplier<T> subjectSupplier, Predicate<T> condition, long timeout, long pollingInterval) {
        for (long start = currentTimeMillis(); !isTimeoutExceeded(timeout, start) && !condition.apply(subjectSupplier.get()); ) {
            sleep(pollingInterval);
        }
    }

    public <T> Boolean isWaiterExceededTimeout(T subject, Predicate<T> condition, long timeout, long pollingInterval) {
        long start;

        for (start = currentTimeMillis(); !isTimeoutExceeded(timeout, start) && !condition.apply(subject); ) {
            sleep(pollingInterval);
        }

        return !isTimeoutExceeded(timeout, start);
    }

    private boolean isTimeoutExceeded(long timeout, long start) {
        return currentTimeMillis() - start > timeout;
    }

    void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
