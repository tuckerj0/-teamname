package copenhagen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JRadioButton;
import java.util.ArrayList;

public class Settings{

    private static ArrayList<JRadioButton> attackButtons = new ArrayList<JRadioButton>();
    private static ArrayList<JRadioButton> defenseButtons = new ArrayList<JRadioButton>();
    private static ArrayList<JRadioButton> boardColor1 = new ArrayList<JRadioButton>();
    private static ArrayList<JRadioButton> boardColor2 = new ArrayList<JRadioButton>();
    private static JSpinner hourSpinner;
    private static JSpinner minSpinner;
    private static JSpinner secSpinner;
    private static JSpinner perMoveSpinner;
    private JFrame settingsFrame= new JFrame("Settings"); // creates frame/window
    private JPanel attackPanel = new JPanel();
    private JPanel defensePanel = new JPanel();
    private JPanel boardColor1Panel = new JPanel();
    private JPanel boardColor2Panel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel perMovePanel = new JPanel();
    private JPanel savePanel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private JButton saveSettingsButton = new JButton("Save and Start Game");//allows user to save seetings
    private static int[] Black = {0, 0, 0};
    private static int[] White = {255, 255, 255};
    private static int[] LightGray = {180, 180, 180};
    private static int[] DarkGray = {50, 50, 50};
    private static int[] Red = {255, 0, 0};
    private static int[] Green = {0, 255, 0};
    private static int[] Blue = {0, 0, 255};
    private static int boardSize = 11;

    public Settings(){
        /**
         * This function will create the JFrame that will allow user to set time settings, color settings, and start side
         */
        settingsFrame.setSize(400, 800); // width, height
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        // Board colors buttons
        JLabel boardPrimColorLabel = new JLabel("Choose primary board color:");
        boardPrimColorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(boardPrimColorLabel);
        boardColor1.add(new JRadioButton("White"));
        boardColor1.add(new JRadioButton("Light Gray"));
        boardColor1.add(new JRadioButton("Dark Gray"));
        boardColor1.add(new JRadioButton("Black"));
        boardColor1.add(new JRadioButton("Red"));
        boardColor1.add(new JRadioButton("Blue"));
        boardColor1.add(new JRadioButton("Green"));
        ButtonGroup primaryColorGroup = new ButtonGroup();
        for(JRadioButton button:boardColor1){
            primaryColorGroup.add(button);
            boardColor1Panel.add(button);
        }
        settingsPanel.add(boardColor1Panel);
        JLabel boardSecColorLabel = new JLabel("Choose secondary board color:");
        boardSecColorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(boardSecColorLabel);
        boardColor2.add(new JRadioButton("White"));
        boardColor2.add(new JRadioButton("Light Gray"));
        boardColor2.add(new JRadioButton("Dark Gray"));
        boardColor2.add(new JRadioButton("Black"));
        boardColor2.add(new JRadioButton("Red"));
        boardColor2.add(new JRadioButton("Blue"));
        boardColor2.add(new JRadioButton("Green"));
        ButtonGroup secondaryColorGroup = new ButtonGroup();
        for(JRadioButton button:boardColor2){
            secondaryColorGroup.add(button);
            boardColor2Panel.add(button);
        }
        settingsPanel.add(boardColor2Panel);

        // Attack Color Buttons
        JLabel attackLabel = new JLabel("Choose Attack Color");
        attackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(attackLabel);
        attackButtons.add(new JRadioButton("White"));
        attackButtons.add(new JRadioButton("Black"));
        attackButtons.add(new JRadioButton("Green"));
        attackButtons.add(new JRadioButton("Blue"));
        attackButtons.add(new JRadioButton("Red"));
        attackButtons.add(new JRadioButton("Orange"));
        ButtonGroup attackGroup = new ButtonGroup();
        for(JRadioButton button:attackButtons){
            attackGroup.add(button);
            attackPanel.add(button);
        }
        settingsPanel.add(attackPanel);

        //Defense Color Buttons
        JLabel defenseLabel = new JLabel("Choose Defense Color");
        defenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(defenseLabel);
        defenseButtons.add(new JRadioButton("White"));
        defenseButtons.add(new JRadioButton("Black"));
        defenseButtons.add(new JRadioButton("Green"));
        defenseButtons.add(new JRadioButton("Blue"));
        defenseButtons.add(new JRadioButton("Red"));
        defenseButtons.add(new JRadioButton("Orange"));
        ButtonGroup defenseGroup = new ButtonGroup();
        for(JRadioButton button:defenseButtons){
            defenseGroup.add(button);
            defensePanel.add(button);
        }
        settingsPanel.add(defensePanel);

        JLabel gameLengthLabel = new JLabel("Set Starting Game Time");
        gameLengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(gameLengthLabel);

        //hours spinner
        timePanel.add(new JLabel("Hours"));
        SpinnerModel hourModel = new SpinnerNumberModel(0, //initial value
           0, //min
           100, //max
           1);//step
        hourSpinner = new JSpinner(hourModel);
        timePanel.add(hourSpinner);

        //minutes spinner
        timePanel.add(new JLabel("Minutes"));
        SpinnerModel minModel = new SpinnerNumberModel(5, //initial value
           0, //min
           59, //max
           1);//step
        minSpinner = new JSpinner(minModel);
        timePanel.add(minSpinner);

        //seconds spinner
        timePanel.add(new JLabel("Seconds"));
        SpinnerModel secModel = new SpinnerNumberModel(0, //initial value
           0, //min
           59, //max
           1);//step
        secSpinner = new JSpinner(secModel);
        timePanel.add(secSpinner);
        settingsPanel.add(timePanel);

        //Per Move Added Time Choice
        JLabel perMoveLabel = new JLabel("Time Added Per Turn");
        perMoveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(perMoveLabel);

        perMovePanel.add(new JLabel("Seconds"));
        SpinnerModel perMoveModel = new SpinnerNumberModel(3, //initial value
           0, //min
           1000, //max
           1);//step
        perMoveSpinner = new JSpinner(perMoveModel);
        perMovePanel.add(perMoveSpinner);
        settingsPanel.add(perMovePanel);

        //Save Button
        ActionListener saveSettingsListener = new SaveSettingsListener();
        saveSettingsButton.addActionListener(saveSettingsListener);
        saveSettingsButton.setFont(new Font("Courier", Font.PLAIN, 48));
        saveSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(saveSettingsButton);

        settingsFrame.add(settingsPanel);
        settingsFrame.pack();
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    /**
     * This functionss saves the settings chosen by the user
     * @return true if the user settings are valid
     */
    public static boolean saveSettings(){
        String primColor = "";
        String secColor = "";
        boolean primColorSelected = false;
        boolean secColorSelected = false;
        int colors[] = {0, 0, 0, 0, 0, 0};
        for (JRadioButton button:boardColor1) {
            if (button.isSelected()) {
                primColor = button.getText();
                primColorSelected = true;
            }
        }
        for (JRadioButton button:boardColor2) {
            if (button.isSelected()) {
                secColor = button.getText();
                secColorSelected = true;
            }
        }
        if(primColorSelected && secColorSelected){
            switch (primColor) {
                case "Black":
                    colors[0] = Black[0];
                    colors[1] = Black[1];
                    colors[2] = Black[2];
                    break;
                case "White":
                    colors[0] = White[0];
                    colors[1] = White[1];
                    colors[2] = White[2];
                    break;
                case "Light Gray":
                    colors[0] = LightGray[0];
                    colors[1] = LightGray[1];
                    colors[2] = LightGray[2];
                    break;
                case "Dark Gray":
                    colors[0] = DarkGray[0];
                    colors[1] = DarkGray[1];
                    colors[2] = DarkGray[2];
                    break;
                case "Red":
                    colors[0] = Red[0];
                    colors[1] = Red[1];
                    colors[2] = Red[2];
                    break;
                case "Green":
                    colors[0] = Green[0];
                    colors[1] = Green[1];
                    colors[2] = Green[2];
                    break;
                default: // blue
                    colors[0] = Blue[0];
                    colors[1] = Blue[1];
                    colors[2] = Blue[2];
            }
            if (primColor.equals("Black") || primColor.equals("Dark Gray")) {
                Hnefatafl.setLetteringColor(White[0], White[1], White[2]);
            }
            else {
                Hnefatafl.setLetteringColor(Black[0], Black[1], Black[2]);
            }
            switch (secColor) {
                case "Black":
                    colors[3] = Black[0];
                    colors[4] = Black[1];
                    colors[5] = Black[2];
                    break;
                case "White":
                    colors[3] = White[0];
                    colors[4] = White[1];
                    colors[5] = White[2];
                    break;
                case "Light Gray":
                    colors[3] = LightGray[0];
                    colors[4] = LightGray[1];
                    colors[5] = LightGray[2];
                    break;
                case "Dark Gray":
                    colors[3] = DarkGray[0];
                    colors[4] = DarkGray[1];
                    colors[5] = DarkGray[2];
                    break;
                case "Red":
                    colors[3] = Red[0];
                    colors[4] = Red[1];
                    colors[5] = Red[2];
                    break;
                case "Green":
                    colors[3] = Green[0];
                    colors[4] = Green[1];
                    colors[5] = Green[2];
                    break;
                default: // blue
                    colors[3] = Blue[0];
                    colors[4] = Blue[1];
                    colors[5] = Blue[2];
            }
            Hnefatafl.setBoardColors(colors[0], colors[1], colors[2], colors[3], colors[4], colors[5]);
        }else{
            JOptionPane.showMessageDialog(null, "Please select both a primary color and a secondary color for the game board.");
            return false;
        }

        String attackColor = "";
        String defenseColor = "";
        boolean attackSelected = false;
        boolean defenseSelected = false;
        for(JRadioButton button:attackButtons){
            if(button.isSelected()){
                attackColor = button.getText();
                attackSelected = true;
                break;
            }
        }
        for(JRadioButton button:defenseButtons){
            if(button.isSelected()){
                defenseColor = button.getText();
                defenseSelected = true;
                break;
            }
        }
        if(attackSelected && defenseSelected){
            if(attackColor.equals(defenseColor)){
                JOptionPane.showMessageDialog(null, "Attack and defense can't be the same color!");
                return false;
            }
            Hnefatafl.setAttackColor(attackColor);
            Hnefatafl.setDefenseColor(defenseColor);
        }else{
            JOptionPane.showMessageDialog(null, "Please select both an attack and defense color");
            return false;
        }

        int secs = (Integer) secSpinner.getValue();
        int mins = (Integer) minSpinner.getValue();
        int hours = (Integer) hourSpinner.getValue();
        int perMoveTime = (Integer) perMoveSpinner.getValue();
        BottomBar.setStartingTime(secs,mins,hours);
        BottomBar.setPerMoveTime(perMoveTime);

        return true;
    }

    /**
     * This is a button listener for when the _whiteButton button is clicked for the white pieces to start as the attackers.
     */
    class SaveSettingsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(Settings.saveSettings()){
                settingsFrame.dispose();
                GameLogic.setStartingPieces(Hnefatafl.getBoardSize());
                Hnefatafl.setUpGameBoard();
                Hnefatafl.displayGameBoard();
            }
        }
    }
}
