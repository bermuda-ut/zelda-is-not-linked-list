package PlayerData;

import GameObject.Item;

/**
 * inventory tied to the player
 * @author MaxLee
 */
public class Inventory {
    private Item items[];
    private int capacity;

    public int currItemCount;

    /**
     * @param capacity maximum number of items player can carry
     */
    public Inventory(int capacity) {
        this.capacity = capacity;
        items = new Item[capacity];
        currItemCount = 0;
    }

    /**
     * check presence of item
     * @param id item id
     * @return true if in inventory, false otherwise
     */
    public boolean hasItem(int id) {
        // inefficient linear check
        for(int i = 0; i < currItemCount; i++)
            if(items[i].getId() == id)
                return true;
        return false;
    }

    /**
     * add item to inventory
     * @param i item to add
     * @return true if adding was successful, false otherwise
     */
    public boolean addItem(Item i) {
        if(currItemCount < capacity) {
            items[currItemCount] = i;
            currItemCount++;
            return true;
        }

        return false;
    }

    /**
     * remove item from inventory. not required implementation
     * @param id id of the item to remove
     * @return true of removal was success, false otherwise
     */
    public boolean removeItem(int id) {
        boolean found = false;
        int i;

        // try to find item
        for(i = 0; i < currItemCount; i++) {
            if(items[i].getId() == id) {
                found = true;
                break;
            }
        }

        // shift items
        for(; i < currItemCount-1; i++) {
            items[i] = items[i+1];
        }

        // reduce number of items
        if(found)
            currItemCount--;

        return found;
    }

    //getters

    public int getCurrItemCount() {
        return currItemCount;
    }

    public Item[] getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }
}
