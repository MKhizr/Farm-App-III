
import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;


/**
 * The DataStorage handles all the data storage functions for the application such as database connection setup, writing data to database, reading data from database and performing security chek on thread
 *
 * @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class DataStorage implements Runnable  {

    private Connection conn;


    /**
     * Instantiates a new Data storage.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public DataStorage() throws SQLException, IOException {

        try
        {
            String userName = "cng443user";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/farmappdb";
            Class.forName ("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e){
            System.err.println ("Cannot connect to database server");
        }


    }


    /**
     * Read data.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public void readData() throws SQLException, IOException {



        Statement Animal_statement = conn.createStatement();

        ResultSet result_set_obj_for_Animal = Animal_statement.executeQuery("select * from Animal");

        while (result_set_obj_for_Animal.next()) {


            String value = result_set_obj_for_Animal.getString(5);

            if ( value.equalsIgnoreCase("c")  ) {
                Cow tempCow = new Cow();

                tempCow.setTagNo(result_set_obj_for_Animal.getInt(1));
                tempCow.setGender(result_set_obj_for_Animal.getString(2));
                tempCow.setDateOfBirth(result_set_obj_for_Animal.getDate(3));
                tempCow.setPurchased(Boolean.parseBoolean(String.valueOf(result_set_obj_for_Animal.getInt(4))));
                tempCow.setWeight(Double.parseDouble(String.valueOf(result_set_obj_for_Animal.getInt(6))));
                FarmApp.animals.add(tempCow);

            }

            else if ( value.equalsIgnoreCase("s")  ) {
                Sheep tempSheep = new Sheep();

                tempSheep.setTagNo(result_set_obj_for_Animal.getInt(1));
                tempSheep.setGender(result_set_obj_for_Animal.getString(2));
                tempSheep.setDateOfBirth(result_set_obj_for_Animal.getDate(3));
                tempSheep.setPurchased(Boolean.parseBoolean(String.valueOf(result_set_obj_for_Animal.getInt(4))));

                FarmApp.animals.add(tempSheep);

            }

        }

        result_set_obj_for_Animal.close();
        Animal_statement.close();



        Statement Employee_statement = conn.createStatement();
        ResultSet result_Set_obj_for_Employee = Employee_statement.executeQuery("select * from Employee");

        while (result_Set_obj_for_Employee.next()) {


            String value = result_Set_obj_for_Employee.getString(4);

            if ( value.equalsIgnoreCase("v")  ) {
                Veterinary tempVet = new Veterinary();

                tempVet.setEmpID(result_Set_obj_for_Employee.getInt(1));
                tempVet.setGender(result_Set_obj_for_Employee.getString(2));
                tempVet.setDateOfBirth(result_Set_obj_for_Employee.getDate(3));
                tempVet.setBScDegree(Boolean.parseBoolean(String.valueOf(result_Set_obj_for_Employee.getInt(5))));
                tempVet.setDateOfGraduation(result_Set_obj_for_Employee.getDate(6));
                tempVet.setExpertiseLevel(result_Set_obj_for_Employee.getInt(7));
                FarmApp.employee.add(tempVet);

            }

            else if ( value.equalsIgnoreCase("f")  ) {
                FarmWorker tempFarmworker = new FarmWorker();

                tempFarmworker.setEmpID(result_Set_obj_for_Employee.getInt(1));
                tempFarmworker.setGender(result_Set_obj_for_Employee.getString(2));
                tempFarmworker.setDateOfBirth(result_Set_obj_for_Employee.getDate(3));
                tempFarmworker.setPreviousFarmName(result_Set_obj_for_Employee.getString(8));
                tempFarmworker.setWorkexperience(result_Set_obj_for_Employee.getInt(9));

                FarmApp.employee.add(tempFarmworker);

            }

        }

        result_Set_obj_for_Employee.close();
        Employee_statement.close();



    }

    /**
     * Write data.
     *
     * @param DuplicateAnimalArrayList  the duplicate animal array list
     * @param DuplicatEmployeeArrayList the duplicat employee array list
     * @throws SQLException the sql exception
     */
    public void writeData(ArrayList DuplicateAnimalArrayList,ArrayList DuplicatEmployeeArrayList) throws SQLException {



        if (DuplicateAnimalArrayList.equals(FarmApp.animals) == false) {


            String Query_to_delete_animal = "DELETE FROM animal";
            PreparedStatement query_executor_animal = conn.prepareStatement(Query_to_delete_animal);
            query_executor_animal.executeUpdate();


            String Query_to_insert_animal = "INSERT INTO Animal (tagNo, gender, dateOfBirth, purchased, type, Weight) VALUES (?,?,?,?,?,?)";

            PreparedStatement PS_for_inserting_Animals = conn.prepareStatement(Query_to_insert_animal);

            int purchasedvar = 100;
            char typeanimal = 'g';

            for (int k = 0; k < FarmApp.animals.size(); k++) {

                PS_for_inserting_Animals.setInt(1, FarmApp.animals.get(k).getTagNo());
                PS_for_inserting_Animals.setString(2, FarmApp.animals.get(k).getGender());

                java.sql.Date DateOfBirthForMYSQL = new java.sql.Date(FarmApp.animals.get(k).getDateOfBirth().getTime());
                PS_for_inserting_Animals.setDate(3, DateOfBirthForMYSQL);


                if (FarmApp.animals.get(k).isPurchased() == true) {
                    purchasedvar = 1;
                } else if (FarmApp.animals.get(k).isPurchased() == false) {
                    purchasedvar = 0;
                }

                PS_for_inserting_Animals.setInt(4, purchasedvar);

                if (FarmApp.animals.get(k) instanceof Cow) {
                    typeanimal = 'c';
                } else if (FarmApp.animals.get(k) instanceof Sheep) {
                    typeanimal = 's';
                }

                PS_for_inserting_Animals.setString(5, String.valueOf(typeanimal));

                if (FarmApp.animals.get(k) instanceof Cow) {
                    PS_for_inserting_Animals.setInt(6, (int) ((Cow) FarmApp.animals.get(k)).getWeight());
                } else {
                    PS_for_inserting_Animals.setNull(6, Types.NULL);
                }

                PS_for_inserting_Animals.executeUpdate();

            }
        }





        if(DuplicatEmployeeArrayList.equals(FarmApp.employee) == false) {

            String Query_to_delete_employee = "DELETE FROM employee";
            PreparedStatement query_executor_employee = conn.prepareStatement(Query_to_delete_employee);
            query_executor_employee.executeUpdate();


            String Query_to_insert_employee = "INSERT INTO Employee (empID, gender, dateOfBirth, type, BScDegree, dateOfGraduation, expertiseLevel, previousFarmName, workExperience) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement PS_for_inserting_Employee = conn.prepareStatement(Query_to_insert_employee);

            int degreevar = 100;
            char typeemployee = 'g';

            for (int k = 0; k < FarmApp.employee.size(); k++) {

                PS_for_inserting_Employee.setInt(1, FarmApp.employee.get(k).getEmpID());
                PS_for_inserting_Employee.setString(2, FarmApp.employee.get(k).getGender());

                java.sql.Date DateOfBirthForMYSQL2 = new java.sql.Date(FarmApp.employee.get(k).getDateOfBirth().getTime());
                PS_for_inserting_Employee.setDate(3, DateOfBirthForMYSQL2);


                if (FarmApp.employee.get(k) instanceof Veterinary) {
                    typeemployee = 'v';
                    PS_for_inserting_Employee.setString(4, String.valueOf(typeemployee));


                    if (((Veterinary) FarmApp.employee.get(k)).isBScDegree() == true) {
                        degreevar = 1;
                    } else if (((Veterinary) FarmApp.employee.get(k)).isBScDegree() == false) {
                        degreevar = 0;
                    }

                    PS_for_inserting_Employee.setInt(5, degreevar);

                    if (degreevar == 1) {

                        java.sql.Date DateOfBirthForMYSQL3 = new java.sql.Date(FarmApp.employee.get(k).getDateOfBirth().getTime());
                        PS_for_inserting_Employee.setDate(6, DateOfBirthForMYSQL3);

                        PS_for_inserting_Employee.setInt(7, ((Veterinary) FarmApp.employee.get(k)).getExpertiseLevel());

                    } else if (degreevar == 0) {
                        PS_for_inserting_Employee.setNull(6, Types.NULL);
                        PS_for_inserting_Employee.setNull(7, Types.NULL);
                    }

                    PS_for_inserting_Employee.setNull(8, Types.NULL);
                    PS_for_inserting_Employee.setNull(9, Types.NULL);


                } else if (FarmApp.employee.get(k) instanceof FarmWorker) {
                    typeemployee = 'f';
                    PS_for_inserting_Employee.setString(4, String.valueOf(typeemployee));

                    PS_for_inserting_Employee.setNull(5, Types.NULL);
                    PS_for_inserting_Employee.setNull(6, Types.NULL);
                    PS_for_inserting_Employee.setNull(7, Types.NULL);

                    PS_for_inserting_Employee.setString(8, ((FarmWorker) FarmApp.employee.get(k)).getPreviousFarmName());
                    PS_for_inserting_Employee.setInt(9, ((FarmWorker) FarmApp.employee.get(k)).getWorkexperience());

                }


                PS_for_inserting_Employee.executeUpdate();

            }
        }

    }

    /**
     * Executes the security check operations for the application.
     *
     */
    @Override
    public void run() {
        try {

            Animal TempAnimal = null;
            FileInputStream file_input_stream_for_animal = null;
            ObjectInputStream object_input_stream_for_animal = null;

            File serialized_animal_file = new File("AnimalDataAssignment4.bin");


            try {
                file_input_stream_for_animal = new FileInputStream(serialized_animal_file);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                object_input_stream_for_animal = new ObjectInputStream(file_input_stream_for_animal);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            ArrayList<Animal> tempAnimal = new ArrayList<Animal>();

            try {
                tempAnimal = (ArrayList<Animal>) object_input_stream_for_animal.readObject();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                object_input_stream_for_animal.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                file_input_stream_for_animal.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            //tempAnimal.remove(0);



            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream obj_output_stream_animal = new ObjectOutputStream(baos);
            obj_output_stream_animal.writeObject(tempAnimal);

            byte[] buffer = baos.toByteArray();
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buffer);
            byte[] digest = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & (int) digest[i]));
                hexString.append(" ");
            }



            //reads content of md5 from file which was saved when app was closed
            File File_for_MD5 = new File("MD5Data.bin");
            BufferedReader MD5_content = new BufferedReader(new FileReader(File_for_MD5));
            String Final_MD5_string = MD5_content.readLine();
            MD5_content.close();

            //comparison of new and old md5
            if (hexString.toString().equals(Final_MD5_string)) {
                System.out.println("Object contents are NOT modified.");
            } else {
                System.out.println("Warning!!! Object contents are modified.");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("No such file.");
        } catch (Exception ex) {
            System.out.println("Opps:" + ex.getMessage());
        }
    }

}
