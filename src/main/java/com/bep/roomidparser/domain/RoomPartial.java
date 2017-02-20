package com.bep.roomidparser.domain;

/**
 * @author sido
 */
public class RoomPartial implements Comparable<RoomPartial> {

    private String key = "";
    private int count = 0;

    public RoomPartial() {}

    public RoomPartial(String key, int count) {
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
    public int compareTo(RoomPartial comparable) {
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
        RoomPartial rp = new RoomPartial();
        if(object instanceof RoomPartial) {
            rp = (RoomPartial) object;
        }
        boolean value = false;
        if(rp.getKey().equalsIgnoreCase(this.getKey())) {
            value = true;
        }
        return value;
    }


}
