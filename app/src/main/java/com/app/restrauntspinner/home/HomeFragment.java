package com.app.restrauntspinner.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.restrauntspinner.R;
import com.app.restrauntspinner.spinner.SpinnerActivity;
import com.app.restrauntspinner.util.Constants;

import static dagger.internal.Preconditions.checkNotNull;

/**
 *
 */
public class HomeFragment extends Fragment implements HomeContract.View, View.OnClickListener {


    private HomeContract.Presenter mPresenter;
    private Button breakfastBtn, lunchBtn, dinnerBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        breakfastBtn = (Button)view.findViewById(R.id.breakfastBtn);
        lunchBtn = (Button)view.findViewById(R.id.lunchBtn);
        dinnerBtn = (Button)view.findViewById(R.id.dinnerBtn);

        breakfastBtn.setOnClickListener(this);
        lunchBtn.setOnClickListener(this);
        dinnerBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void startSpinnerActivity(@Constants.FoodType int type) {
        Intent intent = new Intent(getActivity(), SpinnerActivity.class);
        intent.putExtra(Constants.TYPE, type);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.breakfastBtn:
                mPresenter.onBreakfastClicked();
                break;
            case R.id.lunchBtn:
                mPresenter.onLunchClicked();
                break;
            case R.id.dinnerBtn:
                mPresenter.onDinnerClicked();
                break;
        }
    }
}
