package com.example.sample.data.response.userrealm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Supports extends RealmObject {

	private String text;

	private String url;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Support{" + 
			"text = '" + text + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}