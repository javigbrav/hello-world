package LogIn;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

/***********************************************************************************
 * Author: Zainab Siddiqui / Javiera Garrido Bravo
 * Date: December 20, 2023 
 * Last Modified: January 12, 2024
 * Last Modified by: Javiera Garrido
 * Description: A user's home page in the application
 ***********************************************************************************/

public class Homepage {

    Color WHIRLWIND_PINK = new Color(228, 213, 211);
    Color mintGreen = new Color(220, 242, 206);
    JMenu menu, subLibrary, subUser;
    JMenuItem libr, note, fav, user, home;
    JMenuBar mb = new JMenuBar();
    JTextArea textArea;
    
    static JFrame homepageFrame = new JFrame("Tales Around the World - HOME");

    Homepage() {
    	
    	// Create JTextArea
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        // title
        JTextArea title = new JTextArea("Tales Around The World");
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setOpaque(false);
        title.setEditable(false);
        JPanel titlePanel = new JPanel(); //it was being covered by the search bar, so it needs this
        titlePanel.add(title);
        titlePanel.setOpaque(false);

        // set search bar panel
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        searchField.setText("Search for stories here");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for stories here")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for stories here");
                }
            }
        });
        
        //search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                if (!searchTerm.equals("Search for stories here")) {
                    System.out.println("Performing search for: " + searchTerm);
                } else {
                    System.out.println("Check");
                }
            }
        });
        //implementing search panel
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);
        searchBarPanel.setOpaque(false);

        // menu choice panel
        menu = new JMenu("Menu");
        subLibrary = new JMenu("Library");
        subUser = new JMenu("Profile");
        
         //creating menu items
        libr = new JMenuItem("Library");
        fav = new JMenuItem("Favorites");
        note = new JMenuItem("Annotations");
        user = new JMenuItem("Profile");
        home = new JMenuItem ("Homepage");
        
        //implementing action listeners to direct to other pages using the same frame
        libr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Library.setFrame(homepageFrame);
            	new Library();
                System.out.println("Redirecting to Library page");
            }
        });

        fav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redirecting to Favorites page");
            }
        });

        note.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redirecting to Annotations page");
            }
        });

        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redirecting to profile");
            }
        });
        
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Homepage();
                System.out.println("Redirecting to Homepage");
            }
        });
         //adding items to menus
        subLibrary.add(libr);
        subLibrary.add(note);
        subLibrary.add(fav);
        subUser.add(user);

        menu.setFont(new Font("Helvetica", Font.PLAIN, 14));
        mb.setBackground(WHIRLWIND_PINK);
        menu.setForeground(Color.black);
        menu.add(subUser);
        menu.add(subLibrary);
        menu.add(home);
        mb.add(menu);

        // Create a panel for book buttons with separation from sides, top, and bottom
        JPanel bookPanel = new JPanel(new BorderLayout());
        JPanel topSpace = new JPanel();
        JPanel bottomSpace = new JPanel();
        topSpace.setOpaque(false);
        bottomSpace.setOpaque(false);

        topSpace.setPreferredSize(new Dimension(1, 100)); // Adjust as needed
        bottomSpace.setPreferredSize(new Dimension(1, 75)); // Adjust as needed

        bookPanel.add(topSpace, BorderLayout.NORTH);
        bookPanel.add(bottomSpace, BorderLayout.SOUTH);

        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setOpaque(false);
        rightSpace.setOpaque(false);

        leftSpace.setPreferredSize(new Dimension(1, 1)); // Adjust as needed
        rightSpace.setPreferredSize(new Dimension(170, 1)); // Adjust as needed

        bookPanel.add(leftSpace, BorderLayout.WEST);
        bookPanel.add(rightSpace, BorderLayout.EAST);

        JPanel bookGridPanel = new JPanel();
        bookGridPanel.setLayout(new GridLayout(2, 5, 50, 70)); // Adjust the last two parameters for spacing
        bookGridPanel.setBackground(mintGreen);
        
        
        //book buttons
        for (int i = 1; i <= 10; i++){
            JButton bookButton = new JButton("Book " + i);
            final int bookNumber = i; // Final variable to use in the ActionListener
            bookButton.setBackground(new Color(158, 30, 30));
            bookButton.setPreferredSize(new Dimension(25, 40)); // Adjust size as needed
            bookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle reading from the corresponding text file
                    String fileName = "book" + bookNumber; // will probably need to add private JTextArea textArea;
                    readFile(fileName);
                }
            });
            bookGridPanel.add(bookButton);
        }

        bookPanel.add(bookGridPanel, BorderLayout.CENTER);
        bookPanel.setBackground(mintGreen);
        // Set frame
        homepageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepageFrame.setSize(Tales.screenW, Tales.screenH);
        homepageFrame.getContentPane().setBackground(mintGreen);
        //i dont have the image
        homepageFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kashi\\Downloads\\mintleaf_icon.png")); // MintLeaf Logic logo without text
        
//        //I changed the layout, but know the searchbar is covering half the title
//        homepageFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
//	    homepageFrame.add(titlePanel); //with the border layout i couldnt make this next to the search bar, it appeared under.
//	    homepageFrame.add(searchBarPanel);
//	    homepageFrame.setVisible(true);
//	    //this way i can control better where each this is.
//     	homepageFrame.setLayout(new BorderLayout());
//     	
//     	homepageFrame.add(bookPanel, BorderLayout.CENTER);
//     	homepageFrame.setJMenuBar(mb);
        //Set BorderLayout for the main content panel
        homepageFrame.setLayout(new BorderLayout());

        // Add components to the main content panel
        homepageFrame.add(title, BorderLayout.NORTH);
        homepageFrame.add(searchBarPanel, BorderLayout.WEST);
        homepageFrame.add(bookPanel, BorderLayout.CENTER);
        homepageFrame.setJMenuBar(mb);
     	
        homepageFrame.setVisible(true);
    }

    public void readFile(String fileName) {
		// TODO Auto-generated method stub
    	 try {
             BufferedReader reader = new BufferedReader(new FileReader(fileName));
             StringBuilder content = new StringBuilder();
             String line;
             while ((line = reader.readLine()) != null) {
                 content.append(line).append("\n");
             }
             reader.close();
             textArea.setText(content.toString());
             JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "File Content", JOptionPane.PLAIN_MESSAGE);
         } catch (FileNotFoundException e) {
        	 e.printStackTrace();
             JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
         }
    	 catch (IOException e) {
    		 JOptionPane.showMessageDialog(null, "Error reading the file", e.toString(), JOptionPane.ERROR_MESSAGE);
             
         }
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage();
            }
        });
    }
}
