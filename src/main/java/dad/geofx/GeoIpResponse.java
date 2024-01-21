package dad.geofx;

import java.util.Currency;
import java.util.List;
import java.util.TimeZone;

public class GeoIpResponse {
    private String ip;
    private String hostname;
    private String type;
    private Connection connection;
    private Security security;
    
    private Location location;
    
 // Campos nuevos para la pestaña "Location"
    private double latitude;
    private double longitude;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip;
    private List<Language> languages;
    private String calling_code;
    private TimeZone time_zone;
    private Currency currency;
    

    // Constructor
    public GeoIpResponse(String ip, String hostname, String type, Connection connection) {
        this.ip = ip;
        this.hostname = hostname;
        this.type = type;
        this.connection = connection;
    }

    // Getters y setters
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static class Connection {
        private int asn;
        private String isp;

        // Constructor
        public Connection(int asn, String isp) {
            this.asn = asn;
            this.isp = isp;
        }

        // Getters y setters
        public int getAsn() {
            return asn;
        }

        public void setAsn(int asn) {
            this.asn = asn;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }
    }
    
    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    // Clase interna para la información de seguridad
    public static class Security {
        private boolean is_proxy;
        private boolean is_crawler;
        private boolean is_tor;
        private String threat_level;
        private List<String> threat_types;

        // Getters y setters
        public boolean isProxy() {
            return is_proxy;
        }

        public void setProxy(boolean is_proxy) {
            this.is_proxy = is_proxy;
        }

        public boolean isCrawler() {
            return is_crawler;
        }

        public void setCrawler(boolean is_crawler) {
            this.is_crawler = is_crawler;
        }

        public boolean isTor() {
            return is_tor;
        }

        public void setTor(boolean is_tor) {
            this.is_tor = is_tor;
        }

        public String getThreatLevel() {
            return threat_level;
        }

        public void setThreatLevel(String threat_level) {
            this.threat_level = threat_level;
        }

        public List<String> getThreatTypes() {
            return threat_types;
        }

        public void setThreatTypes(List<String> threat_types) {
            this.threat_types = threat_types;
        }
    }
    
    //TO DO LO NUEVO
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public String getCountryName() {
        return country_name;
    }

    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    public String getRegionCode() {
        return region_code;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public String getRegionName() {
        return region_name;
    }

    public void setRegionName(String region_name) {
        this.region_name = region_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getCallingCode() {
        return calling_code;
    }

    public void setCallingCode(String calling_code) {
        this.calling_code = calling_code;
    }

    public TimeZone getTimeZone() {
        return time_zone;
    }

    public void setTimeZone(TimeZone time_zone) {
        this.time_zone = time_zone;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
 // Clase interna para la información de idioma
    public static class Language {
        private String code;
        private String name;
        private String nativeName;

        // Constructor
        public Language(String code, String name, String nativeName) {
            this.code = code;
            this.name = name;
            this.nativeName = nativeName;
        }

        // Getters y setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNativeName() {
            return nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }
    }

    // Clase interna para la información de la zona horaria
    public static class TimeZone {
        private String id;
        private String current_time;
        private int gmt_offset;
        private String code;
        private boolean is_daylight_saving;

        // Constructor
        public TimeZone(String id, String current_time, int gmt_offset, String code, boolean is_daylight_saving) {
            this.id = id;
            this.current_time = current_time;
            this.gmt_offset = gmt_offset;
            this.code = code;
            this.is_daylight_saving = is_daylight_saving;
        }

        // Getters y setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCurrentTime() {
            return current_time;
        }

        public void setCurrentTime(String current_time) {
            this.current_time = current_time;
        }

        public int getGmtOffset() {
            return gmt_offset;
        }

        public void setGmtOffset(int gmt_offset) {
            this.gmt_offset = gmt_offset;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isDaylightSaving() {
            return is_daylight_saving;
        }

        public void setDaylightSaving(boolean is_daylight_saving) {
            this.is_daylight_saving = is_daylight_saving;
        }
    }

    // Clase interna para la información de la moneda
    public static class Currency {
        private String code;
        private String name;
        private String symbol;

        // Constructor
        public Currency(String code, String name, String symbol) {
            this.code = code;
            this.name = name;
            this.symbol = symbol;
        }

        // Getters y setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
    
    //////NUEVO
    
 // Getters y setters para location
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Clase interna para Location
    public static class Location {
        private List<Language> languages;
        private String calling_code;
        // Posiblemente otros campos que también estén dentro de 'location' en tu JSON

        // Getters y setters para los campos de Location
        public List<Language> getLanguages() {
            return languages;
        }

        public void setLanguages(List<Language> languages) {
            this.languages = languages;
        }

        public String getCallingCode() {
            return calling_code;
        }

        public void setCallingCode(String calling_code) {
            this.calling_code = calling_code;
        }

        // ... otros getters y setters ...
    }
    
 
}


