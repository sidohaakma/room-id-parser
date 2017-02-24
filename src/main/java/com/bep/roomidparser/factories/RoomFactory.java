package com.bep.roomidparser.factories;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.domain.RoomTokenPartial;
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
 *
 * <p>Static methods to handle the room-id strings.</p>
 *
 * @author sido
 */
public class RoomFactory {

    private static final Log LOG = LogFactory.getLog(RoomFactory.class);

    private static final String ALPHABET = "abcdefghikjlmnopqrstuvwxyz";

  /**
   *
   * <p>Determine per room if it's valid and add it to a list</p>
   *
   * @param inputStream file of room-id's
   * @return List<Room> list of valid room-id's
   * @throws IOException
   */
    public static List<Room> determineRooms(InputStream inputStream) throws IOException {
        List<Room> validRoomIds = new ArrayList<Room>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int inValidRoomsCount = 0;
            while ((line = br.readLine()) != null) {
                LOG.debug("Line to be examined: [ " + line + " ]");
                Room room = RoomFactory.generateRoomCharacteristics(line);
                if(room.isValid()) {
                    validRoomIds.add(room);
                } else {
                    inValidRoomsCount++;
                }
            }
            LOG.debug("invalid room count is: [ " + inValidRoomsCount + " ]");
        } catch (IOException err) {
            LOG.error(err);
            throw err;
        }
        return validRoomIds;
    }

  /**
   *
   * <p>Generate per room-id string a set of characteristics of the room.</p>
   * <p>Secondly fill the POJO with these characteristics.</p>
   *
   * @param line room-id string
   * @return Room with characterstics
   */
    private static Room generateRoomCharacteristics(String line) {
        Room room = new Room();
        StringBuilder stringToEvaluate = new StringBuilder();
        StringBuilder rawStringToEvaluate = new StringBuilder();
        String[] roomIdSubs = line.split("\\-");
        for(String roomIdSub : roomIdSubs) {
            if(roomIdSub.matches("^[a-zA-Z]*$")) {
                stringToEvaluate.append(roomIdSub);
                rawStringToEvaluate.append(roomIdSub);
                rawStringToEvaluate.append("-");
            } else {
                String[] roomTokenSubs = roomIdSub.split("\\[");
                for(String roomTokenSub : roomTokenSubs) {
                    if(roomTokenSub.matches("^[0-9]*$")) {
                        room.setSectorId(Integer.valueOf(roomTokenSub));
                    } else {
                        room.setEvaluationToken(roomTokenSub.substring(0, roomTokenSub.length() - 1));
                    }
                }
            }
        }
        room.setTokenToBeEvaluated(stringToEvaluate.toString());
        room.setRawTokenToBeEvaluated(rawStringToEvaluate.toString().substring(0, rawStringToEvaluate.length() - 1));
        room.setRoomTokenPartials(generateRoomPartialsList(room.getTokenToBeEvaluated()));
        LOG.debug("token to be evaluated: [ " + room.getTokenToBeEvaluated() + " ]");
        LOG.debug("raw-token to be evaluated: [ " + room.getRawTokenToBeEvaluated() + " ]");
        LOG.debug("evaluation token: [ " + room.getEvaluationToken() + " ]");
        LOG.debug("parsed evaluation token: [ " + room.getParsedEvaluationToken() + " ]");
        LOG.debug("number: [ " + room.getSectorId() + " ]");
        LOG.debug("status: [ " + room.isValid() + " ]");
        return room;
    }

  /**
   *
   * <p>Determine the RoomTokenPartials. RoomTokenPartials are needed to determine the evaluationToken of the Room.</p>
   *
   * @param roomId room-id string which contains the RoomTokenPartials
   * @return List<RoomTokenPartials> list of room-token-partials to determine the evaluationToken
   */
    private static List<RoomTokenPartial> generateRoomPartialsList(String roomId) {
        List<RoomTokenPartial> roomPartials = new ArrayList<RoomTokenPartial>();
        for(int index = 0; index < roomId.length(); index++) {
            String key = String.valueOf(roomId.charAt(index));
            RoomTokenPartial rp = new RoomTokenPartial(key, StringUtils.countMatches(roomId, key));
            if(!roomPartials.contains(rp)) {
                roomPartials.add(rp);
            }
        }
        Collections.sort(roomPartials);
        return roomPartials;
    }

    /**
     *
     * <p>Decrypt a room name from cypher-encryption.</p>
     *
     * @param rawTokenToBeEvaluated the token with the dashes and without the sectorId
     * @param sectorId sectorId is the key for the cypher-encryption
     * @return decrypted room name
     */
    public static String decryptRoomName(String rawTokenToBeEvaluated, int sectorId) {
        StringBuilder actualName = new StringBuilder();

        for(int index = 0; index < rawTokenToBeEvaluated.length(); index++) {
            String character = String.valueOf(rawTokenToBeEvaluated.charAt(index));
            if(character.matches("^[a-zA-Z]*$")) {
                actualName.append(decryptCharacter(character, sectorId));
            } else {
                actualName.append(" ");
            }
        }

        return actualName.toString();
    }

    /**
     *
     * <p>Determine next character to complemete decryption room name.</p>
     *
     * @param character character from rawTokenToBeEvaluated
     * @param sectorId sectorId is the key for the cypher-encryption
     * @return decrypted character
     */
    private static String decryptCharacter(String character, int sectorId) {
        String newCharacter = "";

        int index = ALPHABET.indexOf(character);
        int countToEndOfAlphabet = ALPHABET.length() - index;
        int numberLeft = sectorId - countToEndOfAlphabet;
        int alphabetCount = numberLeft / ALPHABET.length();

        numberLeft = sectorId - ((alphabetCount * ALPHABET.length()) + countToEndOfAlphabet);
        newCharacter = String.valueOf(ALPHABET.charAt(numberLeft));

        return newCharacter;
    }

}
