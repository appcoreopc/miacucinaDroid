package au.com.miacucina.com.foodtour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import au.com.miacucina.com.foodtour.R;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView imageView;

    public ProfileViewHolder(View view) {

        super(view);
        title = (TextView) view.findViewById(R.id.title);
        imageView = (ImageView) view.findViewById(R.id.imageContent);
    }
}