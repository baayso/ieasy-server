package com.baayso.springboot.netty.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.baayso.springboot.netty.attribute.Attributes;
import com.baayso.springboot.netty.session.Session;

import io.netty.channel.Channel;

public class SessionUtils {

    private static final Map<Long, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();

    public static void bind(Session session, Channel channel) {
        USERID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbind(Channel channel) {
        if (hasLogin(channel)) {
            USERID_CHANNEL_MAP.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(Long userId) {
        return USERID_CHANNEL_MAP.get(userId);
    }

}
