package com.example.sushigo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/database/insert.php")
    Call<Value> daftar(@Field("nama") String nama,
                       @Field("telepon") String telepon,
                       @Field("alamat") String alamat,
                       @Field("detail") String detail,
                       @Field("catatan") String catatan);
}

