package com.baayso.springboot.netty.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Packet {

    /**
     * 协议版本
     */
    @JsonIgnore
    private byte version = 1;

    @JsonIgnore
    public abstract byte getCommand();

}
