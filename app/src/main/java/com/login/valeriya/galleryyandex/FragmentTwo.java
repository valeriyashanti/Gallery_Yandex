package com.login.valeriya.galleryyandex;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

// фрагмент 2 и его вьюха
public class FragmentTwo extends Fragment {


    public static FragmentTwo getInstance (String content, int imageId){
        FragmentTwo fragmentTwo = new FragmentTwo();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        bundle.putInt("imageId",imageId);
        fragmentTwo.setArguments(bundle);
        return fragmentTwo;
    }

    TextView myText;
    ImageView mImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container,false);

        Bundle bundle = getArguments();
        String myContent = bundle.getString("content", "");
        int imageId = bundle.getInt("imageId");


        mImageView = (ImageView) view.findViewById(R.id.image_about);
        mImageView.setImageResource(R.drawable.me);
        myText= (TextView) view.findViewById(R.id.myText);
        myText.setText(myContent);
        return view;

    }
}
