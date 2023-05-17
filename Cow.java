import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * This class is responsible to provide a cow and has all related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class Cow extends Animal {

    private double weight;


    /**
     * Instantiates a new Cow.
     */
    public Cow() {
    }

    /**
     * Instantiates a new Cow.
     *
     * @param weight the weight
     */
    public Cow (double weight){
        this.weight = weight;
    }

    /**
     * Instantiates a new Cow.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param milking     the milking
     * @param treatments  the treatments
     * @param weight      the weight
     */
    public Cow(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap milking, ArrayList<Treatment> treatments, double weight) {
        super(tagNo, gender, dateOfBirth, purchased, milking, treatments);
        this.weight = weight;
    }

    /**
     * Instantiates a new Cow.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param treatments  the treatments
     * @param weight      the weight
     */
    public Cow(int tagNo, String gender, Date dateOfBirth, boolean purchased, ArrayList<Treatment> treatments, double weight) {
        super(tagNo, gender, dateOfBirth, purchased, treatments);
        this.weight = weight;
    }


    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String feeding() {

        String result = " ";

        if (getAge() < 3){
            result += "Only fed with grass." + "\n";
        }
        else if (getAge() > 5 && getWeight() < 500) {
            result += "Needs total mixed ration (TMR) which is a diet that includes hay, fermented grass(silage)," +
                    " maize silage and high energy grains like brewer grains, soy bean, cotton seed and citrus pulp." + "\n";
        }
        else if (getAge() > 10){
            result += "Needs grains and oilseed meals" + "\n";
        }
        else {
            result += "Cow needs to be fed with grass and grains" + "\n";
        }
        return result;
    }


}
