package Main.java;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "Programmed by Zahra, Maryam & Elahe");
        Scanner input = new Scanner(System.in);

        HashMap<Long, People> P_C = new HashMap<>();
        HashMap<Long, People> P_R = new HashMap<>();
        HashMap<Long, People> P_T = new HashMap<>();
        HashSet<Long> PT = new HashSet<>();
        HashMap<Long, People> Result = new HashMap<>();
        HashMap<Long, People> smuggler = new HashMap<>();
        HashSet<Long> SGR = new HashSet<>();

        Vertices vertices = new Vertices();
        Read_Information_From_CSV_File read_information_from_csv_file = new Read_Information_From_CSV_File();
        read_information_from_csv_file.Read_People_Information(vertices, P_C, smuggler, SGR);
        read_information_from_csv_file.Read_Account_Information(vertices);
        read_information_from_csv_file.Read_Homes_Information(vertices);
        read_information_from_csv_file.Read_Cars_Information(vertices);
        read_information_from_csv_file.Read_Phones_Information(vertices);

        Edges edges = new Edges();
        read_information_from_csv_file.Read_Ownership_Information(edges, vertices);
        read_information_from_csv_file.Read_Transactions_Information(edges, vertices);
        read_information_from_csv_file.Read_Calls_Information(edges, vertices);
        read_information_from_csv_file.Read_Relationships_Information(edges, vertices);
        new WriteToFile(vertices, edges);

        Relate_Vertices_With_Edges relate_vertices_with_edges = new Relate_Vertices_With_Edges();
        System.out.println(ConsoleColors.BLUE + "\nDo you want to see which people from the Ports or Customs Organization bought a car or a house for themselves or their relatives?" + ConsoleColors.RESET);
        relate_vertices_with_edges.Ownership_Edge(P_C, P_R, input.nextBoolean());

        System.out.println(ConsoleColors.BLUE + "\nDo you want to see which people from the Ports or Customs Organization Transactions to smuggler?" + ConsoleColors.RESET);
        relate_vertices_with_edges .Transactions_Edges(vertices, SGR, P_R, P_T,PT, input.nextBoolean());

        System.out.println(ConsoleColors.BLUE + "\nDo you want to see which people from the Ports or Customs Organization call to smuggler?" + ConsoleColors.RESET);
        relate_vertices_with_edges.Call_Edges(smuggler, SGR, P_T, PT, Result, input.nextBoolean());
    }
}

class Write {
    public void Write_PR(HashMap<Long, People> people) {

        try {
            FileWriter writer = new FileWriter("E:\\P_R.txt");

            for (People person : people.values()) {
                writer.write("ID:" + person.getSsn() + "\n" + "FirstName:" + person.getFirst_name());
                writer.write("    LastName:" + person.getLast_name() + "     Birthday:" + person.getBirthday());
                writer.write("     City:" + person.getCity() + "     Work:" + person.getWork() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the R_P file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_PT(HashMap<Long, People> people) {

        try {
            FileWriter writer = new FileWriter("E:\\P_T.txt");

            for (People person : people.values()) {
                writer.write("ID:" + person.getSsn() + "\n" + "FirstName:" + person.getFirst_name());
                writer.write("    LastName:" + person.getLast_name() + "     Birthday:" + person.getBirthday());
                writer.write("     City:" + person.getCity() + "     Work:" + person.getWork() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the R_T file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Result(HashMap<Long, People> people) {

        try {
            FileWriter writer = new FileWriter("E:\\Result.txt");

            for (People person : people.values()) {
                writer.write("ID:" + person.getSsn() + "\n" + "FirstName:" + person.getFirst_name());
                writer.write("    LastName:" + person.getLast_name() + "     Birthday:" + person.getBirthday());
                writer.write("     City:" + person.getCity() + "     Work:" + person.getWork() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Result file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class WriteToFile {

    Scanner input = new Scanner(System.in);

    public WriteToFile(Vertices vertices, Edges edges) {

        System.out.println(ConsoleColors.BLUE_UNDERLINED + "----------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE + "Which one do you want to see?\n1-Vertices\n2-Edges\n3-Both" + ConsoleColors.RESET);
        int n = input.nextInt();
        if (n == 1) {
            System.out.println(ConsoleColors.BLUE + "How many specifications do you want to see?" + ConsoleColors.RESET);
            int n1 = input.nextInt();
            if (n1 == 5) {
                Write_People(vertices.people);
                Write_Accounts(vertices.accounts);
                Write_Homes(vertices.homes);
                Write_Cars(vertices.cars);
                Write_Phones(vertices.phones);
            } else {
                System.out.println(ConsoleColors.PURPLE + "Which one do you want to see?\n1-People\n2-Account\n3-Homes\n4-Cars\n5-Phones" + ConsoleColors.RESET);
                for (int i = 0; i < n1; i++) {
                    int nv = input.nextInt();
                    if (nv == 1) Write_People(vertices.people);
                    else if (nv == 2) Write_Accounts(vertices.accounts);
                    else if (nv == 3) Write_Homes(vertices.homes);
                    else if (nv == 4) Write_Cars(vertices.cars);
                    else if (nv == 5) Write_Phones(vertices.phones);
                }
            }
        } else if (n == 2) {
            System.out.println(ConsoleColors.BLUE + "How many specifications do you want to see?" + ConsoleColors.RESET);
            int n1 = input.nextInt();
            if (n1 == 5) {
                Write_Ownership(edges.Ownership);
                Write_Transactions(edges.Transactions);
                Write_Calls(edges.Call);
                Write_Relationship(edges.Relationship);
            } else {
                System.out.println(ConsoleColors.PURPLE + "Which one do you want to see?\n1-Ownership\n2-Transactions\n3-Call\n4-Relationship" + ConsoleColors.RESET);
                for (int i = 0; i < n1; i++) {
                    int ne = input.nextInt();
                    if (ne == 1) Write_Ownership(edges.Ownership);
                    else if (ne == 2) Write_Transactions(edges.Transactions);
                    else if (ne == 3) Write_Calls(edges.Call);
                    else if (ne == 4) Write_Relationship(edges.Relationship);
                }
            }
        } else {
            Write_People(vertices.people);
            Write_Accounts(vertices.accounts);
            Write_Homes(vertices.homes);
            Write_Cars(vertices.cars);
            Write_Phones(vertices.phones);
            Write_Ownership(edges.Ownership);
            Write_Transactions(edges.Transactions);
            Write_Calls(edges.Call);
            Write_Relationship(edges.Relationship);
        }
    }

    public void Write_People(HashMap<Long, People> people) {

        try {
            FileWriter writer = new FileWriter("E:\\People.txt");

            for (People person : people.values()) {
                writer.write("ID:" + person.getSsn() + "\n" + "FirstName:" + person.getFirst_name());
                writer.write("    LastName:" + person.getLast_name() + "     Birthday:" + person.getBirthday());
                writer.write("     City:" + person.getCity() + "     Work:" + person.getWork() + "\n\n");

                writer.write("Ownership:\n");
                if (!person.ownership.isEmpty()) {
                    for (Ownership ownership : person.ownership) {
                        if (ownership.getToHome() != null) {
                            writer.write("Home:" + ownership.getToHome().getPostal_code() + "\n" + "Date:" + ownership.getDate());
                            writer.write("    Amount:" + ownership.getAmount() + "\n\n");
                        }
                        if (ownership.getToCar() != null) {
                            writer.write("Car:" + ownership.getToCar().getPlate() + "\n" + "Date:" + ownership.getDate());
                            writer.write("    Amount:" + ownership.getAmount() + "\n\n");
                        }
                    }
                }

                writer.write("Relationship:\n");
                if (!person.relationship.isEmpty()) {
                    for (Relationship relationship : person.relationship) {
                        writer.write("First_Name Of Relation:" + relationship.getTo().getFirst_name() + "     Last_Name Of Relation" + relationship.getTo().getLast_name() + "\n");
                        writer.write("Relation:" + relationship.getRelation() + "    Date:" + relationship.getDate() + "\n\n");

                    }
                }

                writer.write("Transactions:\n");
                if (!person.transactions.isEmpty()) {
                    for (Transactions transactions : person.transactions) {
                        writer.write("Date:" + transactions.getDate());
                        writer.write("    Amount:" + transactions.getAmount() + "    To:" + transactions.getTo().getSsn() + "\n\n");

                    }
                }

                writer.write("Call:\n");
                if (!person.call.isEmpty()) {
                    for (Call call : person.call) {
                        writer.write("call:" + call.getCall_id() + "\n" + "Date:" + call.getDate());
                        writer.write("    Amount:" + call.getDuration() + "    To:" + call.getTo().getSsn() + "\n\n");

                    }
                }
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the people file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Accounts(HashMap<Long, Account> accounts) {

        try {
            FileWriter writer = new FileWriter("E:\\Accounts.txt");

            for (Account account : accounts.values()) {
                writer.write("ID:" + account.getAccount_id() + "\n" + "Bank_Name:" + account.getBank_name());
                writer.write("     IBAN:" + account.getIBAN() + "     Ssn:" + account.getSsn() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Accounts file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Homes(HashMap<Double, Home> homes) {

        try {
            FileWriter writer = new FileWriter("E:\\Homes.txt");

            for (Home home : homes.values()) {
                writer.write("ID:" + home.getPostal_code() + "\n" + "Ssn:" + home.getSsn());
                writer.write("     Price:" + home.getPrice() + "     Meters:" + home.getSize() + "\n");
                writer.write("     Address" + home.getAddress() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Home file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Cars(HashMap<String, Car> cars) {

        try {
            FileWriter writer = new FileWriter("E:\\Cars.txt");

            for (Car car : cars.values()) {
                writer.write("ID:" + car.getPlate() + "\n" + "Model:" + car.getModel());
                writer.write("     Color:" + car.getColor() + "     Ssn:" + car.getSsn() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the cars file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Phones(HashMap<String, Phone> phones) {

        try {
            FileWriter writer = new FileWriter("E:\\Phones.txt");

            for (Phone phone : phones.values()) {
                writer.write("ID:" + phone.getNumber() + "\n" + "Ssn:" + phone.getSsn());
                writer.write("     Operator:" + phone.getOperator() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Phones file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Ownership(HashSet<Ownership> ownerships) {

        try {
            FileWriter writer = new FileWriter("E:\\Ownership.txt");

            for (Ownership ownership : ownerships) {
                writer.write("From:" + ownership.getFrom());
                if (ownership.getToCar() != null) writer.write("   To:" + ownership.getToCar().getPlate() + "\n");
                else writer.write("   To:" + ownership.getToHome() + "\n");
                writer.write("Ownership_id:" + ownership.getOwnership_id() + "     Date:" + ownership.getDate());
                writer.write("      Amount:" + ownership.getAmount() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Ownership file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Transactions(HashSet<Transactions> transactions) {

        try {
            FileWriter writer = new FileWriter("E:\\Transactions.txt");

            for (Transactions transaction : transactions) {
                writer.write("From:" + transaction.getFrom() + "   To:" + transaction.getTo().getAccount_id() + "\n");
                writer.write("Transaction_id:" + transaction.getTransaction_id());
                writer.write("      Date:" + transaction.getDate() + "      Amount" + transaction.getAmount() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Transaction file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Calls(HashSet<Call> calls) {

        try {
            FileWriter writer = new FileWriter("E:\\Calls.txt");

            for (Call call : calls) {
                writer.write("From:" + call.getFrom() + "   To:" + call.getTo().getNumber() + "\n");
                writer.write("Call_id:" + call.getCall_id());
                writer.write("      Date:" + call.getDate() + "      Duration" + call.getDuration() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the calls file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Relationship(HashSet<Relationship> relationships) {

        try {
            FileWriter writer = new FileWriter("E:\\Relationship.txt");

            for (Relationship relationship : relationships) {
                writer.write("From:" + relationship.getFrom() + "   To:" + relationship.getTo().getSsn() + "\n");
                writer.write("Call_id:" + relationship.getRelation() + "      Date:" + relationship.getDate() + "\n\n");
            }

            writer.close();
            System.out.println(ConsoleColors.GREEN_BOLD + "Successfully wrote to the Relationship file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

class Read_Information_From_CSV_File {

    public void Read_People_Information(Vertices vertices, HashMap<Long, People> P_C, HashMap<Long, People> smuggler, HashSet<Long> SGR) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\people.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                People people = new People();
                people.setFirst_name(value[0].substring(1, value[0].length() - 1));
                people.setLast_name(value[1].substring(1, value[1].length() - 1));
                people.setSsn(Long.parseLong(value[2].substring(1, value[2].length() - 1)));
                people.setBirthday(value[3].substring(1, value[3].length() - 1));
                people.setCity(value[4].substring(1, value[4].length() - 1));
                people.setWork(value[5].substring(1, value[5].length() - 1));
                vertices.New_People(people);

                if (value[5].contains("سازمان بنادر") || value[5].contains("گمرک"))
                    P_C.put(people.getSsn(), people);

                if (value[5].contains("قاچاقچی")) {
                    SGR.add(people.getSsn());
                    smuggler.put(people.getSsn(), people);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Account_Information(Vertices vertices) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\accounts.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Account account = new Account();
                account.setSsn(Long.parseLong(value[0].substring(1, value[0].length() - 1)));
                account.setBank_name(value[1].substring(1, value[1].length() - 1));
                account.setIBAN(value[2].substring(1, value[2].length() - 1));
                account.setAccount_id(Long.parseLong(value[3].substring(1, value[3].length() - 1)));
                vertices.New_Account(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Homes_Information(Vertices vertices) {

        String csvFile = "E:\\homes.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Home home = new Home();

                String[] Home = line.split(cvsSplitBy);
                for (int h = 0; h < Home.length - 3; h++) {
                    StringBuilder tmp0 = new StringBuilder();
                    for (int l = 1; l < Home[h].length() - 1; l++) tmp0.append(Home[h].charAt(l));
                    Home[h] = tmp0.toString();

                    if (h == 0) home.setSsn(Long.parseLong(Home[h]));
                    if (h == 1) home.setPrice(Double.parseDouble(Home[h]));
                    if (h == 2) home.setPostal_code(Double.parseDouble(Home[h]));
                    if (h == 3) home.setSize(Integer.parseInt(Home[h]));
                }
                StringBuilder tmp = new StringBuilder();
                StringBuilder tmp1 = new StringBuilder();
                for (int h = Home.length - 3; h < Home.length; h++) {
                    tmp.append(Home[h]);

                }

                for (int l = 1; l < tmp.length() - 1; l++) tmp1.append(tmp.charAt(l));
                tmp = new StringBuilder(tmp1.toString());
                home.setAddress(tmp.toString());
                vertices.New_Home(home);

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Cars_Information(Vertices vertices) {

        String csvFile = "E:\\cars.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                String[] carr = line.split(cvsSplitBy);
                Car car = new Car();
                for (int h = 0; h < carr.length; h++) {
                    StringBuilder tmp0 = new StringBuilder();
                    for (int l = 1; l < carr[h].length() - 1; l++) tmp0.append(carr[h].charAt(l));
                    carr[h] = tmp0.toString();
                    //System.out.println(carr[h]);
                    if (h == 0) car.setPlate(carr[h]);
                    if (h == 1) car.setSsn(Long.parseLong(carr[h]));
                    if (h == 2) car.setModel(carr[h]);
                    if (h == 3) car.setColor(carr[h]);
                }
                vertices.New_Car(car);


            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Phones_Information(Vertices vertices) {

        String csvFile = "E:\\phones.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Phone phone = new Phone();
                String[] Phone = line.split(cvsSplitBy);
                for (int h = 0; h < Phone.length; h++) {

                    StringBuilder tmp0 = new StringBuilder();
                    for (int l = 1; l < Phone[h].length() - 1; l++) tmp0.append(Phone[h].charAt(l));
                    Phone[h] = tmp0.toString();
                    if (h == 0) phone.setSsn(Long.parseLong(Phone[h]));
                    if (h == 1) phone.setNumber(Phone[h]);
                    if (h == 2) phone.setOperator(Phone[h]);
                }
                vertices.New_Phone(phone);


            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Ownership_Information(Edges edges, Vertices vertices) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\ownerships.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Ownership ownership = new Ownership();
                ownership.setFrom(Long.parseLong(value[0].substring(1, value[0].length() - 1)));
                if (value[1].chars().allMatch(Character::isDigit)) {
                    ownership.setToHome(vertices.homes.get(Double.parseDouble(value[1].substring(1, value[1].length() - 1))));
                } else ownership.setToCar(vertices.cars.get(value[1].substring(1, value[1].length() - 1)));
                ownership.setOwnership_id(value[2].substring(1, value[2].length() - 1));
                ownership.setDate(value[3].substring(1, value[3].length() - 1));
                ownership.setAmount(Long.parseLong(value[4].substring(1, value[4].length() - 1)));
                edges.New_Ownership(ownership);
                vertices.people.get(ownership.getFrom()).ownership.add(ownership);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Transactions_Information(Edges edges, Vertices vertices) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\transactions.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Transactions transactions = new Transactions();
                transactions.setFrom(value[0].substring(1, value[0].length() - 1));
                transactions.setTo(vertices.accounts.get(Long.parseLong(value[1].substring(1, value[1].length() - 1))));
                transactions.setTransaction_id(Double.parseDouble(value[2].substring(1, value[2].length() - 1)));
                transactions.setDate(value[3].substring(1, value[3].length() - 1));
                transactions.setAmount(Long.parseLong(value[4].substring(1, value[4].length() - 1)));
                edges.New_Transactions(transactions);
                vertices.people.get(vertices.accounts.get(Long.parseLong(transactions.getFrom())).getSsn()).transactions.add(transactions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Calls_Information(Edges edges, Vertices vertices) {

        String csvFile = "E:\\calls.csv";
        BufferedReader br;
        String line;
        String cvsSplitBy = ",";
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Call call = new Call();
                String[] calls = line.split(cvsSplitBy);
                for (int h = 0; h < calls.length; h++) {
                    StringBuilder tmp0 = new StringBuilder();
                    for (int l = 1; l < calls[h].length() - 1; l++) tmp0.append(calls[h].charAt(l));
                    calls[h] = tmp0.toString();
                    if (h == 0) call.setFrom(calls[h]);
                    if (h == 1) call.setTo(vertices.phones.get(calls[h]));
                    if (h == 2) call.setCall_id(Double.parseDouble(calls[h]));
                    if (h == 3) call.setDate(calls[h]);
                    if (h == 4) call.setDuration(calls[h]);
                    vertices.people.get(vertices.phones.get(call.getFrom()).getSsn()).call.add(call);
                }
                edges.New_Call(call);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void Read_Relationships_Information(Edges edges, Vertices vertices) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\relationships.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Relationship relationship1 = new Relationship(), relationship2 = new Relationship();
                relationship1.setFrom(Long.parseLong(value[0].substring(1, value[0].length() - 1)));
                relationship1.setTo(vertices.people.get(Long.parseLong(value[1].substring(1, value[1].length() - 1))));
                relationship1.setRelation(value[2].substring(1, value[2].length() - 1));
                relationship1.setDate(value[3].substring(1, value[3].length() - 1));

                relationship1.setFrom(Long.parseLong(value[1].substring(1, value[1].length() - 1)));
                relationship1.setTo(vertices.people.get(Long.parseLong(value[0].substring(1, value[0].length() - 1))));
                relationship1.setRelation(value[2].substring(1, value[2].length() - 1));
                relationship1.setDate(value[3].substring(1, value[3].length() - 1));

                edges.New_Relationship(relationship1);
                if (!vertices.people.get(relationship1.getFrom()).relationship.contains(relationship1))
                    vertices.people.get(relationship1.getFrom()).relationship.add(relationship1);
                if (vertices.people.containsKey(relationship2.getFrom()) && !vertices.people.get(relationship2.getFrom()).relationship.contains(relationship2))
                    vertices.people.get(relationship2.getFrom()).relationship.add(relationship2);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Vertices {
    //vertices:
    HashMap<Long, People> people = new HashMap<>();
    HashMap<Long, Account> accounts = new HashMap<>();
    HashMap<Double, Home> homes = new HashMap<>();
    HashMap<String, Car> cars = new HashMap();
    HashMap<String, Phone> phones = new HashMap();

    //methods
    public void New_People(People p) {
        people.put(p.getSsn(), p);
    }

    public void New_Account(Account account) {
        accounts.put(account.getAccount_id(), account);
    }

    public void New_Home(Home home) {
        homes.put(home.getPostal_code(), home);
    }

    public void New_Car(Car car) {
        cars.put(car.getPlate(), car);
    }

    public void New_Phone(Phone phone) {
        phones.put(phone.getNumber(), phone);
    }
}

class People {

    public LinkedList<Ownership> ownership = new LinkedList<>();
    public LinkedList<Transactions> transactions = new LinkedList<>();
    public LinkedList<Call> call = new LinkedList<>();
    public LinkedList<Relationship> relationship = new LinkedList<>();
    private String first_name;
    private String last_name;
    private long ssn;
    private LocalDate birthday;
    private String city;
    private String work;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        String[] date = birthday.split("-");
        this.birthday = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

}

class Account {
    private long ssn;
    private String bank_name;
    private String IBAN;
    private long account_id;

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
}

class Home {
    private long ssn;
    private double price;
    private double postal_code;
    private int size;
    private String address;

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(double postal_code) {
        this.postal_code = postal_code;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

class Car {
    private String plate;
    private long ssn;
    private String model;
    private String color;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

class Phone {

    private long ssn;
    private String number;
    private String operator;

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}


class Edges {

    HashSet<Ownership> Ownership = new HashSet<>();
    HashSet<Transactions> Transactions = new HashSet<>();
    HashSet<Call> Call = new HashSet<>();
    HashSet<Relationship> Relationship = new HashSet<>();

    public void New_Ownership(Ownership ownership) {
        Ownership.add(ownership);
    }

    public void New_Transactions(Transactions transactions) {
        Transactions.add(transactions);
    }

    public void New_Call(Call call) {
        Call.add(call);
    }

    public void New_Relationship(Relationship relationship) {
        Relationship.add(relationship);
    }


}

class Ownership {

    private long from;
    private Home toHome;
    private Car toCar;
    private String ownership_id;
    private LocalDate date;
    private long amount;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public Home getToHome() {
        return toHome;
    }

    public void setToHome(Home toHome) {
        this.toHome = toHome;
    }

    public Car getToCar() {
        return toCar;
    }

    public void setToCar(Car toCar) {
        this.toCar = toCar;
    }

    public String getOwnership_id() {
        return ownership_id;
    }

    public void setOwnership_id(String ownership_id) {
        this.ownership_id = ownership_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String d) {
        String[] date;
        date = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

class Transactions {

    private String from;
    private Account to;
    private double transaction_id;
    private LocalDate date;
    private long amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public double getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(double transaction_id) {
        this.transaction_id = transaction_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String d) {
        String[] date;
        date = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

class Call {

    private String from;
    private Phone to;
    private double call_id;
    private LocalDateTime date;
    private LocalTime duration;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Phone getTo() {
        return to;
    }

    public void setTo(Phone to) {
        this.to = to;
    }

    public double getCall_id() {
        return call_id;
    }

    public void setCall_id(double call_id) {
        this.call_id = call_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = LocalTime.parse(duration);
    }
}

class Relationship {

    private Long from;
    private People to;
    private String relation;
    private LocalDate date;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public People getTo() {
        return to;
    }

    public void setTo(People to) {
        this.to = to;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String d) {
        String[] date;
        date = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }
}

class Relate_Vertices_With_Edges {

    boolean b = false;

    public void Ownership_Edge(HashMap<Long, People> P_C, HashMap<Long, People> P_R, boolean TOF) {
        for (People p_c : P_C.values()) {
            int v = 0;
            if (!p_c.ownership.isEmpty() && !P_R.containsKey(p_c.getSsn())) {
                for (Ownership ownership : p_c.ownership) {
                    if (ChronoUnit.YEARS.between(LocalDate.now(), ownership.getDate()) < 2 || (ChronoUnit.YEARS.between(ownership.getDate(), LocalDate.now()) == 2 && ownership.getDate().getMonthValue() > LocalDate.now().getMonthValue() || (ownership.getDate().getMonthValue() == LocalDate.now().getMonthValue() && ownership.getDate().getDayOfMonth() >= LocalDate.now().getDayOfMonth()))) {
                        P_R.put(ownership.getFrom(), p_c);
                        break;
                    }
                }
            }

            if (!p_c.relationship.isEmpty() && !P_R.containsKey(p_c.getSsn())) {
                for (Relationship relationship : p_c.relationship) {
                    if (!relationship.getTo().ownership.isEmpty()) {
                        for (Ownership ownership : relationship.getTo().ownership) {
                            if (ChronoUnit.YEARS.between(LocalDate.now(), ownership.getDate()) < 2 || (ChronoUnit.YEARS.between(ownership.getDate(), LocalDate.now()) == 2 && ownership.getDate().getMonthValue() > LocalDate.now().getMonthValue() || (ChronoUnit.YEARS.between(ownership.getDate(), LocalDate.now()) == 2 && ownership.getDate().getMonthValue() == LocalDate.now().getMonthValue() && ownership.getDate().getDayOfMonth() >= LocalDate.now().getDayOfMonth()))) {
                                System.out.println(ownership.getDate().getYear());
                                P_R.put(ownership.getFrom(), p_c);
                                v = 1;
                                break;
                            }
                        }
                        if (v == 1) break;
                    }
                }
            }
        }

        if (TOF) {
            Write write = new Write();
            write.Write_PR(P_R);
            for (People people : P_R.values()) {
                System.out.print("ID:" + people.getSsn() + "\n" + "FirstName:" + people.getFirst_name());
                System.out.print("    LastName:" + people.getLast_name() + "     Birthday:" + people.getBirthday());
                System.out.print("     City:" + people.getCity() + "     Work:" + people.getWork() + "\n\n");

            }
            System.out.println(P_C.size() + " " + P_R.size());
        }

    }

    public void Transactions_Edges(Vertices vertices, HashSet<Long> smuggler, HashMap<Long, People> P_R, HashMap<Long, People> P_T, HashSet<Long> PT, boolean TOF) {


        for (People people : P_R.values()) {
            if (!people.transactions.isEmpty()) {
                b = false;
                if (Check(vertices, people, people.transactions.get(0), smuggler, 0)) {
                    P_T.put(people.getSsn(), people);
                    PT.add(people.getSsn());
                }
            }
        }

        if (TOF) {
            Write write = new Write();
            write.Write_PT(P_T);
            for (People people : P_T.values()) {
                System.out.print("ID:" + people.getSsn() + "\n" + "FirstName:" + people.getFirst_name());
                System.out.print("    LastName:" + people.getLast_name() + "     Birthday:" + people.getBirthday());
                System.out.print("     City:" + people.getCity() + "     Work:" + people.getWork() + "\n\n");
            }
        }
    }

    public boolean Check(Vertices vertices, People people, Transactions transaction, HashSet<Long> smuggler,  int i) {

        if (i == 5) return false;
        for (Transactions transactions : people.transactions) {
            if (smuggler.contains(transactions.getTo().getSsn()) && (transactions.getDate().isBefore(transaction.getDate()) || transactions.getDate().isEqual(transaction.getDate()))) return true;
            if (Check(vertices, vertices.people.get(transactions.getTo().getSsn()), transaction, smuggler,  i + 1)) return true;
        }
        return false;
    }

    public void Call_Edges(HashMap<Long, People> smuggler, HashSet<Long> SGR, HashMap<Long, People> P_T, HashSet<Long> PR, HashMap<Long, People> Result, boolean TOF) {

        for (People people : P_T.values()) {
            if (!people.call.isEmpty())
                for (Call call : people.call)
                    if (SGR.contains(call.getTo().getSsn())) {
                        PR.remove(call.getTo().getSsn());
                        Result.put(people.getSsn(), people);
                        break;
                    }
        }

        for (People people : smuggler.values()) {
            if (!people.call.isEmpty())
                for (Call call : people.call)
                    if (PR.contains(call.getTo().getSsn())) {
                        Result.put(call.getTo().getSsn(), P_T.get(call.getTo().getSsn()));
                        break;
                    }
        }

        if (TOF) {
            Write write = new Write();
            write.Write_Result(Result);
            for (People people : Result.values()) {
                System.out.print("ID:" + people.getSsn() + "\n" + "FirstName:" + people.getFirst_name());
                System.out.print("    LastName:" + people.getLast_name() + "     Birthday:" + people.getBirthday());
                System.out.print("     City:" + people.getCity() + "     Work:" + people.getWork() + "\n\n");
            }
        }
    }
}

class ConsoleColors {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String BLUE_UNDERLINED = "\033[4;34m";
}