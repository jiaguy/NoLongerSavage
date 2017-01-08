package site;
/*===================================================================
File Name:          AdultAttr                                       
Class:              ICS4U                                           
Authors:            Jiaying & Elaine                                
Date Last Modified: 07/06/2016                                      
Description:        The AdultAttr type that extends SecondaryAttr   
                    which contains the specific secondary attributes
                    for the type of person.                          
====================================================================
To Do:                                                              
====================================================================
Change log:                                                                                                     
====================================================================*/
public class AdultAttr extends SecondaryAttr{
   protected int adAttr[][] = new int[NUMSPECSEC][NUMANS];         //array of secondary attributes; the first column is the user's own answer; second column is what the user is looking for
   public static String answers[] = {"want(s) kids", "go(es) clubbing", "have/has a job"};
 
  /*===================================================================
  AdultAttr()                                                                          
  --------------------------------------------------------------------
  empty constructor for the adult secondary attributes                
  ====================================================================*/
   public AdultAttr(){
      adAttr = null;
   }
 
   /*===================================================================
   AdultAttr(int array[][])                                                                                      
   --------------------------------------------------------------------
   array         -array of answers for specific attributes             
   --------------------------------------------------------------------
   inserts answers into adult secondary attribute array                
   ====================================================================*/
   public AdultAttr(int sec[][], int array[][]){
      super(sec);
      for(int i = 0; i<NUMSPECSEC; i++){
         for(int j = 0; j<NUMANS; j++){
            adAttr[i][j] = array[i][j];
         }
      }
   }
 
   //mutators
   public void setAdAttr(int array[][]){
      adAttr = array;
   }
   
   /*====================================================================
   |matchSpecSec(SecondaryAttr other)                                   |                 
   |--------------------------------------------------------------------|
   |other        -explicit SecondaryAttr object                         |
   |--------------------------------------------------------------------|
   |compares the adult secondary attributes and returns the             |
   |percentage of match                                                 |
   ====================================================================*/
   public double matchSpecSec(SecondaryAttr other){
      int match = 0;
      if(other instanceof AdultAttr){
         AdultAttr other1 = ((AdultAttr)(other));
         for(int i = 0; i<NUMSPECSEC; i++){
            if(adAttr[i][1]==other1.adAttr[i][0]){
               match++;    //updates match count
            }
         }
         return (double)match/NUMSPECSEC*100;      //calculates percentage of match
      }  
      else{
         return match;
      }
   }
 
   /*====================================================================
   |toString()                                                          |                 
   |--------------------------------------------------------------------|
   |--------------------------------------------------------------------|
   |returns the questions and answers for specific secondary attributes |
   ====================================================================*/
   public String toString(){
      String ans = super.toString();
      for(int i = 0; i<NUMANS; i++){
         for(int j = 0; j<NUMSPECSEC; j++){
            if(i==0){
               ans += (char)('A'+j)+". Do you "+answers[j]+"\t"+response[adAttr[j][i]]+"\n";
            }
            else{
               ans += (char)('A'+j)+". Are you looking for someone who "+answers[j]+"\t"+response[adAttr[j][i]]+"\n";
            }
         }
         ans+="\n";
      }
      return ans;
   }
   
   /*=================================================================================
   |toString()                                                                       |                 
   |---------------------------------------------------------------------------------|
   |---------------------------------------------------------------------------------|
   |returns all the non static variables in the class in a String variable formatted |
   |manner - for for saving to a file                                                | 
   =================================================================================*/
   public String formatToString(){
     String info = super.secondaryToString(); 
     for(int i = 0; i < NUMANS; i++){
       for(int j = 0; j < NUMSPECSEC; j++)
         info = info + adAttr[j][i]; 
       info = info + nextLine;
     } 
     return info;    
   } // fortoString method 
}
