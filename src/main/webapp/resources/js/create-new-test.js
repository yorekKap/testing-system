var data = {
    categoryId: requestCategoryId,
    testTitle: "",
    questions: [],

    newQuestion: {
        text: "",
        mark: 0,
        options: []
    },

    newOption: {
        text: "",
        isRight: false
    }
};

var FORM_ID = "#create-test-form";

var controller = {
    addQuestion: function (event, model) {
        if (!($("#question-text")[0].checkValidity() &&
            $("#question-mark")[0].checkValidity() &&
            data.newQuestion.options.length !== 0)) {

            return;
        }

        data.newQuestion.orderNumber = data.questions.length + 1;
        data.newQuestion.mark = parseInt(data.newQuestion.mark);
        data.questions.push(jQuery.extend(true, {}, data.newQuestion));

        data.newQuestion = {
            text: "",
            mark: 0,
            options: []
        };
    },

    addOption: function (event, model) {
        if (!$("#option-text")[0].checkValidity()) {
            return;
        }

        data.newQuestion.options.push(data.newOption);

        data.newOption = {
            text: "",
            isRight: false
        }
    },

    removeQuestion: function (event, model) {
        data.questions.splice(model.index, 1);
    },

    removeOption: function (event, model) {
        data.newQuestion.options.splice(model.index, 1);
    },

    createNewTest: function (event, model) {
        if (!($("#test-title")[0].checkValidity() && data.questions.length !== 0)){
            return;
        }

        $.post("/tutor/category/test", {
            action: "CREATE",
            content: JSON.stringify({
                    title: data.testTitle,
                    questions: data.questions,
                    categoryId: data.categoryId
                }
            )
        })
            .then(function () {
                window.location = "/tutor/category?categoryId=" + data.categoryId;
            })
            .catch(function (error) {
                $("#error-message").show();
        });
    }
};

rivets.bind($("#create-new-test"), {
    data: data,
    controller: controller
});