/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zaxariaskatsarakis
 */
public class mail {
    private String username_doc;
    private String username_pat;
    private String subject;
    private String text;
    private int id;
    
    public void set_Username_doc(String doc){
        this.username_doc = doc;
        
    }
    
    public void set_Username_pat(String pat){
        this.username_pat = pat;
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
    
    public String get_Username_doC(){ return this.username_doc;}
    
    public String get_Username_pat(){ return this.username_pat;}
    
    public String get_Subject(){return this.subject;}
     
    public String get_text(){return this.text;}
    
}
