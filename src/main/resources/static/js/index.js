
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
        $('#windowCardName').text(data.result.cardName);
        $('#windowCardDescription').text(data.result.description);
        $('#testData').text((JSON.stringify(data.result.checkLists)));

        const rabelData = data.result.cardLabels;
        const rabelArea = document.getElementById("labelsArea");

        // 라벨 영역 초기화
        while ( rabelArea.hasChildNodes() ) { rabelArea.removeChild( rabelArea.firstChild ); }

        for(var i =0; i < rabelData.length; i++){
            const divButton = document.createElement('div');
            divButton.innerHTML = "<input type='button' class='btn btn-link btn-sm order-1 order-lg-0'  id='rabel"+rabelData[i].label.label_id+"' value='"+rabelData[i].label.labelName+"' style='background-color: "+rabelData[i].label.color+"; color: white' onclick=''>";

            rabelArea.append(divButton);
        }


    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const cardDetailClose = function (){
    $('#window-overlay').css("display","none");
}