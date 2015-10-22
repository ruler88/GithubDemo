package app.rx;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by doomtrooper on 22/10/15.
 */
public class RetryWithIncreasingDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private final boolean incRetryDelay;
    private int retryCount;

    public RetryWithIncreasingDelay(int maxRetries, int retryDelayMillis, boolean incRetryDelay) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.incRetryDelay = incRetryDelay;
        this.retryCount = 0;
    }


    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                if (++retryCount < maxRetries) {
                    // When this Observable calls onNext, the original
                    // Observable will be retried (i.e. re-subscribed).
                    return incRetryDelay?Observable.timer(retryDelayMillis*retryCount,
                            TimeUnit.MILLISECONDS):Observable.timer(retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                }

                // Max retries hit. Just pass the error along.
                return Observable.error(throwable);
            }
        });
    }
}
