package com.example.nurre.beerdex;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nurre on 2018-03-07.
 */

public class BeerImageAdapter extends RecyclerView.Adapter<BeerImageAdapter.BeerCardView> {
    private Context mContext;
    private List<BeerImage> beerImages;

    public class BeerCardView extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title, user_name;

        public BeerCardView(View view){
            super(view);
            image = (ImageView) view.findViewById(R.id.media_image);
            title = (TextView) view.findViewById(R.id.primary_text);
            user_name = (TextView) view.findViewById(R.id.sub_text);
        }

    }

    public BeerImageAdapter(Context mContext, List<BeerImage> beerImages) {
        this.mContext = mContext;
        this.beerImages = beerImages;
    }

    @Override
    public BeerCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new BeerCardView(itemView);
    }

    @Override
    public void onBindViewHolder(BeerCardView holder, int position) {
        BeerImage beerimage = beerImages.get(position);

        //loadPictureFromURL(beerimage.getLink(), holder.image);
        Picasso.with(mContext).load("https://vignette.wikia.nocookie.net/freshprince/images/7/79/Will_Smith.jpg/revision/latest?cb=20141020211734").into(holder.image);
        holder.title.setText(beerimage.getDescription());
        holder.user_name.setText(beerimage.getUserName());

    }

    @Override
    public int getItemCount() {
        return beerImages.size();
    }
}
