import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {



    public static void main(String[] args){

        SkipList list = new SkipList();
        Scanner input = new Scanner(System.in);

        boolean exitFlag = false;

        System.out.println("Please select what mode do you want to use:");
        System.out.println("[1] Input Mode");
        System.out.println("[2] Statistics Mode");

        int mode = input.nextInt();

        if(mode == 1){
            while (!exitFlag) {
                printMenu();

                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        insertMenu(list);
                        break;
                    case 2:
                        removeMenu(list);
                        break;
                    case 3:
                        if (list.getListSize() > 50)
                            System.out.println("Unable to print. List is too big.");
                        else
                            list.printList();
                        break;
                    case 4:
                        closestAfterMenu(list);
                        break;
                    case 5:
                        findMenu(list);
                        break;
                    case 6:
                        list = new SkipList();
                        try {
                            generateRandomList(list);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        exitFlag = true;
                        break;
                    default:
                        printMenu();
                }
            }

        }
        else{

        }

    }

    private static void generateRandomList(SkipList list) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("How many items: (Range 1-50: PRINT / Range 50+: NO PRINT)");
        int desiredSize = input.nextInt();


        int size = 0;

        Random random = new Random();

        long start = System.nanoTime();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output_123.txt"));

        while (size != desiredSize) {

            int randomNumber = random.nextInt(desiredSize * 10);
            writer.write(Integer.toString(randomNumber));
            writer.newLine();
            list.insertElement(randomNumber, false);
            size = list.getListSize();

        }

        writer.close();
        long end = System.nanoTime();
        long total = end - start;

        System.out.println("Created list with " + size + " keys. Time taken: " + total + " nanoseconds");

        if(desiredSize <= 50)
            list.printList();


    }

    private static void closestAfterMenu(SkipList list){

        Scanner input = new Scanner(System.in);

        boolean keepSearching = false;
        while(!keepSearching) {

            System.out.print("Please enter number to be searched: ");
            int number = input.nextInt();

            if (number > Integer.MAX_VALUE)
                System.out.println("Value is out of range");
            else {
                list.closestKeyAfter(number);

                System.out.println("Search for another number? [Y] [N] ");
                String another = input.next();
                if(another.equalsIgnoreCase("Y"))
                    continue;
                else
                    keepSearching = true;

            }
        }

    }

    private static void findMenu(SkipList list){

        Scanner input = new Scanner(System.in);

        boolean keepFinding = false;
        while(!keepFinding) {

            System.out.print("Please enter number to be searched: ");
            int number = input.nextInt();

            if (number > Integer.MAX_VALUE)
                System.out.println("Value is out of range");
            else {
                list.findElement(number, true);

                System.out.println("Search for another number? [Y] [N] ");
                String another = input.next();
                if(another.equalsIgnoreCase("Y"))
                    continue;
                else
                    keepFinding = true;

            }
        }

    }
    private static void insertMenu(SkipList list){

        Scanner input = new Scanner(System.in);

        boolean keepInserting = false;
        while(!keepInserting) {

            System.out.print("Please enter number to be inserted: ");
            int number = input.nextInt();

            if (number > Integer.MAX_VALUE)
                System.out.println("Value is out of range");
            else {
                list.insertElement(number, false);
                list.printList();
                System.out.println("Insert another number? [Y] [N] ");
                String another = input.next();
                if(another.equalsIgnoreCase("Y"))
                    continue;
                else
                    keepInserting = true;

            }
        }

    }

    private static void removeMenu(SkipList list){
        {

            Scanner input = new Scanner(System.in);

            boolean keepRemoving = false;

            list.printList();

            while(!keepRemoving) {

                System.out.print("Please enter number to be removed: ");
                int number = input.nextInt();

                list.removeElement(number);
                list.printList();

                System.out.println("Remove another number? [Y] [N] ");
                String another = input.next();
                if(another.equalsIgnoreCase("Y"))
                    continue;
                else
                    keepRemoving = true;



                }
            }

        }

    private static void printMenu(){

        System.out.println("[1] Insert Element");
        System.out.println("[2] Remove Element");
        System.out.println("[3] Print List");
        System.out.println("[4] Closest Key After");
        System.out.println("[5] Find Element");
        System.out.println("[6] Generate Random List");
        System.out.println("[7] Exit");
        System.out.print(">");


    }

}
