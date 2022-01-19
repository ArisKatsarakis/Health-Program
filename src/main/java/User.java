import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    String username, email, password, firstname, lastname, birthdate;
    String gender;
    String amka;
    String country, city, address;
    Double lat, lon;
    String telephone;
    int height;
    double weight;
    int blooddonor;
    String bloodtype;

    public void createSimpleUsert() throws SQLException, ClassCastException {
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();

            String query = "CREATE TABLE USERS \n"
                    + "(username VARCHAR(30) not null unique,"
                    + "email VARCHAR(40) not null unique,"
                    + "password VARCHAR(32) not null,"
                    + "first_name VARCHAR(35) not null ,"
                    + "last_name VARCHAR(40) not null ,"
                    + "birthday VARCHAR (20) not null ,"
                    + "Sex VARCHAR(10) not null ,"
                    + "amka VARCHAR(20) not null,"
                    + "country varchar(30) not null,"
                    + "city varchar(30) not null,"
                    + "Address varchar(40) not null,"
                    + "telephone varchar(15) not null,"
                    + "height int not null,"
                    + "weight int not null,"
                    + "blood_doner varchar(3) not null,"
                    + "blood_t varchar(4) not null);";

            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());

        }
    }

    public void registerUser() throws SQLException, ClassNotFoundException{
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO  USERS "+"\n" +
                    "VALUES" +
                    "("+" '" +this.getUname()+"'," +
                    "'"+this.getEmail()+"',"+
                    "'"+this.getPassword()+"',"+
                    "'"+this.getFirstname()+"',"+
                    "'"+this.getLastname()+"',"+
                    "'"+this.getBirthdate()+"',"+
                    "'"+this.getGender()+"',"+
                    "'"+this.getAmka()+"',"+
                    "'"+this.getCountry()+"',"+
                    "'"+this.getCity()+"',"+
                    "'"+this.getAddress()+"',"+
                    "'"+this.getTelephone()+"',"+
                    "'"+this.getHeight()+"',"+
                    "'"+this.getWeight()+"',"+
                    "'"+"yes"+"',"+
                    "'"+this.getBloodtype()+"'"+
                    ");";
            System.out.println(query);
            stmt.execute(query);
            stmt.close();

        }catch (Exception e){
            System.out.println("DB_side" + e.toString());
        }
    }

    public static User getUser(String username){
            User get = new User();
            try{
                Connection con = DB_Connection.getConnection();
                Statement stmt = con.createStatement();
                String query = "SELECT * FROM USERS WHERE username = '"+username+"'";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    get.setUsername(username);
                    get.setPassword(rs.getString("password"));
                    get.setEmail(rs.getString("email"));
                    get.setFirstname(rs.getString("first_name"));
                    get.setLastname(rs.getString("last_name"));
                    get.setBirthdate(rs.getString("birthday"));
                    get.setGender(rs.getString("Sex"));
                    get.setAmka(rs.getString("amka"));
                    get.setCountry(rs.getString("country"));
                    get.setCity(rs.getString("city"));
                    get.setAddress(rs.getString("Address"));
                    get.setTelephone(rs.getString("telephone"));
                    get.setHeight(rs.getInt("height"));
                    get.setHeight(rs.getInt("weight"));

                }

            }catch(Exception e){
                System.out.println(e.toString());
            }

            return get;
    }
    public String getUname(){
        return this.username;

    }
    public String getEmail(){
        return this.email;

    }

    public String getPassword(){
        return this.password;

    }

    public String getFirstname(){
        return this.firstname;

    }

    public String getLastname(){
        return this.lastname;

    }

    public String getBirthdate(){
        return this.birthdate;

    }

    public String getGender(){
        return this.gender;

    }


    public String getCountry(){
        return this.country;

    }


    public String getCity(){
        return this.city;

    }


    public String getAddress(){
        return this.address;

    }

    public String getTelephone(){
        return this.telephone;

    }


    public String getBloodtype(){
        return this.bloodtype;

    }


    public String getAmka(){
        return this.amka;

    }
    public int getHeight(){
        return this.height;

    }

    public double getWeight(){
        return this.weight;

    }

    public int getBlooddonor(){
        return this.blooddonor;
    }

    public double getlat(){
        return this.lat;

    }

    public double getlon()
    {
        return this.lon;
    }
    public void setUsername(String uname){
        this.username =  uname;

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setBlooddonor(int blooddonor) {
        this.blooddonor = blooddonor;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
        public static void main(String[] args) {
        User a = new User();
        try {
            a.createSimpleUsert();
            a.setUsername("testing");
            a.setEmail("Emailllll");
            a.registerUser();
        } catch (Exception e) {
        }
    }

}
