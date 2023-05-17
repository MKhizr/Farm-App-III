
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The FarmMeGUI handles all the graphical user interface implementation for the FarmApp and provides an interface for all the functions in FarmApp
 *
 * @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class FarmAppGUI extends JFrame {

    private FarmApp FarmApp;

    private DataStorage DB;

    private JFrame FrameForMainFarmAppWindow;
    private JMenuBar MainMenuBarForFarmApp;
    private JMenu MainMenu,submenuaddCowTreatments,submenuaddSheepTreatments;
    private JMenuItem addCowTab, addSheepTab, deleteAnimalTab, getAnimalTab, addVetTab, addFarmworkerTab, deleteEmployeeTab, getEmployeeDetailTab;
    private JMenuItem listCowsTab, listSheepTab, listVetsTab, listFarmworkerTab, getAnimalTreatmentTab,getAnimalTreatmentOnGivenDateTab, submenuitemaddCowTreatmentsHealthTab,submenuitemaddCowTreatmentsCleaningTab,submenuitemaddSheepTreatmentsHealthTab,submenuitemaddSheepTreatmentsCleaningTab, feedingAnimalTab, getEmpSalaryTab, addMilkingMeasurementsTab, salaryComparatorTab;
    private JButton SaverAnimal;
    private JButton SaverEmployee,DatabaseWriter;

    private JFileChooser filerAnimal;
    private JFileChooser filerEmployee;


    /**
     * Instantiates a new Farm app gui.
     *
     * @param FarmApp the farm app
     */
    public FarmAppGUI(FarmApp FarmApp, DataStorage DB) throws SQLException, IOException, NoSuchAlgorithmException {

        this.FarmApp = FarmApp;
        this.DB = DB;





        //I have created gaps in the newly created functions here so that you can easily differentiate and read
        /*The application works as following: when you run the application, database connection is setup and data is read from database with the help of readData function
        provided below. I have created extra arraylist so that later in writedata function we first check if arraylist is modified or not, if not modified then we don't need to
        delete data from database then write entire arraylist again but if arraylist is modified then we delete data first and then write entire arraylist of database, makes things simpler.
        below you will see a databsewriter function associated to push button which is responsible to write data to database and also created the file for serialised objects and MD5 file
        so when you exit the application, kindly do not forget to click the "database and MD5 writer" push button so that data can be written to database and also serialised object file and md5 file is created.
        thread start is also called here which will do the security check.
         */


        DB.readData();
        ArrayList<Animal> AnimalArrayListToCheckIfChangesMade = new ArrayList<Animal>();
        ArrayList<Employee> EmployeeArrayListToCheckIfChangesMade = new ArrayList<Employee>();
        AnimalArrayListToCheckIfChangesMade.addAll(FarmApp.animals);
        EmployeeArrayListToCheckIfChangesMade.addAll(FarmApp.employee);



        Thread thread  = new Thread(DB);
        thread.start();











        FrameForMainFarmAppWindow = new JFrame("Farm App");
        FrameForMainFarmAppWindow.setSize(900,800);
        FrameForMainFarmAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FarmApp");
        FrameForMainFarmAppWindow.add(title);


        MainMenuBarForFarmApp = new JMenuBar();
        FrameForMainFarmAppWindow.setJMenuBar(MainMenuBarForFarmApp);


        MainMenu = new JMenu("MENU");
        submenuaddCowTreatments = new JMenu("Add cow treatment");
        submenuaddSheepTreatments = new JMenu("Add sheep treatment");
        MainMenuBarForFarmApp.add(MainMenu);


        addCowTab = new JMenuItem("Add Cow");
        addSheepTab = new JMenuItem("Add Sheep");
        deleteAnimalTab = new JMenuItem("Delete Animal");
        getAnimalTab = new JMenuItem("Get Animal Details");
        addVetTab = new JMenuItem("Add Vet");
        addFarmworkerTab = new JMenuItem("Add Farmworker");
        deleteEmployeeTab = new JMenuItem("Delete employee");
        getEmployeeDetailTab = new JMenuItem("Get employee Details");
        submenuitemaddCowTreatmentsHealthTab = new JMenuItem("Add Health treatment");
        submenuitemaddCowTreatmentsCleaningTab = new JMenuItem("Add Cleaning treatment");
        submenuitemaddSheepTreatmentsHealthTab = new JMenuItem("Add Health treatment");
        submenuitemaddSheepTreatmentsCleaningTab = new JMenuItem("Add Cleaning treatment");
        getAnimalTreatmentTab =new JMenuItem("Get animal treatment");
        getAnimalTreatmentOnGivenDateTab =new JMenuItem("Get animal treatment on given date");
        listCowsTab = new JMenuItem("List all Cows");
        listSheepTab = new JMenuItem("List all sheeps");
        listVetsTab = new JMenuItem("List all vets");
        listFarmworkerTab = new JMenuItem("List all farmworkers");
        feedingAnimalTab = new JMenuItem("Feed animal");
        getEmpSalaryTab = new JMenuItem("Get employee salary");
        addMilkingMeasurementsTab = new JMenuItem("Add milking measurements");
        salaryComparatorTab = new JMenuItem("Compare salary");



        MainMenu.add(addCowTab);
        MainMenu.add(addSheepTab);
        MainMenu.add(deleteAnimalTab);
        MainMenu.add(getAnimalTab);
        MainMenu.add(addVetTab);
        MainMenu.add(addFarmworkerTab);
        MainMenu.add(deleteEmployeeTab);
        MainMenu.add(getEmployeeDetailTab);
        submenuaddCowTreatments.add(submenuitemaddCowTreatmentsHealthTab);
        submenuaddCowTreatments.add(submenuitemaddCowTreatmentsCleaningTab);
        submenuaddSheepTreatments.add(submenuitemaddSheepTreatmentsHealthTab);
        submenuaddSheepTreatments.add(submenuitemaddSheepTreatmentsCleaningTab);
        MainMenu.add(submenuaddCowTreatments);
        MainMenu.add(submenuaddSheepTreatments);
        MainMenu.add(getAnimalTreatmentTab);
        MainMenu.add(getAnimalTreatmentOnGivenDateTab);
        MainMenu.add(listCowsTab);
        MainMenu.add(listSheepTab);
        MainMenu.add(listVetsTab);
        MainMenu.add(listFarmworkerTab);
        MainMenu.add(feedingAnimalTab);
        MainMenu.add(getEmpSalaryTab);
        MainMenu.add(addMilkingMeasurementsTab);
        MainMenu.add(salaryComparatorTab);




        filerAnimal = new JFileChooser();
        filerAnimal.setPreferredSize(new Dimension(600,300));

        filerEmployee = new JFileChooser();
        filerEmployee.setPreferredSize(new Dimension(600,300));


        SaverAnimal = new JButton("Save animal data to file.");
        SaverEmployee = new JButton("Save employee data to file.");


        JPanel FilePanelAnimal = new JPanel();
        JPanel FilePanelEmployee = new JPanel();


        FilePanelAnimal.add(filerAnimal);
        FilePanelEmployee.add(filerEmployee);

        FilePanelAnimal.add(SaverAnimal);
        FilePanelEmployee.add(SaverEmployee);

        FrameForMainFarmAppWindow.setLayout(new BorderLayout());

        FrameForMainFarmAppWindow.add(FilePanelAnimal, BorderLayout.NORTH);
        FrameForMainFarmAppWindow.add(FilePanelEmployee, BorderLayout.SOUTH);


        JPanel DatabasePanel = new JPanel();
        DatabaseWriter = new JButton("Database and MD5 writer");
        DatabasePanel.add(DatabaseWriter);
        FrameForMainFarmAppWindow.add(DatabasePanel);










        DatabaseWriter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DB.writeData(AnimalArrayListToCheckIfChangesMade, EmployeeArrayListToCheckIfChangesMade);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                File AnimalFile = new File("AnimalDataAssignment4.bin");

                FileOutputStream fileOutputStreamAnimal = null;
                ObjectOutputStream ObjectStreamAnimal = null;

                try {
                    fileOutputStreamAnimal = new FileOutputStream(AnimalFile);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ObjectStreamAnimal = new ObjectOutputStream(fileOutputStreamAnimal);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    ObjectStreamAnimal.writeObject(FarmApp.animals);
                } catch (IOException ex) {
                    System.out.println(ex);
                }

                try {
                    ObjectStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fileOutputStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    File MD5File = new File("MD5Data.bin");
                    FileInputStream fis = new FileInputStream(AnimalFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int ch;
                    while ((ch = bis.read()) != -1) {
                        baos.write(ch);
                    }

                    byte buffer[] = baos.toByteArray();

                    MessageDigest algorithm = MessageDigest.getInstance("MD5");

                    algorithm.reset();
                    algorithm.update(buffer);
                    byte digest[] = algorithm.digest();

                    StringBuffer hexString = new StringBuffer();

                    for (int i = 0; i < digest.length; i++) {
                        hexString.append(Integer.toHexString(0xFF & (int) digest[i]));
                        hexString.append(" ");
                    }

                    System.out.println(hexString.toString());


                    FileOutputStream File_stream_for_MD5 = new FileOutputStream(MD5File);
                    BufferedOutputStream Buffered_stream_for_MD5 = new BufferedOutputStream(File_stream_for_MD5);
                    Buffered_stream_for_MD5.write(hexString.toString().getBytes());
                    Buffered_stream_for_MD5.close();
                    File_stream_for_MD5.close();

                } catch (FileNotFoundException ex) {
                    System.out.println("No such file.");
                } catch (Exception ex) {
                    System.out.println("Opps: " + ex.getMessage());
                }
            }
        });













        /**
         * Upon pressing SaverAnimal push button, all the animal data is written to that file
         */
        SaverAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                File AnimalFile = new File("AnimalData.bin");



                FileOutputStream fileOutputStreamAnimal = null;
                ObjectOutputStream ObjectStreamAnimal = null;



                try {
                    fileOutputStreamAnimal = new FileOutputStream(AnimalFile);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ObjectStreamAnimal = new ObjectOutputStream(fileOutputStreamAnimal);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try{
                    ObjectStreamAnimal.writeObject(FarmApp.animals);

                } catch (IOException ex){
                    System.out.println(ex);
                }

                try {
                    ObjectStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fileOutputStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        /**
         * Upon pressing SaverEmployee push button, all the employee data is written to that file
         */
        SaverEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                File EmployeeFile = new File("EmployeeData.bin");


                FileOutputStream fileOutputStreamEmployee = null;
                ObjectOutputStream ObjectStreamEmployee = null;


                try {
                    fileOutputStreamEmployee = new FileOutputStream(EmployeeFile);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ObjectStreamEmployee = new ObjectOutputStream(fileOutputStreamEmployee);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try{
                    ObjectStreamEmployee.writeObject(FarmApp.employee);

                } catch (IOException ex){
                    System.out.println(ex);
                }

                try {
                    ObjectStreamEmployee.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fileOutputStreamEmployee.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });

        /**
         * Allows the user to select the file which has animal data
         */
        filerAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Animal TempAnimal = null;
                FileInputStream FileStreamAnimal = null;
                ObjectInputStream ObjectStreamAnimal = null;

                File selectedFileAnimal = filerAnimal.getSelectedFile();
                System.out.printf(selectedFileAnimal.getName());

                try {
                    FileStreamAnimal = new FileInputStream(selectedFileAnimal);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    ObjectStreamAnimal = new ObjectInputStream(FileStreamAnimal);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    FarmApp.animals = (ArrayList<Animal>) ObjectStreamAnimal.readObject();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    ObjectStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    FileStreamAnimal.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });

        /**
         * Allows the user to select the file which has employee data
         */
        filerEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Employee TempEmployee = null;
                FileInputStream FileStreamEmployee = null;
                ObjectInputStream ObjectStreamEmployee = null;

                File selectedFileEmployee = filerEmployee.getSelectedFile();
                System.out.printf(selectedFileEmployee.getName());

                try {
                    FileStreamEmployee = new FileInputStream(selectedFileEmployee);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    ObjectStreamEmployee = new ObjectInputStream(FileStreamEmployee);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    FarmApp.employee = (ArrayList<Employee>) ObjectStreamEmployee.readObject();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    ObjectStreamEmployee.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    FileStreamEmployee.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });


        /**
         * Upon pressing addCowTab menu item, user adds details of cow after which a cow is added to the application
         *
         */
        addCowTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogtoaddcow = new JDialog(FrameForMainFarmAppWindow, "Add Cow", true);

                JPanel inner_panel = new JPanel(new FlowLayout());

                JLabel labelfortagnumber = new JLabel("Tag number: ");
                JTextField textfieldfortagnumber = new JTextField(15);
                inner_panel.add(labelfortagnumber);
                inner_panel.add(textfieldfortagnumber);

                JLabel labelforgender = new JLabel("Gender(M/F): ");
                JTextField textfieldforgender = new JTextField(15);
                inner_panel.add(labelforgender);
                inner_panel.add(textfieldforgender);

                JLabel labelfordateofbirth = new JLabel("Date of Birth (dd/MM/yyyy): ");
                JTextField textfieldfordateofbirth = new JTextField(11);
                inner_panel.add(labelfordateofbirth);
                inner_panel.add(textfieldfordateofbirth);

                JLabel labelforpurchased = new JLabel("Enter true if purchased, false if not: ");
                JTextField textfieldforpurchased = new JTextField(11);
                inner_panel.add(labelforpurchased);
                inner_panel.add(textfieldforpurchased);

                JLabel labelforweight = new JLabel("Weight: ");
                JTextField textfieldforweight = new JTextField(5);
                inner_panel.add(labelforweight);
                inner_panel.add(textfieldforweight);

                JButton buttontoadd = new JButton("Add");

                buttontoadd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagno = Integer.parseInt(textfieldfortagnumber.getText());

                        String gender = textfieldforgender.getText();

                        double weight = Double.parseDouble(textfieldforweight.getText());

                        Date dateofBirth = null;
                        try {
                            dateofBirth = new SimpleDateFormat("dd/MM/yyyy").parse(textfieldfordateofbirth.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        boolean purchased = Boolean.parseBoolean(textfieldforpurchased.getText());

                        System.out.println(tagno);
                        System.out.println(gender);
                        System.out.println(dateofBirth);
                        System.out.println(purchased);
                        System.out.println(weight);


                        FarmApp.addCow(tagno, gender, dateofBirth, purchased, weight);

                    }
                });

                inner_panel.add(buttontoadd);

                dialogtoaddcow.add(inner_panel);
                dialogtoaddcow.pack();
                dialogtoaddcow.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogtoaddcow.setVisible(true);


            }
        });

        /**
         * Upon pressing addSheepTab menu item, user adds details of sheep after which a sheep is added to the application
         *
         */
        addSheepTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog SheepaddDialog = new JDialog(FrameForMainFarmAppWindow, "Add Sheep", true);

                JPanel inner_Panel = new JPanel(new FlowLayout());

                JLabel labelForTagNumber = new JLabel("Tagno: ");
                JTextField TextfieldForTagNumber = new JTextField(8);
                inner_Panel.add(labelForTagNumber);
                inner_Panel.add(TextfieldForTagNumber);

                JLabel labelForgender = new JLabel("Gender(M/F): ");
                JTextField textfieldForgender = new JTextField(9);
                inner_Panel.add(labelForgender);
                inner_Panel.add(textfieldForgender);

                JLabel labelForDateofBirth = new JLabel("Date of Birth (dd/MM/yyyy): ");
                JTextField TextFieldfordateofBirth = new JTextField(11);
                inner_Panel.add(labelForDateofBirth);
                inner_Panel.add(TextFieldfordateofBirth);

                JLabel Labelforpurchased = new JLabel("Enter true if Purchased, false if not: ");
                JTextField TextFieldforpurchased = new JTextField(8);
                inner_Panel.add(Labelforpurchased);
                inner_Panel.add(TextFieldforpurchased);


                JButton addsheepButton = new JButton("Add");

                addsheepButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagNumber = Integer.parseInt(TextfieldForTagNumber.getText());

                        String gender = textfieldForgender.getText();


                        Date dateofBirth = null;
                        try {
                            dateofBirth = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofBirth.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        boolean purchased = Boolean.parseBoolean(TextFieldforpurchased.getText());

                        FarmApp.addSheep(tagNumber, gender, dateofBirth, purchased);

                        SheepaddDialog.dispose();

                    }

                });

                inner_Panel.add(addsheepButton);

                SheepaddDialog.add(inner_Panel);
                SheepaddDialog.setPreferredSize(new Dimension(600, 200));
                SheepaddDialog.pack();
                SheepaddDialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                SheepaddDialog.setVisible(true);


            }

        });

        /**
         * Upon pressing deleteAnimalTab menu item, user enters details of sheep/cow after which a sheep/Cow is deleted from the application
         *
         */
        deleteAnimalTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogfordeleteanimal = new JDialog(FrameForMainFarmAppWindow, "Delete animal", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel Labelfortagnumber = new JLabel("Tagno: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);


                String [] cowORsheep = {"Cow","Sheep"};
                JComboBox<String> combination = new JComboBox<>(cowORsheep);


                JButton deleteanimalButton = new JButton("Delete");

                deleteanimalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Cow")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            FarmApp.deleteCow(tagnumber);
                        }
                        else if(choice.equals("Sheep")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            FarmApp.deleteSheep(tagnumber);
                        }

                        dialogfordeleteanimal.dispose();
                    }
                });

                innerpanel.add(deleteanimalButton);
                innerpanel.add(combination);

                dialogfordeleteanimal.add(innerpanel);
                dialogfordeleteanimal.setPreferredSize(new Dimension(600, 200));
                dialogfordeleteanimal.pack();
                dialogfordeleteanimal.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogfordeleteanimal.setVisible(true);

            }
        });


        /**
         * Upon pressing getAnimalTab menu item, user enters details of sheep/cow after which they can get details of that cow/sheep
         *
         */
        getAnimalTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforgetanimal = new JDialog(FrameForMainFarmAppWindow, "Details of animal", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);


                JLabel Labelfortagnumber = new JLabel("Tagno: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);


                String [] cowORsheep = {"Cow","Sheep"};
                JComboBox<String> combination = new JComboBox<>(cowORsheep);
                innerpanel.add(combination);

                JButton getanimalButton = new JButton("Get");

                getanimalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Cow")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            Cow TempCow =  FarmApp.getCowDetails(tagnumber);

                            if (TempCow == null ) {
                                String details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (TempCow != null ) {
                                String details = "Tag no  of cow: " + TempCow.getTagNo() + "\n";


                                if (TempCow.isPurchased() == true) {
                                    details += "Cow is purchased. \n";
                                } else {
                                    details += "Cow is farm raised.\n";

                                }

                                details += "Date of birth of cow: " + TempCow.getDateOfBirth() + "\n";
                                details += "Gender of cow: " + TempCow.getGender() + "\n";
                                details += "Weight of cow is: " + TempCow.getWeight() + "\n";

                                TextAreafordata.setText(details);
                            }
                        }

                        else if(choice.equals("Sheep")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            Sheep TempSheep =  FarmApp.getSheepDetails(tagnumber);

                            if (TempSheep == null ) {
                                String details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (TempSheep != null ) {
                                String details = "Tag no  of sheep: " + TempSheep.getTagNo() + "\n";
                                details +=  "Date of birth of sheep: " + TempSheep.getDateOfBirth() + "\n";
                                details +=  "Gender of sheep: " + TempSheep.getGender() + "\n";

                                if (TempSheep.isPurchased() == true) {
                                    details += "sheep is purchased. \n";
                                } else if (TempSheep.isPurchased() == false) {
                                    details +=  "sheep is farm raised. \n";
                                }
                                TextAreafordata.setText(details);
                            }
                        }

                    }
                });

                innerpanel.add(getanimalButton);
                dialogforgetanimal.add(innerpanel);
                dialogforgetanimal.setPreferredSize(new Dimension(600, 200));
                dialogforgetanimal.pack();
                dialogforgetanimal.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforgetanimal.setVisible(true);


            }
        });


        /**
         * Upon pressing addVetTab menu item, user adds details of vet after which a vet is added to the application
         *
         */
        addVetTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addingVetdialog = new JDialog(FrameForMainFarmAppWindow, "Add Vet", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForIDNumber = new JLabel("Employee ID: ");
                JTextField TextfieldForIDNumber = new JTextField(10);
                inner_Panel.add(labelForIDNumber);
                inner_Panel.add(TextfieldForIDNumber);

                JLabel labelForgender = new JLabel("Gender: ");
                JTextField textfieldForgender = new JTextField(10);
                inner_Panel.add(labelForgender);
                inner_Panel.add(textfieldForgender);

                JLabel labelForDateofBirth = new JLabel("Date of Birth (dd/MM/yyyy): ");
                JTextField TextFieldfordateofBirth = new JTextField(10);
                inner_Panel.add(labelForDateofBirth);
                inner_Panel.add(TextFieldfordateofBirth);

                JLabel Labelfordegree = new JLabel("Enter true if you have bsc degree otherwise false: ");
                JTextField TextFieldfordegree = new JTextField(10);
                inner_Panel.add(Labelfordegree);
                inner_Panel.add(TextFieldfordegree);

                JLabel labelForDateofgraduation = new JLabel("Date of graduation (dd/MM/yyyy): ");
                JTextField TextFieldfordateofgraduation = new JTextField(10);
                inner_Panel.add(labelForDateofgraduation);
                inner_Panel.add(TextFieldfordateofgraduation);

                JLabel Labelforexpertise = new JLabel("Expertise level as value: ");
                JTextField TextFieldforexpertise = new JTextField(10);
                inner_Panel.add(Labelforexpertise);
                inner_Panel.add(TextFieldforexpertise);


                JButton addvetButton = new JButton("Add");

                addvetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int idnum = Integer.parseInt(TextfieldForIDNumber.getText());

                        String gender = textfieldForgender.getText();


                        Date dateofBirth = null;
                        try {
                            dateofBirth = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofBirth.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        boolean degree = Boolean.parseBoolean(TextFieldfordegree.getText());

                        Date dateofgraduation = null;
                        try {
                            dateofgraduation = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofgraduation.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }


                        int explevel = Integer.parseInt(TextFieldforexpertise.getText());

                        FarmApp.addVet(idnum, gender, dateofBirth, degree, dateofgraduation, explevel);

                        addingVetdialog.dispose();

                    }

                });

                inner_Panel.add(addvetButton);
                addingVetdialog.add(inner_Panel);
                addingVetdialog.setPreferredSize(new Dimension(600, 200));
                addingVetdialog.pack();
                addingVetdialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addingVetdialog.setVisible(true);

            }

        });

        /**
         * Upon pressing addFarmworkerTab menu item, user adds details of farmworker after which a farmworker is added to the application
         *
         */
        addFarmworkerTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addingfarmworkerdialog = new JDialog(FrameForMainFarmAppWindow, "Add farm worker", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForIDNumber = new JLabel("Employee ID: ");
                JTextField TextfieldForIDNumber = new JTextField(10);
                inner_Panel.add(labelForIDNumber);
                inner_Panel.add(TextfieldForIDNumber);

                JLabel labelForgender = new JLabel("Gender: ");
                JTextField textfieldForgender = new JTextField(10);
                inner_Panel.add(labelForgender);
                inner_Panel.add(textfieldForgender);

                JLabel labelForDateofBirth = new JLabel("Date of Birth (dd/MM/yyyy): ");
                JTextField TextFieldfordateofBirth = new JTextField(10);
                inner_Panel.add(labelForDateofBirth);
                inner_Panel.add(TextFieldfordateofBirth);

                JLabel Labelforfarmname = new JLabel("Enter previous farm name: ");
                JTextField TextFieldforfarmname = new JTextField(10);
                inner_Panel.add(Labelforfarmname);
                inner_Panel.add(TextFieldforfarmname);


                JLabel Labelforexperience = new JLabel("Work experience as value: ");
                JTextField TextFieldforexpertience = new JTextField(10);
                inner_Panel.add(Labelforexperience);
                inner_Panel.add(TextFieldforexpertience);


                JButton addfarmworkerButton = new JButton("Add");

                addfarmworkerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int idnum = Integer.parseInt(TextfieldForIDNumber.getText());

                        String gender = textfieldForgender.getText();


                        Date dateofBirth = null;
                        try {
                            dateofBirth = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofBirth.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        String previousfarm = TextFieldforfarmname.getText();

                        int experience = Integer.parseInt(TextFieldforexpertience.getText());


                        FarmApp.addFarmworker(idnum, gender, dateofBirth, previousfarm, experience);

                        addingfarmworkerdialog.dispose();

                    }

                });

                inner_Panel.add(addfarmworkerButton);
                addingfarmworkerdialog.add(inner_Panel);
                addingfarmworkerdialog.setPreferredSize(new Dimension(600, 200));
                addingfarmworkerdialog.pack();
                addingfarmworkerdialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addingfarmworkerdialog.setVisible(true);




            }

        });


        /**
         * Upon pressing deleteEmployeeTab menu item, user enters details of vet/farmworker after which a vet/farmworker is deleted from the application
         *
         */
        deleteEmployeeTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogfordeleteemployee = new JDialog(FrameForMainFarmAppWindow, "Delete employee", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel Labelforemployeeid = new JLabel("ID: ");
                JTextField TextFieldforemploeeid = new JTextField(10);
                innerpanel.add(Labelforemployeeid);
                innerpanel.add(TextFieldforemploeeid);

                String [] vetORfarmworke = {"Vet","Farmworker"};
                JComboBox<String> combination = new JComboBox<>(vetORfarmworke);

                JButton deleteemployeeButton = new JButton("Delete");

                deleteemployeeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Vet")) {

                            int tagnumber = Integer.parseInt(TextFieldforemploeeid.getText());

                            FarmApp.deleteVet(tagnumber);
                        }
                        else if(choice.equals("Farmworker")) {

                            int tagnumber = Integer.parseInt(TextFieldforemploeeid.getText());

                            FarmApp.deleteFarmworker(tagnumber);
                        }

                        dialogfordeleteemployee.dispose();
                    }
                });

                innerpanel.add(deleteemployeeButton);
                innerpanel.add(combination);

                dialogfordeleteemployee.add(innerpanel);
                dialogfordeleteemployee.setPreferredSize(new Dimension(600, 200));
                dialogfordeleteemployee.pack();
                dialogfordeleteemployee.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogfordeleteemployee.setVisible(true);

            }
        });

        /**
         * Upon pressing getEmployeeDetailsTab menu item, user enters details of vet/farmworker after which they can get details of that vet/farmworker
         *
         */
        getEmployeeDetailTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforgetemployee = new JDialog(FrameForMainFarmAppWindow, "Details of employee", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);

                JLabel Labelforemployeeid = new JLabel("ID: ");
                JTextField TextFieldforemployeeid = new JTextField(10);
                innerpanel.add(Labelforemployeeid);
                innerpanel.add(TextFieldforemployeeid);


                String [] vetORfarmworker = {"Vet","Farmworker"};
                JComboBox<String> combination = new JComboBox<>(vetORfarmworker);
                innerpanel.add(combination);

                JButton getemployeeButton = new JButton("Get");

                getemployeeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Vet")) {

                            int id = Integer.parseInt(TextFieldforemployeeid.getText());

                            Veterinary TempVet =  FarmApp.getVetDetails(id);

                            if (TempVet == null ) {
                                String details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (TempVet != null ) {
                                String details = "Vet ID is: " + TempVet.getEmpID()  + "\n";
                                details += "Gender: " + TempVet.getGender()  + "\n";
                                details += "Date of birth: " + TempVet.getDateOfBirth()  + "\n";

                                if (TempVet.isBScDegree() == true) {
                                    details += "Veterinary has a BSc degree. \n";
                                } else if (TempVet.isBScDegree() == false) {
                                    details += "Veterinary does not have a BSc degree. \n";
                                }

                                if (TempVet.isBScDegree() == true) {
                                    details += "Date of graduation of veterinary is: " + TempVet.getDateOfGraduation() + "\n";
                                    details += "Expertise level of veterinary is: " + TempVet.getExpertiseLevel() +"\n";
                                }

                                TextAreafordata.setText(details);
                            }
                        }

                        else if(choice.equals("Farmworker")) {

                            int id = Integer.parseInt(TextFieldforemployeeid.getText());

                            FarmWorker Tempfarmworker =  FarmApp.getFarmworkerDetails(id);

                            if (Tempfarmworker == null ) {
                                String details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (Tempfarmworker != null ) {
                                String details = "Vet ID is: " + Tempfarmworker.getEmpID() + "\n";
                                details += "Gender: " + Tempfarmworker.getGender() + "\n";
                                details += "Date of birth: " + Tempfarmworker.getDateOfBirth() + "\n";
                                details += "Previous farm name: "+ Tempfarmworker.getPreviousFarmName() + "\n";
                                details +=  "Work experience " + Tempfarmworker.getWorkexperience() + "\n";
                                TextAreafordata.setText(details);
                            }
                        }

                    }
                });

                innerpanel.add(getemployeeButton);
                dialogforgetemployee.add(innerpanel);
                dialogforgetemployee.setPreferredSize(new Dimension(600, 200));
                dialogforgetemployee.pack();
                dialogforgetemployee.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforgetemployee.setVisible(true);

            }
        });


        /**
         * Upon pressing submenuitemaddCowTreatmentsCleaningTab menu item, user adds details of cleaning treatments of cow after which cleaning treatment is added to the application for that cow
         *
         */
        submenuitemaddCowTreatmentsCleaningTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addCowTreatmentsCleaningDialog = new JDialog(FrameForMainFarmAppWindow, "Add cleaning treatment for cow", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForTagNumber = new JLabel("Tag no: ");
                JTextField TextfieldForTagNumber = new JTextField(10);
                inner_Panel.add(labelForTagNumber);
                inner_Panel.add(TextfieldForTagNumber);


                JLabel labelForDateoftreatment = new JLabel("Date of treatment (dd/MM/yyyy): ");
                JTextField TextFieldfordateoftreatment = new JTextField(10);
                inner_Panel.add(labelForDateoftreatment);
                inner_Panel.add(TextFieldfordateoftreatment);

                JLabel Labelformaterialsused = new JLabel("Materials used: ");
                JTextField TextFieldformaterialsused = new JTextField(10);
                inner_Panel.add(Labelformaterialsused);
                inner_Panel.add(TextFieldformaterialsused);

                JLabel labelForfarmworkerID = new JLabel("Farm worker ID: ");
                JTextField TextfieldForFarmworkerID = new JTextField(10);
                inner_Panel.add(labelForfarmworkerID);
                inner_Panel.add(TextfieldForFarmworkerID);


                JButton addtreatmentButton = new JButton("Add");

                addtreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagNumber = Integer.parseInt(TextfieldForTagNumber.getText());

                        Date dateofTreatment = null;
                        try {
                            dateofTreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateoftreatment.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        String material = TextFieldformaterialsused.getText();


                        int farmworkerid = Integer.parseInt(TextfieldForFarmworkerID.getText());


                        FarmApp.addCowCleaningTreatment(tagNumber, dateofTreatment,material,farmworkerid);

                    }

                });

                inner_Panel.add(addtreatmentButton);
                addCowTreatmentsCleaningDialog.add(inner_Panel);
                addCowTreatmentsCleaningDialog.pack();
                addCowTreatmentsCleaningDialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addCowTreatmentsCleaningDialog.setVisible(true);

            }

        });


        /**
         * Upon pressing submenuitemaddCowTreatmentsHealthTab menu item, user adds details of health treatments of cow after which health treatment is added to the application for that cow
         *
         */
        submenuitemaddCowTreatmentsHealthTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addCowTreatmentsHealthDialog = new JDialog(FrameForMainFarmAppWindow, "Add health treatment for cow", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForTagNumber = new JLabel("Tag no: ");
                JTextField TextfieldForTagNumber = new JTextField(10);
                inner_Panel.add(labelForTagNumber);
                inner_Panel.add(TextfieldForTagNumber);


                JLabel labelForDateoftreatment = new JLabel("Date of treatment (dd/MM/yyyy): ");
                JTextField TextFieldfordateoftreatment = new JTextField(10);
                inner_Panel.add(labelForDateoftreatment);
                inner_Panel.add(TextFieldfordateoftreatment);

                JLabel Labelforemergency = new JLabel("Enter true if emergency, false if not: ");
                JTextField TextFieldforemergency = new JTextField(10);
                inner_Panel.add(Labelforemergency);
                inner_Panel.add(TextFieldforemergency);

                JLabel labelForvetID = new JLabel("Vet ID: ");
                JTextField TextfieldForvetID = new JTextField(10);
                inner_Panel.add(labelForvetID);
                inner_Panel.add(TextfieldForvetID);


                JLabel labelFormedicationdetails = new JLabel("Medication details: ");
                JTextField TextfieldFormedicationdetails = new JTextField(10);
                inner_Panel.add(labelFormedicationdetails);
                inner_Panel.add(TextfieldFormedicationdetails);

                JLabel labelFormedicationduration = new JLabel("Value for duration: ");
                JTextField TextfieldFormedicationduration = new JTextField(10);
                inner_Panel.add(labelFormedicationduration);
                inner_Panel.add(TextfieldFormedicationduration);

                JLabel labelForDateofmedication = new JLabel("Start date of medication (dd/MM/yyyy): ");
                JTextField TextFieldfordateofmedication = new JTextField(10);
                inner_Panel.add(labelForDateofmedication);
                inner_Panel.add(TextFieldfordateofmedication);

                JLabel labelFordosage = new JLabel("Value for dosage: ");
                JTextField TextfieldFordosage = new JTextField(10);
                inner_Panel.add(labelFordosage);
                inner_Panel.add(TextfieldFordosage);

                JLabel labelFormedicationnotes = new JLabel("Notes: ");
                JTextField TextfieldFormedicationnotes = new JTextField(10);
                inner_Panel.add(labelFormedicationnotes);
                inner_Panel.add(TextfieldFormedicationnotes);

                JButton addtreatmentButton = new JButton("Add");

                addtreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagNumber = Integer.parseInt(TextfieldForTagNumber.getText());

                        Date dateofTreatment = null;
                        try {
                            dateofTreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateoftreatment.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        boolean emergency = Boolean.parseBoolean(TextFieldforemergency.getText());

                        int vetid = Integer.parseInt(TextfieldForvetID.getText());


                        String medicationdetails = TextfieldFormedicationdetails.getText();

                        int duration = Integer.parseInt(TextfieldFormedicationduration.getText());

                        Date dateofmedication = null;
                        try {
                            dateofmedication = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofmedication.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        double dosagevalue = Double.parseDouble(TextfieldFordosage.getText());

                        String notes = TextfieldFormedicationnotes.getText();


                        FarmApp.addCowHealthTreatment(tagNumber, dateofTreatment, emergency, vetid, medicationdetails, duration, dateofmedication,dosagevalue,notes);


                    }

                });

                inner_Panel.add(addtreatmentButton);
                addCowTreatmentsHealthDialog.add(inner_Panel);
                addCowTreatmentsHealthDialog.setPreferredSize(new Dimension(600, 200));
                addCowTreatmentsHealthDialog.pack();
                addCowTreatmentsHealthDialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addCowTreatmentsHealthDialog.setVisible(true);

            }

        });


        /**
         * Upon pressing submenuitemaddSheepTreatmentsCleaningTab menu item, user adds details of cleaning treatments of sheep after which cleaning treatment is added to the application for that sheep
         *
         */
        submenuitemaddSheepTreatmentsCleaningTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addSheepTreatmentsCleaningDialog = new JDialog(FrameForMainFarmAppWindow, "Add cleaning treatment for Sheep", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForTagNumber = new JLabel("Tag no: ");
                JTextField TextfieldForTagNumber = new JTextField(10);
                inner_Panel.add(labelForTagNumber);
                inner_Panel.add(TextfieldForTagNumber);


                JLabel labelForDateoftreatment = new JLabel("Date of treatment (dd/MM/yyyy): ");
                JTextField TextFieldfordateoftreatment = new JTextField(10);
                inner_Panel.add(labelForDateoftreatment);
                inner_Panel.add(TextFieldfordateoftreatment);

                JLabel Labelformaterialsused = new JLabel("Materials used: ");
                JTextField TextFieldformaterialsused = new JTextField(10);
                inner_Panel.add(Labelformaterialsused);
                inner_Panel.add(TextFieldformaterialsused);

                JLabel labelForfarmworkerID = new JLabel("Farm worker ID: ");
                JTextField TextfieldForFarmworkerID = new JTextField(10);
                inner_Panel.add(labelForfarmworkerID);
                inner_Panel.add(TextfieldForFarmworkerID);


                JButton addtreatmentButton = new JButton("Add");

                addtreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagNumber = Integer.parseInt(TextfieldForTagNumber.getText());

                        Date dateofTreatment = null;
                        try {
                            dateofTreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateoftreatment.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        String material = TextFieldformaterialsused.getText();


                        int farmworkerid = Integer.parseInt(TextfieldForFarmworkerID.getText());


                        FarmApp.addSheepCleaningTreatment(tagNumber, dateofTreatment,material,farmworkerid);

                    }

                });

                inner_Panel.add(addtreatmentButton);
                addSheepTreatmentsCleaningDialog.add(inner_Panel);
                addSheepTreatmentsCleaningDialog.pack();
                addSheepTreatmentsCleaningDialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addSheepTreatmentsCleaningDialog.setVisible(true);

            }

        });


        /**
         * Upon pressing submenuitemaddSheepTreatmentsHealthTab menu item, user adds details of health treatments of sheep after which health treatment is added to the application for that sheep
         *
         */
        submenuitemaddSheepTreatmentsHealthTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog addSheepTreatmentsHealthDialog = new JDialog(FrameForMainFarmAppWindow, "Add health treatment for sheep", true);

                JPanel inner_Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel labelForTagNumber = new JLabel("Tag no: ");
                JTextField TextfieldForTagNumber = new JTextField(10);
                inner_Panel.add(labelForTagNumber);
                inner_Panel.add(TextfieldForTagNumber);


                JLabel labelForDateoftreatment = new JLabel("Date of treatment (dd/MM/yyyy): ");
                JTextField TextFieldfordateoftreatment = new JTextField(10);
                inner_Panel.add(labelForDateoftreatment);
                inner_Panel.add(TextFieldfordateoftreatment);

                JLabel Labelforemergency = new JLabel("Enter true if emergency, false if not: ");
                JTextField TextFieldforemergency = new JTextField(10);
                inner_Panel.add(Labelforemergency);
                inner_Panel.add(TextFieldforemergency);

                JLabel labelForvetID = new JLabel("Vet ID: ");
                JTextField TextfieldForvetID = new JTextField(10);
                inner_Panel.add(labelForvetID);
                inner_Panel.add(TextfieldForvetID);


                JLabel labelFormedicationdetails = new JLabel("Medication details: ");
                JTextField TextfieldFormedicationdetails = new JTextField(10);
                inner_Panel.add(labelFormedicationdetails);
                inner_Panel.add(TextfieldFormedicationdetails);

                JLabel labelFormedicationduration = new JLabel("Value for duration: ");
                JTextField TextfieldFormedicationduration = new JTextField(10);
                inner_Panel.add(labelFormedicationduration);
                inner_Panel.add(TextfieldFormedicationduration);

                JLabel labelForDateofmedication = new JLabel("Start date of medication (dd/MM/yyyy): ");
                JTextField TextFieldfordateofmedication = new JTextField(10);
                inner_Panel.add(labelForDateofmedication);
                inner_Panel.add(TextFieldfordateofmedication);

                JLabel labelFordosage = new JLabel("Value for dosage: ");
                JTextField TextfieldFordosage = new JTextField(10);
                inner_Panel.add(labelFordosage);
                inner_Panel.add(TextfieldFordosage);

                JLabel labelFormedicationnotes = new JLabel("Notes: ");
                JTextField TextfieldFormedicationnotes = new JTextField(10);
                inner_Panel.add(labelFormedicationnotes);
                inner_Panel.add(TextfieldFormedicationnotes);

                JButton addtreatmentButton = new JButton("Add");

                addtreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int tagNumber = Integer.parseInt(TextfieldForTagNumber.getText());

                        Date dateofTreatment = null;
                        try {
                            dateofTreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateoftreatment.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        boolean emergency = Boolean.parseBoolean(TextFieldforemergency.getText());

                        int vetid = Integer.parseInt(TextfieldForvetID.getText());


                        String medicationdetails = TextfieldFormedicationdetails.getText();

                        int duration = Integer.parseInt(TextfieldFormedicationduration.getText());

                        Date dateofmedication = null;
                        try {
                            dateofmedication = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordateofmedication.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        double dosagevalue = Double.parseDouble(TextfieldFordosage.getText());

                        String notes = TextfieldFormedicationnotes.getText();


                        FarmApp.addSheepHealthTreatment(tagNumber, dateofTreatment, emergency, vetid, medicationdetails, duration, dateofmedication,dosagevalue,notes);

                    }

                });

                inner_Panel.add(addtreatmentButton);
                addSheepTreatmentsHealthDialog.add(inner_Panel);
                addSheepTreatmentsHealthDialog.setPreferredSize(new Dimension(600, 200));
                addSheepTreatmentsHealthDialog.pack();
                addSheepTreatmentsHealthDialog.setLocationRelativeTo(FrameForMainFarmAppWindow);
                addSheepTreatmentsHealthDialog.setVisible(true);

            }

        });


        /**
         * Upon pressing getAnimalTreatmentTab menu item, user enter details of cow/sheep after which they can get cleaning and health treatments of that cow/sheep
         */
        getAnimalTreatmentTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforgetanimaltreatment = new JDialog(FrameForMainFarmAppWindow, "Details of animal treatment", false);
                dialogforgetanimaltreatment.setPreferredSize(new Dimension(800,800));


                JPanel innerpanel = new JPanel(new FlowLayout());
                innerpanel.setPreferredSize(new Dimension(500,500));


                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setPreferredSize(new Dimension(300,300));
                innerpanel.add(TextAreafordata);


                JLabel Labelfortagnumber = new JLabel("Tag number: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);


                String [] cowORsheep = {"Cow","Sheep"};
                JComboBox<String> combination = new JComboBox<>(cowORsheep);
                innerpanel.add(combination);

                JButton getanimaltreatmentButton = new JButton("Get");

                getanimaltreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Cow")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            String details = FarmApp.getCowTreatment(tagnumber);

                            if (details == " " ) {
                                details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (details != null ) {
                                TextAreafordata.setText(details);
                            }
                        }
                        if(choice.equals("Sheep")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            String details = FarmApp.getSheepTreatment(tagnumber);

                            if (details == " " ) {
                                details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (details != null ) {
                                TextAreafordata.setText(details);
                            }
                        }

                    }
                });


                innerpanel.add(getanimaltreatmentButton);

                JScrollPane scroller = new JScrollPane(TextAreafordata);
                scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                innerpanel.add(scroller);

                dialogforgetanimaltreatment.add(innerpanel);
                dialogforgetanimaltreatment.setPreferredSize(new Dimension(600, 200));
                dialogforgetanimaltreatment.pack();
                dialogforgetanimaltreatment.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforgetanimaltreatment.setVisible(true);


            }
        });


        /**
         * Upon pressing getAnimalTreatmentTab menu item, user enter details of cow/sheep after which they can get cleaning and health treatments of that cow/sheep on the given date
         */
        getAnimalTreatmentOnGivenDateTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforgetanimaltreatmentOnGivenDate = new JDialog(FrameForMainFarmAppWindow, "Details of animal treatment on given date", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);


                JLabel Labelfortagnumber = new JLabel("Tag number: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);

                JLabel Labelfordate = new JLabel("Date (dd/MM/yyyy): ");
                JTextField TextFieldfordate = new JTextField(10);
                innerpanel.add(Labelfordate);
                innerpanel.add(TextFieldfordate);


                String [] cowORsheep = {"Cow","Sheep"};
                JComboBox<String> combination = new JComboBox<>(cowORsheep);
                innerpanel.add(combination);

                JButton getanimaltreatmentButton = new JButton("Get");

                getanimaltreatmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String choice = (String) combination.getSelectedItem();

                        if(choice.equals("Cow")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            Date dateoftreatment = null;
                            try {
                                dateoftreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordate.getText());
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }

                            String details = FarmApp.getCowTreatment(tagnumber,dateoftreatment);

                            if (details == " " ) {
                                details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (details != null ) {
                                TextAreafordata.setText(details);
                            }
                        }
                        if(choice.equals("Sheep")) {

                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            Date dateoftreatment = null;
                            try {
                                dateoftreatment = new SimpleDateFormat("dd/MM/yyyy").parse(TextFieldfordate.getText());
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }

                            String details = FarmApp.getSheepTreatment(tagnumber,dateoftreatment);

                            if (details == " " ) {
                                details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (details != null ) {
                                TextAreafordata.setText(details);
                            }
                        }
                    }
                });

                innerpanel.add(getanimaltreatmentButton);
                //innerpanel.add(combination);
                //JScrollPane scroller = new JScrollPane();
                //TextAreafordata.add(scroller);
                dialogforgetanimaltreatmentOnGivenDate.add(innerpanel);
                dialogforgetanimaltreatmentOnGivenDate.setPreferredSize(new Dimension(600, 200));
                dialogforgetanimaltreatmentOnGivenDate.pack();
                dialogforgetanimaltreatmentOnGivenDate.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforgetanimaltreatmentOnGivenDate.setVisible(true);


            }
        });


        /**
         * Upon pressing listCowsTab menu item, user gets to see full details of all cows in application
         */
        listCowsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JDialog dialogforlistingallcows = new JDialog(FrameForMainFarmAppWindow, "List all cows", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                String[] headers = {"Tag number", "Gender","Date of birth", "Purchased?", "Weight"};

                ArrayList<Animal> AnimalArraylist = FarmApp.listCows();

                int counter=0;

                for(int j=0; j<AnimalArraylist.size(); j++) {
                    if(AnimalArraylist.get(j) instanceof Cow) {
                        counter++;
                    }
                }
                //System.out.println("Value of counter is " + counter);
                Object [][] information = new Object[counter][5];

                int q=0;
                for(int j=0; j<AnimalArraylist.size(); j++) {
                    if(AnimalArraylist.get(j) instanceof Cow) {

                        Cow tempCow = (Cow) AnimalArraylist.get(j);

                        information[q][0] = tempCow.getTagNo();
                        information[q][1] = tempCow.getGender();
                        information[q][2] = tempCow.getDateOfBirth();
                        information[q][3] = tempCow.isPurchased();
                        information[q][4] = tempCow.getWeight();

                        q++;
                    }
                }

                JTable ListAllCowsTable = new JTable(information, headers);

                JScrollPane scroller = new JScrollPane(ListAllCowsTable);

                innerpanel.add(scroller,BorderLayout.EAST);

                dialogforlistingallcows.add(innerpanel);
                dialogforlistingallcows.pack();
                dialogforlistingallcows.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforlistingallcows.setVisible(true);

            }
        });

        /**
         * Upon pressing listSheepTab menu item, user gets to see full details of all sheeps in application
         */
        listSheepTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JDialog dialogforlistingallsheeps = new JDialog(FrameForMainFarmAppWindow, "List all sheeps", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                String[] headers = {"Tag number", "Gender","Date of birth", "Purchased?"};

                ArrayList<Animal> AnimalArraylist = FarmApp.listSheeps();

                int counter=0;

                for(int j=0; j<AnimalArraylist.size(); j++) {
                    if(AnimalArraylist.get(j) instanceof Sheep) {
                        counter++;
                    }
                }
                //System.out.println("Value of counter " + counter);
                Object [][] information = new Object[counter][4];

                int m=0;
                for(int j=0; j<AnimalArraylist.size(); j++) {
                    if(AnimalArraylist.get(j) instanceof Sheep) {

                        Sheep tempSheep = (Sheep) AnimalArraylist.get(j);

                        information[m][0] = tempSheep.getTagNo();
                        information[m][1] = tempSheep.getGender();
                        information[m][2] = tempSheep.getDateOfBirth();
                        information[m][3] = tempSheep.isPurchased();
                        m++;
                    }
                }

                JTable ListAllSheepsTable = new JTable(information, headers);

                JScrollPane scroller = new JScrollPane(ListAllSheepsTable);

                innerpanel.add(scroller,BorderLayout.EAST);

                dialogforlistingallsheeps.add(innerpanel);
                dialogforlistingallsheeps.pack();
                dialogforlistingallsheeps.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforlistingallsheeps.setVisible(true);

            }
        });

        /**
         * Upon pressing listVetsTab menu item, user gets to see full details of all vets in application
         */
        listVetsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JDialog dialogforlistingallvets = new JDialog(FrameForMainFarmAppWindow, "List all vets", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                String[] headers = {"Employee ID", "Gender","Date of birth", "BSc degree", "Date of graduation", "Expertise level"};

                ArrayList<Employee> EmployeeArraylist = FarmApp.listVet();

                int counter=0;

                for(int j=0; j<EmployeeArraylist.size(); j++) {
                    if(EmployeeArraylist.get(j) instanceof Veterinary) {
                        counter++;
                    }
                }
                //System.out.println("Value of counter " + counter);
                Object [][] information = new Object[counter][6];

                int m=0;
                for(int j=0; j<EmployeeArraylist.size(); j++) {
                    if(EmployeeArraylist.get(j) instanceof Veterinary) {

                        Veterinary tempVet = (Veterinary) EmployeeArraylist.get(j);

                        information[m][0] = tempVet.getEmpID();
                        information[m][1] = tempVet.getGender();
                        information[m][2] = tempVet.getDateOfBirth();

                        if(tempVet.isBScDegree() == true){
                            information[m][3] = tempVet.isBScDegree();
                            information[m][4] = tempVet.getDateOfGraduation();
                            information[m][5] = tempVet.getExpertiseLevel();
                        }
                        else if(tempVet.isBScDegree() == false){
                            information[m][3] = tempVet.isBScDegree();
                            information[m][4] = "Not available";
                            information[m][5] = "Not available";
                        }

                        m++;
                    }
                }

                JTable ListAllVetsTable = new JTable(information, headers);


                JScrollPane scroller = new JScrollPane(ListAllVetsTable);

                innerpanel.add(scroller,BorderLayout.EAST);

                dialogforlistingallvets.add(innerpanel);
                dialogforlistingallvets.pack();
                dialogforlistingallvets.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforlistingallvets.setVisible(true);


            }
        });

        /**
         * Upon pressing listFarmworkerTab menu item, user gets to see full details of all farmworkers in application
         */
        listFarmworkerTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JDialog dialogforlistingallfarmworkers = new JDialog(FrameForMainFarmAppWindow, "List all farmworkers", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                String[] headers = {"Employee ID", "Gender","Date of birth", "Previous farmname", "Work experience"};

                ArrayList<Employee> EmployeeArraylist = FarmApp.listFarmworker();

                int counter=0;

                for(int j=0; j<EmployeeArraylist.size(); j++) {
                    if(EmployeeArraylist.get(j) instanceof FarmWorker) {
                        counter++;
                    }
                }
                //System.out.println("Value of counter " + counter);
                Object [][] information = new Object[counter][5];

                int m=0;
                for(int j=0; j<EmployeeArraylist.size(); j++) {
                    if(EmployeeArraylist.get(j) instanceof FarmWorker) {

                        FarmWorker tempFarmworker = (FarmWorker) EmployeeArraylist.get(j);

                        information[m][0] = tempFarmworker.getEmpID();
                        information[m][1] = tempFarmworker.getGender();
                        information[m][2] = tempFarmworker.getDateOfBirth();
                        information[m][3] = tempFarmworker.getPreviousFarmName();
                        information[m][4] = tempFarmworker.getWorkexperience();
                        m++;
                    }
                }

                JTable ListAllFarmworkersTable = new JTable(information, headers);

                JScrollPane scroller = new JScrollPane(ListAllFarmworkersTable);


                innerpanel.add(scroller,BorderLayout.EAST);

                dialogforlistingallfarmworkers.add(innerpanel);
                dialogforlistingallfarmworkers.pack();
                dialogforlistingallfarmworkers.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforlistingallfarmworkers.setVisible(true);


            }
        });

        /**
         * Upon pressing feedingAnimalTab menu item, user enters details of the animal and gets to see the details of the feed that animal has
         */
        feedingAnimalTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforfeedinganimal = new JDialog(FrameForMainFarmAppWindow, "Feeding animal", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);


                JLabel Labelfortagnumber = new JLabel("Tag number: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);


                JButton getfeedingButton = new JButton("Get");

                getfeedingButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                            int tagnumber = Integer.parseInt(TextFieldfortagnumber.getText());

                            String results = FarmApp.feedingAnimal(tagnumber);

                            if (results.equals(" ")) {
                                String details = "No data";
                                TextAreafordata.setText(details);
                            }

                            else if (!results.equals(" ")) {
                                TextAreafordata.setText(results);
                            }
                        }

                });

                innerpanel.add(getfeedingButton);

                dialogforfeedinganimal.add(innerpanel);
                dialogforfeedinganimal.setPreferredSize(new Dimension(600, 200));
                dialogforfeedinganimal.pack();
                dialogforfeedinganimal.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforfeedinganimal.setVisible(true);


            }
        });

        /**
         * Upon pressing getEmpSalaryTab menu item, user enters details of employee and gets to see the salary of that employee
         */
        getEmpSalaryTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforgetEmplsalary = new JDialog(FrameForMainFarmAppWindow, "Employee salary", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);

                JLabel Labelforemployeeid = new JLabel("Employee ID: ");
                JTextField TextFieldforemployeeid = new JTextField(10);
                innerpanel.add(Labelforemployeeid);
                innerpanel.add(TextFieldforemployeeid);

                JButton getempsalaryButton = new JButton("Get");

                getempsalaryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        int id = Integer.parseInt(TextFieldforemployeeid.getText());

                        double results = FarmApp.getEmpSalary(id);

                        if (results == 0) {
                            String details = "No data";
                            TextAreafordata.setText(details);
                        }

                        else if (results != 0) {
                            TextAreafordata.setText(String.valueOf(results));
                        }
                    }

                });

                innerpanel.add(getempsalaryButton);

                dialogforgetEmplsalary.add(innerpanel);
                dialogforgetEmplsalary.setPreferredSize(new Dimension(600, 200));
                dialogforgetEmplsalary.pack();
                dialogforgetEmplsalary.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforgetEmplsalary.setVisible(true);


            }
        });

        /**
         * Upon pressing addMilkingMeasurementsTab menu item, user can enter the milking details of the animal
         */
        addMilkingMeasurementsTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforaddingmilkingmeasurements = new JDialog(FrameForMainFarmAppWindow, "Add milking measurements", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);


                JLabel Labelfortagnumber = new JLabel("Tag number: ");
                JTextField TextFieldfortagnumber = new JTextField(10);
                innerpanel.add(Labelfortagnumber);
                innerpanel.add(TextFieldfortagnumber);

                JLabel Labelforamount = new JLabel("Amount: ");
                JTextField TextFieldforamount = new JTextField(10);
                innerpanel.add(Labelforamount);
                innerpanel.add(TextFieldforamount);

                JButton addmilkingmeasurementsButton = new JButton("Get");

                addmilkingmeasurementsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        int tagnum = Integer.parseInt(TextFieldfortagnumber.getText());
                        double amount = Double.parseDouble(TextFieldforamount.getText());

                        String results = FarmApp.addMilkingMeasurements(tagnum,amount);

                        if (results.equals(" ")) {
                            String details = "Could not add!";
                            TextAreafordata.setText(details);
                        }

                        else if (!results.equals(" ")) {
                            TextAreafordata.setText(results);
                        }
                    }

                });

                innerpanel.add(addmilkingmeasurementsButton);

                dialogforaddingmilkingmeasurements.add(innerpanel);
                dialogforaddingmilkingmeasurements.setPreferredSize(new Dimension(600, 200));
                dialogforaddingmilkingmeasurements.pack();
                dialogforaddingmilkingmeasurements.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforaddingmilkingmeasurements.setVisible(true);


            }
        });

        /**
         * Upon pressing salaryComparatorTab menu item, user enters details of 2 employees and gets to see which employee has higher salary
         */
        salaryComparatorTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialogforsalarycomparison = new JDialog(FrameForMainFarmAppWindow, "Salary comparison", true);

                JPanel innerpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                innerpanel.setSize(600,600);

                JTextArea TextAreafordata = new JTextArea();
                TextAreafordata.setSize(100,100);
                innerpanel.add(TextAreafordata);

                JLabel Labelforemployeeid1 = new JLabel("Employee ID 1: ");
                JTextField TextFieldforemployeeid1 = new JTextField(10);
                innerpanel.add(Labelforemployeeid1);
                innerpanel.add(TextFieldforemployeeid1);

                JLabel Labelforemployeeid2 = new JLabel("Employee ID 2: ");
                JTextField TextFieldforemployeeid2 = new JTextField(10);
                innerpanel.add(Labelforemployeeid2);
                innerpanel.add(TextFieldforemployeeid2);


                JButton salarycomparatorButton = new JButton("Get");

                salarycomparatorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        int id1 = Integer.parseInt(TextFieldforemployeeid1.getText());
                        int id2 = Integer.parseInt(TextFieldforemployeeid2.getText());

                        String results = FarmApp.salaryComparator(id1,id2);

                        TextAreafordata.setText(results);
                    }

                });

                innerpanel.add(salarycomparatorButton);

                dialogforsalarycomparison.add(innerpanel);
                dialogforsalarycomparison.setPreferredSize(new Dimension(600, 200));
                dialogforsalarycomparison.pack();
                dialogforsalarycomparison.setLocationRelativeTo(FrameForMainFarmAppWindow);
                dialogforsalarycomparison.setVisible(true);


            }
        });



        FrameForMainFarmAppWindow.setVisible(true);
    }
}


