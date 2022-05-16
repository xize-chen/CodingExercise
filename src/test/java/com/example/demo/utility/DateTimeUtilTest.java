package com.example.demo.utility;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class DateTimeUtilTest {

    @Test
    void itShouldConvertStringToLocalDateTime() {
        //GIVEN
        //Expected LocalDateTime.
        String timeStr = "2022-05-16 14:08:35";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime expectedDateTime = LocalDateTime.parse(timeStr, formatter);
        //Test input time string.
        String testStr_1 = "2022/05/16 14:08:35";
        String testStr_2 = "14:08:35 05-16-2022";
        String testStr_3 = "05/16 2022 14:08:35";
        List<String> testArr = List.of(testStr_1, testStr_2, testStr_3);

        //WHEN
        List<Optional<LocalDateTime>> convertedTimes = testArr.stream()
                .map(DateTimeUtil::convert)
                .toList();

        //THEN
        convertedTimes.forEach(optionalTime -> {
            assertThat(optionalTime)
                    .isPresent()
                    .hasValueSatisfying(time -> {
                        assertThat(time).isEqualTo(expectedDateTime);
                    });
        });
    }
}