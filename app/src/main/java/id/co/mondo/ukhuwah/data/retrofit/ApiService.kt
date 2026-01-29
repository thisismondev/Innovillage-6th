package id.co.mondo.ukhuwah.data.retrofit

import id.co.mondo.ukhuwah.data.model.BalitaData
import id.co.mondo.ukhuwah.data.model.DataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("macros/s/AKfycbxk9sdtmL5KrXoO14VdS6QWHQBHRE3ldHAmDCcG7w0wUpfmBQ3dYXFZPZHVfMF5cend6w/exec")
    suspend fun kirimData(
        @Body data: List<BalitaData>
    ): Response<DataResponse>
}