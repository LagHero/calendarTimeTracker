package calendarView.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestPreconditions {


    public static <T> T checkNotFound(T resource, String errorMessage) {
        if (resource == null) {
            throw new ResourceNotFoundException(errorMessage);
        }
        return resource;
    }

    public static <T> void checkNotNull(T Argument, String errorMessage) {
        if (Argument == null) {
            throw new BadRequestException(errorMessage);
        }
    }

    public static <T> void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new BadRequestException(errorMessage);
        }
    }

    public static Date parseDate(String stringDate, String dateFormat, String errorMessage) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(stringDate);
        } catch (ParseException e) {
            throw new BadRequestException(errorMessage, e);
        }
    }
}
