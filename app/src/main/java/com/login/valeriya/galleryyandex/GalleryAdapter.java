package com.login.valeriya.galleryyandex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private Context mContext;
    private ArrayList<GalleryItem> mGalleryList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    // конструктор
    public GalleryAdapter (Context context, ArrayList<GalleryItem> exampleList){
        mContext = context;
        mGalleryList = exampleList;
    }

    // ViewHolder для адаптера
    @Override
    public GalleryViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent,false);
        return new GalleryViewHolder(v);
    }

    @Override
    public void onBindViewHolder( GalleryViewHolder holder, int position) {
        GalleryItem currentItem = mGalleryList.get(position);

        String imageUrl = currentItem.getImageUrl();
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    // определяем размер нашего списка
    @Override
    public int getItemCount() {
        return mGalleryList.size();
    }


    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
