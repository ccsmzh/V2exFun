
package org.gino.v2exfun.serialize.model;

import org.gino.v2exfun.utils.CommonUtils;

import java.io.Serializable;

public class Topic implements Serializable{
   	private String content;
   	private String content_rendered;
   	private Number created;
   	private Number id;
   	private Number last_modified;
   	private Number last_touched;
   	private Member member;
   	private Node node;
   	private Number replies;
   	private String title;
   	private String url;

    private String createdTimeShowStr;

 	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
 	public String getContent_rendered(){
		return this.content_rendered;
	}
	public void setContent_rendered(String content_rendered){
		this.content_rendered = content_rendered;
	}
 	public Number getCreated(){
		return this.created;
	}
	public void setCreated(Number created){
		this.created = created;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public Number getLast_modified(){
		return this.last_modified;
	}
	public void setLast_modified(Number last_modified){
		this.last_modified = last_modified;
	}
 	public Number getLast_touched(){
		return this.last_touched;
	}
	public void setLast_touched(Number last_touched){
		this.last_touched = last_touched;
	}
 	public Member getMember(){
		return this.member;
	}
	public void setMember(Member member){
		this.member = member;
	}
 	public Node getNode(){
		return this.node;
	}
	public void setNode(Node node){
		this.node = node;
	}
 	public Number getReplies(){
		return this.replies;
	}
	public void setReplies(Number replies){
		this.replies = replies;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}

    public String getShowCreatedTime(){
        if(created != null){
            createdTimeShowStr = CommonUtils.getLastTime(getCreated().longValue());
        }
        return createdTimeShowStr;
    }

    public void setCreatedTimeShowStr(String createdTimeShowStr) {
        this.createdTimeShowStr = createdTimeShowStr;
    }
}
