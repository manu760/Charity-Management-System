
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class ApplicationRunner {
    
    public static RunEntry runEntry;
    public static void init(){
        runEntry = new RunEntry();  
        String filePath = System.getProperty("user.dir") + File.separator + "run.txt";
        ArrayList<Competitor> comList = null;
        Scanner input = null;
        int index = 0;
        try{
           input = new Scanner(new File(filePath));           
           while(input.hasNext()){
               String str = input.nextLine();
               String[] temp = str.split(";");
               boolean flag = false;
               if (temp[0].compareTo("Half Marathon") == 0)
                   flag = false;
               else
                   flag = true;
               if (flag){
                   FiveKmRun five = new FiveKmRun();
                   five.setDate(temp[4]);
                   five.setStartTime(temp[5]);
                   Park park = new Park();
                   park.setName(temp[1]);
                   park.setNumChangingFacilities(Integer.parseInt(temp[3]));
                   five.setPark(park);     
                   five.setFlag(true);
                   if (temp[6].compareTo("ON") == 0)
                       five.setState(true);
                   else
                       five.setState(false);
                   runEntry.addEvent(five);                   
                   index++;
               }
               else{
                   HalfMarathon half = new HalfMarathon();
                   half.setDate(temp[4]);
                   half.setStartTime(temp[5]);
                   int water = Integer.parseInt(temp[2]);
                   int change = Integer.parseInt(temp[3]);
                   
                   if (change == -1){
                       Town town = new Town();
                       town.setName(temp[1]);                       
                       town.setFlag(true);
                       half.setVenue(town);
                       half.setWaterFacilities(water);
                   }
                   else
                   {
                       Park park = new Park();
                       park.setName(temp[1]);
                       park.setNumChangingFacilities(change);                       
                       park.setFlag(false);
                       half.setVenue(park);    
                       half.setWaterFacilities(water);
                   }
                   if (temp[6].compareTo("ON") == 0)
                       half.setState(true);
                   else
                       half.setState(false);
                   half.setFlag(false);
                   runEntry.addEvent(half);                   
                   index++;
               }
               comList = new ArrayList<Competitor>();
               while(true){
                    String line = input.nextLine();
                    if (line.contains("---------"))
                        break;
                    String[] tmp = line.split(";");
                    if (Integer.parseInt(tmp[1]) < 16 && runEntry.getEvents().get(index - 1).getFlag() == false){
                        System.out.printf("%s is under 16 so he couldn't be added to the half marathon competitor's list\n\n",tmp[0]);
                        continue;
                    }       
                    boolean tempFlag = true;
                    for(int i = 0;i < comList.size();i++)
                        if (comList.get(i).getName().compareTo(tmp[0]) == 0){
                            tempFlag = false;
                            System.out.printf("%s shouldn't be allowed to enter this event again\n",tmp[0]);
                            break;
                        }
                    if (tempFlag == false)
                        continue;
                    Competitor com = new Competitor();
                    
                    com.setAge(Integer.parseInt(tmp[1]));
                    com.setName(tmp[0]);
                    com.setEventNo(index - 1);
                    comList.add(com);
               }
               runEntry.getEvents().get(index - 1).setCompetitor(comList);
           }
        }catch(Exception e){
            
        }
        runEntry.getEvents().get(index - 1).setCompetitor(comList);
        
    }
    public static void ListEvent(){        
        ArrayList<CharityRun> list = runEntry.getEvents();
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("|\tNo\t|\tCharity Run\t|\tDate\t\t|\tVenue\t\t");
        for(int i = 0;i < list.size();i++){
            System.out.println("--------------------------------------------------------------------------------------------");
            if (list.get(i).getFlag()){
                FiveKmRun five = (FiveKmRun)list.get(i);
                System.out.print("|\t" + (i + 1) + "\t|\tFive Km Run\t|\t" + five.getDate() + "\t|\t" + five.getPark().getName() + "\t\n");                
            }                
            else{
                HalfMarathon half = (HalfMarathon)list.get(i);
                System.out.print("|\t" + (i + 1) + "\t|\tHalf Marathon\t|\t" + half.getDate() + "\t|\t" + half.getVenue().getName() + "\t\n");
            }                
        }
        System.out.println();
        System.out.print("select event from the list:>");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            String str = scanner.nextLine();
            option = Integer.parseInt(str);
            if (option < 1 || option > list.size()){
                System.out.println("You should input integer between 1 and " + list.size());
                System.out.println();
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("You should input integer");
            System.out.println();
            return;
        }           
        option--;
        System.out.println();     
        System.out.println("----------------------------------------------------");
        if (list.get(option).getFlag()){
            FiveKmRun five = (FiveKmRun)list.get(option);                
            System.out.println("Event's venue: " + five.getPark().getName());     
            System.out.println("the current number of entries for that event: " + five.getCompetitor().size());
        }
        else{
            HalfMarathon half = (HalfMarathon)list.get(option);                
            if (!half.getVenue().getFlag()){
                Park park = (Park)half.getVenue();
                System.out.println("Event's venue: " + park.getName());
                System.out.println("the current number of entries for that event: " + half.getCompetitor().size());
                System.out.println("number of changing facilities: " + park.getNumChangingFacilities());
                System.out.println("number of water facilities: " + half.getWaterFacilities());
            }
            else{
                Town town = (Town)half.getVenue();
                System.out.println("Event's venue: " + town.getName());
                System.out.println("the current number of entries for that event: " + half.getCompetitor().size());
                System.out.println("number of water facilities: " + half.getWaterFacilities());
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.println();       
        
    }
    public static void ListVenue(){        
       ArrayList<String> venueList = new ArrayList<String>();
       ArrayList<CharityRun> list = runEntry.getEvents();     
        
       for(int i = 0; i < list.size();i++){
           if (list.get(i).getFlag()){
               FiveKmRun five = (FiveKmRun)list.get(i);
               String str = five.getPark().getName();
               boolean flag = true;
               for(int j = 0;j < venueList.size();j++){
                   if (str.compareTo(venueList.get(j)) == 0){
                       flag = false;
                       break;
                   }                    
               }
               if (flag)
                   venueList.add(str);
           }
           else{
               HalfMarathon half = (HalfMarathon)list.get(i);
               String str = half.getVenue().getName();
               boolean flag = true;
               for(int j = 0;j < venueList.size();j++){
                   if (str.compareTo(venueList.get(j)) == 0){
                       flag = false;
                       break;
                   }                    
               }
               if (flag)
                   venueList.add(str);
           }          
       }
       System.out.println();
       
       System.out.println("-------------------------------------------");
       System.out.println("|\tNo\t|\tVenue\t\t");
       for(int i= 0 ;i < venueList.size();i++){
            System.out.println("|\t" + (i + 1) + "\t|\t" + venueList.get(i) + "\t");   
       }           
       System.out.println("-------------------------------------------");
       
       System.out.println();
       System.out.print("'select venue from the list:>");
       Scanner scanner = new Scanner(System.in);
       int option = 0;
       try {
           String str = scanner.nextLine();
           option = Integer.parseInt(str);
           if (option < 1 || option > venueList.size()){
               System.out.println("You should input integer between 1 and " + venueList.size());
               System.out.println();
               return;
           }

       } catch (NumberFormatException e) {
           System.out.println("You should input integer");
           System.out.println();
           return;
       }
       option--;
       System.out.println();
       String str = venueList.get(option);
       int index = 0;
       boolean tempFlag = false;
       for(int j = 0; j < list.size();j++){
           if (list.get(j).getFlag()){
                FiveKmRun five = (FiveKmRun)list.get(j);
                if (str.compareTo(five.getPark().getName()) == 0 && five.getState() == true){
                    System.out.println("-------------------------------------------");
                    System.out.println("Event:\t" + (index + 1));
                    System.out.println("starttime:\t" + five.getStartTime());
                    System.out.println("date:\t" + five.getDate());
                    System.out.println("Number of changing facilities:\t" + five.getPark().getNumChangingFacilities());   
                    System.out.println("-------------------------------------------");
                    System.out.println();     
                    tempFlag = true;
                    index++;
                }
           }    
           else{
                HalfMarathon half = (HalfMarathon)list.get(j);
                if (str.compareTo(half.getVenue().getName()) == 0 && half.getState() == true){
                    if (half.getVenue().getFlag()){
                        Town town = (Town)half.getVenue();
                        System.out.println("-------------------------------------------");
                        System.out.println("Event:\t" + (index + 1));
                        System.out.println("starttime:\t" + half.getStartTime());
                        System.out.println("date:\t" + half.getDate());
                        System.out.println("Number of water facilities:\t" + half.getWaterFacilities());
                        System.out.println("-------------------------------------------");
                        System.out.println();
                        tempFlag = true;
                        index++;
                    }
                     else{
                        Park park = (Park)half.getVenue();
                        System.out.println("-------------------------------------------");
                        System.out.println("Event:\t" + (index + 1));
                        System.out.println("starttime:\t" + half.getStartTime());
                        System.out.println("date:\t" + half.getDate());
                        System.out.println("Number of water facilities:\t" + half.getWaterFacilities());
                        System.out.println("Number of changing facilities:\t" + park.getNumChangingFacilities());
                        System.out.println("-------------------------------------------");
                        System.out.println();
                        tempFlag = true;
                        index++;
                    }
                }               
          }          

        }        
       if (tempFlag == false)
           System.out.println("There are no events taking place here");
       System.out.println();
    }
    public static void Search(String search){    
       search = search.toUpperCase();
       ArrayList<CharityRun> list = runEntry.getEvents();     
       ArrayList<Competitor> comList = new ArrayList<Competitor>();
       comList.clear();
       for(int i = 0; i < list.size();i++){
           ArrayList<Competitor> temp = list.get(i).getCompetitor();
           for(int j = 0;j < temp.size();j++){
               boolean flag = true;               
               for(int k = 0;k < comList.size();k++)
                   if (temp.get(j).getName().compareTo(comList.get(k).getName()) == 0 && temp.get(j).getAge() == comList.get(k).getAge()){
                       flag = false;
                       break;
                   }
               if (flag){
                   comList.add(temp.get(j));
                   //System.out.println(temp.get(j).getName() + "  " + temp.get(j).getAge());
               }                  
           }
       }
       System.out.println();
       for(int i = 0; i < comList.size();i++){  
           String searchName = comList.get(i).getName().toUpperCase();
           if (!searchName.contains(search.toUpperCase()))
               continue;
           searchName = comList.get(i).getName();
           System.out.println("Name: " + searchName + "\tAge: " + comList.get(i).getAge() + "\n");                      
           for(int j = 0;j < list.size();j++){
               ArrayList<Competitor> com = list.get(j).getCompetitor();               
               for(int k = 0;k < com.size();k++){
                    String name = com.get(k).getName();   
                    if (name.compareTo(searchName) == 0){
                        System.out.println("Event: " + (j + 1));
                        if (list.get(j).getFlag()){
                            FiveKmRun five = (FiveKmRun)list.get(j);
                            System.out.println("Event's venue: " + five.getPark().getName());
                        }
                        else{
                            HalfMarathon half = (HalfMarathon)list.get(j);
                            System.out.println("Event's venue: " + half.getVenue().getName());
                        }                        
                        if (list.get(j).getFlag())
                            System.out.println("Event date:" + list.get(j).getDate());
                        else
                            System.out.println("Event date:" + list.get(j).getDate());
                        System.out.println("the event number the competitor has been allocated for that event. :" + (k + 1));
                        if (list.get(j).getFlag())
                            System.out.println("Five Km Run");
                        else
                            System.out.println("Half Matathon");
                        System.out.println("-------------------------------------------");
                    }
               }
           }
       }            
//       }
       System.out.println();
       System.out.println();
    }
    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1. List Event Information.");
            System.out.println("2. List Venue Detail.");
            System.out.println("3. Search Competitor's Event Entries.");
            System.out.println("4. Exit the program");
            System.out.print("Select Option: >");
            String str = scanner.nextLine();
            int option = 0;
            try {
                option = Integer.parseInt(str);
                if (option < 1 || option > 4){
                    System.out.println("You should input integer between 1 and 4");
                    continue;
                }
                    
            } catch (NumberFormatException e) {
                System.out.println("You should input integer");
                continue;
            }
            
            String search = "";
            switch(option){
                case 1:
                    ListEvent();
                    break;
                case 2:
                    ListVenue();
                    break;                    
                case 3:
                    System.out.print("Input the search word: >");
                    search = scanner.nextLine();
                    Search(search);
                    break;
                    
                case 4:                        
                    return;
                  
            }
        }
        
    }
    
}
