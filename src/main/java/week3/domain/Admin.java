package week3.domain;

import java.util.ArrayList;

public class Admin extends User {
    public Admin(String n, String s, String u,String p)
    {
        super(n, s, u, p);
    }

    public Admin(int id, String n, String s, String u, String p)
    {
        super(id, n, s, u, p);
    }

    public String showUsers(ArrayList<User> users)
    {
        String content = "";
        for(User user : users){
            content += user.getId() + " " + user.getName() + " " + user.getSurname() + " " + user.getUsername() + " " + user.getPassword() + "\n";
        }
        return content;
    }

    public ArrayList<User> deleteUser(ArrayList<User> users, int id)
    {
        ArrayList<User> newList = new ArrayList<User>();
        for(User user : users){
            if(user.getId() == id)
            {
                continue;
            }
            newList.add(user);
        }
        return newList;
    }

    public String getInfo(){
        String content = "";
        content += "Name: " + getName() + "\nSurname: " + getSurname()
                + "\nUsername: " + getUsername() + "\nPassword: " + getPassword();
        return content;
    }

    public static class User {
        // id (you need to generate this id by static member variable)
        // name, surname
        // username
        // password
        private int id;
        private static int id_gen = 1;
        private static int max = 1;
        private String name;
        private String surname;
        private String username;
        private String password;

        public User() {
            id = id_gen++;
        }

        public User(String name, String surname)
        {
            this();
            setName(name);
            setSurname(surname);
        }
        public User(String name, String surname, String username, String password){
            this(name, surname);
            setUsername(username);
            setPassword(password);
        }
        public User(int id, String name, String surname, String username, String password){
            this.id = id;
            if(max < id)
            {
                max = id;
            }
            id_gen = max;
            id_gen++;
            setName(name);
            setSurname(surname);
            setUsername(username);
            setPassword(password);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;

        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
        public int getId(){
            return id;
        }

    }
}