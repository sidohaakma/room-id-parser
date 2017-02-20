package com.bep.roomidparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * <p>Starting point of REST-API from room-id-starter</p>
 *
 * @author sido
 *
 */
@EnableAutoConfiguration
public class RoomIdParserApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RoomIdParserApp.class, args);
    }


}
