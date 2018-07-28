package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler;

import android.smartdeveloper.ru.domain.entity.DomainModel;

public abstract class BaseItemViewModel<E extends DomainModel> {

    public abstract void setItem(E item, int position);

    //specific click inside holder, not for everybody
    public void onItemClick(){

    }
}
