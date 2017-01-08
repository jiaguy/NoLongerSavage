package site;
/*==================================================================

File Name:           AccountDatabase                
Class:               ICS4U                                           
Authors:             Jiaying, Elaine, Jonathan, Nicholas                
Date Last Modified:  15/06/2016             
Description:         A database which stores all the users on the site                             
====================================================================
To Do:                                                              
- FIX CHANGEPERSON METHOD                                                             
====================================================================
Change log:                                                         
- added while loops and try/catch in case user enters wrong info
- searchByID method

====================================================================*/
import java.io.*;
import java.util.*;

public class AccountDatabase {
   Scanner sc = new Scanner(System.in);
   Scanner sc1 = new Scanner(System.in);
   public static Person users[];                         //AccountDatabase includes an array of objects that stores the different types of Persons
   public int max;
   public static int numUsers;
   private static final String nextLine = "~";
   public String fileName;

   //empty constructor
   public AccountDatabase(){
      users = null;
      max = 0;
      numUsers = 0;
   }
   /*===================================================================
     AccountDatabase(int m, Person []p)                                                        
     --------------------------------------------------------------------
     m        -the maximum number of users on the site
     p        -the array of people
     --------------------------------------------------------------------
     constructor for an account database                          
     ====================================================================*/
   public AccountDatabase(int m,Person []p){
      max=m;
      users=p;
      numUsers=p.length;
   }

   /*===================================================================
     AccountDatabase(String fileName)                                                        
     --------------------------------------------------------------------
     fileName       -name of the load file
     --------------------------------------------------------------------
     constructor for an account database which loads from file                         
     ====================================================================*/
   public AccountDatabase(String fileName){
	   this.fileName=fileName;
      loadFile(fileName);
   }

   /*===================================================================      //File input and loading from file is done using this method
     loadFile(String file)                                                     
     --------------------------------------------------------------------
     file        -the name of the load file
     --------------------------------------------------------------------
     loads all the information from a file                         
     ====================================================================*/
   public void loadFile(String file){                                         //try and catch blocks are needed whenever a file is read from or written to 
      try{                                                                    //in case the text file cannot be found or does not exist
         BufferedReader br=new BufferedReader(new FileReader(file));
         max=Integer.parseInt(br.readLine());
         numUsers=Integer.parseInt(br.readLine());
         users=new Person[max];
         for(int i=0;i<numUsers;i++){
            String type=br.readLine();
            if(type.equals("a")){
               users[i]=new Adult();
            }
            else if(type.equals("s")){
               users[i]=new Senior();
            }
            else if(type.equals("y")){
               users[i]=new YoungAdult();
            }
            //reading values
            String password=br.readLine();
            String firstName=br.readLine();
            String lastName=br.readLine();
            int age=Integer.parseInt(br.readLine());
            String gender=br.readLine();
            String sexuality=br.readLine();
            //setting values 
            users[i].setID(i);
            users[i].setPassword(password);
            PrimaryAttr primAttr=new PrimaryAttr(firstName,lastName,age,gender.charAt(0),sexuality.charAt(0));
            users[i].setPrimAttr(primAttr);
            //reading in user preferences
            int[][]sa=new int[SecondaryAttr.NUMSEC][SecondaryAttr.NUMANS];
            for (int j = 0; j < sa[j].length; j++) {
               for (int k = 0; k < sa.length; k++) {
                  sa[k][j]=br.read()-(int)'0';
               }
               br.readLine();
            }
            int[][]ta=new int[SecondaryAttr.NUMSPECSEC][SecondaryAttr.NUMANS];
            for (int j = 0; j < ta[j].length; j++) {
               for (int k = 0; k < ta.length; k++) {
                  ta[k][j]=br.read()-(int)'0';
                  
               }
               
               br.readLine();
            }
            
            if(users[i] instanceof Adult){
               SecondaryAttr secAt=new AdultAttr(sa, ta);
               secAt.setSecAttr(sa);
               users[i].setSecAttr(secAt);
            }
            else if(users[i] instanceof Senior){
               SecondaryAttr secAt=new SeniorAttr(sa, ta);
               secAt.setSecAttr(sa);
               users[i].setSecAttr(secAt);
            }
            else if(users[i] instanceof YoungAdult){
               SecondaryAttr secAt=new YoungAdultAttr(sa, ta);
               secAt.setSecAttr(sa);
               users[i].setSecAttr(secAt);
            }
            
         
         }
         
         br.close();
         System.out.println("load success!");
      }
      catch(IOException e){
         System.out.println("Error loading user database.");
      }
      
   }

   /*===================================================================         //File output or saving to the file is done in this method.
     writeToFile(String file)                                                       
     --------------------------------------------------------------------
     file        -the name of the file being written to
     --------------------------------------------------------------------
     writes all information to file                        
     ====================================================================*/
   public void writeToFile(String file)
   {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(file));
         String [] info; 
         out.write("" +max);
         out.newLine();
         out.write("" +numUsers); 
         out.newLine();
         for(int i = 0; i < numUsers; i++){
            if(users[i] instanceof Adult){
               out.write("a");          
            }
            else if(users[i] instanceof Senior){
               out.write("s");
            }
            else if(users[i] instanceof YoungAdult){
               out.write("y");
            }
            out.newLine();
            // Splits the formatted String into a String array for every nextLine constant in the variable 
            info = users[i].formatToString().split(nextLine); 
            for (int j = 0; j < info.length; j++){            
               out.write(info[j]);
               out.newLine(); 
            }  // for end 
         } // for end 
         out.close();
      }  
      catch(IOException iox){
         System.out.println("Error writing to file"); 
      } 
   }  // writeToFile method


   public static void changePerson(int id, int ans){
      Person p = searchByID(id);
      if(ans<30 && (p instanceof Adult || p instanceof Senior)){
         users[id] = new YoungAdult(id, p.getPass(), p.getPrimAttr(), p.getSecAttr());
         //System.out.println("You are now a young adult!");
         ((YoungAdult)users[id]).editSpecSec();
      }
      else if(ans>=30&&ans<59 && (p instanceof YoungAdult || p instanceof Senior)){
         users[id] = new Adult(id, p.getPass(), p.getPrimAttr(), p.getSecAttr());
         //System.out.println("You are now an adult!");
         ((Adult)users[id]).editSpecSec();
      }
      else if(ans>=60 &&(p instanceof YoungAdult || p instanceof Adult)){
         users[id] = new Senior(id, p.getPass(), p.getPrimAttr(), p.getSecAttr());
         //System.out.println("You are now a senior!");
         ((Senior)users[id]).editSpecSec();
      }
      //users[id].editSpecSec();
   }
   
   /*==================================================================
   editAccount(Person p)                                                       
   --------------------------------------------------------------------                                            
   --------------------------------------------------------------------
   promts user if they want to change their account information        
   ====================================================================*/
   public Person editAccount(Person p){
      String flush;
      boolean valid = false;
      int ans = 0;
      int ageans = 0;
      char ans1 = 0;
      
      System.out.println("Would you like to edit your account information?\n1. Yes\n2. No");
      while(!valid){
         try{
            ans = sc.nextInt();
            if(ans==1 || ans ==2){
               valid = true;
            }
            else{
               outofRange();
            }
         }
         catch(InputMismatchException ime){
            invalidInput();
            flush = sc.nextLine();
         }
      }
      if(ans == 1){
      
         System.out.println("Change password?\n1. Yes\n2. No");           //asks user for password change
         valid = false;
         while(!valid){
            try{
               ans = sc.nextInt();
               if(ans==1 || ans==2){
                  valid = true;
               }
               else{
                  outofRange();
               }
            }
            catch(InputMismatchException ime){
               invalidInput();
               flush = sc.nextLine();
            }
         }//while
         if(ans == 1){
            System.out.print("New password: ");
            p.setPassword(sc1.nextLine());
         }
         
         System.out.println("Edit primary attributes?\n1. Yes\n2. No");   //asks user to edit primary attributes
         valid = false;
         while(!valid){
            try{
               ans = sc.nextInt();
               if(ans==1 || ans==2){
                  valid = true;
               }
               else{
                  outofRange();
               }
            }
            catch(InputMismatchException ime){
               invalidInput();
               flush = sc.nextLine();
            }
         }//while
         if(ans == 1){
            System.out.println("Edit name?\n1. Yes\n2. No");        //edit name
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();
                  if(ans==1 || ans==2){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
               }
               catch(InputMismatchException ime){
                  invalidInput();
                  flush = sc.nextLine();
               }
            }//while
            if(ans == 1){
               System.out.print("New first name: ");
               p.getPrimAttr().setFName(sc1.nextLine());
               System.out.print("New last name: ");
               p.getPrimAttr().setLName(sc1.nextLine());
            }
            System.out.println("Edit gender?\n1. Yes\n2. No");      //edit gender
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();
                  if(ans==1 || ans==2){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
               }
               catch(InputMismatchException ime){
                  invalidInput();
                  flush = sc.nextLine();
               }
            }//while
            if(ans == 1){  
               System.out.print("New gender(male(m)/female(f)): ");
               valid = false;
               while(!valid){
                  ans1 = sc1.next().charAt(0);
                  if(ans1=='m' || ans1=='f'){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
                  p.getPrimAttr().setGender(ans1);
               }//while
            }
            System.out.println("Edit sexuality?\n1. Yes\n2. No");   //edit sexuality
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();
                  if(ans==1 || ans==2){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
               }
               catch(InputMismatchException ime){
                  invalidInput();
                  flush = sc.nextLine();
               }
            }
            if(ans == 1){
               System.out.print("New sexuality(straight(s)/lesbian(l)/gay(g)/bisexual(b)): ");
               valid = false;
               while(!valid){
                  ans1 = sc1.next().charAt(0);
                  if(ans1=='s' || ans1=='l' || ans1=='g' ||ans1=='b'){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
               }//while
               p.getPrimAttr().setSexuality(ans1);
            }
            System.out.println("Edit age?\n1. Yes\n2. No");         //edit age
            valid = false;
            while(!valid){
               try{
                  ans = sc.nextInt();
                  if(ans==1 || ans==2){
                     valid = true;
                  }
                  else{
                     outofRange();
                  }
               }
               catch(InputMismatchException ime){
                  invalidInput();
                  flush = sc.nextLine();
               }
            }//while
            if(ans == 1){
               System.out.print("New age: ");
               valid = false;
               while(!valid){
                  try{
                     ageans = sc.nextInt();
                     if(ageans>=18){
                        if(p instanceof YoungAdult && ageans<29){
                           valid = true;
                        }
                        else if(p instanceof Adult && ageans>=30 && ageans <59){
                           valid = true;
                        }
                        else if(p instanceof Senior && ageans>=60){
                           valid = true;
                        }
                        else{
                           System.out.println("Please enter a valid age for your age group!");
                        }
                     }
                     else{
                        System.out.println("You are too young to be here!");
                     }
                  }
                  catch(InputMismatchException ime){
                     invalidInput();
                     flush = sc.nextLine();
                  }
               }
               p.getPrimAttr().setAge(ageans);
            }
         
         }
         
         System.out.println("Edit secondary attributes?\n1. Yes\n2. No");    //asks user to edit secondary attributes
         valid = false;
         while(!valid){
            try{
               ans = sc.nextInt();
               if(ans==1 || ans==2){
                  valid = true;
               }
               else{
                  outofRange();
               }
            }
            catch(InputMismatchException ime){
               invalidInput();
               flush = sc.nextLine();
            }
         }
         if(ans == 1){
            int secAns[][] = new int[SecondaryAttr.NUMSEC][SecondaryAttr.NUMANS];
            
            System.out.println("For the following questions, answer:\n1. Yes\n2. No\n3. Doesnt matter");
            for(int i = 0; i<SecondaryAttr.NUMANS; i++){    //prompts user and stores their answers
               for(int j = 0; j<SecondaryAttr.NUMSEC; j++){
                  if(i==0){
                     System.out.print((char)('A'+j)+". Are you "+SecondaryAttr.answers[j]+"? ");
                     valid = false;
                     while(!valid){
                        try{
                           ans = sc.nextInt();
                           if(ans==1 || ans==2 || ans==3){
                              valid = true;
                           }
                           else{
                              outofRange();
                           }
                        }
                        catch(InputMismatchException ime){
                           invalidInput();
                           flush = sc.nextLine();
                        }
                     }
                     secAns[j][i] = ans-1;
                  }
                  else{
                     System.out.print((char)('A'+j)+". Are you looking for "+SecondaryAttr.answers[j]+"? ");
                     valid = false;
                     while(!valid){
                        try{
                           ans = sc.nextInt();
                           if(ans==1 || ans==2 || ans==3){
                              valid = true;
                           }
                           else{
                              outofRange();
                           }
                        }
                        catch(InputMismatchException ime){
                           invalidInput();
                           flush = sc.nextLine();
                        }
                     }
                  
                     secAns[j][i] = ans-1;
                  }
               }
            }
            p.getSecAttr().setSecAttr(secAns);      //saves new secondary attributes
         
         }
         
         System.out.println("Edit specific secondary attributes?\n1. Yes\n2. No");    //asks user to edit their specific secondary attributes
         valid = false;
         while(!valid){
            try{
               ans = sc.nextInt();
               if(ans==1 || ans==2){
                  valid = true;
               }
               else{
                  outofRange();
               }
            }
            catch(InputMismatchException ime){
               invalidInput();
               flush = sc.nextLine();
            }
         }
         if(ans==1){
            if(p instanceof YoungAdult){
               ((YoungAdultAttr)p.getSecAttr()).setYaAttr(((YoungAdult)p).editSpecSec());
            }
            else if(p instanceof Adult){
               ((AdultAttr)p.getSecAttr()).setAdAttr(((Adult)p).editSpecSec());
            }
            else if(p instanceof Senior){
               ((SeniorAttr)p.getSecAttr()).setSenAttr(((Senior)p).editSpecSec());
            }
            
         }
      }
      return p;
   }
   /*===================================================================
     login(int id, String pass)                                                       
     --------------------------------------------------------------------
     id          -the ID for a person
     pass        -the account password
     --------------------------------------------------------------------
     login method that checks users id and password                        
     ====================================================================*/
   public boolean login(int id, String pass){
      if(pass.equals(users[id].getPass())){
         return true;
      }
      else
         return false;
   }

   /*===================================================================            //There are four different types of searching that a user can choose.
     searchByID(int ID)                                                             //They include searching the database by first name, last name, full
     --------------------------------------------------------------------           //name, and ID.
     id          -the ID for a person
     --------------------------------------------------------------------
     returns a Person when searched using their ID                      
     ====================================================================*/
   public static Person searchByID(int ID){
      boolean found = false;
      for(int i=0; i<numUsers && !found; i++){
         if(users[i].getID() == ID){     //checks if ID exists
            found = true;
            return users[i];      //prints out basic user infos
         }
      }
      return null;
   }

   /*===================================================================
     searchByName(String f, String l)                                                       
     --------------------------------------------------------------------
     f        -the first name of the person
     l        -the last name of the person
     --------------------------------------------------------------------
     returns a Person when searched using their ID                      
     ====================================================================*/
   public boolean searchByName(String f, String l){
      boolean found=false;
      for(int i=0; i<numUsers ; i++){
         if(users[i].primAttr.getFName().equalsIgnoreCase(f) && users[i].primAttr.getLName().equalsIgnoreCase(l)){     //checks if ID exists
            System.out.println(users[i]);      //prints out basic user infos
            found=true;
         }
      }
      if(!found){
         System.out.println("Error. User does not exist.");
      
      }
      return found;
   }

   /*===================================================================
     searchByFName(String f)                                                       
     --------------------------------------------------------------------
     f        -the first name of the person
     --------------------------------------------------------------------
     prints out all the people with the same first name                     
     ====================================================================*/
   public boolean searchByFName(String f){
      boolean found=false;
      for(int i=0; i<numUsers ; i++){
         if(users[i].primAttr.getFName().equalsIgnoreCase(f)){     //checks if first name exists
            System.out.println(users[i]);      //prints out basic user infos
            found=true;
         }
      }
      if(!found){
         System.out.println("User does not exist.");
      
      }
      return found;
   }

   /*===================================================================
     searchByLName(String l)                                                       
     --------------------------------------------------------------------
     l        -the first name of the person
     --------------------------------------------------------------------
     prints out all the people with the same last name                     
     ====================================================================*/
   public boolean searchByLName(String l){
      boolean found=false;
      for(int i=0; i<numUsers; i++){
         if(users[i].primAttr.getLName().equalsIgnoreCase(l)){     //checks if first name exists
            System.out.println(users[i]);      //prints out secondary attributes
            found=true;
         }
      }
      if(!found){
         System.out.println("User does not exist.");
      }
      return found;
   }

   /*===================================================================
     match()                                                     
     --------------------------------------------------------------------
     --------------------------------------------------------------------
     matches all the people in the database and places them into the array                      
     ====================================================================*/
   public boolean match(Person lucky){
      lucky.resetMatches();
      boolean matchFound = false; 
      for(int i = 0; i < numUsers; i++){
         if(lucky.getID() != users[i].getID()){
            // Matches the use
            if (lucky.matchPerson(users[i]))
               matchFound = true; 
         }
      }
      if (matchFound){
         lucky.sortMatch(); 
         lucky.printMatch(); 
      }  // if the match is found, program will proceed to print out all the matches 
      else{
         System.out.println("A True Savage You Are!\n0 Compatible Matches Found\nNot even our dating is able to cure your savagery...\n");
      }
      return matchFound; 
   }

   /*===================================================================
     newUser()                                                       
     --------------------------------------------------------------------
     --------------------------------------------------------------------
     creates a new user and prompts for account info                          
     ====================================================================*/
   public void newUser(){
      Scanner sc = new Scanner(System.in);
      //Scanner sc1 = new Scanner(System.in);
      Person p1=new Person();
      PrimaryAttr pa1 = new PrimaryAttr();
   
      
      System.out.println("\nWelcome to NoLongerSavage.org! \nYour new ID is: "+(numUsers)+"\nPlease fill in the following information.");
      //Set type
      System.out.print("\nAge Group\nIf you are 18-29 years old, enter \"y\" for young adult. \nIf you are 30-59 years old, enter \"a\" for adult. \nIf your age is 60+, enter \"s\" for senior.\nPlease Enter Your Age Group: ");
      boolean valid = false;
      char type = ' ';
      while(!valid){
         type = sc.nextLine().charAt(0);
         if(type == 's' || type == 'a' || type == 'y'){
            if(type == 's'){
               p1=new Senior();
            }
            else if(type == 'a'){
               p1=new Adult();
            }
            else{
               p1=new YoungAdult();
            }
            valid = true;
         }
         else{
            System.out.println("Error. Please enter a valid sexuality: ");
         }
      }
      //Set pass
      System.out.print("Please enter the password you want to use: ");
      p1.setPassword(sc.nextLine());
   
      //Primary Attribute Variables
      //Set name
      System.out.print("\nPlease enter the following personal informations. \nFirst Name: ");
      pa1.setFName(sc.nextLine());
      System.out.print("Last Name: ");
      pa1.setLName(sc.nextLine());
      //sc.nextLine();
   
      //Set age
      System.out.print("Age: ");
      int age=0;
      valid = false;
      String flush;
      while(!valid){
         try{
            age = sc.nextInt();
            if(age>=18 && age<30 && type == 'y'){
               pa1.setAge(age);
               valid = true;    
            //}else if(){
            }else if(age>=30 && age<60 &&type == 'a'){
               pa1.setAge(age);
               valid = true;
            }else if(age>=60 &&type == 's'){
               pa1.setAge(age);
               valid = true;
            }else if(age<18){
               System.out.print("Error. You are too young to be here!\nPlease enter a valid age(18+): ");
            }else{
               System.out.print("Error. You are in the wrong age group.\nYoung Adult: 18-29\nAdult : 30-59\nSenior : 60+\nPlease enter an age within your chosen age group: ");
            }
         }
         catch(InputMismatchException ime){
            System.out.println("Error. Please enter a valid age.");
            flush = sc.nextLine();
         }
      }
      sc.nextLine();
      //Set gender
      
      boolean real = false;
      while(!real){
         System.out.print("Gender (m/f): ");
         String in=sc.nextLine();
         char gender = in.charAt(0);
         if(gender == 'f' || gender == 'm'){
            pa1.setGender(gender);
            real=true;
         }
         else{
            System.out.println("Error. Please enter a valid gender: ");
         }
      }
   
      //Set sexuality
      System.out.print("Sexuality(straight(s), lesbian(l), gay(g), bisexual(b)): ");
      valid = false;
      while(!valid){
         char sex = sc.nextLine().charAt(0);
         if(sex == 's' || sex == 'l' || sex == 'g' || sex == 'b'){
            pa1.setSexuality(sex);
            valid = true;
         }
         else{
            System.out.print("Error. Please enter a valid sexuality: ");
         }
      }
      p1.setPrimAttr(pa1);
   
      int sa[][] = new int[SecondaryAttr.NUMSEC][SecondaryAttr.NUMANS];
   
      //Set secondary atrributes      
      System.out.println("\nFor the following questions, answer \"1\", \"2\", or \"3\":\n1. Yes\n2. No\n3. Doesnt matter");
      int ans;
      for(int i = 0; i<SecondaryAttr.NUMANS; i++){    //prompts user and stores their answers
         for(int j = 0; j<SecondaryAttr.NUMSEC; j++){
            if(i==0){
               valid = false;
               System.out.print((char)('A'+j)+". Are you "+SecondaryAttr.answers[j]+"? ");
               while(!valid){//checks for a valid answer
                  try{
                     ans = sc.nextInt();
                     if(ans==1 || ans==2 || ans==3){
                        valid = true;
                        sa[j][i] = ans-1;
                     }
                  }
                  catch(InputMismatchException ime){
                     flush = sc.nextLine();
                  }
               }
            }
            else{
               valid = false;
               System.out.print((char)('A'+j)+". Are you looking for "+SecondaryAttr.answers[j]+"? ");
               while(!valid){//checks for a valid answer
                  try{
                     ans = sc.nextInt(); 
                     if(ans==1 || ans==2 || ans==3){
                        valid = true;
                        sa[j][i] = ans-1;
                     }
                  }
                  catch(InputMismatchException ime){
                     flush = sc.nextLine();
                  }
               }
            }
         }
      }
      
      if(p1 instanceof Adult){
         SecondaryAttr secAt=new AdultAttr();
         secAt.setSecAttr(sa);
         p1.setSecAttr(secAt);
      }
      else if(p1 instanceof Senior){
         SecondaryAttr secAt=new SeniorAttr();
         secAt.setSecAttr(sa);
         p1.setSecAttr(secAt);
      }
      else if(p1 instanceof YoungAdult){
         SecondaryAttr secAt=new YoungAdultAttr();
         secAt.setSecAttr(sa);
         p1.setSecAttr(secAt);
      }
        //p1.secAttr.setSecAttr(sa);      //saves new secondary attributes
      System.out.println("\nFor the following continuation of questions, answer\"1\", \"2\", or \"3\":\n1. Yes\n2. No\n3. Doesnt matter");
      if(age<30){
         int ya[][] = new int[SecondaryAttr.NUMSPECSEC][SecondaryAttr.NUMANS];
      
         for(int i = 0; i<SecondaryAttr.NUMANS; i++){
            for(int j = 0; j<SecondaryAttr.NUMSPECSEC; j++){
               if(i==0){
                  valid = false;
                  System.out.print((char)('A'+j)+". Are you "+YoungAdultAttr.answers[j]+"? ");  //prompts specific young adult questions
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           ya[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }// while valid
               }
               else{
                  valid = false;
                  System.out.print((char)('A'+j)+". Are you looking for someone who is "+YoungAdultAttr.answers[j]+"? ");
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           ya[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }//while valid
               }
            }
            System.out.println();
         }
         ((YoungAdultAttr)(p1.getSecAttr())).setYaAttr(ya);   //saves new young adult attributes
      }
      else if(age>=30&&age<60){
         int ad[][] = new int[SecondaryAttr.NUMSPECSEC][SecondaryAttr.NUMANS];

         for(int i = 0; i<SecondaryAttr.NUMANS; i++){
            for(int j = 0; j<SecondaryAttr.NUMSPECSEC; j++){
               if(i==0){
                  valid = false;
                  System.out.print((char)('A'+j)+". Do you "+AdultAttr.answers[j]+"? ");  //prompts specific adult questions
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           ad[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }
               
               }
               else{
                  valid = false;
                  System.out.print((char)('A'+j)+". Are you looking for "+AdultAttr.answers[j]+"? ");
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           ad[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }//while valid
               
               }//else
            }
            System.out.println();
         }
         ((AdultAttr)(p1.getSecAttr())).setAdAttr(ad);   //saves new adult attributes
      }
      else{
         int se[][] = new int[SecondaryAttr.NUMSPECSEC][SecondaryAttr.NUMANS];
         
         for(int i = 0; i<SecondaryAttr.NUMANS; i++){
            for(int j = 0; j<SecondaryAttr.NUMSPECSEC; j++){
               if(i==0){
                  valid = false;
                  System.out.print((char)('A'+j)+". Are you "+SeniorAttr.answers[j]+"? ");  //prompts specific senior questions
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           se[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }
               
               }
               else{
                  valid = false;
                  System.out.print((char)('A'+j)+". Are you looking for someone who is "+SeniorAttr.answers[j]+"? ");
                  while(!valid){//checks for valid answer
                     try{
                        ans = sc.nextInt();
                        if(ans==1 || ans==2 || ans==3){
                           valid = true;
                           se[j][i] = ans-1;
                        }
                     }
                     catch(InputMismatchException ime){
                        flush = sc.nextLine();
                     }
                  }
                  System.out.println();
               }
               ((SeniorAttr)(p1.getSecAttr())).setSenAttr(se);   //saves new senior attributes
            }
         }
      }  
      users[numUsers]=p1;
      numUsers++;
      writeToFile(fileName);
      loadFile(fileName);
   }//newUser method

   /*===================================================================
     printMoreInfo(int ID)                                                       
     --------------------------------------------------------------------
     ID          -the ID of another person
     --------------------------------------------------------------------
     prints more info(secondary attributes) of a person                                             
     ====================================================================*/
   public void printMoreInfo(int ID){
   
      for(int i=0; i<numUsers ; i++){
         if(users[i].getID()==ID){     //checks if user exists in database
         
            System.out.println(users[i].secAttr +"\n");      //prints out secondary attributes
            return;
         }
      }
   
      System.out.println("Error. User does not exist.");
   }
   
   /*===================================================================         //Recursion is used to traverse the array and determine the
   oldest(int start, int end)                                                    //oldest person.
   --------------------------------------------------------------------
   start          -the beginning of the array
   end            -the end of the array
   --------------------------------------------------------------------
   returns the oldest person in the database                                             
   ====================================================================*/
   public  Person oldest(int start,int end){
      if(start+1<end){
         return compare(users[start],oldest(start+1,end));
      }
      else{
         return users[start];
      }
   }
   public static Person compare(Person a,Person b){
      if( a.getPrimAttr().getAge()>b.getPrimAttr().getAge()){
         return a;
      }
      else{
         return b;
      }
   	
   }
   
   //String error messages
   public static void invalidInput(){
      System.out.println("Invalid input. Please try again.");   
   }
  
   public static void outofRange(){
      System.out.println("Input is not within the range. Please try again.");
   }

}
