package com.testing.system.dao.parsers.rsparsers;

import org.junit.Test;

/**
 * Created by yuri on 21.09.17.
 */
public class NewResultSetParserTest {

    @Test
    public void shouldParseJoinedResultSet() {
        /*ResultSet rs = ResultSetMocker.mocker()
                .columns("tests",
                        "id", "title", "order_number")
                .columns("questions",
                        "id", "text", "mark", "order_number", "test_id")
                .columns("question_options",
                        "id", "text", "is_right", "question_id")
                .rows(RowsInitializer.parent(1, "FirstTest", 1)
                        .child(RowsInitializer.parent(1, "FirstQuestion", 1, 1, 1)
                                .child(1, "FirstOption", 1, 1)
                                .child(2, "SecondOption", 1, 1)
                                .child(3, "ThirdOption", 1, 1))
                        .child(RowsInitializer.parent(2, "SecondQuestion", 1, 1, 1)
                                .child(4, "FirstOption", 1, 2)
                                .child(5, "SecondOption", 1, 2)
                                .child(6, "ThirdOption", 1, 2)))

                .rows(RowsInitializer.parent(2, "SecondTest", 2)
                        .child(RowsInitializer.parent(3, "FirstQuestion", 1, 1, 2)
                                .child(7, "FirstOption", 1, 3)
                                .child(8, "SecondOption", 1, 3)))
                .mockResultSet();

        OldResultSetParser parser = new OldResultSetParser(rs);
        List<com.testing.system.entities.Test> tests =
                parser.parse(com.testing.system.entities.Test.class);

        System.out.println(tests);*/
    }
}
