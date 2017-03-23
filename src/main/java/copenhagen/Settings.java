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
    private static JSpinner hourSpinner;
    private static JSpinner minSpinner;
    private static JSpinner secSpinner;
    private static JSpinner perMoveSpinner;
    private JFrame settingsFrame= new JFrame("Settings"); // creates frame/window
    private JPanel attackPanel = new JPanel();
    private JPanel defensePanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel perMovePanel = new JPanel();
    private JPanel savePanel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private JButton saveSettingsButton = new JButton("Save");//allows user to save seetings
    private static int boardSize = 11;

    public Settings(){
        /**
         * This function will create the JFrame that will allow user to set time settings, color settings, and start side
         */
        settingsFrame.setSize(400, 400); // width, height
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        //Attack Color Butons
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

        //Game Time Choice
        JLabel gameLengthLabel = new JLabel("Set Game Length");
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
                JOptionPane.showMessageDialog(null, "Attack and defense cant be the same color!");
                return false;
            }
            Hnefatafl.setAttackColor(attackColor);
            Hnefatafl.setDefenseColor(defenseColor);
        }else{
            JOptionPane.showMessageDialog(null, "Please select a both and attack and defense color");
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
