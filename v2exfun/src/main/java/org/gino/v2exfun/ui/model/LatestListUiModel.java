package org.gino.v2exfun.ui.model;

import android.os.AsyncTask;

import org.gino.v2exfun.parser.V2exTopicsParser;
import org.gino.v2exfun.data.serialize.model.Topic;
import org.gino.v2exfun.ui.model.event.LatestListUiModelEvent;
import org.gino.v2exfun.utils.AsyncUtils;

import java.util.List;

/**
 * Created by hongzhuo on 14-1-13.
 */
public abstract class LatestListUiModel extends BaseUiModel{
    public void showListView() {
//        executeRequest(RequestApis.getInstance().getLatestTopics(new Response.Listener<List<Topic>>() {
//                                                                     @Override
//                                                                     public void onResponse(List<Topic> topics) {
//                                                                         for (LatestListUiModelEvent event : LatestListUiModel.this) {
//                                                                            event.onListViewShow(topics);
//                                                                         }
//                                                                     }
//                                                                 }, new Response.ErrorListener() {
//                                                                     @Override
//                                                                     public void onErrorResponse(VolleyError volleyError) {
//
//                                                                     }
//                                                                 }
//        ));
//        AsyncTask task = new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] params) {
//
//                return null;
//            }
//        };
//        task.execute()
        AsyncUtils.excuteAsyncTask(new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                V2exTopicsParser parser = new V2exTopicsParser();
                List<Topic> topics = parser.getV2exNearly50Tipics();


                return topics;
            }

            @Override
            protected void onPostExecute(Object o) {
                List<Topic> topics = (List<Topic>) o;
//                for (LatestListUiModelEvent event : LatestListUiModel.this) {
//                    event.onListViewShow(topics);
//                }
                onListViewShow(topics);
                super.onPostExecute(o);
            }
        });

    }

    abstract protected void onListViewShow(List<Topic> data);

}
