package dad.geofx;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IpifyService {

    private static final String BASE_URL = "https://api.ipify.org";

    private IpifyInterface service;

    public IpifyService() {

        ConnectionPool pool = new ConnectionPool(1, 5, TimeUnit.SECONDS);
        
        
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(pool)
                // .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(IpifyInterface.class);
    }

    public String getPublicIp() throws Exception {
        
        Call<IpifyResponse> call = service.getPublicIp();
        
        Response<IpifyResponse> response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            return response.body().getIp();
        } else {
            throw new Exception("Error retrieving IP: " + response.errorBody().string());
        }
    }

}
