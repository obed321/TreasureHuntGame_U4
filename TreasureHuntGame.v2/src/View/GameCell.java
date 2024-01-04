// Obed Owusu
//am7360
//Systemtuvecklare
package View;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JButton {
    private boolean isHit;

    public GameCell() {
        setPreferredSize(new Dimension(40, 40)); // Set preferred size for the cell
        setFont(new Font("Courier New", Font.PLAIN, 9));
        setEnabled(true);
        setBackground(Color.GREEN); // Default color
    }

    public void setHit(boolean hit) {
        isHit = hit;
        if (isHit) {
            setBackground(Color.YELLOW); // Change color when hit
        } else {
            setBackground(Color.BLACK); // Change color for misses
        }
    }

    public void resetColor() {
        setBackground(Color.GREEN);
    }

}






