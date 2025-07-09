/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package adventure.Entity.types;

/**
 *
 * @author utente
 */
public enum CardinalPoint {
    NORTH,
    SOUTH,
    EAST,
    WEST;

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

