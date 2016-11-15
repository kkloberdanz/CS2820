/**
 * @author Kyle Kloberdanz
 *
 * Please let me know if you need more methods or fields for this class
 */
package production;

public class Item {
    private int     id_number;
    private String  name;

    public Item(int num, String new_name) {
        this.id_number = num;
        this.name = new_name;
    }

    public Item(String new_name, int num) {
        this(num, new_name);
    }

    public void set_id_number(int num) {
        this.id_number = num;
    }

    public int get_id_number() {
        return this.id_number;
    }

    public void set_name(String new_name) {
        this.name = new_name;
    }

    public String get_name() {
        return this.name;
    }

    public String toString() {
        return id_number + ", " + name;
    }

}
