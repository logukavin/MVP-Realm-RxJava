package com.example.sample.data.response.userrealm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class UserResponses extends RealmObject {

	private int per_page;

	private int total;

	private RealmList<DataItems> data;

	private int page;

	private int total_pages;

	private Supports support;

	public void setPerPage(int perPage){
		this.per_page = perPage;
	}

	public int getPerPage(){
		return per_page;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(RealmList<DataItems> data){
		this.data = data;
	}

	public RealmList<DataItems> getData(){
		return data;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.total_pages = totalPages;
	}

	public int getTotalPages(){
		return total_pages;
	}

	public void setSupport(Supports support){
		this.support = support;
	}

	public Supports getSupport(){
		return support;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"per_page = '" + per_page + '\'' +
			",total = '" + total + '\'' + 
			",data = '" + data + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + total_pages + '\'' +
			",support = '" + support + '\'' + 
			"}";
		}
}