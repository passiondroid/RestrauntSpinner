package com.app.restrauntspinner.results;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;

import com.app.restrauntspinner.R;
import com.app.restrauntspinner.model.FourSquareResponse;
import com.app.restrauntspinner.util.Constants;

public class ResultsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ResultViewPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        int px = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        viewPager.setPageMargin(px);
        viewPager.setPageMarginDrawable(R.color.background);

        FourSquareResponse fourSquareResponse = getIntent().getParcelableExtra(Constants.RESPONSE);

        adapterViewPager = new ResultViewPagerAdapter(getSupportFragmentManager(),
                fourSquareResponse.getResponse().getGroups().get(0).getItems());
        viewPager.setAdapter(adapterViewPager);
    }
}
