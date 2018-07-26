package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.widget.Toast;

public class BaseRouter <A extends BaseActivity> {
    private static final String TAG = "BaseRouter";
    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void showError(Throwable e){
        showToastError(e.getMessage());
    }

    private void showToastError(int errorId) {
        Toast.makeText(activity, errorId, Toast.LENGTH_SHORT).show();
    }

    private void showToastError(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
