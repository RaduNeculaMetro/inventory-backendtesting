package utils.api;

import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    public static void threadWait (long miliseconds){
        try{
            Thread.sleep(miliseconds);
        }catch (InterruptedException e){
            throw new RuntimeException("Exception on wait", e);
        }
    }


    public static void verifyAndRetry (Runnable runnable, int steps, long milisecondsBetweenSteps) {
        while(steps>0){
            try{
                runnable.run();
                return;
            }catch (AssertionError error) {
                logger.error("Step no: " + steps + " fails.");
                logger.error("Wait util trying again: " + milisecondsBetweenSteps + " milis.");
                threadWait(milisecondsBetweenSteps);
                steps--;
                if (steps==0){
                    throw error;
                }
            }
        }
    }
}
