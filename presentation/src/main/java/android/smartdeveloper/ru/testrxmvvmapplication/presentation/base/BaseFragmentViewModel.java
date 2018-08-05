package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.smartdeveloper.ru.domain.entity.User;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragmentViewModel<R> extends ViewModel {

    private CompositeDisposable disposables;
    protected R router;

    public CompositeDisposable getDisposables() {
        if(disposables == null){
            disposables = new CompositeDisposable();
        }
        return disposables;
    }

    public void clear() {
        if(disposables != null && !disposables.isDisposed()){
            disposables.clear();
        }
    }

    public void addRouter(R router){
        this.router = router;
    }
    public void removeRouter(){
        this.router = null;
    }

    public abstract void setItem(User user);

    @Override
    protected void onCleared() {
        super.onCleared();
        this.clear();
    }
}
