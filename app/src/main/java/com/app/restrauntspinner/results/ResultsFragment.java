package com.app.restrauntspinner.results;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.restrauntspinner.R;
import com.app.restrauntspinner.model.FourSquareResponse;
import com.app.restrauntspinner.model.Item;
import com.app.restrauntspinner.util.Constants;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    private Item item;

    private ImageView restrauntIV;

    private TextView nameTV, reviewTV, adddressTV, readReviewTV, ratingTV;

    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResultsFragment.
     */
    public static ResultsFragment newInstance(Item response) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.RESPONSE, response);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable(Constants.RESPONSE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        restrauntIV = (ImageView) view.findViewById(R.id.restrauntIV);
        nameTV = (TextView) view.findViewById(R.id.name);
        readReviewTV = (TextView) view.findViewById(R.id.readReviewTV);
        reviewTV = (TextView) view.findViewById(R.id.reviewTV);
        ratingTV = (TextView) view.findViewById(R.id.ratingTV);
        adddressTV = (TextView) view.findViewById(R.id.adddressTV);

        nameTV.setText(item.getVenue().getName());

        if(item.getVenue().getLocation().getFormattedAddress() != null){
            adddressTV.setText(item.getVenue().getLocation().getFormattedAddress().toString().replaceAll("\\[","").replaceAll("]","").trim());
        }else if(item.getVenue().getLocation().getAddress() != null){
            adddressTV.setText(item.getVenue().getLocation().getAddress().replaceAll("\\[","").replaceAll("]","").trim());
        }else{
            adddressTV.setText("No address found");
        }

        if(item.getTips() !=null && item.getTips().size() > 0) {
            reviewTV.setText(item.getTips().get(0).getText());
        }else{
            reviewTV.setText("No reviews found");
        }

        if(item.getVenue().getRating() != null && item.getVenue().getRating() != 0) {
            ratingTV.setText(Double.toString(item.getVenue().getRating()));
        }else{
            ratingTV.setVisibility(View.GONE);
        }

        if(item.getTips() !=null && item.getTips().get(0).getCanonicalUrl() != null){
            readReviewTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = item.getTips().get(0).getCanonicalUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }else{
            readReviewTV.setVisibility(View.GONE);
        }

        String imageUrl = null;
        if(item.getVenue().getPhotos().getGroups().size() > 0) {
            if(item.getVenue().getPhotos().getGroups().get(0).getItems().size() > 0) {
                String prefix = item.getVenue().getPhotos().getGroups().get(0).getItems().get(0).getPrefix();
                String suffix = item.getVenue().getPhotos().getGroups().get(0).getItems().get(0).getSuffix();
                imageUrl = prefix + "width500"  +suffix;
            }
        }

        if(imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .crossFade()
                    .into(restrauntIV);
        }

        return view;
    }

}
