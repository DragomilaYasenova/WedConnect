package restaurant.amenity;

import java.util.Set;

public interface AmenityManager {
    void addAmenity(Amenity type);
    void removeAmenity(Amenity type);
    Set<Amenity> getAmenities();
}
