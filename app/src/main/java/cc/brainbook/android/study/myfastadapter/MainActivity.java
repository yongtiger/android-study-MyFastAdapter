package cc.brainbook.android.study.myfastadapter;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter_extensions.HeaderHelper;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;
import java.util.Comparator;

import cc.brainbook.android.study.myfastadapter.adapter.AnimationWrapAdapter;
import cc.brainbook.android.study.myfastadapter.adapter.FastScrollIndicatorAdapter;
import cc.brainbook.android.study.myfastadapter.dummy.ImageDummyData;
import cc.brainbook.android.study.myfastadapter.items.SimpleImageItem;
import cc.brainbook.android.study.myfastadapter.items.SimpleItem;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FastAdapter<IItem> mFastAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv);


        /* ------------------- ///[FastAdapter] ------------------- */
        ///create the ItemAdapter holding your Items
        final ItemAdapter<SimpleImageItem> itemAdapter = new ItemAdapter<>();

        ///create the managing FastAdapter, by passing in the itemAdapter
        mFastAdapter = FastAdapter.with(itemAdapter);    ///or
//        mFastAdapter = FastAdapter.with(Arrays.asList(itemAdapter));

        ///set our adapters to the RecyclerView
//        mRecyclerView.setAdapter(mFastAdapter);


        /* -------------- ///[MaterialScrollBar] -------------- */
        FastScrollIndicatorAdapter fastScrollIndicatorAdapter = new FastScrollIndicatorAdapter();
//        mRecyclerView.setAdapter(fastScrollIndicatorAdapter.wrap(mFastAdapter));

        //add a FastScrollBar (Showcase compatibility)
//        DragScrollBar materialScrollBar = new DragScrollBar(this, recyclerView, true);
//        materialScrollBar.setHandleColor(ContextCompat.getColor(this, R.color.accent));
//        materialScrollBar.setIndicator(new AlphabetIndicator(this), true);
        ((DragScrollBar)findViewById(R.id.dragScrollBar))
                .setHandleColor(ContextCompat.getColor(this, R.color.accent))
                ///注意：setIndicator()要求fastScrollIndicatorAdapter必须为mRecyclerView.setAdapter()的最外层（即实现INameableAdapter）
                .setIndicator(new AlphabetIndicator(this), true);


        /* -------------- ///[RecyclerView Animators#Scroll Animation]AnimationWrapAdapter -------------- */
        ///Error: 无数据！需要用AnimationWrapAdapter
//        mRecyclerView.setAdapter(new AlphaInAnimationAdapter(mFastAdapter));
//        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(mFastAdapter));
//        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(new AlphaInAnimationAdapter(mFastAdapter))); ///chain adapter
//        ///https://github.com/wasabeef/recyclerview-animators/tree/2.3.0
        AnimationAdapter animationAdapter = (new ScaleInAnimationAdapter(new AlphaInAnimationAdapter(mFastAdapter)));   ///chain adapter
        AnimationWrapAdapter animationWrapAdapter = new AnimationWrapAdapter(animationAdapter);
//        mRecyclerView.setAdapter(animationWrapAdapter.wrap(animationAdapter, mFastAdapter)); ///wrap adapter


        /* -------------- ///[Chain Adapter] -------------- */
        ///注意：setIndicator()要求fastScrollIndicatorAdapter必须为mRecyclerView.setAdapter()的最外层（即实现INameableAdapter）
        mRecyclerView.setAdapter(fastScrollIndicatorAdapter.wrap(animationWrapAdapter.wrap(mFastAdapter)));


        /* -------------- ///[RecyclerView LayoutManager] -------------- */
        ///LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); ///水平方向
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ///GridLayoutManager    ///or
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, true);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL); ///水平方向
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//
//        ///StaggeredGridLayoutManager    ///or
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
////        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));


        /* -------------- ///[RecyclerView Animators#Item Animation] -------------- */
        ///https://github.com/mikepenz/ItemAnimators
        ///Note: does not animate items on scroll, just when added, removed, moved, or changed
        mRecyclerView.setItemAnimator(new SlideDownAlphaAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        mRecyclerView.getItemAnimator().setRemoveDuration(500);


        /* -------------- ///[HeaderHelper] -------------- */
        ///https://github.com/mikepenz/FastAdapter/commit/1e90f79702b40b3991e39e16984db8a9a86dc631
        final HeaderHelper<IItem, IItem> headerHelper =
                new HeaderHelper<>(itemAdapter, new HeaderHelper.GroupingFunction<IItem, IItem>() {
                    @Override
                    public IItem group(IItem currentItem, IItem nextItem, int currentPosition) {
                        if (currentItem == null) {
                            return new SimpleItem().isHeader(true).withName(((SimpleImageItem) nextItem).mName.charAt(0) + "");
                        } else if (nextItem != null) {
                            if (((SimpleImageItem) currentItem).mName.charAt(0) != ((SimpleImageItem) nextItem).mName.charAt(0)) {
                                return new SimpleItem().isHeader(true).withName(((SimpleImageItem) nextItem).mName.charAt(0) + "");
                            }
                        }
                        return null;
                    }
                });
        headerHelper.setComparator(new Comparator<IItem>() {
            public int compare(IItem f1, IItem f2) {
                if (f1 instanceof SimpleImageItem && f2 instanceof SimpleImageItem) {
                    return ((SimpleImageItem) f1).mName.compareTo(((SimpleImageItem) f2).mName);
                } else {
                    return f1.toString().compareTo(f2.toString());
                }
            }
        });


        /* ----------------------------- ///[ItemClick] ------------------------------- */
        mFastAdapter.withOnClickListener(new OnClickListener<IItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<IItem> adapter, IItem item, int position) {
                ///[HeaderHelper]判断item和position是否为Header点击
                if (item instanceof SimpleItem && ((SimpleItem)item).isHeader) {
                    int headerPosition = 0;
                    for (int i = 0; i < position; i++) {    /// todo ...待优化!
                        if (mFastAdapter.getItem(i) instanceof SimpleItem && ((SimpleItem)mFastAdapter.getItem(i)).isHeader) {
                            headerPosition++;
                        }
                    }
                    Toast.makeText(v.getContext(), "Header: "+headerPosition+"", Toast.LENGTH_SHORT).show();
                } else {
                    int itemPosition = position;
                    for (int i = 0; i < position; i++) {    /// todo ...待优化!
                        if (mFastAdapter.getItem(i) instanceof SimpleItem && ((SimpleItem)mFastAdapter.getItem(i)).isHeader) {
                            itemPosition--;
                        }
                    }
                    Toast.makeText(v.getContext(), itemPosition+"", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        /* ------------------------------------------------------------------------------ */
        ///set the items to your ItemAdapter
//        itemAdapter.add(ImageDummyData.getSimpleImageItems());    ///or
        ///if we do this. the first added items will be animated :D
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ///[HeaderHelper]
                ///https://github.com/mikepenz/FastAdapter/commit/1e90f79702b40b3991e39e16984db8a9a86dc631
                headerHelper.apply(new ArrayList<>(ImageDummyData.getSimpleImageItems()));

                //add some dummy data
                itemAdapter.add(ImageDummyData.getSimpleImageItems());

                //restore selections (this has to be done after the items were added
                mFastAdapter.withSavedInstanceState(savedInstanceState);
            }
        }, 50);

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
