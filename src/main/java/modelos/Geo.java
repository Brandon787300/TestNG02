package modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("geo")
public class Geo {
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;

    public double getLng() {
        return lng;
    }
}
