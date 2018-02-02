package vn.edu.tdmu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
public class CurrentTimeDateTimeService implements DateTimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentTimeDateTimeService.class);


    @Override
    public ZonedDateTime getCurrentDataAndTime() {
        ZonedDateTime currentDateAndTime = ZonedDateTime.now();

        LOGGER.info("Returning current date and time: {}", currentDateAndTime);

        return currentDateAndTime;
    }
}
