import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


/**
 * The FarmMe app is an application which allows users to manage their farm using an application. User can add and delete cows,sheeps,farmworkers and veterinaries in this application. They can store treatments and medications for cows or sheeps along with the veterinary
 * and farm worker details who provided the treatments. Full details of cow along with their treatments and medications can be seen and they can manage their entire farm using this application.
 *
 * @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class FarmApp {


    /**
     * The constant animals.
     */
    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    /**
     * The constant employee.
     */
    public static ArrayList<Employee> employee = new ArrayList<Employee>();


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ParseException         the parse exception
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {


        //PopulateData.PopulateInitialData(animals, employee);


        FarmApp App = null;
        DataStorage DB = new DataStorage();
        new FarmAppGUI(App, DB);






    }





    /**
     * Menu.
     */
    public static void menu(){
        System.out.println("1) Press 1 to add a cow. ");
        System.out.println("2) Press 2 to add a sheep. ");
        System.out.println("3) Press 3 to delete a cow. ");
        System.out.println("4) Press 4 to delete a sheep. ");
        System.out.println("5) Press 5 to get cow details. ");
        System.out.println("6) Press 6 to get sheep details. ");
        System.out.println("7) Press 7 to add a vet. ");
        System.out.println("8) Press 8 to add a farmworker. ");
        System.out.println("9) Press 9 to delete a vet. ");
        System.out.println("10) Press 10 to delete a farm worker. ");
        System.out.println("11) Press 11 to get vet details. ");
        System.out.println("12) Press 12 to get farm worker details. ");
        System.out.println("13) Press 13 to add cow treatment. ");
        System.out.println("14) Press 14 to add sheep treatment. ");
        System.out.println("15) Press 15 to get cow treatment. ");
        System.out.println("16) Press 16 to get sheep treatment. ");
        System.out.println("17) Press 17 to get cow treatment on a given date. ");
        System.out.println("18) Press 18 to get sheep treatment on a given date. ");
        System.out.println("19) Press 19 to list all cows. ");
        System.out.println("20) Press 20 to list all sheeps. ");
        System.out.println("21) Press 21 to list all vets. ");
        System.out.println("22) Press 22 to list all farmworkers. ");
        System.out.println("23) Press 23 for feeding animal");
        System.out.println("24) Press 24 to get employee salary.");
        System.out.println("25) Press 25 to add milking measurements.");
        System.out.println("26) Press 26 to find comparison of salaries.");
        System.out.println("27) Press 27 to exit. ");
    }


    /**
     * Add cow.
     *
     * @param tagno       the tagno
     * @param gender      the gender
     * @param dateofBirth the dateof birth
     * @param purchased   the purchased
     * @param weight      the weight
     */
    public static void addCow(int tagno, String gender,Date dateofBirth,boolean purchased, double weight) {

        boolean found = false;

        for(int i = 0; i <animals.size(); i++) {
            if (tagno == animals.get(i).getTagNo()) {
                found = true;
            }
        }

        if (found == false) {

            Cow TempCow = new Cow();
            TempCow.setTagNo(tagno);
            TempCow.setGender(gender);
            TempCow.setWeight(weight);
            TempCow.setDateOfBirth(dateofBirth);
            TempCow.setPurchased(purchased);


            animals.add(TempCow);

        }

        else
            System.out.println("Animal with tag number " + tagno + " already exists. Please select a different tag number.");

    }


    /**
     * Add sheep.
     *
     * @param tagno       the tagno
     * @param gender      the gender
     * @param dateofBirth the dateof birth
     * @param purchased   the purchased
     */
    public static void addSheep(int tagno, String gender,Date dateofBirth,boolean purchased) {

        boolean found = false;


        for(int i = 0; i <animals.size(); i++) {
            if (tagno == animals.get(i).getTagNo()) {
                found = true;
            }
        }

        if (found == false) {

            Sheep TempSheep = new Sheep();
            TempSheep.setTagNo(tagno);
            TempSheep.setGender(gender);
            TempSheep.setDateOfBirth(dateofBirth);
            TempSheep.setPurchased(purchased);

            animals.add(TempSheep);

        }

        else
            System.out.println("Animal with tag number " + tagno + " already exists. Please select a different tag number.");

    }


    /**
     * Animal adder animal.
     *
     * @param obj the obj
     * @param Tag the tag
     * @return the animal
     */
    public static Animal AnimalAdder(Animal obj, int Tag) {

        Scanner inputTaker = new Scanner(System.in);
        obj.setTagNo(Tag);

        System.out.println("Enter date of birth in dd/MM/yyyy format: ");
        Date birthday = DateGenerator();
        obj.setDateOfBirth(birthday);

        String gender = GenderGenerator();
        obj.setGender(gender);

        System.out.println("Enter 'true' if cow is purchased and 'false' if cow is farm raised: ");
        boolean tempbool = inputTaker.nextBoolean();
        obj.setPurchased(tempbool);

        HashMap<LocalDate,Double> milkHashMap = new HashMap<LocalDate,Double>();
        obj.setMilking(milkHashMap);

        return obj;
    }


    /**
     * Delete cow.
     *
     * @param tagNo the tag no
     */
    public static void deleteCow(int tagNo) {

        boolean found = false;
        int deleterPosition = 0;

        for( int i=0; i<animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Cow){
                found = true;
                deleterPosition = i;

            }
        }

        if (found == true) {
            animals.remove(deleterPosition);
            System.out.println("Cow with tag number " + tagNo + "has been deleted" + "\n");
        }
        else {
            System.out.println("Cow with tag number " + tagNo + " not available. Please enter a valid tag number." + "\n");
        }

    }


    /**
     * Delete sheep.
     *
     * @param tagNo the tag no
     */
    public static void deleteSheep(int tagNo) {

        boolean found = false;
        int deleterPosition = 0;

        for( int i=0; i<animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Sheep){
                found = true;
                deleterPosition = i;

            }
        }

        if (found == true) {
            animals.remove(deleterPosition);
            System.out.println("Sheep with tag number " + tagNo + "has been deleted" + "\n");
        }
        else {
            System.out.println("Sheep with tag number " + tagNo + " not available. Please enter a valid tag number." + "\n");
        }

    }


    /**
     * Gets cow details.
     *
     * @param tagNo the tag no
     * @return the cow details
     */
    public static Cow getCowDetails(int tagNo) {

        boolean found = false;
        System.out.println("Details of cow are: ");

        for (int i =0; i < animals.size(); i++) {

            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Cow) {

                found = true;

                Cow TempCow = (Cow) animals.get(i);
                return TempCow;
            }

        }

        if (found == false) {
            System.out.println("Cow with tag number " + tagNo + " not available. Please enter a valid tag number." + "\n");
        }

        return null;

    }


    /**
     * Gets sheep details.
     *
     * @param tagNo the tag no
     * @return the sheep details
     */
    public static Sheep getSheepDetails(int tagNo) {

        boolean found = false;
        System.out.println("Details of sheep are: ");

        for (int i =0; i < animals.size(); i++) {

            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Sheep) {

                found = true;
                Sheep TempSheep = (Sheep) animals.get(i);
                return TempSheep;
            }
        }

        if (found == false) {
            System.out.println("Sheep with tag number " + tagNo + " not available. Please enter a valid tag number." + "\n");
        }
        return null;
    }


    /**
     * Add vet.
     *
     * @param idnum            the idnum
     * @param gender           the gender
     * @param dateofBirth      the dateof birth
     * @param degree           the degree
     * @param dateofgraduation the dateofgraduation
     * @param explevel         the explevel
     */
    public static void addVet(int idnum, String gender, Date dateofBirth, boolean degree, Date dateofgraduation,int explevel)  {

        boolean found = false;


        for (int i =0; i< employee.size(); i++) {
            if( idnum == employee.get(i).getEmpID()) {
                found = true;
            }
        }

        if (found == false) {

            Veterinary tempVet = new Veterinary();

            tempVet.setBScDegree(degree);
            tempVet.setDateOfGraduation(dateofgraduation);
            tempVet.setExpertiseLevel(explevel);
            tempVet.setGender(gender);
            tempVet.setEmpID(idnum);
            tempVet.setDateOfBirth(dateofBirth);

            employee.add(tempVet);
        }

        else {
            System.out.println("Vet with ID " + idnum + " already exists. Please enter another ID.");
        }

    }


    /**
     * Add farmworker.
     *
     * @param idnum        the idnum
     * @param gender       the gender
     * @param dateofBirth  the dateof birth
     * @param previousfarm the previousfarm
     * @param experience   the experience
     */
    public static void addFarmworker(int idnum, String gender, Date dateofBirth, String previousfarm, int experience)  {

        boolean found = false;

        for (int i =0; i< employee.size(); i++) {
            if( idnum == employee.get(i).getEmpID()) {
                found = true;
            }
        }

        if (found == false) {

            FarmWorker tempfarmworker = new FarmWorker();

            tempfarmworker.setEmpID(idnum);
            tempfarmworker.setWorkexperience(experience);
            tempfarmworker.setPreviousFarmName(previousfarm);
            tempfarmworker.setGender(gender);
            tempfarmworker.setDateOfBirth(dateofBirth);

            employee.add(tempfarmworker);
        }

        else {
            System.out.println("FarmWorker with ID " + idnum + " already exists. Please enter another ID.");
        }

    }


    /**
     * Employee adder employee.
     *
     * @param obj the obj
     * @param Tag the tag
     * @return the employee
     */
    public static Employee EmployeeAdder(Employee obj, int Tag){

        Scanner inputTaker = new Scanner(System.in);

        obj.setEmpID(Tag);

        String gender = GenderGenerator();
        obj.setGender(gender);

        System.out.println("Enter date of birth in dd/MM/yyyy format: ");
        Date birthday = DateGenerator();
        obj.setDateOfBirth(birthday);

        return obj;

    }


    /**
     * Delete vet.
     *
     * @param vetID the vet id
     */
    public static void deleteVet(int vetID) {

        boolean found = false;
        int deleterPosition = 0;

        for( int i=0; i< employee.size(); i++) {
            if (vetID == employee.get(i).getEmpID() && employee.get(i) instanceof  Veterinary) {
                found = true;
                deleterPosition = i;
            }
        }

        if (found == true) {
            employee.remove(deleterPosition);
            System.out.println("Vet with ID " + vetID + "has been deleted" + "\n");
        }
        else {
            System.out.println("Vet with ID " + vetID + " not available. Please enter a valid ID." + "\n");
        }

    }


    /**
     * Delete farmworker.
     *
     * @param farmworkerID the farmworker id
     */
    public static void deleteFarmworker(int farmworkerID) {

        boolean found = false;
        int deleterPosition = 0;

        for( int i=0; i< employee.size(); i++) {
            if (farmworkerID == employee.get(i).getEmpID() && employee.get(i) instanceof  FarmWorker) {
                found = true;
                deleterPosition = i;
            }
        }

        if (found == true) {
            employee.remove(deleterPosition);
            System.out.println("FarmWorker with ID " + farmworkerID + "has been deleted" + "\n");
        }
        else {
            System.out.println("FarmWorker with ID " + farmworkerID + " not available. Please enter a valid ID." + "\n");
        }

    }


    /**
     * Gets vet details.
     *
     * @param vetID the vet id
     * @return the vet details
     */
    public static Veterinary getVetDetails(int vetID) {

        boolean found = false;

        for( int i=0; i<employee.size(); i++) {
            if (vetID == employee.get(i).getEmpID()) {
                found = true;

                if (employee.get(i) instanceof Veterinary) {

                    Veterinary TempVet = (Veterinary) employee.get(i);
                    return TempVet;
                }
            }
        }

        if (found == false) {
            System.out.println("Vet with ID " + vetID + " not available. Please enter a valid ID." + "\n");
        }
        return null;
    }


    /**
     * Gets farmworker details.
     *
     * @param farmworkerID the farmworker id
     * @return the farmworker details
     */
    public static FarmWorker getFarmworkerDetails(int farmworkerID) {

        boolean found = false;

        for( int i=0; i<employee.size(); i++) {
            if (farmworkerID == employee.get(i).getEmpID()) {
                found = true;

                if (employee.get(i) instanceof FarmWorker) {
                    FarmWorker Tempworker = (FarmWorker) employee.get(i);
                    return Tempworker;
                    }
                }

            }

        if (found == false) {
            System.out.println("farmworker with ID " + farmworkerID + " not available. Please enter a valid ID." + "\n");
        }
        return null;
    }


    /**
     * Add cow cleaning treatment.
     *
     * @param tagNumber       the tag number
     * @param dateofTreatment the dateof treatment
     * @param material        the material
     * @param farmworkerid    the farmworkerid
     */
    public static void addCowCleaningTreatment(int tagNumber,Date dateofTreatment, String material,int farmworkerid) {

        boolean CowCheck = false;
        boolean VetCheck = false;
        int CowIndexPosition=0;


        for (int i=0; i< animals.size(); i++) {
            if (tagNumber == animals.get(i).getTagNo()){
                CowCheck = true;
                CowIndexPosition = i;
            }
        }

        for (int i=0; i< employee.size(); i++) {
            if (farmworkerid == employee.get(i).getEmpID()){
                VetCheck = true;

            }
        }

        if (CowCheck == true && VetCheck == true) {
                FarmWorker tempfarmworker = new FarmWorker();

            for (int i=0; i< employee.size(); i++) {
                if (farmworkerid == employee.get(i).getEmpID()){
                    tempfarmworker = (FarmWorker) employee.get(i);

                }
            }

            CleaningTreatment tempCleaningTreatment = new CleaningTreatment(dateofTreatment,material, (FarmWorker) tempfarmworker);

            animals.get(CowIndexPosition).setTreatments(tempCleaningTreatment);

        }

        else if (CowCheck == false && VetCheck == true) {
            System.out.println("No such cow found");
        }

        else if (CowCheck == true && VetCheck == false) {
            System.out.println("No such vet found");
        }

        else if (CowCheck == false && VetCheck == false) {
            System.out.println("No such vet or cow found");
        }
    }

    /**
     * Add cow health treatment.
     *
     * @param tagNumber         the tag number
     * @param dateofTreatment   the dateof treatment
     * @param emergency         the emergency
     * @param vetid             the vetid
     * @param medicationdetails the medicationdetails
     * @param duration          the duration
     * @param dateofmedication  the dateofmedication
     * @param dosagevalue       the dosagevalue
     * @param notes             the notes
     */
    public static void addCowHealthTreatment(int tagNumber,Date dateofTreatment, boolean emergency, int vetid, String medicationdetails,int  duration, Date dateofmedication,double dosagevalue,String notes) {

        boolean CowCheck = false;
        boolean VetCheck = false;
        int CowIndexPosition=0;


        for (int i=0; i< animals.size(); i++) {
            if (tagNumber == animals.get(i).getTagNo()){
                CowCheck = true;
                CowIndexPosition = i;
            }
        }

        for (int i=0; i< employee.size(); i++) {
            if (vetid == employee.get(i).getEmpID()){
                VetCheck = true;

            }
        }

        if (CowCheck == true && VetCheck == true) {



            Veterinary tempvet = new Veterinary();

            for (int i=0; i< employee.size(); i++) {
                if (vetid == employee.get(i).getEmpID()){
                    tempvet = (Veterinary) employee.get(i);

                }
            }

            Medication tempMedication = new Medication(medicationdetails, duration, dateofmedication, dosagevalue, notes);

            HealthTreatment tempHealthTreatment = new HealthTreatment(dateofTreatment,emergency);
            tempHealthTreatment.setMedications(tempMedication);
            tempHealthTreatment.setVet(tempvet);

            animals.get(CowIndexPosition).setTreatments(tempHealthTreatment);

        }

        else if (CowCheck == false && VetCheck == true) {
            System.out.println("No such cow found");
        }

        else if (CowCheck == true && VetCheck == false) {
            System.out.println("No such vet found");
        }

        else if (CowCheck == false && VetCheck == false) {
            System.out.println("No such vet or cow found");
        }
    }


    /**
     * Add sheep cleaning treatment.
     *
     * @param tagNumber       the tag number
     * @param dateofTreatment the dateof treatment
     * @param material        the material
     * @param farmworkerid    the farmworkerid
     */
    public static void addSheepCleaningTreatment(int tagNumber,Date dateofTreatment, String material,int farmworkerid) {

        boolean SheepCheck = false;
        boolean VetCheck = false;
        int SheepIndexPosition=0;


        for (int i=0; i< animals.size(); i++) {
            if (tagNumber == animals.get(i).getTagNo()){
                SheepCheck = true;
                SheepIndexPosition = i;
            }
        }

        for (int i=0; i< employee.size(); i++) {
            if (farmworkerid == employee.get(i).getEmpID()){
                VetCheck = true;

            }
        }

        if (SheepCheck == true && VetCheck == true) {


            FarmWorker tempfarmworker = new FarmWorker();

            for (int i=0; i< employee.size(); i++) {
                if (farmworkerid == employee.get(i).getEmpID()){
                    tempfarmworker = (FarmWorker) employee.get(i);

                }
            }



            CleaningTreatment tempCleaningTreatment = new CleaningTreatment(dateofTreatment,material, (FarmWorker) tempfarmworker);

            animals.get(SheepIndexPosition).setTreatments(tempCleaningTreatment);

        }

        else if (SheepCheck == false && VetCheck == true) {
            System.out.println("No such sheep found");
        }

        else if (SheepCheck == true && VetCheck == false) {
            System.out.println("No such vet found");
        }

        else if (SheepCheck == false && VetCheck == false) {
            System.out.println("No such vet or sheep found");
        }
    }

    /**
     * Add sheep health treatment.
     *
     * @param tagNumber         the tag number
     * @param dateofTreatment   the dateof treatment
     * @param emergency         the emergency
     * @param vetid             the vetid
     * @param medicationdetails the medicationdetails
     * @param duration          the duration
     * @param dateofmedication  the dateofmedication
     * @param dosagevalue       the dosagevalue
     * @param notes             the notes
     */
    public static void addSheepHealthTreatment(int tagNumber,Date dateofTreatment, boolean emergency, int vetid, String medicationdetails,int  duration, Date dateofmedication,double dosagevalue,String notes) {

        boolean SheepCheck = false;
        boolean VetCheck = false;
        int SheepIndexPosition=0;


        for (int i=0; i< animals.size(); i++) {
            if (tagNumber == animals.get(i).getTagNo()){
                SheepCheck = true;
                SheepIndexPosition = i;
            }
        }

        for (int i=0; i< employee.size(); i++) {
            if (vetid == employee.get(i).getEmpID()){
                VetCheck = true;

            }
        }

        if (SheepCheck == true && VetCheck == true) {


            Veterinary tempvet = new Veterinary();

            for (int i=0; i< employee.size(); i++) {
                if (vetid == employee.get(i).getEmpID()){
                    tempvet = (Veterinary) employee.get(i);

                }
            }

            Medication tempMedication = new Medication(medicationdetails, duration, dateofmedication, dosagevalue, notes);

            HealthTreatment tempHealthTreatment = new HealthTreatment(dateofTreatment,emergency);

            tempHealthTreatment.setMedications(tempMedication);
            tempHealthTreatment.setVet(tempvet);


            animals.get(SheepIndexPosition).setTreatments(tempHealthTreatment);

        }

        else if (SheepCheck == false && VetCheck == true) {
            System.out.println("No such sheep found");
        }

        else if (SheepCheck == true && VetCheck == false) {
            System.out.println("No such vet found");
        }

        else if (SheepCheck == false && VetCheck == false) {
            System.out.println("No such vet or sheep found");
        }
    }


    /**
     * Get cow treatment string.
     *
     * @param tagNo the tag no
     * @return the string
     */
    public static String getCowTreatment(int tagNo){

        String details = " ";


        for (int i =0; i < animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Cow) {

                for (int j=0; j < animals.get(i).getTreatments().size(); j++ ) {
                    if (animals.get(i).getTreatments().get(j) instanceof HealthTreatment) {

                        details += "Health treatment details: " + "\n";

                        details += "Date of treatment of cow: " + animals.get(i).getTreatments().get(j).getDateOfTreatment() + "\n";

                        if(((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == true){
                            details += "There is emergency" + "\n";
                        }
                        else if(((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == false) {
                            details +="There is no emergency"+ "\n";
                        }

                        for(int k=0; k<((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().size(); k++) {
                            details +="Medication details of cow:  " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDetails() + "\n";
                            details +="Duration of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDuration() + "\n";
                            details +="Start date of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getStartDate() + "\n";
                            details +="Dosage of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDosage()+ "\n";
                            details +="Notes of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getNotes() + "\n";
                        }


                    }
                    else if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {
                        details +="Cleaning treatment details: "+ "\n";
                        details +="Date of treatment of cow: " + animals.get(i).getTreatments().get(j).getDateOfTreatment()+ "\n";
                        details +="Materials used: " + ((CleaningTreatment) animals.get(i).getTreatments().get(j)).getMaterialsused()+ "\n";

                    }
                }
            }
        }

        return details;
    }


    /**
     * Get sheep treatment string.
     *
     * @param tagNo the tag no
     * @return the string
     */
    public static String getSheepTreatment(int tagNo){

        String details = " ";

        for (int i =0; i < animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo() && animals.get(i) instanceof Sheep) {


                for (int j=0; j < animals.get(i).getTreatments().size(); j++ ) {
                    if (animals.get(i).getTreatments().get(j) instanceof HealthTreatment) {
                        details += "Health treatment details: " + "\n";
                        details +="Date of treatment of sheep: " + animals.get(i).getTreatments().get(j).getDateOfTreatment() + "\n";
                        if(((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == true){
                            details += "There is emergency" + "\n";
                        }
                        else if(((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == false) {
                            details += "There is no emergency" + "\n";
                        }

                        for(int k=0; k<((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().size(); k++) {
                            details += "Medication details of sheep:  " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDetails() + "\n";
                            details += "Duration of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDuration() + "\n";
                            details += "Start date of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getStartDate() + "\n";
                            details += "Dosage of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDosage() + "\n";
                            details += "Notes of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getNotes() + "\n";
                        }

                    }
                    else if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {
                        details += "Cleaning treatment details: " + "\n";
                        details += "Date of treatment of sheep: " + animals.get(i).getTreatments().get(j).getDateOfTreatment()+ "\n";
                        details += "Materials used: " + ((CleaningTreatment) animals.get(i).getTreatments().get(j)).getMaterialsused()+ "\n";

                    }

                }
            }
        }

        return details;
    }


    /**
     * Gets cow treatment.
     *
     * @param tagNo           the tag no
     * @param dateOfTreatment the date of treatment
     * @return the cow treatment
     */
    public static String getCowTreatment(int tagNo, Date dateOfTreatment) {

        String details = " ";

        for (int i = 0; i < animals.size(); i++) {
            if ((tagNo == animals.get(i).getTagNo()) && animals.get(i) instanceof Cow) {


                for (int j = 0; j < animals.get(i).getTreatments().size(); j++) {
                    if (dateOfTreatment.equals(animals.get(i).getTreatments().get(j).getDateOfTreatment())) {


                        if (animals.get(i).getTreatments().get(j) instanceof HealthTreatment) {

                            details += "Health treatment details: " + "\n";
                            details += "Date of treatment of cow: " + animals.get(i).getTreatments().get(j).getDateOfTreatment() + "\n";

                            if (((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == true) {
                                details += "There is emergency" + "\n";
                            } else if (((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == false) {
                                details += "There is no emergency" + "\n";
                            }

                            for (int k = 0; k < ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().size(); k++) {
                                details += "Medication details of cow:  " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDetails()+ "\n";
                                details += "Duration of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDuration()+ "\n";
                                details += "Start date of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getStartDate()+ "\n";
                                details += "Dosage of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDosage()+ "\n";
                                details += "Notes of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getNotes() + "\n";
                            }



                        } else if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {
                            details += "Cleaning treatment details: "+ "\n";
                            details += "Date of treatment of cow: " + animals.get(i).getTreatments().get(j).getDateOfTreatment()+ "\n";
                            details += "Materials used: " + ((CleaningTreatment) animals.get(i).getTreatments().get(j)).getMaterialsused()+ "\n";

                        }
                    }
                }
            }
        }

        return details;

    }


    /**
     * Get sheep treatment string.
     *
     * @param tagNo           the tag no
     * @param dateOfTreatment the date of treatment
     * @return the string
     */
    public static String getSheepTreatment(int tagNo, Date dateOfTreatment){

        String details = " ";

        for (int i =0; i < animals.size(); i++) {
            if ( (tagNo == animals.get(i).getTagNo()) && animals.get(i) instanceof Sheep ){


                for (int j=0; j < animals.get(i).getTreatments().size(); j++ ) {

                    if(dateOfTreatment.equals(animals.get(i).getTreatments().get(j).getDateOfTreatment())) {


                        if (animals.get(i).getTreatments().get(j) instanceof HealthTreatment) {

                            details += "Health treatment details: " + "\n";
                            details += "Date of treatment of sheep: " + animals.get(i).getTreatments().get(j).getDateOfTreatment() + "\n";

                            if (((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == true) {
                                details += "There is emergency"+ "\n";
                            } else if (((HealthTreatment) animals.get(i).getTreatments().get(j)).isEmergency() == false) {
                                details += "There is no emergency"+ "\n";
                            }

                            for (int k = 0; k < ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().size(); k++) {
                                details += "Medication details of sheep:  " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDetails() + "\n";
                                details += "Duration of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDuration() + "\n";
                                details += "Start date of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getStartDate() + "\n";
                                details += "Dosage of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getDosage() + "\n";
                                details += "Notes of medication: " + ((HealthTreatment) animals.get(i).getTreatments().get(j)).getMedications().get(k).getNotes() + "\n";
                            }


                        }

                        else if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {

                            details += "Cleaning treatment details: " + "\n";
                            details += "Date of treatment of sheep: " + animals.get(i).getTreatments().get(j).getDateOfTreatment() + "\n";
                            details += "Materials used: " + ((CleaningTreatment) animals.get(i).getTreatments().get(j)).getMaterialsused() + "\n";

                        }

                    }
                }
            }
        }

        return details;

    }


    /**
     * List cows array list.
     *
     * @return the array list
     */
    public static ArrayList<Animal> listCows() {

        return animals;
    }


    /**
     * List sheeps array list.
     *
     * @return the array list
     */
    public static ArrayList<Animal> listSheeps() {

        return animals;
    }

    /**
     * List vet.
     *
     * @return array list
     */
    public static ArrayList<Employee> listVet() {

        return employee;
    }


    /**
     * List farmworker array list.
     *
     * @return the array list
     */
    public static ArrayList<Employee> listFarmworker() {


        return employee;
    }


    /**
     * Salary comparator string.
     *
     * @param tagNo1 the tag no 1
     * @param tagNo2 the tag no 2
     * @return the string
     */
    public static String salaryComparator(int tagNo1, int tagNo2) {

        boolean found1=false;
        boolean found2=false;
        int result =2;

        String results = " ";

        for(int i=0; i<employee.size(); i++) {
            if(employee.get(i).getEmpID() == tagNo1){
                found1 =true;

                for (Employee value : employee) {
                    if (value.getEmpID() == tagNo2) {
                        found2 = true;

                        if (found2) {
                            result = employee.get(i).compareTo(value);

                            if (result == 1) {
                                results += "Salary of " + tagNo1 + " is higher." + "\n";
                            } else if (result == -1) {
                                results += "Salary of " + tagNo2 + " is higher." + "\n";
                            }
                            else if (result == 0) {
                                results += "Salary of is same" + "\n";
                            }
                            break;
                        }
                        break;
                    }

                }

            }

        }

        if (!found1 && !found2) {
            results += "Employee with " + tagNo1 + " not found. " + "\n";
            results += "Employee with " + tagNo2 + " not found. " + "\n";
        }
        else if(found1 && !found2) {
            results += "Employee with " + tagNo2 + " not found. " + "\n";
        }
        else if(!found1 && found2) {
            results += "Employee with " + tagNo1 + " not found. " + "\n";
        }
        return results;
    }


    /**
     * Add milking measurements string.
     *
     * @param tagNo  the tag no
     * @param amount the amount
     * @return the string
     */
    public static String addMilkingMeasurements(int tagNo, double amount) {

        LocalDate currentdate = LocalDate.now();
        String results = " ";

        for(int i=0; i<animals.size(); i++) {
            if ( (animals.get(i).getTagNo() == tagNo)) {
                animals.get(i).getMilking().put(currentdate, amount);
                results = "Added!";
            }
        }
        return results;
    }


    /**
     * Get emp salary double.
     *
     * @param empId the emp id
     * @return the double
     */
    public static double getEmpSalary(int empId){

        boolean found =false;
        double salary=0;

        for(int i=0; i<employee.size(); i++){
            if (empId == employee.get(i).getEmpID()){
                found = true;
                salary = employee.get(i).getSalary();

            }
        }

        if(found == false) {
            System.out.println("Employee with given ID does not exist.");
        }
        return salary;
    }


    /**
     * Feeding animal string.
     *
     * @param tagNo the tag no
     * @return the string
     */
    public static String feedingAnimal(int tagNo){
        boolean found = false;
        String result = " ";
        for(int i=0;i<animals.size();i++) {
            if (animals.get(i).getTagNo()==tagNo) {
                found = true;
                result = animals.get(i).feeding();
            }
        }

        if (found == false){
            System.out.println("Animal not found.");
        }
        return result;
    }


    /**
     * Exit.
     *
     * @throws IOException the io exception
     */
    public static void exit() throws IOException {
        System.out.println("Leaving application. Bye!");

        System.exit(0);

    }


    /**
     * Date generator date.
     *
     * @return the date
     */
    public static Date DateGenerator()  {

        boolean correct =true;
        Date finalDate=null;

        Scanner inputTaker = new Scanner(System.in);
        do {
            correct = true;
            try {

                String tempdate = inputTaker.next();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                finalDate = format.parse(tempdate);

            } catch (DateTimeParseException | ParseException E) {
                System.out.println("Please enter correct date");
                correct=false;
                inputTaker.nextLine();
            }
        }while (!correct);

        return finalDate;
    }


    /**
     * Tag provider int.
     *
     * @return the int
     */
    public static int TagProvider () {

        Scanner temp = new Scanner(System.in);
        int tagNo = 0;
        boolean correct = true;
        do {
            correct = true;
            if(temp.hasNextInt()) {
                tagNo = temp.nextInt();
            }
            else {
                System.out.println("Wrong input, try again.");
                temp.next();
                correct = false;
            }
        }
        while(!correct);
        return tagNo;

    }


    /**
     * Gender generator string.
     *
     * @return the string
     */
    public static String GenderGenerator(){

        Scanner inputTaker = new Scanner(System.in);
        String gender;
        do {

            System.out.println("Enter gender: ");
            gender = inputTaker.next();

        } while(!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")));

        return gender;
    }


}
