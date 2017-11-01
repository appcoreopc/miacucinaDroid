package au.com.miacucina.com.foodtour.adapters;

import android.media.Image;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, titleInfo, description;
        public ImageView imageView;

        public MyViewHolder(View view) {

            super(view);

            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.titleInfo);
            titleInfo = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.imageContent);
        }
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
