package src.main.models;

import java.util.Arrays;
import java.util.Objects;

public class Team {
    private String house;
    private String seeker;
    private String keeper;
    private String[] chasers;
    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

/* FREQUENTLY ASKED QUESTIONS:
    
Question: the constants are final, so why can't we make them public? It's not possible for the caller to update them.
  Answer: Even if the constant is final, I prefer to expose a method instead of the variable. But if you want to expose the variable, that's also correct.

*/
    public Team(String house,String keeper,String seeker,String[] chasers){
        if(house==null || keeper==null || seeker==null){
            throw new IllegalArgumentException("Fields cannot be null");
        }
        if(house.isBlank() || keeper.isBlank() || seeker.isEmpty()){
            throw new IllegalArgumentException("Fields cannot be blank");
        }
        if(chasers.length != 3){
            throw new IllegalArgumentException("Must have three chasers");
        }
        if(hasNull(chasers) || hasBlank(chasers)){
            throw new IllegalArgumentException("chasers cannot be blank/null");
        }
        this.house = house;
        this.seeker = seeker;
        this.keeper = keeper;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }
    
    public Team(Team source){
        this.house = source.house;
        this.seeker = source.seeker;
        this.keeper = source.keeper;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }

    public String[] getChasers() {
        return Arrays.copyOf(this.chasers, this.chasers.length);
    }

    public String getHouse() {
        return house;
    }

    public String getKeeper() {
        return keeper;
    }

    public String getSeeker() {
        return seeker;
    }

    public void setChasers(String[] chasers) {
        if(chasers.length!=3 ||  hasNull(chasers) || hasBlank(chasers)){
            throw new IllegalArgumentException("Illegal chasers argument");
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public void setHouse(String house) {
        checkParameter(house);
        this.house = house;
    }

    public void setKeeper(String keeper) {
        checkParameter(keeper);
        this.keeper = keeper;
    }

    public void setSeeker(String seeker) {
        checkParameter(seeker);
        this.seeker = seeker;
    }

    public static String getPositionChaser() {
        return POSITION_CHASER;
    }

    public static String getPositionSeeker() {
        return POSITION_SEEKER;
    }

    public static String getPositionKeeper() {
        return POSITION_KEEPER;
    }

    public static boolean hasNull(String[] array){
        return Arrays.stream(array).anyMatch(element -> element==null);
    }

    public static boolean hasBlank(String[] array){
         return Arrays.stream(array).anyMatch(ele -> ele.isBlank());
    }

    public void checkParameter(String param){
        if(param==null || param.isBlank()){
            throw new IllegalArgumentException(param+" cannot be null/blank");
        }
    }

    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(!(obj instanceof Team))
            return false;
        Team team = (Team)obj;
        return this.seeker.equals(team.seeker) &&
               this.keeper.equals(team.keeper) &&
               this.house.equals(team.house) &&
               Arrays.toString(this.chasers).equals(Arrays.toString(team.chasers));
    }

    public int hashCode(){
        return Objects.hash(house,seeker,keeper,Arrays.toString(this.chasers));
    }

    public String toString(){
        return  "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +         
                "Seeker: "  + this.seeker + "\n" +         
                "Chasers: " + Arrays.toString(this.chasers) + "\n"; 
    }

}
