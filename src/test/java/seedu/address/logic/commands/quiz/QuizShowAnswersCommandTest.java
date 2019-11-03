package seedu.address.logic.commands.quiz;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResultType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.quiz.QuizBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.quiz.TypicalSavedQuizzes.getTypicalSavedQuizzes;

public class QuizShowAnswersCommandTest {

    private Model model = new ModelManager();

    public QuizShowAnswersCommandTest() {
        model.setSavedQuizzes(getTypicalSavedQuizzes());
    }

    @Test
    public void execute_validList_showsEverything() {
        Model expectedModel = new ModelManager();
        expectedModel.setSavedQuizzes(getTypicalSavedQuizzes());

        assertCommandSuccess(
                new QuizShowAnswersCommand(QuizBuilder.DEFAULT_QUIZ_ID),
                model,
                new CommandResult("Showing answers for " + QuizBuilder.DEFAULT_QUIZ_ID + ".",
                        CommandResultType.SHOW_QUIZ_ANSWERS),
                expectedModel);
    }
}
