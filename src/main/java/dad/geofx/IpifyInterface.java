package dad.geofx;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpifyInterface {

    @GET("?format=json")
    Call<IpifyResponse> getPublicIp();

}

