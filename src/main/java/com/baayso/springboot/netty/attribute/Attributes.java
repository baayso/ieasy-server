package com.baayso.springboot.netty.attribute;

import com.baayso.springboot.netty.session.Session;

import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
