package com.bep.roomidparser.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author sido
 */
public class Room {

    private static final Log log = LogFactory.getLog(Room.class);

    private String tokenToBeEvaluated = "";
    private String parsedEvaluationToken = "";
    private String evaluationToken = "";
    private int number = -1;
    private List<RoomPartial> roomPartials;


    public String getTokenToBeEvaluated() {
        return tokenToBeEvaluated;
    }

    public void setTokenToBeEvaluated(String tokenToBeEvaluated) {
        this.tokenToBeEvaluated = tokenToBeEvaluated;
    }

    public String getEvaluationToken() {
        return evaluationToken;
    }

    public void setEvaluationToken(String evaluationToken) {
        this.evaluationToken = evaluationToken;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<RoomPartial> getRoomPartials() {
        return roomPartials;
    }

    public void setRoomPartials(List<RoomPartial> roomPartials) {
        this.roomPartials = roomPartials;
        StringBuilder parsedEvaluationToken = new StringBuilder();
        int indexKey = 0;
        for(RoomPartial rp : roomPartials) {
            if(indexKey == 5) {
                break;
            }
            parsedEvaluationToken.append(rp.getKey());
            indexKey++;
        }
        setParsedEvaluationToken(parsedEvaluationToken.toString());
    }

    public String getParsedEvaluationToken() {
        return parsedEvaluationToken;
    }

    public void setParsedEvaluationToken(String parsedEvaluationToken) {
        this.parsedEvaluationToken = parsedEvaluationToken;
    }

    public boolean isValid() {
        if(parsedEvaluationToken.isEmpty()) {
            log.error("object not initialized properly");
        }
        return parsedEvaluationToken.equalsIgnoreCase(evaluationToken);
    }
}
