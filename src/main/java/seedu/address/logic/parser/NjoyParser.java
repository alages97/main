package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.group.GroupCommand;
import seedu.address.logic.commands.note.NoteCommand;
import seedu.address.logic.commands.question.QuestionCommand;
import seedu.address.logic.commands.quiz.QuizCommand;
import seedu.address.logic.commands.statistics.StatisticsCommand;
import seedu.address.logic.commands.student.StudentCommand;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.group.GroupCommandParser;
import seedu.address.logic.parser.note.NoteCommandParser;
import seedu.address.logic.parser.question.QuestionCommandParser;
import seedu.address.logic.parser.quiz.QuizCommandParser;
import seedu.address.logic.parser.statistics.StatisticsCommandParser;
import seedu.address.logic.parser.student.StudentCommandParser;

/**
 * Parses user input.
 */
public class NjoyParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case QuizCommand.COMMAND_WORD:
            return new QuizCommandParser().parse(arguments);

        case GroupCommand.COMMAND_WORD:
            return new GroupCommandParser().parse(arguments);

        case QuestionCommand.COMMAND_WORD:
            return new QuestionCommandParser().parse(arguments);

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parse(arguments);

        case StudentCommand.COMMAND_WORD:
            return new StudentCommandParser().parse(arguments);

        case StatisticsCommand.COMMAND_WORD:
            return new StatisticsCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}