package com.example.sample.data.response.userrealm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DataItems extends RealmObject {

	private String last_name;
	@PrimaryKey
	private int id;

	private String avatar;

	private String first_name;

	private String email;

	public void setLastName(String lastName){
		this.last_name = lastName;
	}

	public String getLastName(){
		return last_name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setFirstName(String firstName){
		this.first_name = firstName;
	}

	public String getFirstName(){
		return first_name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"last_name = '" + last_name + '\'' +
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",first_name = '" + first_name + '\'' +
			",email = '" + email + '\'' + 
			"}";
		}
}