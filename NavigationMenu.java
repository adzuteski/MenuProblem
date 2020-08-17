package navigationmenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Antonio
 */
public class NavigationMenu6 {

    static final String URL = "C:\\Users\\Antonio\\Desktop\\excercise\\Nav1.csv";
    
    public static void main(String[] args) {
        //read and sort the Menu
        Map<String, String> readMenu = (TreeMap<String, String>) readFromCSV();
         //Display Map alphabeticly in depth
        displayMenu((TreeMap) readMenu);
} 
    static Map<String, String>readFromCSV() {
      Map<String, String> formatedMap = new TreeMap<>();
      
	try(BufferedReader br = new BufferedReader(new FileReader(URL))) {
	    String line;
	    while ((line = br.readLine()) != null) {
	        String[] navMenu = line.split(";");
                //dont't read hidden
                if(navMenu[3].equals("False")){
                    //create a sorted Map of LinkUrl for sorting and comparing
                    //Value of Name need for printing the visual element
                    formatedMap.put(navMenu[4],navMenu[1]);    
                }
            }    
	}catch(FileNotFoundException e){
        System.out.println(e);
        }catch(IOException e){
        System.out.println(e);
        }
        
        return formatedMap;
    }
    
    static void displayMenu(TreeMap mapForDysplay){
       //declare a Map
       TreeMap<String,String> sortedMap = mapForDysplay;
       //some variables for calculation
       String str= ".";
       String str1 = "...";
       int i = 0;
       String firstKey = (String) sortedMap.firstEntry().getKey();
       ArrayList<String>indexList = new ArrayList<>(); //for finding the previos element
       
       //printing Menu
        for (String key : sortedMap.keySet()){
            indexList.add(key); //add the element
            String actualKey = indexList.get(i);
            String prevKey;//
         //Check to avoid ArrayIndexOutOfBounds   
            if(i != 0){
                prevKey =  indexList.get(i - 1) ;
            }else{
                prevKey = indexList.get(0); // prev Element is first Element of Iteration
            }
            //Adding the Parent Element with children in depth
            if(key.contains(prevKey) ){  // && key.contains(firstKey)
            System.out.println(str + sortedMap.get(key));
            str1 = "...";
            str = str + str1;
            i++;  //adding the new index to actual key
            //continue;//try again and  if have more subelemtns the last element is Father element
            }
            //Find the First level subElements
            else if(key.contains(firstKey)){
            String str2 = "...";
            System.out.println("." + str2 + sortedMap.get(key));
            str = "." + str2 + str2;
            i++;  //adding the new index to actual key
            }
            //Find the next first level Element(Parent)
            else if(!key.equals(firstKey)){
            str = ".";
            System.out.println(str + sortedMap.get(key));//print the first level element
            firstKey = actualKey; //needded for finding subelements of fisrst level element
            str = str + str1;
            i++;  //adding the new index to actual key
            }else{
            //throw new Exception  but for simplicity print
            System.out.println("Something go wrong, read the file and try again");
            } 
        }
    }    
}
