package cc.brainbook.android.study.myfastadapter.adapter;

import com.mikepenz.fastadapter.IItem;

import com.mikepenz.fastadapter.commons.adapters.BaseWrapAdapter;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import cc.brainbook.android.study.myfastadapter.items.SimpleImageItem;

/**
 * Created by mikepenz on 30.12.15.
 * This is a FastAdapter adapter implementation for the Material Design 5.1 scrollbar
 * https://github.com/krimin-killr21/MaterialScrollBar
 */
///[MaterialScrollBar]
public class FastScrollIndicatorAdapter<Item extends IItem> extends BaseWrapAdapter implements INameableAdapter {
    @Override
    public Character getCharacterForElement(int position) {
        IItem item = getItem(position);
        ///[MaterialScrollBar]
        if (item instanceof SimpleImageItem && ((SimpleImageItem) item).mName != null) {
            return ((SimpleImageItem) item).mName.charAt(0);
        }
        return ' ';
    }

}
