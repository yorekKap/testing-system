package com.testing.system.web.validators;

import com.testing.system.entities.Question;
import com.testing.system.entities.QuestionOption;
import com.testing.system.exceptions.validation.ValidationException;
import com.testing.system.web.dto.CreateTestDto;

/**
 * Created by yuri on 11.10.17.
 */
public class CreateTestDtoValidator extends Validator<CreateTestDto> {

    @Override
    public void validate(CreateTestDto testDto) throws ValidationException {
        check(isNotNull(testDto.getCategoryId()) &&
                isNotEmpty(testDto.getTitle()) &&
                isNotEmpty(testDto.getQuestions()));

        for (Question question : testDto.getQuestions()) {
            check(isGreater(question.getMark(), 0) &&
                    isNotNull(question.getOrderNumber()) &&
                    isNotEmpty(question.getText()) &&
                    isNotEmpty(question.getOptions()));

            for (QuestionOption option : question.getOptions()) {
                check(isNotEmpty(option.getText()) &&
                        isNotNull(option.getRight()));
            }
        }
    }
}
