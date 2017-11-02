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

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemDisplay> itemList = new ArrayList<>();
    private ViewType mViewType = ViewType.ALBUM;
    private View itemView;

    public ItemAdapter(List<ItemDisplay> list) {
        itemList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mViewType == ViewType.ALBUM) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, null);
            return new HorizontalAlbumViewHolder(itemView);
        } else if (mViewType == ViewType.PROFILE) {

            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_profile, null);
            return new ProfileViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, null);
            return new StaggeredViewHolder(itemView);
        }
    }

    public void setViewType(ViewType viewType) {
        mViewType = viewType;
    }

    public void setItemList(List<ItemDisplay> list) {
        itemList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (mViewType == ViewType.ALBUM) {

            HorizontalAlbumViewHolder albumViewHolder = (HorizontalAlbumViewHolder) holder;
            ItemDisplay item = itemList.get(position);
            albumViewHolder.title.setText(item.getTitle());
            albumViewHolder.titleInfo.setText(item.getTitleInfo());
            albumViewHolder.description.setText(item.getDescription());
            String imageUrl = itemList.get(position).getImageUrl();

            if (imageUrl != null)
                Picasso.with(itemView.getContext()).load(imageUrl).into(albumViewHolder.imageView);
        } else if (mViewType == ViewType.PROFILE) {

            ProfileViewHolder profileViewHolder = (ProfileViewHolder) holder;
            ItemDisplay item = itemList.get(position);
            profileViewHolder.title.setText(item.getTitle());
            String imageUrl = itemList.get(position).getImageUrl();

            switch (imageUrl) {
                case "1":
                    profileViewHolder.imageView.setImageResource(R.drawable.ic_attach_money_black_24px);
                    break;
                case "2":
                    profileViewHolder.imageView.setImageResource(R.drawable.ic_card_giftcard_black_24px);
                    break;
                case "3":
                    profileViewHolder.imageView.setImageResource(R.drawable.ic_settings_black_24px);
                    break;
                case "4":
                    profileViewHolder.imageView.setImageResource(R.drawable.ic_speaker_notes_black_24px);
                    break;
            }

        } else if (mViewType == ViewType.STAGGERED) {

        }
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
