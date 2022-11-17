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
    private JLabel requestTittle;
    private JLabel clothesN;
    private JLabel clothesC;
    private JLabel clothesS;
    private JLabel furnitureN;
    private JLabel moneyN;
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
    private JButton submit;
    private JButton submitCloth;
    private JButton submitFurnit;
    private JButton submitMoney;
    private JPanel panel;
    private JPanel spaceArea;
    private JPanel buttonArea;
    private JPanel requestArea;
    private JPanel clothesSelection;
    private JPanel clothesTextField;
    private JPanel fnmnjl;
    private JPanel fnmntf;
    private JPanel fnmnbt;
    private Shelter shelter;
    private DefaultListModel clothes;
    private DefaultListModel furniture;
    private DefaultListModel requests;
    private JTextField makeRequest;
    private JTextField selectFurniture;
    private JTextField selectMoney;
    private JTextField selectClothesName;
    private JTextField selectClothesSize;
    private JTextField selectClothesColor;

    //EFFECTS: initializes json and adds item to shelter
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

    //MODIFIES:this
    //EFFECTS: Initializes login page
    public void initLoginPage() {
        frameInit();
        loginO();
        loginT();
        gridInit();

    }

    //MODIFIES:this
    //EFFECTS: sets welcome page photo and text
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

    //MODIFIES:this
    //EFFECTS: sets login option text
    public void loginT() {
        login2 = new JLabel();
        login2.setPreferredSize(new Dimension(500, 50));
        login2.setText("Choose how you want to login");
        login2.setFont(new Font("MV Boli", Font.BOLD, 22));
        login2.setHorizontalAlignment(JLabel.CENTER);

        panel.add(login2);
    }

    //MODIFIES:this
    //EFFECTS: sets login buttons
    public void gridInit() {

        login.setLayout(new GridLayout(0, 1));
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

    //MODIFIES:this
    //EFFECTS: sets login page frame
    public void frameInit() {
        login = new JFrame();
        login.setTitle("Shelter App");
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.setSize(1000, 700); //sets dimensioon
        login.setResizable(false);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 500, 10));
        panel.setBackground(new Color(204, 229, 250));

    }

    //MODIFIES:this
    //EFFECTS: sets use page frame and associated panels
    public void userPage() {
        login.setVisible(false);
        user = new JFrame();
        user.setLayout(new BorderLayout());
        user.setTitle("User Page");
        user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user.setSize(1000, 700); //sets dimensioon
        user.setResizable(false);
        spaceArea = new JPanel();
        spaceArea.setLayout(new GridLayout(0, 1));
        requestArea = new JPanel();
        requestArea.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 50));
        initButtons();
        initSelectionPage();
        user.setVisible(true);
    }

    //EFFECTS: sets up both selection and donation page
    private void initSelectionPage() {
        clothesSelection = new JPanel();
        clothesSelection.setLayout(new FlowLayout(FlowLayout.LEADING, 37, 50));
        clothesTextField = new JPanel();
        clothesTextField.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 50));
        fnmnjl = new JPanel();
        fnmnjl.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 50));
        fnmntf = new JPanel();
        fnmntf.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 50));
        fnmnbt = new JPanel();
        fnmnbt.setLayout(new FlowLayout(FlowLayout.LEADING, 90, 50));
        submitFurnit = new JButton("Submit");
        submitFurnit.setPreferredSize(new Dimension(100, 50));
        submitMoney = new JButton("Submit");
        submitMoney.setPreferredSize(new Dimension(100, 50));
        submitCloth = new JButton("Submit");
        submitCloth.setPreferredSize(new Dimension(100, 50));
    }

    //MODIFIES:this
    //EFFECTS: initializes login page buttons
    private void initButtons() {
        list = new JButton("Available items");
        list.addActionListener(this);
        submit = new JButton("Submit");
        submit.addActionListener(e -> {
            shelter.addRequest(makeRequest.getText());
            submit.setEnabled(false);
        });
        request = new JButton("Make request");
        request.addActionListener(this);
        save = new JButton("Save changes");
        save.addActionListener(this);
        load = new JButton("Load changes");
        load.addActionListener(this);
        select = new JButton("Make Selection");
        select.addActionListener(this);
        buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        buttonArea.add(list);
        buttonArea.add(request);
        buttonArea.add(save);
        buttonArea.add(load);
        user.add(buttonArea, BorderLayout.WEST);

    }

    //MODIFIES:this
    //EFFECTS: prints available clothes
    private void printClothes() {
        spaceArea.removeAll();
        clothes = new DefaultListModel();
        JList lcl = new JList(clothes);
        lcl.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(lcl);
        String title;
        title = "Shelter has the following clothes available:";
        clothes.addElement(title.toUpperCase());
        for (Clothes c : shelter.getClothes()) {
            String k;
            k = (c.getName().substring(0, 1).toUpperCase() + c.getName().substring(1).toLowerCase()
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

    //MODIFIES:this
    //EFFECTS: prints available furniture
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

    //MODIFIES:this
    //EFFECTS: prints available money
    public void printMonetary() {
        JTextArea donations =
                new JTextArea("Shelter received "
                        + shelter.getAmountFounded() + "$ in monetary donations", 2, 1);
        donations.setFont(new Font("MV Boli", Font.ITALIC, 15));
        spaceArea.add(donations);
        user.add(spaceArea, BorderLayout.CENTER);
        spaceArea.revalidate();
        spaceArea.repaint();

    }

    //MODIFIES:this
    //EFFECTS: lets a user make a new request
    private void makeRequests() {
        requestArea.removeAll();
        requestTittle = new JLabel("Make a request of the clothes or furniture you need");
        requestTittle.setFont(new Font("MV Boli", Font.ITALIC, 30));
        requestTittle.setBounds(10, 130, 150, 30);
        requestArea.add(requestTittle);
        makeRequest = new JTextField();
        makeRequest.setPreferredSize(new Dimension(350, 100));
        makeRequest.setFont(new Font("MV Boli", Font.ITALIC, 20));
        makeRequest.setBackground(Color.BLACK);
        makeRequest.setForeground(Color.WHITE);
        requestArea.add(makeRequest);
        requestArea.add(submit);
        user.add(requestArea, BorderLayout.CENTER);
        requestArea.revalidate();
        requestArea.repaint();
    }

    //MODIFIES:this
    //EFFECTS: lets user make a selection of available donations
    private void makeSelection() {
        spaceArea.removeAll();
        selectClothesLab();
        selectFurnitures();
        selectDonation();

    }

    //MODIFIES:this
    //EFFECTS: lets user select available money
    private void selectDonation() {
        fnmnbt.removeAll();
        submitFurnit.addActionListener(e -> {
            shelter.takeFurniture(selectFurniture.getText().toLowerCase());
            submitFurnit.setEnabled(false);
        });
        submitMoney.addActionListener(e -> {
            shelter.useDonations(Integer.valueOf(selectMoney.getText()));
            submitMoney.setEnabled(false);
        });
        fnmnbt.add(submitFurnit);
        fnmnbt.add(submitMoney);
        spaceArea.add(fnmnbt);
        user.add(spaceArea, BorderLayout.CENTER);
        revalidateRepaint(spaceArea);

    }

    //MODIFIES:this
    //EFFECTS: lets user select available furniture
    private void selectFurnitures() {
        fnmnjl.removeAll();
        fnmntf.removeAll();
        furnitureN = new JLabel("Enter the name of the furniture");
        moneyN = new JLabel("Enter the amount you want to take");
        fnmnjl.add(furnitureN);
        fnmnjl.add(moneyN);
        spaceArea.add(fnmnjl);
        selectFurniture = new JTextField();
        selectMoney = new JTextField();
        setJText(selectFurniture);
        setJText(selectMoney);
        fnmntf.add(selectFurniture);
        fnmntf.add(selectMoney);
        spaceArea.add(fnmntf);
    }

    //MODIFIES:this
    //EFFECTS: lets user select available clothes
    private void selectClothesLab() {
        clothesSelection.removeAll();
        clothesTextField.removeAll();
        clothesN = new JLabel("Enter the name of the clothes");
        clothesC = new JLabel("Enter the color of the clothes");
        clothesS = new JLabel("Enter the size from 30 to 54");
        clothesSelection.add(clothesN);
        clothesSelection.add(clothesC);
        clothesSelection.add(clothesS);
        spaceArea.add(clothesSelection);
        //revalidateRepaint(clothesSelection);
        selectClothesField();

    }

    //MODIFIES:this
    //EFFECTS: initiates clothes text fields, and sets button behavior
    private void selectClothesField() {
        selectClothesName = new JTextField();
        selectClothesColor = new JTextField();
        selectClothesSize = new JTextField();
        setJText(selectClothesName);
        setJText(selectClothesColor);
        setJText(selectClothesSize);
        submitCloth.addActionListener(e -> {
            Clothes cl = new Clothes(selectClothesName.getText(), selectClothesColor.getText(),
                    Integer.valueOf(selectClothesSize.getText()));
            if (shelter.getClothes().contains(cl)) {
                shelter.takeClothes(cl);
                submitCloth.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "There is no such an item");
            }
        });
        clothesTextField.add(selectClothesName);
        clothesTextField.add(selectClothesColor);
        clothesTextField.add(selectClothesSize);
        clothesTextField.add(submitCloth);
        spaceArea.add(clothesTextField);
        revalidateRepaint(clothesTextField);
    }

    //MODIFIES:this
    //EFFECTS:sets JText formation
    private void setJText(JTextField f) {
        f.setPreferredSize(new Dimension(200, 50));
        f.setFont(new Font("MV Boli", Font.ITALIC, 15));
        f.setBackground(Color.BLACK);
        f.setForeground(Color.WHITE);
    }

    //MODIFIES:this
    //EFFECTS:sets JPanel to be visible
    private void revalidateRepaint(JPanel p) {
        p.revalidate();
        p.repaint();
    }

    //MODIFIES:this
    //EFFECTS: sets sponsor page frame and associated panels
    public void sponsorPage() {
        login.setVisible(false);
        sponsor = new JFrame();
        setLayout(new BorderLayout());
        sponsor.setTitle("Sponsor Page");
        sponsor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sponsor.setSize(1000, 700); //sets dimensioon
        sponsor.setResizable(false);
        spaceArea = new JPanel();
        spaceArea.setLayout(new GridLayout(0, 1));
        requestArea = new JPanel();
        requestArea.setLayout(new GridLayout(0, 1));
        initSelectionPage();
        initButtonS();
        sponsor.setVisible(true);

    }

    //MODIFIES:this
    //EFFECTS: initiates all buttons
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
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        buttonArea.add(listS);
        buttonArea.add(seeRequest);
        buttonArea.add(donation);
        buttonArea.add(save);
        buttonArea.add(load);
        sponsor.add(buttonArea, BorderLayout.WEST);

    }

    //MODIFIES:this
    //EFFECTS: prints available clothes
    private void printClothesS() {
        spaceArea.removeAll();
        clothes = new DefaultListModel();
        JList lcl = new JList(clothes);
        lcl.setFont(new Font("MV Boli", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(lcl);
        String title;
        title = "Shelter has the following clothes available:";
        clothes.addElement(title.toUpperCase());
        for (Clothes c : shelter.getClothes()) {
            String k;
            k = (c.getName().substring(0, 1).toUpperCase() + c.getName().substring(1).toLowerCase()
                    + " in " + c.getColor() + " color and in size " + c.getSize());
            clothes.addElement(k);
        }
        if (clothes.getSize() < 1) {
            clothes.removeAllElements();
            clothes.addElement("There are no available clothes at this moment");
        }
        spaceArea.add(scrollPane);
    }

    //MODIFIES:this
    //EFFECTS: prints available money
    public void printMonetarySponsor() {
        JTextArea donations =
                new JTextArea("Shelter received "
                        + shelter.getAmountFounded() + "$ in monetary donations", 2, 1);
        donations.setFont(new Font("MV Boli", Font.ITALIC, 15));
        spaceArea.add(donations);
        sponsor.add(spaceArea, BorderLayout.CENTER);
        spaceArea.revalidate();
        spaceArea.repaint();
    }

    //MODIFIES:this
    //EFFECTS: prints current requests
    private void printRequests() {
        spaceArea.removeAll();
        requestArea.removeAll();
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

    //MODIFIES:this
    //EFFECTS: lets user make donations
    private void makeDonation() {
        spaceArea.removeAll();
        requestArea.removeAll();
        donateClothes();
        donateFurnitures();
        donateMonetary();
    }

    //MODIFIES:this
    //EFFECTS: lets user donate  money
    private void donateMonetary() {
        fnmnbt.removeAll();
        submitFurnit.addActionListener(e -> {
            shelter.addFurniture(selectFurniture.getText().toLowerCase());
            submitFurnit.setEnabled(false);
        });
        submitMoney.addActionListener(e -> {
            shelter.fund(Integer.valueOf(selectMoney.getText()));
            submitMoney.setEnabled(false);
        });
        fnmnbt.add(submitFurnit);
        fnmnbt.add(submitMoney);
        spaceArea.add(fnmnbt);
        sponsor.add(spaceArea, BorderLayout.CENTER);
        revalidateRepaint(spaceArea);
    }

    //MODIFIES:this
    //EFFECTS: lets user donate furniture
    private void donateFurnitures() {
        fnmnjl.removeAll();
        fnmntf.removeAll();
        furnitureN = new JLabel("Enter the name of the furniture");
        moneyN = new JLabel("Enter the amount you want to donate");
        fnmnjl.add(furnitureN);
        fnmnjl.add(moneyN);
        spaceArea.add(fnmnjl);
        selectFurniture = new JTextField();
        selectMoney = new JTextField();
        setJText(selectFurniture);
        setJText(selectMoney);
        fnmntf.add(selectFurniture);
        fnmntf.add(selectMoney);
        spaceArea.add(fnmntf);

    }

    //MODIFIES:this
    //EFFECTS: lets user donate clothes
    private void donateClothes() {
        clothesSelection.removeAll();
        clothesTextField.removeAll();
        clothesN = new JLabel("Enter the name of the clothes");
        clothesC = new JLabel("Enter the color of the clothes");
        clothesS = new JLabel("Enter the size from 30 to 54");
        clothesSelection.add(clothesN);
        clothesSelection.add(clothesC);
        clothesSelection.add(clothesS);
        spaceArea.add(clothesSelection);
        //revalidateRepaint(clothesSelection);
        donateClothesField();

    }

    //MODIFIES:this
    //EFFECTS: initiates clothes text fields, and sets button behavior
    private void donateClothesField() {
        selectClothesName = new JTextField();
        selectClothesColor = new JTextField();
        selectClothesSize = new JTextField();
        setJText(selectClothesName);
        setJText(selectClothesColor);
        setJText(selectClothesSize);
        submitCloth.addActionListener(e -> {
            Clothes cl = new Clothes(selectClothesName.getText(), selectClothesColor.getText(),
                    Integer.valueOf(selectClothesSize.getText()));
            shelter.addClothes(cl);
            submitCloth.setEnabled(false);
        });
        clothesTextField.add(selectClothesName);
        clothesTextField.add(selectClothesColor);
        clothesTextField.add(selectClothesSize);
        clothesTextField.add(submitCloth);
        spaceArea.add(clothesTextField);
        revalidateRepaint(clothesTextField);

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
            JOptionPane.showMessageDialog(null, "Loaded shelter changes from " + JSON_STORE);
            load.setEnabled(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES:this
    //EFFECTS: sets action for the list button
    public void userPrint() {
        printClothes();
        printFurniture();
        printMonetary();
    }

    //MODIFIES:this
    //EFFECTS: sets action for the listS button
    public void sponsorPrint() {
        printClothesS();
        printFurniture();
        printMonetarySponsor();
    }

    //MODIFIES: this
    //EFFECTS: handles button event
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
        } else if (e.getSource() == donation) {
            makeDonation();
        } else if (e.getSource() == select) {
            makeSelection();
        }

    }
}
