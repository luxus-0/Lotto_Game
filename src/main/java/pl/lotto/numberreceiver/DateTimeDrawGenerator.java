package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;

class DateTimeDrawGenerator {

    private final Clock clock;

    DateTimeDrawGenerator(Clock clock) {
        this.clock = clock;
    }

    LocalDateTime generateNextDrawDate() {
        return LocalDateTime.now(clock)
                .with(next(SATURDAY))
                .withHour(12)
                .withMinute(0);
    }
}
