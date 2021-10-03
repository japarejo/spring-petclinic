
package org.springframework.samples.petclinic.model.places;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "business_status",
    "geometry",
    "icon",
    "icon_background_color",
    "icon_mask_base_uri",
    "name",
    "opening_hours",
    "photos",
    "place_id",
    "plus_code",
    "price_level",
    "rating",
    "reference",
    "scope",
    "types",
    "user_ratings_total",
    "vicinity"
})
@Generated("jsonschema2pojo")
public class Place {

    @JsonProperty("business_status")
    private String businessStatus;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("icon_background_color")
    private String iconBackgroundColor;
    @JsonProperty("icon_mask_base_uri")
    private String iconMaskBaseUri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("opening_hours")
    private OpeningHours openingHours;
    @JsonProperty("photos")
    private List<Photo> photos = null;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("plus_code")
    private PlusCode plusCode;
    @JsonProperty("price_level")
    private Integer priceLevel;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("types")
    private List<String> types = null;
    @JsonProperty("user_ratings_total")
    private Integer userRatingsTotal;
    @JsonProperty("vicinity")
    private String vicinity;

    @JsonProperty("business_status")
    public String getBusinessStatus() {
        return businessStatus;
    }

    @JsonProperty("business_status")
    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("icon_background_color")
    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    @JsonProperty("icon_background_color")
    public void setIconBackgroundColor(String iconBackgroundColor) {
        this.iconBackgroundColor = iconBackgroundColor;
    }

    @JsonProperty("icon_mask_base_uri")
    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    @JsonProperty("icon_mask_base_uri")
    public void setIconMaskBaseUri(String iconMaskBaseUri) {
        this.iconMaskBaseUri = iconMaskBaseUri;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("opening_hours")
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    @JsonProperty("opening_hours")
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    @JsonProperty("photos")
    public List<Photo> getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @JsonProperty("place_id")
    public String getPlaceId() {
        return placeId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @JsonProperty("plus_code")
    public PlusCode getPlusCode() {
        return plusCode;
    }

    @JsonProperty("plus_code")
    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    @JsonProperty("price_level")
    public Integer getPriceLevel() {
        return priceLevel;
    }

    @JsonProperty("price_level")
    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    @JsonProperty("types")
    public void setTypes(List<String> types) {
        this.types = types;
    }

    @JsonProperty("user_ratings_total")
    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    @JsonProperty("user_ratings_total")
    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    @JsonProperty("vicinity")
    public String getVicinity() {
        return vicinity;
    }

    @JsonProperty("vicinity")
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

}
