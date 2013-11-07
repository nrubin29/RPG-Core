package me.nrubin29.rpg;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import me.nrubin29.rpg.gui.ErrorGUI;
import me.nrubin29.rpg.gui.GUI;
import me.nrubin29.rpg.gui.SplashScreen;
import me.nrubin29.rpg.gui.StatusBar;
import me.nrubin29.rpg.map.Maps;
import me.nrubin29.rpg.quest.Quests;
import me.nrubin29.rpg.tile.TilesheetManager;
import me.nrubin29.rpg.util.Constants;
import me.nrubin29.rpg.util.TimerUtil;

public class Main {

	private static JFrame frame;
    private static GUI gui;
    private static StatusBar sb;

    public static void main(String[] args) {
    	Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread th, Throwable e) {
				e.printStackTrace(); //TODO: Temp
				
				if (frame != null) {
					frame.setVisible(false);
					frame.dispose();
				}
				new ErrorGUI(e);
			}
    	});
    	
    	TilesheetManager.getInstance().setup();
    	
    	final SplashScreen splash = new SplashScreen();
    	
        frame = new JFrame(Constants.NAME + " v" + Constants.VERSION);
        
        frame.setBackground(Constants.BACKGROUND_COLOR);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(Constants.ACTUAL_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        gui = new GUI();
        sb = new StatusBar();
        
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(sb);
        box.add(gui);
        box.add(Box.createVerticalGlue());
        frame.add(box);

        getGUI().renderMap(Maps.SAMPLE_CITY); //TODO: Temp
        Quests.SAMPLE.getInstance().startQuest(); //TODO: Temp
        
        TimerUtil.runTimer(3 * 1000, new Runnable() {
        	public void run() {
        		splash.dispose();
        		frame.setVisible(true);
        	}
        });
    }
    
    public static JFrame getFrame() {
    	return frame;
    }

    public static GUI getGUI() {
        return gui;
    }
    
    public static StatusBar getStatusBar() {
    	return sb;
    }
}