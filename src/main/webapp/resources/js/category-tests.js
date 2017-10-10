var data = {
    isCategoryTitleEditable: false,
    newCategoryTitle: categoryTitle,
    testEdits: requestTestEdits,
    editableToggler: false
};

var controller = {
    editCategoryTitle: function (event, model) {
        data.isCategoryTitleEditable = true;
    },

    updateCategoryTitle: function (event, model) {
        $.post("/tutor/categories",
            {
                action: "UPDATE",
                title: data.newCategoryTitle,
                id: categoryId
            })
            .then(function () {
                location.reload();
            });
    },

    editTestTitle: function (event, model) {
        var index = event.target.dataset.index;
        var testEdit = data.testEdits[index];

        testEdit.isEditable = true;
        data.editableToggler = !data.editableToggler;
    },

    updateTestTitle: function (event, model) {
        var index = event.target.dataset.index;
        var testTitle = event.target.value;
        var testEdit = data.testEdits[index];

        $.post("/tutor/category/test", {
            action: "UPDATE",
            title: testTitle,
            orderNumber: testEdit.orderNumber,
            id: testEdit.id
        }).then(function () {
            location.reload();
        });
    },

    deleteTest: function (event, model) {
        var testId = event.target.dataset.testId;

        $.post("/tutor/category/test", {
            action: "DELETE",
            testId: testId
        }).then(function () {
            location.reload();
        });
    }
};

rivets.formatters.isEditable = function (value, index) {
    return data.testEdits[index].isEditable;
};

rivets.formatters.editableTestTitle = {
    read: function (value, index) {
        return data.testEdits[index].title;
    },

    publish: function (value, index) {
        return data.testEdits[index].title;
    }
};

rivets.bind($("#category-tests"), {
    data: data,
    controller: controller
});

