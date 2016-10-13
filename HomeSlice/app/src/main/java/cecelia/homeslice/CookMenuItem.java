package cecelia.homeslice;

/**
 * Class governing the format for a possible food on the menu
 */
public class CookMenuItem {
    long id;
    String picture;
    String item;
    String ingredients;
    String instructions;

    public CookMenuItem(long id, String picture, String item, String ingredients, String instructions) {
        this.id = id;
        this.picture = picture;
        this.item = item;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
       this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
