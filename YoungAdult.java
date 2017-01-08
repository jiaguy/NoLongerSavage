package site;
/*===================================================================

File Name:				YoungAdult													
Class:					ICS4U                                           
Authors:            Jiaying													   
Date Last Modified:	15/06/2016													
Description:			The YoungAdult person type                                                                 
=====================================================================
Changes:                                                            
-added editAccount method                                           
===================================================================*/
import java.util.*;
public class YoungAdult extends Person{                                    //Polymorphism is demonstrated in this YoungAdult class which is one of the
   Scanner sc = new Scanner(System.in);                                    //subclasses of the Person class.
   /*===================================================================   
	YoungAdult()										                                            
	--------------------------------------------------------------------
   --------------------------------------------------------------------
	empty constructor for YoungAdult object                        		
	====================================================================*/
   public YoungAdult(){
   }
   
   /*===================================================================
	YoungAdult(int id, String pass, PrimaryAttr prim, SecondaryAttr sec)
	--------------------------------------------------------------------
	id				-the identification number                        		
	pass          -the password for the account                         
   prim          -the primary attributes                               
   sec           -the secondary attributes              					
	--------------------------------------------------------------------
	constructor for YoungAdult object which takes in their id, password,
   primary attributes, and secondary attributes               			
	====================================================================*/
   public YoungAdult(int id, String pass, PrimaryAttr prim, SecondaryAttr sec){
      super(id, pass, prim, sec);
   }
   
   /*===================================================================
	editSpecSec()                                                    	
	--------------------------------------------------------------------                                        					|
	--------------------------------------------------------------------
	promts user if they want to change their account information        
	====================================================================*/
   public int[][] editSpecSec(){
      String flush;
      boolean valid = false;
      int ans = 0;
   
      int specAns[][] = new int[SecondaryAttr.NUMSPECSEC][SecondaryAttr.NUMANS];
            
      System.out.println("For the following questions, answer:\n1. Yes\n2. No\n3. Doesnt matter");
      for(int i = 0; i<SecondaryAttr.NUMANS; i++){
         for(int j = 0; j<SecondaryAttr.NUMSPECSEC; j++){
            if(i==0){
               System.out.print((char)('A'+j)+". Are you "+YoungAdultAttr.answers[j]+"? ");  //prompts specific young adult questions
               valid = false;
               while(!valid){
                  try{
                     ans = sc.nextInt();
                     if(ans==1 || ans==2 || ans==3){
                        valid = true;
                     }
                  }
                  catch(InputMismatchException ime){
                     flush = sc.nextLine();
                  }
               }
               specAns[j][i] = ans-1;
            }
            else{
               System.out.print((char)('A'+j)+". Are you looking for "+YoungAdultAttr.answers[j]+"? ");valid = false;
               while(!valid){
                  try{
                     ans = sc.nextInt();
                     if(ans==1 || ans==2 || ans==3){
                        valid = true;
                     }
                  }
                  catch(InputMismatchException ime){
                     flush = sc.nextLine();
                  }
               }
               specAns[j][i] = ans-1;
            }
         }
      }
      return specAns;
      //((YoungAdultAttr)secAttr).setYaAttr(specAns);   //saves new young adult attributes
   
   }
   
   /*===================================================================
	toString()       										                                    
	--------------------------------------------------------------------
   --------------------------------------------------------------------
	returns the String from the superclass                       		
	====================================================================*/
   public String toString(){
      return super.toString();
   }

}