import javax.swing.*; // Importing the Swing library for GUI components
import javax.swing.border.EmptyBorder; // Importing a specific border class from Swing
import java.awt.*; // Importing the AWT library for basic windowing and UI functionality
import java.awt.event.ActionEvent; // Importing the ActionEvent class for handling events
import java.awt.event.ActionListener; // Importing the ActionListener interface for event listening
import java.io.*; // Importing classes related to file input/output operations

public class TextEditor implements ActionListener {
    JFrame frame; // Main window
    JTextArea textArea; // Text input/output area
    JMenuItem newFile, open, save; // Menu items for 'File' menu
    JMenuItem cut, copy, paste, selectAll, close; // Menu items for 'Edit' menu

    // Constructor
    TextEditor(){
        // Initialize frame
        frame = new JFrame();
        // Initialize menu bars and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        // Initialize text area
        textArea = new JTextArea();

        // Initialize menu items for 'File' menu
        newFile = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");

        // Adding Action Listener to menu items
        newFile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);

        // Adding menu items to 'File' menu
        file.add(newFile);
        file.add(open);
        file.add(save);

        // Initialize menu items for 'Edit' menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Adding Action Listener to menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Adding menu items to 'Edit' menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Adding menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // Creating the content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // Adding the text area to the content pane
        panel.add(textArea, BorderLayout.CENTER);

        // Adding scroll feature to the text area
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting the menu bar and scroll pane to the panel
        frame.setJMenuBar(menuBar);
        panel.add(scrollPane);

        // Adding the panel to the frame
        frame.add(panel);

        // Setting sizes and positions
        frame.setBounds(100, 100, 800, 500);
        panel.setBounds(0, 0, frame.getWidth() - 15, frame.getHeight() - 60);
        scrollPane.setBounds(0, 0, panel.getWidth() - 20, panel.getHeight() - 60);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Text Editor (By Dharmendra)");
    }

    // Entry point of the program
    public static void main(String[] args) {
        new TextEditor();
        System.out.println("Made by Dharmendra");
    }

    // Action performed method to handle user actions
    @Override
    public void actionPerformed(ActionEvent e) {
        // For creating a new text editor file
        if (e.getSource() == newFile) {
            new TextEditor();
        }

        // For opening an existing file in the editor
        if (e.getSource() == open) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate;
                    StringBuilder output = new StringBuilder();
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output.append(intermediate).append("\n");
                    }
                    textArea.setText(output.toString());
                } catch (Exception exception) {
                    System.out.println("Cannot open file");
                }
            }
        }

        // For saving a text file
        if (e.getSource() == save) {
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.setApproveButtonText("Save");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    BufferedWriter outfile;
                    outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // For cutting to the clipboard
        if (e.getSource() == cut) {
            textArea.cut();
        }

        // For copying to the clipboard
        if (e.getSource() == copy) {
            textArea.copy();
        }

        // For pasting from the clipboard to the text area
        if (e.getSource() == paste) {
            textArea.paste();
        }

        // For selecting all text in the text area
        if (e.getSource() == selectAll) {
            textArea.selectAll();
        }

        // For closing the Text Editor
        if (e.getSource() == close) {
            System.exit(0);
        }
    }
}
