
import java.sql.Connection;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Mike
 */
public class Doctor extends User{
    int doctor_id;
    String specialty, doctor_info;

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_info() {
        return doctor_info;
    }

    public void setDoctor_info(String doctor_info) {
        this.doctor_info = doctor_info;
    }

    
    
    int certified;

    public int getCertified() {
        return certified;
    }

    public void setCertified(int certified) {
        this.certified = certified;
    }

   

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

  public  void reg_Doctor( ){
      String query = "insert into doctors values ("
              + "'" + this.getUname() + "',"
              + "'" + this.getEmail() + "',"
              + "'" + this.getPassword()+ "',"
              + "'" + this.getFirstname()+ "',"
              +"'" + this.getLastname() + "',"
              +"'" + this.getBirthdate() + "',"
              +"'" + this.getAmka()+ "',"
              +"'" + this.getCountry() + "',"
              +"'" + this.getCity() + "',"
              +"'" + this.getAddress() + "',"
              +"'" + this.getTelephone() + "',"
              +"'" + this.getHeight() + "',"
              +"'" + this.getWeight() + "',"
              +"'" + this.getBlooddonor() + "',"
              +"'" + this.getBloodtype()+ "'"              
              +"'" + this.getCertified()+ "',"
              +");";
      try{
          Connection con = DB_Connection.getConnection();
          Statement stmt = con.createStatement();
          stmt.execute(query);
         
      }catch(Exception e){
          System.out.println(e.toString());
      }
  }
}
