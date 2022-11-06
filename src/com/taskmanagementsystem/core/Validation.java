package com.taskmanagementsystem.core;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class helps with the action of checking or proving the validity or accuracy of a certain operation.
 */
public class Validation {

    public static String inputStringValidation()
    {
        /**
         * Ensure that user adds a full name only using regex
         */
        Scanner input = new Scanner(System.in);
        boolean isValid;
        String re = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
        String text = "";

        do{
            text = input.nextLine();
            Pattern pt = Pattern.compile(re);
            Matcher mt = pt.matcher(text);

            if(mt.matches())
            {
                isValid = true;
            }
            else {
                System.out.println("Invalid input, Please enter letters of full name only. Example: Sara nasser");
                isValid = false;
                System.out.print("Full Name:");
            }
        }while(!isValid);

        return text;
    }

    public static char inputRoleValidation()
    {
        Scanner input = new Scanner(System.in);
        boolean isValid;
        char text ;

        do{
            text = input.next().charAt(0);

            if(text == 'M' || text == 'm' || text == 'A' || text == 'a' ) // error
            {
                isValid = true;
            }
            else {
                System.out.println("Invalid input, Please enter (A OR M), only!");
                isValid = false;
                input.nextLine();
                System.out.print("Are you (A - Admin, M - Member):");
            }
        }while(!isValid);

        return text;
    }

    /**
     * Ensure that user adds correct email format using regex
     */
    public static String emailFormatValidation()
    {
        Scanner input = new Scanner(System.in);
        boolean isValid;
        String re = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        String text = "";


        do{
            text = input.nextLine();
            Pattern pt = Pattern.compile(re);
            Matcher mt = pt.matcher(text);

            if(mt.matches())
            {
                isValid = true;
            }
            else {
                System.out.println("Invalid input, make sure to write your email in this format (something only letters)@(some_domain only letter).(some_toplevel_domain only letter and less then 4)"); // Simple email expression. Doesn't allow numbers in the domain name and doesn't allow for top level domains that are less than 2 or more than 3 letters
                isValid = false;
                System.out.print("Email:");
            }

        }while(!isValid);

        return text;
    }

    /**
     * Ensure that user adds correct password format using regex
     */
    public static String passwordFormatValidation() {
        Scanner input = new Scanner(System.in);
        boolean isValid;
        String re = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        String text = "";

        do{

            text = input.nextLine();
            Pattern pt = Pattern.compile(re);
            Matcher mt = pt.matcher(text);

            if(mt.matches())
            {
                isValid = true;
            }
            else {
                System.out.println("Password must have at least 8 characters that include at least 1 lowercase character, 1 uppercase character, and 1 special character in (!@#$%^&*)");
                isValid = false;
                System.out.print("Password:");
            }

        }while(!isValid);

        return text;
    }


    public static boolean isEmailRegistered(ArrayList<Admin> admin, String email) {
        for (Admin value : admin) {
            if (value.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPreviousMember(String email) {
        for (String value : Main.deactivatedMembers) {
            if (value.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailRegistered(String email, ArrayList<Member> member) {
        for (Member value : member) {
            if (value.getEmail().equals(email))
            {
                return true;
            }
        }
        return false;
    }

    public static int intValidation()
    {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;;

        do{
            if(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please enter a number!");
                System.out.print("Enter: ");
            }else {
                isValid = true;
            }

        }while(!isValid);

        return input.nextInt();
    }

}
