
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
        $('#windowCardDescription').text(data.result.description);
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
            checkListNameArea.innerHTML = "<a id='checkLists"+checkListsData[i].id+"' onclick=''>"+checkListsData[i].checkListName+"</a>";

            checkListsArea.append(checkListNameArea);

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
                checkItemsArea.innerHTML = "<a id='checkItemName"+checkListsData[i].checkItems[j].checkItemId+"' onclick=''>"+checkListsData[i].checkItems[j].checkItemName+"</a>";
                rowDataArea.append(checkItemsArea);
            }

            checkListsArea.append(checkItemsArea);
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

    const windowCardNameEditButton = document.createElement('div');
    windowCardNameEditButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' value='SAVE' onclick='javascript:updateCardName()'>";

    windowCardNameEditArea.append(windowCardNameEdit);
    windowCardNameEditArea.append(windowCardNameEditButton);

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

const windowCardDescriptionEditMode = function (){

    $('#windowCardDescriptionArea').css("display","none");
    $('#windowCardDescriptionEditArea').css("display","");

    const windowCardDescriptionEditArea = document.getElementById("windowCardDescriptionEditArea");

    while ( windowCardDescriptionEditArea.hasChildNodes() ) { windowCardDescriptionEditArea.removeChild( windowCardDescriptionEditArea.firstChild ); }

    const description = $('#windowCardDescription').text();
    const windowCardDescriptionEdit = document.createElement('div');
    windowCardDescriptionEdit.innerHTML = "<input type='textbox' id='windowCardDescriptionEdit' value="+description+">";

    const windowCardDescriptionEditButton = document.createElement('div');
    windowCardDescriptionEditButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0' value='SAVE' onclick='javascript:updateCardDescription()'>";

    windowCardDescriptionEditArea.append(windowCardDescriptionEdit);
    windowCardDescriptionEditArea.append(windowCardDescriptionEditButton);

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
