package com.xinxidu.xxd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxidu.xxd.R;
import com.xinxidu.xxd.adapter.TimeNewsAdapter;
import com.xinxidu.xxd.event.TimeNewsEvent;
import com.xinxidu.xxd.utils.FullyLinearLayoutManager;

import java.util.ArrayList;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class TimeNewsFragment extends Fragment {
    TimeNewsAdapter mTimeNewsAdapter;

    private ArrayList<TimeNewsEvent> mItem;
    private RecyclerView mRecyclerView;

    public TimeNewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_news_gragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<TimeNewsEvent>();
        mItem.add(null);

        mTimeNewsAdapter = new TimeNewsAdapter(getActivity());
        mRecyclerView.setAdapter(mTimeNewsAdapter);
        mTimeNewsAdapter.setData(mItem);
        mTimeNewsAdapter.notifyDataSetChanged();
        return view;
    }
}
