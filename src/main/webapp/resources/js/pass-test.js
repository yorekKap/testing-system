var data = {
    testId: requestTestId,
    selectedOptionIds: []
};

var controller = {
    toggleOption: function(event, model){
        var optionId = parseInt(event.target.dataset.optionId);

        var index = data.selectedOptionIds.indexOf(optionId);
        if(index === -1){
            data.selectedOptionIds.push(optionId);
        } else {
            data.selectedOptionIds.splice(index, 1);
        }
    },

    sendResults: function(event, model){
        $.post("/student/category/tests/pass-test", {content: JSON.stringify(data)})
            .then(function (data) {
                var url = "/student/category/tests/test-record?testRecordId=" + data;
                location.href=url;
            })
    }
}

rivets.bind($("#pass-test"), {
    data: data,
    controller: controller
});

