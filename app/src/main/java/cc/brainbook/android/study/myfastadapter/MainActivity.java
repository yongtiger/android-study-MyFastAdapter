package cc.brainbook.android.study.myfastadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.itemanimators.SlideDownAlphaAnimator;

import cc.brainbook.android.study.myfastadapter.dummy.DummyContent;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configure our fastAdapter
        //get our recyclerView and do basic setup
        mRecyclerView = findViewById(R.id.rv);


        /* -------------- LayoutManager -------------- */
        ///LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); ///水平方向
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        ///GridLayoutManager
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL); ///水平方向
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//
//        ///StaggeredGridLayoutManager
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
////        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        mAdapter = new RecyclerViewAdapter(DummyContent.ITEM_MAP);
//        mRecyclerView.setAdapter(mAdapter);


        ///[RecyclerView Animators#Item Animation]
        ///https://github.com/mikepenz/ItemAnimators
        ///Note: does not animate items on scroll, just when added, removed, moved, or changed
        mRecyclerView.setItemAnimator(new SlideDownAlphaAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);


        ///[RecyclerView Animators#Scroll Animation]
        ///https://github.com/wasabeef/recyclerview-animators/tree/2.3.0
//        mRecyclerView.setAdapter(new AlphaInAnimationAdapter(mAdapter));
//        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mAdapter));
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(new AlphaInAnimationAdapter(mAdapter))); ///chain adapter

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overall, menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem gridMenuItem = menu.findItem(R.id.action_list_type);
        if (mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            gridMenuItem.setIcon(R.drawable.ic_view_agenda_white_24dp);
            gridMenuItem.setTitle("linear_layout");
        } else if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
            gridMenuItem.setIcon(R.drawable.ic_dashboard_white_24dp);
            gridMenuItem.setTitle("staggered_layout");
        } else {
            gridMenuItem.setIcon(R.drawable.ic_view_grid_white_24dp);
            gridMenuItem.setTitle("grid_layout");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list_type) {
            if (mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                ///LinearLayoutManager
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); ///水平方向
                mRecyclerView.setLayoutManager(linearLayoutManager);
                item.setIcon(R.drawable.ic_view_grid_white_24dp);
                item.setTitle("grid_layout");//next click
            } else if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                ///StaggeredGridLayoutManager
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
                item.setIcon(R.drawable.ic_view_agenda_white_24dp);
                item.setTitle("linear_layout");//next click
            } else {
                ///GridLayoutManager
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//                gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL); ///水平方向
                mRecyclerView.setLayoutManager(gridLayoutManager);
                item.setIcon(R.drawable.ic_dashboard_white_24dp);
                item.setTitle("staggered_layout");//next click
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
