package seedu.address.logic.commands.quiz;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResultType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.quiz.QuizBank;

/**
 * Represents a show questions command, specific to a quiz.
 */
public class QuizShowQuestionsCommand extends QuizCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets the questions for a quiz\n"
            + "Parameters:\n"
            + "showQuestions quizID/ [QUIZ_ID]\n"
            + "Example: quizID/ CS2103T Finals\n\n";

    private final String quizId;

    /**
     * Creates a QuizShowQuestionsCommand instance with the appropriate attributes.
     * @param quizId The identifier of the quiz.
     */
    public QuizShowQuestionsCommand(String quizId) {
        requireNonNull(quizId);
        this.quizId = quizId;
    }

    /**
     * Executes the user command.
     * @param model {@code Model} which the command should operate on.
     * @return The result of the command.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (quizId.equals("") || quizId == null) {
            throw new CommandException(BLANK_QUIZ_ID);
        }

        if (!model.checkQuizExists(quizId)) {
            throw new CommandException(String.format(QUIZ_DOES_NOT_EXIST, quizId));
        }
        QuizBank.setCurrentlyQueriedQuiz(quizId);
        return new CommandResult("Showing questions for " + quizId + ".",
                CommandResultType.SHOW_QUIZ_QUESTIONS);
    }

    /**
     * Generates a command execution success message.
     * @param message The relevant message from the model.
     * @return The String representation of a success message.
     */
    private String generateSuccessMessage(String message) {
        return "These are the questions & answers for "
                    + message;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QuizShowQuestionsCommand)) {
            return false;
        }

        // state check
        QuizShowQuestionsCommand e = (QuizShowQuestionsCommand) other;
        return this.quizId.equals(e.quizId);
    }

}
