package restaurant.amenity;

import java.util.LinkedHashSet;
import java.util.Set;

public class AmenityManagerImpl implements AmenityManager {
    private Set<Amenity> amenities = new LinkedHashSet<>();

    @Override
    public void addAmenity(Amenity type) {
        amenities.add(type);
    }

    @Override
    public void removeAmenity(Amenity type) {
        amenities.remove(type);
    }

    @Override
    public Set<Amenity> getAmenities() {
        return amenities;
    }
}
