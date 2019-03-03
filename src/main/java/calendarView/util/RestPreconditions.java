package calendarView.util;

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

    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new BadRequestException(errorMessage);
        }
    }
}
