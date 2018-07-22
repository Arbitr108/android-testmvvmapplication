package android.smartdeveloper.ru.testrxmvvmapplication.presentation.executor;

import android.smartdeveloper.ru.domain.executors.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {

    private static UIThread instance;

    private UIThread(){}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

    public static UIThread getInstance(){
        if(instance == null){
            instance = new UIThread();
        }
        return instance;
    }
}
