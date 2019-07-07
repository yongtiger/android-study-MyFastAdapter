package cc.brainbook.android.study.myfastadapter.items;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import cc.brainbook.android.study.myfastadapter.R;

///[FastAdapter#Load/Error/Empty#EmptyItem]
///https://github.com/mikepenz/AboutLibraries/blob/v6.2.3/library/src/main/java/com/mikepenz/aboutlibraries/ui/item/LoaderItem.java
public class EmptyItem extends AbstractItem<EmptyItem, EmptyItem.ViewHolder> {
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.empty_item_id;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.listempty_opensource;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param holder the viewHolder of this item
     */
    @Override
    public void bindView(final EmptyItem.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
    }

    /**
     * This method returns the ViewHolder for our item, using the provided View.
     *
     * @param v
     * @return the ViewHolder for this Item
     */
    @NonNull
    @Override
    public EmptyItem.ViewHolder getViewHolder(View v) {
        return new EmptyItem.ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View headerView) {
            super(headerView);
        }
    }
}
