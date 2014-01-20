package org.gino.v2exfun.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.gino.v2exfun.data.serialize.http.RequestApis;
import org.gino.v2exfun.data.serialize.model.Reply;
import org.gino.v2exfun.data.serialize.model.Topic;
import org.gino.v2exfun.ui.adapter.TopicDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import org.gino.v2exfun.R;

/**
 * Created by zhuohong on 13-12-1.
 */
public  class TopicDetailFragment extends BaseFragment {

    private ListView mContentListView;
    private TopicDetailAdapter mAdapter;

    private Topic mTopicData;
    private List<Reply> mRepliesData;

    public TopicDetailFragment(){

    }

    public TopicDetailFragment(Topic topic){
        this.mTopicData = topic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepliesData = new ArrayList<Reply>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_detail,container,false);

        mContentListView = (ListView) view.findViewById(R.id.ftd_lv_content);

        //特殊处理－防止顶部没有空隙
        View header = new View(getActivity());
        mContentListView.addHeaderView(header);

        mAdapter = new TopicDetailAdapter(getActivity(),mTopicData,mRepliesData);
        mContentListView.setAdapter(mAdapter);

        executeRequest(RequestApis.getInstance().getRepliesForTopicId(mTopicData.getId().intValue(),new Response.Listener<List<Reply>>() {
            @Override
            public void onResponse(List<Reply> replies) {
                mRepliesData.addAll(replies);
                if(mAdapter!= null){
                    mAdapter.notifyDataSetChanged();
                }
            }
        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
        return view;
    }


}