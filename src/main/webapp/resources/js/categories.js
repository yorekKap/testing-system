console.log("FUCK");
var data = {
    newCategoryTitle: "",
};

var controller = {
    createNewCategory: function (event, model) {
        var postData = {
            title: data.newCategoryTitle,
            action: "CREATE"
        };

        $.post("/tutor/categories", postData)
            .then(function (data) {
                location.reload();
            });
    },

    deleteCategory: function (event, model) {
        var id = event.target.dataset.categoryId;

        $.post("/tutor/categories", {action: "DELETE", categoryId: id})
            .then(function () {
                location.reload();
            });

    }
}

rivets.bind($("#sidebar"), {
    data: data,
    controller: controller
});

