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
import org.gino.v2exfun.serialize.model.Topic;
import org.gino.v2exfun.utils.CommonUtils;

import java.util.List;

import org.gino.v2exfun.R;

/**
 * Created by kefeng on 13-11-23.
 */
public class LatestListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Topic> mTopics;
    private LayoutInflater mLayoutInflater;

    public LatestListAdapter(Context context, List<Topic> data) {
        this.mContext = context;
        this.mTopics = data;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mTopics.size();
    }

    @Override
    public Topic getItem(int i) {
        if (mTopics != null && mTopics.size() > 0) {
            return mTopics.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_latest_list, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.ill_tv_title);
            viewHolder.content = (TextView) view.findViewById(R.id.ill_tv_content);
            viewHolder.createTime = (TextView) view.findViewById(R.id.ill_tv_createtime);
            viewHolder.authorName = (TextView) view.findViewById(R.id.ill_tv_answer_author);
            viewHolder.authorImage = (ImageView) view.findViewById(R.id.ill_iv_head);
            viewHolder.commentNums = (TextView) view.findViewById(R.id.ill_tv_comment_number);
            viewHolder.sort = (TextView) view.findViewById(R.id.ill_tv_sort);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //标题
        viewHolder.title.setText(getItem(position).getTitle());

//        //内容缩略
//        String contentStr = getItem(position).getContent();
//        if (!TextUtils.isEmpty(contentStr)) {
//            String[] tmp = contentStr.split("\\r");
//            if (tmp.length > 0) {
//                viewHolder.content.setText(tmp[0]);
//            } else {
//                viewHolder.content.setText(getItem(position).getContent());
//            }
//        } else {
//            viewHolder.content.setText("");
//        }

        //创建时间
//        viewHolder.createTime.setText(CommonUtils.getLastTime(getItem(position).getCreated().longValue()));

        //作者用户名
        viewHolder.authorName.setText(getItem(position).getMember().getUsername());

        //读取头像
        RequestManager.loadImage(getItem(position).getMember().getAvatar_normal(), ImageLoader.getImageListener(viewHolder.authorImage, R.drawable.bg_head, R.drawable.bg_head));

        //评论数
        viewHolder.commentNums.setText(getItem(position).getReplies().intValue() + "");

        //分类
        viewHolder.sort.setText("#" + getItem(position).getNode().getTitle());
        return view;
    }

    private class ViewHolder {
        public TextView title;
        public TextView content;
        public TextView createTime;
        public TextView authorName;
        public ImageView authorImage;
        public TextView commentNums;
        public TextView sort;
    }
}
