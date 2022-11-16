package ui;

import model.Clothes;
import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShelterAppGui extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/shelter.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame login;
    private JFrame user;
    private JFrame sponsor;
    private JLabel login1;
    private JLabel login2;
    private JButton userB;
    private JButton sponsorB;
    private JButton list;
    private JButton listS;
    private JButton request;
    private JButton save;
    private JButton load;
    private JButton seeRequest;
    private JButton donation;
    private JButton select;
    private JPanel panel;
    private JPanel spaceArea;
    private JPanel buttonArea;
    private JPanel requestArea;
    private Shelter shelter;
    private DefaultListModel clothes;
    private DefaultListModel furniture;
    private DefaultListModel requests;





    public ShelterAppGui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initLoginPage();
        shelter = new Shelter();
        shelter.addFurniture("chair");
        shelter.addClothes(new Clothes("coat", "black", 45));
        shelter.addRequest("chair");
        shelter.fund(500);
        shelter.addRequest("blouse");
        shelter.addClothes(new Clothes("skirt", "white", 36));
        shelter.addFurniture("table");
    }

    public void initLoginPage() {
        frameInit();
        loginO();
        loginT();
        gridInit();

    }

    public void loginO() {
        ImageIcon image = new ImageIcon(new ImageIcon("src/main/logo.png").getImage().getScaledInstance(
                250, 250, Image.SCALE_DEFAULT));
        login1 = new JLabel();
        login1.setText("Welcome to the Shelter App");
        login1.setIcon(image);
        login1.setHorizontalTextPosition(JLabel.CENTER);
        login1.setVerticalTextPosition(JLabel.TOP);
        login1.setForeground(new Color(0, 102, 0));
        login1.setFont(new Font("MV Boli", Font.PLAIN, 35));
        login1.setIconTextGap(0);
        login1.setVerticalAlignment(JLabel.TOP);
        login1.setHorizontalAlignment(JLabel.CENTER);

        panel.add(login1);
    }

    public void loginT() {
        login2 = new JLabel();
        login2.setPreferredSize(new Dimension(500, 50));
        login2.setText("Choose how you want to login");
        login2.setFont(new Font("MV Boli", Font.BOLD, 22));
        login2.setHorizontalAlignment(JLabel.CENTER);

        panel.add(login2);
    }

    public void gridInit() {

        login.setLayout(new GridLayout(0,1));
        userB = new JButton("User");
        userB.addActionListener(this);
        userB.setPreferredSize(new Dimension(250, 100));
        panel.add(userB);
        sponsorB = new JButton("Sponsor");
        sponsorB.addActionListener(this);
        sponsorB.setPreferredSize(new Dimension(250, 100));
        panel.add(sponsorB);
        login.setContentPane(panel);
        login.setVisible(true);
    }

    public void frameInit() {
        login = new JFrame();
        login.setTitle("Shelter App");
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.setSize(1000, 700); //sets dimensioon
        login.setResizable(false);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER,500,10));
        panel.setBackground(new Color(204, 229, 250));

    }

    public void userPage() {
        login.setVisible(false);
        user = new JFrame();
        user.setLayout(new BorderLayout());
        user.setTitle("User Page");
        user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user.setSize(1000, 700); //sets dimensioon
        user.setResizable(false);
        spaceArea = new JPanel();
        spaceArea.setLayout(new GridLayout(3,0));
        user.setVisible(true);
        initButtons();
    }

    private void initButtons() {
        list = new JButton("Available items");
        list.addActionListener(this);
        request = new JButton("Make request");
        request.addActionListener(this);
        save = new JButton("Save changes");
        save.addActionListener(this);
        load = new JButton("Load changes");
        load.addActionListener(this);
        select = new JButton("Make Selection");
        select.addActionListener(this);
        buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        buttonArea.setSize(new Dimension(0, 0));
        buttonArea.add(list);
        buttonArea.add(request);
        buttonArea.add(save);
        buttonArea.add(load);
        user.add(buttonArea, BorderLayout.WEST);

    }

    private void printClothes() {
        clothes = new DefaultListModel();
        JList lcl = new JList(clothes);
        lcl.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(lcl);
        String title;
        title = "Shelter has the following clothes available:";
        clothes.addElement(title.toUpperCase());
        for (Clothes c : shelter.getClothes()) {
            String k;
            k = (c.getName().substring(0,1).toUpperCase() + c.getName().substring(1).toLowerCase()
                    + " in " + c.getColor() + " color and in size " + c.getSize());
            clothes.addElement(k);
        }
        if (clothes.getSize() < 1) {
            clothes.removeAllElements();
            clothes.addElement("There are no available clothes at this moment");
        }
        spaceArea.add(scrollPane);
        buttonArea.add(select);
    }

    private void printClothesS() {
        sponsor.removeAll();
        clothes = new DefaultListModel();
        JList lcl = new JList(clothes);
        lcl.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(lcl);
        String title;
        title = "Shelter has the following clothes available:";
        clothes.addElement(title.toUpperCase());
        for (Clothes c : shelter.getClothes()) {
            String k;
            k = (c.getName().substring(0,1).toUpperCase() + c.getName().substring(1).toLowerCase()
                    + " in " + c.getColor() + " color and in size " + c.getSize());
            clothes.addElement(k);
        }
        if (clothes.getSize() < 1) {
            clothes.removeAllElements();
            clothes.addElement("There are no available clothes at this moment");
        }
        spaceArea.add(scrollPane);

    }

    public void printFurniture() {
        furniture = new DefaultListModel();
        JList lfn = new JList(furniture);
        lfn.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPaneF = new JScrollPane(lfn);
        String title;
        title = "Shelter has the following furniture available:";
        furniture.addElement(title.toUpperCase());
        for (String s : shelter.getFurnitures()) {
            furniture.addElement(s);
        }
        if (furniture.getSize() < 1) {
            furniture.removeAllElements();
            furniture.addElement("There are no available clothes at this moment");
        }
        spaceArea.add(scrollPaneF);

    }

    public void printMonetary() {
        JTextArea donations =
                new JTextArea("Shelter received "
                        + shelter.getAmountFounded() + "$ in monetary donations", 2,1);
        donations.setFont(new Font("MV Boli", Font.ITALIC, 15));
        spaceArea.add(donations);
        user.add(spaceArea, BorderLayout.CENTER);
        spaceArea.revalidate();
        spaceArea.repaint();

    }

    public void printMonetarySponsor() {
        JTextArea donations =
                new JTextArea("Shelter received "
                        + shelter.getAmountFounded() + "$ in monetary donations", 2,1);
        donations.setFont(new Font("MV Boli", Font.ITALIC, 15));
        spaceArea.add(donations);
        sponsor.add(spaceArea, BorderLayout.CENTER);
        spaceArea.revalidate();
        spaceArea.repaint();

    }

    public void sponsorPage() {
        login.setVisible(false);
        sponsor = new JFrame();
        setLayout(new BorderLayout());
        sponsor.setTitle("Sponsor Page");
        sponsor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sponsor.setSize(1000, 700); //sets dimensioon
        sponsor.setResizable(false);
        spaceArea = new JPanel();
        spaceArea.setLayout(new GridLayout(3,0));
        requestArea = new JPanel();
        requestArea.setLayout(new GridLayout(0,1));
        sponsor.setVisible(true);
        initButtonS();
    }

    private void initButtonS() {
        listS = new JButton("Available items");
        listS.addActionListener(this);
        seeRequest = new JButton("Current request");
        seeRequest.addActionListener(this);
        donation = new JButton("Make a donation");
        donation.addActionListener(this);
        save = new JButton("Save changes");
        save.addActionListener(this);
        load = new JButton("Load changes");
        load.addActionListener(this);
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        buttonArea.setSize(new Dimension(0, 0));
        buttonArea.add(listS);
        buttonArea.add(seeRequest);
        buttonArea.add(donation);
        buttonArea.add(save);
        buttonArea.add(load);
        sponsor.add(buttonArea, BorderLayout.WEST);

    }

    private void makeRequests() {
    }

    private void makeSelection() {
    }

    private void printRequests() {
        sponsor.removeAll();
        requests = new DefaultListModel();
        JList lrs = new JList(requests);
        lrs.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPaneR = new JScrollPane(lrs);
        String title;
        title = "Shelter has the following requests:";
        requests.addElement(title.toUpperCase());
        for (String s : shelter.getRequests()) {
            requests.addElement(s);
        }
        if (requests.getSize() < 1) {
            requests.removeAllElements();
            requests.addElement("There are no requests at this moment");
        }
        requestArea.add(scrollPaneR);
        sponsor.add(requestArea);
        requestArea.revalidate();
        requestArea.repaint();
    }

    private void makeDonation() {
    }



    // EFFECTS: saves the changes
    private void saveShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved changes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shelter from file
    private void loadShelter() {
        try {
            shelter = jsonReader.read();
            JOptionPane.showMessageDialog(null,"Loaded shelter changes from " + JSON_STORE);
            load.setEnabled(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void userPrint() {
        printClothes();
        printFurniture();
        printMonetary();
    }

    public void sponsorPrint() {
        printClothesS();
        printFurniture();
        printMonetarySponsor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userB) {
            userPage();
        } else if (e.getSource() == sponsorB) {
            sponsorPage();
        } else if (e.getSource() == save) {
            saveShelter();
        } else if (e.getSource() == load) {
            loadShelter();
        } else if (e.getSource() == list) {
            userPrint();
        } else if (e.getSource() == listS) {
            sponsorPrint();
        } else if (e.getSource() == request) {
            makeRequests();
        } else if (e.getSource() == seeRequest) {
            printRequests();
        }  else if (e.getSource() == donation) {
            makeDonation();
        } else if (e.getSource() == select) {
            makeSelection();
        }
    }
}
