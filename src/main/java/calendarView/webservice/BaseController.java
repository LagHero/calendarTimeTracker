package calendarView.webservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class BaseController {

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void getApplicationStatus() {
        // REVIEW: Check something?
    }

    @GetMapping("/exit")
    @ResponseStatus(HttpStatus.OK)
    public void exitApplication() {
        Runnable runnable = () -> {
            try {
                System.exit(0);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

