package cc.brainbook.android.study.myfastadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;

public class AnimationWrapAdapter<Item extends IItem> extends BaseWrapAdapter {
    private AnimationAdapter mAnimationAdapter;

    public AnimationWrapAdapter<Item> wrap(AnimationAdapter animationAdapter, FastAdapter fastAdapter) {
        this.mAnimationAdapter = animationAdapter;
        this.mFastAdapter = fastAdapter;
        return this;
    }

    /**
     * the onBindViewHolder is managed by the FastAdapter so forward this correctly
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);  ///注意：可以去掉这行，否则重复了
        mAnimationAdapter.onBindViewHolder(holder, position);   ///注意：必须加这行！否则无动画效果
    }

    /**
     * the onBindViewHolder is managed by the FastAdapter so forward this correctly
     *
     * @param holder
     * @param position
     * @param payloads
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);  ///注意：即使重复了也不能去掉这行！否则无数据显示
        mAnimationAdapter.onBindViewHolder(holder, position, payloads); ///注意：必须加这行！否则无动画效果
    }

}
