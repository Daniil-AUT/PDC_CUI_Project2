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
        return "Creating a Ticket:\n" +
                "The \"Create Ticket\" button is available. Click on it, and a \n"
                + "form will appear. " +
                "Fill in the necessary details to create your ticket. Once you've \n"
                + "created a ticket, the \"Create Ticket\" button will be disabled.\n\n" +

                "Viewing Your Ticket:\n" +
                "Your active ticket will be displayed on the dashboard. The \"View\"\n"
                + " and \"Update\" options are enabled only when you have an active \n"
                + "ticket. Click on \"View\" to see the details of your ticket.\n\n" +

                "Updating Your Ticket:\n" +
                "To make updates to your ticket, click on the \"Update\" button. \n"
                + "This will open a form where you can modify details or add comments.\n"
                + " Save your changes to update the ticket.\n\n" +

                "Deleting Your Ticket:\n" +
                "The \"Delete\" button is available, but it's disabled when you \n"
                + "don't have an active ticket. Once you create a ticket, the \"Delete\" \n"
                + "button becomes enabled. Click on it if you wish to remove your existing \n"
                + "ticket. Confirm the action when prompted.";
    }

    public static String assistantTicket() {
        return "Viewing Tickets:\n\n"
        + "Start by navigating to the ticket management interface.\n"
        + "Locate and press the \"View\" button to access a list of all \n"
        + "submitted tickets. Keep in mind that if there are no tickets submitted,\n"
        + " the \"View\" button will be disabled. Ensure that there are active \n"
        + "tickets before attempting to view them.\n\n"
        + "Replying to Tickets:\n\n"
        + "Once you've accessed the list of tickets, choose the specific ticket \n"
        + "you want to reply to. Press the \"Reply\" button to provide a response \n"
        + "or additional information. Note that the \"Reply\" button will only be\n"
        + "active if there are existing tickets. If there are no tickets, you won't\n"
        + "be able to reply until a ticket has been submitted.";
    }
}

