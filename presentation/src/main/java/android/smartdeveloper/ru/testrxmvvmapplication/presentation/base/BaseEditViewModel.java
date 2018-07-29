package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.smartdeveloper.ru.domain.entity.User;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseEditViewModel<E> {

    private CompositeDisposable disposables;

    public CompositeDisposable getDisposables() {
        if(disposables == null){
            disposables = new CompositeDisposable();
        }
        return disposables;
    }

    public void clear() {
        if(disposables != null && !disposables.isDisposed()){
            disposables.dispose();
        }
    }

    public abstract void setItem(User user);
}
