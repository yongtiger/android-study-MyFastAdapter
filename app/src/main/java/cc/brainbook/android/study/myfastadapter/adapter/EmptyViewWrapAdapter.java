package cc.brainbook.android.study.myfastadapter.adapter;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter_extensions.adapters.WrapAdapter;

import java.util.List;

public class EmptyViewWrapAdapter<Item extends IItem> extends WrapAdapter<Item> {

    public EmptyViewWrapAdapter(List<Item> list) {
        super(list);
    }

}