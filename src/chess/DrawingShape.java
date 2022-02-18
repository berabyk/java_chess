
package chess;

import java.awt.Graphics2D;

public interface DrawingShape {
    public boolean contains(Graphics2D graph, double x, double y);
    public void adjustPosition(double dx, double dy);
    public void draw(Graphics2D graph);
}
