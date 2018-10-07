package ru.ryabtsev.se.packets;

/**
 * Enumeration for possible packet types.
 */
public enum PacketType {
    /** Packet type is unknown. */
    UNKNOWN,

    /** Client 'ping' command packet. */
    PING,
    /** Server 'ping' command result packet. */
    RESULT,

    /** Client 'registry' command packet */
    REGISTRY,
    /** Client 'login' command packet */
    LOGIN,
    /** Client 'logout' command packet */
    LOGOUT,

    /** Client 'message' command packet */
    MESSAGE,
    /** Server broadcast packet */
    BROADCAST
}
