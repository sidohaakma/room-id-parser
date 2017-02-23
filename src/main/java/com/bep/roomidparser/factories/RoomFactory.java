package com.bep.roomidparser.factories;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.domain.RoomPartial;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sido
 */
public class RoomFactory {

    private static final Log log = LogFactory.getLog(RoomFactory.class);

    public static List<Room> determineRooms(InputStream inputStream) throws IOException {
        List<Room> validRoomIds = new ArrayList<Room>();
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

    private static Room generateRoomCharacteristics(String line) {
        Room room = new Room();
        StringBuilder stringToEvaluate = new StringBuilder();
        String[] roomIdSubs = line.split("\\-");
        for(String roomIdSub : roomIdSubs) {
            if(roomIdSub.matches("^[a-zA-Z]*$")) {
                stringToEvaluate.append(roomIdSub);
            } else {
                String[] roomTokenSubs = roomIdSub.split("\\[");
                for(String roomTokenSub : roomTokenSubs) {
                    if(roomTokenSub.matches("^[0-9]*$")) {
                        room.setNumber(Integer.valueOf(roomTokenSub));
                    } else {
                        room.setEvaluationToken(roomTokenSub.substring(0, roomTokenSub.length() - 1));
                    }
                }
            }
        }
        room.setTokenToBeEvaluated(stringToEvaluate.toString());
        room.setRoomPartials(generateRoomPartialsList(room.getTokenToBeEvaluated()));
        log.debug("token to be evaluated: [ " + room.getTokenToBeEvaluated() + " ]");
        log.debug("evaluation token: [ " + room.getEvaluationToken() + " ]");
        log.debug("parsed evaluation token: [ " + room.getParsedEvaluationToken() + " ]");
        log.debug("number: [ " + room.getNumber() + " ]");
        log.debug("status: [ " + room.isValid() + " ]");
        return room;
    }

    private static List<RoomPartial> generateRoomPartialsList(String roomId) {
        List<RoomPartial> roomPartials = new ArrayList<RoomPartial>();
        for(int index = 0; index < roomId.length(); index++) {
            String key = String.valueOf(roomId.charAt(index));
            RoomPartial rp = new RoomPartial(key, StringUtils.countMatches(roomId, key));
            if(!roomPartials.contains(rp)) {
                roomPartials.add(rp);
            }
        }
        Collections.sort(roomPartials);
        return roomPartials;
    }


}
