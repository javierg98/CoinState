package com.example.a12jg1.profits;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by 12jg1 on 12/1/2017.
 */

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.ViewHolder>{

    public ArrayList<Asset> assets = new ArrayList<>();
    public ArrayList<String> assetKeys = new ArrayList<>();

    public static final String ASSET = "ASSET";

    public static final String PROFILE_ACTIVITY = "profile";
    public static final String POPULAR_ACTIVITY = "popular";

    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String uid = user.getUid();

    public DatabaseReference assetReference;

    public AssetAdapter(final DatabaseReference assetRef, final String requestedActivity) {

        this.assetReference = assetRef;
        assetReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                //populate the list of items for the other activities
                //REMOVE
                //reimpliment for filtering later
//                if (requestedActivity.equals(PROFILE_ACTIVITY)){
//                    for (DataSnapshot post : dataSnapshot.getChildren()) {
//                        Asset p = post.getValue(Asset.class);
//                        if (p.getId().equals(uid)){
//                            assets.add(p);
//                            assetKeys.add(post.getKey());
//                            Log.d("Testing", p.getName());
//                        }
//                    }
//                }
//                else if(requestedActivity.equals(POPULAR_ACTIVITY)){
//                    for (DataSnapshot post : dataSnapshot.getChildren()) {
//                        Asset p = post.getValue(Asset.class);
//                        assets.add(p);
//                        pollKeys.add(post.getKey());
//                        Log.d("Testing", p.getTitle());
//                    }
//
//                    Collections.sort(assets, new Comparator<Poll>() {
//                        @Override
//                        public int compare(Poll poll, Poll t1) {
//                            return t1.compareTo(poll);
//                        }
//                    });
//
//                }
                //else{
                    for (DataSnapshot post : dataSnapshot.getChildren()) {
                        Asset p = post.getValue(Asset.class);
                        assets.add(p);
                        assetKeys.add(post.getKey());
                        Log.d("Testing", p.getId());
                    }
                //}
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CANCELLED", "Did not get poll");
            }
        });
    }

    /**
     * This function is called only enough times to cover the screen with views.  After
     * that point, it recycles the views when scrolling is done.
     * @param parent the intended parent object (our RecyclerView)
     * @param viewType unused in our function (enables having different kinds of views in the same RecyclerView)
     * @return the new ViewHolder we allocate
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // a LayoutInflater turns a layout XML resource into a View object.
        final View assetListItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_list_item, parent, false);
        return new ViewHolder(assetListItem);
    }
//    Issue with object being passed in to onBindViewHolder. We want to pass assetViewHolder
    //However we need an AsserAdapter.ViewHolder
//    @Override
//    public void onBindViewHolder(AssetAdapter.ViewHolder holder, int position) {
//
//    }

    /**
     * This function gets called each time a ViewHolder needs to hold data for a different
     * position in the list.  We don't need to create any views (because we're recycling), but
     * we do need to update the contents in the views.
     * @param holder the ViewHolder that knows about the Views we need to update
     * @param position the index into the array of movieArticles
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Asset asset = assets.get(position);

        holder.coinView.setText(asset.getId());
        holder.priceView.setText("$ "+String.valueOf(asset.getCurrentValue()));
        holder.changeView.setText(" "+String.valueOf(asset.getCurrentValue()));
        String coinImageUrl = "https://www.cryptocompare.com"+asset.getImageUrl();
        Log.d("Picture",coinImageUrl);
        Picasso.with(holder.imageView.getContext())
                .load(coinImageUrl).into(holder.imageView);

        // Attach a click listener that launches a new Activity
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                // Code for launching an Explicit Intent to go to another Activity in
//                // the same App.
//                Intent intent = new Intent(v.getContext(), DetailActivity.class);
//                intent.putExtra(ASSET, asset);
//
//                v.getContext().startActivity(intent);
//            }
//        });
        View.OnClickListener listener = new itemClicks(asset, holder, uid, position);
        holder.view.setOnClickListener(listener);

    }

    /**
     * RecyclerView wants to know how many list items there are, so it knows when it gets to the
     * end of the list and should stop scrolling.
     * @return the number of movieArticles in the array.
     */
    @Override
    public int getItemCount() {
        return assets.size();
    }

    private class itemClicks implements View.OnClickListener{

        private Asset asset;
        private ViewHolder holder;
        private String uid;
        private int position;

        public itemClicks(Asset asset, ViewHolder holder, String uid, int position){
            this.asset = asset;
            this.holder = holder;
            this.uid = uid;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            view.getContext().startActivity(intent);
        }



    }

    /**
     * A ViewHolder class for our adapter that 'caches' the references to the
     * subviews, so we don't have to look them up each time.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView coinView;
        public TextView priceView;
        public TextView changeView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            this.coinView = (TextView) itemView.findViewById(R.id.coinTextView);
            this.priceView = (TextView) itemView.findViewById(R.id.priceView);
            this.changeView = (TextView) itemView.findViewById(R.id.changeView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
