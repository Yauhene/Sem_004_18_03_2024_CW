package task1;

import java.util.Scanner;
//import task1.*;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.exceptions;

public class TaskOne {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input login: ");
        String login = scanner.nextLine();
        System.out.println("Input password: ");
        String password = scanner.nextLine();
        System.out.println("Input confirmPassword: ");
        String confirmPassword = scanner.nextLine();

        while (login.isEmpty()) {
            System.out.println("Input login: ");
            login = scanner.nextLine();
        }

        while (password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Input password: ");
            password = scanner.nextLine();
            System.out.println("Input confirmPassword: ");
            confirmPassword = scanner.nextLine();
        }
        boolean check;
        try {
            check = checkLoginAndPassword(login,  password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e ) {
            System.out.println(e.getMessage());
            check = false;
        }
        System.out.println(check);
    }

    public static boolean checkLoginAndPassword(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {

        if (login.length() > 20) {
            throw new WrongLoginException("The length of login is more than 20.");
        }
        if (password.length() > 20) {
            throw new WrongPasswordException("The length of password is more than 20.");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("The password is not equals to confirmPassword");
        }
        return true;
    }
}