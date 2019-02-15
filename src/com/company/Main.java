package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
public class Main {

    public static void main(String[] args) throws IOException{

        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        String name;
        String debug;
        double amount;
        ArrayList aryLst = new ArrayList();
        ListIterator iter = aryLst.listIterator();
        FileWriter accountList = new FileWriter("list.txt", true);
        ArrayList deposits = new ArrayList();
        ArrayList accounts = new ArrayList();

        do {
            Scanner kbReader = new Scanner(System.in);
            Scanner kbInput = new Scanner(System.in);
            System.out.println("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");
            name = kbReader.nextLine();

            if (!name.equalsIgnoreCase("EXIT")) {

                System.out.println("Please enter the amount of the deposit. ");
                amount = kbReader.nextDouble();
                deposits.add(fmt.format(amount));
                System.out.println(" "); // gives an eye pleasing blank line
                // between accounts
                bankAccount theAccount = new bankAccount(name, amount);
                accounts.add(theAccount);
                iter.add(theAccount);
                System.out.println("Would you like to enter debug mode? (\"Y\" or \"N\") ");
                debug = kbInput.nextLine();

                if (debug.equalsIgnoreCase("Y")){

                    //These vars are only needed in debug mode
                    String s;
                    String v;

                    accountList.write(name.toUpperCase() + " has an amount of: $" + fmt.format(amount));
                    System.out.println("This transaction was logged.");
                    System.out.println("Deposit amount from largest to smallest is available. (\"Press S\" to see) ");
                    s = kbInput.nextLine();

                    if (s.equalsIgnoreCase("S")){

                        Collections.sort(deposits, Collections.reverseOrder());
                        System.out.println(deposits);

                        System.out.println("A listing of account balances from largest to smallest is available (\"Press V\" to view) ");
                        v = kbInput.nextLine();

                        if (v.equalsIgnoreCase("V")){

                            Collections.sort(accounts, Collections.reverseOrder());
                            System.out.println(" "); //This is where I need to fix

                        }

                    }

                }else{

                    System.out.println("Transaction wasn't logged. Did you want this logged? (\"Y\" or \"N\") ");
                    debug = kbInput.nextLine();
                    if (debug.equalsIgnoreCase("Y")){

                        accountList.write(name.toUpperCase() + " has an amount of: $" + fmt.format(amount));
                        System.out.println("This transaction is now logged");

                    }
                }

                System.out.println(" "); //Creates an eye pleasing break
            }

        } while (!name.equalsIgnoreCase("EXIT"));

        //Closes the file after the loop
        accountList.close();

        // Search aryLst and print out the name and amount of the largest bank
        // account
        bankAccount ba = (bankAccount) iter.previous();
        double maxBalance = ba.balance; // set last account as the winner so far
        String maxName = ba.name;
        while (iter.hasPrevious()) {
            ba = (bankAccount) iter.previous();
            if (ba.balance > maxBalance) {
                // We have a new winner, chicken dinner
                maxBalance = ba.balance;
                maxName = ba.name;
            }
        }
        System.out.println(" ");
        System.out.println("The account with the largest balance belongs to "
                + maxName + ".");
        System.out.println("The amount is $" + fmt.format(maxBalance));

    }
}
