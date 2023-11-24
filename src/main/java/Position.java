/**
 * The {@code Position} class represents a position or point in a two-dimensional space,
 * identified by x (horizontal) and y (vertical) coordinates.
 */
public class Position {
    // Member variables representing the coordinates
    public int x;
    public int y;

    /**
     * Constructs a new {@code Position} with specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new {@code Position} that is a copy of another {@code Position}.
     *
     * @param another The other {@code Position} to copy coordinates from.
     */
    public Position(Position another){
        this(another.x, another.y);
    }

    /**
     * Changes the coordinates of this position to the specified x and y coordinates.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void changePosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}