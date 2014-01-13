package org.gino.v2exfun.ui.model;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.gino.v2exfun.serialize.RequestApis;
import org.gino.v2exfun.serialize.model.Topic;
import org.gino.v2exfun.ui.TopicDetailActivity;
import org.gino.v2exfun.ui.adapter.LatestListAdapter;
import org.gino.v2exfun.ui.model.event.LatestListUiModelEvent;
import org.gino.v2exfun.utils.AsyncUtils;

import java.util.List;

/**
 * Created by hongzhuo on 14-1-13.
 */
public class LatestListUiModel extends BaseUiModel<LatestListUiModelEvent> {
    public void showListView() {
        executeRequest(RequestApis.getInstance().getLatestTopics(new Response.Listener<List<Topic>>() {
                                                                     @Override
                                                                     public void onResponse(List<Topic> topics) {
                                                                         for (LatestListUiModelEvent event : LatestListUiModel.this) {
                                                                            event.onListViewShow(topics);
                                                                         }
                                                                     }
                                                                 }, new Response.ErrorListener() {
                                                                     @Override
                                                                     public void onErrorResponse(VolleyError volleyError) {

                                                                     }
                                                                 }
        ));
    }
}
