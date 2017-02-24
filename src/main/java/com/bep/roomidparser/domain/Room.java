package com.bep.roomidparser.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * <p>Room object to </p>
 *
 * @author sido
 */
public class Room {

  private static final Log LOG = LogFactory.getLog(Room.class);

  private String tokenToBeEvaluated = "";
  private String parsedEvaluationToken = "";
  private String evaluationToken = "";
  private int number = -1;
  private List<RoomTokenPartial> roomTokenPartials;


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

  public List<RoomTokenPartial> getRoomPartials() {
    return roomTokenPartials;
  }

  /**
   * <p>Add a list of room-token-partials to the Room.java.</p>
   * <p>It also generates a evalutionToken to match the given key in a Room</p>
   *
   * <p>Example:
   *
   * jchipqat-qphzti-rjhidbtg-htgkxrt-271[<b>thigj</b>]
   *
   * The bold-text is the to be evaluated key.
   * The generated token has to match this key.
   *
   * </p>
   *
   * @param roomTokenPartials list of partials
   */
  public void setRoomTokenPartials(List<RoomTokenPartial> roomTokenPartials) {
    this.roomTokenPartials = roomTokenPartials;
    StringBuilder parsedEvaluationToken = new StringBuilder();
    int indexKey = 0;
    for (RoomTokenPartial rp : roomTokenPartials) {
      if (indexKey == 5) {
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

  /**
   * <p>Is this room a valid room?</p>
   *
   * @return yes/no
   */
  public boolean isValid() {
    if (parsedEvaluationToken.isEmpty()) {
      LOG.error("object not initialized properly");
    }
    return parsedEvaluationToken.equalsIgnoreCase(evaluationToken);
  }
}
