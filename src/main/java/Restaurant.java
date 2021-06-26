import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return LocalTime.now().isAfter(this.openingTime) && LocalTime.now().isBefore(this.closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
       return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public int getTotalPrice(List<String> items){
        return menu.stream()
          .filter(item -> items.contains(item.getName()))
          .reduce(0, (subtotal, item) -> subtotal + item.getPrice(), Integer::sum);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
          +"Location:"+ location + "\n"
          +"Opening time:"+ openingTime +"\n"
          +"Closing time:"+ closingTime +"\n"
          +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
