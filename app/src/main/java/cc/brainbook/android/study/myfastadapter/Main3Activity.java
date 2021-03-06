package cc.brainbook.android.study.myfastadapter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import cc.brainbook.android.study.myfastadapter.items.EmptyItem;
import cc.brainbook.android.study.myfastadapter.items.ErrorItem;
import cc.brainbook.android.study.myfastadapter.items.LoaderItem;
import cc.brainbook.android.study.myfastadapter.items.SimpleItem;

///[FastAdapter#Load/Error/Empty]
public class Main3Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FastAdapter<IItem> mFastAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final ItemAdapter<IItem> itemAdapter = new ItemAdapter<>();

        mFastAdapter = FastAdapter.with(itemAdapter);


        /* ------------------- ///[FastAdapter#Load/Error/Empty] ------------------- */
        ///[FastAdapter#Load/Error/Empty#LoaderItem]
        itemAdapter.add(new LoaderItem());

//        ///模拟异步加载网络数据：失败
//        //if we do this. the first added items will be animated :D
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ///remove loader
//                itemAdapter.clear();
//
//                ///[FastAdapter#Load/Error/Empty#ErrorItem]
//                itemAdapter.add(new ErrorItem());
//
//                //restore selections (this has to be done after the items were added
//                mFastAdapter.withSavedInstanceState(savedInstanceState);
//            }
//        }, 5000);

        ///模拟异步加载网络数据：成功
        //if we do this. the first added items will be animated :D
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ///remove loader
                itemAdapter.clear();

                ///add some dummy data
                List<IItem> items = new ArrayList<>();
//                for (int i = 1; i <= 25; i++) {
//                    items.add(new SimpleItem().withName("Test " + i).withIdentifier(100 + i));
//                }
                itemAdapter.add(items);

                ///[FastAdapter#Load/Error/Empty#EmptyItem]
                if (itemAdapter.getAdapterItemCount() == 0) {
                    itemAdapter.add(new EmptyItem());
                }

                //restore selections (this has to be done after the items were added
                mFastAdapter.withSavedInstanceState(savedInstanceState);
            }
        }, 5000);


        /* ------------------- RecyclerView ------------------- */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mFastAdapter);
    }

}
