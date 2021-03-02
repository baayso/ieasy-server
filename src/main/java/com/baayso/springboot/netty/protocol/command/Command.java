package com.baayso.springboot.netty.protocol.command;

public interface Command {

    byte LOGIN_REQUEST  = 1;
    byte LOGIN_RESPONSE = 2;

    byte MESSAGE_REQUEST  = 3;
    byte MESSAGE_RESPONSE = 4;

    byte LOGOUT_REQUEST  = 5;
    byte LOGOUT_RESPONSE = 6;

    byte CREATE_GROUP_REQUEST  = 7;
    byte CREATE_GROUP_RESPONSE = 8;

    byte LIST_GROUP_MEMBERS_REQUEST  = 9;
    byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    byte JOIN_GROUP_REQUEST  = 11;
    byte JOIN_GROUP_RESPONSE = 12;

    byte QUIT_GROUP_REQUEST  = 13;
    byte QUIT_GROUP_RESPONSE = 14;

    byte GROUP_MESSAGE_REQUEST  = 15;
    byte GROUP_MESSAGE_RESPONSE = 16;

    byte HEARTBEAT_REQUEST  = 17;
    byte HEARTBEAT_RESPONSE = 18;

}
