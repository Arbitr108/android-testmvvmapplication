package android.smartdeveloper.ru.domain.executors;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
