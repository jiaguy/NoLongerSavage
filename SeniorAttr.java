package site;
/*===================================================================
File Name:          SeniorAtr                                       
Class:              ICS4U                                           
Authors:            Jiaying & Elaine                                
Date Last Modified: 15/06/2016                                      
Description:        The SeniorAttr type that extends SecondaryAttr  
                    which contains the specific secondary attributes
                    for the type of person.                          
====================================================================
To Do:                                                              
====================================================================
Change log:                                                                                                      
====================================================================*/
public class SeniorAttr extends SecondaryAttr{
   protected int senAttr[][] = new int[NUMSPECSEC][NUMANS];            //array of secondary attributes; the first column is the user's own answer; second column is what the user is looking for
   public static String answers[] = {"enjoy(s) travelling", "have/has a family", "still work(s)"};
 
  /*===================================================================
  SeniorAttr()                                                                         
  --------------------------------------------------------------------
  empty constructor for the senior secondary attributes               
  ====================================================================*/
   public SeniorAttr(){
      senAttr = null;
   }
   
   /*===================================================================
   SeniorAttr(int array[][])                                                                                    
   --------------------------------------------------------------------
   array         -array of answers for specific attributes             
   --------------------------------------------------------------------
   inserts answers into senior secondary attribute array               
   ====================================================================*/
   public SeniorAttr(int array[][]){
    senAttr=array;
  }
 
   public SeniorAttr(int sec[][], int array[][]){
      super(sec);
      for(int i = 0; i<NUMSPECSEC; i++){
         for(int j = 0; j<NUMANS; j++){
            senAttr[i][j] = array[i][j];
         }
      }
   }
   
   //mutators
   public void setSenAttr(int array[][]){
      senAttr = array;
   }
   
   /*===================================================================
   matchSpecSec(SecondaryAttr other)                                                    
   --------------------------------------------------------------------
   other        -explicit SecondaryAttr object                         
   --------------------------------------------------------------------
   compares the senior secondary attributes and returns the            
   percentage of match                                                 
   ====================================================================*/
   public double matchSpecSec(SecondaryAttr other){
      int match = 0;
      if(other instanceof SeniorAttr){
         SeniorAttr other1 = ((SeniorAttr)(other));
         for(int i = 0; i<NUMSPECSEC; i++){
            if(senAttr[i][1]==other1.senAttr[i][0]){
               match++;
            }
         }
         return (double)match/NUMSPECSEC*100;
      }
      else{
         return match;
      }
   }
 
   /*================================================================================
   toString()                                                                                        
   ---------------------------------------------------------------------------------
   ---------------------------------------------------------------------------------
   returns all the non static variables in the class in a formatted String variable                                                  
   =================================================================================*/
   public String toString(){
      String ans = super.toString();
      for(int i = 0; i<NUMANS; i++){
         for(int j = 0; j<NUMSPECSEC; j++){
            if(i==0){
               ans += (char)('A'+j)+". Do you "+answers[j]+"\t"+response[senAttr[j][i]]+"\n";
            }
            else{
               ans += (char)('A'+j)+". Are you looking for someone who "+answers[j]+"\t"+response[senAttr[j][i]]+"\n";
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
     for(int i = 0; i < NUMANS; i++){
       for(int j = 0; j < NUMSPECSEC; j++)
         info = info + senAttr[j][i]; 
       info = info + nextLine;
     } 
     return info;    
   }
}
