package au.com.miacucina.com.foodtour.adapters;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.R;

public class ItemAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<ItemDisplay> itemList = new ArrayList<>();

    public ItemAdapter(List<ItemDisplay> list) {
        itemList = list;
    }

    View itemView;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, null);
        return new MyViewHolder(itemView);
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ItemDisplay item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.titleInfo.setText(item.getTitleInfo());
        holder.description.setText(item.getDescription());
        String imageUrl = itemList.get(position).getImageUrl();

        if (imageUrl != null)
            Picasso.with(itemView.getContext()).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void swapItems(List<ItemDisplay> contacts) {
        // compute diffs
        final ItemDiffCallback diffCallback = new ItemDiffCallback(this.itemList, contacts);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        // clear contacts and add
        this.itemList.clear();
        this.itemList.addAll(contacts);

        diffResult.dispatchUpdatesTo(this); // calls adapter's notify methods after diff is computed
    }

}
