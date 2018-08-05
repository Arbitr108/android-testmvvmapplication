package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.content.DialogInterface;
import android.smartdeveloper.ru.testrxmvvmapplication.R;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class BaseRouter <A extends BaseActivity> {
    private static final String TAG = "BaseRouter";
    private final DialogInterface.OnClickListener confirmationDialogClickListener;
    protected A activity;

    protected PublishSubject<Boolean> confirmSubject = PublishSubject.create();

    public BaseRouter(A activity) {
        this.activity = activity;
        this.confirmationDialogClickListener =  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        confirmSubject.onNext(true);
                        Log.d(TAG, "onClick: Positive choice");
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        confirmSubject.onNext(false);
                        dialog.dismiss();
                        break;
                }
            }
        };
    }

    public void showError(Throwable e){
        showToastError(e.getMessage());
        Log.e(TAG, "Router :" + e.getMessage() );
    }

    private void showToastError(int errorId) {
        Toast.makeText(activity, errorId, Toast.LENGTH_SHORT).show();
    }

    private void showToastError(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public Observable<Boolean> confirmAction(String message) {
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setMessage(message)
                .setPositiveButton("Да",confirmationDialogClickListener)
                .setNegativeButton("Нет",confirmationDialogClickListener)
                .show();

        return confirmSubject;
    }

    public void goBack(){
        activity.onBackPressed();
    }

    public void replaceFragment(BaseFragment fragment, int containerResId, boolean addToBackStack, String tag){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right);
        transaction.replace(containerResId, fragment, tag);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
