package com.brianneldon.ilovezappos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Brian on 2/1/2017.
 * Client that queries Zappos API.
 */

public interface ZapposService {
    @GET("Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<ResponseObject> listResults(@Query("term") String query);
}