<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="tutor.categories.tests.new-test.test-title" var="testTitle"/>
<fmt:message key="tutor.categories.tests.new-test.add-new-question" var="addNewQuestion"/>
<fmt:message key="tutor.categories.tests.new-test.create-new-test" var="createNewTest"/>
<fmt:message key="tutor.categories.tests.new-test.options" var="options"/>
<fmt:message key="tutor.categories.tests.new-test.option-text" var="optionText"/>
<fmt:message key="tutor.categories.tests.new-test.question-text" var="questionText"/>
<fmt:message key="tutor.categories.tests.new-test.question-mark" var="questionMark"/>
<fmt:message key="tutor.categories.tests.new-test.is-right" var="isRight"/>
<fmt:message key="tutor.categories.tests.new-test.add-option" var="addOption"/>
<fmt:message key="tutor.categories.tests.new-test.add-question" var="addQuestion"/>

<html>
<head>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
    <title>${title}</title>
</head>
<body>
<div id=wrapper class="toggled">
    <%@ include file="/resources/jspf/sidebar.jspf" %>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div id="tutor-create-new-test">
                    <div class="row">
                        <div class="form-group col-md-10">
                            <label for="test-title">${testTitle}</label>
                            <input class="form-control" id="test-title" name="testTitle"
                                   placeholder="${testTitle}" required="true"
                                   tabindex="1" type="text" rv-value="data.testTitle">
                        </div>
                    </div>
                    <div clas="row">
                        <ul>
                            <li rv-each-question="data.questions">
                                { question.text } { question.mark }
                                <i class="fa fa-times" aria-hidden="true"
                                   rv-on-click="controller.removeQuestion"></i>
                                <ul>
                                    <li rv-each-option="question.options">
                                        { option.text } {option.isRight }
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-8">
                        <button class="btn btn-default" rv-on-click="controller.createNewTest">${createNewTest}</button>
                    </div>
                    <div class="row col-md-10" style="border-style:solid">
                        <div class="form-group col-md-10">
                            <label for="question-text">${questionText}</label>
                            <input class="form-control" id="question-text" name="questionText"
                                   placeholder="${questionText}" required="true"
                                   tabindex="1" type="text" rv-value="data.newQuestion.text">
                        </div>
                        <div class="form-group col-md-10">
                            <label for="question-mark">${questionMark}</label>
                            <input class="form-control" id="question-mark" name="questionMark"
                                   placeholder="${questionMark}" required="true"
                                   tabindex="1" type="number" rv-value="data.newQuestion.mark">
                        </div>
                        <div class="col-md-10">
                            <h3>${options}</h3>
                        </div>
                        <div class="col-md-9">
                            <ul>
                                <li rv-each-option="data.newQuestion.options">
                                    { option.text } { option.isRight }
                                    <i class="fa fa-times" aria-hidden="true"
                                       rv-on-click="controller.removeOption"></i>
                                </li>
                            </ul>
                        </div>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="option-text">${optionText}</label>
                                <input class="form-control" type="text" id="option-text"
                                       rv-value="data.newOption.text">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" rv-checked="data.newOption.isRight">
                                    ${isRight}
                                </label>
                            </div>
                            <button type="button" class="btn btn-default"
                                    rv-on-click="controller.addOption">${addOption}</button>
                        </form>
                        <button class="btn btn-default"
                                rv-on-click="controller.addQuestion">${addQuestion}</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var requestCategoryId = ${categoryId};
</script>
<script type="text/javascript" src="/resources/js/create-new-test.js"></script>
</body>
</html>
