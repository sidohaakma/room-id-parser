package com.bep.roomidparser.domain;

/**
 *
 * <p>A subset of the room-id-string that has to be evaluated.</p>
 *
 * @author sido
 */
public class RoomTokenPartial implements Comparable<RoomTokenPartial> {

    private String key = "";
    private int count = 0;

    public RoomTokenPartial() {}

    public RoomTokenPartial(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(RoomTokenPartial comparable) {
        int returnValue = 0;
        if (this.count > comparable.count) {
            returnValue = -1;
        } else if (count < comparable.count) {
            returnValue = 1;
        } else {
            returnValue = this.key.compareTo(comparable.key);
        }
        return returnValue;
    }

    @Override
    public boolean equals(Object object) {
        RoomTokenPartial rp = new RoomTokenPartial();
        if(object instanceof RoomTokenPartial) {
            rp = (RoomTokenPartial) object;
        }
        boolean value = false;
        if(rp.getKey().equalsIgnoreCase(this.getKey())) {
            value = true;
        }
        return value;
    }


}
