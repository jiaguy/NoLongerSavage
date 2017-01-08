package site;
/*===================================================================

File Name:				Adult							      						
Class:					ICS4U                                          
Authors:             Jiaying													   
Date Last Modified:	15/06/2016													
Description:			The Senior person type                                                                   
=====================================================================
Changes:                                                            
-added editAccount method                                           
====================================================================*/
import java.util.*;
public class Senior extends Person{
   
   /*===================================================================
	Senior()										                                               
	--------------------------------------------------------------------
   --------------------------------------------------------------------
	empty constructor for Senior object                              	
	====================================================================*/
   public Senior(){
   }
   
   /*===================================================================
	Senior(int id, String pass, PrimaryAttr prim, SecondaryAttr sec)    
	--------------------------------------------------------------------
	id				-the identification number                        		
	pass          -the password for the account                         
   prim          -the primary attributes                               
   sec           -the secondary attributes              					
	--------------------------------------------------------------------
	constructor for Senior object which takes in their id, password,    
   primary attributes, and secondary attributes               			
	====================================================================*/
   public Senior(int id, String pass, PrimaryAttr prim, SecondaryAttr sec){
      super(id, pass, prim, sec);
   }
   
   /*===================================================================
	editSpecSec()                                                    
	--------------------------------------------------------------------                                       					|
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
               System.out.print((char)('A'+j)+". Are you "+SeniorAttr.answers[j]+"? ");  //prompts specific senior questions
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
               System.out.print((char)('A'+j)+". Are you looking for "+SeniorAttr.answers[j]+"? ");
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
         //((SeniorAttr)secAttr).setSenAttr(specAns);   //saves new senior attributes
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