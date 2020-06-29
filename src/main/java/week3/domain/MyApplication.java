package week3.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MyApplication {
    // users - a list of users
    private User signedUser;
    private Admin signedAdmin;
    private Password password;

    ArrayList<User> UserList = new ArrayList();
    ArrayList<Admin> AdminList = new ArrayList();
    private void addUser(User user) {
        UserList.add(user);
    }
    private void addAdmin(Admin user) {
        AdminList.add(user);
    }

    private boolean checkUsername(String u, int N){
        if(N == 1)
        {
            for(User user : UserList)
            {
                if(user.getUsername().equals(u))
                {
                    return true;
                }
            }
        }
        else
        {
            for(Admin admin : AdminList)
            {
                if(admin.getUsername().equals(u))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (signedUser == null && signedAdmin == null) {
                System.out.println("You are not signed in.");
                System.out.println("1. Authentication");
                System.out.println("2. Authentication as Admin");
                System.out.println("3. Exit");
                int choice = sc.nextInt();
                if (choice == 1) authentication(1);
                if (choice == 2) {
                    authentication(2);
                }
                else {
                    saveUserList();
                    break;
                }
            }
        }
    }
    private void userProfile() throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            if (signedUser != null && signedAdmin == null) {
                System.out.println("You are signed in.");
                System.out.println("1. See name");
                System.out.println("2. See surname");
                System.out.println("3. See username");
                System.out.println("4. See password");
                System.out.println("5. Log out");
                int choice = sc.nextInt();
                if(choice == 1) {System.out.println(signedUser.getName());}
                if(choice == 2) {System.out.println(signedUser.getSurname());}
                if(choice == 3) {System.out.println(signedUser.getUsername());}
                if(choice == 4) {System.out.println(signedUser.getPassword());}
                if(choice == 5) {logOff(); break;}

            }
        }
    }

    private void adminProfile() throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            if (signedUser == null && signedAdmin != null) {
                System.out.println("You are signed in as Admin.");
                System.out.println("1. See my info");
                System.out.println("2. Delete User");
                System.out.println("3. Show Users");
                System.out.println("4. Log out");
                int choice = sc.nextInt();
                if(choice == 1) {System.out.println( signedAdmin.getInfo() );}
                if(choice == 2) {
                    System.out.println("Choose user by id");
                    int id = sc.nextInt();
                    UserList = signedAdmin.deleteUser(UserList, id);
                    System.out.println("User deleted succesfully!");
                }
                if(choice == 3) {System.out.println(signedAdmin.showUsers(UserList));}
                if(choice == 4) {logOff(); break;}
            }
        }
    }


    private void nullUser() {
        signedUser = null;
        signedAdmin = null;
    }

    private void logOff() throws IOException {
        nullUser();
    }

    private void authentication(int N) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            if(signedUser == null && signedAdmin == null)
            {
                System.out.println("1. Sign in");
                System.out.println("2. Sing up");
                System.out.println("3. Exit");
                int choice = sc.nextInt();
                if (choice == 1) signIn(N);
                else if(choice == 2) signUp(N);
                else if(choice == 3) break;
            }
        }
    }

    private void signIn(int N) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            if (signedUser == null && signedAdmin == null) {
                System.out.println("Username:");
                String u = sc.nextLine();
                while(!checkUsername(u, N))
                {
                    System.out.println("Username doesn't exist, try again:");
                    u = sc.nextLine();
                }
                System.out.println("Password:");
                String p;
                p = sc.nextLine();
                password = new Password(p);
                int count = 0;
                while(!password.checkPass(p, u, UserList, AdminList, N))
                {
                    count++;
                    if(count == 3)
                    {
                        System.out.println("you have exceeded the limit");
                        break;
                    }
                    System.out.println("Incorrect password, try again:");
                    p = sc.nextLine();
                    password = new Password(p);
                }
                if(count < 3){
                    if(N == 1)
                    {
                        for(User user : UserList)
                        {
                            if(user.getUsername().equals(u) && user.getPassword().equals(p))
                            {
                                signedUser = user;
                            }
                        }
                        userProfile();
                    }
                    else
                    {
                        for(Admin admin : AdminList)
                        {
                            if(admin.getUsername().equals(u) && admin.getPassword().equals(p))
                            {
                                signedAdmin = admin;
                            }
                        }
                        adminProfile();
                    }
                }
                break;
            }
            else {
                break;
            }
        }
    }


    private void signUp(int N) throws IOException {
        if(signedUser == null && signedAdmin == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Write name:");
            String n = sc.nextLine();
            System.out.println("Write surname:");
            String s = sc.nextLine();
            System.out.println("Create username:");
            String u = sc.nextLine();
            while(checkUsername(u, N))
            {
                System.out.println("Username is in use, enter another:");
                u = sc.nextLine();
            }
            System.out.println("Create password");
            System.out.println("it should contain uppercase and lowercase letters and digits");
            System.out.println("and its length must be more than 9 symbols:");
            String p;
            int count = 0;
            while (true) {
                p = sc.nextLine();
                password = new Password(p);
                if (password.checkPassword() == "false") {
                    System.out.println("Incorrect password format!");
                    System.out.println("Try again");
                    continue;
                }
                if (password.checkPassword() != "false") {
                    break;
                }
            }
            if(N == 1)
            {
                signedUser = new User(n, s, u, p);
                addUser(signedUser);
                if (signedUser != null) {
                    userProfile();
                }
            }
            else
            {
                signedAdmin = new Admin(n, s, u, p);
                addAdmin(signedAdmin);
                if(signedAdmin != null)
                {
                    adminProfile();
                }
            }
        }
    }

    public void start() throws IOException {
        File file = new File("D:\\JAVA\\week2.2.0\\src\\com\\company\\db.txt");
        Scanner fileScanner = new Scanner(file);
        File file2 = new File("D:\\JAVA\\week2.2.0\\src\\com\\company\\dbAdmin.txt");
        Scanner fileScanner2 = new Scanner(file2);
        // fill userlist from db.txt
        while (fileScanner.hasNext())
        {
            int id = fileScanner.nextInt();
            String n = fileScanner.next();
            String s = fileScanner.next();
            String u = fileScanner.next();
            String p = fileScanner.next();
            User user = new User(id, n, s, u, p);
            addUser(user);
        }
        while (fileScanner2.hasNext())
        {
            int id = fileScanner2.nextInt();
            String n = fileScanner2.next();
            String s = fileScanner2.next();
            String u = fileScanner2.next();
            String p = fileScanner2.next();
            Admin admin = new Admin(id, n, s, u, p);
            addAdmin(admin);
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Select command:");
            System.out.println("1. Menu");
            System.out.println("2. Exit");
            int choice = sc.nextInt();
            if (choice == 1) {
                menu();
            }
            if(choice == 2){
                break;
            }
        }

        // save the userlist to db.txt
    }


    private void saveUserList() throws IOException {
        String content = "";
        String content2 = "";
        for(User user : UserList){
            content += user.getId() + " " + user.getName() + " " + user.getSurname() + " " + user.getUsername() + " " + user.getPassword() + "\n";
        }
        for(Admin admin : AdminList){
            content2 += admin.getId() + " " + admin.getName() + " " + admin.getSurname() + " " + admin.getUsername() + " " + admin.getPassword() + "\n";
        }
        Files.write(Paths.get("D:\\JAVA\\week2.2.0\\src\\com\\company\\db.txt"), content.getBytes());
        Files.write(Paths.get("D:\\JAVA\\week2.2.0\\src\\com\\company\\dbAdmin.txt"), content2.getBytes());
    }
}