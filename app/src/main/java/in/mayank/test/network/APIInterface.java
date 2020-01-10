package in.mayank.test.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    public static String API_DOMAIN = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    public static String MASTER_JSON = "facts.json";

    @GET(API_DOMAIN + MASTER_JSON)
    public Call<ResponseBody> getNetworkData();
}

