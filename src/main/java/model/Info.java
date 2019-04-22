package model;

public class Info {
    private String name;
    private String symbol;
    private String category;
    private String logo;
    private String tags;

    public Info(String name, String symbol, String category, String logo, String tags, String description) {
        this.name = name;
        this.symbol = symbol;
        this.category = category;
        this.logo = logo;
        this.tags = tags;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCategory() {
        return category;
    }

    public String getLogo() {
        return logo;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    private String description;
}
