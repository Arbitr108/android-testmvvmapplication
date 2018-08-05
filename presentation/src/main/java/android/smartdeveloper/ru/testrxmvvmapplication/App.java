package android.smartdeveloper.ru.testrxmvvmapplication;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext(){
        return context;
    }
}
