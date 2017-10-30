package au.com.miacucina.com.foodtour.REST;

import java.util.List;
import au.com.miacucina.com.foodtour.Model.TourLocationModel;

public interface TourLocation {

    String getLocation(String locationCode) throws InterruptedException;

}
