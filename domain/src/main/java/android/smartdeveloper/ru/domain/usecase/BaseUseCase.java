package android.smartdeveloper.ru.domain.usecase;

import android.smartdeveloper.ru.domain.executors.ExecutionThread;
import android.smartdeveloper.ru.domain.executors.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {
    protected Scheduler executionThread;
    protected Scheduler postExecutionThread;

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        executionThread = Schedulers.io();
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public BaseUseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    public BaseUseCase(ExecutionThread executionThread, PostExecutionThread postExecutionThread){
        this.executionThread = Schedulers.from(executionThread);
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    protected Scheduler getExecutionThread() {
        return executionThread;
    }

    protected Scheduler getPostExecutionThread() {
        return postExecutionThread;
    }
}
