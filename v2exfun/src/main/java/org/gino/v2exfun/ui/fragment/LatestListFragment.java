package org.gino.v2exfun.ui.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.gino.v2exfun.serialize.RequestApis;
import org.gino.v2exfun.serialize.model.Topic;
import org.gino.v2exfun.ui.TopicDetailActivity;
import org.gino.v2exfun.ui.adapter.LatestListAdapter;
import org.gino.v2exfun.ui.model.LatestListUiModel;
import org.gino.v2exfun.ui.model.event.LatestListUiModelEvent;
import org.gino.v2exfun.utils.AsyncUtils;

import java.util.List;

import org.gino.v2exfun.R;

/**
 * Created by kefeng on 13-11-21.
 */
public class LatestListFragment extends BaseFragment {
    private ListView mContentListView;
    private LatestListAdapter mListViewAdapter;

    private LatestListUiModelEvent mEvent;
    private LatestListUiModel mUIModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_list, container, false);

        mContentListView = (ListView) view.findViewById(R.id.fll_lv_content);

        //特殊处理－防止顶部没有空隙
        View header = new View(getActivity());
        mContentListView.addHeaderView(header);

        mEvent = new LatestListUiModelEvent() {
            @Override
            public void onListViewShow(List<Topic> topics) {
                mListViewAdapter = new LatestListAdapter(getActivity(), topics);
                mContentListView.setAdapter(mListViewAdapter);
                mContentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), TopicDetailActivity.class);
                        intent.putExtra("topic", mListViewAdapter.getItem(i - 1));
                        getActivity().startActivity(intent);
                    }
                });

            }
        };
        mUIModel = new LatestListUiModel();
        mUIModel.regEvent(mEvent);

        mUIModel.showListView();
        return view;
    }
}