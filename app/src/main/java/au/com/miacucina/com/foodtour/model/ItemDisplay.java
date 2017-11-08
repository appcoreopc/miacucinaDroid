package au.com.miacucina.com.foodtour.model;

public class ItemDisplay {

    private String title;

    private String description;

    private String titleInfo;

    private String imageUrl;

    public ItemDisplay(String itemTitle, String itemDescription, String itemTitleInfo, String itemImageUrl)
    {
        this.title = itemTitle;
        this.description = itemDescription;
        this.titleInfo = itemTitleInfo;
        this.imageUrl = itemImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }
}


