package au.com.miacucina.com.foodtour.appDataResource;

import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.model.ItemDisplay;

public class AppMenu {

    public static List<?> populateData() {

        ArrayList itemList = new ArrayList<>();

        ItemDisplay a = new ItemDisplay("title1", "title info1", "description1", "imageUrl");
        ItemDisplay b = new ItemDisplay("title2", "title info1", "description1", "imageUrl");
        ItemDisplay c = new ItemDisplay("title3", "title info1", "description1", "imageUrl");
        ItemDisplay d = new ItemDisplay("title4", "title info1", "description1", "imageUrl");
        ItemDisplay e = new ItemDisplay("title5", "title info1", "description1", "imageUrl");
        ItemDisplay f = new ItemDisplay("title6", "title info1", "description1", "imageUrl");
        ItemDisplay g = new ItemDisplay("title7", "title info1", "description1", "imageUrl");
        ItemDisplay h = new ItemDisplay("title7", "title info1", "description1", "imageUrl");
        ItemDisplay i = new ItemDisplay("title7", "title info1", "description1", "imageUrl");
        ItemDisplay j = new ItemDisplay("title7", "title info1", "description1", "imageUrl");
        ItemDisplay k = new ItemDisplay("title7", "title info1", "description1", "imageUrl");

        itemList.add(a);
        itemList.add(b);
        itemList.add(c);
        itemList.add(d);
        itemList.add(e);
        itemList.add(f);
        itemList.add(g);

        itemList.add(h);
        itemList.add(i);
        itemList.add(j);
        itemList.add(k);

        return itemList;
    }

    public static List<?> getMenu() {
        List<ItemDisplay> itemList = new ArrayList<>();

        ItemDisplay creditCoupons = new ItemDisplay("Credit & Coupons", "", "", "1");
        ItemDisplay inviteFriends = new ItemDisplay("Invite friends", "", "", "2");
        ItemDisplay settings = new ItemDisplay("Settings", "", "", "3");
        ItemDisplay feedback = new ItemDisplay("Give us feedback", "", "", "4");

        itemList.add(creditCoupons);
        itemList.add(inviteFriends);
        itemList.add(settings);
        itemList.add(feedback);

        return itemList;
    }
}
