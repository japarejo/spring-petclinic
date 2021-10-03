
package org.springframework.samples.petclinic.model.places;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "northeast",
    "southwest"
})
@Generated("jsonschema2pojo")
public class Viewport {

    @JsonProperty("northeast")
    private Location northeast;
    @JsonProperty("southwest")
    private Location southwest;

    @JsonProperty("northeast")
    public Location getNortheast() {
        return northeast;
    }

    @JsonProperty("northeast")
    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    @JsonProperty("southwest")
    public Location getSouthwest() {
        return southwest;
    }

    @JsonProperty("southwest")
    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }

}
