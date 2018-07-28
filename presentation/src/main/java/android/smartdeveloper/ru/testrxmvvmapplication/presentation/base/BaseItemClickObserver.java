package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseItemClickObserver<M, R extends BaseRouter> implements Observer<M> {

    protected final R router;


    public BaseItemClickObserver(R router){
        this.router = router;
    }

    @Override
    public abstract void onSubscribe(Disposable d);

    @Override
    public abstract void onNext(M m);

    @Override
    public void onError(Throwable e) {
        router.showError(e);
    }

    @Override
    public void onComplete() {

    }
}
