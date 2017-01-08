package site;
/*===================================================================         //Polymorphism is also demonstrated in this class which extends the abstract
File Name:          YoungAdultAttr                                            //class SecondaryAttr. This allows a SecondaryAttr object to take on and
Class:              ICS4U                                                     //become different subclasses based on their specific needs.
Authors:            Jiaying & Elaine                                
Date Last Modified: 15/06/2016                                      
Description:        The YoungAdultAttr type that extends Secondary  
                    Attr which contains the specific secondary      
                    attributes for the type of person.              
====================================================================
To Do:                                                              
====================================================================
Change log:                                                                                                      
====================================================================*/
public class YoungAdultAttr extends SecondaryAttr{
   protected int yaAttr[][] = new int[NUMSPECSEC][NUMANS];;                //array of secondary attributes; the first column is the user's own answer; second column is what the user is looking for
   public static String answers[] = {"good looking", "a fan of horror movies", "a college graduate"};
 
  /*===================================================================
  YoungAdultAttr()                                                                    
  --------------------------------------------------------------------
  empty constructor for the young adult secondary attributes          
  ====================================================================*/
   public YoungAdultAttr(){
      yaAttr = null;
   }
 
   /*===================================================================
   YoungAdultAttr(int array[][])                                                                                 
   --------------------------------------------------------------------
   array         -array of answers for specific attributes             
   --------------------------------------------------------------------
   inserts answers into young adult secondary attribute array          
   ====================================================================*/
   public YoungAdultAttr(int sec[][], int array[][]){
      super(sec);
      for(int i = 0; i<NUMSPECSEC; i++){
         for(int j = 0; j<NUMANS; j++){
            yaAttr[i][j] = array[i][j];
         }
      }
   }
   
   //mutators
   public void setYaAttr(int array[][]){
      yaAttr = array;
   }
 
   /*===================================================================
   matchSpecSec(SecondaryAttr other)                                                    
   --------------------------------------------------------------------
   other        -explicit SecondaryAttr object                         
   --------------------------------------------------------------------
   compares the young adult secondary attributes and returns the       
   percentage of match                                                 
   ====================================================================*/
   public double matchSpecSec(SecondaryAttr other){
      int match = 0;
      if(other instanceof YoungAdultAttr){
         YoungAdultAttr other1 = ((YoungAdultAttr)(other));
         for(int i = 0; i<NUMSPECSEC; i++){
            if(yaAttr[i][1]==other1.yaAttr[i][0]){
               match++;       //updates match count
            }
         }
         return (double)match/NUMSPECSEC*100;   //calculates percentage
      }
      else{
         return match;
      }
   }
 
   /*===================================================================
   toString()                                                                           
   --------------------------------------------------------------------
   --------------------------------------------------------------------
   returns the questions and answers for specific secondary attributes 
   ====================================================================*/
   public String toString(){
      String ans = super.toString();
      for(int i = 0; i<NUMANS; i++){
         for(int j = 0; j<NUMSPECSEC; j++){
            if(i==0){
               ans += (char)('A'+j)+". Are you "+answers[j]+"\t"+response[yaAttr[j][i]]+"\n";
            }
            else{
               ans += (char)('A'+j)+". Are you looking for someone who is "+answers[j]+"\t"+response[yaAttr[j][i]]+"\n";
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
   public String formatToString(){
     String info = super.secondaryToString(); 
     for(int a = 0; a < NUMANS; a++){
       for(int b = 0; b < NUMSPECSEC; b++)
         info = info + yaAttr[b][a]; 
       info = info + nextLine;
     } 
     return info;    
   }
 
}
