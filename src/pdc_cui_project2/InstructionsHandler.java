package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class InstructionsHandler {


    public static String userGreeting() {
        return  "FAQ Button:\n" +
        "\n" +
        "Click the \"View FAQ\" button to view frequently asked questions.\n" +
        "Explore common queries and find answers to your concerns.\n" +
        "\n" +
        "Log In Button:\n" +
        "\n" +
        "Click the \"Log In\" button to proceed to the login page.\n" +
        "If you already have an account, enter your credentials.\n" +
        "If you don't have an account, you can navigate to the \"Sign Up\" page.\n" +
        "\n" +
        "Sign Up Button:\n" +
        "\n" +
        "Click the \"Sign Up\" button to create a new account.\n" +
        "Follow the prompts to enter your details and set up your account.\n" +
        "\n" +
        "Quit Button:\n" +
        "\n" +
        "Click the \"Quit\" button to exit the program.\n" +
        "If you wish to leave the application, this is the way to do it.";

    }

    public static String userLogIn() {
        
        return "Please choose your role to be Student, Assistant, or Customer. \n"+
                "This helps us tailor your experience.\n\n"+
                "For the Email entry,  please enter your email address and make sure it\n"+
                "ends with \"@mail.com\" for proper registration.\n\n" +
                "For the password entry,  enter your password in password field and \n"+
                "remember, no blank spaces allowed. Keep it secure!\n\n";
    }

    public static String userAccount() {
        return "**Account Instructions**\n" +
               "All commands are case insensitive!\n\n" +
               "If you wish to go back to the Home Page, you can do so by typing 'home'.\n" +
               "By doing so, you will log out of your account.\n\n" +
               "If you wish to view your personal ticket, you can do so by typing 'view'\n" +
               "in command line.\n\n" +
               "If you wish to make a ticket, you can do so by typing 'create' in command\n" +
               "line.\n\n" +
               "If you wish to delete your ticket (deletes the whole ticket), you can do\n" +
               "so by typing 'delete' in the command line.\n\n" +
               "Note: A user is permitted to having one ticket at all times\n\n" +
               "If you wish to exit, you can do so at any time by typing 'quit' in command\n" +
               "line to exit the program.\n\n" +
               "**Warning: You must word the command exactly as specified in the instructions.**";
    }

    public static String createUserAccount() {
        return "**Account Creation**\n" +
               "All commands are case insensitive!\n\n" +
               "If you wish to go back to the Home Page, you can do so by typing 'home'.\n" +
               "By doing so, your progress will be lost.\n\n" +
               "Note: Your name cannot contain number/special characters/blank spaces\n" +
               "and the email should end with '@mail.com'.\n\n" +
               "Please fill in the fields without leaving any blank spaces.\n\n" +
               "If you wish to exit, you can do so at any time by typing 'quit' in command\n" +
               "line to exit the program.\n\n" +
               "**Warning: You must word the command exactly as specified in the instructions.**";
    }

    public static String userTicket() {
        return "**Ticket Instructions**\n" +
               "All commands are case insensitive!\n\n" +
               "If you wish to go back to the Home Page, you can do so by typing 'home'.\n" +
               "By doing so, you will log out of your account.\n\n" +
               "If you change your mind, you can go back to your Account, you can do so\n" +
               "by typing 'back' in the command line.\n\n" +
               "If you want the ticket to be registered, you must fill in all the field\n" +
               "with appropriate information, please note that by typing some keywords\n" +
               "of the program, the program will respond to them accordingly with saving\n" +
               "your ticket.\n\n" +
               "If you wish to exit, you can do so at any time by typing 'quit' in command\n" +
               "line to exit the program.\n\n" +
               "**Warning: You must word the command exactly as specified in the instructions.**";
    }

    public static String assistantTicket() {
        return "**Ticket Instructions**\n" +
               "All commands are case insensitive!\n\n" +
               "If you wish to go back to the Home Page, you can do so by typing 'home'.\n" +
               "By doing so, you will log out of your account.\n\n" +
               "If you change your mind, you can go back to your Account, you can do so\n" +
               "by typing 'account' in the command line.\n\n" +
               "If you wish to look at all the submitted tickets, you can do so by typing\n" +
               "'view' in the command line.\n\n" +
               "If you wish to reply to one of the tickets, you can do so by typing the\n" +
               "ticket ID.\n\n" +
               "**Warning: You must word the command exactly as specified in the instructions.**";
    }
}

