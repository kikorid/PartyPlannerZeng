/**
 * This is the Person file.
 * It contains two function: groupIntoTables and printTables and printSpecificTables.
 * First, the groupIntoTables will arrange the people to sit different tables. In each table, there can not be more than one person in one table.
 * The printTables and printSpecificTables are only for answering the questions. 
 * 
 * @author Julia Zeng
 * @version 1.0
 * @since 01.19.2023
 * 
 */

import java.util.ArrayList;

public class Person {
  //people object variable
  private String name;
  private int comp;
  private String compName;

  //array of companies that match the company id with the company
  static String[] arr= {"Wal-Mart","Kroger","Amazon","Lowes","Best Western","KMart","Fusian","Heinz","Gucci","Prada","Nike","Dodge","Maserati","Razor","AMD","Razer"};

  public Person(String lastName, String firstName, int company) {
    name = firstName + " " + lastName;
    comp = company;
    compName = "";

  }
/* groupIntoTables functions
it creates a 2d array with the data type Person-> The table number is the table Index-> they increase each time i run the for each loop
Then it checks at each table if the people does not repeat-> add a person object at the array

*/

  public static Person[][] groupIntoTables(ArrayList<Person> people) {
    //create an Person array table 10x 10
        Person[][] tables = new Person[10][10];
    //tableIndex represents the colu
        int tableIndex = 0;// the row
        for (Person p : people) { //for each loops in the arraylist
          //for 
            boolean added = false; //adding more people at a table
          //less than ten tables
            for (int i = 0; i < 10; i++) {
                if (tableIndex == 10) {
                    tableIndex = 0; //clear after one row
                }
              //the table num
                Person[] table = tables[tableIndex];
                boolean sameCompany = false; //if they are in the same company
              //for each tp in the table array 
              for (Person tp : table) {
                //if its not null and the company is the same as the people's list
                    if (tp != null && tp.comp == p.comp) {
                        sameCompany = true;
                        break;
                    }
                }
                if (!sameCompany) { //if they are not in the same company 
                  //issue1: since the tableIndex increase each time
                  
                    for (int j = 0; j < 10; j++) {
                        if (table[j] == null) {
                            table[j] = p; //add a person object
                          //if its not the same company you add
                            added = true;
                            break;
                        }
                    }
                }
                if (added) {
                    break;
                }
                tableIndex++;
            }

        }//end of for each loop for person 
        return tables;
    }

/*
the printable prints an array of person objects 
it will run in another file
*/

    public static void printTables(Person[][] tables) {
        for (int i = 0; i < tables.length; i++) {
            System.out.println("Table " + (i + 1) + ":");
            for (int j = 0; j < tables[i].length; j++) {
              //it declares a p variable-> with arr i and J
                Person p = tables[i][j];
                if (p != null) { //print the people at each table
                    System.out.println("Name: " + p.name + ", Company Name: " + arr[p.comp-1]);
                }
            }
            System.out.println();
        }
    }

/*
the printable prints an array of person objects 
it will run in another file
*/  
    public static void printSpecificTables(Person[][] tables,int num) {
       System.out.println("Table " + num + ":");
            for (int j = 0; j < tables[num-1].length; j++) {
              //it declares a p variable-> with arr i and J
                Person p = tables[num-1][j];
                if (p != null) { //print the people at each table
                    System.out.println("Name: " + p.name + ", Company Name: " + arr[p.comp-1]);
                }
            }
            System.out.println();
    }



  
  // getter files because the data are private
  public String getName() {
    return name;
  }

  public int getComp() {
    return comp;
  }

//not rly needed 
  public String toString(){
    return "name: " + name + "; Company: " + comp + "\n";
  }

}



