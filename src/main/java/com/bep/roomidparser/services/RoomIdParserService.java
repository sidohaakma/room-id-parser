package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.UserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author sido
 */
public interface RoomIdParserService {

    List<Room> determineValidRoomIds(InputStream inputStream) throws UserException, IOException;

    int calculateNumberOfValidRooms(List<Room> validRoomIds) throws UserException;;
}
