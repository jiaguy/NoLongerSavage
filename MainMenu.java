package site;
/*===================================================================

File Name:       MainMenu                 
Class:          ICS4U                                           
Authors:            Elaine, Jiaying, Jonathan, Nicholas                
Date Last Modified: 15/06/2016             
Description:      The main menu of the site which displays all the options                          
====================================================================
To Do:                                                              
-                                                                    
====================================================================
Change Log:                                                                                                      
====================================================================*/
import java.util.*;
public class MainMenu {

 /*===================================================================
   run(AccountDatabase ad)                                                                                    
   --------------------------------------------------------------------
   ad    -database of people           
   --------------------------------------------------------------------
   Runs the site and all of it's functions
   ====================================================================*/
   public static void run(AccountDatabase ad){
      Scanner sc=new Scanner(System.in);
      String fileName = "user.txt";
      String flush;
      int ans=0;
      int choice = 0;
      boolean valid = false;   // checks for correct user input 
      boolean logout = false;
      boolean quit = false;
      String[]s={"View personal profile", "Update personal profile", "Search profile", "List matches", "Logout"};
      String more = "More info";
      Person user;
   
      user = ad.searchByID(Login.loginID);
   
      while(!logout){
         System.out.println("\nMain Menu\nWhat would you like to do?");
         for(int i=0; i<s.length; i++){
            System.out.println(i+1+". "+s[i]);  //prints out the menu options
         }
         valid = false;
         while(!valid){
            try{
               ans = sc.nextInt();
               if(ans>=1 && ans<=5){
                  valid = true;
               }
               else{
                  System.out.println("Input is not within range. Please try again.");
               }
            }
            catch(InputMismatchException ime){
               System.out.println("Invalid Input");
               flush = sc.nextLine();
            }
         }//while
      
       //View Personal Profile Option
         if(ans == 1){
            System.out.println(user);
            System.out.println("View more info?\n1. Yes\n2. No");
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();
                  if(ans==1 || ans==2){
                     valid = true;
                  }
               }
               catch(InputMismatchException ime){
                  flush = sc.nextLine();
               }
            }//while
            if(ans==1){
               ad.printMoreInfo(user.getID());
            }
         }
         
         //Edit Account Option
         else if(ans == 2){
            user = ad.editAccount(user);
         }
         
         // Search Profile Option 
         else if (ans == 3){
            System.out.println("1.Search by name\n2. Search by ID");
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();   //user enters ID
                  if(ans==1 ||ans==2){
                     valid = true;
                  }
                  else{
                     System.out.println("Input is not within range. Please try again.");
                  }
               }
               catch(InputMismatchException ime){
                  System.out.println("Invalid Input");
                  flush = sc.nextLine();
               }
            }//while
         
          //Search by Name Option
            if (ans == 1)
            {  
               System.out.println("1. Search by first name\n2. Search by last name\n3. Search by full name");
               valid = false; 
               String first, last;
               while(!valid){
                  try{
                     ans = sc.nextInt();
                     if(ans==1 || ans==2 ||ans==3){
                        valid = true;
                     }
                  } 
                  catch (InputMismatchException ime){
                     System.out.println("Invalid Input. Please try again!");
                     flush = sc.nextLine(); 
                  }
               } // while end
            
             //Search by First Name Option
               if(ans==1){
                  quit = false;
                  valid = false;
                  sc.nextLine();
                  System.out.print("Enter first name(enter \"quit\" to quit search): ");
                  while(!quit){       //loop of fname
                     first = sc.nextLine();
                     if(first.equals("quit") == true){
                        quit = true;
                     }
                     else if(ad.searchByFName(first) == true){
                        System.out.println("View more info?\n1. Yes\n2. No");
                        while(!valid){
                           try{
                              choice = sc.nextInt();
                              if(choice==1 || choice==2){
                                 valid = true;
                              }
                           }
                           catch(InputMismatchException ime){
                              flush = sc.nextLine();
                           }
                        }//while checks valid
                        quit = true;
                     }
                     else{
                        System.out.print("Try again(enter \"quit\" to quit search): ");
                     }
                  }//while checks quit
               
                  if(choice==1){
                     IDOption(ad);
                  }
               
                //Search by Last Name Option
               }
               else if(ans==2){
                  quit = false;
                  valid = false;
                  sc.nextLine();
                  System.out.print("Enter last name(enter \"quit\" to quit search): ");
                  while(!quit){
                     last = sc.nextLine();
                     if(last.equals("quit") == true){
                        quit = true;
                     }
                     else if(ad.searchByLName(last) == true){
                        System.out.println("View more info?\n1. Yes\n2. No");
                        while(!valid){
                           try{
                              choice = sc.nextInt();
                              if(choice==1 || choice==2){
                                 valid = true;
                              }
                           }
                           catch(InputMismatchException ime){
                              flush = sc.nextLine();
                           }
                        }//while checks valid
                        quit = true;
                     }
                     else{
                        System.out.print("Try again(enter \"quit\" to quit search): ");
                     }
                  }//while checks quit
                  if(choice==1){
                     IDOption(ad);
                  }//if ans=1
                  
                  //Search by Full Name Option
                  else if(ans==3){
                     quit = false;
                     while(!quit){
                        first=sc.nextLine();
                        last=sc.nextLine();
                        if(first.equals("quit") ){
                           quit = true;
                        }
                        else {
                           System.out.println(ad.searchByName(first, last)+"\nView more info?\n1. Yes\n2. No");
                           while(!valid){
                              try{
                                 ans = sc.nextInt();
                                 if(ans==1 || ans==2){
                                    valid = true;
                                 }
                              }
                              catch(InputMismatchException ime){
                                 flush = sc.nextLine();
                              }
                           }//while checks valid
                        }
                     
                     }//while checks quit
                  
                     if(ans==1){
                        IDOption(ad);
                     }//if ans=1
                  }
               }
            
             //Search by ID Option
            
            }
            else if (ans == 2){
               IDOption(ad);
            }
         }
         // else if end  
         
         // List matches option
         else if (ans == 4){
         
            boolean select = false;
            if (ad.match(user)){
               valid = false; 
            
               while(!select){
                  System.out.println("View more info on a certain match?\n1. Yes\n2. No");
                  while(!valid){
                     try{
                        ans = sc.nextInt();
                        sc.nextLine();
                        if(ans==1 || ans==2){
                           valid = true;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }//while
               
                  if(ans==1){
                     IDOption(ad);
                     System.out.println("Do you want to select this match?\n1. Yes\n2. No");
                     choice = sc.nextInt();
                     sc.nextLine();
                     if(choice == 1){
                        select = true;
                        System.out.println("Congratulations! You are no longer savage!");
                        System.out.println("  oo");
                        System.out.println("    oo  OOOOOOOO:       OOOOOOOO!");       
                        System.out.println("    oOOOO!!!!;;;;O    OO.......:;!O");    
                        System.out.println("   OOO!!!;;;;;;;;O  O.......:   ;!O");
                        System.out.println("  OOO!!!!;;::::::.OO........:    ;!O");  
                        System.out.println("  OO!!!!;;:::::..............:   ;!O");
                        System.out.println("  OOO!!!;::::::..............:   ;!O");   
                        System.out.println("   OO!!;;::::::.............:   ;!O");  
                        System.out.println("    OO!;;::::::......oo.....::::!O");     
                        System.out.println("      O!!;::::::.......oo..:::O");     
                        System.out.println("       !!!;:::::........ooO");
                        System.out.println("         !!;:::::.......O oo");      
                        System.out.println("            ;;::::.....O    oo");
                        System.out.println("              :::..O          oo  o");
                        System.out.println("               ::.              ooo");
                        System.out.println("                :              ooooo");
                     }
                  }
                  user.printMatch();
               }//while checks select
            }else{
            	System.out.printf("No matches found, life sucks, %s.%n",user.getPrimAttr().getFName());// if a match is found, the user will now have the option to ask them out 
            }
            
         
         }
         
         // Logout option
         else if(ans == 5){
            ad.writeToFile(fileName);
            logout = true;
         } 
      }
   }//run method

 /*===================================================================
   IODption                                                                                    
   --------------------------------------------------------------------         
   --------------------------------------------------------------------
   Goes through the search by ID option and allows user to view additional
   info.
   ====================================================================*/
   public static void IDOption(AccountDatabase ad){
      Scanner sc=new Scanner (System.in);
      boolean valid;
      String flush;
      int id = 0;
   
      System.out.println("Enter the ID of the user you want to search: ");
      valid = false;
      while(!valid){
         try{
            id = sc.nextInt();
            valid = true;
         }
         catch(InputMismatchException ime){
            flush = sc.nextLine();
         }
      }//while checks valid
      System.out.println(ad.searchByID(id));
      ad.printMoreInfo(ad.searchByID(id).getID());
   
   }//IDOption method

}//Main menu class
