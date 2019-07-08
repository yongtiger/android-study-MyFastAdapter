package cc.brainbook.android.study.myfastadapter.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.BaseWrapAdapter;

import java.security.SecureRandom;

import cc.brainbook.android.headerdecoration.interfaces.HeaderAdapter;
import cc.brainbook.android.study.myfastadapter.R;
import cc.brainbook.android.study.myfastadapter.items.SimpleImageItem;

public class HeaderDecorationAdapter<Item extends IItem> extends BaseWrapAdapter implements HeaderAdapter<RecyclerView.ViewHolder> {

    @Override
    public long getHeaderId(int position) {
        IItem item = getItem(position);

        //in our sample we want a separate header per first letter of our items
        //this if is not necessary for your code, we only use it as this sticky header is reused for different item implementations
        if (item instanceof SimpleImageItem) {
            return ((SimpleImageItem) item).mName.charAt(0);
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        //we create the view for the header
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(String.valueOf((char) getHeaderId(position)));
        textView.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        final SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }
}
