package cc.brainbook.android.study.myfastadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.turingtechnologies.materialscrollbar.ICustomAdapter;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

import cc.brainbook.android.study.myfastadapter.items.SimpleImageItem;
import cc.brainbook.android.study.myfastadapter.items.SimpleItem;
import cc.brainbook.android.study.myfastadapter.items.expandable.SimpleSubExpandableItem;
import cc.brainbook.android.study.myfastadapter.items.expandable.SimpleSubItem;
import cc.brainbook.android.study.myfastadapter.model.ModelIconItem;

/**
 * Created by mikepenz on 30.12.15.
 * This is a FastAdapter adapter implementation for the Material Design 5.1 scrollbar
 * https://github.com/krimin-killr21/MaterialScrollBar
 */
///[MaterialScrollBar]
public class FastScrollIndicatorAdapter<Item extends IItem> extends BaseWrapAdapter implements INameableAdapter, ICustomAdapter {
    @Override
    public Character getCharacterForElement(int position) {
        IItem item = getItem(position);
        ///[MaterialScrollBar]
        if (item instanceof SimpleItem && ((SimpleItem) item).name != null) {
            return ((SimpleItem) item).name.getText().charAt(0);
        } else if (item instanceof SimpleImageItem && ((SimpleImageItem) item).mName != null) {
            return ((SimpleImageItem) item).mName.charAt(0);
        } else if (item instanceof SimpleSubItem && ((SimpleSubItem) item).name != null) {
            return ((SimpleSubItem) item).name.getText().charAt(0);
        } else if (item instanceof SimpleSubExpandableItem && ((SimpleSubExpandableItem) item).name != null) {
            return ((SimpleSubExpandableItem) item).name.getText().charAt(0);
        }
        return ' ';
    }

    @Override
    public String getCustomStringForElement(int position) {
        IItem item = getItem(position);
        if (item instanceof ModelIconItem && ((ModelIconItem) item).getModel().icon.getName() != null) {
            //based on the position we set the headers text
            return ((ModelIconItem) item).getModel().icon.getName();
        }
        return "";
    }


}
