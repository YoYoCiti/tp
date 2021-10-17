package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_CAPACITY = new Prefix("c/");
    public static final Prefix PREFIX_AVAILABILITY = new Prefix("d/");
    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_COMMAND_WORD = new Prefix("cw/");
}
