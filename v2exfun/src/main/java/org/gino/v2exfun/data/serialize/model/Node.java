
package org.gino.v2exfun.data.serialize.model;

import java.io.Serializable;

public class Node implements Serializable{
   	private String avatar_large;
   	private String avatar_mini;
   	private String avatar_normal;
   	private Number id;
   	private String name;
   	private String title;
   	private String title_alternative;
   	private Number topics;
   	private String url;

 	public String getAvatar_large(){
		return this.avatar_large;
	}
	public void setAvatar_large(String avatar_large){
		this.avatar_large = avatar_large;
	}
 	public String getAvatar_mini(){
		return this.avatar_mini;
	}
	public void setAvatar_mini(String avatar_mini){
		this.avatar_mini = avatar_mini;
	}
 	public String getAvatar_normal(){
		return this.avatar_normal;
	}
	public void setAvatar_normal(String avatar_normal){
		this.avatar_normal = avatar_normal;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getTitle_alternative(){
		return this.title_alternative;
	}
	public void setTitle_alternative(String title_alternative){
		this.title_alternative = title_alternative;
	}
 	public Number getTopics(){
		return this.topics;
	}
	public void setTopics(Number topics){
		this.topics = topics;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
