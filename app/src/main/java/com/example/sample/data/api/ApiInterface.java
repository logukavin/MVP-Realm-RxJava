package com.example.sample.data.api;


import com.example.sample.data.response.user.UserResponse;
import com.example.sample.data.response.userrealm.UserResponses;

import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(ApiConstants.API_USER)
    Observable<UserResponses> getUser();


    @GET(ApiConstants.API_USER)
    Observable<UserResponse> getUserPager(@Query("page") int page);

}
