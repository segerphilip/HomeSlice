package cecelia.homeslice;

/**
 * Created by Cecelia on 10/9/16.
 */
public class Customer {

    private String name;
    private String databaseId;

    public Customer() {}

    public Customer(String name, String id) {
        this.name = name;
        this.databaseId = id;
    }

    public String getName() {
        return this.name;
    }

    public String getDatabaseId() {
        return this.databaseId;
    }
}
