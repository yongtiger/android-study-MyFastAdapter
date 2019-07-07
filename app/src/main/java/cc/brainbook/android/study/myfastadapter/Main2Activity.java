package cc.brainbook.android.study.myfastadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.brainbook.android.study.myfastadapter.items.SimpleImageItem;
import cc.brainbook.android.study.myfastadapter.items.SimpleItem;
///[Multiple ItemAdapter]
public class Main2Activity extends AppCompatActivity {
    private static final String[] headers = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private RecyclerView mRecyclerView;
    private FastAdapter<IItem> mFastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        /* ------------------- ///[Multiple ItemAdapter] ------------------- */
        final ItemAdapter<SimpleImageItem> headerAdapter = new ItemAdapter<>();
        final ItemAdapter<IItem> itemAdapter = new ItemAdapter<>();
        final ItemAdapter<SimpleItem> footerAdapter = new ItemAdapter<>();

//        mFastAdapter = FastAdapter.with(itemAdapter);    ///or
        mFastAdapter = FastAdapter.with(Arrays.asList(headerAdapter, itemAdapter, footerAdapter));

        //configure our fastAdapter
        //as we provide id's for the items we want the hasStableIds enabled to speed up things
        mFastAdapter.setHasStableIds(true);

        //restore selections (this has to be done after the items were added
        mFastAdapter.withSavedInstanceState(savedInstanceState);

        ///注意：必须在mFastAdapter = FastAdapter.with(Arrays.asList(headerAdapter, itemAdapter, footerAdapter))之后！
        headerAdapter.add(new SimpleImageItem().withIdentifier(1L).withName("Yang Zhuo Yong Cuo, Tibet China").withDescription("#100063").withImage("https://raw.githubusercontent.com/mikepenz/earthview-wallpapers/develop/thumb/yang_zhuo_yong_cuo,_tibet-china-63.jpg"));
        footerAdapter.add(new SimpleItem().withName("Footer").withHeader("KKK").withIdentifier(999));
        List<IItem> items = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            items.add(new SimpleItem().withName("Test " + i).withHeader(headers[i / 1]).withIdentifier(100 + i));
        }
        itemAdapter.add(items);


        /* ------------------- RecyclerView ------------------- */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mFastAdapter);
    }
}
