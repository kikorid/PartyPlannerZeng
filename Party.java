/**
 * This is the Party files.
 * It contains two function: loadAndRunProgram and makePeople.
 * First, the loadAndRunProgram will load the csv files called DataPeople and make it into a string array. Then, it will prompt the makePeople to make an arrayList of Person Objects.
 * The loadAndRunProgram also prompt the user for functionalities like registering new guest etc.
 * 
 * @author Julia Zeng, used W3School reference for importing files
 * @version 1.0
 * @since 01.19.2023
 * 
 * Preconditions: If the questions that prompts for the users require integer answer, the answer can only be integer. There is no error checking for that. It might be updated in the future. 
 */

import java.io.*;
import java.util.*;

/*
Read the javadoc for understanding the Party Class. 
*/
public class Party {
  private String[] list;
  boolean quit = false; //for the questions
  Scanner scan = new Scanner(System.in);

/* function loadAndRunProgram
The file reading is based on W3school. This is the link: https://www.w3schools.com/java/java_files_read.asp

1. The function will gather a list of data and call the makePeople fuction and store the person objects in the arrayList

2. It also prompt uses for the following actions: Register Guest, print the tables, print a specfic table, print company, search attendee

*/
  public void loadAndRunProgram(int size) {
    int tableSize = size;
     ArrayList<Person> peopleList = new ArrayList<Person>(); 

    //based on w3school, check the multiline comment for extra information
     try {
      File myObj = new File("DataPeople.csv");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        //take all the stuff
        String data = myReader.nextLine();
        list = null;
        //make a list and divide it by comma
        list = data.split(",");  
        //call the makePeople function and send the list into it
        //then add the person objects from the list into the arraylist
        peopleList.add(makePeople(list));
      }
        
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    

    /* This

check user amounts (10/tablemax, 100 max total, etc)
place users at table
print 'rosters' by table
print rosters by company
Add getter functionality to 'find' a person and report what table they are at

*/
    while(quit == false){
      //prompt a bunch of actions
      System.out.print("\n");
      
      System.out.println("Welcome! What do you want to do? Register Guest(1), print the whole table(2), print specific table(3), print company(4), search attendee(5), quit(6)");
    int ans = scan.nextInt();
    if(ans==1){
      //enroll or register a person
      //add more people into the array list
      System.out.println("");
      System.out.print("Great! Enter the Last Name of your person: ");
      //clean the buffer
      String temp = scan.nextLine();
      String lastName = scan.nextLine();
      System.out.println("");
      
      System.out.print("Please enter the First Name of your person: ");
      String firstName = scan.nextLine();
      System.out.println("");
      System.out.println("This is a list of companies with their number:" + "\n");

      //provide the list of markets
      System.out.println("1,Wal-Mart" + "  " + "2,Kroger" + "  " + "3,Amazon" + "  " + "4,Lowes"+ "  "+ "5,Best Western" + "  " + "6,KMart" + "  " + "7,Fusian" + "  "+ "8,Heinz" + "  "+ "9,Gucci" +  "  " + "10,Prada" + "  " + "11,Nike" + "  "+ "12,Dodge"+ "  "+ "13,Maserati" + "  " + "14,Razor" + "  " + "15,AMD" + "  "+ "16,Razer"+ "\n");
      //it works if the user is using the integers
      System.out.print("Please enter the Company Number of your person: ");
      int CompanyNum = scan.nextInt();
     // System.out.print(lastName + firstName + CompanyNum);

      //it will add a person object into the arraylist
      boolean added = false;
      boolean peopleCap = false;
      boolean tableCap = false;
      int counter = 0;

      //for loop to find how many people are in one company
      for(int i =0; i< peopleList.size(); i++){
        if (peopleList.get(i).getComp() == CompanyNum){
          counter++;
        }
        
      }

      //making sure everyworks
      if(peopleList.size() > 99){
        peopleCap= true;
      }else if (counter > 9){
        tableCap = true;
      }else{
        added= true;
        
      }
      
      if(added == true){
         peopleList.add(new Person(lastName,firstName,CompanyNum));
         System.out.println("Guest Added!");
      }else if (peopleCap == true){
        System.out.println("You can't add more people in the list, becauser there are 100 people already!");
      }else if (tableCap == true){
        System.out.println("You can't add more people to this company anymory! There are too much of them");
      }
     

    }else if(ans ==2){
      //it prints the whole table
      Person[][] tables = Person.groupIntoTables(peopleList,tableSize);
      System.out.print("\n");
      //it calls the Person file and find the printTable methods, which allows it to print the table
      Person.printTables(tables);
    }else if(ans == 3){
      //it prints the table based on a number
      Person[][] tables = Person.groupIntoTables(peopleList,tableSize);
      System.out.print("\n" + "Yeah! Enter the table number you want to print: ");
      int tableNum = scan.nextInt();
      //a little error check, but i gave up in the end
      if(tableNum <11 && tableNum >0){
        System.out.print("\n");
        //it calls the Person file and the printSpecificTables for the table input 
        Person.printSpecificTables(tables,tableNum);
      }else{
        System.out.print("There are only ten tables... ");
      }
      
      
    }else if(ans ==4){
      //it will print the company roster
      System.out.println("");
      System.out.println("This is a list of companies with their number:" + "\n");

      //provide the list of markets
      System.out.println("1,Wal-Mart" + "  " + "2,Kroger" + "  " + "3,Amazon" + "  " + "4,Lowes"+ "  "+ "5,Best Western" + "  " + "6,KMart" + "  " + "7,Fusian" + "  "+ "8,Heinz" + "  "+ "9,Gucci" +  "  " + "10,Prada" + "  " + "11,Nike" + "  "+ "12,Dodge"+ "  "+ "13,Maserati" + "  " + "14,Razor" + "  " + "15,AMD" + "  "+ "16,Razer"+ "\n");
      Person[][] tables = Person.groupIntoTables(peopleList,tableSize);
      
      System.out.print("\n" +"Enter the company number you want to print: ");
      int compNum = scan.nextInt();
      //it will run the arrayList and match the right company number
      Person.printCompanyRoster(tables,compNum);
      
      
    }else if(ans ==5){
      //Add getter functionality to 'find' a person and report what table they are at
      boolean personYes = false;
      Person[][] tables = Person.groupIntoTables(peopleList,tableSize);
      System.out.print("\n" + "Hello! Enter the name you would like to search (FirstName LastName): ");
      String temp = scan.nextLine();
      String ansName = scan.nextLine();
      //loops to find the name 
      System.out.println("");
      for(int i =0; i< tables.length;i++){
        for(int j=0; j< tables[i].length;j++){
          Person p = tables[i][j];
          if (p != null) {
            //if the name are the same, it will prints 
            if(p.getName().equals(ansName)){
              System.out.println(ansName);
              System.out.print("The table number: " + (i+1) +"\n" + "The seat number: " + (j+1));
              personYes= true;
              break;
            }
          }
        }//end of the j forloop
      }//end of i forloop
      if(personYes== false){
        System.out.print("\n" + "Person does not exist");
      }
      System.out.print("\n");
      
      
    }else if(ans==6){
      //end the loop
      quit= true;
    }
      
   }//end of the while loop

}

  /*
  it will make person objects and return the objects to another function
  */
  public Person makePeople(String[] plist){
   // int size = plist.length();
    Person c1 = new Person(plist[1], plist[2], Integer.parseInt(plist[3]));
    return c1;

    
  }
}
