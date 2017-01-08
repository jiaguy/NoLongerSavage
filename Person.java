package site;
/*==================================================================

File Name:           Person                
Class:               ICS4U                                           
Authors:             Jiaying, Elaine, Nicholas                
Date Last Modified:  15/06/2016             
Description:         The Person superclass which stores the account  
                     ID, password, primary attributes, and secondary 
                     attributes                              
====================================================================
To Do:                                                              
-                                                                   
====================================================================
Change log:                                                         
-fields, constructors, accessors, and match person method (Jiaying) 
-added editAccount method                                                                                        
====================================================================*/
import java.util.*;
public class Person{
   Scanner sc = new Scanner(System.in);
   Scanner sc1 = new Scanner(System.in);
   public static int totPerson;//number of people on site
   protected int ID;                                                 //Encapsulation is used in this parent class where some of the fields are private and 
   private int numMatches;                                           //hidden from view from other classes while the protected fields are able to be viewed
   protected String password;                                        //in the children classes
   protected PrimaryAttr primAttr;                                   //Inheritance is also used whre the children classes inherit the fields and methods of the
   protected SecondaryAttr secAttr;                                  //Person class.
   protected double percentMatch;
   protected double [] potential; 
   protected Person match[];//stores the people that match
   public static String nextLine = "~";

   /*===================================================================
   Person()                                                         
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   empty constructor for Person object                           
   ====================================================================*/
   public Person(){
      ID = 0;
      password = "";
      primAttr = null;
      secAttr = null;
      percentMatch = 0;
      match = null;
      potential = null;
      numMatches = 0;
   }

   /*==================================================================
   Person(int id, String pass, PrimaryAttr prim, SecondaryAttr sec)  
   --------------------------------------------------------------------
   id            -the identification number                          
   pass          -the password for the account                         
   prim          -the primary attributes                               
   sec           -the secondary attributes                   
   --------------------------------------------------------------------
   constructor for Person object which takes in their id, password,    
   primary attributes, and secondary attributes                       
   ====================================================================*/
   public Person(int id, String pass, PrimaryAttr prim, SecondaryAttr sec){
      ID = id;
      password = pass;
      primAttr = prim;
      secAttr = sec;
      percentMatch = 0;
      numMatches = 0;
      potential=new double[totPerson];
      match = new Person[totPerson];//creates array with total number of people on site
   }
   
//accessors and mutators
   public void setID(int id) {
      ID = id;
   }
 /*public void setMatch(Person[] match) {
  this.match = match;
 }*/
   public void setPassword(String password) {
      this.password = password;
   }
   public void setPercentMatch(double percentMatch) {
      this.percentMatch = percentMatch;
   }
   public void setPrimAttr(PrimaryAttr primAttr) {
      this.primAttr = primAttr;
   }
   public void setSecAttr(SecondaryAttr secAttr) {
      this.secAttr = secAttr;
   }
   public static void setTotPerson(int totPerson) {
      Person.totPerson = totPerson;
   }
   public String getPass(){
      return password;
   }
   public Person getMatch(int num){
      return match[num];
   }
   public int getID(){
      return ID;
   }
   public SecondaryAttr getSecAttr(){
      return secAttr;
   }
   public PrimaryAttr getPrimAttr(){
      return primAttr;
   }
 //end accessors mutators
   
   /*==================================================================
   getPercentMatch()                                             
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   returns the percent of match of the person                      
   ====================================================================*/
   public double getPercentMatch(){
      return percentMatch;
   }

   /*==================================================================
   matchPerson(Person other)                             
   --------------------------------------------------------------------
   other         -the explicit person object     
   --------------------------------------------------------------------
   determines if a person is a match and returns a boolean          
   ===================================================================*/
   public boolean matchPerson(Person other){
      other.percentMatch = this.secAttr.matchAttr(other.secAttr);
      if (this.primAttr.matchAttr(other.primAttr) && other.percentMatch >= SecondaryAttr.SECPERCENT)
      {
         this.potential[numMatches]=other.getPercentMatch();
         other.potential[numMatches]=other.getPercentMatch();
         this.match[numMatches] = other; 
         numMatches++;
         other.match[other.numMatches] = this;
         other.numMatches++;
         return true; 
      }   // Updates the users matches
      return false; 
   }
   
   public void resetMatches(){
      numMatches = 0; 
      match = new Person[totPerson];
   }
   /*===================================================================
   toString()                                                       
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   returns the ID                                                 
   ====================================================================*/
   public String toString(){
      String phrase = "ID: "+ID +"\n"+primAttr;
      return phrase;
   }
   
   /*===================================================================
   formatToString()                                                       
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   returns the formatted primary and secondary attributes                                                  
   ====================================================================*/
   public String formatToString(){
      String info = password +nextLine+ primAttr.formatToString() + secAttr.formatToString();
      return info; 
   }
   
   /*===================================================================
   sortMatch()                                                       
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   sorts the array of matches from the highest to lowest percentage                                                  
   ====================================================================*/
   public void sortMatch(){                                                   //Sorting is used in this match array of Persons 
      boolean swapped = true; 
      Person storage; 
      for(int i = numMatches - 1 ; i >= 0 && swapped; i--)
      {
         swapped = false; 
         for(int a = 0; a < i; a++)
            if(match[a].getPercentMatch() > match[a+1].getPercentMatch()){//checks if percentage is higher than current percentage
               swapped = true; 
               storage = match[a+1];      //swaps array
               match[a+1] = match[a];
               match[a] = storage; 
            } // if end 
      } // for end 
   } // sortMatch method 
   
   /*===================================================================
   printMatch()                                                       
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   print the array of matches and attributes for a person                                              
   ====================================================================*/
   public void printMatch(){
      System.out.println(numMatches + " Capadable Matches Found\n");
      for(int i = 0; i < numMatches; i++)
         System.out.println(match[i] +"\n"+ match[i].primAttr+ "\n");
   }  // printMatch method 
   
   /*===================================================================
   printMoreMatchInfo(int ID)                                                       
   --------------------------------------------------------------------
   ID          -the ID of another person
   --------------------------------------------------------------------
   prints more info(secondary attributes) of a person in their match array                                           
   ====================================================================*/
   public boolean printMoreMatchInfo(int ID){
      boolean found = false;
      for(int i=0; i<numMatches && !found; i++){
         if(match[i].ID == ID){     //checks if ID exists
            found = true;
            System.out.println(match[i].secAttr +"\n");      //prints out secondary attributes
         }
      }
      if(!found){
         System.out.println("Please enter the ID again.");
      }
      return found;
   }
      
   
}