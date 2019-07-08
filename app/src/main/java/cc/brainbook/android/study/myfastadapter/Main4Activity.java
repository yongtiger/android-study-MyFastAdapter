package cc.brainbook.android.study.myfastadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import cc.brainbook.android.study.myfastadapter.items.EmptyItem;
import cc.brainbook.android.study.myfastadapter.items.SimpleItem;

///[FastAdapter#Empty]
public class Main4Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FastAdapter<IItem> mFastAdapter;
    private ItemAdapter<IItem> mItemAdapter = new ItemAdapter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mFastAdapter = FastAdapter.with(mItemAdapter);

        ///add some dummy data
        List<IItem> items = new ArrayList<>();
            for (int i = 1; i <= 2; i++) {
                items.add(new SimpleItem().withName("Test " + i).withIdentifier(100 + i));
            }
        mItemAdapter.add(items);


        /* ------------------- RecyclerView ------------------- */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mFastAdapter);
    }

    public void onDeleteClick(View view) {
        ///[FastAdapter#Empty]如果item为空、或者item仅有一个EmptyItem，则退出
        if (mItemAdapter.getAdapterItemCount() == 0
                || mItemAdapter.getAdapterItemCount() == 1
                && mFastAdapter.getItemViewType(0) == R.id.empty_item_id) {
            return;
        }

        mItemAdapter.remove(0);

        ///[FastAdapter#EmptyItem]如果item为空，则添加EmptyItem
        if (mItemAdapter.getAdapterItemCount() == 0) {
            mItemAdapter.add(new EmptyItem());
        }
    }

    public void onAddClick(View view) {
        ///[FastAdapter#Empty]如果item仅有一个EmptyItem，则清空
        if (mItemAdapter.getAdapterItemCount() == 1
                && mFastAdapter.getItemViewType(0) == R.id.empty_item_id) {
            mItemAdapter.clear();
        }

        mItemAdapter.add(new SimpleItem().withName("Add Test "));
    }
}
