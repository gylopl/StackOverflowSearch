package makdroid.stackoverflowsearch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import makdroid.stackoverflowsearch.R;
import makdroid.stackoverflowsearch.model.Item;

/**
 * Created by Grzecho on 01.07.2016.
 */
public class ResponseStackOverAdapter extends RecyclerView.Adapter<ResponseStackOverAdapter.ItemHolder> {
    private List<Item> mItemCollection;

    public ResponseStackOverAdapter(List<Item> itemCollection) {
        this.mItemCollection = itemCollection;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.card_row_item, parent, false);
        return new ItemHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        Item item = mItemCollection.get(position);
        holder.userName.setText(item.owner.displayName);
        Picasso.with(holder.userName.getContext()).load(item.owner.profileImage).resize(50, 50).into(holder.userAvatar);
        holder.questionTitle.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return mItemCollection.size();
    }

    final static class ItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_name)
        TextView userName;
        @Bind(R.id.user_avatar)
        ImageView userAvatar;
        @Bind(R.id.question_title)
        TextView questionTitle;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public Item getItem(int position) {
        return mItemCollection.get(position);
    }

    public void clearItems() {
        int size = this.mItemCollection.size();
        if (size > 0) {
            this.mItemCollection.clear();
            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addItems(List<Item> items) {
        this.mItemCollection.addAll(items);
        this.notifyItemRangeInserted(0, items.size());
    }

}