package site;
/*===================================================================

File Name:       Login                 
Class:          ICS4U                                           
Authors:            Elaine, Jiaying, Jonathan, Nicholas                
Date Last Modified: 07/06/2016             
Description:      This is where the person either logs in or      
                    creates a new account                           
====================================================================
====================================================================*/
import java.util.*;
public class Login {
   public static int loginID;
   
   /*===================================================================
   Welcome(AccountDatabase ad)                                                                                    
   --------------------------------------------------------------------
   ad    -database of people           
   --------------------------------------------------------------------
   The login screen of the site. Gives user the choice of login, sign
   up, or exiting site.
   ====================================================================*/
   public static void Welcome(AccountDatabase ad){
      Scanner sc = new Scanner(System.in);
      final int MAXLOGIN = 5;   //max number of tries allowed
      boolean in = false;
      boolean exit = false;
      boolean valid;
      int sign = 0;
      int id = 0;
      String flush, pass;
      
      while(!exit){
      System.out.println("\nWelcome to NoLongerSavage.org! What would you like to do today?");
         System.out.print("1.Log in.\n2.Sign up.\n3.Exit\n Enter number from menu: ");
         valid = false;
         while(!valid){                                                       //when asking for user input, many InputMismatchException errors may
            try{                                                              //occur and are prevented by using try catch blocks and looping the question  
               sign = sc.nextInt();  //user chooses what they want to do      //until the user gives valid input
               if(sign==1 || sign==2 || sign==3){
                  valid = true;
               }
               else{
                  System.out.println("Input is not within the range. Please try again.");
               }
            }
            catch(InputMismatchException ime){
               System.out.println("You have entered invalid input. Please try again.");
               flush = sc.nextLine();
            }
         }//while
        
         if(sign == 1){   //log in option
            for(int i = 0; i<MAXLOGIN && !in; i++){
               System.out.print("\nUser ID: ");
               valid = false;
               while(!valid){
            	   
                  try{
                     id = sc.nextInt();
                     if(id<ad.numUsers&&id>=0){
                     valid=true;}
                  }
                  catch(InputMismatchException ime){
                     flush = sc.nextLine();
                  }
               }
               sc.nextLine();
               System.out.print("Password: ");
               pass = sc.nextLine();
               if(ad.login(id, pass) == true){   //method checks whether password matches ID
                 loginID = id;
                  MainMenu.run(ad);
                  in = true;
               }
               else if(i<MAXLOGIN-1){
                  System.out.println("Please try again.");
               }
            }//for
            //exit = true;
         }
         else if(sign == 2){
            ad.newUser();         //calls method that creates new user
            loginID = AccountDatabase.numUsers;
            Login.Welcome(ad);
            //MainMenu.run(ad);
            //in = true;
         }
         else{
            exit = true;
         }
      }//exit
   }//Welcome method
}
