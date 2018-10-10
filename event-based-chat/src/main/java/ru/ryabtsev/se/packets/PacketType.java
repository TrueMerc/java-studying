package ru.ryabtsev.se.packets;

/**
 * Enumeration for possible packet types.
 */
public enum PacketType {
    /** Packet type is unknown. */
    UNKNOWN,

    // Service packets

    /** Client 'ping' command packet. */
    PING_REQUEST,
    /** Server response to 'ping' command packet. */
    PING_RESPONSE,

    /** Server 'ping' command result packet. */
    // FIXME Decide: Is packet of this type neccessary for application?
    RESULT,

    // Registration packets

    /** Client 'login' command packet */
    LOGIN_REQUEST,
    LOGIN_RESPONSE,

    /** Client 'logout' command packet */
    LOGOUT_REQUEST,
    LOGOUT_RESPONSE,

    /** Client 'registry' command packet */
    REGISTRY_REQUEST,
    REGISTRY_RESPONSE,


    // Message packets

    /** Client 'unicast' command packet */
    UNICAST_REQUEST,
    UNICAST_RESPONSE,

    /** Client 'broadcast' command packet */
    BROADCAST_REQUEST,
    BROADCAST_RESPONSE
}
