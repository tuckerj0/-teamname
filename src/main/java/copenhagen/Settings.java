package copenhagen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JRadioButton;

public class Settings{

    private static JRadioButton whiteOption;
    private static JRadioButton blackOption;
    private static JSpinner hourSpinner;
    private static JSpinner minSpinner;
    private static JSpinner secSpinner;
    private static JSpinner perMoveSpinner;
    private JFrame settingsFrame= new JFrame("Settings"); // creates frame/window
    private JPanel colorPanel = new JPanel();
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

        whiteOption = new JRadioButton("White");
        blackOption = new JRadioButton("Black");

        ButtonGroup group = new ButtonGroup();
        group.add(whiteOption);
        group.add(blackOption);

        JLabel colorLabel = new JLabel("Choose Starting Color");
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(colorLabel);
        colorPanel.add(whiteOption);
        colorPanel.add(blackOption);
        settingsPanel.add(colorPanel);

        JLabel gameLengthLabel = new JLabel("Set Game Length");
        gameLengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsPanel.add(gameLengthLabel);

        //seconds spinner
        timePanel.add(new JLabel("Hours"));
        SpinnerModel hourModel = new SpinnerNumberModel(0, //initial value
           0, //min
           100, //max
           1);//step
        hourSpinner = new JSpinner(hourModel);
        timePanel.add(hourSpinner);

        //seconds spinner
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


        // adding the save settings button
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
        if(whiteOption.isSelected()){
            Hnefatafl.whiteStart();
        }else if(blackOption.isSelected()){
            Hnefatafl.blackStart();
        }else{
            JOptionPane.showMessageDialog(null, "Please select a starting color");
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
