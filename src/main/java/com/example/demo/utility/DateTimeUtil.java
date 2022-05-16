package com.example.demo.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeUtil {
    private static final Pattern YEAR = Pattern.compile("(?<=^|\\D)(\\d{4})(?=\\D|$)");
    private static final Pattern MONTH_DAY = Pattern.compile("(?<!\\d|:)(\\d{2}[^:\\d]\\d{2})(?!\\d|:)");
    private static final Pattern HOUR_MINUTE_SECOND = Pattern.compile("(?<=^|\\D)(\\d{2}:\\d{2}(:\\d{2})?)(?=\\D|$)");
    private static final String PATTERN_NOT_FOUND = "NOT_FOUND";

    public static Optional<LocalDateTime> convert(String timeStr){
        //Reformat the input timeString.
        String formatStr = reformat(timeStr);

        //If reformatting is successful then return an optional dateTime.
        if(!formatStr.contains(PATTERN_NOT_FOUND)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(formatStr, formatter);
            return Optional.of(dateTime);
        }

        //Returns an empty optional if the time format cannot be converted correctly.
        return Optional.empty();
    }

    //This Method is used to generate a formatted time string(e.g. "2022-05-16 12:12:12").
    private static String reformat(String input) {
        StringBuilder output = new StringBuilder();

        //Process the year part in the input string.
        Matcher year = YEAR.matcher(input);
        output.append(year.find()?
                (year.group(1) + "-") : PATTERN_NOT_FOUND);

        //Process the month_day part.
        Matcher monthDay = MONTH_DAY.matcher(input);
        output.append(monthDay.find()?
                (monthDay.group(1).substring(0,2) + "-" + monthDay.group(1).substring(3))
                : PATTERN_NOT_FOUND);

        //Process the hour_minute_second part.
        Matcher hourPart = HOUR_MINUTE_SECOND.matcher(input);
        if(hourPart.find()){
            String buffer = " " + hourPart.group(1);
            long countColons = buffer.chars().filter(chr -> chr == ':').count();
            output.append(countColons == 1? (buffer + ":00") : countColons == 2? buffer : PATTERN_NOT_FOUND);
            return output.toString();
        }
        output.append(PATTERN_NOT_FOUND);
        return output.toString();
    }

}
