import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.*;

import copenhagen.GameRules;

public class GameRulesTest {

    // This test checks if the the gridBagConstraints has the accurate parameters based on what was passed to the
    // function.
    @Test
    public void testCreateGridBagConstraints() {
        GridBagConstraints actual = GameRules.createGridBagConstraints(10, 7);
        assertEquals(GridBagConstraints.HORIZONTAL, actual.fill);
        assertEquals(10, actual.gridx);
        assertEquals(7, actual.gridy);
    }

    // This test checks if the gridBagConstraints has the right parameters even with negative numbers passed to the
    // function.
    @Test
    public void testCreateGridBagConstraintsIfNegativeNumbers() {
        GridBagConstraints actual = GameRules.createGridBagConstraints(-3, -4);
        assertEquals(GridBagConstraints.HORIZONTAL, actual.fill);
        assertEquals(-3, actual.gridx);
        assertEquals(-4, actual.gridy);
    }

    // This test checks if the newly created rule has the same text as what is passed to the function including the new
    // line characters.
    @Test
    public void testCreateRule() {
        JTextArea actual = GameRules.createRule("\n\nRandom Text: more \ntest");
        assertEquals("\n\nRandom Text: more \ntest", actual.getText());
    }

    // This test checks if the newly created caption for a given photo has the same text as what is passed to the
    // function.
    @Test
    public void testCreateCaption() {
        JLabel actual = GameRules.createCaption("Random Caption for Photo.");
        assertEquals("Random Caption for Photo.", actual.getText());
    }

    // This test checks the function that puts the array of JLabels with pictures and a caption to make sure that
    // every single JLabel object ends up getting put into the newly created JPanel.
    @Test
    public void testDisplayPicturesAndCaption() {
        int expected = 5;
        JLabel[] items = new JLabel[] {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()};
        JPanel actual = GameRules.displayPicturesAndCaption(items);
        assertEquals(expected, actual.getComponentCount());
    }

    // This test is to make sure the vertical scroll bar properties are the correct values when the JScrollPane is
    // created.
    @Test
    public void testcreateJScrollPane() {
        JScrollBar expected = new JScrollBar();
        expected.setUnitIncrement(20);
        expected.setValue(0);
        JScrollPane actual = GameRules.createJScrollPane(new JPanel());
        assertEquals(expected.getUnitIncrement(), actual.getVerticalScrollBar().getUnitIncrement());
        assertEquals(expected.getValue(), actual.getVerticalScrollBar().getValue());
    }
}
