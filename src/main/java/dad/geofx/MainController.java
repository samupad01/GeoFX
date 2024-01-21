package dad.geofx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.concurrent.Task;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import retrofit2.Call;
import retrofit2.Response;

public class MainController implements Initializable {

    private IpifyService ipifyService = new IpifyService();
    private GeoIpService geoIpService = new GeoIpService();

    @FXML
    private BorderPane view;

    @FXML
    private TextField ipTextField;

    //Componentes de la ventana 'Connection'.
    @FXML
    private Label ipLabel;
    @FXML
    private Label ispLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label asnLabel;
    @FXML
    private Label hostnameLabel;
    @FXML
    private Button checkIpButton;
    
    //Componentes para la ventana Security
    
    @FXML
    private CheckBox proxyCheck;
    @FXML
    private CheckBox crawlerCheck;
    @FXML
    private CheckBox torCheck;
    @FXML
    private Label threatLevelLabel;
    @FXML
    private Label potentialThreatTypesLabel;
    @FXML
    private Label safeLabel;
    
    //Campos nuevos
    @FXML
    private Label latitudeLabel;
    @FXML
    private Label longitudeLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label zipLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label callingCodeLabel;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Label currencyLabel;
    @FXML
    private ImageView flagImageView;
    
 

    public MainController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
        fetchPublicIp();
    }

    private void fetchPublicIp() {
        Task<String> getIpTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                return ipifyService.getPublicIp();
            }
        };

        getIpTask.setOnSucceeded(event -> {
            String publicIp = getIpTask.getValue();
            ipTextField.setText(publicIp);
            fetchIpDetails(publicIp);
        });

        getIpTask.setOnFailed(event -> {
            ipTextField.setText("Error al obtener la IP");
        });

        new Thread(getIpTask).start();
    }

    private void fetchIpDetails(String ipAddress) {
        Task<GeoIpResponse> task = new Task<>() {
            @Override
            protected GeoIpResponse call() throws Exception {
                Call<GeoIpResponse> call = geoIpService.getGeoServiceApi().getIpDetails(ipAddress);
                Response<GeoIpResponse> response = call.execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Error al obtener detalles de la IP: " + response.errorBody().string());
                }
                return response.body();
            }
        };

        task.setOnSucceeded(event -> {
            GeoIpResponse geoIpResponse = task.getValue();
            ipLabel.setText(geoIpResponse.getIp());
            ispLabel.setText(geoIpResponse.getConnection().getIsp());
            typeLabel.setText(geoIpResponse.getType());
            asnLabel.setText(String.valueOf(geoIpResponse.getConnection().getAsn()));
            hostnameLabel.setText(geoIpResponse.getHostname());
            
            updateSecurityTab(geoIpResponse.getSecurity());
            updateLocationTab(geoIpResponse);
           
        });

        task.setOnFailed(event -> {
            ipLabel.setText("Error al obtener detalles de la IP");
        });

        new Thread(task).start();
    }
    
    @FXML
    private void onCheckIpAction() {
        String manualIp = ipTextField.getText();
        if (manualIp != null && !manualIp.isEmpty()) {
            fetchIpDetails(manualIp);
        }
    }
    
    private void updateSecurityTab(GeoIpResponse.Security security) {
        proxyCheck.setSelected(security.isProxy());
        crawlerCheck.setSelected(security.isCrawler());
        torCheck.setSelected(security.isTor());
        threatLevelLabel.setText(security.getThreatLevel());

        // Establecer el texto para el label safeLabel basado en el nivel de amenaza
        String threatLevelText;
        switch (security.getThreatLevel().toLowerCase()) {
            case "low":
                threatLevelText = "This IP is safe. No threats have been detected.";
                break;
            case "medium":
                threatLevelText = "Potential threats detected. This IP may be unsafe.";
                break;
            case "high":
                threatLevelText = "Definitive threats detected. This IP is unsafe.";
                break;
            default:
                threatLevelText = "Threat level unknown.";
                break;
        }
        
        safeLabel.setText(threatLevelText);  // Asegúrate de que safeLabel está definido en tu clase con @FXML
        potentialThreatTypesLabel.setText(formatThreatTypes(security.getThreatTypes()));
    }

    private String formatThreatTypes(List<String> threatTypes) {
        if (threatTypes == null || threatTypes.isEmpty()) {
            return "No threats have been detected for this IP address.";
        }
        // Aquí se juntan los tipos de amenazas en una cadena, separados por coma.
        return String.join(", ", threatTypes);
    }
    
    private void updateLocationTab(GeoIpResponse response) {
        latitudeLabel.setText(String.format("%.6f", response.getLatitude()));
        longitudeLabel.setText(String.format("%.6f", response.getLongitude()));
        countryLabel.setText(response.getCountryName() + " (" + response.getCountryCode() + ")");
        cityLabel.setText(response.getCity());
        zipLabel.setText(response.getZip());

        if (response.getLocation() != null) {
            // Actualizar el campo de idioma
            if (response.getLocation().getLanguages() != null && !response.getLocation().getLanguages().isEmpty()) {
                languageLabel.setText(response.getLocation().getLanguages().get(0).getName());
            } else {
                languageLabel.setText("Unknown");
            }

            // Actualizar el campo de código de llamada
            if (response.getLocation().getCallingCode() != null && !response.getLocation().getCallingCode().trim().isEmpty()) {
                callingCodeLabel.setText("+" + response.getLocation().getCallingCode());
            } else {
                callingCodeLabel.setText("Not available");
            }
        }
        timeZoneLabel.setText(response.getTimeZone().getId());
        currencyLabel.setText(response.getCurrency().getName());

        // Cargar la imagen de la bandera
        String flagImagePath = "/images/banderas/" + response.getCountryCode().toUpperCase() + ".png";
        Image flagImage = new Image(getClass().getResourceAsStream(flagImagePath));
        flagImageView.setImage(flagImage);
    }



    public BorderPane getView() {
        return view;
    }
}

    












