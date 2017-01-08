package site;
/*===================================================================
File Name:          SecondaryAttr                                   
Class:              ICS4U                                           
Authors:            Jiaying & Elaine                                
Date Last Modified: 04/06/2016                                      
Description:        The abstract SecondaryAttr class which contains 
                    the secondary attributes of a person and their  
                    matching methods.                                
====================================================================
To Do:                                                              
====================================================================
Change log:                                                         
-fields, constructors, matching methods, toString method (Jiaying)                                               
====================================================================*/
abstract class SecondaryAttr{
   public static final int NUMSEC = 7;                   //number of main secondary attributes
   public static final int NUMANS = 2;                   //number of answers for one question eg. own answer and what user wants 
   public static final int NUMSPECSEC = 3;               //number of specific secondary attributes (eg. number of Young adult specs)
   public static final int SECPERCENT = 40;              //lowest possible percentage for a SecondaryAttr to match
   protected int secAttr[][] = new int[NUMSEC][NUMANS];  //array of secondary attributes; the first column is the user's own answer; second column is what the user is looking for
   public static String answers[] = {"an introvert", "a morning person", "a tidy person", "religious", "a smoker", "a drinker", "someone looking for marriage"};
   public static String response[] = {"yes", "no", "doesn't matter"};
   protected static final String nextLine = "~";  
                                                
  /*===================================================================
  SecondaryAttr()                                                                     
  --------------------------------------------------------------------
  empty constructor for the secondary attributes                      
  ====================================================================*/
   public SecondaryAttr(){
      secAttr = null;
   }
   
   //mutators
   public void setSecAttr(int array[][]){
      secAttr = array;
   }
   /*===================================================================
   SecondaryAttr(int array[][])                                                                                  
   --------------------------------------------------------------------
   array         -array of answers for secondary attributes            
   --------------------------------------------------------------------
   inserts answers into secondary attribute array                      
   ====================================================================*/
   public SecondaryAttr(int array[][]){
      for(int i = 0; i<NUMSEC; i++){
         for(int j = 0; j<NUMANS; j++){
            secAttr[i][j] = array[i][j];
         }
      }
   }
   
   /*===================================================================
   matchAttr double(SecondaryAttr other)                                                       
   --------------------------------------------------------------------
   other        -explicit SecondaryAttr object                         
   --------------------------------------------------------------------
   multiplies the general secondary spec and the specific secondary    
   spec percentage with a ratio to determine the final secondary spec  
   match percentage and returns true if percentage is over the         
   minimum match percentage required                                   
   ====================================================================*/
   public double matchAttr(SecondaryAttr other){
      double percentage = (matchSec(other)*NUMSEC/10)+(matchSpecSec(other)*NUMSPECSEC/10);
         
      return percentage;
   
   }
   
   /*===================================================================
   toString()                                                                           
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   returns the questions and answers for secondary attributes          
   ====================================================================*/
   public String toString(){
      //questions & their corresponding answers
      String ans="";
      for(int i = 0; i<NUMANS; i++){
         for(int j = 0; j<NUMSEC; j++){
            if(i==0){
               ans += (char)('A'+j)+". Are you "+answers[j]+"\t"+response[secAttr[j][i]]+"\n";
            }
            else if(i==1){
               ans += (char)('A'+j)+". Are you looking for "+answers[j]+"\t"+response[secAttr[j][i]]+"\n";
            }
         }
         ans+="\n";
      }
      return ans;
   }
   
   /*================================================================================
   toString()                                                                                        
   ---------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------
   returns all the non static variables in the class in a String variable formatted 
   manner - for for saving to a file                                                 
   =================================================================================*/
   public String secondaryToString(){
      String info = ""; 
      for(int i = 0; i < NUMANS; i++){
         for(int j = 0; j < NUMSEC; j++)
            info = info + secAttr[j][i];  
         info = info + nextLine;
      }
      return info; 
   }
   
   /*===================================================================
   matchSec(SecondaryAttr sec)                                                          
   --------------------------------------------------------------------
   other        -explicit SecondaryAttr object                         
   --------------------------------------------------------------------
   matches the general secondary attributes and returns double for     
   percentage of match                                                 
   ====================================================================*/
  
   public double matchSec(SecondaryAttr other){
      int count = 0;    //number of answers that matches
      double percent;
      
      for(int i=0; i<NUMSEC; i++){
         if(secAttr[i][1] == other.secAttr[i][0]){
            count++;
         }         
      }
      percent = (double)count/NUMSEC*100;   //calculates percent of match
      return percent;
   }
   
   // abstract method that matches the specific secondary attributes and returns percent of match
   public abstract double matchSpecSec(SecondaryAttr sec);
   // abstract method that completely formats the secondary attributes 
   public abstract String formatToString();
   
}