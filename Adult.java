package site;
/*==================================================================

File Name:				Adult							      						
Class:					ICS4U                                           
Authors:            Jiaying													   
Date Last Modified:	04/06/2016													
Description:			The Adult person type                                                                      
====================================================================
Changes:                                                            
-added editAccount method                                           
===================================================================*/
import java.util.*;
public class Adult extends Person{
   Scanner sc = new Scanner(System.in);
   /*===================================================================
	Adult()										                                                  
	--------------------------------------------------------------------
   --------------------------------------------------------------------
	empty constructor for Adult object                              		
	====================================================================*/
   public Adult(){
   }
   
   /*===================================================================
	Adult(int id, String pass, PrimaryAttr prim, SecondaryAttr sec)     
	--------------------------------------------------------------------
	id				-the identification number                        		
	pass          -the password for the account                         
   prim          -the primary attributes                               
   sec           -the secondary attributes              					
	--------------------------------------------------------------------
	constructor for Adult object which takes in their id, password,     
   primary attributes, and secondary attributes               			
	====================================================================*/
   public Adult(int id, String pass, PrimaryAttr prim, SecondaryAttr sec){
      super(id, pass, prim, sec);
   }
   
   /*===================================================================
	editSpecSec()                                                    	
	--------------------------------------------------------------------                                        					
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
               System.out.print((char)('A'+j)+". Are you "+AdultAttr.answers[j]+"? ");  //prompts specific adult questions
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
               System.out.print((char)('A'+j)+". Are you looking for "+AdultAttr.answers[j]+"? ");
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
         }
      }
      return specAns;
         //((AdultAttr)secAttr).setAdAttr(specAns);   //saves new adult attributes
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