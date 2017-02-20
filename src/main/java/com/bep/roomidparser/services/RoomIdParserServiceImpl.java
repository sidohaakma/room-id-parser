package com.bep.roomidparser.services;

import com.bep.roomidparser.exceptions.UserException;
import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.factories.RoomFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

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
    public List<Room> determineValidRoomIds(InputStream inputStream) throws UserException, IOException {
        List<Room> validRoomIds = new ArrayList<Room>();

        if(inputStream == null) {
            throw new UserException("Input data is corrupt");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int inValidRoomsCount = 0;
            while ((line = br.readLine()) != null) {
                log.debug("Line to be examined: [ " + line + " ]");
                Room room = RoomFactory.generateRoomCharacteristics(line);
                if(room.isValid()) {
                    validRoomIds.add(room);
                } else {
                    inValidRoomsCount++;
                }
            }
            log.debug("invalid room count is: [ " + inValidRoomsCount + " ]");
        } catch (IOException err) {
            log.error(err);
            throw err;
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
