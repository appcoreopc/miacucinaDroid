package au.com.miacucina.com.foodtour.appDataResource;

import java.util.ArrayList;
import java.util.List;

import au.com.miacucina.com.foodtour.Model.ItemDisplay;

public class AppMenu {

    public static List<?> getMenu()
    {
        List<ItemDisplay> itemList = new ArrayList<>();

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

        return itemList;
    }

}
