/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagementsystem;

/**
 *
 * @author b.villarini
 */
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class WestminsterHotelManager implements HotelManager {
    private ArrayList<HotelStaff> hotelStaffList;
    private ArrayList<Guest> hotelGuestList;
    private int staffLimit;

    public WestminsterHotelManager(int maxMembersNumber) {
        hotelStaffList = new ArrayList<>();
        hotelGuestList = new ArrayList<>();
        staffLimit = maxMembersNumber;
    }

    @Override
    public boolean runMenu() {
        boolean exit = false; // Exit flag
        System.out.println("\n-- HOTEL MANAGEMENT SYSTEM CONSOLE MENU--");
        System.out.println("To save and exit, press 0");
        System.out.println("To Add a new staff member, press 1");
        System.out.println("To Print the list of staff members press 2");
        System.out.println("To Add a guest, press 3");
        System.out.println("To Open GUI, press 4");
        System.out.println("To Save Staff to File, press 5");
        System.out.println("To Open Guest GUI, press 6");
        System.out.println("To Delete Staff by ID, press 7");
        System.out.println("To Search Staff by ID, press 8");
        System.out.println("To Calculate Total Guest Nights, press 9");
        System.out.println("To Sort Staff by Name (A-Z), press 10");
        System.out.println("To Save Guest List to File, press 11");
        System.out.println("To Load Guest List from File, press 12");
        System.out.println("To Count Staff by Role, press 13");
        System.out.println("To Show Guest with Most Nights, press 14");
        System.out.println("To Export Staff List to CSV, press 15");
        System.out.println("To Check Out Guest by Room Number, press 16");
        System.out.println("To Print All Guests, press 17");
        System.out.println("To View System Summary, press 18");
        System.out.println("To Search Guest by Room Number, press 19");
        System.out.println("To Generate Staff Report, press 20");
        System.out.println("To Sort Guest by Nights Stayed, press 21");
        System.out.println("To Show Staff Birthdays This Month, press 22"); 
        System.out.println("To View Room Type summary, press 23");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 0:
                exit = true;
                break;
            case 1:
                this.addHotelStaff();
                break;
            case 2:
                this.printHotelStaffList();
                break;
            case 3:
                this.addGuest();
                break;
            case 4:
                this.runGUI();
                break;
            case 5:
                this.saveStaffToFile();
                break;
            case 6:
                this.showGuestGUI();
                break;
            case 7:
                this.deleteStaffByID();
                break;
            case 8:
                this.searchStaffByID();
                break;
            case 9:
                this.totalGuestNights();
                break;
            case 10:
                this.sortStaffByName();
                break;
            case 11:
                this.saveGuestToFile();
                break;
            case 12:
                this.loadGuestFromFile();
                break;
            case 13:
                this.countStaffByRole();
                break;
            case 14:
                this.guestWithMostNights();
                break;
            case 15:
                this.exportStaffToCSV();
                break;
            case 16:
                this.checkOutGuest();
                break;
            case 17:
                this.printGuestList();
                break;
            case 18:
                this.displaySystemSummary();
                break;
            case 19:
                this.searchGuestByRoomNumber();
                break;
            case 20:
                this.generateStaffReport();
                break;
            case 21:
                this.sortGuestByNights();
                break;
            case 22:
                this.showBirthdaysThisMonth();
                break;
            case 23:
                this.showGuestRoomTypesSummary();
                break;

        }
        return exit;
    }

    @Override
    public void addHotelStaff() {
        Scanner s = new Scanner(System.in);
        if (hotelStaffList.size() < staffLimit) {
            System.out.println("Press 1 if you want to add a Manager");
            System.out.println("Press 2 if you want to add a Housekeeper");

            int choiceStaff = s.nextInt();
            s.nextLine();

            // Common questions
            System.out.println("Enter the first name");
            String name = s.nextLine();

            System.out.println("Enter the last name");
            String surname = s.nextLine();

            System.out.println("Enter the staff ID");
            String staffID = s.nextLine();

            System.out.println("Enter the date of birth (dd/MM/yyyy format only!)");
            boolean valid = false;
            String dob;

            LocalDate date = null;
//            boolean parsingSucceeds = false;
            while (!valid) {
                dob = s.nextLine();
                if (!isValidDateFormat(dob)) {
                    System.out.println("Invalid format! Use dd/MM/yyyy only.");
                    continue;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    date = LocalDate.parse(dob, formatter);
                    valid = true; // If parsing succeeds, the format is correct
                } catch (DateTimeParseException e) {
                    System.out.println("Enter the correct format. It should be dd/MM/yyyy!");
                    valid = false;
                }
            }
//            for(HotelStaff s : hotelStaffList){
//                if (s.getStaffID().equalsIgnoreCase(staffID)){
//                    System.out.println("Staff ID already exists. Please try again with a unique ID.");
//                    return;
//                }
//            }


            // Check if the staff is a manager or a guest
            switch (choiceStaff) {
                case 1:
                    // It is a manager
                    System.out.println("Enter the license number");
                    String licenseNum = s.nextLine();

                    // Create a new Manager and add to the list
                    Manager manager = new Manager(name, surname);
                    manager.setLicenseNumber(licenseNum);
                    manager.setDateOfBirth(date);
                    manager.setStaffID(staffID);
                    this.addStaffToList(manager);
                    break;

                case 2:
                    // It is a HouseKeeper
                    System.out.println("Enter the years of experience");
                    int yearsOfExperience = s.nextInt();
                    s.nextLine();
                    
                    
                    HouseKeeper housekeeper = new HouseKeeper(name, surname);
                    housekeeper.setYearsOfExperience(yearsOfExperience);
                    housekeeper.setDateOfBirth(date);
                    housekeeper.setStaffID(staffID);
                    this.addStaffToList(housekeeper);
                    break;
                    
            }
        } else {
            System.out.println("No more space in the system");
        }


    }

    public void addStaffToList(HotelStaff staff) {
        if (this.hotelStaffList.size() < staffLimit) {
            hotelStaffList.add(staff);
        } else {
            System.out.println("No more space in the list");
        }
    }

    @Override
    public void printHotelStaffList() {
        if (!hotelStaffList.isEmpty()) {
            for (HotelStaff member : hotelStaffList) {
                System.out.println(member.toString());
            }
        } else {
            System.out.println("There are no staff members in the system.");
        }
    }
    
     @Override
    public void addGuest() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the first name");
        String name = s.nextLine();

        System.out.println("Enter the last name");
        String surname = s.nextLine();

        System.out.println("Enter the room number");
        int roomNum = s.nextInt();
        s.nextLine();

        System.out.println("Enter the number of nights stayed");
        int nightsStayed = s.nextInt();
         s.nextLine();
         
         Guest guest = new Guest(name, surname);
         guest.setRoomNumber(roomNum);
         guest.setNightsStayed(nightsStayed);
         
         hotelGuestList.add(guest);
    }

    @Override
    public void runGUI() {
        HotelTableGUI table = new HotelTableGUI(hotelStaffList);
        table.setVisible(true);
    }

    public void saveStaffToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("staff.txt"))){
            for(HotelStaff staff : hotelStaffList){
                writer.write(staff.toString());
                writer.newLine();
            }
            System.out.println("Staff list saved successfully.");

        }catch(IOException e){
            System.out.println("Error saving staff list: "+ e.getMessage());

        }
    }

    public void showGuestGUI(){
        GuestTableGUI guestGUI = new GuestTableGUI(hotelGuestList);
        guestGUI.setVisible(true);
    }

    public void deleteStaffByID(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Staff ID to delete:");
        String id= s.nextLine();
        boolean found = false;
        for (HotelStaff staff : hotelStaffList){
            if(staff.getStaffID().equalsIgnoreCase(id)){
                hotelStaffList.remove(staff);
                System.out.println("Staff removed successfully.");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Staff ID not found.");
        }
    }

    public void searchStaffByID(){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Staff ID to Search:");
        String searchID = s.nextLine();

        boolean found = false;
        for (HotelStaff staff : hotelStaffList){
            if(staff.getStaffID().equalsIgnoreCase(searchID)){
                System.out.println("Staff found:");
                System.out.println(staff.toString());
                found =  true;
                break;
            }
        }

        if(!found){
            System.out.println("No staff member found with that ID.");
        }
    }

    public void totalGuestNights(){
        int totalNights = 0;
        for(Guest guest : hotelGuestList){
            totalNights += guest.getNightsStayed();
        }
        System.out.println("Total number of nights stayed by all guests: "+ totalNights);
    }

    public void sortStaffByName(){
        Collections.sort(hotelStaffList, Comparator.comparing(HotelStaff::getName));
        System.out.println("Staff list sorted by name(A-Z).");
    }

    public void saveGuestToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("guest.txt"))){
            for(Guest guest : hotelGuestList){
                writer.write(guest.getName() +","+ guest.getSurname() + ","+ guest.getRoomNumber()+","+guest.getNightsStayed());
                writer.newLine();
            }
            System.out.println("Guest list saved successfully.");

        }catch(IOException e){
            System.out.println("Error saving guest list: "+ e.getMessage());

        }
    }

    public void loadGuestFromFile(){
        try(BufferedReader reader=new BufferedReader(new FileReader("guests.txt"))){
            String line;
            while((line = reader.readLine())!= null){
                String[] data= line.split(",");
                if(data.length == 4){
                    Guest guest = new Guest(data[0], data[1]);
                    guest.setRoomNumber(Integer.parseInt(data[2]));
                    guest.setNightsStayed(Integer.parseInt(data[3]));
                    hotelGuestList.add(guest);
                }
            }
            System.out.println("Guest list loaded successfully.");

        }catch(IOException e){
            System.out.println("Error loading guest list: "+ e.getMessage());

        }
    }

    public void countStaffByRole(){
        int managerCount = 0;
        int houseKeeperCount = 0;

        for(HotelStaff s: hotelStaffList){
            if(s instanceof Manager)managerCount++;
            else if(s instanceof HouseKeeper) houseKeeperCount++;
        }

        System.out.println("Total Managers: "+ managerCount);
        System.out.println("Total Housekeepers: "+ houseKeeperCount);
    }

    public void guestWithMostNights(){
        if(hotelGuestList.isEmpty()){
            System.out.println("No guests in the system.");
            return;
        }
        Guest topGuest= hotelGuestList.get(0);

        for(Guest guest:hotelGuestList){
            if(guest.getNightsStayed()>topGuest.getNightsStayed()){
                topGuest = guest;
            }
        }
        System.out.println("Guest with most nights stayed:");
        System.out.println("Name:"+ topGuest.getName()+" "+topGuest.getSurname());
        System.out.println("Room Number: "+ topGuest.getRoomNumber());
        System.out.println("Nights Stayed:"+ topGuest.getNightsStayed());
    }

    public void exportStaffToCSV(){
        try(PrintWriter writer = new PrintWriter("staff_export.csv")){
            writer.println("Name,Surname,DOB,Role");
            for(HotelStaff staff: hotelStaffList){
                String role = staff instanceof Manager? "Manager" : "HouseKeeper";
                writer.printf("%s,%s,%s,%s,%s,%s\n", staff.getName(), staff.getSurname(), staff.getStringDate(), role);
            }
            System.out.println("Staff expected to staff_export.csv successfully.");
        }catch(IOException e){
            System.out.println("Error exporting staff to CSV: "+ e.getMessage());
        }
    }

    public void checkOutGuest(){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Room Number to Check-Out Guest:");
        int roomNum = s.nextInt();

        boolean found = false;
        for(Guest guest : hotelGuestList){
            if(guest.getRoomNumber() == roomNum){
                hotelGuestList.remove(guest);
                System.out.println("Guest checked out from Room "+roomNum);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No guest found in Room "+roomNum);
        }
    }

    public void printGuestList(){
        if(hotelGuestList.isEmpty()){
            System.out.println("No Guests available.");
            return;
        }
        for(Guest guest : hotelGuestList){
            System.out.println(guest.getName() + " " + guest.getSurname() + "-Room:" + guest.getRoomNumber() + "-Nights: " + guest.getNightsStayed());
        }
    }

    public boolean isValidDateFormat(String dateStr){
        return dateStr.matches("\\d{2}/\\d{2}/\\d{4}}");
    }

    public void displaySystemSummary(){
        System.out.println("----SYSTEM SUMMARY----");
        System.out.println("Total Staff:" + hotelStaffList.size());
        System.out.println("Total Guests:" + hotelGuestList.size());

    }

    public void searchGuestByRoomNumber(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Room Number: ");
        int roomNum = s.nextInt();

        boolean found = false;
        for(Guest g : hotelGuestList){
            if(g.getRoomNumber() == roomNum){
                System.out.println("Guest Found: " + g.getName() + " " +g.getSurname());
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No guest in Room "+roomNum);
        }
    }

    public void displayHotelInfo(){
        System.out.println("\n====================================");
        System.out.println(" WELCOME TO WESTMINSTER HOTEL SYSTEM ");
        System.out.println(" Developed by Your Name - Student ID ");
        System.out.println("====================================\n");
    }

    public void generateStaffReport(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("staff_report.txt"))){
            writer.write("====STAFF REPORT====\n");
            for(HotelStaff staff : hotelStaffList){
                writer.write(staff.toString()+"\n");
            }
            writer.write("===============================");
            System.out.println("Report generated as staff_report.txt");

        }catch(IOException e){
            System.out.println("Failed to write report: "+ e.getMessage());
        }
    }

    public void sortGuestByNights(){
        hotelGuestList.sort((g1,g2)->Integer.compare(g2.getNightsStayed(), g1.getNightsStayed()));
        System.out.println("Guests sorted by Nights stayed (descending).");
    }

    public void showBirthdaysThisMonth(){
        int currenetMonth = LocalDate.now().getMonthValue();
        boolean any = false;
        System.out.println("Staff birthdays this month:");
        for (HotelStaff staff : hotelStaffList){
            if (staff.getDateOfBirth().getMonthValue() == currenetMonth){
                System.out.println(staff.getName() + " " + staff.getSurname() + "-" + staff.getStringDate());
                any = true;
            }
        }
        if(!any){
            System.out.println("No birthdays this month.");
        }
    }

    public void showGuestRoomTypesSummary(){
        int standard = 0,deluxe = 0, suite=0;
        for (Guest g : hotelGuestList){
            int room = g.getRoomNumber();
            if(room<=50)standard++;
            else if(room<=75)deluxe++;
            else suite++;
            
        }
        System.out.println("Room Type Summary:");
        System.out.println("Standard Rooms (1-50):"+standard);
        System.out.println("Deluxe Rooms (51-75): " + deluxe); 
        System.out.println("Suite Rooms (76-100): " + suite); 
    }
}