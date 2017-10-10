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

var controller = {
    addQuestion: function (event, model) {
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
        $.post("/tutor/category/test", {
            action: "CREATE",
            content: JSON.stringify({
                    title: data.testTitle, orderNumber: 1,
                    questions: data.questions,
                    categoryId: data.categoryId
                }
            )
        })
            .then(function () {
                window.location = "/tutor/category?categoryId=" + data.categoryId;
            })
    }
};

rivets.bind($("#create-new-test"), {
    data: data,
    controller: controller
});