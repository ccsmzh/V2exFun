package org.gino.v2exfun.ui.model.event;

import org.gino.v2exfun.serialize.model.Topic;

import java.util.List;

/**
 * Created by hongzhuo on 14-1-13.
 */
public interface LatestListUiModelEvent extends BaseUiModelEvent{
    void onListViewShow(List<Topic> topics);
}
