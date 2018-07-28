package android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler;

import android.smartdeveloper.ru.domain.entity.DomainModel;
import android.smartdeveloper.ru.testrxmvvmapplication.presentation.base.recycler.events.OnItemClickEventModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseRecyclerViewAdapter
        <E extends DomainModel, VM extends BaseItemViewModel<E>>
                extends RecyclerView.Adapter<BaseItemViewHolder<E, VM, ?>> {

    protected boolean isItemClickedEnabled = true;

    private PublishSubject<OnItemClickEventModel<E>> itemClickSubject = PublishSubject.create();

    protected List<E> items = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull BaseItemViewHolder<E, VM, ?> holder, int position) {
        holder.hydrate(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract void updateItem(E item);

    public void setItems(List<E> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void removeItem(E entity){
        int index = items.indexOf(entity);
        this.items.remove(index);
        notifyItemRemoved(index);
    }

    public void moveItem(int fromPosition, int toPosition){
        E item = items.remove(fromPosition);
        if(toPosition > fromPosition){
            toPosition--;
        }
        items.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addItem(E entity){
        items.add(entity);
        notifyItemInserted(items.size() - 1);
    }

    public void addItems (List<E> items){
        this.items.addAll(items);
        notifyItemRangeInserted(items.size()-1, items.size());
    }

    public void clear(){
        this.items.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseItemViewHolder<E, VM, ?> holder) {
        super.onViewAttachedToWindow(holder);
        if(isItemClickedEnabled){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    itemClickSubject.onNext(new OnItemClickEventModel<>(items.get(position), position));
                    holder.getViewModel().onItemClick();
                }
            });
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseItemViewHolder<E, VM, ?> holder) {
        super.onViewDetachedFromWindow(holder);
        if(isItemClickedEnabled){
            holder.itemView.setOnClickListener(null);
        }
    }

    public Observable<OnItemClickEventModel<E>> observeItemClick(){
        return itemClickSubject;
    }
}
