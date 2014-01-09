
package org.gino.v2exfun.serialize.model;

import java.io.Serializable;

public class Reply implements Serializable{
   	private String content;
   	private String content_rendered;
   	private Number created;
   	private Number id;
   	private Number last_modified;
   	private Member member;
   	private Number thanks;

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
 	public Member getMember(){
		return this.member;
	}
	public void setMember(Member member){
		this.member = member;
	}
 	public Number getThanks(){
		return this.thanks;
	}
	public void setThanks(Number thanks){
		this.thanks = thanks;
	}
}
