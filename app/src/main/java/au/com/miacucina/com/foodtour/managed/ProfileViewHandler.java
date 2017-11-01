package au.com.miacucina.com.foodtour.managed;
import android.content.Context;
import java.util.List;
import au.com.miacucina.com.foodtour.Model.ItemDisplay;
import au.com.miacucina.com.foodtour.adapters.ItemAdapter;

public class ProfileViewHandler implements LayoutViewHandler {

    private Context mContext;
    private ItemAdapter mAdapter;
    private List<ItemDisplay> itemDisplays;

    public ProfileViewHandler(Context applicationContext, ItemAdapter adapter, List<ItemDisplay> itemList) {
        mContext = applicationContext;
        mAdapter = adapter;
        itemDisplays = itemList;
    }

    @Override
    public void renderLayout(String url) throws InterruptedException {

    }
}
