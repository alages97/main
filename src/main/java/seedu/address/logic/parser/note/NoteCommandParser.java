package seedu.address.logic.parser.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.note.NoteAddCommand;
import seedu.address.logic.commands.note.NoteCommand;
import seedu.address.logic.commands.note.NoteDeleteCommand;
import seedu.address.logic.commands.note.NoteEditCommand;
import seedu.address.logic.commands.note.NoteEditCommand.EditNoteDescriptor;
import seedu.address.logic.commands.note.NoteListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new {@code NoteCommand} object
 */
public class NoteCommandParser implements Parser<NoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an NoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, CliSyntax.PREFIX_NOTE, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_LIST,
                        CliSyntax.PREFIX_DELETE);

        boolean isEdit = false;
        Index index = Index.fromZeroBased(0);

        try {
            String preamble = argMultimap.getPreamble();

            if (!preamble.isBlank()) {
                index = ParserUtil.parseIndex(preamble);
                isEdit = true;
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteEditCommand.MESSAGE_USAGE),
                    pe);
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_LIST).isPresent()) {
            return new NoteListCommand();
        } else if (argMultimap.getValue(CliSyntax.PREFIX_DELETE).isPresent()) {
            try {
                int indexToDelete = Integer
                        .parseInt(argMultimap.getValue(CliSyntax.PREFIX_DELETE).orElse("0"));

                if (indexToDelete <= 0) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                    NoteDeleteCommand.MESSAGE_USAGE));
                }
                index = index.fromOneBased(indexToDelete);
            } catch (NumberFormatException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                NoteDeleteCommand.MESSAGE_USAGE));
            }
            return new NoteDeleteCommand(index);
        } else if (isEdit) { // Edit command
            EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();
            editNoteDescriptor.setNote(argMultimap.getValue(CliSyntax.PREFIX_NOTE).orElse(""));
            editNoteDescriptor.setDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).orElse(""));
            return new NoteEditCommand(index, editNoteDescriptor);
        } else { // Create command
            if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NOTE, CliSyntax.PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String
                                .format(MESSAGE_INVALID_COMMAND_FORMAT, NoteAddCommand.MESSAGE_USAGE));
            }

            String note = argMultimap.getValue(CliSyntax.PREFIX_NOTE).orElse("");
            String description = argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).orElse("");

            return new NoteAddCommand(new Note(note, description));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                              Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}