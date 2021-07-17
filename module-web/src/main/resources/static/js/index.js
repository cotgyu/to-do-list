
var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function() {
            _this.save();
        });

    }
    // ,
    // save : function () {
    //     var data = {
    //         cardId: $('#cardId').val(),
    //     };
    //
    //     $.ajax({
    //         type: 'POST',
    //         url: '/api/card',
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8',
    //         data: JSON.stringify(data)
    //
    //     }).done(function () {
    //         alert('성공');
    //     }).fail(function (error){
    //         alert(JSON.stringify(error));
    //     });
    //
    // }

};

main.init();


const getCardDetail = function (cardId) {

    $.ajax({
        type: 'GET',
        url: '/api/card/'+ cardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',

    }).done(function (data) {

        $('#window-overlay').css("display","block");
        $('#windowCardNameArea').css("display","");
        $('#windowCardNameEditArea').css("display","none");

        $('#windowCardDescriptionArea').css("display","");
        $('#windowCardDescriptionEditArea').css("display","none");

        $('#windowCardName').text(data.result.cardName);

        if(data.result.description == undefined || data.result.description == ''){
            $('#windowCardDescription').text('');
            $('#defaultDescriptionButton').css("display","");
        }else{
            $('#defaultDescriptionButton').css("display","none");
            $('#windowCardDescription').text(data.result.description);
        }

        $('#windowCardIdArea').val(cardId);

        const rabelData = data.result.cardLabels;
        const rabelArea = document.getElementById("windowLabelsArea");

        // 라벨 영역 초기화
        while ( rabelArea.hasChildNodes() ) { rabelArea.removeChild( rabelArea.firstChild ); }

        for(var i =0; i < rabelData.length; i++){

            const divButton = document.createElement('div');
            divButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0'  id='rabel"+rabelData[i].label.label_id+"' value='"+rabelData[i].label.labelName+"' style='background-color: "+rabelData[i].label.color+"; color: white' onclick=''>";

            rabelArea.append(divButton);
        }

        const checkListsData = data.result.checkLists;
        const checkListsArea = document.getElementById("windowCheckListsArea");

        // 체크리스트 영역 초기화
        while ( checkListsArea.hasChildNodes() ) { checkListsArea.removeChild( checkListsArea.firstChild ); }

        for (var i=0; i< checkListsData.length; i++){

            const checkListNameArea = document.createElement('div');
            checkListNameArea.innerHTML = "<a id='checkLists"+checkListsData[i].id+"' onclick='javascript:windowCheckListNameEditMode("+checkListsData[i].id+")'>"+checkListsData[i].checkListName+"</a><input type='button' id='deleteListButton"+checkListsData[i].id+"' class='btn btn-link btn-sm' value='Delete List' style='margin-left: 2%; background-color: #d6d6d6; color: black' onclick='deleteCheckList("+checkListsData[i].id+")'>";

            const checkListNameEditArea = document.createElement('div');
            checkListNameEditArea.innerHTML = "<input type='textbox' name = 'windowCheckListsEdit' id='windowCheckListsEdit"+checkListsData[i].id+"' value='"+checkListsData[i].checkListName+"' style='display: none;'>";

            checkListsArea.append(checkListNameArea);
            checkListsArea.append(checkListNameEditArea);

            const checkItemsArea = document.createElement('div');

            for(var j=0; j < checkListsData[i].checkItems.length; j++){

                const rowDataArea = document.createElement('div');
                rowDataArea.className = "row";
                rowDataArea.style.marginLeft = "+0.35rem";
                rowDataArea.style.marginBottom = "+0.35rem";

                checkListsArea.append(rowDataArea);

                const checkBoxArea = document.createElement('div');
                const checked = checkListsData[i].checkItems[j].checkFlag == 'Y' ? 'checked' : "";
                checkBoxArea.innerHTML = "<input type='checkbox' id='checkItem"+checkListsData[i].checkItems[j].checkItemId+"' onchange='javascript:changeCheckItemChecked("+checkListsData[i].checkItems[j].checkItemId+")' "+checked+"/>";
                rowDataArea.append(checkBoxArea);

                const checkItemsArea = document.createElement('div');
                checkItemsArea.innerHTML = "<a id='checkItemName"+checkListsData[i].checkItems[j].checkItemId+"' onclick='javascript:checkItemNameEditMode("+checkListsData[i].checkItems[j].checkItemId+")'>"+checkListsData[i].checkItems[j].checkItemName+"</a>";
                rowDataArea.append(checkItemsArea);

                const checkItemsEditArea = document.createElement('div');
                checkItemsEditArea.innerHTML = "<input type='textbox' id='checkItemNameEdit"+checkListsData[i].checkItems[j].checkItemId+"' value='"+checkListsData[i].checkItems[j].checkItemName+"' style='display: none;'><input type='button' id='deleteItemButton"+checkListsData[i].checkItems[j].checkItemId+"' class='btn btn-link btn-sm' value='Delete Item' style='margin-left: 80%; height: 90%; background-color: #d6d6d6; color: black' onclick='deleteCheckItem("+checkListsData[i].checkItems[j].checkItemId+")'>";
                rowDataArea.append(checkItemsEditArea);

            }
            checkListsArea.append(checkItemsArea);

            const addCheckItemArea = document.createElement('div');
            addCheckItemArea.setAttribute("id", "checkItemsViewArea"+checkListsData[i].id);
            addCheckItemArea.style.marginLeft = "+0.35rem";
            addCheckItemArea.style.marginBottom = "+1.5rem";

            addCheckItemArea.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0'  id='windowCheckItemAddBtn"+checkListsData[i].id+"' value='Add an Item' style='background-color: #d6d6d6; color: black' onclick='addCheckItem("+checkListsData[i].id+")'>";
            checkItemsArea.append(addCheckItemArea);
        }


    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const cardDetailClose = function (){
    //$('#window-overlay').css("display","none");
    location.reload();
}

const updateCardName = function (){

    var data = {
        cardName: $('#windowCardNameEdit').val(),
        description: $('#windowCardDescription').text(),
        delFlag: 'N'
    };

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/'+ cardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {
        $('#windowCardName').css("display","inline");

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const windowCardEditMode = function (){

    $('#windowCardNameArea').css("display","none");
    $('#windowCardNameEditArea').css("display","");

    const windowCardNameEditArea = document.getElementById("windowCardNameEditArea");

    while ( windowCardNameEditArea.hasChildNodes() ) { windowCardNameEditArea.removeChild( windowCardNameEditArea.firstChild ); }

    const cardName = $('#windowCardName').text();
    const windowCardNameEdit = document.createElement('div');
    windowCardNameEdit.innerHTML = "<input type='text' id='windowCardNameEdit' value="+cardName+">";

    windowCardNameEditArea.append(windowCardNameEdit);

    const cardId = $('#windowCardIdArea').val();

    $('#windowCardNameEdit').focus();
    $('#windowCardNameEdit').on('focusout', function (){
        if($('#windowCardNameEdit').val() == ''){
            getCardDetail(cardId);
        }else {
            updateCardName();
        }
    });

    $('#windowCardNameEdit').keypress(function(e){
        if(e.keyCode == 13){
            if($('#windowCardNameEdit').val() == ''){
                getCardDetail(cardId);
            }else {
                updateCardName();
            }
        }
    });
}


const updateCardDescription = function (){

    var data = {
        cardName: $('#windowCardName').text(),
        description: $('#windowCardDescriptionEdit').val(),
        delFlag: 'N'
    };

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/'+ cardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {
        $('#windowCardName').css("display","inline");

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const deleteCard = function (){

    var data = {
        cardName: $('#windowCardName').text(),
        description: $('#windowCardDescriptionEdit').val(),
        delFlag: 'Y'
    };

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/'+ cardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {
        $('#windowCardName').css("display","inline");

        cardDetailClose();

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}



const windowCardDescriptionEditMode = function (){

    $('#windowCardDescriptionArea').css("display","none");
    $('#windowCardDescriptionEditArea').css("display","");

    const windowCardDescriptionEditArea = document.getElementById("windowCardDescriptionEditArea");

    while ( windowCardDescriptionEditArea.hasChildNodes() ) { windowCardDescriptionEditArea.removeChild( windowCardDescriptionEditArea.firstChild ); }

    const description = $('#windowCardDescription').text();
    const windowCardDescriptionEdit = document.createElement('div');
    windowCardDescriptionEdit.innerHTML = "<input type='textbox' id='windowCardDescriptionEdit' style='width: 85%; margin-right: 5px' size='100' value="+description+">" +
        "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' style='background-color: green; color: white;' value='SAVE' onclick='javascript:updateCardDescription()'>";

    windowCardDescriptionEditArea.append(windowCardDescriptionEdit);

    $('#windowCardDescriptionEdit').on('focusout', function (){
        updateCardDescription();
    });

    $('#windowCardDescriptionEdit').keypress(function(e){
        if(e.keyCode == 13){
            updateCardDescription();
        }
    });
}

const changeCheckItemChecked = function (itemId){

    const checked = $('#checkItem'+itemId).is(":checked") ? "Y" : "N";

    const checkItemName = $('#checkItemName'+itemId).text();

    const data = {
        checkItemName:checkItemName,
        delFlag: 'N',
        checkFlag: checked
    }

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/checkList/checkItem/'+ itemId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const windowCheckListNameEditMode = function (checkListId){

    $('#checkLists'+checkListId).css("display","none");
    $('#deleteListButton'+checkListId).css("display","none");

    $('#windowCheckListsEdit'+checkListId).css("display","");
    $('#windowCheckListsEditButton'+checkListId).css("display","");


    const cardId = $('#windowCardIdArea').val();
    $('#windowCheckListsEdit'+checkListId).focus();
    $('#windowCheckListsEdit'+checkListId).on('focusout', function (){
        if($('#windowCheckListsEdit'+checkListId).val() == ''){
            getCardDetail(cardId);
        }else {
            updateCheckListName(checkListId);
        }
    });

    $('#windowCheckListsEdit'+checkListId).keypress(function(e){
        if(e.keyCode == 13){
            if($('#windowCheckListsEdit'+checkListId).val() == ''){
                getCardDetail(cardId);
            }else {
                updateCheckListName(checkListId);
            }
        }
    });
}


const updateCheckListName = function (checkListId){

    const checkListTitle = $('#windowCheckListsEdit'+checkListId).val();
    const cardId = $('#windowCardIdArea').val();

    const data = {
        checkListTitle:checkListTitle,
        delFlag: 'N',
        cardId: cardId
    }

    $.ajax({
        type: 'PUT',
        url: '/api/card/checkList/'+ checkListId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const deleteCheckList = function (checkListId){

    const checkListTitle = $('#windowCheckListsEdit'+checkListId).val();
    const cardId = $('#windowCardIdArea').val();

    const data = {
        checkListTitle:checkListTitle,
        delFlag: 'Y',
        cardId: cardId
    }

    $.ajax({
        type: 'PUT',
        url: '/api/card/checkList/'+ checkListId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}



const checkItemNameEditMode = function (checkItemId){

    $('#checkItemName'+checkItemId).css("display","none");
    $('#deleteItemButton'+checkItemId).css("display","none");
    $('#checkItemNameEdit'+checkItemId).css("display","");
    $('#checkItemNameEditButton'+checkItemId).css("display","");


    const cardId = $('#windowCardIdArea').val();
    $('#checkItemNameEdit'+checkItemId).focus();
    $('#checkItemNameEdit'+checkItemId).on('focusout', function (){
        if($('#checkItemNameEdit'+checkItemId).val() == ''){
            getCardDetail(cardId);
        }else {
            updateCheckItemName(checkItemId);
        }
    });

    $('#checkItemNameEdit'+checkItemId).keypress(function(e){
        if(e.keyCode == 13){
            if($('#checkItemNameEdit'+checkItemId).val() == ''){
                getCardDetail(cardId);
            }else {
                updateCheckItemName(checkItemId);
            }
        }
    });
}

const updateCheckItemName = function (itemId){

    const checked = $('#checkItem'+itemId).is(":checked") ? "Y" : "N";

    const checkItemName = $('#checkItemNameEdit'+itemId).val();

    const data = {
        checkItemName:checkItemName,
        delFlag: 'N',
        checkFlag: checked
    }

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/checkList/checkItem/'+ itemId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const deleteCheckItem = function (itemId){

    const checked = $('#checkItem'+itemId).is(":checked") ? "Y" : "N";

    const checkItemName = $('#checkItemNameEdit'+itemId).val();

    const data = {
        checkItemName:checkItemName,
        delFlag: 'Y',
        checkFlag: checked
    }

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'PUT',
        url: '/api/card/checkList/checkItem/'+ itemId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}


const addCheckItem = function (checkListId){

    $('#windowCheckItemAddBtn'+checkListId).css("display","none");

    const checkItemsViewArea = $('#checkItemsViewArea'+checkListId);

    const addCheckItemDiv = document.createElement('div');
    addCheckItemDiv.innerHTML = "<input type='textbox' id='addCheckItemText'>" +
        "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='labelSaveButton' value='SAVE' style='background-color: #d6d6d6; color: black' onclick='javascript:saveCheckItem("+checkListId+")'>";

    checkItemsViewArea.append(addCheckItemDiv);

    const cardId = $('#windowCardIdArea').val();

    $('#addCheckItemText').focus();

    $('#addCheckItemText').on('focusout', function (){
        if($('#addCheckItemText').val() == ''){
            getCardDetail(cardId);
        }
    });

    $('#addCheckItemText').keypress(function(e){
        if(e.keyCode == 13){
            if($('#addCheckItemText').val() == ''){
                getCardDetail(cardId);
            } else {
                saveCheckItem(checkListId);
            }
        }
    });

}

const saveCheckItem = function (checkListId){

    const data = {
        checkListId: checkListId,
        delFlag: 'N',
        checkFlag: 'N',
        checkItemName: $('#addCheckItemText').val()
    }

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'POST',
        url: '/api/card/checkList/checkItem',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {

        getCardDetail(cardId);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const addCheckList = function (){

    const cardId = $('#windowCardIdArea').val();

    const data = {
        checkListTitle: 'CheckList',
        cardId: cardId,
        delFlag: 'N'
    }

    $.ajax({
        type: 'POST',
        url: '/api/card/checkList',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        getCardDetail(cardId);
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const viewEditLabelsArea = function (){

    if($('#labelsEditArea').css('display') == 'block'){
        $('#labelsEditArea').css("display","none");

        const labelsEditArea = document.getElementById('labelsEditArea');
        while ( labelsEditArea.hasChildNodes() ) { labelsEditArea.removeChild( labelsEditArea.firstChild ); }

        return;
    }

    const cardId = $('#windowCardIdArea').val();

    $.ajax({
        type: 'GET',
        url: '/api/card/label/'+ cardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',

    }).done(function (data) {
        $('#labelsEditArea').css("display","");

        const labelsEditArea = document.getElementById('labelsEditArea');
        while ( labelsEditArea.hasChildNodes() ) { labelsEditArea.removeChild( labelsEditArea.firstChild ); }

        const cardLabelData = data.result;

        for(var i =0; i < cardLabelData.length; i++){
            const windowLabelsEdit = document.createElement('div');
            windowLabelsEdit.className = "row";

            const windowLabelsButton = document.createElement('div');
            windowLabelsButton.style.marginRight = "+0.35rem";

            windowLabelsButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='cardLabel"+cardLabelData[i].labelId+"' value='"+cardLabelData[i].labelName+"' style='background-color: "+cardLabelData[i].color+"; color: white' onclick=''>" +
                "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' value='UPDATE' style='background-color: #d6d6d6; color: black' onclick='javascript:editLabelMode("+cardLabelData[i].labelId+")'>" +
                "<input type='hidden' id='labelColorHidden"+cardLabelData[i].labelId+"' value='"+cardLabelData[i].color+"'>";
            windowLabelsEdit.append(windowLabelsButton);


            const labelCheckBoxArea = document.createElement('div');
            const checked = (cardLabelData[i].checkFlag == 'Y') ? 'checked' : '';
            labelCheckBoxArea.innerHTML = "<input type='checkbox' id='checkCardLabel"+cardLabelData[i].labelId+"' onchange='javascript:updateCardLabel("+cardLabelData[i].labelId+")' "+checked+"/>";
            windowLabelsEdit.append(labelCheckBoxArea);

            labelsEditArea.append(windowLabelsEdit);
        }

        const windowCreateLabelsButton = document.createElement('div')
        windowCreateLabelsButton.setAttribute("id", "createLabelArea");

        windowCreateLabelsButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='createLabelButton' value='Create new Label' style='background-color: #d6d6d6; color: black' onclick='javascript:createLabelMode()'>";
        labelsEditArea.append(windowCreateLabelsButton);

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const updateCardLabel = function (labelId){

    const cardId = $('#windowCardIdArea').val();
    const checkedValue = $('#checkCardLabel'+labelId).is(":checked");

    const data = {
        label_id: labelId,
        checkFlag: checkedValue,
        card_id: cardId
    }

    $.ajax({
        type: 'PUT',
        url: '/api/card/label/cardLabel',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        getCardDetail(cardId);
        viewEditLabelsArea();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const createLabelMode = function (){

    const labelsEditArea = document.getElementById('labelsEditArea');
    while ( labelsEditArea.hasChildNodes() ) { labelsEditArea.removeChild( labelsEditArea.firstChild ); }

    const labelNameInput = document.createElement('div');
    labelNameInput.innerHTML = "<input type='text' id='windowLabelNameInput'>";

    const labelColorInput = document.createElement('div');
    labelColorInput.innerHTML = "<input type='text' id='windowLabelColorInput' value='green'>";

    const labelSaveButton = document.createElement('div');
    labelSaveButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='labelSaveButton' value='SAVE' style='background-color: #d6d6d6; color: black' onclick='javascript:saveLabel()'>";

    labelsEditArea.append(labelNameInput);
    labelsEditArea.append(labelColorInput);
    labelsEditArea.append(labelSaveButton);
}

const saveLabel = function (){
    const cardId = $('#windowCardIdArea').val();

    const labelName = $('#windowLabelNameInput').val();
    const labelColor = $('#windowLabelColorInput').val();

    const data = {
        labelName: labelName,
        color: labelColor
    }

    $.ajax({
        type: 'POST',
        url: '/api/card/label',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {

        getCardDetail(cardId);
        viewEditLabelsArea();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const editLabelMode = function (labelId){

    const labelName = $('#cardLabel'+labelId).val();
    const labelColor = $('#labelColorHidden'+labelId).val();

    const labelsEditArea = document.getElementById('labelsEditArea');
    while ( labelsEditArea.hasChildNodes() ) { labelsEditArea.removeChild( labelsEditArea.firstChild ); }

    const labelNameInput = document.createElement('div');
    labelNameInput.innerHTML = "<input type='text' id='windowUpdateLabelNameInput' value='"+labelName+"'>";

    const labelColorInput = document.createElement('div');
    labelColorInput.innerHTML = "<input type='text' id='windowUpdateLabelColorInput' value='"+labelColor+"'>";

    const labelSaveButton = document.createElement('div');
    labelSaveButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='labelUpdateButton' value='UPDATE' style='background-color: #d6d6d6; color: black' onclick='javascript:updateLabel("+labelId+")'>";

    labelsEditArea.append(labelNameInput);
    labelsEditArea.append(labelColorInput);
    labelsEditArea.append(labelSaveButton);
}

const updateLabel = function (labelId){

    const cardId = $('#windowCardIdArea').val();

    const labelName = $('#windowUpdateLabelNameInput').val();
    const labelColor = $('#windowUpdateLabelColorInput').val();

    const data = {
        labelName: labelName,
        color: labelColor,
        delFlag: 'N'
    }

    $.ajax({
        type: 'PUT',
        url: '/api/card/label/'+labelId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {

        getCardDetail(cardId);
        viewEditLabelsArea();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const addTopicMode = function (){

    $('#addTopicButton').css('display', 'none');
    $('#addTopicNameInput').css('display', '');
    $('#saveTopicButton').css('display', '');

    $('#addTopicNameInput').focus();
    $('#addTopicNameInput').on('focusout', function (){
        if($('#addTopicNameInput').val() == ''){
            location.reload();
        }
    });

    $('#addTopicNameInput').keypress(function(e){
        if(e.keyCode == 13){
            if($('#addTopicNameInput').val() == ''){
                location.reload();
            }else {
                saveTopic();
            }
        }
    });

}

const saveTopic = function (){

    const topicName = $('#addTopicNameInput').val();

    if(topicName == ''){
        location.reload();
        return;
    }
    const boardId = $('#boardIdHidden').val();

    const data = {
        boardId: boardId,
        topicName: topicName
    }

    $.ajax({
        type: 'POST',
        url: '/api/topic',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const changeTopicNameMode = function (topicId){
    $('#topicView'+topicId).css('display','none');
    $('#editTopicNameInput'+topicId).css('display','');
    $('#editTopicButton'+topicId).css('display','');

    $('#editTopicNameInput'+topicId).focus();
    $('#editTopicNameInput'+topicId).on('focusout', function (){
        if($('#editTopicNameInput'+topicId).val() == ''){
            location.reload();
        }else {
            editTopicName(topicId);
        }
    });

    $('#editTopicNameInput'+topicId).keypress(function(e){
        if(e.keyCode == 13){
            if($('#editTopicNameInput'+topicId).val() == ''){
                location.reload();
            }else {
                editTopicName(topicId);
            }
        }
    });
}

const editTopicName = function (topicId){

    const topicName = $('#editTopicNameInput'+topicId).val();

    if(topicName == ''){
        location.reload();
        return;
    }

    const data = {
        id:topicId,
        topicName: topicName,
        delFlag: 'N'
    }

    $.ajax({
        type: 'PUT',
        url: '/api/topic/'+topicId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const addCardMode = function (topicId){
    $('#addCardButton'+topicId).css('display','none');
    $('#addCardInput'+topicId).css('display','');
    $('#saveCardButton'+topicId).css('display','');

    $('#addCardInput'+topicId).focus();
    $('#addCardInput'+topicId).on('focusout', function (){
        if($('#addCardInput'+topicId).val() == ''){
            $('#addCardButton'+topicId).css('display','');
            $('#addCardInput'+topicId).css('display','none');
            $('#saveCardButton'+topicId).css('display','none');
        }
    });

    $('#addCardInput'+topicId).keypress(function(e){
        if(e.keyCode == 13){
            if($('#addCardInput'+topicId).val() == ''){
                $('#addCardButton'+topicId).css('display','');
                $('#addCardInput'+topicId).css('display','none');
                $('#saveCardButton'+topicId).css('display','none');
            } else {
                saveCard(topicId);
            }
        }
    });

}

const saveCard = function (topicId){

    const cardName = $('#addCardInput'+topicId).val();
    const data = {
        topicId: topicId,
        cardName: cardName
    }

    $.ajax({
        type: 'POST',
        url: '/api/card',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

const deleteTopic = function (topicId) {

    if(confirm('정말로 삭제하시겠습니까?')){
        const topicName = $('#topicView'+topicId).val();

        const data = {
            id:topicId,
            topicName: topicName,
            delFlag: 'Y'
        }

        $.ajax({
            type: 'PUT',
            url: '/api/topic/'+topicId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function () {
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }
}