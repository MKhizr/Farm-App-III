import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;


/**
 * This class is responsible to populate the app with data initially
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class PopulateData {

    /**
     * Populate initial data.
     *
     * @param animals  the animals
     * @param employee the employee
     * @throws ParseException the parse exception
     */
    public static void  PopulateInitialData(ArrayList<Animal> animals, ArrayList<Employee> employee) throws ParseException {


        Date dateofbirth,dateofgraduation,dateoftreatment,dateofmedication,birthoffarmer,dateofcleaningtreatment,cowbirthdate,sheepbirthdate;
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateofmedication = DateFormat.parse("15/10/2019");
        dateofbirth = DateFormat.parse("03/09/1987");
        dateofgraduation = DateFormat.parse("04/11/2001");
        dateoftreatment = DateFormat.parse("07/10/2019");
        birthoffarmer = DateFormat.parse("07/10/1992");
        dateofcleaningtreatment = DateFormat.parse("17/09/2020");
        cowbirthdate = DateFormat.parse("19/09/2015");

        Medication medication1 = new Medication("Montiplus - AntiFungal", 3, dateofmedication, 10, "5 weeks");
        //Medication medication12 = new Medication("Pelarome - Antibiotic", 3, dateofmedication, 5, "3 weeks");

        ArrayList<Medication> medicationlist = new ArrayList<Medication>();
        medicationlist.add(medication1);

        Veterinary vet1 = new Veterinary(1,"Male",dateofbirth,true,dateofgraduation,1);

        HealthTreatment healthTreatment1 = new HealthTreatment(dateoftreatment,true,medicationlist,vet1);

        FarmWorker farmWorker1 = new FarmWorker(2,"male",birthoffarmer,"kalkanli",1);

        CleaningTreatment cleaningTreatment1 = new CleaningTreatment(dateofcleaningtreatment,"tonic",farmWorker1);

        ArrayList<Treatment> treatmentlist = new ArrayList<Treatment>();

        treatmentlist.add(cleaningTreatment1);
        treatmentlist.add(healthTreatment1);

        Cow cow1 = new Cow(1,"male",cowbirthdate,true,treatmentlist,60.5);

        animals.add(cow1);
        employee.add(vet1);
        employee.add(farmWorker1);






        dateofmedication = DateFormat.parse("01/02/2005");
        dateofbirth = DateFormat.parse("04/03/2006");
        dateofgraduation = DateFormat.parse("04/11/2007");
        dateoftreatment = DateFormat.parse("08/10/2008");
        birthoffarmer = DateFormat.parse("07/10/2009");
        dateofcleaningtreatment = DateFormat.parse("17/09/2020");
        cowbirthdate = DateFormat.parse("19/08/2010");

        Medication medication2 = new Medication("Pelarome - Antibiotic", 3, dateofmedication, 5, "3 weeks");

        ArrayList<Medication> medicationlist2 = new ArrayList<Medication>();
        medicationlist2.add(medication2);

        Veterinary vet2 = new Veterinary(3,"Male",dateofbirth,false,dateofgraduation,2);

        HealthTreatment healthTreatment2 = new HealthTreatment(dateoftreatment,false,medicationlist2,vet2);

        FarmWorker farmWorker2 = new FarmWorker(4,"male",birthoffarmer,"Guzelyurt",2);

        CleaningTreatment cleaningTreatment2 = new CleaningTreatment(dateofcleaningtreatment,"phenyl",farmWorker2);

        ArrayList<Treatment> treatmentlist2 = new ArrayList<Treatment>();
        treatmentlist2.add(cleaningTreatment2);
        treatmentlist2.add(healthTreatment2);

        Cow cow2 = new Cow(2,"female",cowbirthdate,false,treatmentlist2,90.5);
        animals.add(cow2);
        employee.add(vet2);
        employee.add(farmWorker2);




        dateofmedication = DateFormat.parse("03/09/2010");
       // dateofbirth = DateFormat.parse("24/05/2006");
       // dateofgraduation = DateFormat.parse("14/12/2017");
        dateoftreatment = DateFormat.parse("19/12/2018");
       // birthoffarmer = DateFormat.parse("17/11/2004");
        dateofcleaningtreatment = DateFormat.parse("14/10/2022");
        sheepbirthdate = DateFormat.parse("19/09/2002");

        Medication medication3 = new Medication("TelaPharm - Antiobiotic", 1, dateofmedication, 9, "9 weeks");

        ArrayList<Medication> medicationlist3 = new ArrayList<Medication>();
        medicationlist3.add(medication3);

        //Veterinary vet3 = new Veterinary(5,"Male",dateofbirth,false,dateofgraduation,3);

        HealthTreatment healthTreatment3 = new HealthTreatment(dateoftreatment,true,medicationlist3,vet1);

        //FarmWorker farmWorker3 = new FarmWorker(6,"female",birthoffarmer,"magusa",3);

        CleaningTreatment cleaningTreatment3 = new CleaningTreatment(dateofcleaningtreatment,"detol",farmWorker1);


        ArrayList<Treatment> treatmentlist3 = new ArrayList<Treatment>();
        treatmentlist3.add(cleaningTreatment3);
        treatmentlist3.add(healthTreatment3);

        Sheep sheep1 = new Sheep(3,"male",sheepbirthdate,true,treatmentlist3);
        animals.add(sheep1);
        employee.add(vet1);
        employee.add(farmWorker1);



        dateofmedication = DateFormat.parse("03/09/2010");
       // dateofbirth = DateFormat.parse("24/05/2006");
       // dateofgraduation = DateFormat.parse("14/12/2017");
        dateoftreatment = DateFormat.parse("19/12/2018");
       // birthoffarmer = DateFormat.parse("17/11/2004");
        dateofcleaningtreatment = DateFormat.parse("14/10/2022");
        sheepbirthdate = DateFormat.parse("19/09/2002");

        Medication medication4 = new Medication("pregnancy - Antiobiotic",3 , dateofmedication, 10, "10 weeks");

        ArrayList<Medication> medicationlist4 = new ArrayList<Medication>();
        medicationlist4.add(medication4);

        //Veterinary vet4 = new Veterinary(7,"Male",dateofbirth,true,dateofgraduation,4);

        HealthTreatment healthTreatment4 = new HealthTreatment(dateoftreatment,true,medicationlist4,vet2);

        //FarmWorker farmWorker4 = new FarmWorker(8,"female",birthoffarmer,"magusa",3);

        CleaningTreatment cleaningTreatment4 = new CleaningTreatment(dateofcleaningtreatment,"detol",farmWorker2);

        ArrayList<Treatment> treatmentlist4 = new ArrayList<Treatment>();
        treatmentlist4.add(cleaningTreatment4);
        treatmentlist4.add(healthTreatment4);

        Sheep sheep2 = new Sheep(4,"male",sheepbirthdate,true,treatmentlist4);
        animals.add(sheep2);
        employee.add(vet2);
        employee.add(farmWorker2);


    }





}
