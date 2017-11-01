package au.com.miacucina.com.foodtour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import au.com.miacucina.com.foodtour.R;

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