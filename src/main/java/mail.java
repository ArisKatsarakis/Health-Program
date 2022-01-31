/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zaxariaskatsarakis
 */
public class mail {
    private String from;
    private String to;
    private String subject;
    private String text;
    private int id;
    
    public void set_From(String doc){
        this.from = doc;
        
    }
    
    public void set_To(String pat){
        this.to = pat;
    }
    
    public void set_Subject(String sub){
        this.subject = sub;
    }
    
    public void set_Text(String text){
        this.text = text;
    }
    
    public void set_id(int id ){
        this.id = id;
    }
    
    public int get_id(){
        return this.id;
    }
    
    public String get_from(){ return this.from;}
    
    public String get_to(){ return this.to;}
    
    public String get_Subject(){return this.subject;}
     
    public String get_text(){return this.text;}
    
}
