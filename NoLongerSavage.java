package site;
/*===================================================================
File Name:    			NoLongerSavage                 
Class:     					ICS4U                                           
Authors:            Elaine, Jiaying, Nicholas, Jonathan                
Date Last Modified: 15/06/2016             
Description:   			The class which runs the nolongersavage site                          
====================================================================*/
   public class NoLongerSavage {
	public static void main(String args[]){
  	String fileName = "user.txt";
  
  	AccountDatabase ad=new AccountDatabase(fileName);
    
  	Login.Welcome(ad);
  
  }//main method
}