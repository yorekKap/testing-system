function categories() {
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

        },

        printTitle: function (event, model) {
            console.log(data.newCategoryTitle);
        }
    }

    return {
        data: data,
        controller: controller
    };

}

rivets.bind($("#sidebar"), categories());
