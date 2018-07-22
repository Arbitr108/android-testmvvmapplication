package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<R extends BaseRouter> extends ViewModel {

    private CompositeDisposable disposables;
    protected R router;

    public CompositeDisposable getDisposables() {
        if(disposables == null){
            disposables = new CompositeDisposable();
        }
        return disposables;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposables != null && !disposables.isDisposed()){
            disposables.dispose();
        }
    }

    public void addRouter(R router){
        this.router = router;
    }
    public void removeRouter(){
        this.router = null;
    }
}
