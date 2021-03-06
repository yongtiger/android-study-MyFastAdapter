package cc.brainbook.android.study.myfastadapter.adapter;

import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.BaseWrapAdapter;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;

public class AnimationWrapAdapter<Item extends IItem> extends BaseWrapAdapter {
    private AnimationAdapter mAnimationAdapter;

    public AnimationWrapAdapter(AnimationAdapter animationAdapter) {
        mAnimationAdapter = animationAdapter;
    }

    /**
     * the onBindViewHolder is managed by the FastAdapter so forward this correctly
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
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
        super.onBindViewHolder(holder, position, payloads);
        mAnimationAdapter.onBindViewHolder(holder, position, payloads); ///注意：必须加这行！否则无动画效果
    }

}
