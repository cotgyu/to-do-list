
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

        if(data.result.description == ''){
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

            // TODO java에서 가공해서 줄 것
            if(checkListsData[i].delFlag == 'Y'){
                continue;
            }

            const checkListNameArea = document.createElement('div');
            checkListNameArea.innerHTML = "<a id='checkLists"+checkListsData[i].id+"' onclick='javascript:windowCheckListNameEditMode("+checkListsData[i].id+")'>"+checkListsData[i].checkListName+"</a><input type='button' class='btn btn-link btn-sm' value='Delete List' style='margin-left: 2%; background-color: #d6d6d6; color: black' onclick='deleteCheckList("+checkListsData[i].id+")'>";

            const checkListNameEditArea = document.createElement('div');
            checkListNameEditArea.innerHTML = "<input type='textbox' name = 'windowCheckListsEdit' id='windowCheckListsEdit"+checkListsData[i].id+"' value='"+checkListsData[i].checkListName+"' style='display: none;'>";

            checkListsArea.append(checkListNameArea);
            checkListsArea.append(checkListNameEditArea);

            const checkItemsArea = document.createElement('div');

            for(var j=0; j < checkListsData[i].checkItems.length; j++){

                // TODO java에서 가공해서 줄 것
                if(checkListsData[i].checkItems[j].delFlag== 'Y'){
                    continue;
                }

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
                checkItemsEditArea.innerHTML = "<input type='textbox' id='checkItemNameEdit"+checkListsData[i].checkItems[j].checkItemId+"' value='"+checkListsData[i].checkItems[j].checkItemName+"' style='display: none;'><input type='button' class='btn btn-link btn-sm' value='Delete Item' style='margin-left: 80%; height: 90%; background-color: #d6d6d6; color: black' onclick='deleteCheckItem("+checkListsData[i].checkItems[j].checkItemId+")'>";
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

    $('#windowCardNameEdit').keyup(function(e){
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
    windowCardDescriptionEdit.innerHTML = "<input type='textbox' id='windowCardDescriptionEdit' value="+description+">";

    const windowCardDescriptionEditButton = document.createElement('div');
    windowCardDescriptionEditButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' style='background-color: green; color: white;' value='SAVE' onclick='javascript:updateCardDescription()'>";

    windowCardDescriptionEditArea.append(windowCardDescriptionEdit);
    windowCardDescriptionEditArea.append(windowCardDescriptionEditButton);

    $('#windowCardDescriptionEdit').on('focusout', function (){
        updateCardDescription();
    });

    $('#windowCardDescriptionEdit').keyup(function(e){
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

    $('#windowCheckListsEdit'+checkListId).keyup(function(e){
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

    $('#checkItemNameEdit'+checkItemId).keyup(function(e){
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
    addCheckItemDiv.innerHTML = "<input type='textbox' id='addCheckItemText'>";

    checkItemsViewArea.append(addCheckItemDiv);

    const cardId = $('#windowCardIdArea').val();

    $('#addCheckItemText').focus();

    $('#addCheckItemText').on('focusout', function (){
        if($('#addCheckItemText').val() == ''){
            getCardDetail(cardId);
        } else {
            saveCheckItem(checkListId);
        }

        return;
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

            windowLabelsButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' id='cardLabel"+cardLabelData[i].labelId+"' value='"+cardLabelData[i].labelName+"' style='background-color: "+cardLabelData[i].color+"; color: white' onclick=''>";
            windowLabelsEdit.append(windowLabelsButton);


            const labelCheckBoxArea = document.createElement('div');
            const checked = cardLabelData[i].checkFlag == 'Y' ? 'checked' : "";
            labelCheckBoxArea.innerHTML = "<input type='checkbox' id='checkItem"+cardLabelData[i].labelId+"' onchange='' "+checked+"/>";
            windowLabelsEdit.append(labelCheckBoxArea);


            labelsEditArea.append(windowLabelsEdit);
        }




    }).fail(function (error){
        alert(JSON.stringify(error));
    });


}