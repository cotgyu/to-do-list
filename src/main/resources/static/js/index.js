
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
        $('#windowCardDescription').text(data.result.description)

    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}

const cardDetailClose = function (){
    $('#window-overlay').css("display","none");
}