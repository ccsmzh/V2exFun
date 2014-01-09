package org.gino.v2exfun.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import org.gino.v2exfun.serialize.RequestManager;
import org.gino.v2exfun.serialize.model.Reply;
import org.gino.v2exfun.serialize.model.Topic;

import java.util.List;

import org.gino.v2exfun.R;

/**
 * Created by zhuohong on 13-12-1.
 */
public class TopicDetailAdapter extends BaseAdapter {

    private static final int ITEM_TYPE_TOP_DETAIL = 1;
    private static final int ITEM_TYPE_REPLY_DETAIL = 2;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private Topic mTopicData;
    private List<Reply> mRepliesdata;

    public TopicDetailAdapter(Context context, Topic topic, List<Reply> replies) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mTopicData = topic;
        this.mRepliesdata = replies;
    }

    @Override
    public int getCount() {
        if (mRepliesdata != null && mTopicData != null) {
            return mRepliesdata.size() + 1;
        }
        if (mTopicData != null) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (i == 0) {
            return mTopicData;
        } else {
            return mRepliesdata.get(i - 1);
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_TOP_DETAIL;
        } else {
            return ITEM_TYPE_REPLY_DETAIL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            switch (getItemViewType(i)) {
                case ITEM_TYPE_TOP_DETAIL:
                    view = mLayoutInflater.inflate(R.layout.item_topic_detail_top, null);
                    break;
                case ITEM_TYPE_REPLY_DETAIL:
                    view = mLayoutInflater.inflate(R.layout.item_topic_detail_reply, null);
                default:
                    break;
            }
        }

        switch (getItemViewType(i)) {
            case ITEM_TYPE_TOP_DETAIL:
                Topic tmpTopic = (Topic) getItem(i);
                ((TextView) view.findViewById(R.id.itdt_tv_title)).setText(tmpTopic.getTitle());
                ((TextView) view.findViewById(R.id.itdt_tv_content)).setText(tmpTopic.getContent());
                ((TextView) view.findViewById(R.id.itdt_tv_author_name)).setText(tmpTopic.getMember().getUsername());
                RequestManager.loadImage(tmpTopic.getMember().getAvatar_normal(), ImageLoader.getImageListener((ImageView) view.findViewById(R.id.itdt_iv_author_img), R.drawable.bg_head, R.drawable.bg_head));
                break;
            case ITEM_TYPE_REPLY_DETAIL:
                Reply tmpReply = (Reply) getItem(i);
                ((TextView)view.findViewById(R.id.itdr_tv_author_name)).setText(tmpReply.getMember().getUsername());
                ((TextView)view.findViewById(R.id.itdr_tv_reply_content)).setText(tmpReply.getContent());
                RequestManager.loadImage(tmpReply.getMember().getAvatar_normal(), ImageLoader.getImageListener((ImageView) view.findViewById(R.id.itdr_iv_author_img), R.drawable.bg_head, R.drawable.bg_head));
                break;
            default:
                break;
        }

        return view;
    }
}
