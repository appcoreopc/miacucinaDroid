package au.com.miacucina.com.foodtour.appDataResource;

import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;

public class AppMenu {

    public static List<?> getMenu()
    {
        List<ItemDisplay> itemList = new ArrayList<>();

        ItemDisplay creditCoupons = new ItemDisplay("Credit & Coupons", "title info1", "description1", "imageUrl");
        ItemDisplay inviteFriends = new ItemDisplay("Invite friends", "title info1", "description1", "imageUrl");
        ItemDisplay settings = new ItemDisplay("Settings", "title info1", "description1", "imageUrl");
        ItemDisplay feedback = new ItemDisplay("Give us feeback", "title info1", "description1", "imageUrl");

        itemList.add(creditCoupons);
        itemList.add(inviteFriends);
        itemList.add(settings);
        itemList.add(feedback);

        return itemList;
    }

}
