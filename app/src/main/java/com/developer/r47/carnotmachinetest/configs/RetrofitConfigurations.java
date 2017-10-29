package com.developer.r47.carnotmachinetest.configs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r47 on 29/10/17.
 */

public class RetrofitConfigurations {

    //base url for the calls to be made
    private static final String baseUrl = "https://jsonplaceholder.typicode.com/";
    public static final int INTERNAL_ERROR_CODE = 1001;
    public static final String INTERNAL_ERROR_MESSAGE = "Oops Something went wrong";

    /*creates the retrofit builder instance, with the base url of the api to be called, and the
    json converter factory. Now the json response from the api will be converted using the
    GsonConverterFactory into a POJO of the desired class
    * */
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());

    //get the instance of the retrofit
    public static Retrofit getRetrofitBuilderInstance() {
        return retrofitBuilder.build();
    }
}
