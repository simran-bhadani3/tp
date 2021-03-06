package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.DeleteOrderItemCommand;

public class DeleteOrderItemCommandParserTest {

    private DeleteOrderItemCommandParser parser = new DeleteOrderItemCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrderItemCommand() {
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        assertParseSuccess(parser, "1", new DeleteOrderItemCommand(indexList));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteOrderItemCommand.MESSAGE_USAGE));
    }
}
