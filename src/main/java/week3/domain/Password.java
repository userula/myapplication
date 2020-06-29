package week3.domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Password {
    // passwordStr // it should contain uppercase and lowercase letters and digits
    // and its length must be more than 9 symbols
    private String passwordStr;

    public Password(String p){
        passwordStr = p;
    }

    public String checkPassword() {

        if (passwordStr.length() < 9){
            return "false";
        }
        else {
            int n = passwordStr.length();
            int u = 0;
            int l = 0;
            int d = 0;
            for (int i = 0; i < n; i++) {
                if(passwordStr.charAt(i) == ' ')
                {
                    return "false";
                }
                if(isDigit(passwordStr.charAt(i))){
                    d++;
                }
                if(hasLowerCase(passwordStr.charAt(i))){
                    l++;
                }
                if(hasUpperCase(passwordStr.charAt(i))){
                    u++;
                }
            }
            if(l > 0 && u > 0 && d > 0){
                return passwordStr;
            }
            else{
                return "false";
            }
        }
    }

    public boolean checkPass(String p, String u, ArrayList<User> UserList, ArrayList<Admin> AdminList, int N) {
        if (N == 1)
        {
            for (User user : UserList) {
                if (user.getUsername().equals(u)) {
                    if (user.getPassword().equals(p)) {
                        return true;
                    }
                }
            }
        }
        else
        {
            for (Admin admin : AdminList) {
                if (admin.getUsername().equals(u)) {
                    if (admin.getPassword().equals(p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isDigit(char ch){
        for(char i = '0'; i <= '9'; i++)
        {
            if(ch == i)
            {
                return true;
            }
        }
        return false;
    }

    private boolean hasUpperCase(char ch){
        for(char i = 'A'; i <= 'Z'; i++)
        {
            if(ch == i)
            {
                return true;
            }
        }
        return false;
    }
    private boolean hasLowerCase(char ch){
        for(char i = 'a'; i <= 'z'; i++)
        {
            if(ch == i)
            {
                return true;
            }
        }
        return false;
    }
}
