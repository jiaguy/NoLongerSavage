package site;
/*===================================================================
File Name:          PrimaryAttr.java                                
Class:              ICS4U1-01                                       
Authors:            Nicholas Wang                                   
Date Last Modified: 14/06/2016                                      
Description:        The PrimaryAttr class stores the basic info: age
                    gender, first name, last name, etc. It also     
                    contains the method used to match the           
                    PrimaryAttr with another user                   
====================================================================*/
public class PrimaryAttr 
{
   // Variables
   private String firstName, lastName;
   private char gender, sexuality; 
   private int age; 
   private static char LESBIAN = 'l', GAY = 'g', STRAIGHT = 's', BISEXUAL = 'b';
   private static char MALE = 'm', FEMALE = 'f';
   private static int AGE_RANGE = 10;      // Constant for the age range that someone could date
   private static final String nextLine = "~";  // Constant for next line when saving to file
   
   // Constructors 
   public PrimaryAttr(){
     firstName = "";
     lastName = "";
     gender = '0';
     sexuality = '0';
     age = 0; 
   }
   
   /*======================================================================================
   public PrimaryAttr(String first, String last, char gender, char sexuality, int age)    
   ---------------------------------------------------------------------------------------
   first              - variable containing the first name of the user                    
   last               - variable containing the last name  of the user                    
   gender             - variable containing the char representing the user's gender       
   sexuality          - variable containing the char representing the user's sexuality    
   age                - variable containing the age of the user                           
   ---------------------------------------------------------------------------------------
   constructor for PrimaryAttr object which takes in first name, last name, gender,       
   sexuality and age of the user                                                          
   =======================================================================================*/
   public PrimaryAttr(String first, String last, int age, char gender, char sexuality){
     firstName = first;
     lastName = last; 
     this.gender = gender; 
     this.sexuality = sexuality;
     this.age = age;
   }  
   
   //Accessors
   public int getAge(){
      return age;
   }
   public String getFName(){
      return firstName;
   }
   
   public String getLName(){
      return lastName;
   }
   //Mutators
   public void setFName(String first){
      firstName = first;
   }
   
   public void setLName(String last){
      lastName = last;
   }
   
   public void setGender(char gender){
      this.gender = gender;
   }
   
   public void setSexuality(char sexuality){
      this.sexuality = sexuality;
   }
   
   public void setAge(int age){
      this.age = age;
   }
   
   // Methods
   /*===================================================================
   public boolean matchAttr (PrimaryAttr other)                                         
   --------------------------------------------------------------------
   other         -the PrimaryAttr of the explicit Person object        
   --------------------------------------------------------------------
   determines if a person is a match and returns a boolean             
   ====================================================================*/
   public boolean matchAttr (PrimaryAttr other)                            
   {
     boolean match = false;  
     // Checks if the users are within the set age range 
     if (Math.abs(age - other.age) <= AGE_RANGE){ 
       // Checks if the users have the same sexuality 
       if (sexuality == other.sexuality){
         // Checks if the user is a homosexual 
         if(sexuality == LESBIAN || sexuality == GAY){
           // Checks if the users' genders are identical
           if (gender == other.gender)
              match = true;
         } 
         // Checks if the user is a heterosexual
         else if (sexuality == STRAIGHT){
           // Checks if the users have opposite genders
           if ((gender == MALE && other.gender == FEMALE) || (gender == FEMALE && other.gender == MALE))
              match = true;
         }
         // Checks if the user is a bisexual 
         else if (sexuality == BISEXUAL)
            match = true;
       } // if sexuality check end 
     } // if age check end 
      return match; 
   }  //  matchAttr method
   
   /*================================================================================
   toString()                                                                                        
   ---------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------
   returns all the non static variables in the class in a formated String variable                                                   
   =================================================================================*/
   public String toString() 
   {
      String info = "" + firstName + ", " + lastName + "\n" + age + " Years Old\n";
      if (gender == FEMALE)
         info = info + "Female\n";
      else
         info = info + "Male\n";
      if (sexuality == LESBIAN)
         info = info + "Lesbian\n";
      else if (sexuality == GAY)
         info = info + "Gay";
      else if (sexuality == STRAIGHT) 
         info = info + "Straight";
      else 
         info = info + "Bisexual";
      return info; 
   }  // toString method
   
   /*================================================================================
   formatToString()                                                                                       
   ---------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------
   returns all the non static variables in the class in a String variable formatted 
   manner - for for saving to a file                                                 
   =================================================================================*/
   public String formatToString()
   {
      String info = "" + firstName + nextLine + lastName + nextLine + age + nextLine;
      if (gender == FEMALE)
         info = info + "f" + nextLine;
      else
         info = info + "m" + nextLine;
      if (sexuality == LESBIAN)
         info = info + "l" + nextLine;
      else if (sexuality == GAY)
         info = info + "g" + nextLine;
      else if (sexuality == STRAIGHT) 
         info = info + "s" + nextLine;
      else 
         info = info + "b" + nextLine;
      return info; 
   }  // formatToString method
}  // class PrimaryAttr