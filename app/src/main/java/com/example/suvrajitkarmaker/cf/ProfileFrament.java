package com.example.suvrajitkarmaker.cf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFrament extends Fragment {
    ImageView img;
    TextView tx,cnt,ranting,maxrating,rank,maxrank;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        View view= inflater.inflate(R.layout.fragment_profile,container,false);
        tx=view.findViewById(R.id.prfilename);
        tx.setText(fetchData.fullname);

        cnt=view.findViewById(R.id.profilecontry);
        cnt.setText(fetchData.city);

        ranting=view.findViewById(R.id.profilerating);
        ranting.setText("Current Contest rating: "+Integer.toString(fetchData.rating));

        rank=view.findViewById(R.id.profilerank);
        rank.setText("Current Contest rank: "+ fetchData.rank);

        maxrating=view.findViewById(R.id.profilemaxrating);
        maxrating.setText("Max Contest rating: "+Integer.toString(fetchData.maxRating));

        maxrank=view.findViewById(R.id.profilemaxrank);
        maxrank.setText("Max Contest rank: "+fetchData.maxRank);

        new DownloadImageFromInternet((ImageView) view.findViewById(R.id.profilepicture))
                .execute("https:"+fetchData.titlePhoto);



        return view;
    }
}
