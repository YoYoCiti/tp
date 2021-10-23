package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Imports a list of Persons from a given CSV file.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = ": Imports the member data from a CSV file into SportsPA's data file.\n"
            + "There must be headers for the CSV file and they should be in this order:"
            + " NAME, PHONE, AVAILABILITY, TAGS"
            + "Parameters: KEYWORD CSV_FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " myFilePath.csv";
    public static final String MESSAGE_SUCCESS = "Successfully imported from CSV file!";

    private final ArrayList<Person> personList;

    /**
     * Creates an ImportCommand object to add the given list of Persons.
     *
     * @param personList the given list of Persons
     */
    public ImportCommand(ArrayList<Person> personList) {
        this.personList = personList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (Person person: personList) {
            if (model.hasPerson(person)) {
                Person personToReplace = model.getSamePerson(person);
                requireNonNull(personToReplace);
                model.setPerson(personToReplace, person);
            } else {
                model.addPerson(person);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ImportCommand
                && personList.equals(((ImportCommand) other).personList));
    }
}
