package dad.geofx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoIPAPI {
    @GET("ip_api.php")
    Call<GeoIpResponse> getIpDetails(@Query("ip") String ipAddress);
}



