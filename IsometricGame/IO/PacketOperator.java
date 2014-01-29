/*
 * PacketOperator.java
 *
 * Created on July 9, 2003, 9:50 AM
 */

package IsometricGame.IO;

/** Holds all the bitmasks for data communication between the Client and the Server.
 * @author jgauci
 */
public interface PacketOperator {
   //for server
   final byte CREATE_WALL=0,DELETE_WALL=1,CREATE_TILE=2,CREATE_ITEM=3,DELETE_ITEM=4,SAVE_MAP_REQUEST=5,LOAD_MAP_REQUEST=6,SEND_MAP=7,MOVE_ITEM=8,
   CREATE_AVATAR=9,CHANGE_AVATAR_OBJECTIVE=10,CLIENT_EXIT=11,FIRE_SHOT=12,CREATE_DOODAD=13,DELETE_DOODAD=14,GET_ITEM_FROM_BACKPACK=15,
   PUT_ITEM_IN_BACKPACK=16,THROW_ITEM=17;
   
   //for login
   final byte LOGIN_FAILED=0,LOGIN_PLAYER=1,LOGIN_BUILDER=2;
   
   //for client
   final byte ROOM_CREATED=-1,WALL_CHANGED=-2,ITEM_CREATED=-3,ITEM_REMOVED_FROM_GROUND=-4,MAP_SENT=-5,ITEM_MOVED=-6,AVATAR_CREATED=-8,AVATAR_MOVED=-9,
   AVATAR_DELETED=-10,WALL_DELETED=-11,SHOT_FIRED=-12,DOODAD_CREATED=-13,DOODAD_DELETED=-14,HELD_ITEM_ADDED=-15,HELD_ITEM_REMOVED=-16,
   ITEM_GOT_FROM_BACKPACK=-17,ITEM_PUT_IN_BACKPACK=-18,ITEM_DELETED=-19,ITEM_THROWN=-20,PROJECTILE_DROPPED_AT=-21;
   
   final byte WAIT_OBJECTIVE=0,MOVE_OBJECTIVE=1,ATTACK_OBJECTIVE=2,DROP_OBJECTIVE=3,GET_OBJECTIVE=4,EQUIP_OBJECTIVE=5;
   
   //Avatar Types
   final byte NONE=-1,ACTOR=0;
   
   //Doodad Types
   final byte DOODAD=0;
}
