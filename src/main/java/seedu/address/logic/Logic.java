package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.note.Note;
import seedu.address.model.note.ReadOnlyNotesRecord;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.ReadOnlyStatisticsRecord;
import seedu.address.model.statistics.Statistics;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    //region AddressBook
    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();
    //endregion

    //region Statistics
    /**
     * Returns the StatisticsRecord.
     *
     * @see seedu.address.model.Model#getStatisticsRecord()
     */
    ReadOnlyStatisticsRecord getStatisticsRecord();

    /** Returns an unmodifiable view of the processed statistics of students */
    ObservableList<Statistics> getProcessedStatistics();
    //endregion

    //region Notes
    /**
     * Returns the NotesRecord.
     *
     * @see seedu.address.model.Model#getNotesRecord()
     */
    ReadOnlyNotesRecord getNotesRecord();

    /** Returns an unmodifiable view of the filtered list of notes */
    ObservableList<Note> getFilteredNotesList();

    /**
     * Returns the user prefs' notes record file path.
     */
    Path getNotesRecordFilePath();
    //endregion

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
