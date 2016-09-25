package PlayerData;

import GameObject.Item;

/**
 * Created by noxm on 25/09/16.
 */
public class Inventory {
    public int currItemCount;
    private Item items[];
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        items = new Item[capacity];
        currItemCount = 0;
    }

    public boolean hasItem(int id) {
        for(int i = 0; i < currItemCount; i++)
            if(items[i].getId() == id)
                return true;
        return false;
    }

    public boolean addItem(Item i) {
        if(currItemCount < capacity) {
            items[currItemCount] = i;
            currItemCount++;
            return true;
        }
        return false;
    }

    public boolean removeItem(int id) {
        boolean found = false;
        int i;

        for(i = 0; i < currItemCount; i++) {
            if(items[i].getId() == id) {
                found = true;
                break;
            }
        }

        for(; i < currItemCount-1; i++) {
            items[i] = items[i+1];
        }

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
