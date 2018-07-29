package android.smartdeveloper.ru.testrxmvvmapplication.presentation.screen.user.edit;

public class OnClickItemModel<E> {
    private E entity;

    public OnClickItemModel(E entity) {
        this.entity = entity;
    }

    public E getEntity() {
        return entity;
    }
}
