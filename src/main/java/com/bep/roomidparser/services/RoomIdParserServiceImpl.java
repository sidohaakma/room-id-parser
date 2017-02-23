package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.UserException;
import com.bep.roomidparser.factories.RoomFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * @author sido
 *
 */
@Service
public class RoomIdParserServiceImpl implements RoomIdParserService {

    private static final Log log = LogFactory.getLog(RoomIdParserServiceImpl.class);

    @Override
    public List<Room> determineValidRoomIds(InputStream inputStream) throws UserException {
        List<Room> validRoomIds = new ArrayList<Room>();

        if(inputStream == null) {
            throw new UserException("Input data is corrupt");
        }

        try {
            validRoomIds = RoomFactory.determineRooms(inputStream);
        } catch (IOException err) {
            throw new UserException(err.getMessage());
        }

        return validRoomIds;
    }


    public int calculateNumberOfValidRooms(List<Room> validRoomIds) throws UserException {
        int value = 0;
        for(Room room : validRoomIds) {
            value += room.getNumber();
        }
        return value;
    }


}
