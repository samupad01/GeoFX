package dad.geofx;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeoIpService {

    private GeoIPAPI geoServiceApi;

    public GeoIpService() {
        Retrofit retrofitIpapi = new Retrofit.Builder()
            .baseUrl("https://ipapi.com/") // Actualizado a la nueva URL base
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        geoServiceApi = retrofitIpapi.create(GeoIPAPI.class);
    }

    public GeoIPAPI getGeoServiceApi() {
        return geoServiceApi;
    }
}

