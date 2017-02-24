package com.bep.roomidparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 *
 * <p>Starting point of room-id-parser</p>
 *
 * @author sido
 *
 */
@ComponentScan
@SpringBootApplication
public class RoomIdParserApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RoomIdParserApp.class, args);
    }


}
