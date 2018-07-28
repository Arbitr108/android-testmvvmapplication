package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.events;

import android.smartdeveloper.ru.domain.entity.DomainModel;

public class OnItemClickEventModel<E extends DomainModel> {
    private E entity;
    private int position;

    public OnItemClickEventModel(E entity, int position) {
        this.entity = entity;
        this.position = position;
    }

    public E getEntity() {
        return entity;
    }

    public int getPosition() {
        return position;
    }
}
