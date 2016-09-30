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
import com.xinxidu.xxd.adapter.BullionCommentAdapter;
import com.xinxidu.xxd.event.BullionCommentEvent;
import com.xinxidu.xxd.utils.FullyLinearLayoutManager;

import java.util.ArrayList;

/**
 * Created by limingquan on 2016/9/5.
 */
public class BullionCommentFragment extends Fragment{
    BullionCommentAdapter mBullionCommentAdapter;

    private ArrayList<BullionCommentEvent> mItem;
    private RecyclerView mRecyclerView;

    public BullionCommentFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bullion_comment_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItem = new ArrayList<BullionCommentEvent>();
        mItem.add(null);

        mBullionCommentAdapter = new BullionCommentAdapter(getActivity());
        mRecyclerView.setAdapter(mBullionCommentAdapter);
        mBullionCommentAdapter.setData(mItem);
        mBullionCommentAdapter.notifyDataSetChanged();
        return view;
    }
}
