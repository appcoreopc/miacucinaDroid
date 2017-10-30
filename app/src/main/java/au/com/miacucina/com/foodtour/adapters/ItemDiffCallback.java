package au.com.miacucina.com.foodtour.adapters;

import android.support.v7.util.DiffUtil;

import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;

/**
 * Created by Jeremy on 10/30/2017.
 */

public class ItemDiffCallback extends DiffUtil.Callback {

    private List<ItemDisplay> oldList;
    private List<ItemDisplay> newList;

    public ItemDiffCallback(List<ItemDisplay> oldList, List<ItemDisplay> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTitle() == newList.get(newItemPosition).getTitle();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemDisplay oldContact = oldList.get(oldItemPosition);
        ItemDisplay newContact = newList.get(newItemPosition);

        if (oldList.get(oldItemPosition).getTitle() == newList.get(newItemPosition).getTitle())
            return true;
        return false;
    }
}
