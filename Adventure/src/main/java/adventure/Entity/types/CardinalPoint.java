/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package adventure.Entity.types;

/**
 *
 * 
 * 
 * Enumerativo che conserva gli identificativi dei quattro punti cardinali.
 * 
 * @author Paolo
 */
public enum CardinalPoint {

    /**
     *
     */
    NORTH,

    /**
     *
     */
    SOUTH,

    /**
     *
     */
    EAST,

    /**
     *
     */
    WEST;
    
    /**
     * 
     * Il metodo restituisce il cardinal Point opposto relativamente a quello su cui viene invocato {@code getOpposite()}
     * 
     * @return {@link CardinalPoint} opposto rispetto a quello su cui si sta invocando {@code getOpposite()}.
     * 
     */

    CardinalPoint getOpposite()
    {
	CardinalPoint opposite = null;

	if (this == CardinalPoint.NORTH)
		opposite = CardinalPoint.SOUTH;
	else if (this == CardinalPoint.SOUTH)
		opposite = CardinalPoint.NORTH;
	else if (this == CardinalPoint.EAST)
		opposite = CardinalPoint.WEST;
	else if (this == CardinalPoint.WEST)
		opposite = CardinalPoint.EAST;

	return opposite;
		
}
}

