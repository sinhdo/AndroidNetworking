package com.example.bai2.demo4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsert {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<SvrResponseInsert> insertPrd(

        @Field("name") String name,
        @Field("price") String price,
        @Field("description") String description
    );

}
